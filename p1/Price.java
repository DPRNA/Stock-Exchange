package p1;

import java.util.Objects;

public class Price implements Comparable<Price>{

    // price object
    public final int cents;

    // constructor for price object
    public Price(int cents) {
        this.cents = cents;
    }

    // checks if price is negative
    public boolean isNegative() {
        return cents < 0;
    }

    // returns new price object with sum of current price + price object passed
    public Price add(Price p) {
        if (p == null) {
            throw new InvalidPriceOperation("p1.Price object can not be null");
        }
        int sum = cents + p.cents;
        return new Price(sum);
    }

    // returns new price object with difference of current price - price object passed
    public Price subtract(Price p) {
        if (p == null) {
            throw new InvalidPriceOperation("p1.Price object can not be null");
        }
        int sum = cents - p.cents;
        return new Price(sum);
    }

    // returns new price object with product of current price * price object passed
    public Price multiply(int n) {
        int sum = cents * n;
        return new Price(sum);
    }

    public double toDouble() {
        return cents / 100.0; // Assuming cents represents the price in cents
    }

    // returns true if current price object is >= price object passed
    public boolean greaterOrEqual(Price p) {
        if (p == null) {
            throw new InvalidPriceOperation("p1.Price object can not be null");
        }
        return this.cents >= p.cents;
    }

    // returns true if current price object is <= price object passed
    public boolean lessOrEqual(Price p) {
        if (p == null) {
            throw new InvalidPriceOperation("p1.Price object can not be null");
        }
        return this.cents <= p.cents;
    }

    // returns true if current price object is > price object passed
    public boolean greaterThan(Price p) {
        if (p == null) {
            throw new InvalidPriceOperation("p1.Price object can not be null");
        }
        return this.cents > p.cents;
    }

    // returns true if current price object is < price object is passed
    public boolean lessThan(Price p) {
        if (p == null) {
            throw new InvalidPriceOperation("p1.Price object can not be null");
        }
        return this.cents < p.cents;
    }


    // returns true if current price object == price boject passed
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return cents == price.cents;
    }

    // returns an int value (unique per price value)
    @Override
    public int hashCode() {
        return Objects.hashCode(cents);
    }

    // returns a negative int, zero, or a positive int if this object is less than equal to, or greater than the specified object
    // the difference between current price object and price object passed
    @Override
    public int compareTo(Price p) {
        if (p == null) {
            throw new InvalidPriceOperation("p1.Price object can not be null");
        }
        return cents - p.cents;
//        return Integer.compare(cents, p.cents);
//        if (cents < p.cents) return -1;
//        if (cents == p.cents) return 0;
//        else return 1;
    }

    // returns a string containing the price object value formatted as $d*.cc
    @Override
    public String toString() {
        double dollars = cents / 100.0;
        return String.format("$%,.2f", dollars);
    }
}
