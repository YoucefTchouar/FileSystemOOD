package filters;

import linux.models.File;
import operators.EqualTo;

public class ExtensionFilter extends Filter<String> implements IFilter<File> {
    private String extension;

    public ExtensionFilter(String extension) {
        this.operator = new EqualTo<>();
        this.extension = extension;
    }

    @Override
    public boolean apply(File file) {
        return this.operator.apply(file.getExtension(), this.extension);
    }
}
