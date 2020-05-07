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

    public void showSelectedProduct(VendingMachine vendingMachine, int numberOfShelf){
        Shelf selectedshelf = vendingMachine.getShelfsMap().get(numberOfShelf);
        String productName = selectedshelf.getProduct().getName();
        double productPrice = selectedshelf.getProduct().getPrice();
        System.out.println(String.format("Wybrałeś %s CENA : %s PLN",productName,productPrice));
    }

    public void showMessage(String message){
        System.out.println(message);
    }


}
