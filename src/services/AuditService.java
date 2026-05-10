package services;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class AuditService {

    private static AuditService instance;

    private AuditService() {}

    public static AuditService getInstance() {
        if(instance == null) {
            instance = new AuditService();
        }
        return instance;
    }

    public void log(String action) {
        try {
            FileWriter writer = new FileWriter("data/audit.csv", true);
            writer.write(action + "," + LocalDateTime.now() + "\n");
            writer.close();
        } catch(IOException e) {
            System.out.println("Audit error");
        }
    }
}