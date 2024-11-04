package utils;

import parser.XmlJsonParser;

import java.io.*;

public class MyFileHandler {
    public static void writeToTextFile(String fileName, String str) throws FileNotFoundException {
        writeText(fileName, str, false);
    }

    private static void writeText(String fileName, String str, boolean append)
            throws FileNotFoundException {
        PrintWriter writeToFile = null;

        try {
            FileOutputStream fileOutStream = new FileOutputStream(fileName, append);
            writeToFile = new PrintWriter(fileOutStream);
            writeToFile.println(str);
        } finally {
            if (writeToFile != null) {
                writeToFile.close();
            }
        }
    }

    public static void writeToBinaryFile(String fileName, Object obj) throws FileNotFoundException, IOException {
        ObjectOutputStream writeToFile = null;

        try {
            FileOutputStream fileOutStream = new FileOutputStream(fileName);
            writeToFile = new ObjectOutputStream(fileOutStream);

            writeToFile.writeObject(obj);
        } finally {
            if (writeToFile != null) {
                try {
                    writeToFile.close();
                } catch (IOException e) {
                    System.out.println("IO Error closing file " + fileName);
                }
            }
        }
    }

    public static void writeToXMLFile(String fileName, Object obj) {
        try {
            XmlJsonParser xmlJsonParser = new XmlJsonParser();

            xmlJsonParser.toXml(obj, fileName, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object readFromBinaryFile(String fileName) throws IOException, ClassNotFoundException {
        Object obj = null;
        ObjectInputStream readFromFile = null;
        try {
            FileInputStream fileInStream = new FileInputStream(fileName);
            readFromFile = new ObjectInputStream(fileInStream);
            try {
                obj = readFromFile.readObject();
            } catch (EOFException eof) {
                //Done reading
            }
        } finally {
            if (readFromFile != null) {
                try {
                    readFromFile.close();
                } catch (IOException e) {
                    System.out.println("IO Error closing file " + fileName);
                }
            }
        }

        return obj;
    }

}
