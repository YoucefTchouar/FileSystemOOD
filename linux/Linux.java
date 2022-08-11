package linux;

import filters.IFilter;
import linux.exceptions.FileSystemEntityAlreadyExists;
import linux.exceptions.FileSystemEntityNotFound;
import linux.models.File;
import linux.models.FileSystem;
import linux.models.FileSystemEntity;

import java.util.List;
import java.util.ArrayList;

public class Linux {
    private FileSystem fileSystem;

    public Linux() {
        this.fileSystem = new FileSystem();
    }

    public void addFile(FileSystemEntity fileSystemEntity) throws FileSystemEntityAlreadyExists {
        this.fileSystem.addFileSystemEntity(fileSystemEntity.getDirectory(), fileSystemEntity);
    }

    public void addManyFiles(List<FileSystemEntity> fileSystemEntities) throws FileSystemEntityAlreadyExists {
        for(FileSystemEntity fileSystemEntity : fileSystemEntities)
            this.addFile(fileSystemEntity);
    }

    public List<File> find(String path, List<IFilter<File>> filters) throws FileSystemEntityNotFound {
        List<File> files = this.fileSystem.getFiles(path);
        List<File> result = new ArrayList<>();

        for(File file : files) {
            boolean valid = true;
            for(IFilter<File> filter : filters)
                valid &= filter.apply(file);
            if(valid)
                result.add(file);
        }

        return result;
    }
}
