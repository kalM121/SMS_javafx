package com.example.studentmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;

import static java.lang.String.*;

public class dashboardController implements Initializable {
    @FXML
    private Button home_btn;
    @FXML
    private AnchorPane home_form;

    @FXML
    private Label home_totalEnrolled;

    @FXML
    private BarChart<?, ?> home_totalEnrolledChart;

    @FXML
    private LineChart<?, ?> home_totalFemaleChart;

    @FXML
    private Label home_totalMale;

    @FXML
    private LineChart<?, ?> home_totalMaleChart;

    @FXML
    private Label home_totalFemale;

    @FXML
    private Button addStudents_addBtn;

    @FXML
    private DatePicker addStudents_birthDate;

    @FXML
    private Button addStudents_btn;

    @FXML
    private Button addStudents_clearBtn;

    @FXML
    private TableColumn<studentData, String> addStudents_col_birthDate;

    @FXML
    private TableColumn<studentData, String> addStudents_col_course;

    @FXML
    private TableColumn<studentData, String> addStudents_col_firstName;

    @FXML
    private TableColumn<studentData, String> addStudents_col_gender;

    @FXML
    private TableColumn<studentData, String> addStudents_col_lastName;

    @FXML
    private TableColumn<studentData, String> addStudents_col_status;

    @FXML
    private TableColumn<studentData, String> addStudents_col_studId;

    @FXML
    private TableColumn<studentData, String> addStudents_col_year;

    @FXML
    private ComboBox<?> addStudents_course;

    @FXML
    private Button addStudents_deleteBtn;

    @FXML
    private TextField addStudents_firstName;

    @FXML
    private AnchorPane addStudents_form;

    @FXML
    private ComboBox<?> addStudents_gender;

    @FXML
    private ImageView addStudents_imageView;

    @FXML
    private Button addStudents_insertBtn;

    @FXML
    private TextField addStudents_lastName;

    @FXML
    private TextField addStudents_search;

    @FXML
    private ComboBox<?> addStudents_status;

    @FXML
    private TextField addStudents_studId;

    @FXML
    private TableView<studentData> addStudents_tableView;

    @FXML
    private Button addStudents_updateBtn;

    @FXML
    private ComboBox<?> addStudents_year;

    @FXML
    private Button availableCourse_addBtn;

    @FXML
    private Button availableCourse_clearBtn;

    @FXML
    private TableColumn<courseData, String> availableCourse_col_course;

    @FXML
    private TableColumn<courseData, String> availableCourse_col_degree;

    @FXML
    private TableColumn<courseData, String> availableCourse_col_description;

    @FXML
    private TextField availableCourse_course;

    @FXML
    private TextField availableCourse_degree;

    @FXML
    private Button availableCourse_deleteBtn;

    @FXML
    private TextField availableCourse_description;

    @FXML
    private AnchorPane availableCourse_form;

    @FXML
    private TableView<courseData> availableCourse_tableView;

    @FXML
    private Button availableCourse_updateBtn;

    @FXML
    private Button availableCourses_btn;




    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button studentGrade_clearBtn;

    @FXML
    private TableColumn<studentData,String> studentGrade_col_course;

    @FXML
    private TableColumn<studentData,String> studentGrade_col_final;

    @FXML
    private TableColumn<studentData,String> studentGrade_col_firstSem;

    @FXML
    private TableColumn<studentData,String> studentGrade_col_secondSem;

    @FXML
    private TableColumn<studentData,String> studentGrade_col_studentId;

    @FXML
    private TableColumn<studentData,String> studentGrade_col_year;

    @FXML
    private TextField studentGrade_final;

    @FXML
    private TextField studentGrade_firstSem;

    @FXML
    private AnchorPane studentGrade_form;

    @FXML
    private TextField studentGrade_search;

    @FXML
    private TextField studentGrade_studentId;

    @FXML
    private TableView<studentData> studentGrade_tableView;

