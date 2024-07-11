package p2;
import p1.Price;

public class Order implements Tradable{
    // data values
    private  String user;
    private  String product;
    private  Price price;
    private  BookSide side;
    private  String id;
    private  int originalVolume;
    private int remainingVolume;
    private int cancelledVolume;
    private int filledVolume;

    // constructor
    public Order(String user, String product, Price price, BookSide side, int originalVolume) {
        this.user = user;
        this.product = product;
        this.price = price;
        this.side = side;
        this.originalVolume = originalVolume;
        this.remainingVolume = originalVolume;
        this.cancelledVolume = 0;
        this.filledVolume = 0;
        this.id = generateId();
    }

    // generate unique ID
    private String generateId() {
        return user + product + price.toString() + System.nanoTime();
    }
    // get methods for order info
    public String getUser() {
        return user;
    }

    public String getProduct() {
        return product;
    }

    public Price getPrice() {
        return price;
    }

    public BookSide getSide() {
        return side;
    }

    public String getId() {
        return id;
    }

    public int getOriginalVolume() {
        return originalVolume;
    }

    public int getRemainingVolume() {
        return remainingVolume;
    }

    // set methods to update order info
    public void setRemainingVolume(int newVol) {
        this.remainingVolume = newVol;
    }

    public int getCancelledVolume() {
        return cancelledVolume;
    }

    public void setCancelledVolume(int newVol) {
        this.cancelledVolume = newVol;
    }

    public int getFilledVolume() {
        return filledVolume;
    }

    public void setFilledVolume(int newVol) {
        this.filledVolume = newVol;
    }

    // create tradableDTO objct with order info
    public TradableDTO makeTradableDTO() {
        return new TradableDTO(user, product, price, side, id, originalVolume, remainingVolume, filledVolume, cancelledVolume);
    }

    // string
    @Override
    public String toString() {
        return String.format("%s order: %s %s at %s, Orig Vol: %d, Rem Vol: %d, Fill Vol: %d, CXL Vol: %d, ID: %s",
                user, side, product, price, originalVolume, remainingVolume, filledVolume, cancelledVolume, id);
    }

}
