package in.fyers.pojo.response.token;

public class SendOtp {

    public String s;
    public int code;
    public String message;
    public String mobile_no;
    public String email_id;
    public String request_key;
    public String client_name;
    public String nick_name;
    public Object avatar_link;
    public boolean totp_enabled;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getRequest_key() {
        return request_key;
    }

    public void setRequest_key(String request_key) {
        this.request_key = request_key;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public Object getAvatar_link() {
        return avatar_link;
    }

    public void setAvatar_link(Object avatar_link) {
        this.avatar_link = avatar_link;
    }

    public boolean isTotp_enabled() {
        return totp_enabled;
    }

    public void setTotp_enabled(boolean totp_enabled) {
        this.totp_enabled = totp_enabled;
    }
}
