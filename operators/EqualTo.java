package operators;

public class EqualTo<T> implements Operator<T> {
    @Override
    public boolean apply(T e1, T e2) {
        if(e1 instanceof String)
            return e1.equals(e2);

        if(e1 instanceof Number)
            return ((Number) e1).doubleValue() == ((Number) e2).doubleValue();

        return e1 == e2;
    }
}
