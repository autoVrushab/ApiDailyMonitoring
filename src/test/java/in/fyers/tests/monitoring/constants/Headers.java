package in.fyers.tests.monitoring.constants;

public enum Headers {

    AccessToken("Authorization"),
    ContentType("Content-Type");

    public String value;

    Headers(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
