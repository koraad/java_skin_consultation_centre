import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class EncodeDecode {


    // encoding the image

    public String encodeImage(String filePath) throws IOException {

        FileInputStream imageStream = new FileInputStream(filePath);

        byte[] imageData = imageStream.readAllBytes();


        return Base64.getEncoder().encodeToString(imageData);
    }

    // encoding the image

    public byte[] decodeImage(String imageString) throws IOException {

        return Base64.getDecoder().decode(imageString);
    }
}
