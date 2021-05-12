package interfaces.impl;

import interfaces.T_IF;

public class T_Impl implements T_IF {

    private String ss = "sa";

    @Override
    public String getDesc() {
        return ss;
    }
}
