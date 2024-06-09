package ilya.initializer;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

@Slf4j
public class BannerPrinter {
    public static final String PATH_TO_BANNER = "/banner.txt";

    void printBanner() {
        URL banner = BannerPrinter.class.getResource(PATH_TO_BANNER);
        if (banner == null) {
            log.error("Файл с баннером не найден");
            return;
        }

        try (InputStream inputStream = banner.openStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            log.error("Ошибка чтения файла с баннером: {}", e.getMessage());
        }
    }
}
