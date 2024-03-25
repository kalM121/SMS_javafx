package com.example.studentmanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FXMLDocumentController implements Initializable{

    @FXML
    private Button logbutton;

    @FXML
    private PasswordField passwordtextfield;

    @FXML
    private AnchorPane title_view;

    @FXML
    private TextField usernametextfield;

    @FXML
    private Button cancelBtn;
    private studentviewController studentView;



    public void cancelBtnOnAction(){
        Stage stage= (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    public void initialize(URL url, ResourceBundle rb){

    }

    private double x= 0 ;
    private double y= 0;
    @FXML
  public void loginAdmin() {

        database connectNow = new database();
        Connection connect = connectNow.getConnection();



        String username = usernametextfield.getText();
        String password = passwordtextfield.getText();

        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter both username and password.");
            alert.showAndWait();
            return;
        }

        try {
            String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
            PreparedStatement statement = connect.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                if (result.getString("role_id").equals("1")) {
                    // lead to admin dashboard GUI

                    logbutton.getScene().getWindow().hide();
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(StudentManagementSystem.class.getResource("dashboard.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 1100, 600);

                        Stage dashstage = new Stage();

                        scene.setOnMousePressed((MouseEvent event) -> {
                            x = event.getSceneX();
                            y = event.getSceneY();
                        });

                        scene.setOnMouseDragged((MouseEvent event) -> {
                            dashstage.setX(event.getScreenX() - x);
                            dashstage.setY(event.getScreenY() - y);
                            dashstage.setOpacity(.8);
                        });

                        scene.setOnMouseReleased((MouseEvent event) -> dashstage.setOpacity(1));

                        dashstage.initStyle(StageStyle.TRANSPARENT);
                        dashstage.setScene(scene);
                        dashstage.show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (result.getString("role_id").equals("2")) {
                    // lead to studentview GUI

                    logbutton.getScene().getWindow().hide();
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(StudentManagementSystem.class.getResource("studentview.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);

                       studentviewController studentView = fxmlLoader.getController();
                       studentView.setLoggedInUsername(getUsername());




                        Stage viewstage = new Stage();

                        scene.setOnMousePressed((MouseEvent event) -> {
                            x = event.getSceneX();
                            y = event.getSceneY();
                        });

                        scene.setOnMouseDragged((MouseEvent event) -> {
                            viewstage.setX(event.getScreenX() - x);
                            viewstage.setY(event.getScreenY() - y);
                            viewstage.setOpacity(.8);
                        });

                        scene.setOnMouseReleased((MouseEvent event) -> viewstage.setOpacity(1));

                        viewstage.initStyle(StageStyle.TRANSPARENT);
                        viewstage.setScene(scene);
                        viewstage.show();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Logged in successfully.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Wrong username or password.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
}

    public String getUsername() {
        return usernametextfield.getText();
    }
}










