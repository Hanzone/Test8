package bean;

import lombok.Data;

import java.util.List;

@Data
public class JavaBeanVo {
    private int id;
    private List<JavaBean> beans;

    public JavaBeanVo() {
    }

    public JavaBeanVo(int id, List<JavaBean> beans) {
        this.id = id;
        this.beans = beans;
    }


}
