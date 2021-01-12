package ua.mainacademy;

import java.util.logging.Logger;

public class AppRunnerV2 {

    private static final Logger LOGGER = Logger.getLogger(AppRunnerV2.class.getName());

    public static void main(String[] args) {
        int num1 = 98765;
        int num2 = 101;

        LOGGER.info("\n" + getMultiResultAsString(num1, num2) + "\n");
    }

    private static String getMultiResultAsString(int num1, int num2) {
        String result = "";
        int multiresult = num1*num2;
        int maxLength = String.valueOf(multiresult).length();
        result += getSymbolsRow(maxLength - String.valueOf(num1).length(), " ");
        result += num1 + "\n";
        result += getSymbolsRow(maxLength - String.valueOf(num2).length(), " ");
        result += num2 + "\n";
        if (num2>=10) {
            if (num1 > num2) {
                result += getSymbolsRow(maxLength - String.valueOf(num1).length(), " ");
                result += getSymbolsRow(String.valueOf(num1).length(), "-") + "\n";
            } else {
                result += getSymbolsRow(maxLength - String.valueOf(num2).length(), " ");
                result += getSymbolsRow(String.valueOf(num2).length(), "-") + "\n";
            }
            result += getMultiRows(num1, num2, maxLength);
        }
        result += getSymbolsRow(maxLength, "-") + "\n";
        result += multiresult + "\n";
        return result;
    }

    private static String getMultiRows(int num1, int num2, int maxLength) { // num2 = 123
        String result = "";
        String num2AsText = String.valueOf(num2);
        int counter = 0;
        for (int i = num2AsText.length()-1; i >=0 ; i--) {
            int multiResult = num1 * Character.getNumericValue(num2AsText.charAt(i));
            result += getSymbolsRow(maxLength - String.valueOf(multiResult).length() - counter, " ");
            result += multiResult + "\n";
            counter++;
        }
        return result;
    }

    private static String getSymbolsRow(int amount, String symbol) {
        String result = "";
        for (int i = 0; i < amount; i++) {
            result += symbol;
        }
        return result;
    }




}
