import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please choose an option:");
        System.out.println("1. Count words in a sentence");
        System.out.println("2. Count words in a Microsoft Word file");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Clear the newline character from the input buffer

        switch (choice) {
            case 1:
                System.out.println("Enter a sentence:");
                String sentence = scanner.nextLine();
                int[] counts = countWords(sentence);
                System.out.println("Total number of words in the sentence: " + counts[0]);
                System.out.println("Number of repeated words in the sentence: " + counts[1]);
                System.out.println("Number of unique words in the sentence: " + counts[2]);
                break;
            case 2:
                System.out.println("Enter the file path of the Microsoft Word file:");
                String filePath = scanner.nextLine();
                try {
                    FileInputStream fis = new FileInputStream(filePath);
                    int[] fileCounts = countWordsInFile(fis);
                    System.out.println("Total number of words in the file: " + fileCounts[0]);
                    System.out.println("Number of repeated words in the file: " + fileCounts[1]);
                    System.out.println("Number of unique words in the file: " + fileCounts[2]);
                } catch (FileNotFoundException e) {
                    System.out.println("File not found.");
                }
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }

        scanner.close();
    }

    private static int[] countWords(String sentence) {
        String[] words = sentence.trim().split("\\s+");
        int totalWordCount = words.length;
        int repeatedWordCount = countRepeatedWords(words);
        int uniqueWordCount = totalWordCount - repeatedWordCount;

        return new int[]{totalWordCount, repeatedWordCount, uniqueWordCount};
    }

    private static int countRepeatedWords(String[] words) {
        Map<String, Integer> wordOccurrences = new HashMap<>();
        int repeatedWordCount = 0;

        for (String word : words) {
            int count = wordOccurrences.getOrDefault(word, 0);
            wordOccurrences.put(word, count + 1);

            if (count == 1) {
                repeatedWordCount++;
            }
        }

        return repeatedWordCount;
    }

    private static int[] countWordsInFile(FileInputStream fis) {
        Scanner fileScanner = new Scanner(fis);
        int totalWordCount = 0;
        int repeatedWordCount = 0;
        Map<String, Integer> wordOccurrences = new HashMap<>();

        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] words = line.trim().split("\\s+");
            totalWordCount += words.length;
            repeatedWordCount += countRepeatedWords(words);
            for (String word : words) {
                wordOccurrences.put(word, wordOccurrences.getOrDefault(word, 0) + 1);
            }
        }

        int uniqueWordCount = totalWordCount - repeatedWordCount;
        fileScanner.close();

        return new int[]{totalWordCount, repeatedWordCount, uniqueWordCount};
    }
}