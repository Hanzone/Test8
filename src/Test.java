import enums.Test_Enum;
import funInterfaces.BufferedReaderProcessor;
import interfaces.Test_IF;
import interfaces.impl.Test_Impl;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.Collator;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.out;

/**
 * Created by Hanzone on 2018/4/12.
 */
public class Test<T> {

     int we = 122;
    static String s = "123";

    private static final long MILLS_SECOND_OF_DAY = 24 * 3600 * 1000L;
    private static final int SHARD_COUNT = 8;

    public static void main(String args[]) throws ScriptException, IOException, IllegalAccessException, InstantiationException {


//          final long MILLS_SECOND_OF_DAY = 24 * 3600 * 1000L;
//          final int SHARD_COUNT = 2;
//
//        int customShard = 4;
//
//        Date date = new Date();
//
//        String s = Long.toString(date.getTime() / MILLS_SECOND_OF_DAY / SHARD_COUNT % customShard);
//
//        print(s);


        String s = new Test<String>().readLines(br -> {
            try {
                return br.readLine() + br.readLine();
            } catch (IOException e) {
                return "";
            }
        });
        print(s);

//        List<String> strings = Arrays.asList("Sasa", "sdada");
//        strings.stream().map()


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


    public void hh() {
    }

}
