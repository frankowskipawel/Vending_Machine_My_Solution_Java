import entity.*;

import java.util.Scanner;

public class Main {

    private static VendingMachine vendingMachine = new VendingMachine();
    private static Display display = new Display();

    public static void main(String[] args) {
        initialize();

        display.showAllProducts(vendingMachine);
        Scanner scanner = new Scanner(System.in);
        Shelf shelf = new Shelf();

        int selectedShelf = 0;
        boolean isSelectedShelfCorrect = false;
        while (!isSelectedShelfCorrect) {
            try {
                display.showMessage("Wybierz numer półki : ");
                selectedShelf = scanner.nextInt();
                shelf.shelfNumberValidation(vendingMachine, selectedShelf);
                if (vendingMachine.isShelfNotEmpty(selectedShelf)) {
                    display.showSelectedProduct(vendingMachine, selectedShelf);
                    isSelectedShelfCorrect = true;
                } else {
                    display.showMessage("Brak produktów na wybranej półce!");
                }
            } catch (Exception e) {
                display.showMessage(e.getMessage());
            }
        }

        boolean isAddedCoin = true;
        while (isAddedCoin) {
            try {
                display.showMessage("Wrzuć monete (wpisz nominał): ");
                String coinValue = scanner.next().toUpperCase();
                if (coinValue.equals("OK")) {
                    vendingMachine.dispenseProduct(selectedShelf);
                    display.showMessage("Wydano towar.");
                    break;
                }
                if (coinValue.equals("ANULUJ")) {
                    vendingMachine.returnCoinsFromTempCoinBox();
                    break;
                }
                double pasreToDoubleCoin = Double.parseDouble(coinValue);
                Coin.coinValidation(pasreToDoubleCoin);
                vendingMachine.putCoinToTempCoinBox(Coin.getCoinByValue(pasreToDoubleCoin));
            } catch (Exception e) {
                display.showMessage("Niepoprawny nominał! "+e.getMessage());
            }
        }
        vendingMachine.putCoinsFromTempCoinBoxToCashInStock();
        System.out.println(vendingMachine.getCashBox().getCashInStock());
    }

    private static void initialize() {
        try {
            Product product1 = new Product("Coca Cola 0.25l", 1.5);
            Product product2 = new Product("Sprite 0.25l", 1.5);
            Product product3 = new Product("Fanta 0.25l", 1.5);
            Product product4 = new Product("Snickers 100g", 1);
            Product product5 = new Product("Prince Polo 50g", 1.2);
            Product product6 = new Product("Mineral Water 500ml", 1.3);
            Product product7 = new Product("Rogal 7-days", 2);
            Product product8 = new Product("Sok pomarańczowy Cappy 0,2l", 2.5);
            Product product9 = new Product("Sok Jabłkowy Cappy 0,2l", 2.5);
            Product product10 = new Product("Baton Twix 80g", 1);
            vendingMachine.addProduct(1, product1, 8);
            vendingMachine.addProduct(2, product2, 5);
            vendingMachine.addProduct(3, product3, 9);
            vendingMachine.addProduct(4, product4, 10);
            vendingMachine.addProduct(5, product5, 8);
            vendingMachine.addProduct(6, product6, 10);
            vendingMachine.addProduct(7, product7, 10);
            vendingMachine.addProduct(20, product8, 9);
            vendingMachine.addProduct(21, product9, 10);
            vendingMachine.addProduct(25, product10, 10);
        } catch (Exception e) {
            display.showMessage(e.getMessage());
        }
    }
}
