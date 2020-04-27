package concurrent;

import lombok.Getter;
import lombok.Setter;

public class ThreadContext {

    private static final ThreadLocal<String> store = new ThreadLocal<>();

    @Getter
    @Setter
    private static String sss;

    public static String get() {
        return store.get();
    }

    public static void set(String val) {
        store.set(val);
    }

    public static void remove() {
        store.remove();
    }

}
