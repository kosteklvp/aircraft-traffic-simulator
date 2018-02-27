package pack.v2;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Samolot extends JFrame {

/////////////////////////////////////////////zmienne//////////////////////////////////////////////////////////
	
	public int idSamolotu;
	public int zbiornik;

	public boolean czyZywy;

	public int idMiastaNastepne;
	public int idMiastaObecne;
	
	public JLabel samolotG = null;
	
	static int przesX= -37;
	static int przesY= -20;
	
	
////////////////////////////////////////////////konstruktor/////tostring///////////////////////////////////////////////////////////
	
	// konstruktor
	public Samolot(int index) {
		this.idSamolotu = index;
		this.idMiastaObecne = index;
		
		this.zbiornik = Samolot.generujZbiornik();
		this.czyZywy = true;
		//rysowanie samolotu
		this.samolotG = new JLabel(" " + String.valueOf(idSamolotu-1) + "*");
		samolotG.setBounds((int)Miasto.miasto[idMiastaObecne-1].osX + przesX,(int) Miasto.miasto[idMiastaObecne-1].osY + przesY, 200, 50);

		samolotG.setForeground(new Color(255,85,25));
		samolotG.setFont(new Font("Courier New",Font.BOLD,26));
	}

	//tostring
	public String toString() {
		return "Samolot [zbiornik=" + zbiornik + ", miastoStartowe=" + Miasto.miasto[idMiastaObecne - 1].nazwa + "]";
	}

////////////////////////////////////////////metody///////////////////////////////////////////////////////////////////
	

	// generwoanie pojemnosci zbiornika
	public static int generujZbiornik() {
		Random rand = new Random();
		return rand.nextInt(450) + 350; 

	}

	//generowanie miasta nastepnego
	public static int generujMiastoNastepne(int idObecnego) {
		Random rand = new Random();
		int a = rand.nextInt(10) + 1;

		while (a == idObecnego) {
			a = rand.nextInt(10) + 1;
		}
		return a;
	}

}
