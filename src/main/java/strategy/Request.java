package strategy;

import entity.VendingMachine;

public interface Request {

    boolean productReleaseRequest(VendingMachine vendingMachine, int shelfNumber);
}
