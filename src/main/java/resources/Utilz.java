package resources;

import com.opencsv.CSVWriter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Utilz {

    public Utilz() {
    }

    public static Object[] getKeys(String pathToCsv) throws IOException {

        File csvFile = new File(pathToCsv);
        String[] data;
        List<String> myArr = new ArrayList<>();

        if (csvFile.isFile()) {
            BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
            String row;
            while ((row = csvReader.readLine()) != null) {
                data = row.split(" ");
                myArr.add(data[1]);
            }
            csvReader.close();
        }

        return myArr.toArray();

    }

    public static void writeToFileCsv(List<String> myList, String fileName) throws IOException {
        Base bs = new Base();
        File file = new File(bs.getpathToResources() + fileName);

        try {
            // create FileWriter object with file as parameter
            FileWriter outputFile = new FileWriter(file);

            // create CSVWriter object fileWriter object as parameter
            CSVWriter writer = new CSVWriter(outputFile, CSVWriter.NO_QUOTE_CHARACTER, ' ',
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

//            // adding header to csv
//            String[] header = { "license key" };
//            writer.writeNext(header);

            // add data to csv
            for (String license: myList) {
                String[] entry = {license};
                writer.writeNext(entry);
            }

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void setExplicitWait(WebDriver driver, int seconds, WebElement element) {
        WebDriverWait w = new WebDriverWait(driver, seconds);
        w.until(ExpectedConditions.visibilityOf(element));
    }

}
