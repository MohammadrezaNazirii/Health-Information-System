package HIS;

public class Patient {
    private String username;
    private String password;
    int ID;
    int Status=0;//date:picked(entrance date set)   0:unpicked

    String Name;
    String LastName;
    int Age;
    String Sex;
    String Disease;
    String Mode;
    String Medicine;
    String Summery;
    int Bill = -1; // -1:not discharged   other:discharged
    Integer DoctorID;

    Patient(String name, String lastname, int age, String sex, String disease, String mode, int id, String username, String pass){
        this.Name = name;
        this.LastName = lastname;
        Age = age;
        Sex = sex;
        Disease = disease;
        Mode = mode;
        ID = id;
        this.username = username;
        password = pass;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
