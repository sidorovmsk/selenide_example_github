package github.utils;

import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigReader {
    private InputStream inputStream;
    private Map<String, String> propertyMap = new HashMap<>();

    public ConfigReader(String configName) {
        readConfigToMap2(configName);
    }

    public String getParameter(String name) {
        if (!propertyMap.containsKey(name)) {
            Assertions.fail("Ключ -" + name + "- отсутствует в списке параметров");
        }
        return propertyMap.get(name);
    }

    private void readConfigToMap2(String fileName) {
        Properties prop = new Properties();
        try {
            inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(fileName);
            prop.load(inputStream);
            for (String name : prop.stringPropertyNames()) {
                propertyMap.put(name, new String(prop.getProperty(name).getBytes("ISO8859-1")));
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка чтения конфига: " + e.toString());
        }
    }
}
