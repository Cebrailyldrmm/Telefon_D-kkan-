package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminAnasayfaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_ekle;

    @FXML
    private Button btn_toplam;
    @FXML
    private Button btn_kpt;
    @FXML
    private AnchorPane scnePane;


    @FXML
    void btn_kpt_click(ActionEvent event) {
    	Stage stage=(Stage) scnePane.getScene().getWindow();
    	stage.close();
    }
    @FXML
    void btn_ekle_click(ActionEvent event) throws IOException{
    	btn_ekle.getScene().getWindow().hide();
    	Stage unut=new Stage();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Ekle-Cıkar.fxml"));
    	Scene scene=new Scene(root); 
    	unut.setScene(scene);
    	unut.show();
    	unut.setResizable(false);
    }

    @FXML
    void btn_toplam_click(ActionEvent event)throws IOException {
    	btn_toplam.getScene().getWindow().hide();
    	Stage unut=new Stage();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("listele.fxml"));
    	Scene scene=new Scene(root); 
    	unut.setScene(scene);
    	unut.show();
    	unut.setResizable(false);
    }

    @FXML
    void initialize() {
       
    }

}
