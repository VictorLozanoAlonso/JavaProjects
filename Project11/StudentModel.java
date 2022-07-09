public class StudentModel {
    private int id = 0;
    private String name = "";
    private String email = "";
    private String address = "";
    private int enrYear = 0;
    private String program = "";

    public void setStudent (int id, String name, String email, String address, int enrYear, String program){
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.enrYear = enrYear;
        this.program = program;
    }
    public int getId(){
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getAddress(){
        return address;
    }
    public int getEnrYear(){
        return enrYear;
    }
    public String getProgram(){
        return program;
    }
}
