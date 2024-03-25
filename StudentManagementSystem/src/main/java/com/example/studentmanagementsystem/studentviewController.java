package com.example.studentmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class studentviewController implements Initializable {
    @FXML
    private Button cancelBtn;

    @FXML
    private Button home_btn;

    @FXML
    private Button logout;
    @FXML
    private TableView<studentData> student_info_tableView;


    @FXML
    private TableColumn<studentData, String> student_col_course;

    @FXML
    private TableColumn<studentData, String> student_col_firstName;

    @FXML
    private TableColumn<studentData, String> student_col_gender;

    @FXML
    private TableColumn<studentData, String> student_col_lastName;

    @FXML
    private TableColumn<studentData, String> student_col_status;

    @FXML
    private TableColumn<studentData, String> student_col_studId;

    @FXML
    private TableColumn<studentData, String> student_col_year;

    @FXML
    private TableView<studentData> student_grade_tableView;
    @FXML
    private TableColumn<studentData, Double> student_col_firstsem;
    @FXML
    private TableColumn<studentData, Double> student_col_secondsem;
    @FXML
    private TableColumn<studentData, Double> student_col_final;
    @FXML
    private TableColumn<studentData, String> student_col_gid;

    @FXML
    private TableColumn<studentData, String> student_col_gy;
    @FXML
    private TableColumn<studentData, String> student_col_gc;


    @FXML
    private Label username;


  private String loggedInUsername;
    private String loggedInstudId;
    public void setLoggedInUsername(String username) {
        this.loggedInUsername = username;
        tableView();
        tableView1();
    }
    public void tableView() {
        loggedInstudId= fetchStudId(loggedInUsername);
        if (loggedInstudId != null) {
            configureTableColumns();
            student_grade_tableView.setItems(getStudentResults());
        }
    }
    private void configureTableColumns() {
            student_col_gid.setCellValueFactory(new PropertyValueFactory<>("studId"));
            student_col_gy.setCellValueFactory(new PropertyValueFactory<>("year"));
            student_col_gc.setCellValueFactory(new PropertyValueFactory<>("course"));

            student_col_firstsem.setCellValueFactory(new PropertyValueFactory<>("firstsem"));
            student_col_secondsem.setCellValueFactory(new PropertyValueFactory<>("secondsem"));
            student_col_final.setCellValueFactory(new PropertyValueFactory<>("finals"));

            student_grade_tableView.setItems(getStudentResults());
        }

    private String fetchStudId(String username){
        try{
            database connectNow = new database();
            Connection connect = connectNow.getConnection();

            PreparedStatement prepare=connect.prepareStatement("SELECT studId FROM students WHERE username=?");
            prepare.setString(1, username);

            ResultSet result=prepare.executeQuery();

            if (result.next()){
                return result.getString("studId");
            }


        }catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }

    private ObservableList<studentData> getStudentResults() {
        ObservableList<studentData> studentResults = FXCollections.observableArrayList();

        try {
            database connectNow = new database();
            Connection connect = connectNow.getConnection();

           PreparedStatement prepare = connect.prepareStatement("SELECT  g.studId, g.year, g.course, g.first_sem, g.second_sem, g.final FROM student_grade g JOIN students s ON g.studId = s.studId WHERE g.studId= ?");
            prepare.setString(1, this.loggedInstudId);
           ResultSet result = prepare.executeQuery();

            while (result.next()) {
                Integer studId = result.getInt("studId");
                String year = result.getString("year");
                String course = result.getString("course");
                double firstSem = result.getDouble("first_sem");
                double secondSem = result.getDouble("second_sem");
                double finals = result.getDouble("final");

                studentData studentData = new studentData(studId,year,course,firstSem, secondSem, finals);
                studentResults.add(studentData);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentResults;
    }
    public void tableView1() {
        loggedInstudId = fetchStudId(loggedInUsername);
        if (loggedInstudId != null) {
            configureTableColumn();
            student_info_tableView.setItems(getStudentInfo());
        }
    }

    private void configureTableColumn() {
        student_col_studId.setCellValueFactory(new PropertyValueFactory<>("studId"));
        student_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        student_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        student_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        student_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        student_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        student_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));

        student_info_tableView.setItems(getStudentInfo());
    }

    private ObservableList<studentData> getStudentInfo() {
        ObservableList<studentData> studentInfoList = FXCollections.observableArrayList();

        try {
            database connectNow = new database();
            Connection connect = connectNow.getConnection();

            PreparedStatement prepare = connect.prepareStatement("SELECT studId,firstName, lastName, gender, course, status, year FROM students WHERE studId = ?");
            prepare.setString(1, loggedInstudId);
            ResultSet result = prepare.executeQuery();

            if (result.next()) {
                Integer studId = result.getInt("studID");
                String year = result.getString("year");
                String course = result.getString("course");
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");
                String gender = result.getString("gender");
                String status = result.getString("status");


                studentData studentInfo = new studentData(studId,year,course,firstName, lastName,gender,  status );
                studentInfoList.add(studentInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentInfoList;
    }



    private double x = 0;
    private double y = 0;
    public void logout() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                logout.getScene().getWindow().hide();

                FXMLLoader fxmlLoader = new FXMLLoader(StudentManagementSystem.class.getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(fxmlLoader.load(), 550, 400);

                scene.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                scene.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                scene.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
            } else {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void displayUsername(){

        TextField usernameTextField = new TextField();
        usernameTextField.setText(getData.username);
    }
    public void defaultNav(){
        home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right,  #2178b2, #0d5283);");

    }


    public void close(){
        System.exit(0);

    }
    public void initialize(URL url, ResourceBundle resourceBundle){

        displayUsername();
        defaultNav();

    }
}

