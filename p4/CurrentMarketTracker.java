package p4;
import p1.Price;
public class CurrentMarketTracker {
    private static CurrentMarketTracker instance;

    // constructor
    private CurrentMarketTracker() {
    }

    // get method to get single instance of CurrentMarketTracker
    public static CurrentMarketTracker getInstance() {
        if (instance == null) { // make new CurrentMarketTracker if doesnt exist
            instance = new CurrentMarketTracker();
        }
        return instance;
    }

    // update market with buy and sell side info
    public void updateMarket(String symbol, Price buyPrice, int buyVolume, Price sellPrice, int sellVolume) {
        // market width calculation
        double marketWidth = calculateMarketWidth(buyPrice, sellPrice);

        // CurrentMarketSide objects for buy side
        CurrentMarketSide buySide = new CurrentMarketSide(buyPrice, buyVolume);
        // CurrentMarket side object for sell side
        CurrentMarketSide sellSide = new CurrentMarketSide(sellPrice, sellVolume);

        // print market
        printCurrentMarket(symbol, buySide, sellSide, marketWidth);

        // pass info to CurrentMarketPublisher
        CurrentMarketPublisher.getInstance().acceptCurrentMarket(symbol, buySide, sellSide);
    }

    // calculate market width
    private double calculateMarketWidth(Price buyPrice, Price sellPrice) {
        if (buyPrice == null || sellPrice == null) {
            return 0;
        }
        return Math.abs(buyPrice.toDouble() - sellPrice.toDouble());
    }

    // print current market info
    private void printCurrentMarket(String symbol, CurrentMarketSide buySide, CurrentMarketSide sellSide, double marketWidth) {
        System.out.println("*********** Current Market ***********");
        System.out.println("* " + symbol + " " + buySide + " - " + sellSide + " [" + marketWidth + "]");
        System.out.println("**************************************");
    }
}
