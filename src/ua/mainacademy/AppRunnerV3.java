package ua.mainacademy;

import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

public class AppRunnerV3 {

    private static final Logger LOGGER = Logger.getLogger(AppRunnerV3.class.getName());


    public static void main(String[] args) {
        TreeSet numbers = new TreeSet<>();
        for (int i = 9999; i >= 1000; i--) {
            for (int j = i; j >= 1000; j--) {
                if (isPalindrome(i*j)) {
                    numbers.add(i*j);
                }
            }
        }
        LOGGER.info("Max palindrome is " + numbers.last());
    }

    public static boolean isPalindrome(int number) {
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(number));
        stringBuilder.reverse();
        return stringBuilder.toString().equals(String.valueOf(number));
    }

}
