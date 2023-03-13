import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        int[][] chodeMatrix = new int[number][number];
        String output = "YES";
        for (int i = 0; i < number; i++ ){
            for (int j = 0; j < number; j++) {
                chodeMatrix[i][j] = scanner.nextInt();
            }
        }

        for (int i = 0; i < number; i++ ){
            for (int j = 0; j < number; j++) {
                if (chodeMatrix[i][j] != chodeMatrix[j][i]) {
                    output = "NO";
                }
            }
        }

        System.out.println(output);

    }
}