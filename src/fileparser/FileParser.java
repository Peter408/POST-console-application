package fileparser;

import java.io.*;

abstract class FileParser {
    private File file;
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private String fileName;

    public FileParser(String fileName) throws FileNotFoundException {
        setup(fileName);
    }

    private void setup(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        file = new File(fileName);
        fileReader = new FileReader(file);
        bufferedReader = new BufferedReader(fileReader);
    }

    public String parseSegment(int segmentLength) throws IOException {
        char[] returnValue = new char[segmentLength];
        bufferedReader.read(returnValue);
        return String.valueOf(returnValue);
    }

    public String parseLine() throws IOException {
        return bufferedReader.readLine();
    }

    public void nextLine() throws IOException {
        bufferedReader.readLine();
    }

    public void restart() throws FileNotFoundException {
        setup(this.fileName);
    }
}