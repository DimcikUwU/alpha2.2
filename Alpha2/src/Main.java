

import compression.TextDecompressor;

import java.io.*;
import java.util.logging.*;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        if (args.length < 4) {
            LOGGER.severe("Please provide the operation (compress or decompress), input file, output file, and dictionary file as arguments.");
            return;
        }

        String operation = args[0];
        String inputFile = args[1];
        String outputFile = args[2];
        String dictionaryFile = args[3];

        try {
            FileHandler fileHandler = new FileHandler("log/TextProcessor.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.INFO);

            File inputFileFile = new File(inputFile);
            File outputFileFile = new File(outputFile);
            File dictionaryFileFile = new File(dictionaryFile);

            validateFile(inputFile);
            if ("compress".equals(operation)) {
                LOGGER.info("Starting compression...");
                compression.TextCompressor compressor = new compression.TextCompressor(inputFileFile, outputFileFile, dictionaryFileFile);
                compressor.compressText();
                LOGGER.info("Compression completed.");
            } else if ("decompress".equals(operation)) {
                validateFile(dictionaryFile);
                LOGGER.info("Starting decompression...");
                TextDecompressor decompressor = new TextDecompressor(inputFileFile, outputFileFile, dictionaryFileFile);
                decompressor.decompressText();
                LOGGER.info("Decompression completed.");
            } else {
                LOGGER.severe("Invalid operation: " + operation);
            }
        } catch (IOException e) {
            LOGGER.severe("An error occurred: " + e.getMessage());
        }
    }

    private static void validateFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists() || !file.canRead()) {
            throw new IOException("Cannot read the file: " + fileName);
        }
        if (!fileName.endsWith(".txt")) {
            throw new IOException("File is not a .txt file: " + fileName);
        }
    }
}



//// Main.java
//package ui;
//
//import compression.TextCompressor;
//import compression.TextDecompressor;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.io.File;
//import java.io.IOException;
//
//public class Main extends JFrame {
//    private File inputFile;
//    private File outputFile;
//    private File dictionaryFile;
//    private boolean compressSelected = true;
//
//    public Main() {
//        this.setSize(300, 230);
//        this.setTitle("Text Compressor/Decompressor");
//        this.setResizable(false);
//        components();
//        this.setLocationRelativeTo(null);
//        this.setVisible(true);
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//    }
//
//    private void components() {
//        JPanel jp = new JPanel();
//        jp.setLayout(null);
//
//        // Rest of your code...
//
//        JRadioButton compressButton = new JRadioButton("Compress", true);
//        compressButton.setBounds(70, 70, 150, 20);
//        compressButton.addActionListener((ActionEvent e) -> compressSelected = true);
//
//        JRadioButton decompressButton = new JRadioButton("Decompress", false);
//        decompressButton.setBounds(70, 90, 150, 20);
//        decompressButton.addActionListener((ActionEvent e) -> compressSelected = false);
//
//        ButtonGroup group = new ButtonGroup();
//        group.add(compressButton);
//        group.add(decompressButton);
//
//        JButton startButton = new JButton("Start");
//        startButton.setBounds(70, 130, 150, 40);
//        startButton.setFont(new Font("console", 12, 12));
//        startButton.addActionListener((ActionEvent e) -> {
//            if (inputFile != null && outputFile != null && dictionaryFile != null) {
//                try {
//                    if (compressSelected) {
//                        TextCompressor compressor = new TextCompressor(inputFile, outputFile, dictionaryFile);
//                        compressor.compressText();
//                    } else {
//                        TextDecompressor decompressor = new TextDecompressor(inputFile, outputFile, dictionaryFile);
//                        decompressor.decompressText();
//                    }
//                    JOptionPane.showMessageDialog(null, "Operation completed successfully!");
//                } catch (IOException ex) {
//                    JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
//                }
//            } else {
//                JOptionPane.showMessageDialog(null, "Please select valid files");
//            }
//        });
//
//        jp.add(compressButton);
//        jp.add(decompressButton);
//        jp.add(startButton);
//        this.getContentPane().add(jp);
//    }
//
//    public static void main(String[] args) {
//        Main mi = new Main();
//    }
//}



//// Main.java
//package ui;
//
//import compression.TextCompressor;
//import compression.TextDecompressor;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.io.File;
//import java.io.IOException;
//
//public class Main extends JFrame {
//    private File inputFile;
//    private File outputFile;
//    private File dictionaryFile;
//    private boolean compressSelected = true;
//
//    public Main() {
//        this.setSize(300, 300); // Increased size to fit new buttons
//        this.setTitle("Text Compressor/Decompressor");
//        this.setResizable(false);
//        components();
//        this.setLocationRelativeTo(null);
//        this.setVisible(true);
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//    }
//
//    private void components() {
//        JPanel jp = new JPanel();
//        jp.setLayout(null);
//
//        // Rest of your code...
//
//        JButton inputFileButton = new JButton("Choose Input File");
//        inputFileButton.setBounds(70, 110, 150, 20);
//        inputFileButton.addActionListener((ActionEvent e) -> {
//            JFileChooser fileChooser = new JFileChooser();
//            int returnValue = fileChooser.showOpenDialog(null);
//            if (returnValue == JFileChooser.APPROVE_OPTION) {
//                inputFile = fileChooser.getSelectedFile();
//            }
//        });
//
//        JButton outputFileButton = new JButton("Choose Output File");
//        outputFileButton.setBounds(70, 140, 150, 20);
//        outputFileButton.addActionListener((ActionEvent e) -> {
//            JFileChooser fileChooser = new JFileChooser();
//            int returnValue = fileChooser.showOpenDialog(null);
//            if (returnValue == JFileChooser.APPROVE_OPTION) {
//                outputFile = fileChooser.getSelectedFile();
//            }
//        });
//
//        JButton dictionaryFileButton = new JButton("Choose Dictionary File");
//        dictionaryFileButton.setBounds(70, 170, 150, 20);
//        dictionaryFileButton.addActionListener((ActionEvent e) -> {
//            JFileChooser fileChooser = new JFileChooser();
//            int returnValue = fileChooser.showOpenDialog(null);
//            if (returnValue == JFileChooser.APPROVE_OPTION) {
//                dictionaryFile = fileChooser.getSelectedFile();
//            }
//        });
//
//        jp.add(inputFileButton);
//        jp.add(outputFileButton);
//        jp.add(dictionaryFileButton);
//        this.getContentPane().add(jp);
//    }
//
//    public static void main(String[] args) {
//        Main mi = new Main();
//    }
//}