    @FXML
    private Label studentGrade_year;

    @FXML
    private Label studentGrade_course;

    @FXML
    private TextField studentGrade_secondSem;

    @FXML
    private Button studentGrade_updateBtn;
    @FXML
    private Button studentsGrade_btn;

    @FXML
    private Label username;




    private Image image;

    public void homeDisplayTotalEnrolledStudents() {

        String sql = "SELECT COUNT(id) FROM students";

        database connectNow = new database();
        Connection connect = connectNow.getConnection();

        int countEnrolled = -1;

        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
           ResultSet  result = prepare.executeQuery();

            if (result.next()) {
                countEnrolled = result.getInt(1)-1;
            }

            home_totalEnrolled.setText(valueOf(countEnrolled));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeDisplayFemaleEnrolled() {

        String sql = "SELECT COUNT(id) FROM students WHERE gender = 'Female' and status = 'Enrolled'";

        database connectNow = new database();
        Connection connect = connectNow.getConnection();

        try {
            int countFemale = 0;

            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            if (result.next()) {
                countFemale = result.getInt("COUNT(id)");
            }

            home_totalFemale.setText(valueOf(countFemale));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeDisplayMaleEnrolled() {

        String sql = "SELECT COUNT(id) FROM students WHERE gender = 'Male' and status = 'Enrolled'";

        database connectNow = new database();
        Connection connect = connectNow.getConnection();

        try {
            int countMale = 0;

            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            if (result.next()) {
                countMale = result.getInt("COUNT(id)");
            }
            home_totalMale.setText(valueOf(countMale));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void homeDisplayTotalEnrolledChart() {

        home_totalEnrolledChart.getData().clear();

        String sql = "SELECT date, COUNT(id) FROM students WHERE status = 'Enrolled' GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 5";

        database connectNow = new database();
        Connection connect = connectNow.getConnection();

        try {
            XYChart.Series chart = new XYChart.Series();

            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }

            home_totalEnrolledChart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void homeDisplayFemaleEnrolledChart() {

        home_totalFemaleChart.getData().clear();

        String sql = "SELECT date, COUNT(id) FROM students WHERE status = 'Enrolled' and gender = 'Female' GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 5";

        database connectNow = new database();
        Connection connect = connectNow.getConnection();

        try {
            XYChart.Series chart = new XYChart.Series();

            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }

            home_totalFemaleChart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void homeDisplayEnrolledMaleChart() {

        home_totalMaleChart.getData().clear();

        String sql = "SELECT date, COUNT(id) FROM students WHERE status = 'Enrolled' and gender = 'Male' GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 5";

        database connectNow = new database();
        Connection connect = connectNow.getConnection();

        try {
            XYChart.Series chart = new XYChart.Series();

            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }

            home_totalMaleChart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void addStudentsAdd(){
        String insertData = "INSERT INTO students "
                + "(studId,year,course,firstName,lastName,gender,birth,status,image,date) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";

        database connectNow = new database();
        Connection connect = connectNow.getConnection();

        try {
            Alert alert;

            if (addStudents_studId.getText().isEmpty()
                    || addStudents_year.getSelectionModel().getSelectedItem() == null
                    || addStudents_course.getSelectionModel().getSelectedItem() == null
                    || addStudents_firstName.getText().isEmpty()
                    || addStudents_lastName.getText().isEmpty()
                    || addStudents_gender.getSelectionModel().getSelectedItem() == null
                    || addStudents_birthDate.getValue() == null
                    || addStudents_status.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path == "") {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                String checkData = "SELECT studId FROM students WHERE studId = '"
                        + addStudents_studId.getText() + "'";

                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(checkData);

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Student #" + addStudents_studId.getText() + " was already exist!");
                    alert.showAndWait();
                } else {
                    PreparedStatement prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, addStudents_studId.getText());
                    prepare.setString(2, (String) addStudents_year.getSelectionModel().getSelectedItem());
                    prepare.setString(3, (String) addStudents_course.getSelectionModel().getSelectedItem());
                    prepare.setString(4, addStudents_firstName.getText());
                    prepare.setString(5, addStudents_lastName.getText());
                    prepare.setString(6, (String) addStudents_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(7, valueOf(addStudents_birthDate.getValue()));
                    prepare.setString(8, (String) addStudents_status.getSelectionModel().getSelectedItem());

                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");
                    prepare.setString(9, uri);

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(10, valueOf(sqlDate));

                    prepare.executeUpdate();

                   String insertStudentGrade = "INSERT INTO student_grade "
                            + "(studId,year,course,first_sem,second_sem,final) "
                            + "VALUES(?,?,?,?,?,?)";

                    prepare = connect.prepareStatement(insertStudentGrade);
                    prepare.setString(1, addStudents_studId.getText());
                    prepare.setString(2, (String) addStudents_year.getSelectionModel().getSelectedItem());
                    prepare.setString(3, (String) addStudents_course.getSelectionModel().getSelectedItem());
                    prepare.setString(4, "0");
                    prepare.setString(5, "0");
                    prepare.setString(6, "0");

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    //to update the table
                    addStudentsShowListData();

                    //to clear the fields
                    addStudentsClear();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void addStudentsUpdate(){
        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");

        String updateData = "UPDATE students SET "
                + "year = '" + addStudents_year.getSelectionModel().getSelectedItem()
                + "', course = '" + addStudents_course.getSelectionModel().getSelectedItem()
                + "', firstName = '" + addStudents_firstName.getText()
                + "', lastName = '" + addStudents_lastName.getText()
                + "', gender = '" + addStudents_gender.getSelectionModel().getSelectedItem()
                + "', birth = '" + addStudents_birthDate.getValue()
                + "', status = '" + addStudents_status.getSelectionModel().getSelectedItem()
                + "', image = '" + uri + "' WHERE studId= '"
                + addStudents_studId.getText() + "'";

        database connectNow = new database();
        Connection connect = connectNow.getConnection();

        try {
            Alert alert;
            if (addStudents_studId.getText().isEmpty()
                    || addStudents_year.getSelectionModel().getSelectedItem() == null
                    || addStudents_course.getSelectionModel().getSelectedItem() == null
                    || addStudents_firstName.getText().isEmpty()
                    || addStudents_lastName.getText().isEmpty()
                    || addStudents_gender.getSelectionModel().getSelectedItem() == null
                    || addStudents_birthDate.getValue() == null
                    || addStudents_status.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path == "") {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE studId" + addStudents_studId.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    Statement statement = connect.createStatement();
                    statement.executeUpdate(updateData);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    addStudentsShowListData();

                    addStudentsClear();

                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void addStudentsDelete(){

        String deleteData = "DELETE FROM students WHERE studId = '"
                + addStudents_studId.getText() + "'";

        database connectNow = new database();
        Connection connect = connectNow.getConnection();

        try {
            Alert alert;
            if (addStudents_studId.getText().isEmpty()
                    || addStudents_year.getSelectionModel().getSelectedItem() == null
                    || addStudents_course.getSelectionModel().getSelectedItem() == null
                    || addStudents_firstName.getText().isEmpty()
                    || addStudents_lastName.getText().isEmpty()
                    || addStudents_gender.getSelectionModel().getSelectedItem() == null
                    || addStudents_birthDate.getValue() == null
                    || addStudents_status.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path == "") {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE studId" + addStudents_studId.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {

                    Statement statement = connect.createStatement();
                    statement.executeUpdate(deleteData);

                    String checkData = "SELECT studId FROM student_grade "
                            + "WHERE studId= '" + addStudents_studId.getText() + "'";

                    PreparedStatement prepare = connect.prepareStatement(checkData);
                    ResultSet result = prepare.executeQuery();


                   if (result.next()) {
                        String deleteGrade = "DELETE FROM student_grade WHERE "
                                + "studId= '" + addStudents_studId.getText() + "'";

                        statement = connect.createStatement();
                        statement.executeUpdate(deleteGrade);

                    }

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    addStudentsShowListData();

                    addStudentsClear();

                } else {
                    return;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addStudentsClear(){
        addStudents_studId.setText("");
        addStudents_year.getSelectionModel().clearSelection();
        addStudents_course.getSelectionModel().clearSelection();
        addStudents_firstName.setText("");
        addStudents_lastName.setText("");
        addStudents_gender.getSelectionModel().clearSelection();
        addStudents_birthDate.setValue(null);
        addStudents_status.getSelectionModel().clearSelection();
        addStudents_imageView.setImage(null);

        getData.path = "";

    }

    public void addStudentsInsertImage(){
        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*jpg", "*png"));

        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {

            image = new Image(file.toURI().toString(), 180, 179, false, true);
            addStudents_imageView.setImage(image);

            getData.path = file.getAbsolutePath();
        }

    }
    public void addStudentsSearch(){
        FilteredList<studentData> filter = new FilteredList<>(addStudentsListD, e -> true);

        addStudents_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateStudentData -> {

                if (newValue.isEmpty()||newValue == null  ) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateStudentData.getStudId().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getYear().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getCourse().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getFirstName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getLastName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getGender().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getBirth().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getStatus().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<studentData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(addStudents_tableView.comparatorProperty());
        addStudents_tableView.setItems(sortList);

    }
    private String[] yearList = {"First Year", "Second Year", "Third Year", "Fourth Year", "fifth Year "};

    public void addStudentsYearList() {
        List<String> yearL = new ArrayList<>();
        for (String data : yearList) {
            yearL.add(data);
        }
        ObservableList obList = FXCollections.observableArrayList(yearL);
        addStudents_year.setItems(obList);
    }

    public void addStudentsCourseList(){
        String listCourse = "SELECT * FROM course";

        database connectNow = new database();
        Connection connect = connectNow.getConnection();

        try {

            ObservableList listC = FXCollections.observableArrayList();

            PreparedStatement prepare = connect.prepareStatement(listCourse);
            ResultSet result = prepare.executeQuery();

            while (result.next()) {
                listC.add(result.getString("course"));
            }
            addStudents_course.setItems(listC);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String[] genderList = {"Male", "Female"};
    public void addStudentsGenderList() {
        List<String> genderL = new ArrayList<>();
        for (String data : genderList) {
            genderL.add(data);
        }
        ObservableList obList = FXCollections.observableArrayList(genderL);
        addStudents_gender.setItems(obList);
    }

    private String[] StatusList = {"Enrolled", "Not Enrolled", "Inactive"};
    public void addStudentsStatusList() {
        List<String> statusL = new ArrayList<>();
        for (String data : StatusList) {
            statusL.add(data);


        }
        ObservableList obList = FXCollections.observableArrayList(statusL);
        addStudents_status.setItems(obList);
    }

    public ObservableList<studentData> addStudentsListData() {

        ObservableList<studentData> listStudents = FXCollections.observableArrayList();

        String sql = "SELECT studID, year,course, firstname, lastName, gender,status,image,birth  FROM students WHERE studID <> '1111111'";

        database connectNow = new database();
        Connection connect = connectNow.getConnection();

        try {
            studentData studentD;
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result ;
            result = prepare.executeQuery();

            while (result.next()) {
                studentD = new studentData(
                        result.getInt("studID"),
                        result.getString("year"),
                        result.getString("course"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("gender"),
                        result.getString("status"),
                        result.getString("image"),
                        result.getDate("birth")
                );

                listStudents.add(studentD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listStudents;
    }

    private ObservableList<studentData> addStudentsListD;

    public void addStudentsShowListData() {
        addStudentsListD = addStudentsListData();

        addStudents_col_studId.setCellValueFactory(new PropertyValueFactory<>("studId"));
        addStudents_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        addStudents_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        addStudents_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addStudents_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addStudents_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addStudents_col_birthDate.setCellValueFactory(new PropertyValueFactory<>("birth"));
        addStudents_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        addStudents_tableView.setItems(addStudentsListD);


    }

    public void addStudentsSelect() {
        studentData studentD = addStudents_tableView.getSelectionModel().getSelectedItem();
        int num = addStudents_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        addStudents_studId.setText(valueOf(studentD.getStudId()));
        addStudents_firstName.setText(valueOf(studentD.getFirstName()));
        addStudents_lastName.setText(valueOf(studentD.getLastName()));

        String birthDateString = String.valueOf(studentD.getBirth());
        LocalDate birthDate = null;
        if (birthDateString != null && !birthDateString.equals("null")) {
            birthDate = LocalDate.parse(birthDateString);
        }
        addStudents_birthDate.setValue(birthDate);

        String url = "file:" + studentD.getImage();

        image = new Image(url, 180, 179, false, true);
        addStudents_imageView.setImage(image);

        getData.path=studentD.getImage();

    }
    public void availableCourseAdd(){
        String insertData= "INSERT INTO course (course,description,degree) values(?,?,?)";

        database connectNow = new database();
        Connection connect = connectNow.getConnection();
        try {

            Alert alert;
            if (availableCourse_course.getText().isEmpty()
                    ||availableCourse_description.getText().isEmpty()||
                    availableCourse_degree.getText().isEmpty()){
                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("please fill all blank fields");
                alert.showAndWait();
            }else {
                String checkData="SELECT course FROM course WHERE course=' " +availableCourse_course.getText() + "' ";

                 Statement statement=connect.createStatement();
                ResultSet result = statement.executeQuery(checkData);

                if (result.next()){
                    alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(" course:"+ availableCourse_course.getText()+ "already exist");
                    alert.showAndWait();
                }else{
                    PreparedStatement prepare = connect.prepareStatement(insertData);
                    prepare.setString(1,availableCourse_course.getText());
                    prepare.setString(2,availableCourse_description.getText());
                    prepare.setString(3,availableCourse_degree.getText());

                    prepare.executeUpdate();

                    alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("successfully Added!");
                    alert.showAndWait();

                    availableCourseShowListData();

                    availableCourseClear();

                }
            }
        }catch (Exception e) {e.printStackTrace();}

    }
    public void availableCourseUpdate(){
        String updateData = "UPDATE course SET description = '"
                + availableCourse_description.getText() + "', degree = '"
                + availableCourse_degree.getText() + "' WHERE course = '"
                + availableCourse_course.getText() + "'";

        database connectNow = new database();
        Connection connect = connectNow.getConnection();

        try {
            Alert alert;

            if (availableCourse_course.getText().isEmpty()
                    || availableCourse_description.getText().isEmpty()
                    || availableCourse_degree.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Course: " + availableCourse_course.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    Statement statement = connect.createStatement();
                    statement.executeUpdate(updateData);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    availableCourseShowListData();

                    availableCourseClear();

                } else {
                    return;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void availableCourseDelete(){
        String deleteData = "DELETE FROM course WHERE course = '"
                + availableCourse_course.getText() + "'";

        database connectNow = new database();
        Connection connect = connectNow.getConnection();

        try {
            Alert alert;

            if (availableCourse_course.getText().isEmpty()
                    || availableCourse_description.getText().isEmpty()
                    || availableCourse_degree.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Course: " + availableCourse_course.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                   Statement statement = connect.createStatement();
                    statement.executeUpdate(deleteData);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    availableCourseShowListData();

                    availableCourseClear();

                } else {
                    return;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void availableCourseClear() {
        availableCourse_course.setText("");
        availableCourse_description.setText("");
        availableCourse_degree.setText("");
    }


    public ObservableList<courseData> availableCourseListData() {
        ObservableList<courseData> ListData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM course";

        database connectNow = new database();
        Connection connect = connectNow.getConnection();
        try {
            courseData courseD;
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            result = prepare.executeQuery();

            while (result.next()) {
                courseD = new courseData(result.getString("course"),
                        result.getString("description"),
                        result.getString("degree"));
                ListData.add(courseD);
            }
        } catch (Exception e) {e.printStackTrace();}
        return ListData;
    }
    private ObservableList<courseData> availableCourseList;
    public void availableCourseShowListData(){
        availableCourseList= availableCourseListData();

        availableCourse_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        availableCourse_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        availableCourse_col_degree.setCellValueFactory(new PropertyValueFactory<>("degree"));

        availableCourse_tableView.setItems(availableCourseList);


    }
    public void availableCourseSelect(){
        courseData courseD= availableCourse_tableView.getSelectionModel().getSelectedItem();
        int num= availableCourse_tableView.getSelectionModel().getSelectedIndex();

        if ((num-1)<-1){
            return;
        }

        availableCourse_course.setText(courseD.getCourse());
        availableCourse_description.setText(courseD.getDescription());
        availableCourse_degree.setText(courseD.getDegree());


    }
    public void studentGradeUpdate(){
        double finalCheck1 = 0;
        double finalCheck2 = 0;

        String checkData = "SELECT * FROM student_grade WHERE studId = '"
                + studentGrade_studentId.getText() + "'";

        database connectNow = new database();
        Connection connect = connectNow.getConnection();

        double finalResult = 0;

        try {

            PreparedStatement prepare = connect.prepareStatement(checkData);
            ResultSet result = prepare.executeQuery();

            if (result.next()) {
                finalCheck1 = result.getDouble("first_sem");
                finalCheck2 = result.getDouble("second_sem");
            }

            if (finalCheck1 == 0 || finalCheck2 == 0) {
                finalResult = 0;
            } else {
                finalResult = (Double.parseDouble(studentGrade_firstSem.getText())
                        + Double.parseDouble(studentGrade_secondSem.getText()))/ 2;
            }

            String updateData = "UPDATE student_grade SET "
                    + " year = '" + studentGrade_year.getText()
                    + "', course = '" + studentGrade_course.getText()
                    + "', first_sem = '" + studentGrade_firstSem.getText()
                    + "', second_sem = '" + studentGrade_secondSem.getText()
                    + "', final = '" + finalResult + "' WHERE studId = '"
                    + studentGrade_studentId.getText() + "'";

            Alert alert;

            if (studentGrade_studentId.getText().isEmpty()
                    || studentGrade_year.getText().isEmpty()
                    || studentGrade_course.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            } else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE studId" + studentGrade_studentId.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    Statement statement = connect.createStatement();
                    statement.executeUpdate(updateData);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    studentGradesShowListData();

                    studentGradeClear();
                } else {
                    return;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void studentGradeClear(){
        studentGrade_studentId.setText("");
        studentGrade_year.setText("");
        studentGrade_course.setText("");
        studentGrade_firstSem.setText("");
        studentGrade_secondSem.setText("");
    }
    public ObservableList<studentData> studentGradesListData() {

        ObservableList<studentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM student_grade";

        database connectNow = new database();
        Connection connect = connectNow.getConnection();

        try {
            studentData studentD;

            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            while (result.next()) {
                studentD = new studentData(
                        result.getInt("studId"),
                        result.getString("year"),
                        result.getString("course"),
                        result.getDouble("first_sem"),
                        result.getDouble("second_sem"),
                        result.getDouble("final"));

                listData.add(studentD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    private ObservableList<studentData> studentGradesList;

    public void studentGradesShowListData() {
        studentGradesList = studentGradesListData();

        studentGrade_col_studentId.setCellValueFactory(new PropertyValueFactory<>("studId"));
        studentGrade_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        studentGrade_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        studentGrade_col_firstSem.setCellValueFactory(new PropertyValueFactory<>("firstsem"));
        studentGrade_col_secondSem.setCellValueFactory(new PropertyValueFactory<>("secondsem"));
        studentGrade_col_final.setCellValueFactory(new PropertyValueFactory<>("finals"));

        studentGrade_tableView.setItems(studentGradesList);

    }
    public void studentGradesSelect(){
        studentData studentD = studentGrade_tableView.getSelectionModel().getSelectedItem();
        int num = studentGrade_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        studentGrade_studentId.setText(valueOf(studentD.getStudId()));
        studentGrade_year.setText(studentD.getYear());
        studentGrade_course.setText(studentD.getCourse());
        studentGrade_firstSem.setText(valueOf(studentD.getFirstsem()));
        studentGrade_secondSem.setText(valueOf(studentD.getSecondsem()));
    }
    public void studentGradesSearch(){
        FilteredList<studentData> filter = new FilteredList<>(studentGradesList, e -> true);

        studentGrade_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateStudentData -> {

                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();

                if (predicateStudentData.getStudId().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getYear().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getCourse().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getFirstsem().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getSecondsem().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getFinals().toString().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<studentData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(studentGrade_tableView.comparatorProperty());
        studentGrade_tableView.setItems(sortList);
    }



    private double x = 0;
    private double y = 0;
    public void logout(){
        try {
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");

            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)){

                logout.getScene().getWindow().hide();

                FXMLLoader fxmlLoader = new FXMLLoader(StudentManagementSystem.class.getResource("FXMLDocument.fxml"));
                Stage stage=new Stage();
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
            }else {
                return;
            }
    }catch (Exception e){
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

    public void switchForm(ActionEvent event){
        if (event.getSource()==home_btn){
            home_form.setVisible(true);
        addStudents_form.setVisible(false);
        availableCourse_form.setVisible(false);
        studentGrade_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #2178b2, #0d5283);");
            availableCourses_btn.setStyle("-fx-background-color:transparent");
            studentsGrade_btn.setStyle("-fx-background-color:transparent");
            addStudents_btn.setStyle("-fx-background-color:transparent");

            homeDisplayTotalEnrolledStudents();
            homeDisplayFemaleEnrolled();
            homeDisplayMaleEnrolled();

            homeDisplayEnrolledMaleChart();
            homeDisplayTotalEnrolledChart();
            homeDisplayFemaleEnrolledChart();

    }else if(event.getSource()==addStudents_btn){
            home_form.setVisible(false);
            addStudents_form.setVisible(true);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(false);

            addStudents_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #2178b2, #0d5283);");
            availableCourses_btn.setStyle("-fx-background-color:transparent");
            studentsGrade_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");

            addStudentsShowListData();
            addStudentsYearList();
            addStudentsGenderList();
            addStudentsStatusList();
            addStudentsCourseList();
            addStudentsSearch();
        } else if (event.getSource()==availableCourses_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(false);
            availableCourse_form.setVisible(true);
            studentGrade_form.setVisible(false);

            availableCourses_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #2178b2, #0d5283);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            studentsGrade_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");

            availableCourseShowListData();

        }else if (event.getSource()==studentsGrade_btn){
            home_form.setVisible(false);
            addStudents_form.setVisible(false);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(true);

            studentsGrade_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #2178b2, #0d5283);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            availableCourses_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");

            studentGradesShowListData();
            studentGradesSearch();
        }

    }

    public void close(){
        System.exit(0);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayUsername();
        defaultNav();

        homeDisplayTotalEnrolledStudents();
        homeDisplayMaleEnrolled();
        homeDisplayFemaleEnrolled();
        homeDisplayTotalEnrolledChart();
        homeDisplayEnrolledMaleChart();
        homeDisplayFemaleEnrolledChart();



       addStudentsShowListData();
        addStudentsYearList();
        addStudentsGenderList();
        addStudentsStatusList();
        addStudentsCourseList();


        availableCourseShowListData();

        studentGradesShowListData();


    }
}
