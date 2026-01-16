
import java.io.*;
import java.util.*;

public class n1 {
    public static void main(String[] args) {
        String filePath = "text.txt";

        Map<String, Integer> topWords = new HashMap<> ();

        try (BufferedReader file = new BufferedReader(new FileReader(filePath))) {
            String currentLine;
            while ((currentLine = file.readLine()) != null) {
                String[] line = currentLine.toLowerCase().split("[^a-zа-я]");
                for (String word: line) {
                    if (word.isEmpty() != true) {
                        topWords.put(word, topWords.getOrDefault(word, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }


        List<Map.Entry<String, Integer>> sortTopWords = new ArrayList<>(topWords.entrySet());
        Collections.sort(sortTopWords, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        Iterator<Map.Entry<String, Integer>> iterator = sortTopWords.iterator();
        int i = 0;

        while(i<10) {
            System.out.println(iterator.next());
            i++;
        }

        System.out.println(topWords.get("ophelia"));
    }
}
