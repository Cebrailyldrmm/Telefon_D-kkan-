package application;
import com.İsteMySQL.util.VeritabınıUtilJava;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class LoginController {

	public LoginController()
	{
		baglantı=VeritabınıUtilJava.Baglan();
	}
	 @FXML
	    private ResourceBundle resources1;

	    @FXML
	    private URL location1;

	   @FXML
	    private AnchorPane anchor_Login;

	    @FXML
	    private AnchorPane anchor_Sigin;

	 @FXML
	 private AnchorPane anchor_Unut;

	
    @FXML
    private ResourceBundle resources;
    @FXML
    private Button btn_Unut;
    @FXML
    private URL location;
    @FXML
    private Label lbl_hata;

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_sigin;
    @FXML
    private TextField txt_kod;

    @FXML
    private TextField txt_kul;

    @FXML
    private TextField txt_pass;

    @FXML
    private Label lbl_kod;
    Connection baglantı=null;
    PreparedStatement sorguifadesi=null;
    ResultSet getirilen=null;
    String sql;
    public static String gr="";
    int sum=0;
  public void cagir(String name) throws IOException
    {
    	Stage unut=new Stage();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource(name));
    	Scene scene=new Scene(root); 
    	unut.setScene(scene);
    	unut.show();
    	unut.setResizable(false);
   
    }
    @FXML
    void btn_Unut_click(ActionEvent event) throws IOException {
    	btn_Unut.getScene().getWindow().hide();
    	cagir("SifreUnutum.fxml");
    }
    
    
    @FXML
    void btn_login_click(ActionEvent event) {
    sql="select * from login where Kul_Ad=? and Sifre=?";    	
try {
	sorguifadesi=baglantı.prepareStatement(sql);
	sorguifadesi.setString(1, txt_kul.getText().trim());
	sorguifadesi.setString(2,VeritabınıUtilJava.MD5Sifrele(txt_pass.getText().trim()));

	ResultSet getirilen=sorguifadesi.executeQuery();
	if(!getirilen.next())
	{
		lbl_hata.setText("Kullanıcı veya Şifre Hatalıdır.....!");
		
	}
	else {
		getirilen.getString(1);
		System.out.println("Kid:"+getirilen.getString("K_İd"));
		System.out.println("Kullanıcı:"+getirilen.getString("Kul_Ad"));
		System.out.println("Şifre:"+getirilen.getString("Sifre"));
		System.out.println("Yetki :"+getirilen.getString("Yetki"));
		String yetki=String.valueOf(getirilen.getString("Yetki"));
		int a=Integer.valueOf(getirilen.getString("Yetki"));
		gr=getirilen.getString("Kul_Ad");
		
		if(yetki=="0"||a==0)
		{
			if(sum==Integer.valueOf(txt_kod.getText()))
			cagir("AnaSayfa.fxml");
		}
		else if(yetki=="1"||a==1)
		{
			if(sum==Integer.valueOf(txt_kod.getText()))
			cagir("AdminAnasayfa.fxml");
		}
		else
		{
			System.out.println("hata var");
		}
	}
	
}
catch(Exception e) 
{
	lbl_hata.setText(e.getMessage().toString());
	}
    
    }

    @FXML
    void btn_sigin_click(ActionEvent event) throws IOException {
    	btn_sigin.getScene().getWindow().hide();
    	Stage unut=new Stage();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Sigin.fxml"));
    	Scene scene=new Scene(root); 
    	unut.setScene(scene);
    	unut.show();
    	unut.setResizable(false);
    	
    }
    
    void kod()
    {
    	int sayı1,sayı2;
        Random rdn= new Random();
        sayı1=rdn.nextInt(100);
        sayı2=rdn.nextInt(100);
        sum=sayı1+sayı2;
        lbl_kod.setText(sayı1+"+"+sayı2+" =");
    }
    @FXML
    void initialize() {
    	kod();
        
    }

}
