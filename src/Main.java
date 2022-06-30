import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));

        FileOutputStream fileOutputStream = new FileOutputStream("output.txt");
        PrintStream printStream = new PrintStream(fileOutputStream);


        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            try {
                double res = Calc(input);
                printStream.println(input + " = " + res);
            } catch(NumberFormatException e){
                printStream.println(input + " = " + "Error! Not number");
            } catch(IllegalArgumentException e){
                printStream.println(input + " = " +"Operation Error!");
            } catch(ArithmeticException e){
                printStream.println(input + " = " +"Error! Division by zero");
            }
        }
        scanner.close();
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
