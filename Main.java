import filters.ExtensionFilter;
import filters.IFilter;
import filters.SizeFilter;
import linux.Linux;
import linux.exceptions.FileSystemEntityAlreadyExists;
import linux.exceptions.FileSystemEntityNotFound;
import linux.models.File;
import linux.models.FileSystemEntity;
import operators.GreaterThan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileSystemEntityNotFound, FileSystemEntityAlreadyExists {
        Linux linux = new Linux();

        List<IFilter<File>> filters = new ArrayList<>(
                Arrays.asList(
                        new ExtensionFilter("txt"),
                        new SizeFilter(10, new GreaterThan<>())
                )
        );
        List<FileSystemEntity> files = new ArrayList<>(
                Arrays.asList(
                        new File("/hello/world", "file1", "txt", 10),
                        new File("/hello", "file2", "csv", 12),
                        new File("/world", "file3", "txt", 13)
                )
        );

        linux.addManyFiles(files);

        List<File> result = linux.find("/*", filters);

        System.out.println(result);
    }
}
