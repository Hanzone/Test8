package util;

public enum ErrorCode {
    SUCCESS(200, "SUCCESS"),
    FAIL(400, "FAIL");

    private int code;
    private String desc;
    ErrorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return this.code;
    }

    public String getName() {
        return name();
    }

    public String getDesc() {
        return this.desc;
    }

    public ErrorCode getByCode(int code) {
        for (ErrorCode errorCode : values()) {
            if (code == errorCode.getCode()) {
                return errorCode;
            }
        }

        throw new IllegalArgumentException("Code not exist.");
    }
}
