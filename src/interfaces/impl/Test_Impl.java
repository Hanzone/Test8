package interfaces.impl;

import interfaces.Test_IF;
import interfaces.Test_IF2;

/**
 * Created by Haorenpu on 2018/4/13.
 */
public class Test_Impl implements Test_IF, Test_IF2 {

    public int i1;
    public Test_Impl(int i){
        this.i1 = i;
    }
    @Override
    public void say() {
        System.out.println("say Test_Impl");
    }

    public static class C {
        static String testString  = "1";

        public void say() {
            System.out.println(testString);
            testString += testString;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(i1 + " ");
    }


    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return toString().equals(obj.toString());
    }
}
