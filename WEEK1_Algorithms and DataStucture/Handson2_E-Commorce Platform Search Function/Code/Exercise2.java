import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Product {
    int productId;
    String productName;
    String category;

    public Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product ID: " + productId + ", Name: " + productName + ", Category: " + category;
    }
}

public class Exercise2 {

    public static Product linearSearch(Product[] products, String name) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].productName.equalsIgnoreCase(name)) {
                return products[i];
            }
        }
        return null;
    }

    public static Product binarySearch(Product[] products, String name) {
        int left = 0;
        int right = products.length - 1;

        for (; left <= right;) {
            int mid = left + (right - left) / 2;
            int cmp = products[mid].productName.compareToIgnoreCase(name);

            if (cmp == 0) {
                return products[mid];
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Product[] products = {
            new Product(101, "Laptop", "Electronics"),
            new Product(102, "Shirt", "Clothing"),
            new Product(103, "Watch", "Accessories"),
            new Product(104, "Phone", "Electronics"),
            new Product(105, "Shoes", "Footwear")
        };

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product name to search: ");
        String searchName = sc.nextLine();

        System.out.println("\n Linear Search :");
        Product foundLinear = linearSearch(products, searchName);
        if (foundLinear != null) {
            System.out.println("Found: " + foundLinear);
        } else {
            System.out.println("Product not found.");
        }

        Arrays.sort(products, new Comparator<Product>() {
            public int compare(Product p1, Product p2) {
                return p1.productName.compareToIgnoreCase(p2.productName);
            }
        });

        System.out.println("\n Binary Search :");
        Product foundBinary = binarySearch(products, searchName);
        if (foundBinary != null) {
            System.out.println("Found: " + foundBinary);
        } else {
            System.out.println("Product not found.");
        }

        sc.close();
    }
}
