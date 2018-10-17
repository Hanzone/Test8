package bean;

import enums.Test_Enum;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Hanzone
 * @date 2018年06月13日
 */
@Data
@Builder
public class JavaBean {

    private int id;
    private String name;
    private boolean isBig;
    private List<Test_Enum> enums;

}
