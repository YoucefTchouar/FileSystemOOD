package filters;

import linux.models.File;
import operators.Operator;

public class SizeFilter extends Filter<Integer> implements IFilter<File> {
    private int size;
    private Operator<Integer> operator;

    public SizeFilter(int size, Operator<Integer> operator) {
        this.operator = operator;
        this.size = size;
    }

    @Override
    public boolean apply(File file) {
        return this.operator.apply(file.getSize(), this.size);
    }
}
