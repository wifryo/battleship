import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long input1 = scanner.nextLong();
        String operation = scanner.next();
        long input2 = scanner.nextLong();

        switch (operation) {
            case "+":
                System.out.println(input1 + input2);
                break;
            case "-":
                System.out.println(input1 - input2);
                break;
            case "/":
                if (input2 == 0){
                    System.out.println("Division by 0!");
                }
                else {
                    System.out.println(input1 / input2);
                }
                break;
            case "*":
                System.out.println(input1 * input2);
                break;
            default:
                System.out.println("Unknown operator");
                break;    }
}}