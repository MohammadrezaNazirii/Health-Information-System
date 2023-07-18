package HIS;

import java.util.ArrayList;
import java.util.Objects;

public class Nurse {
    String username;
    String password;
    int ID;
    ArrayList<String> Messages = new ArrayList<>();
    String Name;
    String LastName;
    String Record;
    String Sex;

    Nurse(String name, String lastname, String record, String sex, int id, String username, String pass){
        Name = name;
        LastName = lastname;
        Record = record;
        Sex = sex;
        ID = id;
        this.username = username;
        password = pass;
    }

    String NoDoctorAssignedPatients(){
        String Temp = "";
        int counter = 1;
        for(Patient patient:UserManagement.PatientList){
            if(patient.Status == 0){
                Temp += counter+".Name: "+patient.Name+" "+patient.LastName+"    Problem: "+patient.Disease+'\n';
                counter++;
            }
        }
        return Temp;
    }

    String DischargePatients(){
        String Temp = "";
        int counter = 1;
        for(Patient patient:UserManagement.PatientList){
            if(patient.Bill != -1){
                Temp += counter+".Name: "+patient.Name+" "+patient.LastName+"    Bill: "+patient.Bill+" $"+'\n';
                counter++;
            }
        }
        return Temp;
    }
    String CheckedInPatients(int firstDate, int secondDate){
        String Temp = "";
        int counter = 1;
        for(Patient patient:UserManagement.PatientList){
            if(firstDate <= patient.Status && patient.Status <= secondDate){
                Temp += counter+".Name: "+patient.Name+" "+patient.LastName+"    Entrance Date: "+patient.Status+'\n';
                counter++;
            }
        }
        return Temp;
    }

    String GetPrescription(){
        String Temp = "";
        int counter = 1;
        for(Patient p:UserManagement.PatientList){
            if(p.Status != 0 && p.Bill == -1){
                Temp += counter+".Name: "+p.Name+"   LastName: "+p.LastName+"   Entrance Date: "+p.Status+'\n'+"Problem: "+p.Disease+'\n';
                counter++;
            }
        }
        return Temp;
    }
    int GetPrescription(int index){
        ArrayList<Patient> Temp = new ArrayList<>();
        for(Patient p:UserManagement.PatientList){
            if(p.Status != 0 && p.Bill == -1){
                Temp.add(p);
            }
        }
        for(Physician p:UserManagement.PhysicianList){
            if(Objects.equals(Temp.get(index - 1).DoctorID, p.ID)){
                p.Messages.add("Please write medicine for Patient-ID:"+Temp.get(index - 1).ID+". Nurse-ID:"+ID);
                return 1;
            }
        }
        return 0;
    }

    String GetMessages(){
        String Temp = "";
        int counter = 1;
        for(String s:Messages){
            Temp += counter+"."+s+'\n';
            counter++;
        }
        return Temp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
