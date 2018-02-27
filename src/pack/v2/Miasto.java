package pack.v2;

import java.util.Random;

public class Miasto {

	
/////////////////////////////////////////////zmienne///////////////////////////////////////////////////////////////

	public String nazwa;
	public int idMiasta;

	static int przesX=-3;
	static int przesY=-25;
	public int osX; 
	public int osY;

	public boolean czySamolot;
	
	// tablica dostepnych miast
	public static String dostepneMiasta[] = { "Sulechów", "Buków", "Klêpsk", "Krê¿o³y", "Kije", "Kalsk", "Niekarzyn", "Cibórz", "Kêpsko", "Brzezie" };
	

//////////////////////////////////////////////////konstruktor////tostring//////////////////////////////////////////////////////
	public Miasto(int i) {

		this.osX = Miasto.generujPunkt();
		this.osY = Miasto.generujPunkt();			
		this.idMiasta = i + 1;
		this.nazwa = Miasto.generujNazwe(i, Miasto.dostepneMiasta);
	}

	// tostring
	public String toString() {
		return "Miasto [nazwa=" + nazwa + ", idMiasta=" + idMiasta + ", osX=" + osX + ", osY=" + osY + "]";
	}
	
//////////////////////////////////////////////////metody///////////////////////////////////////////////////////

	// generowanie punktu
	public static int generujPunkt() {
		Random rand = new Random();
		return rand.nextInt(665) + 25;
	}

	// generowanie nazwy
	public static String generujNazwe(int i, String Miasta[]) {
		return Miasta[i];
	}

	// generowanie odleglosci
	public static int generujOdleglosc(int x1, int y1, int x2, int y2) {
		

		return (int) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}

	
	
	
	// generowanie miast + tablica miast
	public static Miasto[] miasto = new Miasto[10];

	public static void generujMiasta() {
		for (int i = 0; i < 10; i++) {
			miasto[i] = new Miasto(i);
			System.out.println(miasto[i]);
			
		}
	}
	

	

}
