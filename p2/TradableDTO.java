package p2;

import p1.Price;

public class TradableDTO{
    // data elements
    public  String user;
    public  String product;
    public  Price price;
    public  BookSide side;
    public  String id;
    public  int originalVolume;
    public  int remainingVolume;
    public  int filledVolume;
    public  int cancelledVolume;

    // constructor
    public TradableDTO(String user, String product, Price price, BookSide side, String id, int originalVolume, int remainingVolume, int filledVolume, int cancelledVolume) {
        this.user = user;
        this.product = product;
        this.price = price;
        this.side = side;
        this.id = id;
        this.originalVolume = originalVolume;
        this.remainingVolume = remainingVolume;
        this.filledVolume = filledVolume;
        this.cancelledVolume = cancelledVolume;


    }

    public String getProduct() {
        return product;
    }

    public BookSide getSide() {
        return side;
    }

    public String getId() {
        return id;
    }



    // string
    @Override
    public String toString() {
        return String.format("%s quote from %s: %s, Orig Vol: %d, Rem Vol: %d, Fill Vol: %d, CXL Vol: %d, ID: %s",
                product, user, price, originalVolume, remainingVolume, filledVolume, cancelledVolume, id);
    }
}
