package interfaces;

public interface T_IF {

    default void echo() {
        System.out.println(getDesc());
    }

    String getDesc();

}
