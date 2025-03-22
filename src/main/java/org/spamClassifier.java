package org;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

import java.io.InputStream;

public class spamClassifier {
    private NaiveBayes classifier;
    private Instances originalDataset;   // Contains the original structure (with a string attribute)
    private Instances filteredDataset;   // The filtered (vectorized) dataset
    private StringToWordVector filter;

    public spamClassifier(String resourcePath) throws Exception {
        // Load the ARFF file from the classpath
        InputStream is = getClass().getResourceAsStream(resourcePath);
        if (is == null) {
            throw new IllegalArgumentException("Resource not found: " + resourcePath);
        }
        DataSource source = new DataSource(is);
        originalDataset = source.getDataSet();
        originalDataset.setClassIndex(originalDataset.numAttributes() - 1);

        // Configure the StringToWordVector filter on the original dataset
        filter = new StringToWordVector();
        filter.setInputFormat(originalDataset);
        // Apply the filter to create the filtered dataset
        filteredDataset = Filter.useFilter(originalDataset, filter);

        // Build the classifier using the filtered dataset
        classifier = new NaiveBayes();
        classifier.buildClassifier(filteredDataset);
    }

    public String classifyEmail(String emailText) throws Exception {
        // Preprocess the email text
        String processedText = TextPreprocessor.cleanText(emailText);

        // Create a new instance using the original dataset's structure
        Instance newInst = new DenseInstance(originalDataset.numAttributes());
        newInst.setDataset(originalDataset);
        newInst.setValue(0, processedText);
        // Set the class attribute as missing since we want to predict it
        newInst.setMissing(originalDataset.classIndex());

        // Create a temporary Instances object containing this single instance
        Instances tempInst = new Instances(originalDataset, 0);
        tempInst.add(newInst);

        // Apply the same StringToWordVector filter to transform the new instance
        Instances filteredInst = Filter.useFilter(tempInst, filter);
        Instance filteredInstance = filteredInst.instance(0);

        // Classify the filtered instance
        double result = classifier.classifyInstance(filteredInstance);
        return filteredDataset.classAttribute().value((int) result);
    }

    public void testClassifier() throws Exception {
        // Optional interactive testing method
        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        System.out.println("Enter email text to classify (type 'exit' to quit):");
        String line;
        while (!(line = reader.readLine()).equalsIgnoreCase("exit")) {
            System.out.println("Prediction: " + classifyEmail(line));
            System.out.println("Enter next email text:");
        }
    }
}
