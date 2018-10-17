package interfaces;

/**
 * Created by Haorenpu on 2018/4/13.
 */
public interface Test_IF {

    default void say() {
        System.out.println("say Test_IF");
    }

    //void say();

    static void act () {
        System.out.println("do Test_IF");
    }

    void papapa();
}
