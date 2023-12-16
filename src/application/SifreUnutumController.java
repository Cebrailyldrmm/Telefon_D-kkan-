package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Random;
import com.İsteMySQL.util.VeritabınıUtilJava;
public class SifreUnutumController {
public SifreUnutumController()
{
	baglantı=VeritabınıUtilJava.Baglan();	

}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_save;

    @FXML
    private Label lbl_kod;

    @FXML
    private TextField txt_kod;

    @FXML
    private TextField txt_kul;

    @FXML
    private TextField txt_tşif;

    @FXML
    private TextField txt_yşif;

    Connection baglantı=null;
    PreparedStatement sorguifadesi=null;
    ResultSet getirilen=null;
    String sql;
    
    @FXML
    void btn_save_click(ActionEvent event) throws SQLException {
       sql="update login set Sifre=? where Kul_Ad=?";
        
       
    	   sorguifadesi=baglantı.prepareStatement(sql);
    	   sorguifadesi.setString(1, VeritabınıUtilJava.MD5Sifrele(txt_yşif.getText().trim()));
    	   sorguifadesi.setString(2, txt_kul.getText().trim());
    	   sorguifadesi.executeUpdate();
    	   try {Alert alert=new Alert(AlertType.CONFIRMATION);
   		alert.setHeaderText("Sıfre değistirme");
   		alert.setContentText(" Emin misiniz?");
   		alert.setTitle("şifre değistirme");
   		alert.showAndWait();}
       	catch(Exception e)
       	{
       		System.out.println(e.getMessage().toString());
       	}
    	   System.out.println("Şifre Güncelleşti");
    	  try { btn_save.getScene().getWindow().hide();
         	Stage unut=new Stage();
           	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Login.fxml"));
           	Scene scene=new Scene(root); 
           	unut.setScene(scene);
           	unut.show();
           	unut.setResizable(false);}
catch(Exception e)
    	  {System.out.println(e.getMessage().toString());}
    }
    
	int sum=0;
    void initialize() {
    	int sayı1=10,sayı2=14;
        Random rdn= new Random();
        sayı1=rdn.nextInt(100);
        sayı2=rdn.nextInt(100);
        sum=sayı1+sayı2;
        lbl_kod.setText(sayı1+"+"+sayı2+" =");
        
     
    }

}
