/*************************************************
* FileReader
* Responsible for base methods for reading from file
**********************************************/
public class FileReader{
    private File file;
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    
    
    //Method to initialize the process of reading from file
    public initializeReader(String fileName){
        this.file = new File(fileName);
        this.fileReader = new fileReader(file);
        this.bufferedReader = new BufferedReader(fileReader);
    }
}
