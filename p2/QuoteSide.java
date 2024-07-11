package p2;
import p1.Price;

public class QuoteSide {
    // data elements
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
    public QuoteSide(String user, String product, Price price, int originalVolume, BookSide side) {
        this.user = user;
        this.product = product;
        this.price = price;
        this.originalVolume = originalVolume;
        this.remainingVolume = originalVolume;
        this.cancelledVolume = 0;
        this.filledVolume = 0;
        this.side = side;
        this.id = generateId();
    }

    private String generateId() {
        return user + product + price.toString() + System.nanoTime();
    }

    // get methods

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

    // set methods
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

    public Order toOrder() {
        return new Order(user, product, price, side, originalVolume);
    }


    // create tradableDTO objct with order info
    public TradableDTO makeTradableDTO() {
        return new TradableDTO(user, product, price, side, id, originalVolume, remainingVolume, filledVolume, cancelledVolume);
    }

    // string
    @Override
    public String toString() {
        return String.format("%s quote from %s: %s, Orig Vol: %d, Rem Vol: %d, Fill Vol: %d, CXL Vol: %d, ID: %s",
                product, user, price, originalVolume, remainingVolume, filledVolume, cancelledVolume, id);
    }
}
