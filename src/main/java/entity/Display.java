package entity;

public class Display {

    public void showAllProducts(VendingMachine vendingMachine) {
        for (java.util.Map.Entry shelfs : vendingMachine.getShelfsMap().entrySet()) {
            Shelf shelf = (Shelf) shelfs.getValue();
            Product product = shelf.getProduct();
            if (product != null) {
                System.out.println(shelfs.getKey() + " : " + product.getName());
            }
        }
    }
}
