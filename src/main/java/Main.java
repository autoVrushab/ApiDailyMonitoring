import in.fyers.utils.DateUtil;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Long EpocTime = 1716357587L;


        System.out.println(DateUtil.convertEpocToDateTime(EpocTime));
    }
}
