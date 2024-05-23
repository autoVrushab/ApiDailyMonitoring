package in.fyers.pojo.response.token;

public class VerifyPin {

    public String s;
    public int code;
    public String message;
    public VerifyPinData data;

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public VerifyPinData getData() {
        return data;
    }

    public void setData(VerifyPinData data) {
        this.data = data;
    }
}
