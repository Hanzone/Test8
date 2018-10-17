package enums;

import java.util.Arrays;
import java.util.List;

public enum Test_Enum {
    ANDROID(10, Arrays.asList("10", "11")),
    IOS(11, Arrays.asList("11", "12")),
    WINPHONE(12, Arrays.asList("12", "13")),
    IPAD(13, Arrays.asList("13", "14")),
    WEB(14, Arrays.asList("14", "15")),
    ANDROID_PAD(15, Arrays.asList("15", "16")),
    OTHER(16, Arrays.asList("16", "17"));


    Test_Enum(int code, List<String> versions) {
        this.code = code;
        this.versions = versions;
    }

    private int code;

    public int getCode() {
        return code;
    }

    private List<String> versions;

    public List<String> getVersions() {
        return versions;
    }

}
