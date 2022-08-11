package filters;

public interface IFilter<T> {
    boolean apply(T element);
}
