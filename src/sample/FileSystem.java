package sample;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.TelefonEntry;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileSystem {

    private static Path path = Paths.get("TelefonEntries.json");


    public static List<TelefonEntry> readEntriesFromFile() {
        List<TelefonEntry> entries = new ArrayList<>();
        try (InputStream is = Files.newInputStream(path)) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(is);
            rootArray(entries, rootArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
    }

    static void rootArray(List<TelefonEntry> entries, JsonNode rootArray) {
        for (JsonNode root : rootArray) {
            String firstName = root.path("firstName").asText();
            String lastName = root.path("lastName").asText();
            String number = root.path("number").asText();
            entries.add(new TelefonEntry(lastName, firstName, number));
        }
    }


    public static void writeFile(List<TelefonEntry> entries) {
        JsonFactory factory = new JsonFactory();
        try (OutputStream outputStream = Files.newOutputStream(path);
             JsonGenerator jsonGenerator = factory.createGenerator(outputStream)) {
            jsonGenerator.writeStartArray();
            for (TelefonEntry entry : entries) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("lastName", entry.getLastName());
                jsonGenerator.writeStringField("firstName", entry.getFirstName());
                jsonGenerator.writeStringField("number", entry.getNumber());
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
            jsonGenerator.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




