package in.fyers.pojo.response.history;

import java.util.List;

public class HistoryResponse {

    public List<List<Double>> candles;
    public int code;
    public String message;
    public String s;

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

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

    public List<List<Double>> getCandles() {
        return candles;
    }

    public void setCandles(List<List<Double>> candles) {
        this.candles = candles;
    }
}
