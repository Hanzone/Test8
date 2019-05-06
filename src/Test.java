import java.io.IOException;
import java.util.function.BinaryOperator;
import static java.lang.System.out;

/**
 * Created by Hanzone on 2018/4/12.
 */
public class Test<T> {

    public static void main(String args[]) throws InterruptedException, IOException, IllegalAccessException {


    }


    private static <T> BinaryOperator<T> throwingMerger() {
        return (k, v) -> {
            throw new IllegalStateException(String.format("Duplicate key %s", k));
        };
    }

    static void print(Object o) {
        out.println(o);
    }

}
