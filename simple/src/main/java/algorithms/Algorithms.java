package algorithms;

import java.util.*;

public class Algorithms {

    public static void main(String[] args) {

        System.out.println("--- BEGIN");

        //for (int i = 1; i <= 100; i++) System.out.println(String.valueOf(fibR(i)));
        //for (long i = 1; i <= 10000000; i++) System.out.println(i + ": " + String.valueOf(isPrime(i)));
        //System.out.println(revS("diversification"));
        // for (int i = 1; i <= 10; i++) System.out.println(i + ": " + String.valueOf(facR(i)));
        //System.out.println(arms(100_000_000));
        //System.out.println(revI(1234567));
        System.out.println(perms("abcd"));

        // a -> list(a)
        // ab
        // abc -> c+ab, a+c+b, ab+c
        // abcd

        System.out.println("--- END");
    }


    private static List<String> perms(String input) {
        if (input.length() == 1)
            return Collections.singletonList(input);
        List<String> perms = new ArrayList<>();
        char ch = input.charAt(0);
        String remainder = input.substring(1, input.length());
        List<String> remPerms = perms(remainder);
        for (String remPerm : remPerms) {
            remainder = remPerm;
            for (int i = 0; i <= remPerm.length(); i++) {
                String start = (i == 0 ? "" : remainder.substring(0, i));
                String end = remainder.substring(i, remainder.length());
                String perm = start + ch + end;
                perms.add(perm);
            }
        }
        return perms;
    }

    public static int revI(int val) {
        // 1234 -> 4321
        int res = 0;
        int rem = 0;
        while (val > 0) {
            rem = val % 10;
            res = res * 10 + rem;
            val = val / 10;
        }
        return res;
    }

    public static List<Integer> arms(int max) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i <= max; i++) {
            if (isArms(i)) {
                result.add(i);
            }
        }
        return result;
    }

    public static boolean isArms(int val) {
        if (val <= 9) {
            return true;
        }
        int digits = String.valueOf(val).length();
        int sum = 0;
        int number = val;
        while (number > 0) {
            int last = number % 10;
            sum += Math.pow(last, digits);
            number = number / 10;
        }
        return sum == val;
    }

    public static Map<Integer, Long> fibCache = new HashMap<>();

    public static long fibR(int n) {
        if (fibCache.containsKey(n)) return fibCache.get(n);
        if (n <= 1) return 1;
        long fib = fibR(n - 1) + fibR(n - 2);
        fibCache.put(n, fib);
        return fib;
    }

    public static long fibI(int n) {
        long fib1 = 1;
        long fib2 = 1;
        if (n <= 1) return fib1;
        long fib = 0;
        for (int i = 2; i <= n; i++) {
            fib = fib1 + fib2;
            fib1 = fib2;
            fib2 = fib;
        }
        return fib;
    }

    public static boolean isPrime(long n) {
        if (n <= 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i < n; i += 2)
            if (n % i == 0)
                return false;
        return true;
    }

    public static String revS(String input) {
        if (input.length() == 1) return input;
        return input.substring(input.length() - 1) + revS(input.substring(0, input.length() - 1));
    }

    public static long facR(int n) {
        if (n == 1) return 1;
        long fac = facR(n - 1) * n;
        return fac;
    }
}
