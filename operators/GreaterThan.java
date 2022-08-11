package operators;

public class GreaterThan<T extends Number> implements Operator<T> {
    @Override
    public boolean apply(T e1, T e2) {
        return e1.doubleValue() > e2.doubleValue();
    }
}
