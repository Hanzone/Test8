package enums;

import java.util.Arrays;
import java.util.List;

public enum Test_Enum {
    ANDROID(10, Arrays.asList("10s")),
    IOS(11, Arrays.asList("11s")),
    WINPHONE(12, Arrays.asList("12s")),
    IPAD(13, Arrays.asList("13s")),
    WEB(14, Arrays.asList("14s")),
    ANDROID_PAD(15, Arrays.asList("15s")),
    OTHER(16, Arrays.asList("16s"));


    Test_Enum(int k, List<String> vs) {
        this.k = k;
        this.vs = vs;
    }

    private int k;

    public int getK() {
        return k;
    }

    private List<String> vs;

    public List<String> getVs() {
        return vs;
    }

}
