import java.io.*;
public class FileCounter {
    public static void main(String[] args) {
        String filePath = "sample.txt"; // replace with your file path
        int lines = 0, words = 0, characters = 0, nonWhitespaceCharacters = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines++;
                characters += line.length();
                nonWhitespaceCharacters += line.replaceAll("\\s", "").length(); // Count only non-whitespace characters
                words += line.trim().isEmpty() ? 0 : line.split("\\s+").length;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        System.out.println("Lines: " + lines);
        System.out.println("Words: " + words);
        System.out.println("Characters (including spaces): " + characters);
        System.out.println("Non-whitespace Characters: " + nonWhitespaceCharacters);
    }
}
