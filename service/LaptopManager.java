package service;

import java.util.ArrayList;
import java.io.*;
import model.Laptop;
import model.GamingLaptop;
import model.BusinessLaptop;

public class LaptopManager {

    private ArrayList<Laptop> laptops;

    public LaptopManager() {
        laptops = new ArrayList<>();
    }

    // Insert laptop
    public boolean addLaptop(Laptop laptop) {

        for (Laptop l : laptops) {

            if (l.getId().equals(laptop.getId())) {
                return false;
            }
        }

        laptops.add(laptop);
        saveToFile();

        return true;
    }

    // Get all laptops
    public ArrayList<Laptop> getLaptops() {
        return laptops;
    }

    // Update laptop
    public boolean updateLaptop(String id, String brand, String model,
                                int ram, int storage, double price, String type) {

        for (Laptop laptop : laptops) {

            if (laptop.getId().equals(id)) {

                laptop.setBrand(brand);
                laptop.setModel(model);
                laptop.setRam(ram);
                laptop.setStorage(storage);
                laptop.setPrice(price);
                laptop.setType(type);

                saveToFile();

                return true;
            }
        }

        return false;
    }

    // Delete laptop
    public boolean deleteLaptop(String id) {

        for (int i = 0; i < laptops.size(); i++) {

            if (laptops.get(i).getId().equals(id)) {

                laptops.remove(i);
                saveToFile();

                return true;
            }
        }

        return false;
    }

    // Save laptop data to file
    public void saveToFile() {

        try {

            BufferedWriter writer =
                    new BufferedWriter(new FileWriter("laptop_data.txt"));

            for (Laptop laptop : laptops) {

                writer.write(
                    laptop.getId() + "," +
                    laptop.getBrand() + "," +
                    laptop.getModel() + "," +
                    laptop.getRam() + "," +
                    laptop.getStorage() + "," +
                    laptop.getPrice() + "," +
                    laptop.getType()
                );

                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {

            System.out.println("Error saving data.");
        }
    }

    // Load laptop data from file
    public void loadFromFile() {

        try {

            BufferedReader reader =
                    new BufferedReader(new FileReader("laptop_data.txt"));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                String id = data[0];
                String brand = data[1];
                String model = data[2];
                int ram = Integer.parseInt(data[3]);
                int storage = Integer.parseInt(data[4]);
                double price = Double.parseDouble(data[5]);
                String type = data[6];

                Laptop laptop;

                if (type.equals("Gaming Laptop")){

                    laptop = new GamingLaptop(
                        id, brand, model, ram, storage, price
                    );

                } else {

                    laptop = new BusinessLaptop(
                        id, brand, model, ram, storage, price
                    );
                }

                laptops.add(laptop);
            }

            reader.close();

        } catch (IOException e) {

            System.out.println("Error loading data.");
        }
    }
}