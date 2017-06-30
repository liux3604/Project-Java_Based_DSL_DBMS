/**
 * Created by User on 6/28/2017.
 */

package db;
import java.util.regex.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.util.Map;
import java.util.regex.*;
import java.util.HashMap;
import org.junit.Test;
import org.junit.Assert;


public class Table {
    private ArrayList<ArrayList<String>> hiddenList;
    private Map<Integer, String> columNameMap= new HashMap<Integer, String>();
    private int columnNum;


    public Table(){
        hiddenList = new ArrayList<>();
        columnNum=0;
        columNameMap = new HashMap<>();
    }

    public Table(String tableFileName){
        // Create a table from a tableFileName.tbl
        hiddenList = new ArrayList<>();
        columnNum=0;
        columNameMap = new HashMap<>();

        try{
            BufferedReader in = new BufferedReader(new FileReader(tableFileName+".tbl"));
            String titleLine = in.readLine();
            Pattern delimiter = Pattern.compile(",");
            String[] columnNames = delimiter.split(titleLine);

            for ( int i=0;i<columnNames.length;i++) {

                //Load in the column names and create list of columns
                String eachColumn = columnNames[i];
                Pattern p = Pattern.compile("(?:^|\\s*)([^\\s]+?)(?:\\s+)([^\\s]+?)(?:\\s*|$)"); //("\\s*(\\w+[^\\s])\\s*(\\w[^\\s])\\s*")
                Matcher m = p.matcher(eachColumn);
                m.matches();
                String columnName = m.group(1);
                String columnType= m.group(2);
                columNameMap.put(i,columnName);
                hiddenList.add(new ArrayList<String>());
                columnNum++;
            }

            // fill in the table with cell values
            String line;
            while ((line = in.readLine()) != null) {
                Pattern p2 = Pattern.compile("(?:^|\\s)'([^']*?)'(?:\\s|$)"); // https://regex101.com/r/hG5eE1/1
                Matcher m2 = p2.matcher(line);

                int count=0;
                while (m2.find()) {
                    hiddenList.get(count).add(m2.group(count+1));
                    count++;
                }

            }
            in.close();
        }catch(Exception FileNotFoundException){
            System.out.println("The requested file "+tableFileName+".tbl doesn't exist xixi");
        }
    }


    public void printTable(){
        System.out.println("reached here");

        System.out.println("num of columns is: " + columnNum);

    }

    public static void main(String[] args){
        Table newTable = new Table("teams");
        System.out.println(newTable.columnNum);
        newTable.printTable();
    }


}
