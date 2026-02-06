package SearchEngine;

import java.io.File;
import java.util.Scanner;

public class RankedRetrieval {

    private String datasetFile;

    public RankedRetrieval(String datasetFile) {
        this.datasetFile = datasetFile;
    }

    public void retrieveRankedResults(String query) {
        try {
            File file = new File(datasetFile);
            Scanner scanner = new Scanner(file);


            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            String[] queryTokens = query.toLowerCase().split("\\s+");

            System.out.println("Ranked Retrieval Results:");
            System.out.printf("%-10s %-10s\n", "DocID", "Score");
            System.out.println("----------------------------");

            int[][] docScores = new int[50][2];
            int docCount = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.isEmpty() || !line.contains(",")) {
                    continue;
                }

                String[] parts = line.split(",", 2);

                if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                    continue;
                }

                int docID;
                try {
                    docID = Integer.parseInt(parts[0].trim());
                } catch (NumberFormatException e) {
                    continue;
                }

                String content = parts[1].toLowerCase();

                int score = 0;
                for (String token : queryTokens) {
                    score += calculateFrequency(token, content);
                }

                if (score > 0) {
                    docScores[docCount][0] = docID;
                    docScores[docCount][1] = score;
                    docCount++;
                }
            }

            sortResults(docScores, docCount);

            for (int i = 0; i < docCount; i++) {
                System.out.printf("%-10d %-10d\n", docScores[i][0], docScores[i][1]);
            }

            scanner.close();

        } catch (Exception e) {
            System.out.println("Error reading dataset: " + e.getMessage());
        }
    }


    private int calculateFrequency(String term, String content) {
        int frequency = 0;
        String[] tokens = content.replaceAll("[^a-zA-Z0-9\\s]", "").split("\\s+");
        for (String token : tokens) {
            if (token.equals(term)) {
                frequency++;
            }
        }
        return frequency;
    }


    private void sortResults(int[][] results, int count) {
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (results[j][1] < results[j + 1][1] ||
                        (results[j][1] == results[j + 1][1] && results[j][0] > results[j + 1][0])) {
                    int[] temp = results[j];
                    results[j] = results[j + 1];
                    results[j + 1] = temp;
                }
            }
        }
    }
}
