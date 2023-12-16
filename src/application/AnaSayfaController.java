package application;


import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.İsteMySQL.util.VeritabınıUtilJava;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
//import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class AnaSayfaController {

	public AnaSayfaController()
	{
		baglantı=VeritabınıUtilJava.Baglan();
	}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<kayıtar_İşlemler> TableView_;

    @FXML
    private TableColumn<kayıtar_İşlemler, Integer> Table_Fİyat;

    @FXML
    private TableColumn<kayıtar_İşlemler, Integer> Table_ID;

    @FXML
    private TableColumn<kayıtar_İşlemler, String> Table_Marka;

    @FXML
    private TableColumn<kayıtar_İşlemler, String> Table_Model;

    @FXML
    private TableColumn<kayıtar_İşlemler, Date> Table_Tarih;

    @FXML
    private TableColumn<kayıtar_İşlemler, String> Table_Özellikler;

    @FXML
    private Button btn_satın;

    @FXML
    private TextField txt_link;

    @FXML
    private TextField txt_link1;

    @FXML
    private TextField txt_link2;
    @FXML
    private TextField txt_ad;
    @FXML
    void TableView_Click(ActionEvent event) {

    }


    Connection baglantı=null;
    PreparedStatement sorguifadesi=null;
    ResultSet getirilen=null;
    String sql;
    
    
    public void degerlergetir(TableView<kayıtar_İşlemler>table)
    {
    	sql="SELECT * FROM telefonlar";
    	ObservableList<kayıtar_İşlemler> kayıtlargetir=FXCollections.observableArrayList();
    	try {
    		sorguifadesi=baglantı.prepareStatement(sql);
    		ResultSet getirilen=sorguifadesi.executeQuery();
    		while(getirilen.next())
    		{
    			kayıtlargetir.add(new kayıtar_İşlemler(getirilen.getInt("ID"),getirilen.getString("Marka"),getirilen.getString("Model"),getirilen.getString("Hakkında"),getirilen.getInt("Fiyatı"),getirilen.getDate("Tarih")));
    		}
    		
    		Table_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
    		Table_Marka.setCellValueFactory(new PropertyValueFactory<>("sutun_marka"));
    		Table_Model.setCellValueFactory(new PropertyValueFactory<>("sutun_model"));
    		Table_Fİyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
    		Table_Özellikler.setCellValueFactory(new PropertyValueFactory<>("sutun_Öz"));
    		Table_Tarih.setCellValueFactory(new PropertyValueFactory<>("tarih"));
    		TableView_.setItems(kayıtlargetir);    		
    	}
    	catch(Exception e) 
    	{
    		System.out.println(e.getMessage().toString());
    	}
    	
    }
    
    @FXML
    void TableView_MouseClick(MouseEvent event) {
      kayıtar_İşlemler kayıt=new kayıtar_İşlemler();
      kayıt=(kayıtar_İşlemler) TableView_.getItems().get(TableView_.getSelectionModel().getSelectedIndex());
      txt_link.setText(kayıt.getSutun_marka());
      txt_link1.setText(kayıt.getSutun_model());
      txt_link2.setText(String.valueOf(kayıt.getFiyat()));
     // txt_area.setText(kayıt.getSutun_Öz());
      
    }

    
    @FXML
    void btn_satın_Click(ActionEvent event) {
      sql="insert into satın (Ad,Marka,Model,Fiyatı) values (?,?,?,?)";
    	 
        
       try
       { sorguifadesi=baglantı.prepareStatement(sql);
        sorguifadesi.setString(1, txt_ad.getText().trim());
		sorguifadesi.setString(2,  txt_link.getText().trim());
		sorguifadesi.setString(3, txt_link1.getText().trim());
		sorguifadesi.setInt(4,Integer.valueOf( txt_link2.getText().trim()));
		sorguifadesi.executeUpdate();
		try {Alert alert=new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Satın Alımı");
		alert.setContentText(" Emin misiniz?");
		alert.setTitle("dddddd");
		alert.showAndWait();}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage().toString());
    	}
		System.out.println("Satın Alındı");

       }
       catch(Exception e)
       {
    	   System.out.println(e.getMessage().toString());
       }
    }

    @FXML
    void initialize() {
        
       degerlergetir(TableView_);
       txt_ad.setText(LoginController.gr);
    }

}
