package HIS;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Objects;

public class Scenes {
    static int LoginCounter = 0;

    static void LoginScene(Stage stage){
        Button login = new Button("Login");
        Button exit = new Button("Exit");
        Label username = new Label("Username:");
        Label pass = new Label("Password:");
        TextField usernameTextField = new TextField();
        PasswordField passwordField = new PasswordField();

        HBox usernameBox = new HBox();
        usernameBox.getChildren().addAll(username, usernameTextField);
        usernameBox.setAlignment(Pos.CENTER);
        usernameBox.setSpacing(15);
        HBox passwordBox = new HBox();
        passwordBox.getChildren().addAll(pass, passwordField);
        passwordBox.setAlignment(Pos.CENTER);
        passwordBox.setSpacing(17);

        usernameBox.setCursor(Cursor.TEXT);
        passwordBox.setCursor(Cursor.TEXT);
        login.setCursor(Cursor.HAND);
        exit.setCursor(Cursor.HAND);

        usernameTextField.setTooltip(new Tooltip("Enter Username"));
        passwordField.setTooltip(new Tooltip("Enter Password"));
        login.setTooltip(new Tooltip("Click"));

        Label help = new Label("\nHelp:");
        Label helpPhysician = new Label("Physician's username starts with D.");
        Label helpNurse = new Label("Nurse's username starts with N.");
        Label helpPatient = new Label("Patient's username starts with P.");

        VBox root = new VBox();
        root.getChildren().addAll(usernameBox, passwordBox, login, exit, help, helpPhysician, helpNurse, helpPatient);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        Scene LoginScene = new Scene(root, 400, 700);
        stage.setScene(LoginScene);

        Label error = new Label("");
        root.getChildren().add(error);
        exit.setOnAction(actionEvent -> {
            System.exit(0);
        });

        login.setOnAction(action ->{
            if(LoginCounter == 3){
                LoginCounter = 0;
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if ((Objects.equals(usernameTextField.getText(), Admin.getInstance().getUsername())) && (Objects.equals(passwordField.getText(), Admin.getInstance().getPassword()))){
                usernameTextField.setText("");
                passwordField.setText("");
                LoginCounter=0;
                AdminScene(stage);
            }else if(Objects.equals(usernameTextField.getText(), Admin.getInstance().getUsername())){
                LoginCounter++;
            }
            String Temp = usernameTextField.getText();
            Temp = Temp + ' ';

            switch (Temp.charAt(0)){
                case 'P':
                    for(Patient p:UserManagement.PatientList){
                        if(Objects.equals(usernameTextField.getText(), p.getUsername()) && Objects.equals(passwordField.getText(), p.getPassword())){
                            usernameTextField.setText("");
                            passwordField.setText("");
                            LoginCounter=0;
                            PatientScene(stage, p);
                            break;
                        }else if(Objects.equals(usernameTextField.getText(), p.getUsername())){
                            LoginCounter++;
                        }
                    }
                case 'N':
                    for(Nurse p:UserManagement.NurseList){
                        if(Objects.equals(usernameTextField.getText(), p.getUsername()) && Objects.equals(passwordField.getText(), p.getPassword())){
                            usernameTextField.setText("");
                            passwordField.setText("");
                            LoginCounter=0;
                            NurseScene(stage, p);
                            break;
                        }else if(Objects.equals(usernameTextField.getText(), p.getUsername())){
                            LoginCounter++;
                        }
                    }
                case 'D':
                    for(Physician p:UserManagement.PhysicianList){
                        if(Objects.equals(usernameTextField.getText(), p.getUsername()) && Objects.equals(passwordField.getText(), p.getPassword())){
                            usernameTextField.setText("");
                            passwordField.setText("");
                            LoginCounter=0;
                            PhysicianScene(stage, p);
                            break;
                        }else if(Objects.equals(usernameTextField.getText(), p.getUsername())){
                            LoginCounter++;
                        }
                    }
                default:
                    error.setText("Username and Password not match!");
                    break;
            }
        });
    }

    static void AdminScene(Stage stage){
        Button ListAllUsersButton = new Button("List All Users");
        Button SearchUserButton = new Button("Search User");
        Button AddUserButton = new Button("Add User");
        Button DeleteUserButton = new Button("Delete User");
        Button ChangePasswordButton = new Button("Change Password");
        Button Exit = new Button("Exit");



        ListAllUsersButton.setCursor(Cursor.HAND);
        SearchUserButton.setCursor(Cursor.HAND);
        AddUserButton.setCursor(Cursor.HAND);
        DeleteUserButton.setCursor(Cursor.HAND);
        ChangePasswordButton.setCursor(Cursor.HAND);
        Exit.setCursor(Cursor.HAND);

        ListAllUsersButton.setTooltip(new Tooltip("List All Users"));
        SearchUserButton.setTooltip(new Tooltip("Search User"));
        AddUserButton.setTooltip(new Tooltip("Add User"));
        DeleteUserButton.setTooltip(new Tooltip("Delete User"));
        ChangePasswordButton.setTooltip(new Tooltip("Change Password"));
        Exit.setTooltip(new Tooltip("Exit"));

        VBox root = new VBox();
        root.getChildren().addAll(ListAllUsersButton, SearchUserButton, AddUserButton, DeleteUserButton, ChangePasswordButton, Exit);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        Scene AdminScene = new Scene(root, 400, 700);
        stage.setScene(AdminScene);

        Label list = new Label("");
        root.getChildren().add(list);
        Exit.setOnAction(action ->{
            LoginScene(stage);
        });
        ListAllUsersButton.setOnAction(action ->{
            String temp = UserManagement.ListAllUsers();
            list.setText(temp);
        });
        AddUserButton.setOnAction(action ->{
            AddScene(stage);
        });
        DeleteUserButton.setOnAction(actionEvent -> {
            DeleteScene(stage);
        });
        SearchUserButton.setOnAction(actionEvent -> {
            SearchScene(stage);
        });
        ChangePasswordButton.setOnAction(actionEvent -> {
            ChangePasswordScene(stage);
        });
    }

    static void ChangePasswordScene(Stage stage){
        Label OldPass = new Label("Current Password:        ");
        TextField Oldpass = new TextField();
        HBox OldpassHBox = new HBox(OldPass, Oldpass);
        OldpassHBox.setAlignment(Pos.CENTER);
        OldpassHBox.setSpacing(15);

        Label NewPass = new Label("New Password:             ");
        TextField Newpass = new TextField();
        HBox NewpassHBox = new HBox(NewPass, Newpass);
        NewpassHBox.setAlignment(Pos.CENTER);
        NewpassHBox.setSpacing(15);

        Label Repeat = new Label("Repeat New Password:");
        TextField repeat = new TextField();
        HBox RepeatHBox = new HBox(Repeat, repeat);
        RepeatHBox.setAlignment(Pos.CENTER);
        RepeatHBox.setSpacing(15);

        Button Submit = new Button("Submit");
        Button Back = new Button("Back");
        Submit.setCursor(Cursor.HAND);
        Back.setCursor(Cursor.HAND);

        Label result = new Label("");

        VBox rootChangePass = new VBox();
        rootChangePass.getChildren().addAll(OldpassHBox, NewpassHBox, RepeatHBox, Submit, Back, result);

        rootChangePass.setAlignment(Pos.CENTER);
        rootChangePass.setSpacing(10);

        Scene ChangePassScene = new Scene(rootChangePass, 400, 700);
        stage.setScene(ChangePassScene);

        Submit.setOnAction(actionEvent -> {
            if(Objects.equals(Newpass.getText(), repeat.getText())) {
                int res = UserManagement.ChangePassword(Oldpass.getText(), Newpass.getText());
                switch (res){
                    case -1:
                        result.setText("Current Password is wrong.");
                        break;
                    case 0:
                        result.setText("Invalid Password for New Password.");
                        break;
                    case 1:
                        result.setText("Password changed successfully.");
                        break;
                }
            }else {
                result.setText("New Password and Repeat New Password are not same:/");
            }
        });
        Back.setOnAction(actionEvent -> {
            AdminScene(stage);
        });
    }

    static void SearchScene(Stage stage){
        Label Search = new Label("Search:    ");
        TextField search = new TextField();
        HBox SearchHBox = new HBox(Search, search);
        SearchHBox.setAlignment(Pos.CENTER);
        SearchHBox.setSpacing(15);

        Button Submit = new Button("Submit");
        Button Back = new Button("Back");
        Submit.setCursor(Cursor.HAND);
        Back.setCursor(Cursor.HAND);

        Label result = new Label("");

        VBox rootSearch = new VBox();
        rootSearch.getChildren().addAll(SearchHBox, Submit, Back, result);

        rootSearch.setAlignment(Pos.CENTER);
        rootSearch.setSpacing(10);

        Scene SearchScene = new Scene(rootSearch, 400, 700);
        stage.setScene(SearchScene);

        Submit.setOnAction(actionEvent -> {
            result.setText(UserManagement.SearchUser(search.getText()));
        });
        Back.setOnAction(actionEvent -> {
            AdminScene(stage);
        });
    }

    static void DeleteScene(Stage stage){
        Label ID = new Label("ID:    ");
        TextField id = new TextField();
        HBox IDHBox = new HBox(ID, id);
        IDHBox.setAlignment(Pos.CENTER);
        IDHBox.setSpacing(15);

        Button Submit = new Button("Submit");
        Button Back = new Button("Back");
        Submit.setCursor(Cursor.HAND);
        Back.setCursor(Cursor.HAND);

        Label error = new Label("");

        VBox rootDelete = new VBox();
        rootDelete.getChildren().addAll(IDHBox, Submit, Back, error);

        rootDelete.setAlignment(Pos.CENTER);
        rootDelete.setSpacing(10);

        Scene DeleteScene = new Scene(rootDelete, 400, 700);
        stage.setScene(DeleteScene);

        Submit.setOnAction(actionEvent -> {
            int res = UserManagement.DeleteUser(Integer.parseInt(id.getText()));

            if(res == 1) {
                error.setText("Successful");
            }
            else {
                error.setText("Failed");
            }
        });
        Back.setOnAction(actionEvent -> {
            AdminScene(stage);
        });
    }

    static void AddScene(Stage stage){
        RadioButton PhysicianButton = new RadioButton("Physician");
        RadioButton NurseButton = new RadioButton("Nurse");
        RadioButton PatientButton = new RadioButton("Patient");


        Label Name = new Label("Name:      ");
        TextField name = new TextField();
        HBox NameHBox = new HBox(Name, name);
        NameHBox.setAlignment(Pos.CENTER);
        NameHBox.setSpacing(15);

        Label Lastname = new Label("Lastname:");
        TextField lastname = new TextField();
        HBox LastnameHBox = new HBox(Lastname, lastname);
        LastnameHBox.setAlignment(Pos.CENTER);
        LastnameHBox.setSpacing(15);

        Label Field = new Label("Field:        ");
        TextField field = new TextField();
        HBox FieldHBox = new HBox(Field, field);
        FieldHBox.setAlignment(Pos.CENTER);
        FieldHBox.setSpacing(15);

        Label Record = new Label("Record:    ");
        TextField record = new TextField();
        HBox RecordHBox = new HBox(Record, record);
        RecordHBox.setAlignment(Pos.CENTER);
        RecordHBox.setSpacing(15);

        Label Sex = new Label("Sex:          ");
        TextField sex = new TextField();
        HBox SexHBox = new HBox(Sex, sex);
        SexHBox.setAlignment(Pos.CENTER);
        SexHBox.setSpacing(15);

        Label Username = new Label("Username:");
        TextField username = new TextField();
        HBox UsernameHBox = new HBox(Username, username);
        UsernameHBox.setAlignment(Pos.CENTER);
        UsernameHBox.setSpacing(15);

        Label Password = new Label("Password: ");
        TextField password = new TextField();
        HBox PasswordHBox = new HBox(Password, password);
        PasswordHBox.setAlignment(Pos.CENTER);
        PasswordHBox.setSpacing(15);

        Label Age = new Label("Age:         ");
        TextField age = new TextField();
        HBox AgeHBox = new HBox(Age, age);
        AgeHBox.setAlignment(Pos.CENTER);
        AgeHBox.setSpacing(15);

        Label Disease = new Label("Disease:   ");
        TextField disease = new TextField();
        HBox DiseaseHBox = new HBox(Disease, disease);
        DiseaseHBox.setAlignment(Pos.CENTER);
        DiseaseHBox.setSpacing(15);

        Label Mode = new Label("Mode:      ");
        TextField mode = new TextField();
        HBox ModeHBox = new HBox(Mode, mode);
        ModeHBox.setAlignment(Pos.CENTER);
        ModeHBox.setSpacing(15);

        Button Submit = new Button("Submit");
        Button Back = new Button("Back");
        Submit.setCursor(Cursor.HAND);
        Back.setCursor(Cursor.HAND);


        VBox rootAdd = new VBox();
        rootAdd.getChildren().addAll(PhysicianButton, NurseButton, PatientButton);

        rootAdd.setAlignment(Pos.CENTER);
        rootAdd.setSpacing(10);

        Scene AddScene = new Scene(rootAdd, 400, 700);
        stage.setScene(AddScene);

        Label error = new Label("");
        rootAdd.getChildren().add(error);



        PhysicianButton.setOnAction(action ->{
            rootAdd.getChildren().removeAll(PhysicianButton, NurseButton, PatientButton);
            rootAdd.getChildren().addAll(NameHBox, LastnameHBox, FieldHBox, RecordHBox, SexHBox, UsernameHBox, PasswordHBox, Submit, Back);
        });
        NurseButton.setOnAction(action ->{
            rootAdd.getChildren().removeAll(PhysicianButton, NurseButton, PatientButton);
            rootAdd.getChildren().addAll(NameHBox, LastnameHBox, RecordHBox, SexHBox, UsernameHBox, PasswordHBox, Submit, Back);
        });
        PatientButton.setOnAction(action ->{
            rootAdd.getChildren().removeAll(PhysicianButton, NurseButton, PatientButton);
            rootAdd.getChildren().addAll(NameHBox, LastnameHBox, AgeHBox, RecordHBox, SexHBox, DiseaseHBox, ModeHBox, UsernameHBox, PasswordHBox, Submit, Back);
        });
        Back.setOnAction(actionEvent -> {
            AdminScene(stage);
        });
        field.setText("");
        disease.setText("");
        Submit.setOnAction(actionEvent -> {
            if(!Objects.equals(field.getText(), "")){
                UserManagement.AddUser(name.getText(), lastname.getText(), field.getText(), record.getText(), sex.getText(), username.getText(), password.getText());
                field.setText("");
                AdminScene(stage);
            }
            else if(!Objects.equals(disease.getText(), "")){
                if(Objects.equals(mode.getText(), "VIP") || Objects.equals(mode.getText(), "Normal") || Objects.equals(mode.getText(), "Insurance")) {
                    UserManagement.AddUser(name.getText(), lastname.getText(), Integer.parseInt(age.getText()), sex.getText(), disease.getText(), mode.getText(), username.getText(), password.getText());
                    disease.setText("");
                    AdminScene(stage);
                }
                else {
                    error.setText("Mode only can be VIP or Normal or Insurance");
                }
            }
            else {
                UserManagement.AddUser(name.getText(), lastname.getText(), record.getText(), sex.getText(), username.getText(), password.getText());
                AdminScene(stage);
            }

        });
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    static void PhysicianScene(Stage stage, Physician p){
        Button PickPatientButton = new Button("Pick Patient");
        Button ListPatientsButton = new Button("List Patients");
        Button ViewPatientInfoButton = new Button("View Patient Info");
        Button WriteMedicineButton = new Button("Write Medicine");
        Button DischargePatientButton = new Button("Discharge Patient");
        Button ChangePasswordButton = new Button("Change Password");
        Button Exit = new Button("Exit");

        PickPatientButton.setCursor(Cursor.HAND);
        ListPatientsButton.setCursor(Cursor.HAND);
        ViewPatientInfoButton.setCursor(Cursor.HAND);
        WriteMedicineButton.setCursor(Cursor.HAND);
        DischargePatientButton.setCursor(Cursor.HAND);
        ChangePasswordButton.setCursor(Cursor.HAND);
        Exit.setCursor(Cursor.HAND);

        PickPatientButton.setTooltip(new Tooltip("Pick Patient"));
        ListPatientsButton.setTooltip(new Tooltip("List Patients"));
        ViewPatientInfoButton.setTooltip(new Tooltip("View Patient Info"));
        WriteMedicineButton.setTooltip(new Tooltip("Write Medicine"));
        DischargePatientButton.setTooltip(new Tooltip("Discharge Patient"));
        ChangePasswordButton.setTooltip(new Tooltip("Change Password"));
        Exit.setTooltip(new Tooltip("Exit"));

        Label list = new Label("");

        VBox rootPhysician = new VBox();
        rootPhysician.getChildren().addAll(PickPatientButton, ListPatientsButton, ViewPatientInfoButton, WriteMedicineButton, DischargePatientButton, ChangePasswordButton, Exit, list);
        rootPhysician.setAlignment(Pos.CENTER);
        rootPhysician.setSpacing(10);

        Scene PhysicianScene = new Scene(rootPhysician, 400, 700);
        stage.setScene(PhysicianScene);

        Exit.setOnAction(action ->{
            LoginScene(stage);
        });
        PickPatientButton.setOnAction(actionEvent -> {
            PickPatientScene(stage, p);
        });
        ListPatientsButton.setOnAction(actionEvent -> {
            if(p.ListOfPatients.size() == 0)
                list.setText("There is no patient.");
            else
                list.setText(p.ListPatients());
        });
        ViewPatientInfoButton.setOnAction(actionEvent -> {
            ViewPatientInfoScene(stage, p);
        });
        WriteMedicineButton.setOnAction(actionEvent -> {
            if(p.Messages.size() == 0)
                list.setText("There is no message from nurse.");
            else
                WriteMedicineScene(stage, p);
        });
        DischargePatientButton.setOnAction(actionEvent -> {
            DischargePatientScene(stage, p);
        });
        ChangePasswordButton.setOnAction(actionEvent -> {
            PhysicianChangePasswordScene(stage, p);
        });
    }

    static void PhysicianChangePasswordScene(Stage stage, Physician p){
        Label OldPass = new Label("Current Password:        ");
        TextField Oldpass = new TextField();
        HBox OldpassHBox = new HBox(OldPass, Oldpass);
        OldpassHBox.setAlignment(Pos.CENTER);
        OldpassHBox.setSpacing(15);

        Label NewPass = new Label("New Password:             ");
        TextField Newpass = new TextField();
        HBox NewpassHBox = new HBox(NewPass, Newpass);
        NewpassHBox.setAlignment(Pos.CENTER);
        NewpassHBox.setSpacing(15);

        Label Repeat = new Label("Repeat New Password:");
        TextField repeat = new TextField();
        HBox RepeatHBox = new HBox(Repeat, repeat);
        RepeatHBox.setAlignment(Pos.CENTER);
        RepeatHBox.setSpacing(15);

        Button Submit = new Button("Submit");
        Button Back = new Button("Back");
        Submit.setCursor(Cursor.HAND);
        Back.setCursor(Cursor.HAND);

        Label result = new Label("");

        VBox rootChangePass = new VBox();
        rootChangePass.getChildren().addAll(OldpassHBox, NewpassHBox, RepeatHBox, Submit, Back, result);

        rootChangePass.setAlignment(Pos.CENTER);
        rootChangePass.setSpacing(10);

        Scene ChangePassScene = new Scene(rootChangePass, 400, 700);
        stage.setScene(ChangePassScene);

        Submit.setOnAction(actionEvent -> {
            if(Objects.equals(Newpass.getText(), repeat.getText())) {
                int res = UserManagement.ChangePassword(Oldpass.getText(), Newpass.getText(), p);
                switch (res){
                    case -1:
                        result.setText("Current Password is wrong.");
                        break;
                    case 0:
                        result.setText("Invalid Password for New Password.");
                        break;
                    case 1:
                        result.setText("Password changed successfully.");
                        break;
                }
            }else {
                result.setText("New Password and Repeat New Password are not same:/");
            }
        });
        Back.setOnAction(actionEvent -> {
            PhysicianScene(stage, p);
        });
    }

    static void DischargePatientScene(Stage stage, Physician p){
        Label ID = new Label("Patient ID:      ");
        TextField id = new TextField();
        HBox IDHBox = new HBox(ID, id);
        IDHBox.setAlignment(Pos.CENTER);
        IDHBox.setSpacing(15);

        Label summery = new Label("Summery:");

        Label result = new Label("");

        TextArea textArea = new TextArea();
        textArea.setMaxSize(270, 200);

        Button Submit = new Button("Submit");
        Button Back = new Button("Back");
        Submit.setCursor(Cursor.HAND);
        Back.setCursor(Cursor.HAND);


        VBox rootDischarge = new VBox();
        rootDischarge.getChildren().addAll(IDHBox, summery, textArea, Submit, Back, result);

        rootDischarge.setAlignment(Pos.CENTER);
        rootDischarge.setSpacing(10);

        Scene DischargeScene = new Scene(rootDischarge, 400, 700);
        stage.setScene(DischargeScene);

        Submit.setOnAction(actionEvent -> {
            int temp = p.DischargePatient(Integer.parseInt(id.getText()), textArea.getText());
            if(temp == 1)
                result.setText("Patient successfully discharged, please click Back.");
            else if (temp == 0)
                result.setText("Patient not found.");
        });
        Back.setOnAction(actionEvent -> {
            PhysicianScene(stage, p);
        });
    }

    static void WriteMedicineScene(Stage stage, Physician p){ //farz mikonim nurse Patient-ID dorost bede.
        Patient patient = p.AnswerMedicine();

        Label message = new Label(p.Messages.get(0));
        Label patientInfo = new Label("Name: "+patient.Name+" "+patient.LastName+'\n'+"Problem: "+patient.Disease+"    Date of admission: "+patient.Status+'\n');
        Label medicine = new Label("Medicine:");

        TextArea textArea = new TextArea();
        textArea.setMaxSize(270, 200);

        Button Submit = new Button("Submit");
        Button Back = new Button("Back");
        Submit.setCursor(Cursor.HAND);
        Back.setCursor(Cursor.HAND);


        VBox rootWriteMedicine = new VBox();
        rootWriteMedicine.getChildren().addAll(message, patientInfo, medicine, textArea, Submit, Back);

        rootWriteMedicine.setAlignment(Pos.CENTER);
        rootWriteMedicine.setSpacing(10);

        Scene WriteMedicineScene = new Scene(rootWriteMedicine, 400, 700);
        stage.setScene(WriteMedicineScene);

        Submit.setOnAction(actionEvent -> {
            p.AnswerMedicine(patient, textArea.getText());
            PhysicianScene(stage, p);
        });
        Back.setOnAction(actionEvent -> {
            PhysicianScene(stage, p);
        });

    }

    static void ViewPatientInfoScene(Stage stage, Physician p){
        Label ByName = new Label("Search by Lastname");

        Label Lastname = new Label("Lastname: ");
        TextField lastname = new TextField();
        HBox LastnameHBox = new HBox(Lastname, lastname);
        LastnameHBox.setAlignment(Pos.CENTER);
        LastnameHBox.setSpacing(15);

        Label ByID = new Label("Search by ID");

        Label ID = new Label("ID:             ");
        TextField id = new TextField();
        HBox IDHBox = new HBox(ID, id);
        IDHBox.setAlignment(Pos.CENTER);
        IDHBox.setSpacing(15);

        Label result = new Label("");

        Button Search = new Button("Search");
        Button Back = new Button("Back");
        Search.setCursor(Cursor.HAND);
        Back.setCursor(Cursor.HAND);


        VBox rootSearch = new VBox();
        rootSearch.getChildren().addAll(ByName, LastnameHBox, ByID, IDHBox, result, Search, Back);

        rootSearch.setAlignment(Pos.CENTER);
        rootSearch.setSpacing(10);

        Scene SearchScene = new Scene(rootSearch, 400, 700);
        stage.setScene(SearchScene);

        Back.setOnAction(actionEvent -> {
            PhysicianScene(stage, p);
        });
        Search.setOnAction(actionEvent -> {
            if(!Objects.equals(lastname.getText(), "") && Objects.equals(id.getText(), "")){
                result.setText(p.ViewPatientInfo(lastname.getText()));
            }else if(Objects.equals(lastname.getText(), "") && !Objects.equals(id.getText(), "")){
                result.setText(p.ViewPatientInfo(Integer.parseInt(id.getText())));
            }else{
                result.setText("Invalid input.");
            }
        });
    }

    static void PickPatientScene(Stage stage, Physician p){
        String Patients = p.PickPatient();
        Label patients = new Label(Patients);

        Label Choose = new Label("ENTER NUMBER: ");
        TextField choose = new TextField();
        HBox ChooseHBox = new HBox(Choose, choose);
        ChooseHBox.setAlignment(Pos.CENTER);
        ChooseHBox.setSpacing(15);

        Label Date = new Label("Entrance Date:");
        DatePicker date = new DatePicker();
        HBox DateHBox = new HBox(Date, date);
        DateHBox.setAlignment(Pos.CENTER);
        DateHBox.setSpacing(15);

        Button Submit = new Button("Submit");
        Button Back = new Button("Back");
        Submit.setCursor(Cursor.HAND);
        Back.setCursor(Cursor.HAND);


        VBox rootPick = new VBox();
        rootPick.getChildren().addAll(patients, ChooseHBox, DateHBox, Submit, Back);

        rootPick.setAlignment(Pos.CENTER);
        rootPick.setSpacing(10);

        Scene PickScene = new Scene(rootPick, 400, 700);
        stage.setScene(PickScene);

        Label error = new Label("");
        rootPick.getChildren().add(error);

        Back.setOnAction(actionEvent -> {
            PhysicianScene(stage, p);
        });
        Submit.setOnAction(actionEvent -> {
            int res = p.PickPatient(Integer.parseInt(choose.getText())-1, date.getValue().toString());
            if(res == 1)
                error.setText("Successful");
            else if(res == 0)
                error.setText("Failed");
        });
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    static void NurseScene(Stage stage, Nurse n){

        Button CheckThePatientStateButton = new Button("Check The Patient State");
        Button ChangePasswordButton = new Button("Change Password");
        Button MessageButton = new Button("Messages");
        Button Exit = new Button("Exit");

        CheckThePatientStateButton.setCursor(Cursor.HAND);
        ChangePasswordButton.setCursor(Cursor.HAND);
        MessageButton.setCursor(Cursor.HAND);
        Exit.setCursor(Cursor.HAND);

        CheckThePatientStateButton.setTooltip(new Tooltip("Check The Patient State"));
        ChangePasswordButton.setTooltip(new Tooltip("Change Password"));
        MessageButton.setTooltip(new Tooltip("Messages"));
        Exit.setTooltip(new Tooltip("Exit"));

        Label error = new Label("");

        VBox rootNurse = new VBox();
        rootNurse.getChildren().addAll(CheckThePatientStateButton, ChangePasswordButton, MessageButton, Exit, error);
        rootNurse.setAlignment(Pos.CENTER);
        rootNurse.setSpacing(10);

        Scene NurseScene = new Scene(rootNurse, 400, 700);
        stage.setScene(NurseScene);

        Exit.setOnAction(action ->{
            LoginScene(stage);
        });
        CheckThePatientStateButton.setOnAction(actionEvent -> {
            CheckThePatientStateScene(stage, n);
        });
        ChangePasswordButton.setOnAction(actionEvent -> {
            NurseChangePasswordScene(stage, n);
        });
        MessageButton.setOnAction(actionEvent -> {
            if(Objects.equals(n.GetMessages(), ""))
                error.setText("No Message.");
            else
                error.setText(n.GetMessages());
        });
    }

    static void CheckThePatientStateScene(Stage stage, Nurse n){
        RadioButton NoDoctorAssignedButton = new RadioButton("No Doctor Assigned");
        RadioButton CheckedInButton = new RadioButton("Checked In");
        RadioButton GetPrescriptionButton = new RadioButton("Get Prescription");
        RadioButton DischargeButton = new RadioButton("Discharge");
        RadioButton ExitButton = new RadioButton("Back");

        Label result = new Label("");

        VBox rootCheckState = new VBox();
        rootCheckState.getChildren().addAll(NoDoctorAssignedButton, CheckedInButton, GetPrescriptionButton, DischargeButton, ExitButton, result);

        rootCheckState.setAlignment(Pos.CENTER);
        rootCheckState.setSpacing(10);

        Scene CheckStateScene = new Scene(rootCheckState, 400, 700);
        stage.setScene(CheckStateScene);

        ExitButton.setOnAction(actionEvent -> {
            NurseScene(stage, n);
        });
        NoDoctorAssignedButton.setOnAction(actionEvent -> {
            if(Objects.equals(n.NoDoctorAssignedPatients(), ""))
                result.setText("There is no NoDoctorAssigned Patient.");
            else
                result.setText(n.NoDoctorAssignedPatients());
        });
        DischargeButton.setOnAction(actionEvent -> {
            if(Objects.equals(n.DischargePatients(), ""))
                result.setText("There is no discharged patient.");
            else
                result.setText(n.DischargePatients());
        });
        CheckedInButton.setOnAction(actionEvent -> {
            CheckedInScene(stage, n);
        });
        GetPrescriptionButton.setOnAction(actionEvent -> {
            if(Objects.equals(n.GetPrescription(), ""))
                result.setText("No Patients ready for get prescription.");
            else
                GetPrescriptionScene(stage, n);
        });
    }

    static void GetPrescriptionScene(Stage stage, Nurse n){
        Label data = new Label(n.GetPrescription());
        Label label = new Label("Enter index of patient:");
        TextField enterIndex = new TextField();
        HBox EnterIndex = new HBox(enterIndex);
        EnterIndex.setAlignment(Pos.CENTER);
        Button Submit = new Button("Submit");
        Button Back = new Button("Back");
        Submit.setCursor(Cursor.HAND);
        Back.setCursor(Cursor.HAND);

        Label error = new Label("");

        VBox rootGetPrescription = new VBox();
        rootGetPrescription.getChildren().addAll(data, label, EnterIndex, Submit, Back, error);
        rootGetPrescription.setAlignment(Pos.CENTER);
        rootGetPrescription.setSpacing(10);

        Scene GetPrescriptionScene = new Scene(rootGetPrescription, 400, 700);
        stage.setScene(GetPrescriptionScene);

        Back.setOnAction(action ->{
            NurseScene(stage, n);
        });
        Submit.setOnAction(actionEvent -> {
            int res = n.GetPrescription(Integer.parseInt(enterIndex.getText()));
            if(res == 1)
                error.setText("Successful");
            else if(res == 0)
                error.setText("Failed");
        });
    }

    static void CheckedInScene(Stage stage, Nurse n){
        Label first = new Label("Select first date:");
        DatePicker First = new DatePicker();

        Label second = new Label("Select second date:");
        DatePicker Second = new DatePicker();

        Button Submit = new Button("Submit");
        Button Back = new Button("Back");
        Submit.setCursor(Cursor.HAND);
        Back.setCursor(Cursor.HAND);

        Label result = new Label("");

        VBox rootCheckedIn = new VBox();
        rootCheckedIn.getChildren().addAll(first, First, second, Second, Submit, Back, result);

        rootCheckedIn.setAlignment(Pos.CENTER);
        rootCheckedIn.setSpacing(10);

        Scene CheckedInScene = new Scene(rootCheckedIn, 400, 700);
        stage.setScene(CheckedInScene);

        Back.setOnAction(actionEvent -> {
            CheckThePatientStateScene(stage, n);
        });
        Submit.setOnAction(actionEvent -> {
            String Date1 = First.getValue().toString();
            String Date2 = Second.getValue().toString();
            char[] date1 = new char[6];
            char[] date2 = new char[6];
            Date1.getChars(2, 4, date1, 0);
            Date1.getChars(5, 7, date1, 2);
            Date1.getChars(8, 10, date1, 4);
            Date2.getChars(2, 4, date2, 0);
            Date2.getChars(5, 7, date2, 2);
            Date2.getChars(8, 10, date2, 4);
            int D1 = Integer.parseInt(String.valueOf(date1));
            int D2 = Integer.parseInt(String.valueOf(date2));

            if(Objects.equals(n.CheckedInPatients(D1, D2), ""))
                result.setText("No Patients.");
            else
                result.setText(n.CheckedInPatients(D1, D2));
        });
    }

    static void NurseChangePasswordScene(Stage stage, Nurse p){
        Label OldPass = new Label("Current Password:        ");
        TextField Oldpass = new TextField();
        HBox OldpassHBox = new HBox(OldPass, Oldpass);
        OldpassHBox.setAlignment(Pos.CENTER);
        OldpassHBox.setSpacing(15);

        Label NewPass = new Label("New Password:             ");
        TextField Newpass = new TextField();
        HBox NewpassHBox = new HBox(NewPass, Newpass);
        NewpassHBox.setAlignment(Pos.CENTER);
        NewpassHBox.setSpacing(15);

        Label Repeat = new Label("Repeat New Password:");
        TextField repeat = new TextField();
        HBox RepeatHBox = new HBox(Repeat, repeat);
        RepeatHBox.setAlignment(Pos.CENTER);
        RepeatHBox.setSpacing(15);

        Button Submit = new Button("Submit");
        Button Back = new Button("Back");
        Submit.setCursor(Cursor.HAND);
        Back.setCursor(Cursor.HAND);

        Label result = new Label("");

        VBox rootChangePass = new VBox();
        rootChangePass.getChildren().addAll(OldpassHBox, NewpassHBox, RepeatHBox, Submit, Back, result);

        rootChangePass.setAlignment(Pos.CENTER);
        rootChangePass.setSpacing(10);

        Scene ChangePassScene = new Scene(rootChangePass, 400, 700);
        stage.setScene(ChangePassScene);

        Submit.setOnAction(actionEvent -> {
            if(Objects.equals(Newpass.getText(), repeat.getText())) {
                int res = UserManagement.ChangePassword(Oldpass.getText(), Newpass.getText(), p);
                switch (res){
                    case -1:
                        result.setText("Current Password is wrong.");
                        break;
                    case 0:
                        result.setText("Invalid Password for New Password.");
                        break;
                    case 1:
                        result.setText("Password changed successfully.");
                        break;
                }
            }else {
                result.setText("New Password and Repeat New Password are not same:/");
            }
        });
        Back.setOnAction(actionEvent -> {
            NurseScene(stage, p);
        });
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    static void PatientScene(Stage stage, Patient p){
        Button CheckOutButton = new Button("Check Out");
        Button ChangePasswordButton = new Button("Change Password");
        Button Exit = new Button("Exit");

        CheckOutButton.setCursor(Cursor.HAND);
        ChangePasswordButton.setCursor(Cursor.HAND);
        Exit.setCursor(Cursor.HAND);

        CheckOutButton.setTooltip(new Tooltip("Check Out"));
        ChangePasswordButton.setTooltip(new Tooltip("Change Password"));
        Exit.setTooltip(new Tooltip("Exit"));

        Label error = new Label("");

        VBox rootPatient = new VBox();
        rootPatient.getChildren().addAll(CheckOutButton, ChangePasswordButton, Exit, error);
        rootPatient.setAlignment(Pos.CENTER);
        rootPatient.setSpacing(10);

        Scene PatientScene = new Scene(rootPatient, 400, 700);
        stage.setScene(PatientScene);

        Exit.setOnAction(action ->{
            LoginScene(stage);
        });
        CheckOutButton.setOnAction(actionEvent -> {
            if(p.Bill == -1){
                error.setText("You are not discharged.");
            }else{
                error.setText("You are discharged.\nTotal cost: "+p.Bill+" $");
            }
        });
        ChangePasswordButton.setOnAction(actionEvent -> {
            PatientChangePasswordScene(stage, p);
        });
    }

    static void PatientChangePasswordScene(Stage stage, Patient p){
        Label OldPass = new Label("Current Password:        ");
        TextField Oldpass = new TextField();
        HBox OldpassHBox = new HBox(OldPass, Oldpass);
        OldpassHBox.setAlignment(Pos.CENTER);
        OldpassHBox.setSpacing(15);

        Label NewPass = new Label("New Password:             ");
        TextField Newpass = new TextField();
        HBox NewpassHBox = new HBox(NewPass, Newpass);
        NewpassHBox.setAlignment(Pos.CENTER);
        NewpassHBox.setSpacing(15);

        Label Repeat = new Label("Repeat New Password:");
        TextField repeat = new TextField();
        HBox RepeatHBox = new HBox(Repeat, repeat);
        RepeatHBox.setAlignment(Pos.CENTER);
        RepeatHBox.setSpacing(15);

        Button Submit = new Button("Submit");
        Button Back = new Button("Back");
        Submit.setCursor(Cursor.HAND);
        Back.setCursor(Cursor.HAND);

        Label result = new Label("");

        VBox rootChangePass = new VBox();
        rootChangePass.getChildren().addAll(OldpassHBox, NewpassHBox, RepeatHBox, Submit, Back, result);

        rootChangePass.setAlignment(Pos.CENTER);
        rootChangePass.setSpacing(10);

        Scene ChangePassScene = new Scene(rootChangePass, 400, 700);
        stage.setScene(ChangePassScene);

        Submit.setOnAction(actionEvent -> {
            if(Objects.equals(Newpass.getText(), repeat.getText())) {
                int res = UserManagement.ChangePassword(Oldpass.getText(), Newpass.getText(), p);
                switch (res){
                    case -1:
                        result.setText("Current Password is wrong.");
                        break;
                    case 0:
                        result.setText("Invalid Password for New Password.");
                        break;
                    case 1:
                        result.setText("Password changed successfully.");
                        break;
                }
            }else {
                result.setText("New Password and Repeat New Password are not same:/");
            }
        });
        Back.setOnAction(actionEvent -> {
            PatientScene(stage, p);
        });
    }
}
