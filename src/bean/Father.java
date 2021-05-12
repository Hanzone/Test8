package bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * @author hanzone.hao@ximalaya.com
 * @date 2019年04月16日
 */
@Data
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Father implements Comparable<Father> {

    int id = 1;
    int idHave = 1;
    long age = 2L;
    double ss;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    Date birthday;
    String as_aqq;

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
