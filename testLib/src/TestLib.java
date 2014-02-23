import java.io.IOException;
import java.io.StringWriter;

public class TestLib {
    static public byte[] getBytes(String... input) {
        return getString(input).getBytes();
    }

    static public String getString(String... input) {
        try {
            try (StringWriter stringOutForInput = new StringWriter()) {
                for(String ui: input) {
                    stringOutForInput.write(String.format("%s%n", ui));
                }
                return stringOutForInput.getBuffer().toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
