/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billpaymentelectricityorganazation;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author home
 */
public class technecian {

    public technecian(Stage technecianStage, String username) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/electricity", "root", "AHCaaa22");
        Statement stmt = con.createStatement();

        Label welcom = new Label("HELLO DEAR " + "\"" + username + "\"");
        welcom.setTranslateX(0);
        welcom.setTranslateY(-270);
        welcom.setStyle("-fx-font-size: 45; -fx-font-weight: BOLD; -fx-text-fill: #FFFFFF");

        Button Exit = new Button("EXIT");
        Exit.setStyle("-fx-background-color: red");
        Exit.setPrefSize(160, 80);
        Exit.setTranslateX(-200);
        Exit.setTranslateY(-150);

        Button Register = new Button("REGISTER");
        Register.setStyle("-fx-background-color: GREEN");
        Register.setPrefSize(160, 80);
        Register.setTranslateX(200);
        Register.setTranslateY(-150);

        ResultSet customerQuery = stmt.executeQuery("select * from thechnicalAssist");
        while (customerQuery.next()) {
            if (username.equals(customerQuery.getString("username"))) {
                JOptionPane.showMessageDialog(null, "Dear " + username + "\n as a Technecian you should read electric metter  \n"
                        + " Now Please Insert Data Now: ");
            }
        }

        TextField usernameOfConsumer = new TextField();
        usernameOfConsumer.setPromptText("Enter name and lastName here");
        usernameOfConsumer.setPrefSize(900, 70);
        usernameOfConsumer.setMaxSize(900, 70);
        usernameOfConsumer.setTranslateX(0);
        usernameOfConsumer.setTranslateY(-50);

        TextField numberOfCounter = new TextField();
        numberOfCounter.setPromptText("Enter number of consumer counter");
        numberOfCounter.setPrefSize(900, 70);
        numberOfCounter.setMaxSize(900, 70);
        numberOfCounter.setTranslateX(0);
        numberOfCounter.setTranslateY(50);

        TextField numberPrevoiusWatt = new TextField();
        numberPrevoiusWatt.setPromptText("Enter quntity of Prevoius watts");
        numberPrevoiusWatt.setPrefSize(900, 70);
        numberPrevoiusWatt.setMaxSize(900, 70);
        numberPrevoiusWatt.setTranslateX(0);
        numberPrevoiusWatt.setTranslateY(150);

        TextField numberNewWatt = new TextField();
        numberNewWatt.setPromptText("Enter quntity of new consumed watts");
        numberNewWatt.setPrefSize(900, 70);
        numberNewWatt.setMaxSize(900, 70);
        numberNewWatt.setTranslateX(0);
        numberNewWatt.setTranslateY(250);

        Exit.setOnAction((event) -> {
            technecianStage.close();
        });

        Register.setOnAction(((event) -> {

            if ((usernameOfConsumer.getText().length() != 0) && (numberOfCounter.getText().length() != 0)
                    && (numberPrevoiusWatt.getText().length() != 0) && (numberNewWatt.getText().length() != 0)) {
                try {
                    ResultSet amongCustomers = stmt.executeQuery("select * from customer");
                    while (amongCustomers.next()) {

                        if (usernameOfConsumer.getText().equals(amongCustomers.getString("username"))) {

                            String query = "UPDATE customer SET previusWattUsage=? , nowWattUsage=? WHERE username=?";
                            PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
                            preparedStmt.setInt(1, Integer.valueOf(numberPrevoiusWatt.getText()));
                            preparedStmt.setInt(2, Integer.valueOf(numberNewWatt.getText()));
                            preparedStmt.setString(3, usernameOfConsumer.getText());
                            preparedStmt.execute();
                            JOptionPane.showMessageDialog(null, "You Enterd Information Correctly!");
                            JOptionPane.showMessageDialog(null, "Have Nice Time!");
                            technecianStage.close();
                        }

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(technecian.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "You Did Not Enter All Of Information! ");
                JOptionPane.showMessageDialog(null, "PLEASE TRY AGAIN LATER");
                technecianStage.close();
            }

        }));

        StackPane rootTechnecian = new StackPane();
        Scene technecianScene = new Scene(rootTechnecian, 950, 650);
        rootTechnecian.setStyle("-fx-background-color: #1EDDA9");
        rootTechnecian.getChildren().addAll(Exit, welcom, Register, numberNewWatt, numberPrevoiusWatt, usernameOfConsumer, numberOfCounter);
        technecianStage.setScene(technecianScene);

    }

}
