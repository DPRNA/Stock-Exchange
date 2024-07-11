package p1;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // tests
        System.out.println("p1.PriceFactory.makePrice() tests:");
        System.out.println(PriceFactory.makePrice("000"));
        System.out.println(PriceFactory.makePrice("001.76"));
        System.out.println(PriceFactory.makePrice("3,333.33"));
        System.out.println(PriceFactory.makePrice("$-12.85"));
        System.out.println(PriceFactory.makePrice("-.89"));
        System.out.println(PriceFactory.makePrice("001.76"));
        System.out.println();

        Price price1 = PriceFactory.makePrice("12.99");
        Price price2 = PriceFactory.makePrice("24.99");
        Price price3 = PriceFactory.makePrice("-0.54");
        Price price4 = PriceFactory.makePrice("12.99");

        System.out.println("isNegative:");
        System.out.println("p1.Price.java tests");
        System.out.println(price1.isNegative());
        System.out.println(price3.isNegative());
        System.out.println();

        System.out.println("add:");
        System.out.println(price1.add(price2));
        System.out.println(price3.add(price1));
        System.out.println();

        System.out.println("subtract:");
        System.out.println(price1.subtract(price2));
        System.out.println(price1.subtract(price1));
        System.out.println();

        System.out.println("multiply:");
        System.out.println(price1.multiply(2));
        System.out.println();

        System.out.println("greaterOrEqual:");
        System.out.println(price1.greaterOrEqual(price2));
        System.out.println(price2.greaterOrEqual(price1));
        System.out.println();

        System.out.println("lessOrEqual:");
        System.out.println(price1.lessOrEqual(price2));
        System.out.println(price2.lessOrEqual(price1));
        System.out.println();

        System.out.println("greaterThan:");
        System.out.println(price1.greaterThan(price2));
        System.out.println(price2.greaterThan(price1));
        System.out.println();

        System.out.println("lessThan:");
        System.out.println(price1.lessThan(price2));
        System.out.println(price2.lessThan(price1));
        System.out.println();

        System.out.println("equals:");
        System.out.println(price1.equals(price2));
        System.out.println(price1.equals(price4));
        System.out.println();

        System.out.println("compareTo:");
        System.out.println(price1.compareTo(price2));
        System.out.println(price2.compareTo(price1));
        System.out.println(price1.compareTo(price4));
        System.out.println();

        System.out.println("toString:");
        System.out.println(price1.toString());
        System.out.println(price2.toString());
        System.out.println(price3.toString());
        System.out.println(price4.toString());
    }

}