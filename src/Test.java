import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Test<T> {

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2,
            3,
            1,
            TimeUnit.MINUTES,
            new ArrayBlockingQueue<>(1),
            new ThreadFactoryBuilder().setDaemon(false).setPriority(Thread.NORM_PRIORITY).setNameFormat("sim-mobile-import-%d").build());

    public static Cache<Integer, Integer> CACHE = CacheBuilder.newBuilder()
            .maximumSize(10)
            .expireAfterWrite(60, TimeUnit.SECONDS)
            .recordStats()
            .build();

    public static void main(String[] args) throws Exception {


    }

    private static Integer sss(int i) {
        try {
            return CACHE.get(i, () -> {
                if (i == 1) {
                    throw new RuntimeException("RPC EEEEEEEEEEEEEEE");
                }
                return i * 2;
            });
        } catch (ExecutionException e) {
            log.error("ssss", e);
            return 1000;
        }
    }

    private static StringBuilder appendValue(StringBuilder sb, Object value) {
        if (value instanceof String) {
            return sb.append(", ").append("'").append(value).append("'");
        } else {
            return sb.append(", ").append(value);
        }
    }

    public static void writefile(List<String> lines) {
        String fileName="/Users/Ted/sssss.sql";
        try {
            BufferedWriter out=new BufferedWriter(new FileWriter(fileName));

            lines.forEach(line -> {
                try {
                    out.write(line);
                    out.newLine();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getByteFile() {

        try {
            File file = new File("/Users/Ted/sss.sql");
            FileInputStream fis = new FileInputStream(file);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int len;
            byte[] buffer = new byte[2048];
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }

            byte[] bytes = baos.toByteArray();

            String s = new String(Base64.getEncoder().encode(bytes));
            print(s);
            fis.close();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<String> getStrFile() {

        List<String> strs = new ArrayList<>();
        try {
            FileReader fr = new FileReader("/Users/Ted/old.sql");
            BufferedReader bf = new BufferedReader(fr);

            String str;
            while ((str = bf.readLine()) != null) {
                strs.add(str);
            }
            fr.close();
            bf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return strs;
    }

    @FunctionalInterface
    private interface Supplier2<T> {
        T get(int i, String j);
    }

    static void print(Object o) {
        System.out.println(o);
    }

}
