package linux.models;

import linux.exceptions.FileSystemEntityAlreadyExists;
import linux.exceptions.FileSystemEntityNotFound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileSystem {
    private Directory root;

    public FileSystem() {
        this.root = new Directory("/", null);
    }

    public void addFileSystemEntity(String path, FileSystemEntity fileSystemEntity) throws FileSystemEntityAlreadyExists {
        this.addFileSystemEntityHelper((path.length() > 0 && path.charAt(0) == '/' ? path.substring(1) : path).split("/"), this.root, fileSystemEntity, 0);
    }

    public List<File> getFiles(String name) throws FileSystemEntityNotFound {
        return this.getFileSystemEntity(name, File.class);
    }

    public List<Directory> getDirectories(String name) throws FileSystemEntityNotFound {
        return this.getFileSystemEntity(name, Directory.class);
    }

    private List getFileSystemEntity(String path, Class clazz) throws FileSystemEntityNotFound {
        List<FileSystemEntity> result = new ArrayList<>();

        return this.getFileSystemEntityHelper((path.length() > 0 && path.charAt(0) == '/' ? path.substring(1) : path).split("/"), clazz, this.root, 0, result);
    }

    private void addFileSystemEntityHelper(String[] path, Directory root, FileSystemEntity fileSystemEntity, int index) throws FileSystemEntityAlreadyExists {
        if(index == path.length)
            root.addFileSystemEntity(fileSystemEntity);
        else
            this.addFileSystemEntityHelper(path, root.getOrCreateDirectory(path[index]), fileSystemEntity, index + 1);
    }

    private List getFileSystemEntityHelper(String[] path, Class clazz, Directory root, int index, List<FileSystemEntity> result) {
        if (root == null)
            return result;

        if (path.length == index) {
            result.addAll(root.getAllOfType(clazz));
            return result;
        }

        if (path[index].equals("*")) {
            List<Directory> directories = root.getAllOfType(Directory.class);

            for(Directory directory : directories) {
                getFileSystemEntityHelper(path, clazz, directory, index + 1, result);
                getFileSystemEntityHelper(path, clazz, directory, index, result);
            }
        } else {
            getFileSystemEntityHelper(path, clazz, root.getDirectory(path[index]), index + 1, result);
        }

        return result;
    }
}
