package de.codeblocksmc.codelib.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.NoSuchFileException;
import java.util.logging.Logger;

public class JSONFileHelper {
    private final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final Logger log;

    public JSONFileHelper(Logger log) {
        this.log = log;
    }

    /**
     * Loads a JSON file from the specified file path and deserializes it into the specified type.
     *
     * <p>This method is generic and allows deserialization of any type,
     * as long as a corresponding {@link Type} is provided.
     * It uses GSON to convert the JSON content into Java objects.</p>
     *
     * @param <T>   The type of the returned object.
     * @param path  The file path of the JSON file to be loaded.
     * @param type  The type of the object to be deserialized. This can be specified
     *              using a {@link TypeToken}.
     * @return      The deserialized object of type {@code T}, or {@code null}
     *              if an error occurs while reading or deserializing the file.
     *
     * @throws NullPointerException if the specified file path is {@code null}.
     * @throws NoSuchFileException if the specified file does not exist.
     *
     * <p><b>Example:</b></p>
     * <pre>{@code
     * Type mapType = new TypeToken<Map>(){}.getType();
     * Map myMap = loadMap("path/to/file.json", mapType);
     *
     * Type ticketListType = new TypeToken<List<Ticket>>(){}.getType();
     * List<Ticket> tickets = loadMap("path/to/file.json", ticketListType);
     * }</pre>
     *
     * <p>This method is particularly useful when loading data from JSON files
     * into complex or generic data types such as lists, maps, or custom classes.</p>
     */
    @Nullable
    public <T> T loadObject(String path, Type type) throws NoSuchFileException {
        if (!new File(path).exists()) {
            throw new NoSuchFileException(path);
        }
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            StringBuilder jsonStringBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                jsonStringBuilder.append(line);
            }

            return GSON.fromJson(jsonStringBuilder.toString(), type);
        } catch (IOException e) {
            log.warning(e.getMessage());
            return null;
        }
    }


    /**
     * Saves the given object as a JSON file at the specified path.
     *
     * <p>This method serializes the provided object into a JSON file using GSON.
     * The file will be saved with the given file name in the specified directory.</p>
     *
     * @param <T>       The type of the object to be saved.
     * @param object    The object to be serialized and saved.
     * @param directory The directory where the JSON file should be saved.
     *                  If the directory does not exist, it will be created.
     * @param fileName  The name of the JSON file (without the path).
     *
     * @throws NullPointerException if {@code object}, {@code directory}, or {@code fileName} is {@code null}.
     */
    public <T> void saveObject(T object, File directory, String fileName) {
        if (directory.mkdirs()) {
            log.info("Creating data folders...");
        }
        try (FileWriter writer = new FileWriter(new File(directory, fileName + ".json"))) {
            writer.write(GSON.toJson(object));
        } catch (IOException e) {
            log.warning("Failed to save object: " + e.getMessage());
        }
    }

}
