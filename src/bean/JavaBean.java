package bean;

/**
 * @author Hanzone
 * @date 2018年06月13日
 */
public class JavaBean {

    private int id;

    private String name;

    private boolean isBig;

    public JavaBean() {
    }

    public JavaBean(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public boolean getIsBig() {
        return isBig;
    }

    public void setIsBig(boolean isBig) {
        this.isBig = isBig;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
