package jdk;

/**
 * @author Hanzone
 * @date 2021-06-01
 */
public class OuterClass {

    public static void main(String[] args) {

        OuterClass outerClass = new OuterClass();
        StaticNestedClass staticNestedClass = new StaticNestedClass();
        InnerClass innerClass = outerClass.new InnerClass();

    }

    /**
     * 内部类
     */
    public class InnerClass {

    }

    /**
     * 内部静态类
     */
    public static class StaticNestedClass {

    }

}
