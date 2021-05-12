package bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompositeBean<T> {

    private CompositeSubBean1 compositeSubBean1;

    private CompositeSubBean2 compositeSubBean2;

    private T t;

}
