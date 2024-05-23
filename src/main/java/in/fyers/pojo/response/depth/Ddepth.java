package in.fyers.pojo.response.depth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ddepth {

    @JsonProperty("NSE:SBIN-EQ")
    public NseEq nse;

    public NseEq getNse() {
        return nse;
    }

    public void setNse(NseEq nse) {
        this.nse = nse;
    }
}
