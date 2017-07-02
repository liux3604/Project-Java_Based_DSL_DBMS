/**
 * Created by User on 6/30/2017. This class is for testing purposes only
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import db.Database;

public class Main_test {
    private static final String EXIT   = "exit";
    private static final String PROMPT = "> ";

    public static void main(String[] args) throws IOException {
        Database db = new Database();
        System.out.print(PROMPT);
    }
}
