import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test<T> {

    public static void main(String[] args) throws Exception {

        print("s");

    }

    static String compute(String key) {
        System.out.println("computing" + key);
        if (key == "3") {
            return null;
        }
        return key + "sss";
    }

    static void print(Object o) {
        System.out.println(o);
    }

}
