import java.util.Scanner;
public class Exercise7 {
    public static double Value(double p, double r, int y) {
        if (y == 0) {
            return p;
        } else {
            return Value(p, r, y - 1) * (1 + r);
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double p = in.nextDouble(); 
        double r = in.nextDouble();        
        int y = in.nextInt();            
        double result = Value(p, r, y);
        System.out.printf("Future value after %d years: %.2f\n", y, result);
        in.close();
    }
}
