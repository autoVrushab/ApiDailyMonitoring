package in.fyers.tests.monitoring.tests;

import in.fyers.pojo.response.depth.DepthResponse;
import in.fyers.pojo.response.history.HistoryResponse;
import in.fyers.pojo.response.holding.HoldingsResponse;
import in.fyers.pojo.response.order.OrderPlacementResponse;
import in.fyers.pojo.response.quotes.QuotesResponse;
import in.fyers.tests.BaseTest;
import in.fyers.tests.monitoring.constants.Headers;
import in.fyers.tests.monitoring.constants.QueryParams;
import in.fyers.tests.monitoring.constants.Uri;
import in.fyers.tests.monitoring.payloads.PayLoad;
import in.fyers.utils.ApiUtils;
import in.fyers.utils.DateUtil;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonitoringTest extends BaseTest {

    @Test(groups = {"History API"})
    public void historyApiTest(){
        String baseUri = getBaseUri() + Uri.History.getValue();
        Map<String, String> queryParam = new HashMap<>();
        queryParam.put(QueryParams.HistorySymbol.getValue(), "NSE:SBIN-EQ");
        queryParam.put(QueryParams.Resolution.getValue(), "30");
        queryParam.put(QueryParams.DateFormat.getValue(), "1");
        queryParam.put(QueryParams.RangeFrom.getValue(), DateUtil.returnDateByAddingDays("yyyy-MM-dd", -5));
        queryParam.put(QueryParams.RangeTo.getValue(), DateUtil.returnDateInSpecificFormat("yyyy-MM-dd"));
        Map<String, String> headers = new HashMap<>();
        headers.put(Headers.AccessToken.getValue(), getAccessToken());

        Response response = ApiUtils.performGET(baseUri, queryParam, headers);
        HistoryResponse historyResponse = response.getBody().as(HistoryResponse.class);
        List<Double> oneCandle = new ArrayList<>(historyResponse.getCandles().getLast());

        for (Double aDouble : oneCandle) {
            Assert.assertTrue(aDouble > 0);
        }
        Assert.assertEquals(historyResponse.getMessage(), "");
        Assert.assertEquals(historyResponse.getS(), "ok");
        Assert.assertEquals(historyResponse.getCode(), 200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(groups = {"Depth API"})
    public void depthApiTest(){
        String baseUri = getBaseUri() + Uri.Depth.getValue();
        Map<String, String> queryParam = new HashMap<>();
        queryParam.put(QueryParams.HistorySymbol.getValue(), "NSE:SBIN-EQ");
        queryParam.put(QueryParams.DepthFlag.getValue(), "1");
        Map<String, String> headers = new HashMap<>();
        headers.put(Headers.AccessToken.getValue(), getAccessToken());

        Response response = ApiUtils.performGET(baseUri, queryParam, headers);
        DepthResponse depthResponse = response.getBody().as(DepthResponse.class);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(depthResponse.getMessage(), "Success");
        Assert.assertEquals(depthResponse.getS(), "ok");
        boolean verifyFields = depthResponse.getD().getNse().getO() >= 0 &&
                depthResponse.getD().getNse().getH() >= 0 && depthResponse.getD().getNse().getL() >= 0 &&
                depthResponse.getD().getNse().getC() >= 0 && (depthResponse.getD().getNse().getChp() >= 0 || depthResponse.getD().getNse().getChp() < 0) &&
                depthResponse.getD().getNse().getLtp() >= 0 && depthResponse.getD().getNse().getLtq() >= 0 &&
                depthResponse.getD().getNse().getV() >= 0 && depthResponse.getD().getNse().getAtp() >= 0 &&
                depthResponse.getD().getNse().getLower_ckt() >= 0 &&
                depthResponse.getD().getNse().getUpper_ckt() >= 0 && depthResponse.getD().getNse().getOi() >= 0 &&
                (depthResponse.getD().getNse().getOipercent() >= 0 || depthResponse.getD().getNse().getOipercent() < 0);
        boolean verifyAskFields = depthResponse.getD().getNse().getAsk().getFirst().getPrice() >= 0 &&
                depthResponse.getD().getNse().getAsk().getFirst().getVolume() >= 0 &&
                depthResponse.getD().getNse().getAsk().getFirst().getOrd() >= 0;
        boolean verifyBidFields = depthResponse.getD().getNse().getBids().getFirst().getPrice() >= 0 &&
                depthResponse.getD().getNse().getBids().getFirst().getVolume() >= 0 &&
                depthResponse.getD().getNse().getBids().getFirst().getOrd() >= 0;
        Assert.assertTrue(verifyBidFields && verifyAskFields && verifyFields);
    }

    @Test(groups = {"Holdings API"})
    public void holdingsApiTest(){
        String baseUri = getBaseUri() + Uri.Holdings.getValue();
        Map<String, String> queryParam = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        headers.put(Headers.AccessToken.getValue(), getAccessToken());

        Response response = ApiUtils.performGET(baseUri, queryParam, headers);
        HoldingsResponse holdingsResponse = response.getBody().as(HoldingsResponse.class);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(holdingsResponse.getCode(), 200);
        Assert.assertEquals(holdingsResponse.getMessage(), "");
        Assert.assertEquals(holdingsResponse.getS(), "ok");
        Assert.assertEquals(holdingsResponse.getOverall().count_total, 1);
        Assert.assertTrue(holdingsResponse.getOverall().getTotal_current_value() > 0);
        Assert.assertTrue(holdingsResponse.getHoldings().getFirst().getCostPrice() > 0);
        Assert.assertEquals(holdingsResponse.getHoldings().getFirst().getFyToken(), "101000000011915");
        Assert.assertEquals(holdingsResponse.getHoldings().getFirst().getSymbol(), "NSE:YESBANK-EQ");
        Assert.assertEquals(holdingsResponse.getHoldings().getFirst().getIsin(), "INE528G01035");
        Assert.assertEquals(holdingsResponse.getHoldings().getFirst().getQuantity(), 1);
    }

    @Test(groups = {"Orders API"})
    public void ordersApiEquityTest(){
        String baseUri = getBaseUri() + Uri.Orders.getValue();
        Map<String, Object> body = PayLoad.generateOrdersPayLoad("NSE:YESBANK-EQ", 1, 2, 0);
        Map<String, String> headers = new HashMap<>();
        headers.put(Headers.AccessToken.getValue(), getAccessToken());

        Response response = ApiUtils.performPOST(baseUri, body, headers);
        OrderPlacementResponse ordersResponse = response.getBody().as(OrderPlacementResponse.class);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(ordersResponse.getMessage());
        Assert.assertNotNull(ordersResponse.getS());
        Assert.assertNotNull(ordersResponse.getId());
    }

    @Test(groups = {"Orders API"})
    public void ordersApiMcxTest(){
        String baseUri = getBaseUri() + Uri.Orders.getValue();
        Map<String, Object> body = PayLoad.generateOrdersPayLoad("MCX:GOLDPETAL24MAYFUT", 1, 2, 0);
        Map<String, String> headers = new HashMap<>();
        headers.put(Headers.AccessToken.getValue(), getAccessToken());

        Response response = ApiUtils.performPOST(baseUri, body, headers);
        OrderPlacementResponse ordersResponse = response.getBody().as(OrderPlacementResponse.class);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(ordersResponse.getMessage());
        Assert.assertNotNull(ordersResponse.getS());
        Assert.assertNotNull(ordersResponse.getId());
    }

    @Test(groups = {"Orders API"})
    public void ordersApiEquityLimitOrderTest(){
        String baseUri = getBaseUri() + Uri.Orders.getValue();
        Map<String, Object> body = PayLoad.generateOrdersPayLoad("NSE:EASEMYTRIP-EQ", 1, 1, 15);
        Map<String, String> headers = new HashMap<>();
        headers.put(Headers.AccessToken.getValue(), getAccessToken());

        Response response = ApiUtils.performPOST(baseUri, body, headers);
        OrderPlacementResponse ordersResponse = response.getBody().as(OrderPlacementResponse.class);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(ordersResponse.getMessage());
        Assert.assertEquals(ordersResponse.getS(), "error");
        Assert.assertNotNull(ordersResponse.getId());
    }

    @Test(groups = {"Orders API"})
    public void ordersApiFuturesTest(){
        String baseUri = getBaseUri() + Uri.Orders.getValue();
        Map<String, Object> body = PayLoad.generateOrdersPayLoad("NSE:NIFTY24MAYFUT", 50, 2, 0);
        Map<String, String> headers = new HashMap<>();
        headers.put(Headers.AccessToken.getValue(), getAccessToken());

        Response response = ApiUtils.performPOST(baseUri, body, headers);
        OrderPlacementResponse ordersResponse = response.getBody().as(OrderPlacementResponse.class);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(ordersResponse.getMessage());
        Assert.assertEquals(ordersResponse.getS(), "error");
        Assert.assertNotNull(ordersResponse.getId());
    }

    @Test(dataProvider = "get_quotes_testdata", groups = {"Quotes API"})
    public void quotesApiTestWithDataProvider(Map<String, String> symbolDetail){

        String baseUri = getBaseUri() + Uri.Quotes.getValue();
        Map<String, String> queryParam = new HashMap<>();
        queryParam.put(QueryParams.Symbol.getValue(), symbolDetail.get("symbol"));
        Map<String,String> headers = new HashMap<>();
        headers.put(Headers.AccessToken.getValue(), getAccessToken());

        Response response = ApiUtils.performGET(baseUri, queryParam, headers);
        QuotesResponse quotesResponse = response.getBody().as(QuotesResponse.class);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(quotesResponse.getMessage(), "");
        Assert.assertEquals(quotesResponse.getD().getFirst().getN(), symbolDetail.get("symbol"));
        Assert.assertEquals(quotesResponse.getD().getFirst().getV().getDescription(), symbolDetail.get("description"));
        Assert.assertEquals(quotesResponse.getD().getFirst().getV().getExchange(), symbolDetail.get("exchange"));
        Assert.assertEquals(quotesResponse.getD().getFirst().getV().getFyToken(), symbolDetail.get("fyToken"));
        Assert.assertEquals(quotesResponse.getD().getFirst().getV().getOriginal_name(), symbolDetail.get("symbol"));
        Assert.assertEquals(quotesResponse.getD().getFirst().getV().getSymbol(), symbolDetail.get("symbol"));
        Assert.assertEquals(quotesResponse.getD().getFirst().getV().getShort_name(), symbolDetail.get("shortName"));
        boolean verifyFields = (quotesResponse.getD().getFirst().getV().getAsk() >= 0) &&
                (quotesResponse.getD().getFirst().getV().getBid() >= 0) &&
                (quotesResponse.getD().getFirst().getV().getHigh_price() >= 0) &&
                (quotesResponse.getD().getFirst().getV().getLow_price() >= 0) &&
                (quotesResponse.getD().getFirst().getV().getLp() >= 0) &&
                (quotesResponse.getD().getFirst().getV().getOpen_price() >= 0) &&
                (quotesResponse.getD().getFirst().getV().getPrev_close_price() >= 0) &&
                (quotesResponse.getD().getFirst().getV().getSpread() >= 0 || quotesResponse.getD().getFirst().getV().getSpread() < 0) &&
                (quotesResponse.getD().getFirst().getV().getVolume() >= 0);
        Assert.assertTrue(verifyFields);
    }


    @DataProvider(name = "get_quotes_testdata")
    public Object[][] getData(){
        Map<String, String> sbi= new HashMap<>();
        sbi.put("symbol", "NSE:SBIN-EQ");
        sbi.put("description", "NSE:SBIN-EQ");
        sbi.put("originalName", "NSE:SBIN-EQ");
        sbi.put("shortName", "SBIN-EQ");
        sbi.put("exchange", "NSE");
        sbi.put("fyToken", "10100000003045");

        Map<String, String> tataMotors= new HashMap<>();
        tataMotors.put("symbol", "NSE:TATAMOTORS-EQ");
        tataMotors.put("description", "NSE:TATAMOTORS-EQ");
        tataMotors.put("originalName", "NSE:TATAMOTORS-EQ");
        tataMotors.put("shortName", "TATAMOTORS-EQ");
        tataMotors.put("exchange", "NSE");
        tataMotors.put("fyToken", "10100000003456");


        Map<String, String> axisBank= new HashMap<>();
        axisBank.put("symbol", "NSE:AXISBANK-EQ");
        axisBank.put("description", "NSE:AXISBANK-EQ");
        axisBank.put("originalName", "NSE:AXISBANK-EQ");
        axisBank.put("shortName", "AXISBANK-EQ");
        axisBank.put("exchange", "NSE");
        axisBank.put("fyToken", "10100000005900");

        Map<String, String> bseHero= new HashMap<>();
        bseHero.put("symbol", "BSE:HEROMOTOCO-A");
        bseHero.put("description", "BSE:HEROMOTOCO-A");
        bseHero.put("originalName", "BSE:HEROMOTOCO-A");
        bseHero.put("shortName", "HEROMOTOCO-A");
        bseHero.put("exchange", "BSE");
        bseHero.put("fyToken", "1210000000500182");

        Map<String, String> bseHcl= new HashMap<>();
        bseHcl.put("symbol", "BSE:HCLTECH-A");
        bseHcl.put("description", "BSE:HCLTECH-A");
        bseHcl.put("originalName", "BSE:HCLTECH-A");
        bseHcl.put("shortName", "HCLTECH-A");
        bseHcl.put("exchange", "BSE");
        bseHcl.put("fyToken", "1210000000532281");

        return new Object[][]{
                {sbi}, {tataMotors}, {axisBank}, {bseHcl}, {bseHero}
        };
    }
}