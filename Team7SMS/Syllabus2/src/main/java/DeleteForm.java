import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class DeleteForm {
    public static void deleteForm(String a) {
        // Silmek istediğiniz dosyanın yolu ve adı
        String folderpath = System.getProperty("user.dir");
        String folderPath = folderpath + File.separator + "src" + File.separator + "jsons"+File.separator;
        String dosyaYolu = folderPath+a+".json"; // Örnek dosya yolu

        Path dosyaYoluPath = Paths.get(dosyaYolu);

        try {
            Files.delete(dosyaYoluPath);
        } catch (IOException e) {
        }
    }
}
