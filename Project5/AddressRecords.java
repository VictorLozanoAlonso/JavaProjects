import java.io.IOException;
import java.io.RandomAccessFile;

public class AddressRecords {
    String [] records;
    int nRecords = 0, position = 0;

    AddressRecords(String filename){
        try {
            int  i = 0;
            RandomAccessFile in = new RandomAccessFile(filename, "r");
            while((in.readLine()) != null){
                i++;
            }
            nRecords = i;
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read(String filename) throws IOException {
        try {
            if(nRecords >= 5) {
                int i = 0;
                this.records = new String[nRecords];
                RandomAccessFile in = new RandomAccessFile(filename, "r");
                while (i < nRecords) {
                    records[i] = in.readLine();
                    i++;
                }
                in.close();
            } else throw new IOException("Records less than 5");
        } catch (IOException e) {
            throw e;
        }
    }

    public void updateRecord(String [] record, int index){
        String aux = "";
        for(int j = 0; j < 5; j++){
            aux +=record[j];
            if(j < 4)
                aux += ",";
        }
        records[index] = aux;
    }

    public void write(String [] record, String filename, boolean adding) {
        try {
            int i = 0;
            String aux = "";
            RandomAccessFile out = new RandomAccessFile(filename, "rw");
            while (i < records.length) {
                out.writeBytes(records[i]);
                out.writeBytes("\n");
                i++;
            }
            if(adding) {
                for (int j = 0; j < 5; j++) {
                    aux += record[j];
                    if (j < 4)
                        aux += ",";
                }
                out.writeBytes(aux);
                nRecords++;
            }
            out.close();
            this.records = new String[nRecords];
            read("data.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String [] getRecord(int index){
        String [] record = new String[5];
        record = records[index].split(",");
        return record;
    }
    public int getNumRecords(){
        return nRecords;
    }
    public int getPosition(){
        return position;
    }
    public void setPosition(int index){
        position = index;
    }
    public void incPosition(){
        position++;
    }
    public void decPosition(){
        position--;
    }
}
