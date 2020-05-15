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

        int numberSelectedShelf = 0;
        boolean isSelectedShelfCorrect = false;
        while (!isSelectedShelfCorrect) {
            try {
                display.showMessage("Wybierz numer półki : ");
                numberSelectedShelf = scanner.nextInt();
                shelf.shelfNumberValidation(vendingMachine, numberSelectedShelf);
                if (vendingMachine.isShelfNotEmpty(numberSelectedShelf)) {
                    display.showSelectedProduct(vendingMachine, numberSelectedShelf);
                    isSelectedShelfCorrect = true;
                    shelf = vendingMachine.getShelfsMap().get(numberSelectedShelf);
                } else {
                    display.showMessage("Brak produktów na wybranej półce!");
                }
            } catch (Exception e) {
                display.showMessage(e.getMessage());
            }
        }

        boolean isAddedCoin = true;
        double missingAmount=0;
        while (isAddedCoin) {
            try {
                missingAmount=vendingMachine.sumCoinsFromTempCoinBox()-shelf.getProduct().getPrice();
                String prefix = "";
                if (missingAmount<0){prefix="pozostało";}
                if (missingAmount>0){prefix="OK (za dużo)";}
                if (missingAmount==0){prefix="OK (pozostało)";}

                display.showMessage(String.format("Wrzuć monete (wpisz nominał) %s %.2fPLN: ", prefix, missingAmount));
                String coinValue = scanner.next().toUpperCase();

                if (coinValue.equals("OK")) {
                    isAddedCoin = !vendingMachine.requestProduct(numberSelectedShelf);
                    if (isAddedCoin == false) {
                        break;
                    }
                } else if (coinValue.equals("ANULUJ")) {
                    vendingMachine.returnCoinsFromTempCoinBox();
                    break;
                } else {
                    double coinInDouble = Double.parseDouble(coinValue);
                    Coin.coinValidation(coinInDouble);
                    vendingMachine.putCoinToTempCoinBox(Coin.getCoinByValue(coinInDouble));
                }
            } catch (Exception e) {
                display.showMessage("Niepoprawny nominał! " + e.getMessage());
            }
        }
        vendingMachine.putCoinsFromTempCoinBoxToCashInStock();
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
