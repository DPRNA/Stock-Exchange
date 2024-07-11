package p4;

public interface CurrentMarketObserver {

    // method for updating current market
    void updateCurrentMarket(String symbol, CurrentMarketSide buySide, CurrentMarketSide sellSide);
}
