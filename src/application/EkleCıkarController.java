package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.İsteMySQL.util.VeritabınıUtilJava;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EkleCıkarController {
public EkleCıkarController()
{
	baglantı=VeritabınıUtilJava.Baglan();
	
	}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_Güncelle;

    @FXML
    private Button btn_ekle;

    @FXML
    private Button btn_sil;
    @FXML
    private Button btn_geri;
    @FXML
    private DatePicker date_tarih;

    @FXML
    private TableView<kayıtar_İşlemler> tableview;

    @FXML
    private TableColumn<kayıtar_İşlemler, Integer> tbl_fiyat;
    @FXML
    private TableColumn<kayıtar_İşlemler, Integer> tbl_id;

    @FXML
    private TableColumn<kayıtar_İşlemler, String> tbl_hak;

    @FXML
    private TableColumn<kayıtar_İşlemler, String> tbl_marka;

    @FXML
    private TableColumn<kayıtar_İşlemler, String> tbl_model;

    @FXML
    private TableColumn<kayıtar_İşlemler, Date> tbl_tarih;

    @FXML
    private TextField txt_fiyat;

    @FXML
    private TextField txt_gün;

    @FXML
    private TextArea txt_hak; 
    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_marka;

    @FXML
    private TextField txt_model;
    
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
    		tbl_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    		tbl_marka.setCellValueFactory(new PropertyValueFactory<>("sutun_marka"));
    		tbl_model.setCellValueFactory(new PropertyValueFactory<>("sutun_model"));
    		tbl_fiyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
    		tbl_hak.setCellValueFactory(new PropertyValueFactory<>("sutun_Öz"));
    	    tbl_tarih.setCellValueFactory(new PropertyValueFactory<>("tarih"));
    	    tableview.setItems(kayıtlargetir); 
    	}
    	catch(Exception e) 
    	{
    		System.out.println(e.getMessage().toString());
    	}
    	
    }
    
    
    @FXML
    void btn_Güncelle(ActionEvent event)  throws SQLException {
    	  sql="update telefonlar set Fiyatı=? where ID=?";
          
          
   	   sorguifadesi=baglantı.prepareStatement(sql);
   	   sorguifadesi.setString(1, txt_gün.getText().trim());
   	   sorguifadesi.setString(2, txt_id.getText().trim());
   	   sorguifadesi.executeUpdate();
       degerlergetir(tableview);

	   try {Alert alert=new Alert(AlertType.CONFIRMATION);
   		alert.setHeaderText("Fiyat Güncelleme");
   		alert.setContentText(" Emin misiniz?");
   		alert.setTitle("Telefon Güncelleme");
   		alert.showAndWait();}
       	catch(Exception e)
       	{
       		System.out.println(e.getMessage().toString());
       	}
    	   System.out.println("Guncelleme Yapıldı");
    }

    @FXML
    void btn_ekle_Click(ActionEvent event) throws SQLException {
       sql="insert into telefonlar (Marka,Model,Hakkında,Fiyatı,Tarih) values (?,?,?,?,?)";
    	 
        
        sorguifadesi=baglantı.prepareStatement(sql);
        sorguifadesi.setString(1, txt_marka.getText().trim());
		sorguifadesi.setString(2, txt_model.getText().trim());
		sorguifadesi.setString(3, txt_hak.getText().trim());
		sorguifadesi.setString(4, txt_fiyat.getText().trim());
		sorguifadesi.setString(5, String.valueOf(date_tarih.getValue()));
		sorguifadesi.executeUpdate();
		//((TextField)date_tarih.getEditor()).getText().trim()
		degerlergetir(tableview);
		
		   try {Alert alert=new Alert(AlertType.CONFIRMATION);
	   		alert.setHeaderText("Telefon Ekleme");
	   		alert.setContentText(" Emin misiniz?");
	   		alert.setTitle("Telefon Ekleme");
	   		alert.showAndWait();}
	       	catch(Exception e)
	       	{
	       		System.out.println(e.getMessage().toString());
	       	}
	    	   System.out.println("Ekleme Yapıldı");
    }
	    	 
    int sayı;
    @FXML
    void btn_sil_Click(ActionEvent event) {
    	 ObservableList<kayıtar_İşlemler> seçilen,tumkayıt;
         tumkayıt=tableview.getItems();
         seçilen=tableview.getSelectionModel().getSelectedItems();
         seçilen.forEach(tumkayıt::remove);

		   try {Alert alert=new Alert(AlertType.CONFIRMATION);
	   		alert.setHeaderText("Telefon Silme");
	   		alert.setContentText(" Emin misiniz?");
	   		alert.setTitle("Telefon Silme");
	   		alert.showAndWait();}
	       	catch(Exception e)
	       	{
	       		System.out.println(e.getMessage().toString());
	       	}
	    	   System.out.println("Silme Gerçeklesti");
         
       }
    @FXML
    void btn_geri_click(ActionEvent event) {
    	try
    	{btn_geri.getScene().getWindow().hide();
    	Stage unut=new Stage();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("AdminAnaSayfa.fxml"));
    	Scene scene=new Scene(root); 
    	unut.setScene(scene);
    	unut.show();
    	unut.setResizable(false);}
    	catch(Exception e) {}
    }


    @FXML
    void tableviewMouse(MouseEvent event) {
    	 kayıtar_İşlemler kayıt=new  kayıtar_İşlemler();
         kayıt=( kayıtar_İşlemler) tableview.getItems().get(tableview.getSelectionModel().getSelectedIndex());
         txt_id.setText(String.valueOf(kayıt.getId()));
    }

    @FXML
    void initialize() {
    	degerlergetir(tableview);

    }

}
