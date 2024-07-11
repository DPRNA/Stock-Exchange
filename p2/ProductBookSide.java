package p2;

import p1.Price;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import p3.ProductManager;
import p3.UserManager;

public class ProductBookSide {

    // data elements
    private BookSide side;
    private HashMap<Price, ArrayList<Tradable>> bookEntries;

    // constructor
    public ProductBookSide(BookSide side) {
        this.side = side;
        this.bookEntries = new HashMap<>();
    }

    // add tradable to book
    public TradableDTO add(Tradable o) {
        Price price = o.getPrice(); // get price of tradable

        // get list of tradables at that price, make new list if nonexistent
        ArrayList<Tradable> tradablesAtPrice = bookEntries.getOrDefault(price, new ArrayList<>());
        tradablesAtPrice.add(o);
        bookEntries.put(price, tradablesAtPrice);
        return o.makeTradableDTO();
    }

    // cancel tradable by ID
    public TradableDTO cancel(String tradableId) {
        for (ArrayList<Tradable> tradablesAtPrice : bookEntries.values()) {
            for (Tradable t : tradablesAtPrice) {
                if (t.getId().equals(tradableId)) {
                    int remainingVolume = t.getRemainingVolume();
                    t.setCancelledVolume(t.getCancelledVolume() + remainingVolume);
                    t.setRemainingVolume(0);
                    tradablesAtPrice.remove(t);
                    if (tradablesAtPrice.isEmpty()) {
                        bookEntries.remove(t.getPrice());
                    }
                    TradableDTO dto = t.makeTradableDTO();
                    UserManager.getInstance().addToUser(t.getUser(), dto);
                    return dto;
                }
            }
        }
        return null;
    }

    // remove all quotes for certain user
    public TradableDTO[] removeQuotesForUser(String userName) {
        List<Tradable> removedQuotes = new ArrayList<>();
        List<TradableDTO> objects = new ArrayList<>();

        for (ArrayList<Tradable> tradablesAtPrice : bookEntries.values()) {
            for (Tradable t : tradablesAtPrice) {
                if (t.getUser().equals(userName)) {
                    removedQuotes.add(t);
                }
            }
        }

        for (Tradable t : removedQuotes) {
            TradableDTO dto = t.makeTradableDTO();
            objects.add(dto);
            UserManager.getInstance().addToUser(t.getUser(), dto);
        }

        removedQuotes.clear();
        return objects.toArray(new TradableDTO[0]);
    }

    // get top price
    public Price topOfBookPrice() {
        if (side == BookSide.BUY) { // if side is buy
            // return max price
            return bookEntries.keySet().stream().max(Price::compareTo).orElse(null);
        } else { // return minimum otherwise
            return bookEntries.keySet().stream().min(Price::compareTo).orElse(null);
        }
    }

    // total volume at top price of book
    public int topOfBookVolume() {
        Price topPrice = topOfBookPrice();
        // if there is a top price, return sum of remaining vols of all tradables at that price
        if (topPrice != null) {
            return bookEntries.get(topPrice).stream().mapToInt(Tradable::getRemainingVolume).sum();
        } // return 0 if there is no top price
        return 0;
    }

    public void tradeOut(Price price, int vol) {
        int remainingVol = vol;
        ArrayList<Price> pricesRemove = new ArrayList<>();

        for (Price p : bookEntries.keySet()) {
            if ((side == BookSide.BUY && p.compareTo(price) < 0) || (side == BookSide.SELL && p.compareTo(price) > 0)) {
                continue;
            }

            ArrayList<Tradable> tradablesAtPrice = bookEntries.get(p);
            ArrayList<Tradable> tradablesRemove = new ArrayList<>();

            for (Tradable t : tradablesAtPrice) {
                if (remainingVol <= 0) {
                    break;
                }

                int orderRemainingVol = t.getRemainingVolume();

                if (orderRemainingVol > remainingVol) {
                    t.setRemainingVolume(orderRemainingVol - remainingVol);
                    t.setCancelledVolume(t.getCancelledVolume() + remainingVol);
                    remainingVol = 0;
                    TradableDTO dto = t.makeTradableDTO();
                    UserManager.getInstance().addToUser(t.getUser(), dto);
                } else {
                    t.setRemainingVolume(0);
                    t.setCancelledVolume(t.getCancelledVolume() + orderRemainingVol);
                    remainingVol -= orderRemainingVol;
                    tradablesRemove.add(t);
                    TradableDTO dto = t.makeTradableDTO();
                    UserManager.getInstance().addToUser(t.getUser(), dto);
                }
            }

            tradablesAtPrice.removeAll(tradablesRemove);
            if (tradablesAtPrice.isEmpty()) {
                pricesRemove.add(p);
            }
        }

        for (Price p : pricesRemove) {
            bookEntries.remove(p);
        }
    }

    @Override
    public String toString() {
        // string builder to make the string representation
        StringBuilder sb = new StringBuilder();

        // append side of order book
        sb.append("Side: ").append(side).append("\n");

        // loop through each price level in order book
        for (Price price : bookEntries.keySet()) {
            // append price level
            sb.append("\tPrice: ").append(price).append("\n");

            // loop through each tradable at current price
            for (Tradable t : bookEntries.get(price)) {
                // indent for formatting and append string of tradable
                sb.append("\t\t");

                sb.append(t.toString()).append("\n");
            }
        }
        return sb.toString();
    }
}
