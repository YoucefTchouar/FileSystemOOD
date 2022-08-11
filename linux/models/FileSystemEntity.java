package linux.models;

public interface FileSystemEntity {
    default boolean isDirectory() {
        return false;
    }

    String getName();
    String getDirectory();
}
