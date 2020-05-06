package entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class VendingMachine {
    private List<Product> productsList = new ArrayList<>();

    public void addProduct(Product product) {
        productsList.add(product);
    }



}
