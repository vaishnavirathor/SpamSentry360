package org;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // Initialize the classifier with the dataset
            spamClassifier classifier = new spamClassifier("/emails.arff");

            // Interactive input for classification
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter an email to classify (type 'exit' to quit):");

            while (true) {
                System.out.print("Your email: ");
                String emailText = scanner.nextLine();

                if (emailText.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting...");
                    break;
                }

                // Classify the email
                String prediction = classifier.classifyEmail(emailText);
                System.out.println("Prediction: " + prediction);
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
