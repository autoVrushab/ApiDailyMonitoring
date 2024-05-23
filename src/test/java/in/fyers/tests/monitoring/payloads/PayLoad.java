package in.fyers.tests.monitoring.payloads;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayLoad {

    /**
     * Method to generate the pay load for Login API
     * @param authCode- Auth Code to be passed
     * @return - returns the payload for the API
     */
    public static Map<String, Object> generateLoginPayLoad(String authCode) {
        Map<String, Object> load = new HashMap<>();
        load.put("grant_type", "authorization_code");
        load.put("appIdHash", "d00054f0adfd31c55971f6ce7d0c5090e85205a39b92bf64845f1de1bb5d6904");
        load.put("code", authCode);
        return load;
    }

    /**
     * Method to generate the payload for orders api
     * @return - Returns the payload with equity, MCX and futures order
     */
    public static Map<String, Object> generateOrdersPayLoad(String symbol, Integer qty, Integer type, Integer limitPrice){
        Map<String, Object> payLoad = new HashMap<>();
        payLoad.put("symbol", symbol);
        payLoad.put("qty", qty);
        payLoad.put("type", type);
        payLoad.put("side", 1);
        payLoad.put("productType", "INTRADAY");
        payLoad.put("limitPrice", limitPrice);
        payLoad.put("stopPrice", 0);
        payLoad.put("validity", "DAY");
        payLoad.put("disclosedQty", 0);
        payLoad.put("offlineOrder", false);
        return payLoad;
    }

    public static Map<String, Object> generateSendOtpData(){
        Map<String, Object> data = new HashMap<>();
        data.put("fy_id", "YV02791");
        data.put("app_id", "2");
        return data;
    }

    public static Map<String, Object> generateVerifyOtpData(String requestKey){
        Map<String, Object> data = new HashMap<>();
        data.put("request_key", requestKey);
        data.put("otp", "000000");
        return data;
    }

    public static Map<String, Object> generateVerifyPinData(String requestKey){
        Map<String, Object> data = new HashMap<>();
        data.put("request_key", requestKey);
        data.put("identity_type", "pin");
        data.put("identifier", "0000");
        return data;
    }

    public static Map<String, Object> generateTokenData(){
        Map<String, Object> data = new HashMap<>();
        data.put("fyers_id", "YV02791");
        data.put("app_id", "E30OQX14VP");
        data.put("redirect_uri", "https://trade.fyers.in/api-login/redirect-uri/index.html");
        data.put("appType", "100");
        data.put("code_challenge", "");
        data.put("state", "sample_state");
        data.put("scope", "");
        data.put("nonce", "");
        data.put("response_type", "code");
        data.put("create_cookie", "true");
        return data;
    }


}
