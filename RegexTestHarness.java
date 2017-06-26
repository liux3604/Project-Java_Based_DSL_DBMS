/**
 * Created by User on 6/25/2017.
 */
import java.io.Console;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;

public class RegexTestHarness {

    public static void main(String[] args){
        Console console = System.console();
        Scanner sc= new Scanner(System.in);

        while (true) {
            System.out.println("Enter your regex: ");
            Pattern pattern = Pattern.compile(sc.nextLine());

            System.out.println("Enter input string to search: ");
            Matcher matcher = pattern.matcher(sc.nextLine());

            boolean found = false;
            while (matcher.find()) {
                System.out.println("I found the text "+matcher.group() +" starting at index " +matcher.start() + " and ending at index "+ matcher.end());
                found = true;
            }
            if(!found){
                System.out.println("No match found.%n");
            }
        }
    }
}