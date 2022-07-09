import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class BabiesNameData {
    List<String> boyName = new ArrayList<String>();
    List<String> girlName = new ArrayList<String>();

    public BabiesNameData(String year){
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
    //Function to look for the rank of one name. Return the rank position, otherwise return -1
    public int getRank(String name, String gender){
        int rank = 0;
        boolean found = false;
        String aux = "";
        if ((gender.toLowerCase()).compareTo("m") == 0){
            while(rank < boyName.size() && !found){
                aux = boyName.get(rank).toString();
                if((name.toLowerCase()).compareTo(aux.toLowerCase()) == 0)
                    found = true;
                rank++;
            }
        } else{
            while(rank < girlName.size() && !found){
                aux = girlName.get(rank).toString();
                if((name.toLowerCase()).compareTo(aux.toLowerCase()) == 0)
                    found = true;
                rank++;
            }
        }
        if(found)
            return rank;
        return -1;
    }
}
