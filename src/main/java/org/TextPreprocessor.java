package org;

import java.util.Set;

public class TextPreprocessor {
    private static final Set<String> STOPWORDS = Set.of("the", "is", "a", "this", "that", "to", "and");

    public static String cleanText(String text) {
        // Remove non-alphabet characters and convert to lowercase
        text = text.replaceAll("[^a-zA-Z ]", "").toLowerCase();
        StringBuilder filteredText = new StringBuilder();
        for (String word : text.split("\\s+")) {
            if (!STOPWORDS.contains(word)) {
                filteredText.append(word).append(" ");
            }
        }
        return filteredText.toString().trim();
    }
}
