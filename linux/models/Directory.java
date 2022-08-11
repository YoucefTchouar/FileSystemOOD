package linux.models;

import linux.exceptions.FileSystemEntityAlreadyExists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Directory implements FileSystemEntity {
    private Map<String, FileSystemEntity> fileSystemEntities;
    private String name;
    private String directory;

    public Directory(String name, String directory) {
        this.name = name;
        this.directory = directory;
        this.fileSystemEntities = new HashMap<>();
    }

    public boolean addFileSystemEntity(FileSystemEntity fileSystemEntity) throws FileSystemEntityAlreadyExists {
        return this.addFileSystemEntity(fileSystemEntity, false);
    }

    public boolean addFileSystemEntity(FileSystemEntity fileSystemEntity, boolean overwrite) throws FileSystemEntityAlreadyExists {
        if(!overwrite && this.containsDuplicate(fileSystemEntity))
            throw new FileSystemEntityAlreadyExists();

        this.fileSystemEntities.put(fileSystemEntity.getName(), fileSystemEntity);
        return true;
    }

    public Directory getOrCreateDirectory(String name) {
        if((this.fileSystemEntities.containsKey(name) && !this.fileSystemEntities.get(name).isDirectory()) || !this.fileSystemEntities.containsKey(name))
            this.fileSystemEntities.put(name, new Directory(name, null));

        return (Directory) this.fileSystemEntities.get(name);
    }

    public boolean contains(String name) {
        return this.fileSystemEntities.containsKey(name);
    }

    public Directory getDirectory(String name) {
        if(!this.fileSystemEntities.containsKey(name))
            return null;

        FileSystemEntity result = this.fileSystemEntities.get(name);

        return result.isDirectory() ? (Directory) result : null;
    }

    public List getAllOfType(Class clazz) {
        List<FileSystemEntity> result = new ArrayList<>();

        for(Map.Entry<String, FileSystemEntity> entry : this.fileSystemEntities.entrySet())
            if(clazz.isInstance(entry.getValue()))
                result.add(entry.getValue());

        return result;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDirectory() {
        return this.directory;
    }

    @Override
    public String toString() {
        return String.format("{%s}", this.name);
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    private boolean containsDuplicate(FileSystemEntity fileSystemEntity) {
        return (
                fileSystemEntity.isDirectory() &&
                        this.fileSystemEntities.containsKey(fileSystemEntity.getName())
                        && this.fileSystemEntities.get(fileSystemEntity.getName()).isDirectory()
        ) &&
                (
                        !fileSystemEntity.isDirectory() &&
                                this.fileSystemEntities.containsKey(fileSystemEntity.getName()) &&
                                !this.fileSystemEntities.get(fileSystemEntity.getName()).isDirectory()
                );
    }
}
