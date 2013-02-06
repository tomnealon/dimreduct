/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dimreduct;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Takes a CSV file and stores contents as a list of lists of doubles.
 * @author tom
 */
public class CSVReader {
    private int noRows;
    private int noCols;
    private ArrayList<ArrayList> store = new ArrayList<>();
    private ToStringHelper help = new ToStringHelper();
    
    public CSVReader() {
        
    }

    public CSVReader(String fileName) throws IOException {
        System.out.println("Reader created using: " + fileName);
        BufferedReader CSVFile = new BufferedReader(new FileReader(fileName));
        String dataRow = CSVFile.readLine();
        while (dataRow != null) {
            String[] stringArray = dataRow.split(",");
            ArrayList<String> rowList = new ArrayList<>();
            rowList.addAll(Arrays.asList(stringArray));
            store.add(rowList);
            dataRow = CSVFile.readLine();
        }
        CSVFile.close();
        initialise();
    }
    
    public CSVReader transformAsColumnVectors() {
        CSVReader childReader = new CSVReader();
        for(int i = 0; i < this.noCols; i++) {
            childReader.addRow(this.getColumn(i));
        }
        childReader.initialise();
        return childReader;
    }
    
    public ArrayList<Double> getMeanOfColumns() {
        ArrayList<Double> meanOfCols = new ArrayList<>();
        for(int i = 0; i < this.noCols; i++) {
            
        }
        return meanOfCols;
    }
    
    public double getMean(ArrayList<String> stringList) {
        Double mean = 0.0;
        for(String stringValue : stringList) {
            mean += Double.parseDouble(stringValue);
        }
        mean = mean / stringList.size();
        return mean;
    }
    
    public ArrayList<String> getColumn(int col) {
        ArrayList<String> column = new ArrayList<>();
        for(ArrayList<String> dataRow : store) {
            column.add(dataRow.get(col));
        }
        System.out.println(help.toString(column));
        return column;
    }
    
    public String getCell(int row, int col) {
        return String.valueOf(store.get(row).get(col));
    }
    
    public void writeFile(String fileName) throws IOException {
        BufferedWriter outputFile = new BufferedWriter(new FileWriter(fileName));
        FileWriter writer = new FileWriter(fileName);
        String outputLine = "";
        for(ArrayList<String> dataRow : store) {
            for(String value : dataRow) {
                outputLine = outputLine + value + ", "; 
            }
            outputLine = outputLine.trim() + "/n";
            writer.write(outputLine);
        }
    }
    
    private void initialise() {
        this.noRows = store.size();
        this.noCols = store.get(0).size();
    }
    
    public int getNoRows() {
        return noRows;
    }

    public int getNoCols() {
        return noCols;
    }
    
    public ArrayList<String> getRow(int rowNumber) {
        return store.get(rowNumber);
    }
    
    public ArrayList<Double> geRowAsDoubles(int rowNumber) {
        ArrayList<Double> doubleRow = new ArrayList<>();
        for(String eachString : getRow(rowNumber)) {
            doubleRow.add(Double.parseDouble(eachString));
        }
        return doubleRow;
    }
    
    public void addRow(ArrayList<String> newRow) {
        store.add(newRow);
        initialise();
    }
    
    public void printAll() {
        for(ArrayList<String> row : store) {
            System.out.println(help.toString(row));
        }
    }
    
}
