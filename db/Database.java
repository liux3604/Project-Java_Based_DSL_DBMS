package db;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import static javafx.beans.binding.Bindings.select;

public class Database {
    //The hidden object i am assigning to is an array list of array list with the type String.
    private ArrayList<Table> hiddenTableList;
    private int tableCount;
    private Map<Integer, String> dataBaseMap;

    final String REST  = "\\s*(.*)\\s*",
            COMMA = "\\s*,\\s*",
            AND   = "\\s+and\\s+";

    // Stage 1 syntax, contains the command name.
    final Pattern CREATE_CMD = Pattern.compile("create table " + REST),
            LOAD_CMD   = Pattern.compile("load " + REST),
            STORE_CMD  = Pattern.compile("store " + REST),
            DROP_CMD   = Pattern.compile("drop table " + REST),
            INSERT_CMD = Pattern.compile("insert into " + REST),
            PRINT_CMD  = Pattern.compile("print " + REST),
            SELECT_CMD = Pattern.compile("select " + REST);

    // Stage 2 syntax, contains the clauses of commands.
    final Pattern CREATE_NEW  = Pattern.compile("(\\S+)\\s+\\(\\s*(\\S+\\s+\\S+\\s*" +
            "(?:,\\s*\\S+\\s+\\S+\\s*)*)\\)"),
            SELECT_CLS  = Pattern.compile("([^,]+?(?:,[^,]+?)*)\\s+from\\s+" +
                    "(\\S+\\s*(?:,\\s*\\S+\\s*)*)(?:\\s+where\\s+" +
                    "([\\w\\s+\\-*/'<>=!.]+?(?:\\s+and\\s+" +
                    "[\\w\\s+\\-*/'<>=!.]+?)*))?"),
            CREATE_SEL  = Pattern.compile("(\\S+)\\s+as select\\s+" +
                    SELECT_CLS.pattern()),
            INSERT_CLS  = Pattern.compile("(\\S+)\\s+values\\s+(.+?" +
                    "\\s*(?:,\\s*.+?\\s*)*)");

    public Database() {
        // YOUR CODE HERE
        hiddenTableList = new ArrayList();
        tableCount=0;
        dataBaseMap = new HashMap<Integer, String>();
    }

    public String transact(String query) {
                Matcher m;
                if ((m = CREATE_CMD.matcher(query)).matches()) {
                    createTable(m.group(1));
                } else if ((m = LOAD_CMD.matcher(query)).matches()) {
                    loadTable(m.group(1));
                } else if ((m = STORE_CMD.matcher(query)).matches()) {
                    storeTable(m.group(1));
                } else if ((m = DROP_CMD.matcher(query)).matches()) {
                    dropTable(m.group(1));
                } else if ((m = INSERT_CMD.matcher(query)).matches()) {
                    insertRow(m.group(1));
                } else if ((m = PRINT_CMD.matcher(query)).matches()) {
                    printTable(m.group(1));
                } else if ((m = SELECT_CMD.matcher(query)).matches()) {
                    select(m.group(1));
                } else {
                    System.err.printf("Malformed query: %s\n", query);
                }
            return "";
    }

    private void loadTable(String name){
        System.out.printf("You are trying to load the table named %s\n", name);
        try{
            BufferedReader in = new BufferedReader(new FileReader(name+".tbl"));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            hiddenTableList.add(new Table(name));
            dataBaseMap.put(tableCount,name);
            tableCount++;
        }catch(Exception FileNotFoundException){
            System.out.println("The requested file "+name+".tbl doesn't exist haha");
        }

    }

    private void printTable(String query){
        //first test if the table even exits
        String tableName = query.trim();

        if(dataBaseMap.containsValue(tableName)){

            Table thisTable= new Table();
            for(Map.Entry m: dataBaseMap.entrySet()){
                if(m.getValue()==tableName){
                     thisTable = hiddenTableList.get((int)m.getKey());
                }
            }
            thisTable.printTable();

        }else{
            System.out.println("I cannot print because the table "+ tableName +" doesn't exist in the database.");
        }
    }

    
    public Table getTable(String tableNameInput){
        //return the table matching a table name string
        String tableName = tableNameInput.trim();
        if(dataBaseMap.containsValue(tableName)){

            Table thisTable= new Table();
            for(Map.Entry m: dataBaseMap.entrySet()){
                if(m.getValue()==tableName){
                    thisTable = hiddenTableList.get((int)m.getKey());
                }
            }
            return thisTable;

        }else{
            return null;
        }
    }


    private void createTable(String query){

    }

    private void storeTable(String query){}
    private void dropTable(String query){}
    private void insertRow(String query){}
    private void select(String query){}



}
