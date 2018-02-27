package pack.v2;

import java.io.IOException;

public class WatekG extends Thread {

	// tablica samolotow watkow
	Watek[] twatek = null;

	// konstruktor
	public WatekG(Watek[] twatek) {
		this.twatek = twatek;
	}

	public void run()
	{
		
		
		try {
			 Main.grafika = new Grafika();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//dodawanie samolotu do grafiki
		for(int i=0;i<Main.N;i++) {
			Main.grafika.add(twatek[i].samolot.samolotG);	
		
		}
	}

}
