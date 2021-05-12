package interfaces;

public abstract class Test_AB {

    private String name;

    public Test_AB(String name) {
        this.name = name;
        System.out.println("Test_AB[" + name + "] Constructed!");
    }

    public abstract void abMethod();

}
