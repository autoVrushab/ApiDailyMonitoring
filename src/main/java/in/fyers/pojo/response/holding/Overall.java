package in.fyers.pojo.response.holding;

public class Overall {

    public int count_total;
    public double pnl_perc;
    public double total_current_value;
    public double total_investment;
    public double total_pl;

    public int getCount_total() {
        return count_total;
    }

    public void setCount_total(int count_total) {
        this.count_total = count_total;
    }

    public double getPnl_perc() {
        return pnl_perc;
    }

    public void setPnl_perc(double pnl_perc) {
        this.pnl_perc = pnl_perc;
    }

    public double getTotal_current_value() {
        return total_current_value;
    }

    public void setTotal_current_value(double total_current_value) {
        this.total_current_value = total_current_value;
    }

    public double getTotal_investment() {
        return total_investment;
    }

    public void setTotal_investment(double total_investment) {
        this.total_investment = total_investment;
    }

    public double getTotal_pl() {
        return total_pl;
    }

    public void setTotal_pl(double total_pl) {
        this.total_pl = total_pl;
    }
}
