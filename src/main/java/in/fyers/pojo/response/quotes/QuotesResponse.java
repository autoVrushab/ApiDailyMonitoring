package in.fyers.pojo.response.quotes;

import java.util.List;

public class QuotesResponse {

    public String message;
    public int code;
    public List<Dclass> d;
    public String s;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Dclass> getD() {
        return d;
    }

    public void setD(List<Dclass> d) {
        this.d = d;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
