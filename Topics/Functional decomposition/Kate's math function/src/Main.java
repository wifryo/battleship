import java.util.Scanner;

class MultipleFunction {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double x = scanner.nextDouble();
        System.out.println(f(x));
    }

    public static double f(double x) {
        if (x <= 0) {
        return f1(x);
        }
        if (x > 0 && x < 1) {
            return f2(x);
        }
        return f3(x);
    }

    //implement your methods here
    public static double f1(double jim) {
        return (jim*jim +1);
    }

    public static double f2(double stevesy) {
        return(1/(stevesy*stevesy));
    }

    public static double f3(double bogger) {
        return(bogger*bogger-1);
    }
}