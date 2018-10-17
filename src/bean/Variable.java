package bean;

import lombok.Data;

@Data
public class Variable {
    private String variableCode;
    private String variableName;
    private String opt;

    public Variable() {}

    public Variable(String variableCode, String variableName) {
        this.variableCode = variableCode;
        this.variableName = variableName;
    }

}
