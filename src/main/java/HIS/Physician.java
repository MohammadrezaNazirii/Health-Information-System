package HIS;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Physician {
    private String username;
    private String password;
    Integer ID;
    ArrayList<Patient> ListOfPatients = new ArrayList<>();
    ArrayList<String> Messages = new ArrayList<>();

    String Name;
    String LastName;
    String Field;
    String Record;
    String Sex;

    ArrayList<Patient> PatientsThatCouldBePicked = new ArrayList<>();

    Physician(String name, String lastname, String field, String record, String sex, int id, String username, String pass){
        Name = name;
        LastName = lastname;
        Field = field;
        Record = record;
        Sex = sex;
        ID = id;
        this.username = username;
        password = pass;
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

    String PickPatient(){
        PatientsThatCouldBePicked.clear();
        String result = "";
        int counter=0;
        for (Patient patient : UserManagement.PatientList) {
            if (patient.Status == 0) {
                try {
                    File config = new File("C:\\Users\\ASUS\\IdeaProjects\\HW_GUI\\src\\main\\java\\HIS\\config.txt");
                    Scanner myReader = new Scanner(config);
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        if (data.contains(Field)) {
                            if (data.contains(patient.Disease)) {
                                counter++;
                                PatientsThatCouldBePicked.add(patient);
//                                System.out.println(counter + ". " + patient.Name + " " + patient.LastName + "   Problem: " + patient.Disease);
                                result += counter + ". " + patient.Name + " " + patient.LastName + '\n' + "Problem: " + patient.Disease + '\n';
                            }
                        }
                    }
                    myReader.close();
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    int PickPatient(int index, String Date){ //1:successful   0:failed
        if(index <= (PatientsThatCouldBePicked.size()-1)) {
            ListOfPatients.add(PatientsThatCouldBePicked.get(index));


//            //tarikhe emroz ro migire ke baraye test moshkel iijad mikone.
//            LocalDate D = LocalDate.now();
//            String Date = D.toString();
//            char[] date = new char[6];
//            Date.getChars(2, 4, date, 0);
//            Date.getChars(5, 7, date, 2);
//            Date.getChars(8, 10, date, 4);


            char[] date = new char[6];
            ///// input Date in format 2023-01-07
            Date.getChars(2, 4, date, 0);
            Date.getChars(5, 7, date, 2);
            Date.getChars(8, 10, date, 4);


            PatientsThatCouldBePicked.get(index).Status = Integer.parseInt(String.valueOf(date));
            //set entrance status
            PatientsThatCouldBePicked.get(index).DoctorID = ID;
            return 1;
        }
        else
            return 0;
    }

    String ListPatients(){
        String Temp="";
        int counter = 1;
        for(Patient patient:ListOfPatients){
            Temp += counter+".Name: "+patient.Name+" "+patient.LastName+'\n'+"      Problem: "+patient.Disease+"    Date of admission: "+patient.Status+'\n';
            counter++;
        }
        return Temp;
    }

    String ViewPatientInfo(int id){
        for(Patient patient:ListOfPatients){
            if(patient.ID == id){
                return "Name: "+patient.Name+" "+patient.LastName+'\n'+"Problem: "+patient.Disease+"    Date of admission: "+patient.Status+'\n';
            }
        }
        return "Not Found:/";
    }

    String ViewPatientInfo(String lastname){
        for(Patient patient:ListOfPatients){
            if(Objects.equals(patient.LastName, lastname)){
                return "Name: "+patient.Name+" "+patient.LastName+'\n'+"Problem: "+patient.Disease+"    Date of admission: "+patient.Status+'\n';
            }
        }
        return "Not Found:/";
    }

    Patient AnswerMedicine(){
        int Patient_ID = Integer.parseInt(Messages.get(0).substring(Messages.get(0).indexOf("Patient-ID")+11, Messages.get(0).indexOf("Patient-ID")+14));
        for(Patient p:ListOfPatients){
            if(p.ID == Patient_ID)
                return p;
        }
        return null;
    }

    void AnswerMedicine(Patient p, String medicine){
        ////// medicine input
        p.Medicine = medicine;
        int Nurse_ID = Integer.parseInt(Messages.get(0).substring(Messages.get(0).indexOf("Nurse-ID")+9, Messages.get(0).indexOf("Nurse-ID")+12));
        for(Nurse n:UserManagement.NurseList){
            if(n.ID == Nurse_ID){
                n.Messages.add("Patient Prescription set.Patient-ID:"+p.ID+"\nPhysician-ID:"+ID);
                break;
            }
        }
        Messages.remove(0);
    }

    int Calculate(int d){
        LocalDate D = LocalDate.now();
        String Date = D.toString();
        char[] date = new char[6];
        Date.getChars(2, 4, date, 0);
        Date.getChars(5, 7, date, 2);
        Date.getChars(8, 10, date, 4);

        Integer result = Integer.parseInt(String.valueOf(date)) - d;
        String res = result.toString();
        int len = res.length();
        int finall=0;
        switch (len){
            case 1:
            case 2:
                finall = result;
                break;
            case 3:
                finall = Integer.parseInt(String.valueOf(res.charAt(0)))*30 + Integer.parseInt(res.substring(1));
                break;
            case 4:
                finall = Integer.parseInt(res.substring(0, 2))*30 + Integer.parseInt(res.substring(2));
                break;
            case 5:
                finall = Integer.parseInt(String.valueOf(res.charAt(0)))*365 + Integer.parseInt(res.substring(1, 3))*30 + Integer.parseInt(res.substring(3));
                break;
            case 6:
                finall = Integer.parseInt(res.substring(0, 2))*365 + Integer.parseInt(res.substring(2, 4))*30 + Integer.parseInt(res.substring(4));
                break;
        }
        return finall;
    }

    int DischargePatient(int id, String summery){
        ////// summery input
        for (Patient p:ListOfPatients){
            if(p.ID == id){
                p.Summery = summery;
                int nDay = Calculate(p.Status);
                switch (p.Mode){
                    case "VIP":
                        p.Bill = nDay*120;
                        break;
                    case "Normal":
                        p.Bill = nDay*70;
                        break;
                    case "Insurance":
                        p.Bill = nDay*35;
                        break;
                }
                ListOfPatients.remove(p);
                return 1;
            }
        }
        return 0;
    }
}
