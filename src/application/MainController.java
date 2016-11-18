/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author Josip
 */
public class MainController {
    
    @FXML
    private Label result;
    private double num1 = 0;
    private String operator = "";
    private boolean operationStarted = true;
    private final Model model = new Model();
            
    @FXML
    public void proccessNumbers (ActionEvent event) {
        if (operationStarted) {
            result.setText(""); //ako je operacija zapocela postavlja zaslon u prazan text
            operationStarted = false;
        }
        
        String value = ((Button)event.getSource()).getText();
        result.setText(result.getText() + value);
    }
    @FXML
    public void proccessOperators (ActionEvent event) {
        String value = ((Button)event.getSource()).getText();
        
        if (!value.equals("=")) { //provjerava dal je stisnut gumb jednako -> ako nije dodijeljuje se operator i broj
            if (!operator.isEmpty()) return; //provjerava dal operator zadrzi text
            
            
            operator = value;
            num1 = Double.parseDouble(result.getText());
            String num1Str = String.valueOf(num1); //pretvara vrijednost broja 1 u string
            //result.setText(""); --> stari kod
            result.setText(num1Str+operator); //ispisuje broj i operator na zaslon
            
           
        } else { //ako je vrijednost operatora jednaka znaku  =
            if (operator.isEmpty()) return; //za svaki slucaj provjerava dal je operator prazan, ako je returna
                String prevInput = result.getText(); //dohvaca prijasnji izraz
                
                
                
                String[] secondPart = prevInput.split("\\"+operator); //dobavlja drugi broj koji se nalazi poslije operatora
                
                double num2 = Double.valueOf(secondPart[1].trim()); //pretvara drugi broj u double
                

                
                System.out.println("previous input = " + prevInput); //ispisuje upisani izraz

                //double num2 = Double.parseDouble(result.getText());
                
                double output = model.calculate(num1, num2, operator); //racuna izraz i vraca ga u varijabli output
                
                System.out.println("num1 = " + num1);
                System.out.println("num2 = " + num2);
                
                String num1Str = String.valueOf(num1);
                String num2Str = String.valueOf(num2);
                
                
                result.setText(String.valueOf(output)); //ispisuje rezultat 
                operator = ""; //postavlja vrijednost operatora u nista
                operationStarted = true; //znak da se prijasnja operacija izvrsila i da moze poceti nova
            
        }
        
    }
    

    
}
