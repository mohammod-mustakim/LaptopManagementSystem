package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import service.LaptopManager;
import model.Laptop;
import model.GamingLaptop;
import model.BusinessLaptop;

import java.awt.Font;

public class LaptopManagementGUI extends JFrame {

    public LaptopManagementGUI() {

        setTitle("Laptop Management System");
        setSize(1000, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        LaptopManager manager = new LaptopManager();
        manager.loadFromFile();

        JLabel title = new JLabel("Laptop Management System");
        title.setBounds(300, 20, 450, 40);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        panel.add(title);

        JPanel informationPanel = new JPanel();
        informationPanel.setLayout(null);
        informationPanel.setBounds(40, 80, 920, 390);
        informationPanel.setBorder(BorderFactory.createEtchedBorder());
        panel.add(informationPanel);

        JLabel informationLabel = new JLabel("Laptop Information");
        informationLabel.setBounds(20, 10, 250, 30);
        informationLabel.setFont(new Font("Arial", Font.BOLD, 20));
        informationPanel.add(informationLabel);

        // ID
        JLabel idLabel = new JLabel("Laptop ID:");
        idLabel.setBounds(40, 55, 120, 30);
        informationPanel.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(160, 55, 300, 30);
        informationPanel.add(idField);

        // Brand
        JLabel brandLabel = new JLabel("Brand:");
        brandLabel.setBounds(40, 95, 120, 30);
        informationPanel.add(brandLabel);

        JTextField brandField = new JTextField();
        brandField.setBounds(160, 95, 300, 30);
        informationPanel.add(brandField);

        // Model
        JLabel modelLabel = new JLabel("Model:");
        modelLabel.setBounds(40, 135, 120, 30);
        informationPanel.add(modelLabel);

        JTextField modelField = new JTextField();
        modelField.setBounds(160, 135, 300, 30);
        informationPanel.add(modelField);

        // RAM
        JLabel ramLabel = new JLabel("RAM (GB):");
        ramLabel.setBounds(40, 175, 120, 30);
        informationPanel.add(ramLabel);

        JTextField ramField = new JTextField();
        ramField.setBounds(160, 175, 300, 30);
        informationPanel.add(ramField);

        // Storage
        JLabel storageLabel = new JLabel("Storage (GB):");
        storageLabel.setBounds(40, 215, 120, 30);
        informationPanel.add(storageLabel);

        JTextField storageField = new JTextField();
        storageField.setBounds(160, 215, 300, 30);
        informationPanel.add(storageField);

        // Price
        JLabel priceLabel = new JLabel("Price (TK):");
        priceLabel.setBounds(40, 255, 120, 30);
        informationPanel.add(priceLabel);

        JTextField priceField = new JTextField();
        priceField.setBounds(160, 255, 300, 30);
        informationPanel.add(priceField);

        // Type
        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setBounds(40, 295, 120, 30);
        informationPanel.add(typeLabel);

        String[] types = {
            "Gaming Laptop",
            "Business Laptop"
        };

        JComboBox<String> typeComboBox = new JComboBox<>(types);
        typeComboBox.setBounds(160, 295, 300, 30);
        informationPanel.add(typeComboBox);

        // Buttons
        JButton insertButton = new JButton("Insert");
        insertButton.setBounds(20, 340, 100, 40);
        informationPanel.add(insertButton);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(130, 340, 100, 40);
        informationPanel.add(updateButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(240, 340, 100, 40);
        informationPanel.add(deleteButton);

        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(350, 340, 100, 40);
        informationPanel.add(clearButton);

        // Insert Button Hover Effect
        insertButton.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseEntered(java.awt.event.MouseEvent e) {
                insertButton.setBackground(java.awt.Color.LIGHT_GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                insertButton.setBackground(null);
            }
        });

        // Update Button Hover Effect
        updateButton.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseEntered(java.awt.event.MouseEvent e) {
                updateButton.setBackground(java.awt.Color.LIGHT_GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                updateButton.setBackground(null);
            }
        });

        // Delete Button Hover Effect
        deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseEntered(java.awt.event.MouseEvent e) {
                deleteButton.setBackground(java.awt.Color.LIGHT_GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                deleteButton.setBackground(null);
            }
        });

