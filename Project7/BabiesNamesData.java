import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class BabiesNamesData {
    List<String> boyName = new ArrayList<String>();
    List<String> girlName = new ArrayList<String>();

    public BabiesNamesData(String year){
        File filename = new File ("babynamesranking" + year + ".txt");
        try {
            //Load the file records into the list boyName and girlName respectively
            String line = "";
            String []token = new String[5];
            RandomAccessFile data = new RandomAccessFile(filename, "r");
            while((line = data.readLine()) != null){
                token = line.split("\t");
                boyName.add(token[1]);
                girlName.add(token[3]);
            }
            data.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<String> FindCommonNames(){
        List<String> names = new ArrayList<String>();
        for(String name:boyName){
            if(girlName.contains(name))
                names.add(name);
        }
        return names;
    }
}
