package linux.exceptions;

public class FileSystemEntityAlreadyExists extends Exception {
    public FileSystemEntityAlreadyExists() {
        super("File System Entity already exist.");
    }
}
