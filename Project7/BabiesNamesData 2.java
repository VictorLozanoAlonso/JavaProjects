import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
            Collections.sort(boyName);
            Collections.sort(girlName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<String> getBoyNames(){
        return boyName;
    }
    public List<String> getGirlNames(){
        return girlName;
    }
    public void removeDuplicates(){
        boyName = boyName.stream().distinct().collect(Collectors.toList());
        girlName = girlName.stream().distinct().collect(Collectors.toList());
        System.out.println("\nRemove Duplicates done.");
    }
    public void printNames(List<String> names){
        String nameList = "";
        for(String name:names)
            nameList += name + ", ";
        nameList = nameList.substring(0, (nameList.length()-2));
        System.out.print(nameList);
    }
}
