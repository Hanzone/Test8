import com.google.common.collect.Lists;
import com.ximalaya.ad.common.util.LogMessageBuilder;
import funInterfaces.BufferedReaderProcessor;
import interfaces.Test_IF;
import interfaces.impl.Test_Impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.System.out;

/**
 * Created by Hanzone on 2018/4/12.
 */
public class Test<T> {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    int we = 122;
    static String s = "123";

    public static void main(String args[]) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {


    }

    public enum SortDirection {
        ASC("asc"),
        DESC("desc");
        private String direction;

        private SortDirection(String direction) {
            this.direction = direction;
        }

        public static SortDirection fromCode(String value) {
            for (SortDirection sortDirection : values()) {
                if (sortDirection.getDirection().equals(value)) {
                    return sortDirection;
                }
            }
            return null;
        }

        public String getDirection() {
            return this.direction;
        }
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
