import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        FileInputStream fileInputStream = new FileInputStream(file);
        load(fileInputStream);
        fileInputStream.close();
        FileOutputStream outputStream = new FileOutputStream(file);
        save(outputStream);
        outputStream.flush();

    }

    public void save(OutputStream outputStream) throws Exception {
        Properties propSave = new Properties();
        propSave.putAll(properties);
        propSave.store(outputStream, "");
    }

    public void load(InputStream inputStream) throws Exception {
        Properties prop = new Properties();
        prop.load(inputStream);
        prop.forEach((k, v) -> properties.put((String)k, (String)v));
    }

    public static void main(String[] args) throws Exception {
    }
}
