import java.io.*;
import java.util.ArrayList;

/**
* Created by hojin.ghim on 3/1/14.
*/
public class StringArrayInputOutput implements Closeable {
    ByteArrayInputStream byteArrayInputStream;
    ByteArrayOutputStream byteArrayOutputStream;
    PrintStream printStream;

    public StringArrayInputOutput(String... input) {
        byteArrayInputStream = new ByteArrayInputStream(Lib.getLinedString(input).getBytes());
        byteArrayOutputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(byteArrayOutputStream);
    }

    public InputStream getInputStream() {
        return byteArrayInputStream;
    }

    public PrintStream getPrintStream() {
        return printStream;
    }

    public String[] toStringArray() throws IOException {
        ArrayList<String> results = new ArrayList<>();
        String line;
        try (BufferedReader readerForOutput = new BufferedReader(new InputStreamReader(
                new ByteArrayInputStream(byteArrayOutputStream.toByteArray())))) {
            line = readerForOutput.readLine();
            while (null != line) {
                results.add(line);
                line = readerForOutput.readLine();
            }
        }
        return results.toArray(new String[results.size()]);
    }

    @Override
    public void close() throws IOException {
        byteArrayInputStream.close();
        byteArrayOutputStream.close();
        printStream.close();
    }

}
