package pack.v2;

import java.io.IOException;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Main extends JFrame {

	//ilosc samolotw
	final static int N = 10; 

	static Watek[] watek = new Watek[10];
	static WatekG watekg = new WatekG(watek);
	static Grafika grafika = null;
	
	

	
	public static void main(String[] args) throws IOException {

		Miasto.generujMiasta();

		for (int i = 0; i < N; i++) {
			watek[i] = new Watek(i + 1);
			watek[i].start();
		}

		watekg.run();
		

	}

}


