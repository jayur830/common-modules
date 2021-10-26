package;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

@Slf4j
public class ResourceUtils {
    private static final GsonJsonParser parser = new GsonJsonParser();

    public static File readFile(final String resourcePath) throws IOException {
        return getResource(resourcePath).getFile();
    }

    public static BufferedImage readImage(final String resourcePath) throws IOException {
        return ImageIO.read(readFile(resourcePath));
    }

    public static String readText(final String resourcePath) throws IOException {
        StringBuilder s = new StringBuilder();
        InputStream inputStream = getResource(resourcePath).getInputStream();
        int len;
        do {
            byte[] buf = new byte[256];
            len = inputStream.read(buf);
            s.append(new String(buf, 0, len));
        } while (len == 256);
        inputStream.close();
        return s.toString();
    }

    public static Object readJSON(final String resourcePath) throws IOException {
        String text = readText(resourcePath);
        return text.charAt(0) == '{' ? parser.parseMap(text) : parser.parseList(text);
    }

    public static byte[] readBinaryImage(final String resourcePath) throws IOException {
        BufferedImage img = readImage(resourcePath);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(img, "png", outputStream);
        outputStream.flush();
        return outputStream.toByteArray();
    }

    private static Resource getResource(final String resourcePath) {
        return new ClassPathResource(resourcePath);
    }

    public static void write(String path, final String filename, final String text) {
        try {
            if (path.charAt(0) == '/') path = path.substring(1);
            if (path.charAt(path.length() - 1) == '/') path = path.substring(0, path.length() - 1);
            FileOutputStream outputStream = new FileOutputStream(new File(String.format("src/main/resources/%s/%s", path, filename)));
            outputStream.write(text.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}