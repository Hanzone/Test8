package bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import enums.Test_Enum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author Hanzone
 * @date 2018年06月13日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming
public class JavaBean {


    private int id;
    @JsonProperty(index = 4)
    private String name;
    @JsonProperty(index = 3)
    private boolean isBig;
    @JsonProperty(index = 2)
    private List<Test_Enum> enums;
    @JsonProperty(index = 1)
    private long price;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    private Date time;

}
