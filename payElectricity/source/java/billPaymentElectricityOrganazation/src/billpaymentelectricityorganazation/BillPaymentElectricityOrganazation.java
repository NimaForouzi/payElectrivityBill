/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billpaymentelectricityorganazation;

import java.awt.Color;
import java.awt.Paint;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sun.font.TextLabel;

/**
 *
 * @author home
 */
public class BillPaymentElectricityOrganazation extends Application {

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        Image image = new Image(new FileInputStream("asfa.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        imageView.setTranslateX(0);
        imageView.setTranslateY(-200);

        Label Title = new Label("bill system of Electricity Orgazanation");
        Title.setStyle("-fx-font-size:35; -fx-font-weight: bold ");
        Title.setTranslateX(-20);
        Title.setTranslateY(-75);

        Label userNameLBL = new Label("username");
        userNameLBL.setStyle("-fx-font-size:20; -fx-font-weight: bold");
        userNameLBL.setTranslateX(-150);
        userNameLBL.setTranslateY(0);

        Label userNameLBL1 = new Label("password");
        userNameLBL1.setStyle("-fx-font-size:20; -fx-font-weight: bold");
        userNameLBL1.setTranslateX(-150);
        userNameLBL1.setTranslateY(75);

        TextField userNameTextField = new TextField();
        userNameTextField.setPrefSize(250, 32);
        userNameTextField.setMaxSize(250, 32);
        userNameTextField.setTranslateX(50);
        userNameTextField.setTranslateY(0);

        PasswordField passwordField = new PasswordField();
        passwordField.setPrefSize(250, 32);
        passwordField.setMaxSize(250, 32);
        passwordField.setTranslateX(50);
        passwordField.setTranslateY(75);

        Button btn = new Button("ENTER");
        btn.setPrefSize(100, 50);
        btn.setTranslateX(75);
        btn.setTranslateY(240);

        Button btn1 = new Button("EXIT");
        btn1.setPrefSize(100, 50);
        btn1.setTranslateX(-75);
        btn1.setTranslateY(240);
        Label warning = new Label();

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/electricity", "root", "AHCaaa22");
                    Statement stmt = con.createStatement();
                    ResultSet customerQuery = stmt.executeQuery("select * from customer");
                    boolean flag = false;
                    while (customerQuery.next()) {
                        
                        if ((userNameTextField.getText().equals(customerQuery.getString("username"))) || (passwordField.getText().equals(customerQuery.getInt("pass")))) {
                            customer a = new customer(primaryStage, customerQuery.getString("username"));
                            flag = true;
                        }
                    }
                    ResultSet technicalAssistQuery = stmt.executeQuery("select * from thechnicalAssist");
                    while (technicalAssistQuery.next()) {
                        
                        if ((userNameTextField.getText().equals(technicalAssistQuery.getString("username"))) || (passwordField.getText().equals(technicalAssistQuery.getInt("pass")))) {
                            technecian b = new technecian(primaryStage, technicalAssistQuery.getString("username"));
                            flag = true;
                        }
                    }
                    ResultSet financialManager = stmt.executeQuery("select * from financialManager");
                    while (financialManager.next()) {
                        
                        if ((userNameTextField.getText().equals(financialManager.getString("username"))) || (passwordField.getText().equals(financialManager.getInt("pass")))) {
                            financialManger c = new financialManger(primaryStage, financialManager.getString("username"));
                            flag = true;
                        }
                    }
                    if (flag == false) {
                        warning.setTranslateX(0);
                        warning.setTranslateY(155);
                        warning.setText("Please Enter Right Username And Password!");
                        warning.setStyle("-fx-font-size:25; -fx-font-weight:Bold; -fx-text-fill: RED;");
                    }
                    con.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
        );

        btn1.setOnAction(
                new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event
            ) {
                primaryStage.close();
            }
        }
        );

        StackPane root = new StackPane();

        root.getChildren()
                .addAll(btn, btn1, imageView, warning, userNameLBL, userNameLBL1, userNameTextField, passwordField, Title);

        root.setStyle(
                "-fx-background-color: #828282;");

        Scene scene = new Scene(root, 950, 650);

        primaryStage.setTitle(
                "ELECTRICIRY ORAGANZATION!");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
