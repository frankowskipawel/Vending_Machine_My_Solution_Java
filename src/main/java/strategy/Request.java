package strategy;

import entity.VendingMachine;

public interface Request {

    public static VendingMachine vendingMachine = VendingMachine.getInstance();

    boolean productReleaseRequest(int shelfNumber);
}
