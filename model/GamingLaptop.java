package model;

public class GamingLaptop extends Laptop {

    public GamingLaptop(String id, String brand, String model,
                        int ram, int storage, double price) {

        super(id, brand, model, ram, storage, price, "Gaming");
    }

    @Override
    public String getLaptopCategory() {
        return "Gaming Laptop";
    }
}