package sdv.java.m1.tp.service;

import sdv.java.m1.tp.enums.CriticiteAction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

public class Traceur {
    private static final String FILE_PATH = "traces.txt";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public void tracer(CriticiteAction criticite, String description) {

        String timestamp = ZonedDateTime.now().format(DATE_FORMATTER);
        String logEntry = String.format("%s %s %s\n", criticite, timestamp, description);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(logEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> lire() {
        try {
            return Files.readAllLines(Paths.get(FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}