        // Clear Button Hover Effect
        clearButton.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseEntered(java.awt.event.MouseEvent e) {
                clearButton.setBackground(java.awt.Color.LIGHT_GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                clearButton.setBackground(null);
            }
        });

        // Laptop List
        JLabel listLabel = new JLabel("Laptop List");
        listLabel.setBounds(40, 490, 250, 30);
        listLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(listLabel);

        String[] columns = {
            "ID",
            "Brand",
            "Model",
            "RAM",
            "Storage",
            "Price",
            "Type"
        };

        DefaultTableModel tableModel =
                new DefaultTableModel(columns, 0);

        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(40, 530, 920, 220);
        panel.add(scrollPane);

        JLabel totalLabel = new JLabel("Total Laptops: 0");
        totalLabel.setBounds(40, 765, 250, 30);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(totalLabel);


        // ==========================================
        // LOAD SAVED LAPTOPS INTO TABLE
        // ==========================================

        for (Laptop laptop : manager.getLaptops()) {

            tableModel.addRow(new Object[]{

                laptop.getId(),
                laptop.getBrand(),
                laptop.getModel(),
                laptop.getRam(),
                laptop.getStorage(),
                laptop.getPrice(),
                laptop.getType()

            });
        }

        // Update total laptop count
        totalLabel.setText(
                "Total Laptops: "
                + manager.getLaptops().size()
        );


        // ==========================================
        // INSERT BUTTON
        // ==========================================

        insertButton.addActionListener(e -> {

            try {

                String id = idField.getText();
                String brand = brandField.getText();
                String model = modelField.getText();

                if (id.isEmpty()
                        || brand.isEmpty()
                        || model.isEmpty()
                        || ramField.getText().isEmpty()
                        || storageField.getText().isEmpty()
                        || priceField.getText().isEmpty()) {

                    throw new Exception(
                            "Please fill all fields."
                    );
                }

                int ram =
                        Integer.parseInt(
                                ramField.getText()
                        );

                int storage =
                        Integer.parseInt(
                                storageField.getText()
                        );

                double price =
                        Double.parseDouble(
                                priceField.getText()
                        );

                String type =
                        typeComboBox
                                .getSelectedItem()
                                .toString();

                Laptop laptop;

                if (type.equals("Gaming Laptop")) {

                    laptop = new GamingLaptop(
                            id,
                            brand,
                            model,
                            ram,
                            storage,
                            price
                    );

                } else {

                    laptop = new BusinessLaptop(
                            id,
                            brand,
                            model,
                            ram,
                            storage,
                            price
                    );
                }

                boolean added =
                        manager.addLaptop(laptop);

                if (added) {

                    // Add new laptop to table
                    tableModel.addRow(new Object[]{

                        laptop.getId(),
                        laptop.getBrand(),
                        laptop.getModel(),
                        laptop.getRam(),
                        laptop.getStorage(),
                        laptop.getPrice(),
                        laptop.getType()

                    });

                    // Update total
                    totalLabel.setText(
                            "Total Laptops: "
                            + manager.getLaptops().size()
                    );

                    JOptionPane.showMessageDialog(
                            this,
                            "Laptop inserted successfully."
                    );

                } else {

                    throw new Exception(
                            "Laptop ID already exists."
                    );
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "RAM, Storage and Price must be numbers."
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage()
                );
            }
        });


        // ==========================================
        // UPDATE BUTTON
        // ==========================================

        updateButton.addActionListener(e -> {

            try {

                String id = idField.getText();
                String brand = brandField.getText();
                String model = modelField.getText();

                if (id.isEmpty()
                        || brand.isEmpty()
                        || model.isEmpty()
                        || ramField.getText().isEmpty()
                        || storageField.getText().isEmpty()
                        || priceField.getText().isEmpty()) {

                    throw new Exception(
                            "Please fill all fields."
                    );
                }

                int ram;

                try {

                    ram = Integer.parseInt(
                            ramField.getText()
                    );

                } catch (NumberFormatException ex) {

                    throw new Exception(
                            "RAM must be an integer."
                    );
                }

                int storage;

                try {

                    storage = Integer.parseInt(
                            storageField.getText()
                    );

                } catch (NumberFormatException ex) {

                    throw new Exception(
                            "Storage must be an integer."
                    );
                }

                double price;

                try {

                    price = Double.parseDouble(
                            priceField.getText()
                    );

                } catch (NumberFormatException ex) {

                    throw new Exception(
                            "Price must be a number."
                    );
                }

                String type =
                        typeComboBox
                                .getSelectedItem()
                                .toString();

                boolean updated =
                        manager.updateLaptop(
                                id,
                                brand,
                                model,
                                ram,
                                storage,
                                price,
                                type
                        );

                if (updated) {

                    // Update JTable
                    for (int i = 0;
                            i < tableModel.getRowCount();
                            i++) {

                        if (tableModel.getValueAt(i, 0)
                                .toString()
                                .equals(id)) {

                            tableModel.setValueAt(
                                    brand,
                                    i,
                                    1
                            );

                            tableModel.setValueAt(
                                    model,
                                    i,
                                    2
                            );

                            tableModel.setValueAt(
                                    ram,
                                    i,
                                    3
                            );

                            tableModel.setValueAt(
                                    storage,
                                    i,
                                    4
                            );

                            tableModel.setValueAt(
                                    price,
                                    i,
                                    5
                            );

                            tableModel.setValueAt(
                                    type,
                                    i,
                                    6
                            );

                            break;
                        }
                    }

                    JOptionPane.showMessageDialog(
                            this,
                            "Laptop updated successfully."
                    );

                } else {

                    throw new Exception(
                            "Laptop not found."
                    );
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Exception: "
                        + ex.getMessage()
                );
            }
        });


        // ==========================================
        // SELECT ROW FROM TABLE
        // ==========================================

        table.getSelectionModel()
                .addListSelectionListener(e -> {

            int row = table.getSelectedRow();

            if (row != -1) {

                idField.setText(
                        tableModel
                                .getValueAt(row, 0)
                                .toString()
                );

                brandField.setText(
                        tableModel
                                .getValueAt(row, 1)
                                .toString()
                );

                modelField.setText(
                        tableModel
                                .getValueAt(row, 2)
                                .toString()
                );

                ramField.setText(
                        tableModel
                                .getValueAt(row, 3)
                                .toString()
                );

                storageField.setText(
                        tableModel
                                .getValueAt(row, 4)
                                .toString()
                );

                priceField.setText(
                        tableModel
                                .getValueAt(row, 5)
                                .toString()
                );

                typeComboBox.setSelectedItem(
                        tableModel
                                .getValueAt(row, 6)
                                .toString()
                );
            }
        });


        // ==========================================
        // DELETE BUTTON
        // ==========================================

        deleteButton.addActionListener(e -> {

            String id = idField.getText();

            boolean deleted =
                    manager.deleteLaptop(id);

            if (deleted) {

                for (int i = 0;
                        i < tableModel.getRowCount();
                        i++) {

                    if (tableModel.getValueAt(i, 0)
                            .toString()
                            .equals(id)) {

                        tableModel.removeRow(i);

                        break;
                    }
                }

                totalLabel.setText(
                        "Total Laptops: "
                        + manager.getLaptops().size()
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Laptop deleted successfully."
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Laptop not found."
                );
            }
        });


        // ==========================================
        // CLEAR BUTTON
        // ==========================================

        clearButton.addActionListener(e -> {

            idField.setText("");
            brandField.setText("");
            modelField.setText("");
            ramField.setText("");
            storageField.setText("");
            priceField.setText("");

            typeComboBox.setSelectedIndex(0);
        });


        add(panel);

        setVisible(true);
    }
}