package application;

public class Kayıt_Satıs {

	private int id;
	private String kul_ad;
	private String marka;
	private String model;
	private int fiyat;
	
	public Kayıt_Satıs()
	{}
	public Kayıt_Satıs(int id, String ad,String marka,String model,int fiyat)
	{
		this.id=id;
		this.kul_ad=ad;
		this.marka=marka;
		this.model=model;
		this.fiyat=fiyat;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKul_ad() {
		return kul_ad;
	}
	public void setKul_ad(String kul_ad) {
		this.kul_ad = kul_ad;
	}
	public String getMarka() {
		return marka;
	}
	public void setMarka(String marka) {
		this.marka = marka;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getFiyat() {
		return fiyat;
	}
	public void setFiyat(int fiyat) {
		this.fiyat = fiyat;
	}
	
	
	
	
}
