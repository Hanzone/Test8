package funInterfaces;

@FunctionalInterface
public interface Supplier2<T> {
    T get(int i, String j);
}