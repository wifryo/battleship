import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;


public class Main {
    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static void main(String[] args) {
        String filePath = "src/dataset_91033.txt";
        try {
            System.out.println(readFileAsString(filePath));
        } catch (IOException e) {
            System.out.println("Cannot read file: " + e.getMessage());
        }
    }
}