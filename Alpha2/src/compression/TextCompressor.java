// TextCompressor.java
package compression;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TextCompressor {
    private File inputFile;
    private File outputFile;
    private File dictionaryFile;

    public TextCompressor(File inputFile, File outputFile, File dictionaryFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.dictionaryFile = dictionaryFile;
    }

    public void compressText() throws IOException {
        Map<String, Integer> dictionary = new HashMap<>();
        String line;
        int abbreviationIndex = 1;

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        BufferedWriter dictionaryWriter = new BufferedWriter(new FileWriter(dictionaryFile));

        while ((line = reader.readLine()) != null) {
            String[] words = line.split(" ");
            StringBuilder newLine = new StringBuilder();

            for (String word : words) {
                if (word.length() > 4 && !dictionary.containsKey(word)) {
                    dictionary.put(word, abbreviationIndex);
                    dictionaryWriter.write(word + " -> " + abbreviationIndex + "\n");
                    abbreviationIndex++;
                }

                if (dictionary.containsKey(word)) {
                    newLine.append(dictionary.get(word)).append(" ");
                } else {
                    newLine.append(word).append(" ");
                }
            }

            writer.write(newLine.toString().replaceAll("\\s+", " ").trim() + "\n");
        }

        // Close the resources
        reader.close();
        writer.close();
        dictionaryWriter.close();
    }




}
