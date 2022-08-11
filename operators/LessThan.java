package operators;

public class LessThan<T extends Number> implements Operator<T> {
    @Override
    public boolean apply(T e1, T e2) {
        return e1.doubleValue() < e2.doubleValue();
    }
}
