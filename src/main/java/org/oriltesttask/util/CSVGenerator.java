package org.oriltesttask.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CSVGenerator {

    public static <T> ByteArrayInputStream generate(List<List<T>> rows) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();;
        CSVPrinter csvPrinter;
        try {
            csvPrinter = new CSVPrinter(new PrintWriter(out), CSVFormat.DEFAULT);
            for (List<T> data : rows) {
                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
