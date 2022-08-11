package filters;

import linux.models.File;
import operators.EqualTo;

public class NameFilter extends Filter<String> implements IFilter<File> {
    private String name;

    public NameFilter(String name) {
        this.operator = new EqualTo<>();
        this.name = name;
    }

    @Override
    public boolean apply(File file) {
        return this.operator.apply(file.getName(), this.name);
    }
}
