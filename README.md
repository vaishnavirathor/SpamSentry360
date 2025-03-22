Project SpamSentry360 is a Spam Classification System built using Java and Weka (Machine Learning Library). It is designed to predict whether an email is spam or ham (not spam).

🔹 Technologies Used:
Java → Core programming language.

Weka → Machine Learning library for text classification.

Naive Bayes Classifier → A simple but effective probabilistic model for spam detection.

StringToWordVector → Converts text into numerical features for machine learning.

Maven → Builds and manages dependencies.

🔹 Project Workflow:
Loads an ARFF dataset containing email texts labeled as spam or ham.

Applies text preprocessing using StringToWordVector.

Trains a Naive Bayes classifier on the processed dataset.

Accepts user input (email text) via the console.

Predicts whether the input email is spam or ham.

