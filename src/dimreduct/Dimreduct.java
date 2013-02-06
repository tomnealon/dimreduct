/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dimreduct;

import java.io.IOException;

/**
 *
 * @author tom
 */
public class Dimreduct {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       CSVReader reader = new CSVReader("./data_sources/seeds_dataset.csv");
       System.out.println(reader.getCell(3, 3));
       reader.printAll();
       //reader.getColumn(1);
       
       CSVReader tranformedReader = reader.transformAsColumnVectors();
       
       tranformedReader.printAll();
    }
}
