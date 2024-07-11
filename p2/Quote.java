package p2;
import p1.Price;

public class Quote {
    // data elements
    private String user;
    private String product;
    private QuoteSide buySide;
    private QuoteSide sellSide;

    // constructor
    public Quote(String symbol, Price buyPrice, int buyVolume, Price sellPrice, int sellVolume, String userName){
        this.product = symbol;
        this.user = userName;
        // quotesides for buy and sell side
        this.buySide = new QuoteSide(userName, symbol, buyPrice, buyVolume, BookSide.BUY);
        this.sellSide = new QuoteSide(userName, symbol, sellPrice, sellVolume, BookSide.SELL);
    }

    // get methods
    public QuoteSide getQuoteSide(BookSide sideIn) {
        // if side is buy return buy side, return sell side otherwise
        return (sideIn == BookSide.BUY) ? buySide : sellSide;
    }

    public String getSymbol(){
        return product;
    }

    public String getUser(){
        return user;
    }

}
