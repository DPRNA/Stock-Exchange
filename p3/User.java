package p3;

import p4.CurrentMarketObserver;
import p4.CurrentMarketSide;
import java.util.HashMap;
import p2.TradableDTO;

public class User implements CurrentMarketObserver {
    // data fields
    private final String userId;
    private final HashMap<String, TradableDTO> tradables;
    private final HashMap<String, CurrentMarketSide[]> currentMarkets;

    // constructor
    public User(String userId) {
        this.userId = validateUserId(userId);
        this.tradables = new HashMap<>();
        this.currentMarkets = new HashMap<>();
    }

    public String getUserId() {
        return userId;
    }

    // modifiers for user id
    private String validateUserId(String userId) {
        // check if user id format is valid
        if (userId == null || !userId.matches("[A-Z]{3}")) {
            // if the id is not valid, throw exception
            throw new IllegalArgumentException("Invalid User ID.");
        }
        return userId;
    }

    // add TradableDTO to user order hashmap
    public void addTradable(TradableDTO o) {
        // add tradable to hashmap if not null
        if (o != null) {
            tradables.put(o.id, o);
        }
    }

    // return true if remaining quantity of any TradableDTO is more than 0
    public boolean hasTradableWithRemainingQty() {
        // check for tradables with remaining values
        for (TradableDTO tradable : tradables.values()) {
            if (tradable.remainingVolume > 0) {
                return true;
            }
        }
        return false;
    }

    // return any TradableDTO with remaining quantity more than 0
    public TradableDTO getTradableWithRemainingQty() {
        // find tradable with remaining vol
        for (TradableDTO tradable : tradables.values()) {
            if (tradable.remainingVolume > 0) {
                return tradable;
            }
        }
        return null;
    }

    // update current market for symbol
    @Override
    public void updateCurrentMarket(String symbol, CurrentMarketSide buySide, CurrentMarketSide sellSide) {
        // Create a 2-element array of CurrentMarketSide objects
        CurrentMarketSide[] sides = new CurrentMarketSide[]{buySide, sellSide};
        // Put the array in the currentMarkets HashMap
        currentMarkets.put(symbol, sides);
    }

    // make sumary of current markets
    public String getCurrentMarkets() {
        StringBuilder summary = new StringBuilder();
        // loop through currentMarkets hashmap
        for (String symbol : currentMarkets.keySet()) {
            // append symbol, top buy price, top buy volume, top sell prcie, top sell volume
            summary.append(symbol).append("\t")
                    .append(currentMarkets.get(symbol)[0]).append(" - ")
                    .append(currentMarkets.get(symbol)[1]).append("\n");
        }
        // return formatted summarhy
        return summary.toString();
    }

    // toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("User Id: " + userId + "\n");
        for (TradableDTO tradable : tradables.values()) {
            // product
            sb.append("     Product: ").append(tradable.product)
                    // price
                    .append(", Price: ").append(tradable.price)
                    // original vol
                    .append(", OriginalVolume: ").append(tradable.originalVolume)
                    // remaining vol
                    .append(", RemainingVolume: ").append(tradable.remainingVolume)
                    // cancelled vol
                    .append(", CancelledVolume: ").append(tradable.cancelledVolume)
                    // filled vol
                    .append(", FilledVolume: ").append(tradable.filledVolume)
                    // user
                    .append(", User: ").append(tradable.user).append(", Side: ")
                    // side
                    .append(tradable.side)
                    // id
                    .append(", Id: ").append(tradable.id).append("\n");
        }
        return sb.toString();
    }
}
