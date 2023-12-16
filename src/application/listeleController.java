package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.İsteMySQL.util.VeritabınıUtilJava;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class listeleController {

	public listeleController()
	{
		baglantı=VeritabınıUtilJava.Baglan();
	}
    @FXML
    private ResourceBundle resources;
    @FXML
    private Button txt_t;

    @FXML
    private URL location;

    @FXML
    private Button btn_ara1;

    @FXML
    private Button btnn;
    @FXML
    private TableView<Kayıt_Satıs> tableview;

    @FXML
    private TableColumn<Kayıt_Satıs, Integer> tbl_fiyat;

    @FXML
    private TableColumn<Kayıt_Satıs, Integer> tbl_id;

    @FXML
    private TableColumn<Kayıt_Satıs, String> tbl_kul;

    @FXML
    private TableColumn<Kayıt_Satıs, String> tbl_marka;

    @FXML
    private TableColumn<Kayıt_Satıs, String> tbl_model;
    
    @FXML
    private TextField txt_ara;
    @FXML
    private TextField txt_fiyatı;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_kul;

    @FXML
    private TextField txt_marka;

    @FXML
    private TextField txt_model;
    
    public void temizle()
    {
    	txt_id.clear();
    	txt_marka.clear();
    	txt_kul.clear();
    	txt_model.clear();
    	txt_fiyatı.clear();
    }

    @FXML
    void txt_t_click(ActionEvent event) {
       temizle();
    }

    @FXML
    void tableview_click(ActionEvent event) {

    }

    @FXML
    void tableviewmouse(MouseEvent event) {
    	 Kayıt_Satıs kayıt=new Kayıt_Satıs();
         kayıt=(Kayıt_Satıs) tableview.getItems().get(tableview.getSelectionModel().getSelectedIndex());
        txt_id.setText(String.valueOf(kayıt.getId()));
        txt_kul.setText(kayıt.getKul_ad());
        txt_marka.setText(kayıt.getMarka());
        txt_model.setText(kayıt.getModel());
        txt_fiyatı.setText(String.valueOf(kayıt.getFiyat()));
    }
    @FXML
    void btn_ara(ActionEvent event) {
     //sql="select * from satın where Ad like '%\"+txt_ara.getText()+\"%'\"";
    // getir(tableview,sql);
     
    }
    
    @FXML
    void txt_araclick(ActionEvent event) {
    	if(txt_ara.getText()=="")
    	{ sql="select * from satın";}
    	else { sql="select * from satın where Ad like '%"+txt_ara.getText()+"%' ";}
         getir(tableview,sql);
    	
    }
    @FXML
    void txt_araclickKeyPressed(KeyEvent event) {
    	if(txt_ara.getText()=="")
    	{ sql="select * from satın";}
    	else { sql="select * from satın where Ad like '%"+txt_ara.getText()+"%' ";}
         getir(tableview,sql);
    	
    }

    Connection baglantı=null;
    PreparedStatement sorguifadesi=null;
    ResultSet getirilen=null;
    String sql;
    
    public void getir(TableView<Kayıt_Satıs>table ,String sql)
    {
   	   
    	ObservableList<Kayıt_Satıs> kayıtlargetir=FXCollections.observableArrayList();
    	
    	try {
    		sorguifadesi=baglantı.prepareStatement(sql);
    		ResultSet getirilen=sorguifadesi.executeQuery(sql);
    		while(getirilen.next())
    		{
    			
    			kayıtlargetir.add(new Kayıt_Satıs(getirilen.getInt("id"),getirilen.getString("Ad"),getirilen.getString("Marka"),getirilen.getString("Model"),getirilen.getInt("Fiyatı")));
    		}
    		tbl_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    		tbl_kul.setCellValueFactory(new PropertyValueFactory<>("kul_ad"));
    		tbl_marka.setCellValueFactory(new PropertyValueFactory<>("marka"));
    		tbl_model.setCellValueFactory(new PropertyValueFactory<>("model"));
    		tbl_fiyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
    		tableview.setItems(kayıtlargetir); 
    		
    	}
    	catch(Exception e)
    	{System.out.println(e.getMessage().toString());}
    	
    	
    
    }
    
    @FXML
    void btn_geri_click(ActionEvent event)  throws IOException{
    	//btnn.getScene().getWindow().hide();
    	Stage unut=new Stage();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("AdminAnasayfa.fxml"));
    	Scene scene=new Scene(root); 
    	unut.setScene(scene);
    	unut.show();
    	unut.setResizable(false);
    }
    @FXML
    void initialize() {
    	 sql="SELECT * FROM `satın`";
       getir(tableview,sql);
    }

}
