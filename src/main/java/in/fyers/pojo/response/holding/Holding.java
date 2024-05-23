package in.fyers.pojo.response.holding;

public class Holding {

    public double costPrice;
    public int id;
    public String fyToken;
    public String symbol;
    public String isin;
    public int quantity;
    public int exchange;
    public int segment;
    public int qty_t1;
    public int remainingQuantity;
    public int collateralQuantity;
    public int remainingPledgeQuantity;
    public double pl;
    public double ltp;
    public double marketVal;
    public String holdingType;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFyToken() {
        return fyToken;
    }

    public void setFyToken(String fyToken) {
        this.fyToken = fyToken;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getExchange() {
        return exchange;
    }

    public void setExchange(int exchange) {
        this.exchange = exchange;
    }

    public int getSegment() {
        return segment;
    }

    public void setSegment(int segment) {
        this.segment = segment;
    }

    public int getQty_t1() {
        return qty_t1;
    }

    public void setQty_t1(int qty_t1) {
        this.qty_t1 = qty_t1;
    }

    public int getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(int remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public int getCollateralQuantity() {
        return collateralQuantity;
    }

    public void setCollateralQuantity(int collateralQuantity) {
        this.collateralQuantity = collateralQuantity;
    }

    public int getRemainingPledgeQuantity() {
        return remainingPledgeQuantity;
    }

    public void setRemainingPledgeQuantity(int remainingPledgeQuantity) {
        this.remainingPledgeQuantity = remainingPledgeQuantity;
    }

    public double getPl() {
        return pl;
    }

    public void setPl(double pl) {
        this.pl = pl;
    }

    public double getLtp() {
        return ltp;
    }

    public void setLtp(double ltp) {
        this.ltp = ltp;
    }

    public double getMarketVal() {
        return marketVal;
    }

    public void setMarketVal(double marketVal) {
        this.marketVal = marketVal;
    }

    public String getHoldingType() {
        return holdingType;
    }

    public void setHoldingType(String holdingType) {
        this.holdingType = holdingType;
    }
}
