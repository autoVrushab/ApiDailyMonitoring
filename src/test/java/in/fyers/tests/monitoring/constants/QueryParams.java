package in.fyers.tests.monitoring.constants;

public enum QueryParams {

    Symbol("symbols"),
    HistorySymbol("symbol"),
    Resolution("resolution"),
    DateFormat("date_format"),
    RangeFrom("range_from"),
    RangeTo("range_to"),
    DepthFlag("ohlcv_flag");

    public String value;

    QueryParams(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
