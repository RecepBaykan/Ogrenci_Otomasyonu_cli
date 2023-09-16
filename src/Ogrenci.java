
public class Ogrenci {
	
	private int okulNo;
	private String adi;
	private String soyadi;
	
	
	public int getOkulNo() {
		
		return okulNo;
	}
	
	public void setOkulNo(int no) {
		
		if(no<0) {
			okulNo = -1;
		}else {
			okulNo = no;
		}
	}
	
	
	public String getAdi() {
		
		return adi;
	}
	
	public void setAdi(String s) {
		
		adi = s;
	}
	
	public String getSoyadi() {
		
		return soyadi;
	}
	
	public void setSoyadi(String s) {
		
		soyadi = s;
	}
	


}
