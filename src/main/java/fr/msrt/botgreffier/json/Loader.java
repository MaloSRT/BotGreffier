package fr.msrt.botgreffier.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class Loader {

    public String load(String name) {

        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);

        try {
            InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(stream));
            BufferedReader buff = new BufferedReader(reader);
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = buff.readLine()) != null) {
                content.append(line);
            }
            buff.close();
            return content.toString();
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
            return null;
        }

    }

}