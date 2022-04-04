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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class financialManger {
    
    public financialManger(Stage financialStage, String username) throws ClassNotFoundException, SQLException {
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/electricity", "root", "AHCaaa22");
        Statement stmt = con.createStatement();
        
        StackPane rootFinancialManager = new StackPane();
        Scene financialScene = new Scene(rootFinancialManager, 950, 650);
        financialStage.setScene(financialScene);
        rootFinancialManager.setStyle("-fx-background-color: #5A0D54");
        
        Label welcom = new Label("HELLO DEAR " + "\"" + username + "\"");
        welcom.setTranslateX(0);
        welcom.setTranslateY(-270);
        welcom.setStyle("-fx-font-size: 45; -fx-font-weight: BOLD; -fx-text-fill: #FFFFFF");
        
        Button Exit = new Button("EXIT");
        Exit.setStyle("-fx-background-color: red");
        Exit.setPrefSize(160, 80);
        Exit.setTranslateX(-390);
        Exit.setTranslateY(-150);
        
        Exit.setOnAction((event) -> {
            financialStage.close();
        });
        
        Button Cost = new Button("ElectricityCost");
        Cost.setStyle("-fx-background-color: YELLOW");
        Cost.setPrefSize(160, 80);
        Cost.setTranslateX(-200);
        Cost.setTranslateY(-150);
        
        Cost.setOnAction(((event) -> {
            rootFinancialManager.getChildren().clear();
            Label business = new Label("furmola for business usage (privious watt^3)+(priviuswatt^2)+100");
            business.setTranslateX(0);
            business.setTranslateY(0);
            business.setStyle("-fx-font-size: 30; -fx-font-weight: BOLD; -fx-text-fill: #FFFFFF");
            Label domestic = new Label("furmola for domestic usage (privious watt^2)+(priviuswatt^1)+50");
            domestic.setTranslateX(0);
            domestic.setTranslateY(100);
            domestic.setStyle("-fx-font-size: 30; -fx-font-weight: BOLD; -fx-text-fill: #FFFFFF");
            rootFinancialManager.getChildren().addAll(business, domestic);
            try {
                JOptionPane.showMessageDialog(null, "Customers debt is accounting and Database is Updating on basis of furmola... \n Please wait");
                ResultSet amongCustomers = stmt.executeQuery("select * from customer");
                int previus, now, debt=0;
                while (amongCustomers.next()) {
                    if (amongCustomers.getString("Various").equals("domestic")) {
                        previus = amongCustomers.getInt("previusWattUsage");
                        now = amongCustomers.getInt("nowWattUsage");
                        debt = (int) (Math.pow(previus, 2) + Math.pow(now, 2) + 50);
                    } else if(amongCustomers.getString("Various").equals("business")) {
                        previus = amongCustomers.getInt("previusWattUsage");
                        now = amongCustomers.getInt("nowWattUsage");
                        debt = (int) (Math.pow(previus, 3) + Math.pow(now, 2) + 100);
                    }
                    
                  
                }
                  try {
                        String query = "UPDATE customer SET debt=?";
                        
                        PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
                        preparedStmt.setInt(1, debt);
                        
                        preparedStmt.execute();
                        
                       JOptionPane.showMessageDialog(null, "all debt registerd in customers info Successfully!");
                        JOptionPane.showMessageDialog(null, "program will be closed");
                        financialStage.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(financialManger.class.getName()).log(Level.SEVERE, null, ex);
                    }
            } catch (SQLException ex) {
                Logger.getLogger(financialManger.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }));
        
        Button debts = new Button("All debts");
        debts.setStyle("-fx-background-color: PURPLE");
        debts.setPrefSize(160, 80);
        debts.setTranslateX(0);
        debts.setTranslateY(-150);
        
        debts.setOnAction(((event) -> {
            rootFinancialManager.getChildren().clear();
            int x = -300;
            try {
                ResultSet customerDebt = stmt.executeQuery("select * from customer");
                while (customerDebt.next()) {
                    Label list = new Label("the list of all debts: ");
                    list.setTranslateX(-220);
                    list.setTranslateY(-280);
                    list.setStyle("-fx-font-size: 45; -fx-font-weight: BOLD; -fx-text-fill: #FFFFFF");
                    
                    if (customerDebt.getInt("debt") != 0) {
                        
                        Label haveDebt = new Label(customerDebt.getString("username") + " HAVE " + customerDebt.getInt("debt") + " rials ");
                        haveDebt.setTranslateX(-100);
                        x = x + 100;
                        haveDebt.setTranslateY(x);
                        haveDebt.setStyle("-fx-font-size: 45; -fx-font-weight: BOLD; -fx-text-fill: #FFFFFF");
                        
                        rootFinancialManager.getChildren().addAll(haveDebt, list);
                    }
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(financialManger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
        
        Button Summons = new Button("Summons");
        Summons.setStyle("-fx-background-color: GRAY");
        Summons.setPrefSize(160, 80);
        Summons.setTranslateX(200);
        Summons.setTranslateY(-150);
        
        Summons.setOnAction(((event) -> {
            JOptionPane.showMessageDialog(null, "a Massage SECCESSFULLY sented for all debtfull customers!");
        }));
        
        Button Accounting = new Button("Accounting");
        Accounting.setStyle("-fx-background-color: BLUE");
        Accounting.setPrefSize(160, 80);
        Accounting.setTranslateX(390);
        Accounting.setTranslateY(-150);
        
        Accounting.setOnAction(((event) -> {
            int allPay = 0;
            try {
                ResultSet customerPay = stmt.executeQuery("select * from financialManager");
                while(customerPay.next()){
                    int temp = customerPay.getInt("payments");
                    allPay += temp;
                }
            } catch (SQLException ex) {
              
            }
            rootFinancialManager.getChildren().clear();
            Label wholePay = new Label("Whole Payment from customers is: " + allPay);
            wholePay.setTranslateX(0);
            wholePay.setTranslateY(-50);
            wholePay.setStyle("-fx-font-size: 45; -fx-font-weight: BOLD; -fx-text-fill: #FFFFFF");
            int profitNum = ((allPay*3)/5);
            Label profit = new Label("profit of customer payment is: " + profitNum);
            profit.setTranslateX(0);
            profit.setTranslateY(50);
            profit.setStyle("-fx-font-size: 45; -fx-font-weight: BOLD; -fx-text-fill: #FFFFFF");
            rootFinancialManager.getChildren().addAll(profit, wholePay);
        }));
        
        rootFinancialManager.getChildren().addAll(Exit, Accounting, Summons, debts, Cost, welcom);
        
    }
    
}
