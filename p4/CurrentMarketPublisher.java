package p4;

import java.util.ArrayList;
import java.util.HashMap;

public class CurrentMarketPublisher {
    private static CurrentMarketPublisher instance;

    // hashmp for filters for subsribed CurrentMarketObservers
    private final HashMap<String, ArrayList<CurrentMarketObserver>> filters;

    // consttructor for singleton
    private CurrentMarketPublisher() {
        filters = new HashMap<>();
    }

    // static method to geet single instance of CurrentMarketPublisher
    public static CurrentMarketPublisher getInstance() {
        // check for null
        if (instance == null) {
            // create new instance if null
            instance = new CurrentMarketPublisher();
        }
        return instance;
    }

    // subscribe a CurrentMarketObserver for certain stock symbl
    public void subscribeCurrentMarket(String symbol, CurrentMarketObserver cmo) {
        ArrayList<CurrentMarketObserver> observers = filters.computeIfAbsent(symbol, k -> new ArrayList<>());
        observers.add(cmo);
    }

    // unsubstribe a CurrentMarketObservre for certain stock symbol
    public void unSubscribeCurrentMarket(String symbol, CurrentMarketObserver cmo) {
        ArrayList<CurrentMarketObserver> observers = filters.get(symbol);
        if (observers != null) {
            observers.remove(cmo);
        }
    }

    // accept current market updates, notfiy substribed observers
    public void acceptCurrentMarket(String symbol, CurrentMarketSide buySide, CurrentMarketSide sellSide) {
        if (!filters.containsKey(symbol)) {
            return;
        }
        ArrayList<CurrentMarketObserver> observers = filters.get(symbol);
        for (CurrentMarketObserver observer : observers) {
            observer.updateCurrentMarket(symbol, buySide, sellSide);
        }
    }
}
