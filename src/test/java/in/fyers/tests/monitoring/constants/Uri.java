package in.fyers.tests.monitoring.constants;

public enum Uri {

    Login("/api/v3/validate-authcode"),
    History("/data/history"),
    Holdings("/api/v3/holdings"),
    Orders("/api/v3/orders/sync"),
    Depth("/data/depth"),
    Quotes("/data/quotes");

    public String value;

    Uri(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

}
