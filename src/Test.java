import bean.Father;
import bean.Son;
import bean.TransDTO1;
import com.google.common.collect.Lists;
import concurrent.MyLock;
import concurrent.MyThread;
import concurrent.ThreadContext;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import static java.lang.System.out;

/**
 * Created by Hanzone on 2018/4/12.
 */
@Slf4j
public class Test<T> {

    static {
        print(Test.class.getClassLoader());
    }



    public static void main(String[] args) throws Exception {

//        FileOutputStream fos = new FileOutputStream("/Users/Ted/s.txt");
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        TransDTO1 transDTO1 = new TransDTO1(1, "ted");
//        oos.writeObject(transDTO1);
//        oos.flush();
//        oos.close();

//        FileInputStream fis = new FileInputStream("/Users/Ted/s.txt");
//
//        ObjectInputStream ois = new ObjectInputStream(fis);
//        TransDTO1 transDTO1 = (TransDTO1) ois.readObject();
//        print(transDTO1);


//        ThreadContext.set("123");
//        ThreadContext.setSss("sss123");
//
//        MyThread myThread = new MyThread(new MyLock());
//        myThread.start();
//
//        Thread.sleep(1000);
//        System.out.print(ThreadContext.get());
//        System.out.print(ThreadContext.getSss());


        String param = null;

        switch (param) {
// 肯定不是进入这里
            case "sth": System.out.println("it's sth"); break;
// 也不是进入这里
            case "null": System.out.println("it's null"); break;
// 也不是进入这里
            default: System.out.println("default");
        }



    }


    private static void dbTable() {
        long uid = 9949230521447L;

        String db = db(uid);
        String table = table(uid);

        print("db: " + db + " table: " + table);
    }

    private static String db(long uid) {
        long l = ((uid % 4096) & 63) >> 4;
        return "" + l;
    }

    private static String table(long uid) {

        long l = ((uid % 4096) & 63) % 16;

        return "" + l;
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

    private static void getStrFile() {

        try {

            FileReader fr = new FileReader("/Users/Ted/sss.sql");
            BufferedReader bf = new BufferedReader(fr);

            String str;
            StringBuilder sb = new StringBuilder();
            Base64.Decoder decoder = Base64.getDecoder();
            while ((str = bf.readLine()) != null) {
                sb.append(new String(decoder.decode(str)));
                sb.append('\n');
            }

            print(sb.toString());
            fr.close();
            bf.close();
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
