
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
public class JsonFileReader {
public static ArrayList<Object> readJson(String jsonFileName) {
    ObjectMapper objectMapper = new ObjectMapper();
    ArrayList<Object> listA = new ArrayList<>();
        // Dosyaların bulunduğu alt dizini belirt
        String folderpath = System.getProperty("user.dir");
        String folderPath = folderpath + File.separator + "src" + File.separator + "jsons";

    try {
        // JSON dosyasını oku ve JsonNode nesnesine çevir
        JsonNode jsonNode = objectMapper.readTree(new File(folderPath+"\\" + jsonFileName));

        // JsonNode üzerinden verilere eriş
        Iterator<JsonNode> elements = jsonNode.elements();

        while (elements.hasNext()) {
            JsonNode element = elements.next();
            listA.add(element.asText());
        }

        // Diğer verilere erişmek için JsonNode'yi kullanmaya devam edebilirsiniz
    } catch (IOException e) {
        e.printStackTrace();
        return null; // Hata durumunda null döndür
    }
    return listA;
}

public static ArrayList<Object> readJson(String jsonFileName,String folderPath) {
    ObjectMapper objectMapper = new ObjectMapper();
    ArrayList<Object> listA = new ArrayList<>();
        // Dosyaların bulunduğu alt dizini belirt
        String folderpath = System.getProperty("user.dir");

    try {
        // JSON dosyasını oku ve JsonNode nesnesine çevir
        JsonNode jsonNode = objectMapper.readTree(new File(folderPath));

        // JsonNode üzerinden verilere eriş
        Iterator<JsonNode> elements = jsonNode.elements();

        while (elements.hasNext()) {
            JsonNode element = elements.next();
            listA.add(element.asText());
        }

        // Diğer verilere erişmek için JsonNode'yi kullanmaya devam edebilirsiniz
    } catch (IOException e) {
        e.printStackTrace();
        return null; // Hata durumunda null döndür
    }
    return listA;
}

}
