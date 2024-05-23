package in.fyers.pojo.response.token;

public class VerifyOtp {

    public String s;
    public int code;
    public String request_key;
    public String client_name;
    public String nick_name;
    public boolean pin_created;
    public String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isPin_created() {
        return pin_created;
    }

    public void setPin_created(boolean pin_created) {
        this.pin_created = pin_created;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getRequest_key() {
        return request_key;
    }

    public void setRequest_key(String request_key) {
        this.request_key = request_key;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
