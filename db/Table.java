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
    private ArrayList<ArrayList<String>> hiddenColumnList;
    private Map<Integer, String> columNameMap;
    private int columnNum;
    private int rowNum;


    public Table(){
        hiddenColumnList = new ArrayList<>();
        columnNum=0;
        columNameMap = new HashMap<Integer, String>();
        rowNum=0;
    }

    public Table(String tableFileName){
        // Create a table from a tableFileName.tbl
        hiddenColumnList = new ArrayList<>();
        columnNum=0;
        columNameMap = new HashMap<Integer, String>();
        rowNum=0;

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
                hiddenColumnList.add(new ArrayList<String>());
                columnNum++;
            }

            // fill in the table with cell values
            String line;
            while ((line = in.readLine()) != null) {
                rowNum++;
                Pattern p2 = Pattern.compile("(?:^|\\s)'([^']*?)'(?:\\s|$)"); // https://regex101.com/r/hG5eE1/1
                Matcher m2 = p2.matcher(line);

                int count=0;
                while (m2.find()) { //where bug is
                    hiddenColumnList.get(count).add(m2.group(count+1));
                    count++;
                }

            }
            in.close();
        }catch(Exception FileNotFoundException){
            System.out.println("The requested file "+tableFileName+".tbl doesn't exist xixi");
        }
    }


    void printTable(){
        System.out.println("reached here");

        //Print the title of the table first
        System.out.println("num of columns is: " + columnNum);
        for(int i=0;i<columnNum;i++){
            System.out.print("'"+columNameMap.get(i)+"'");
            if(i<=columnNum-2){
                System.out.print(",");
            }
        }
        System.out.println();

        //print out all the cell values in the table
        for(int j=0;j<rowNum;j++){
            for(int k=0;k<columnNum;k++){
                System.out.print("'"+hiddenColumnList.get(k).get(j)+"'");
                if(k<=columnNum-2){
                    System.out.print(",");
                }else{
                    System.out.println();
                }
            }
        }


    }

    public String getCellValue(int row, int column){
        // return the cell value in the specified row and column position in the table
        return hiddenColumnList.get(column).get(row);
    }

    public static void main(String[] args){
        Table newTable = new Table("teams");
        System.out.println(newTable.columnNum);
        System.out.println(newTable.getCellValue(0,0));
    }


}
