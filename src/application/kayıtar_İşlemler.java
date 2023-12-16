package application;

import java.util.Date;

public class kayıtar_İşlemler {

	private int id;
	private String sutun_marka;
	private String sutun_model;
	private String sutun_Öz;
	private int fiyat;
	private Date tarih;
    public kayıtar_İşlemler()
	{
		
	}
    
	
	public kayıtar_İşlemler(int id,String marka,String model,String öz,int fiyat,Date tarih)
	{
		this.id=id;
		this.sutun_marka=marka;
		this.sutun_model=model;
		this.sutun_Öz=öz;
		this.fiyat=fiyat;
		this.tarih=tarih;
	}
	public kayıtar_İşlemler(String marka,String model,String öz,int fiyat,Date tarih)
	{
		
		this.sutun_marka=marka;
		this.sutun_model=model;
		this.sutun_Öz=öz;
		this.fiyat=fiyat;
		this.tarih=tarih;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSutun_marka() {
		return sutun_marka;
	}
	public void setSutun_marka(String sutun_marka) {
		this.sutun_marka = sutun_marka;
	}
	public String getSutun_model() {
		return sutun_model;
	}
	public void setSutun_model(String sutun_model) {
		this.sutun_model = sutun_model;
	}
	public String getSutun_Öz() {
		return sutun_Öz;
	}
	public void setSutun_Öz(String sutun_Öz) {
		this.sutun_Öz = sutun_Öz;
	}
	public int getFiyat() {
		return fiyat;
	}
	public void setFiyat(int fiyat) {
		this.fiyat = fiyat;
	}
	public Date getTarih() {
		return tarih;
	}
	public void setTarih(Date tarih) {
		this.tarih = tarih;
	}
	
	
}
