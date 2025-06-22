class Order {
    int orderId;
    String customerName;
    double totalPrice;

    public Order(int orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + ", Name: " + customerName + ", Price: " + totalPrice;
    }
}

public class Exercise3 {

    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].totalPrice > orders[j + 1].totalPrice) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    public static int partition(Order[] orders, int low, int high) {
        Order pivot = orders[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (orders[j].totalPrice <= pivot.totalPrice) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }

        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;

        return i + 1;
    }

    public static void printOrders(String title, Order[] orders) {
        System.out.println(title);
        for (Order o : orders) {
            System.out.println(o);
        }
    }

    public static void main(String[] args) {
        Order[] orders = {
            new Order(101, "Kishore K M", 450.50),
            new Order(102, "Meena", 150.00),
            new Order(103, "Poona", 300.25),
            new Order(104, "Dio", 700.00)
        };

        printOrders("Original Orders:", orders);

        bubbleSort(orders);
        printOrders("\nSorted by Bubble Sort:", orders);

        orders = new Order[]{
            new Order(101, "Kishore K M", 450.50),
            new Order(102, "Meena", 150.00),
            new Order(103, "Poona", 300.25),
            new Order(104, "Dio", 700.00)
        };

        quickSort(orders, 0, orders.length - 1);
        printOrders("\nSorted by Quick Sort:", orders);
    }
}
