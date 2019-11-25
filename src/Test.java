import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.*;

import static java.lang.System.out;

/**
 * Created by Hanzone on 2018/4/12.
 */
@Slf4j
public class Test<T> {

    static {
        print(Test.class.getClassLoader());
    }

    public static void main(String args[]) throws Exception {


    }

    private static void getBaseStr() {

        try {
            File file = new File("/Users/Hanzone/DeskTop/TrustPay.cer");
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

    @FunctionalInterface
    private interface Supplier2<T> {
        T get(int i, String j);
    }

    static void print(Object o) {
        out.println(o);
    }

}
