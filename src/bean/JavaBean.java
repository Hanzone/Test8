package bean;

import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Hanzone
 * @date 2018年06月13日
 */
@Data
public class JavaBean implements Serializable {

    private int id;

    private String name;

    private boolean isBig;

    private List<String> strings = Lists.newArrayList();

    public JavaBean() {
    }

    public JavaBean(int id, String name) {
        this.id = id;
        this.name = name;
        this.strings = Lists.newArrayList("1", "2");
    }

}
