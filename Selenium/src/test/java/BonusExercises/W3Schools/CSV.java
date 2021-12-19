package BonusExercises.W3Schools;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import com.sun.org.glassfish.gmbal.Description;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSV {

    @Description("Read CSV from file path")
    public static List<List<String>> readCSV (String filePath) {
        List <List <String>> records = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader (new FileReader(filePath))) {
            String [] values;
            while ((values = csvReader.readNext ()) != null)  {
                records.add (Arrays.asList(values));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace ();
        }
        return records;
    }

    @Description("Write data from file to CSV")
    public static void writeCSV(String filePath, List<List<String>> dataList) throws IOException {
        File file = new File(filePath);
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);

            for (List<String> data: dataList) {
                writer.writeNext((data.stream().toArray(String[] ::new)));
            }
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
