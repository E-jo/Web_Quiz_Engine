package engine.Services;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'rotateLeft' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER d
     *  2. INTEGER_ARRAY arr
     */

    public static List<Integer> rotateLeft(int d, List<Integer> arr) {
        List<Integer> wrapShift = arr.subList(0, d);
        List<Integer> leftShift = arr.subList(d, arr.size());
        leftShift.addAll(wrapShift);
        return leftShift;
    }

    public static void validate(String password) {
        if ((!Pattern.matches(".*[a-z]+.*", password))
            || (!Pattern.matches(".*[A-Z].*+", password))
            || (!Pattern.matches(".*[0-9].*+", password))) {
                System.out.println("NO");
        } else {
            System.out.println("YES");
        }

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        /*
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int d = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = Result.rotateLeft(d, arr);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining(" "))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();

         */
        profit();
    }

    static void profit() {
        Scanner sc = new Scanner(System.in);
        double m = sc.nextDouble();
        double p = sc.nextDouble();
        double k = sc.nextDouble();
        int years = 0;
        double percent = p / 100;

        while (m < k) {
            m = m + m * percent;
            years++;
        }
        System.out.println(years);
    }

    static void matchMid() {
        Scanner scanner = new Scanner(System.in);
        String part = scanner.nextLine();
        String line = scanner.nextLine();
        String[] words = line.split(" ");
        Pattern midMatch = Pattern.compile(".+" + part + ".+", Pattern.CASE_INSENSITIVE);
        boolean found = false;
        for (String word : words) {
            Matcher matchMid = midMatch.matcher(word);
            found = matchMid.matches();
            if (found) {
                break;
            }
        }
        if (found) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    static void matchBeginOrEnd() {
        Scanner scanner = new Scanner(System.in);
        String part = scanner.nextLine();
        String line = scanner.nextLine();
        String[] words = line.split("[\\s\\W]+");
        Pattern beginMatch = Pattern.compile("^" + part + ".*", Pattern.CASE_INSENSITIVE);
        Pattern endMatch = Pattern.compile(".*" + part + "$", Pattern.CASE_INSENSITIVE);
        boolean found = false;
        for (String word : words) {
            Matcher matchBegin = beginMatch.matcher(word);
            Matcher matchEnd = endMatch.matcher(word);
            found = matchBegin.matches() || matchEnd.matches();
            if (found) {
                break;
            }
        }
        System.out.println(found ? "YES" : "NO");
    }

    static void matchSize() {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        String line = scanner.nextLine();

        String[] words = line.replaceAll("[\\W_]", " ").split("[\\s]+");
        Pattern sizePattern = Pattern.compile(".{" + size + "}");
        boolean found = false;
        for (String word : words) {
            Matcher sizeMatcher = sizePattern.matcher(word);
            found = sizeMatcher.matches();
            if (found) {
                break;
            }
        }
        System.out.println(found ? "YES" : "NO");
    }

    static void bigNumFinder() {
        Scanner scanner = new Scanner(System.in);

        String stringWithNumbers = scanner.nextLine();

        Pattern bigNumPattern = Pattern.compile("[0-9]{10,}");
        Matcher bigNumMatcher = bigNumPattern.matcher(stringWithNumbers);

        while (bigNumMatcher.find()) {
            System.out.println(bigNumMatcher.group() + ": " + (bigNumMatcher.end() - bigNumMatcher.start()));
        }
    }

    static void passwordFinder() {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        Pattern passwordPattern = Pattern.compile("password:*\\s*\\w+", Pattern.CASE_INSENSITIVE);
        Matcher passwordMatcher = passwordPattern.matcher(text);
        boolean found = false;

        while (passwordMatcher.find()) {
            String[] splitString = passwordMatcher.group().split("[:\\s]+");
            System.out.println(splitString[1]);
            found = true;
        }

        if (!found) {
            System.out.println("No passwords found.");
        }

    }

    public static void keyFinder() {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        Pattern keyOnePattern = Pattern.compile("the\\s+key\\s+is\\s+[0-9bcdfghjklmnpqrstvwxyz]+\\s+",
                Pattern.CASE_INSENSITIVE);
        Pattern keyTwoPattern = Pattern.compile("the\\s+key\\s+is\\s+[?!#aeiou]+\\s+",
                Pattern.CASE_INSENSITIVE);

        Matcher keyOneMatcher = keyOnePattern.matcher(text);
        Matcher keyTwoMatcher = keyTwoPattern.matcher(text);

        boolean found = false;

        while (keyOneMatcher.find()) {
            String[] splitString = keyOneMatcher.group().trim().split("\\s+");
            System.out.println(splitString[3]);
            found = true;
        }
        while (keyTwoMatcher.find()) {
            String[] splitString = keyTwoMatcher.group().split("\\s+");
            System.out.println(splitString[3]);
            found = true;
        }
        if (!found) {
            System.out.println("No keys found.");
        }
    }

    public static LocalDateTime merge(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        int year = Math.max(dateTime1.getYear(), dateTime2.getYear());
        int month = Math.max(dateTime1.getMonthValue(), dateTime2.getMonthValue());
        int day = Math.max(dateTime1.getDayOfMonth(), dateTime2.getDayOfMonth());
        int hour = Math.max(dateTime1.getHour(), dateTime2.getHour());
        int minute = Math.max(dateTime1.getMinute(), dateTime2.getMinute());
        int second = Math.max(dateTime1.getSecond(), dateTime2.getSecond());

        return LocalDateTime.of(year, month, day, hour, minute, second);
    }

    public static void mergeRunner() {
        final Scanner scanner = new Scanner(System.in);
        final LocalDateTime firstDateTime = LocalDateTime.parse(scanner.nextLine());
        final LocalDateTime secondDateTime = LocalDateTime.parse(scanner.nextLine());
        System.out.println(merge(firstDateTime, secondDateTime));
    }

    public static void adjustTime() {
        Scanner sc = new Scanner(System.in);
        LocalDateTime input = LocalDateTime.parse(sc.nextLine());
        int n = sc.nextInt();
        input = input.plusMinutes(n);
        System.out.print(input.getYear() + " ");
        System.out.print(input.getDayOfYear() + " ");
        System.out.print(input.getHour() + " ");
        System.out.print(input.getMinute() + " ");
        if (input.getSecond() != 0) {
            System.out.print(input.getSecond() + " ");
        }
    }

    public static void hourDifference() {
        Scanner sc = new Scanner(System.in);
        LocalDateTime ldtOne = LocalDateTime.parse(sc.nextLine());
        LocalDateTime ldtTwo = LocalDateTime.parse(sc.nextLine());
        System.out.println(ChronoUnit.HOURS.between(ldtOne, ldtTwo));
    }
}