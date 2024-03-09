import java.io.*;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        LinkedList<Integer> linkedListSource=new LinkedList<>();
        String fileSource="src/Source.txt";

        try (BufferedReader readerSource = new BufferedReader(new FileReader(fileSource))) {
            String lineSource;
            while ((lineSource = readerSource.readLine()) != null) {
                String[] valuesSource = lineSource.split(",");
                for (String value : valuesSource) {
                    linkedListSource.insertToEnd(Integer.parseInt(value.trim()));
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }


        LinkedList<Integer> linkedListSearch=new LinkedList<>();
        String fileSearch="src/Search.txt";

        try (BufferedReader readerSearch = new BufferedReader(new FileReader(fileSearch))) {
            String lineSearch;
            while ((lineSearch = readerSearch.readLine()) != null) {
                String[] valuesSearch = lineSearch.split(",");
                for (String value : valuesSearch) {
                    linkedListSearch.insertToEnd(Integer.parseInt(value.trim()));
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        //----This part is for testing----\\
        linkedListSource.search(198,linkedListSearch);
        linkedListSource.searchAndMoveToFront(300,linkedListSource,linkedListSearch);
        linkedListSource.compareMethods();
    }
}