import java.io.File;
import java.util.ArrayList;

public class ListReader {

    public static String folderpath;

    public static String[] list() {
        // Çalışma dizinini al
        folderpath = System.getProperty("user.dir");

        // Dosyaların bulunduğu alt dizini belirt
        String folderPath = folderpath + File.separator + "src" + File.separator + "jsons";

        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        ArrayList<String> newList = new ArrayList<>();

        // Dosyaları listeleyerek işlemleri gerçekleştir
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && !file.getName().contains("Table")) {
                    String fileName = file.getName();
                    if (fileName.endsWith(".json")) {
                        fileName = fileName.substring(0, fileName.length() - 5);
                    }
                    newList.add(fileName);
                }
            }
        }

        String[] stringArray = newList.toArray(new String[0]);
        return stringArray;
    }

}
