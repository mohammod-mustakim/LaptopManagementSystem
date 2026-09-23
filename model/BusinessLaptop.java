package model;

public class BusinessLaptop extends Laptop {

    public BusinessLaptop(String id, String brand, String model,
                          int ram, int storage, double price) {

        super(id, brand, model, ram, storage, price, "Business");
    }

    @Override
    public String getLaptopCategory() {
        return "Business Laptop";
    }
}