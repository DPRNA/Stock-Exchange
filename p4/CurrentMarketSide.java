package p4;
import p1.Price;

public class CurrentMarketSide {

    private final Price price;
    private final int volume;

    // constructor
    public CurrentMarketSide(Price price, int volume) {
        this.price = price; // Sets the price of the CurrentMarketSide
        this.volume = volume; // Sets the volume of the CurrentMarketSide
    }

    // toString
    @Override
    public String toString() {
        // if price is null, display is $0.00x
        if (price == null) {
            return "$0.00x" + volume;
        } else {
            // if not null, return formatted string
            return price.toString() + "x" + volume;
        }
    }
}
