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

    public static void main(String[] args) {
        double answer=0;
        long sum=0;
        for(int r=0;r<=8;r++){
            for(int g=0;g<=8;g++){
                for(int b=0;b<=8;b++){
                    sum = sum + com(8,r)*com(8,g)*com(8,b);
                }
            }
        }

        answer= sum/512;

        System.out.println(answer);

    }

    public static long calc(long n) {
        if (n <= 1)
            return 1;
        else
            return n * calc(n - 1);
    }

    public static long com(long n, long a){
        // calculates C(n,a)
        long answer;
        answer = calc(n)/(calc(n-a))/(calc(a));
        return answer;
    }


}
