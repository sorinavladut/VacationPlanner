package services;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvService {

    private static CsvService instance;

    private CsvService() {}

    public static CsvService getInstance() {
        if(instance == null) {
            instance = new CsvService();
        }
        return instance;
    }

    public void write(String path, String line) {
        try {
            FileWriter writer = new FileWriter(path, true);
            writer.write(line + "\n");
            writer.close();
        } catch(IOException e) {
            System.out.println("Write error");
        }
    }

    public void overwrite(String path, List<String> lines) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, false))) {
            for (String line : lines) {
                writer.println(line);
            }
        } catch (IOException e) {
            System.out.println("Overwrite error");
        }
    }

    public List<String> read(String path) {
        List<String> lines = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;

            while((line = reader.readLine()) != null) {
                lines.add(line);
            }

            reader.close();

        } catch(IOException e) {
            System.out.println("Read error");
        }

        return lines;
    }
}
