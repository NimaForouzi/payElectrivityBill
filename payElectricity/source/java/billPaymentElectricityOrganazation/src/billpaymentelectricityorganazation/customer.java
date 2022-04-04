/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billpaymentelectricityorganazation;

import com.mysql.jdbc.PreparedStatement;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class customer {

    public customer(Stage customerStage, String username) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/electricity", "root", "AHCaaa22");
        Statement stmt = con.createStatement();

        ResultSet customerQuery = stmt.executeQuery("select * from customer");
        while (customerQuery.next()) {
            if (username.equals(customerQuery.getString("username")) && (customerQuery.getInt("debt") != 0)) {
                JOptionPane.showMessageDialog(null, "dear " + customerQuery.getString("username") + " you have new Massge! \n MASSAGE: \n WARNING! You Have Unpaid Debt!");
            } else if(username.equals(customerQuery.getString("username")) && (customerQuery.getInt("debt") == 0)) {
                JOptionPane.showMessageDialog(null, "dear " + customerQuery.getString("username") + " you don't have any Massage!");
            }
        }
        StackPane rootCustomer = new StackPane();
        Scene customerScene = new Scene(rootCustomer, 950, 650);
        customerStage.setScene(customerScene);

        Label welcom = new Label("HELLO DEAR " + "\"" + username + "\"");
        welcom.setTranslateX(0);
        welcom.setTranslateY(-270);
        welcom.setStyle("-fx-font-size: 45; -fx-font-weight: BOLD; -fx-text-fill: #FFFFFF");

        Button Exit = new Button("EXIT");
        Exit.setStyle("-fx-background-color: red");
        Exit.setPrefSize(160, 80);
        Exit.setTranslateX(-200);
        Exit.setTranslateY(-150);

        Button SeeFactor = new Button("See Factor");
        SeeFactor.setStyle("-fx-background-color: #C3CD00");

        SeeFactor.setPrefSize(160, 80);
        SeeFactor.setTranslateX(0);
        SeeFactor.setTranslateY(-150);

        Button payBill = new Button("Pay Bill");
        payBill.setStyle("-fx-background-color: #B900CF");
        payBill.setPrefSize(160, 80);
        payBill.setTranslateX(200);
        payBill.setTranslateY(-150);

        Exit.setOnAction((event) -> {
            customerStage.close();
        });

        SeeFactor.setOnAction((event) -> {
            Label name = new Label();
            Label kind = new Label();
            Label valuationProcess = new Label();
            Label debtAmount = new Label();
            Label Adress = new Label();
            try {
                ResultSet moreINFO = stmt.executeQuery("select * from customer");
                while (moreINFO.next()) {
                    if (username.equals(moreINFO.getString("username"))) {
                        name = new Label("consumer : " + username);
                        kind.setText("Your Way For Use Is : " + moreINFO.getString("Various"));

                        if (moreINFO.getString("Various").equals("domestic")) {

                            valuationProcess.setText("account charge will calculated 'domesticly'");
                            Adress.setText("Your Address:" + moreINFO.getString("adress"));
                            debtAmount.setText("so your final debt is: " + moreINFO.getInt("debt"));

                        }
                        if (moreINFO.getString("Various").equals("business")) {
                            valuationProcess.setText("account charg will calculated 'business' manner");

                            debtAmount.setText("so your final debt is: " + moreINFO.getInt("debt"));

                        }
                    }
                }
                moreINFO.close();

                name.setTranslateX(-170);
                name.setTranslateY(-50);
                name.setStyle("-fx-font-size: 45; -fx-font-weight: Bold; -fx-text-fill: #FFF");

                kind.setTranslateX(-210);
                kind.setTranslateY(10);
                kind.setStyle("-fx-font-size: 35; -fx-font-weight: Bold; -fx-text-fill: #FFF");

                valuationProcess.setTranslateX(-60);
                valuationProcess.setTranslateY(70);
                valuationProcess.setStyle("-fx-font-size: 35; -fx-font-weight: Bold; -fx-text-fill: #FFF");
                valuationProcess.setWrapText(true);

                Adress.setTranslateX(-130);
                Adress.setTranslateY(140);
                Adress.setStyle("-fx-font-size: 35; -fx-font-weight: Bold; -fx-text-fill: #FFF");
                Adress.setWrapText(true);

                debtAmount.setTranslateX(0);
                debtAmount.setTranslateY(220);
                debtAmount.setStyle("-fx-font-size: 55; -fx-font-weight: Bold; -fx-text-fill: #FFF");
                rootCustomer.getChildren().addAll(debtAmount, valuationProcess, kind, Adress, name);

            } catch (Exception e) {
                System.out.println(e);
            }

        });

        payBill.setOnAction((event) -> {
            try {

                ResultSet payAll = stmt.executeQuery("select * from customer");
                while (payAll.next()) {
                    if (username.equals(payAll.getString("username"))) {

                        int answer = JOptionPane.showConfirmDialog(null, (username + " You Want To Pay All " + payAll.getInt("debt") + " Debt"), "Debt Paing", JOptionPane.YES_NO_CANCEL_OPTION);
                        System.out.println(answer);
                        String[] options = {"OK"};
                        JPanel panel = new JPanel();
                        JLabel lbl = new JLabel("Enter Your name: ");
                        JTextField txt = new JTextField(10);
                        panel.add(lbl);
                        panel.add(txt);
                        if (answer == 0) {

                            String query = "UPDATE customer SET debt=?, previusWattUsage=?, nowWattUsage=? WHERE username=?";
                            PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);

                            preparedStmt.setInt(1, 0);
                            preparedStmt.setInt(2, 0);
                            preparedStmt.setInt(3, 0);
                            preparedStmt.setString(4, username);
                            preparedStmt.execute();

                            String query1 = "INSERT INTO financialManager(payments) VALUES(?)";
                            PreparedStatement preparedStmt1 = (PreparedStatement) con.prepareStatement(query1);
                            preparedStmt1.setInt(1, payAll.getInt("debt"));
                            preparedStmt1.execute();

                            JOptionPane.showConfirmDialog(null, "You paid All Your Debt seccessfuly! \n enter a button to exit program", "end", JOptionPane.YES_OPTION);

                            customerStage.close();
                        } else {
                            JOptionPane.showConfirmDialog(null, "The Operation Wasn't seccessfull! \n enter a button to exit program", "end", JOptionPane.YES_OPTION);
                            customerStage.close();

                        }
                        payAll.close();
                    }
                }
                con.close();
            } catch (Exception e) {
                System.out.println(e);
            }

        });

        rootCustomer.setStyle("-fx-background-color: #017D8C");
        rootCustomer.getChildren().addAll(Exit, SeeFactor, payBill, welcom);

    }

}
