package unittest.fileparser;

import fileparser.*;

public class TestFileParser {
    private static final String FILE_TEST_FILE_PATH = "./src/UnitTest/FileParserTest/fileTest.txt";
    private static final int SEGMENT_LENGTH_VALUE = 4;
    private static final String SEGMENT_LENGTH_OUTPUT = "line";
    private static final String PARSE_LINE_OUTPUT = "1";
    private static final String PARSE_LINE_AFTER_NEXT_LINE_OUTPUT = "line3";
    private static final String SEGMENT_FAIL_LOG = "parseSegment() failed";
    private static final String PARSE_LINE_FAIL_LOG = "readLine() failed";
    private static final String NEXT_LINE_FAIL_LOG = "nextLine() failed";
    private static final String SUCCESS_LOG = "Success";
    private static final String FAIL_LOG = "Fail";
    public static void main( String[] args ) {
        try {
            ProductParser testFileParser = new ProductParser(FILE_TEST_FILE_PATH);
            if (!SEGMENT_LENGTH_OUTPUT.equals(testFileParser.parseSegment(SEGMENT_LENGTH_VALUE))) {
                throw new Exception(SEGMENT_FAIL_LOG);
            }
            if (!PARSE_LINE_OUTPUT.equals(testFileParser.parseLine())) {
                throw new Exception(PARSE_LINE_FAIL_LOG);
            }
            testFileParser.nextLine();
            if (!PARSE_LINE_AFTER_NEXT_LINE_OUTPUT.equals(testFileParser.parseLine())) {
                throw new Exception(NEXT_LINE_FAIL_LOG);
            }
            System.out.println(SUCCESS_LOG);
        } catch( Exception exception) {
            System.out.println(exception);
            System.out.println(FAIL_LOG);
        }
    }
}