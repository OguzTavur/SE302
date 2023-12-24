import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileCheck {
    public static boolean fileCheck(String dizin, String dosyaAdi) {
        Path dosyaYolu = Paths.get(dizin, dosyaAdi);
        return Files.exists(dosyaYolu) && Files.isRegularFile(dosyaYolu);
    }


}
