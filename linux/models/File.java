package linux.models;

public class File implements FileSystemEntity {
    private String name;
    private String extension;
    private int size;
    private String directory;

    public File(String directory, String name, String extension, int size) {
        this.directory = directory;
        this.name = name;
        this.extension = extension;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public String getName() {
        return name;
    }
    public String getDirectory() {
        return directory;
    }

    @Override
    public String toString() {
        return String.format("{name: %s, extension: %s, size: %2d}", this.name, this.extension, this.size);
    }
}
