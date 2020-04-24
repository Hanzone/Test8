package bean;

import lombok.Data;
import lombok.ToString;

/**
 * @author hanzone.hao@ximalaya.com
 * @date 2019年04月16日
 */
@Data
@ToString(callSuper=true)
public class GrandSon extends Son {

    public GrandSon() {

    }

    private String name;

    public GrandSon(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public GrandSon(int id, long age) {
        this.id = id;
        this.age = age;
    }

}
