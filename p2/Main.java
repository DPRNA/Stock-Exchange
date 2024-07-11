package p2;

import p1.PriceFactory;

import static p2.BookSide.BUY;
import static p2.BookSide.SELL;

public class Main {

    public static void main(String[] args) {
        // This main content should execute w/o issue - no exceptions should occur.
        ProductBook pb = new ProductBook("TGT");
        try {
            System.out.print("1) Enter BUY order for TGT from EST");
            System.out.println(" - book shows 1 order on the BUY side");
            Order o1 = new Order("EST", "TGT", PriceFactory.makePrice(17720), BUY, 50);
            pb.add(o1);
            System.out.println(pb);
            System.out.print("========================================================");
            System.out.println("========================================================\n");
            System.out.println("2) Cancel BUY order for TGT from EST - book should be empty");
            pb.cancel(BUY, o1.getId());
            System.out.println(pb);
            System.out.print("========================================================");
            System.out.println("========================================================\n");
            System.out.print("3) Enter BUY order for TGT from ANA");
            System.out.println(" - book shows 1 order on the BUY side");
            Order o2 = new Order("ANA", "TGT", PriceFactory.makePrice(17720), BUY, 50);
            pb.add(o2);
            System.out.println(pb);
            System.out.print("========================================================");
            System.out.println("========================================================\n");
            System.out.print("4) Enter Quote for TGT from BOB - book shows 1 order on ");
            System.out.println("the BUY side and 1 quote on the BUY & SELL side");
            Quote qte = new Quote("TGT",
                    PriceFactory.makePrice(17720), 75,
                    PriceFactory.makePrice(17730), 75,
                    "BOB");
            pb.add(qte);
            System.out.println(pb);
            System.out.print("========================================================");
            System.out.println("========================================================\n");
            System.out.print("5) Enter SELL order for TGT from COD");
            System.out.println(" - book shows new SELL order on the SELL side");
            Order o3 = new Order("COD", "TGT", PriceFactory.makePrice(17730), SELL, 85);
            pb.add(o3);
            System.out.println(pb);
            System.out.print("========================================================");
            System.out.println("========================================================\n");
            System.out.print("6) Enter BUY order for TGT from DIG - fully trades against SELL-side");
            System.out.println("quote (partially) from BOB - partial sell-side quote from BOB remains");
            Order o4 = new Order("DIG", "TGT", PriceFactory.makePrice(17730), BUY, 60);
            pb.add(o4);
            System.out.println(pb);
            System.out.print("========================================================");
            System.out.println("========================================================\n");
            System.out.println("7) Cancel all open TGT orders & quotes - leaves book empty");
            pb.cancel(BUY, o2.getId());
            pb.cancel(SELL, o3.getId());
            pb.removeQuotesForUser("BOB");
            System.out.println(pb);
            System.out.print("========================================================");
            System.out.println("========================================================\n");
            System.out.print("8) Enter BUY order for TGT from ANA - ");
            System.out.println("book shows 1 order from ANA on the BUY side");
            Order o5 = new Order("ANA", "TGT", PriceFactory.makePrice(17710), BUY, 50);
            pb.add(o5);
            System.out.println(pb);
            System.out.print("========================================================");
            System.out.println("========================================================\n");
            System.out.print("9) Enter BUY order for TGT from BOB at a lesser price - ");
            System.out.println("book contains 2 BUY orders at 2 prices");
            Order o6 = new Order("BOB", "TGT", PriceFactory.makePrice(17720), BUY, 100);
            pb.add(o6);
            System.out.println(pb);
            System.out.print("========================================================");
            System.out.println("========================================================\n");
            System.out.print("10) Enter BUY order for TGT from COD at an even lesser price");
            System.out.println(" - book contains 3 BUY orders at 3 prices");
            Order o7 = new Order("COD", "TGT", PriceFactory.makePrice(17730), BUY, 150);
            pb.add(o7);
            System.out.println(pb);
            System.out.print("========================================================");
            System.out.println("========================================================\n");
            System.out.print("11) Enter Quote for TGT from DIG at a better BUY side - ");
            System.out.println("book shows quote sides at the top of both BUY & SELL sides");
            qte = new Quote("TGT",
                    PriceFactory.makePrice(17740), 200,
                    PriceFactory.makePrice(17750), 200,
                    "DIG");
            pb.add(qte);
            System.out.println(pb);
            System.out.print("========================================================");
            System.out.println("========================================================\n");
            System.out.print("12) Enter large SELL order for TGT from EST to trade out BUY side - ");
            System.out.println("trades with everything on the BUY side, leaving 1 SELL-side quote");
            Order o8 = new Order("EST", "TGT", PriceFactory.makePrice(17710), SELL, 500);
            pb.add(o8);
            System.out.println(pb);
            System.out.print("========================================================");
            System.out.println("========================================================\n");
            System.out.println("13) Cancel TGT quote from DIG - leaves the book empty");
            pb.removeQuotesForUser("DIG");
            System.out.println(pb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


