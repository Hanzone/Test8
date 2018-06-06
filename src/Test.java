import funInterfaces.BufferedReaderProcessor;
import interfaces.Test_IF;
import redis.clients.jedis.Jedis;

import java.io.*;

import static java.lang.System.out;

/**
 * Created by Hanzone on 2018/4/12.
 */
public class Test<T> {

    int we = 122;
    static String s = "123";

    public static void main(String args[]) {

        Jedis jedis = new Jedis("10.211.55.5", 6379);
        // jedis.set("foo", "bar");
        String value = jedis.get("foo");
        print(value);
    }


    T readLines(BufferedReaderProcessor<T> brp) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/Hanzone/test.sql"))) {
            return brp.process(br);
        }
    }

    interface PersonFactory<P extends Test_IF> {
        P create();
    }

    @FunctionalInterface
    interface Converter<F, F2, T> {
        T convert(F from1, F2 from2);
    }

    static void print(Object o) {
        out.println(o);
    }

}
