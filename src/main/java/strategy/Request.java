package strategy;

import entity.VendingMachine;

public interface Request {

    void productReleaseRequest(VendingMachine vendingMachine, int shelfNumber);
}
