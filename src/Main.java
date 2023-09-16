import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	
	
	
	public static void main(String[] args ) {
		
		VTSistemi db = new VTSistemi();
		
		ArrayList<Ogrenci> ogrenciListesi = new ArrayList<Ogrenci>();
		
		
		//db.baglan();
		//db.ogrenciListesi(ogrenciListesi);
		/**/
		
		
		
		//db.ekle(ogrenciListesi);
		
		
		boolean working = true;
		
		Scanner s = new Scanner(System.in);
		int islem = -1;
		while(working) {
			
			giris();
			
			islem = s.nextInt();
			switch(islem) {
			
			case 1:
				db.ogrenciListesi(ogrenciListesi);
				liste(ogrenciListesi);
				System.out.println(" ");
				
				break;
			case 2:
				db.ekle(ogrenciListesi);
				break;
				
			case 3:
				db.sil();
				break;
			case 4:
				db.guncelle();
				break;
			case 5:
				working = false;
				break;
				default:
					System.err.println("Hatalı işlem numarası");
				break;
				
				
			}
			
			
		}
		
		
		
	}
	
	static void giris() {
		System.out.println("1. Öğrenci Listesi");
		System.out.println("2. Öğrenci ekle");
		System.out.println("3. Öğrenci sil");
		System.out.println("4. Öğrenci güncelle");
		
		System.out.println("5. Progrmaı sonlandır");
	}
	
	static void liste(ArrayList<Ogrenci> ogrenciListesi) {
		for(int i = 0; i< ogrenciListesi.size(); i++) {
			System.out.print(ogrenciListesi.get(i).getOkulNo()+ " ");
			System.out.print(ogrenciListesi.get(i).getAdi() +  " " );
			System.out.print(ogrenciListesi.get(i).getSoyadi() + " ");
			System.out.println(" ");

		}
		
	}
	
	

}
