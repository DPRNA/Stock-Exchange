package p2;

import p1.Price;
import p4.CurrentMarketTracker;

public class ProductBook {
    // data elements to represent product's book
    private String product;
    private ProductBookSide buySide;
    private ProductBookSide sellSide;

    // constructor to initialize product name and instances of ProductBookSide
    public ProductBook(String product) {
        this.product = product;
        // create buy and sell sides
        this.buySide = new ProductBookSide(BookSide.BUY);
        this.sellSide = new ProductBookSide(BookSide.SELL);
    }

    // add tradable item to buy or sell
    public TradableDTO add(Tradable t) {
        // Add tradable to the respective side
        if (t.getSide() == BookSide.BUY) {
            TradableDTO dto = buySide.add(t);
            updateMarket();
            return dto;
        } else {
            TradableDTO dto = sellSide.add(t);
            updateMarket();
            return dto;
        }
    }

    // add quotes from quote object to buy and sell sides
    public TradableDTO[] add(Quote qte) {
        // create order objects from quote object and add to buy and sell sides
        TradableDTO[] objects = new TradableDTO[2];
        // buy side: create order with user, symbol, buy side price, buy side, original volume from quote
        objects[0] = buySide.add(new Order(qte.getUser(), qte.getSymbol(), qte.getQuoteSide(BookSide.BUY).getPrice(), BookSide.BUY, qte.getQuoteSide(BookSide.BUY).getOriginalVolume()));
        // sell side: create an order with same as buy side but with sell side price, and sell side from quote
        objects[1] = sellSide.add(new Order(qte.getUser(), qte.getSymbol(), qte.getQuoteSide(BookSide.SELL).getPrice(), BookSide.SELL, qte.getQuoteSide(BookSide.SELL).getOriginalVolume()));
        updateMarket();
        return objects;
    }

    // cancel an order on buy or sell side
    public TradableDTO cancel(BookSide side, String orderId) {
        // cacncel order form buy side
        if (side == BookSide.BUY) {
            TradableDTO dto = buySide.cancel(orderId);
            updateMarket();
            return dto;
        } else {
            // cancel order from sell side if the side is not a buy side
            TradableDTO dto = sellSide.cancel(orderId);
            updateMarket();
            return dto;
        }
    }

    // remove quotes for certain user from buy and sell side
    public TradableDTO[] removeQuotesForUser(String userName) {
        // remove quotes for specific user from buy side
        TradableDTO[] buySideQuotes = buySide.removeQuotesForUser(userName);
        // remove quotes for specific user form sell side
        TradableDTO[] sellSideQuotes = sellSide.removeQuotesForUser(userName);

        // combine quotes from buy side and sell side into one array
        TradableDTO[] objects = new TradableDTO[buySideQuotes.length + sellSideQuotes.length];
        System.arraycopy(buySideQuotes, 0, objects, 0, buySideQuotes.length);
        System.arraycopy(sellSideQuotes, 0, objects, buySideQuotes.length, sellSideQuotes.length);

        // upadte market after removal of quotes
        updateMarket();

        return objects;
    }

    // Update the market with the top-of-book prices and volumes
    // update market with top of book prices/volumes
    private void updateMarket() {
        // get top  book price for buy side
        Price buyPrice = buySide.topOfBookPrice();
        // get top book volume for buy side
        int buyVolume = buySide.topOfBookVolume();
        // get top book price for sell side
        Price sellPrice = sellSide.topOfBookPrice();
        // get top book volume for sell side
        int sellVolume = sellSide.topOfBookVolume();

        // call updateMarket (from CurrentMarketTracker) method
        CurrentMarketTracker.getInstance().updateMarket(product, buyPrice, buyVolume, sellPrice, sellVolume);
    }

    // string  representation of produt book
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // product
        sb.append("Product: ").append(product).append("\n");
        // buy side
        sb.append("Side: BUY\n");
        sb.append(buySide.toString()).append("\n");
        // sell side
        sb.append("Side: SELL\n");
        sb.append(sellSide.toString());
        return sb.toString();
    }
}
