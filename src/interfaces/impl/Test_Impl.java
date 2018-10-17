package interfaces.impl;

import interfaces.Test_IF;
import interfaces.Test_IF2;

/**
 * Created by Haorenpu on 2018/4/13.
 */
public abstract class Test_Impl implements Test_IF, Test_IF2 {

    private int i1;

    private Integer i2;

    private Test_Impl(int i){
        this.i1 = i;
    }

    public Test_Impl create(int i) {
        return this;
    }

    @Override
    public void say() {
        Test_IF.super.say();
    }

    public abstract void papapa();

    public void laugh() {
        System.out.println("哈哈哈！！！");
    }

    public int getI1() {
        return i1;
    }

    public void setI1(int i1) {
        this.i1 = i1;
    }

    public Integer getI2() {
        return i2;
    }

    public void setI2(Integer i2) {
        this.i2 = i2;
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

    public boolean equals(Object obj) {
        return toString().equals(obj.toString());
    }
}
