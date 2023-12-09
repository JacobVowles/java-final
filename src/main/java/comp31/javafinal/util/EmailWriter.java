package comp31.javafinal.util;
import java.io.IOException;
import java.io.FileWriter;
public class EmailWriter {
    public void writeEmail(String filepath, String content) {
    try(FileWriter writer = new FileWriter(filepath))
    {
        writer.write(content);
        System.out.println("Successfully wrote to the file.");
    }catch(IOException e)
    {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }
}
}
