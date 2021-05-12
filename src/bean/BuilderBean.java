package bean;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class BuilderBean {

    private final int id;
    private final String name;

}
