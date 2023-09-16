import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class VTSistemi {

	String kullaniciAdi = "root";
	String sifre = "1234";
	String url = "jdbc:mysql://localhost:3306/ogrenci";
	Connection connection;

	public void baglan() {

		connection = null;

		try {

			connection = DriverManager.getConnection(url, kullaniciAdi, sifre);

		} catch (SQLException S) {

			System.out.println("Bağlantı başarısız oldu");
			System.out.println(S);

		}
	}

	public void ogrenciListesi(ArrayList<Ogrenci> ogrenciListesi) {
		ogrenciListesi.clear();
		baglan();
		Ogrenci ogr;
		try {

			Statement stat = connection.createStatement();
			ResultSet res;
			res = stat.executeQuery("select OkulNo, adi, soyadi from ogrenciler");

			while (res.next()) {
				ogr = new Ogrenci();
				//System.out.println(res.getString("OkulNo") + " " + res.getString("adi") + " " + res.getString("soyadi"));
				ogr.setAdi(res.getString("adi"));
				ogr.setSoyadi(res.getString("soyadi"));
				ogr.setOkulNo(Integer.parseInt(res.getString("okulNo")));
				ogrenciListesi.add(ogr);
				
				
				
			}
			
			

		} catch (SQLException S) {
			System.out.println("Bağlantı başarısız oldu");
			System.out.println(S);
		}

	}
	
	public void ekle(ArrayList<Ogrenci> ogrenciListesi) {
		
		Ogrenci ogr = new Ogrenci();
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("okul numarası gir");
		int no = 0;
		try {
			no = s.nextInt();
			ogr.setOkulNo(no);
			
		}catch (NumberFormatException e) {
			System.out.println("Öğrenci numarası hatalı, sonra düzeltin");
			no = -1;
			ogr.setOkulNo(no);
		}
		
		System.out.println("adını girin");
		String ad = s.next();
		ogr.setAdi(ad);
		
		System.out.println("soyadını girin");
		String soyad = s.next();
		ogr.setAdi(soyad);
		System.out.println("Başarıyla eklendi");
		ogrenciListesi.add(ogr);
		
		baglan();
		
		try {

			PreparedStatement stat = null;
			ResultSet res;
			String sorgu = "insert into ogrenciler (OkulNo, adi, soyadi) values (?, ?, ?)";
			stat = connection.prepareStatement(sorgu);
			stat.setInt(1, no);
			stat.setString(2, ad);
			stat.setString(3, soyad);
			
			stat.executeUpdate();
			
			stat.close();
			connection.close();
			
			
			
			
			

		} catch (SQLException S) {
			System.out.println("Bağlantı başarısız oldu");
			System.out.println(S);
		}finally {
				
			
			
		}
		
		
		
		
		
		
		
		
	}
	
	public void guncelle() {
		
		System.out.println("Ogrenci numarasını gir");
		Scanner s = new Scanner(System.in);
		int okulNo = -1;
		try {
			okulNo = s.nextInt();
		}catch (Exception e) {
			okulNo = -1;
			System.err.println(e);
		}
		
		
		baglan();
		Ogrenci ogr;
		try {

			Statement stat = connection.createStatement();
			ResultSet res;
			res = stat.executeQuery("select OkulNo, adi, soyadi from ogrenciler");

			while (res.next()) {
			
				if(res.getInt("okulNo") == okulNo) {
					
					System.out.println("Yeni okul numarası gir");
					int no = s.nextInt();
					
					System.out.println("Yeni ad gir");
					String ad = s.next();
					
					System.out.println("Yeni soyad gir");
					String soyad = s.next();
					
					String sorgu = "Update ogrenciler SET OkulNo=?, adi=?, soyadi=? WHERE OkulNo=?";
					PreparedStatement guncelleStat = connection.prepareStatement(sorgu);
					
					guncelleStat.setInt(1, no);
					guncelleStat.setString(2, ad);
					guncelleStat.setString(3, soyad);
					guncelleStat.setInt(4, okulNo);
					
					guncelleStat.close();
					stat.close();
					connection.close();
					break;
					
					
					
					
					
				}
				
			}
			
			System.out.println("Güncellendi");
			
			

		} catch (SQLException S) {
			System.out.println("Bağlantı başarısız oldu");
			System.out.println(S);
		}
		
		
		
	}
	
	
	public void sil() {
		
		Scanner s = new Scanner(System.in);
		System.out.println("Okul numarası");
		int no = s.nextInt();
		
		baglan();
		
		try {

			Statement stat = connection.createStatement();
			ResultSet res;
			res = stat.executeQuery("select OkulNo, adi, soyadi from ogrenciler");

			while (res.next()) {
				
				if(res.getInt("OkulNo") == no) {
					
					String sorgu = "Delete FROM ogrenciler WHERE OkulNo=?";
					PreparedStatement silStat = connection.prepareStatement(sorgu);
					
					silStat.setInt(1, no);
					
					silStat.executeUpdate();
					
					silStat.clearBatch();
					res.close();
					connection.close();
					
					break;
					
					
				}
				
				
			}
			
			System.out.println("Silindi");
		}catch(SQLException S) {
			
			
			System.out.println(s);
		}
	
	}
	
	
	
	
	
	
	
	
}
