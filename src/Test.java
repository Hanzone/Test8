import interfaces.Test_IF;
import interfaces.impl.Test_Impl;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.Collator;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.out;

/**
 * Created by Hanzone on 2018/4/12.
 */
public class Test {

     int we = 122;
    static String s = "123";

    private static final long MILLS_SECOND_OF_DAY = 24 * 3600 * 1000L;
    private static final int SHARD_COUNT = 8;

    public static void main(String args[]) throws ScriptException, UnsupportedEncodingException, IllegalAccessException, InstantiationException {


        List<String> stringss = Arrays.asList("1a", "2", "3", "4");
        List<String> strings = Arrays.asList("1b", "2", "3", "4", "5");


        Map<String,List<String>> map = new HashMap<>();
        map.put("1", stringss);
        map.put("2", strings);
        map.values().stream().findAny().ifPresent(l -> l.stream().findAny().ifPresent(Test::print));

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


    public void hh() {
    }

}
