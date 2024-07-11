package p1;

import java.util.HashMap;
import java.util.Map;

public class PriceFactory {
    
    private static final Map<Integer, Price> hash = new HashMap<>();

    public static Price makePrice(int value) {
        // check if price object for given val is already in hash
        Price price = hash.get(value);

        // create new price object if it does not exist and add to hash
        if (price == null) {
            price = new Price(value);
            hash.put(value, price);
        }

        return price;
    }

    public static Price makePrice(String stringValueIn) {
        // get rid of commas and $
        stringValueIn = stringValueIn.replaceAll("[,$]", "");
        double doubleValue = Double.parseDouble(stringValueIn);
        // convert dollars to cents
        double doubleCents = doubleValue * 100;
        int intValue = (int) doubleCents;

        // Call the makePrice method with the integer value
        return makePrice(intValue);
    }
}
