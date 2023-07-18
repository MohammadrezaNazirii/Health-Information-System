package HIS;

import java.util.ArrayList;
import java.util.Objects;

public class UserManagement {
    static int id = 100;
    static ArrayList<Integer> DeletedIDs = new ArrayList<>();
    static ArrayList<Physician> PhysicianList = new ArrayList<>();
    static ArrayList<Nurse> NurseList = new ArrayList<>();
    static ArrayList<Patient> PatientList = new ArrayList<>();
    private static final Admin admin = Admin.getInstance();

    static String ListAllUsers(){
        String temp = "";
        temp += "Physicians:\n";
        if(PhysicianList.size() == 0)
            temp += "404!Not found.\n";
        for (int i=0;i<PhysicianList.size();i++){
            temp += ((i+1)+". "+PhysicianList.get(i).Name+" "+PhysicianList.get(i).LastName+'\n');
        }
        temp += "Nurses:\n";
        if(NurseList.size() == 0)
            temp += "404!Not found.\n";
        for (int i=0;i<NurseList.size();i++){
            temp += ((i+1)+". "+NurseList.get(i).Name+" "+NurseList.get(i).LastName+'\n');
        }
        temp += "Patients:\n";
        if(PatientList.size() == 0)
            temp += "404!Not found.\n";
        for (int i=0;i<PatientList.size();i++){
            temp += ((i+1)+". "+PatientList.get(i).Name+" "+PatientList.get(i).LastName+'\n');
        }
        return temp;
    }
    static String SearchUser(String value){
        String result = "";
//        ArrayList<String> result = new ArrayList<>();
        String val = value.toLowerCase();
        for(Patient p:PatientList) {
            if (p.LastName.toLowerCase().contains(val)) {
                result += p.LastName;
                result += '\n';
//                result.add(p.LastName);
            }
        }
        for(Nurse p:NurseList) {
            if (p.LastName.toLowerCase().contains(val)) {
                result += p.LastName;
                result += '\n';
//                result.add(p.LastName);
            }
        }
        for(Physician p:PhysicianList) {
            if (p.LastName.toLowerCase().contains(val)) {
                result += p.LastName;
                result += '\n';
//                result.add(p.LastName);
            }
        }
        return result;
    }
    static void AddUser(String name, String lastname, String field, String record, String sex, String username, String pass){ //Physician
        String Username = 'D'+username;
        if(Objects.equals(name, ""))
            return;
        PhysicianList.add(PhysicianList.size(), new Physician(name, lastname, field, record, sex, id, Username, pass));
        id++;
    }
    static void AddUser(String name, String lastname, String record, String sex, String username, String pass){ //Nurse
        String Username = 'N'+username;
        if(Objects.equals(name, ""))
            return;
        NurseList.add(NurseList.size(), new Nurse(name, lastname, record, sex, id, Username, pass));
        id++;
    }
    static void AddUser(String name, String lastname, int age, String sex, String disease, String mode, String username, String pass){ //Patient
        String Username = 'P'+username;
        if(Objects.equals(name, ""))
            return;
        PatientList.add(PatientList.size(), new Patient(name, lastname, age, sex, disease, mode, id, Username, pass));
        id++;
    }
    static int DeleteUser(int Id){ //    1:successful, 0:failed
        for(int i=0;i<DeletedIDs.size();i++){
            if(Id == DeletedIDs.get(i)){
                return 0;
            }
        }
        for(int i=0;i<PhysicianList.size();i++){
            if (PhysicianList.get(i).ID == Id){
                PhysicianList.remove(i);
                DeletedIDs.add(Id);
                return 1;
            }
        }
        for(int i=0;i<NurseList.size();i++){
            if(NurseList.get(i).ID == Id){
                NurseList.remove(i);
                DeletedIDs.add(Id);
                return 1;
            }
        }
        for(int i=0;i<PatientList.size();i++){
            if(PatientList.get(i).ID == Id){
                PatientList.remove(i);
                DeletedIDs.add(Id);
                return 1;
            }
        }
        return 0;
    }

    static int ChangePassword(String curPass, String newPass){ //1:pass avaz shod , 0:invalid pass , -1:unequality cur and new
        String src = "!@#$%&*";
        int temp = -1;
        for(int i=0;i<src.length();i++){
            if(newPass.indexOf(src.charAt(i)) != -1){
                temp = newPass.indexOf(src.charAt(i));
                break;
            }
        }
        if(temp != -1){
            if (Objects.equals(admin.getPassword(), curPass)) {
                admin.setPassword(newPass);
            }
            else {
                return -1;
            }
        }
        else{
            return 0;
        }
        return 1;
    }
    static int ChangePassword(String curPass, String newPass, Physician p){ //1:pass avaz shod , 0:invalid pass , -1:unequality cur and new
        String src = "!@#$%&*";
        int temp = -1;
        for(int i=0;i<src.length();i++){
            if(newPass.indexOf(src.charAt(i)) != -1){
                temp = newPass.indexOf(src.charAt(i));
                break;
            }
        }
        if(temp != -1){
            if (Objects.equals(p.getPassword(), curPass)) {
                p.setPassword(newPass);
            }
            else {
                return -1;
            }
        }
        else{
            return 0;
        }
        return 1;
    }
    static int ChangePassword(String curPass, String newPass, Nurse n){ //1:pass avaz shod , 0:invalid pass , -1:unequality cur and new
        String src = "!@#$%&*";
        int temp = -1;
        for(int i=0;i<src.length();i++){
            if(newPass.indexOf(src.charAt(i)) != -1){
                temp = newPass.indexOf(src.charAt(i));
                break;
            }
        }
        if(temp != -1){
            if (Objects.equals(n.getPassword(), curPass)) {
                n.setPassword(newPass);
            }
            else {
                return -1;
            }
        }
        else{
            return 0;
        }
        return 1;
    }
    static int ChangePassword(String curPass, String newPass, Patient p){ //1:pass avaz shod , 0:invalid pass , -1:unequality cur and new
        String src = "!@#$%&*";
        int temp = -1;
        for(int i=0;i<src.length();i++){
            if(newPass.indexOf(src.charAt(i)) != -1){
                temp = newPass.indexOf(src.charAt(i));
                break;
            }
        }
        if(temp != -1){
            if (Objects.equals(p.getPassword(), curPass)) {
                p.setPassword(newPass);
            }
            else {
                return -1;
            }
        }
        else{
            return 0;
        }
        return 1;
    }
}
