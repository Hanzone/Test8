package bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author hanzone.hao@ximalaya.com
 * @date 2019年04月16日
 */
@Data
public class Father implements Comparable<Father> {

    int id = 1;
    long age = 2L;
    double ss;

    public Father() {
    }

    public Father(int id) {
        this.id = id;
    }

    public Father(int id, long age) {
        this.id = id;
        this.age = age;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public int compareTo(Father other) {
        return Integer.compare(this.id, other.id);
    }
}
