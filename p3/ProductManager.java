package p3;

import p2.Tradable;
import p2.TradableDTO;
import p2.ProductBook;
import p2.Quote;
import p2.QuoteSide;
import p2.BookSide;
import p2.Order;
import p2.Tradable;


import java.util.HashMap;
import java.util.Random;

public class ProductManager {
    private static ProductManager instance; //singleton

    //HashMap for productbooks
    private final HashMap<String, ProductBook> productBooks;

    //constructor
    private ProductManager() {
        productBooks = new HashMap<>();
    }

    // get instance of prodcutmanager
    public static ProductManager getInstance() {
        if (instance == null) {
            instance = new ProductManager();
        }
        return instance;
    }

    // add new productbook for certain symbol
    public void addProduct(String symbol) {
        // check if symbol is valid
        if (symbol == null || !symbol.matches("[A-Z]{3}")) {
            // three letters
            // if not throuw exception
            throw new DataValidationException("Invalid product symbol.");
        }
        // make new book and add to map
        ProductBook productBook = new ProductBook(symbol);
        productBooks.put(symbol, productBook);
    }

    // get product based on symbol
    public ProductBook getProductBook(String symbol) {
        // check if product exists
        if (!productBooks.containsKey(symbol)) {
            throw new DataValidationException("Product does not exist.");
        }
        return productBooks.get(symbol);
    }

    // get random product symbol from PRoductBooks
    public String getRandomProduct() {
        if (productBooks.isEmpty()) { // check if product exists
            throw new DataValidationException("No products exist.");
        }
        // convert keys of hashmap to array
        Object[] productsArray = productBooks.keySet().toArray();
        // choose random symbol
        int randomIndex = new Random().nextInt(productsArray.length);
        return (String) productsArray[randomIndex];
    }

    // add tradable to productbook and usermanager
    public TradableDTO addTradable(Tradable o) {
        if (o == null) { // check if tradable is valid
            throw new DataValidationException("Tradable cannot be null.");
        }
        // get associated productbook with tradable
        ProductBook productBook = getProductBook(o.getProduct());
        // add tradable
        TradableDTO tradableDTO = productBook.add(o);
        UserManager.getInstance().addToUser(o.getUser(), tradableDTO);
        return tradableDTO;
    }

    // add quote to productbook
    public TradableDTO[] addQuote(Quote q) {
        if (q == null) { // check if quote is valid
            throw new DataValidationException("Quote cannot be null.");
        }
        // get associated productbook with tradable
        ProductBook productBook = getProductBook(q.getSymbol());
        productBook.removeQuotesForUser(q.getUser());
        // add buy and sell from the quote to productbook and usermananger
        TradableDTO buyDTO = addTradable(q.getQuoteSide(BookSide.BUY).toOrder());
        TradableDTO sellDTO = addTradable(q.getQuoteSide(BookSide.SELL).toOrder());
        return new TradableDTO[]{buyDTO, sellDTO};
    }

    // cancel TradableDTO in ProductBook
    public TradableDTO cancel(TradableDTO o) {
        if (o == null) { // check if tradabledto is valid
            throw new DataValidationException("TradableDTO cannot be null.");
        }
        // get productbook asociated with TradableDTO, cancel TDTO
        ProductBook productBook = getProductBook(o.getProduct());
        return productBook.cancel(o.getSide(), o.getId());
    }

    // cancel quotes for certian product/user
    public TradableDTO[] cancelQuote(String symbol, String user) {
        if (symbol == null) { // check if product symbol is valid
            throw new DataValidationException("Symbol cannot be null.");
        }
        if (user == null) { // check is user is valid
            throw new DataValidationException("User cannot be null.");
        }
        ProductBook productBook = getProductBook(symbol);
        return productBook.removeQuotesForUser(user);
    }

    // Override toString method
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ProductBook productBook : productBooks.values()) {
            sb.append(productBook.toString()).append("\n");
        }
        return sb.toString();
    }
}
