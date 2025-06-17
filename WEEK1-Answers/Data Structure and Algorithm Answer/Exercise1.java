//Inventory Management System
import java.util.*;

public class Exercise1 {
    static class Product {
        int id;
        String name;
        int quantity;
        double price;

        Product(int id, String name, int quantity, double price) {
            this.id = id;
            this.name = name;
            this.quantity = quantity;
            this.price = price;
        }

        public String toString() {
            return "ID: " + id + ", Name: " + name + ", Qty: " + quantity + ", Price: " + price;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<Integer, Product> inventory = new HashMap<>();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Show All Products");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            if (choice == 0) {
                System.out.println("Exiting");
                break;
            }

            if (choice == 1) {
                System.out.print("Enter Product ID: ");
                int id = sc.nextInt();
                sc.nextLine(); 
                System.out.print("Enter Product Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Quantity: ");
                int qty = sc.nextInt();
                System.out.print("Enter Price: ");
                double price = sc.nextDouble();

                Product p = new Product(id, name, qty, price);
                inventory.put(id, p);
                System.out.println(" Product added.");

            } else if (choice == 2) {
                System.out.print("Enter Product ID to update: ");
                int id = sc.nextInt();
                if (inventory.containsKey(id)) {
                    sc.nextLine(); 
                    System.out.print("Enter New Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter New Quantity: ");
                    int qty = sc.nextInt();
                    System.out.print("Enter New Price: ");
                    double price = sc.nextDouble();

                    Product p = new Product(id, name, qty, price);
                    inventory.put(id, p);
                    System.out.println(" Product updated.");
                } else {
                    System.out.println(" Product not found.");
                }

            } else if (choice == 3) {
                System.out.print("Enter Product ID to delete: ");
                int id = sc.nextInt();
                if (inventory.containsKey(id)) {
                    inventory.remove(id);
                    System.out.println(" Product deleted.");
                } else {
                    System.out.println(" Product not found.");
                }

            } else if (choice == 4) {
                if (inventory.isEmpty()) {
                    System.out.println("No products in inventory.");
                } else {
                    System.out.println("All Products:");
                    for (Product p : inventory.values()) {
                        System.out.println(p);
                    }
                }

            } else {
                System.out.println(" Invalid choice.");
            }
        }
        sc.close();
    }
}
