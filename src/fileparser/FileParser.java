package fileparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

abstract class FileParser {
    private File file;
    private FileReader fileReader;
    private BufferedReader bufferedReader;

    public FileParser(String fileName) throws FileNotFoundException {
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
}