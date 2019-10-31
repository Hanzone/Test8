package bean;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author hanzone.hao@ximalaya.com
 * @date 2019年04月16日
 */
@Data
@ToString(callSuper=true)
public class Son extends Father {

    Son() {

    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    private String name;

    public Son(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Son(int id, long age) {
        this.id = id;
        this.age = age;
    }

//    @Override
//    public String toString() {
//        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
//    }

}
