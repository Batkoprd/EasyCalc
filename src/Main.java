import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        String userInput = scanner.nextLine();

        FileWriter fileWriter = new FileWriter("output.txt");

        try {
            Double res = Calc(userInput);
            fileWriter.write(String.valueOf(res));
            fileWriter.flush();
        } catch (NumberFormatException e) {
            System.out.println("Error! Not number");
        } catch (IllegalArgumentException e) {
            System.out.println("Operation Error!");
        } catch (ArithmeticException e) {
            System.out.println("Error! Division by zero");
        }
    }


    public static double Calc(String input) {
        String numRegex = "-?(\\d+)\\.?(\\d+)?";
        String operationRegex = "[+\\-*/]";

        String[] arr = input.split(" ");
        Double num1 = Double.parseDouble(arr[0]);
        Double num2 = Double.parseDouble(arr[2]);
        String operation = arr[1];

        if (!arr[0].matches(numRegex) || !arr[2].matches(numRegex)) {
            throw new NumberFormatException();
        }
        if (!operation.matches(operationRegex)) {
            throw new IllegalArgumentException();
        }

        double result = 0.0;

        switch (operation) {
            case "+" -> result = num1 + num2;
            case "-" -> result = num1 - num2;
            case "*" -> result = num1 * num2;
            case "/" -> {
                if (num2 == 0) {
                    throw new ArithmeticException();
                }
                result = num1 / num2;
            }
        }

        if (result == -0.0) return 0;
        return result;
    }

}
