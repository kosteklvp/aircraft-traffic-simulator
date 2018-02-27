package pack.v2;

public class Watek extends Thread {

	// deklaracja zmiennych
	int odleglosc = 0;
	int odlDzielona = 0, odlX = 0, odlY = 0, odlXS = 0, odlYS = 0, odlegloscX = 0, odlegloscY = 0;

	final int PRZESUW = 32;
	final int TANK = 5;
	final int SLEEP = 125;

	int index;
	int zbiornik = 0;

	Samolot samolot = null;

	// konstruktor watku
	public Watek(int i) {
		index = i;
	}

	public void run() {

		// generowanie samolotu
		samolot = new Samolot(index);
		System.out.println("Wygenerowano samolot numer " + samolot.idSamolotu + " w mieœcie "
				+ Miasto.miasto[samolot.idMiastaObecne - 1].nazwa);

		try {
			sleep(SLEEP);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while (samolot.czyZywy == true) {

			// generowanie miasta nastepnego
			samolot.idMiastaNastepne = Samolot.generujMiastoNastepne(samolot.idMiastaObecne);
			System.out.println("Wygenerowano miasto nastepne dla samolot numer " + samolot.idSamolotu + ": "
					+ Miasto.miasto[samolot.idMiastaNastepne - 1].nazwa);

			try {
				Thread.sleep(SLEEP);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// dodawanie samolotu do miasta
			Miasto.miasto[samolot.idMiastaObecne - 1].czySamolot = true;

			// liczenie odleglosci
			odleglosc = Miasto.generujOdleglosc(Miasto.miasto[samolot.idMiastaObecne - 1].osX,
					Miasto.miasto[samolot.idMiastaObecne - 1].osY, Miasto.miasto[samolot.idMiastaNastepne - 1].osX,
					Miasto.miasto[samolot.idMiastaNastepne - 1].osY);

			// liczenie odleglosci wedlug osi x i y
			odlDzielona = odleglosc / PRZESUW;
			if (odlDzielona < 1)
				odlDzielona = 1;

			odlX = Math.abs(
					Miasto.miasto[samolot.idMiastaObecne - 1].osX - Miasto.miasto[samolot.idMiastaNastepne - 1].osX)
					/ odlDzielona;
			odlY = Math.abs(
					Miasto.miasto[samolot.idMiastaObecne - 1].osY - Miasto.miasto[samolot.idMiastaNastepne - 1].osY)
					/ odlDzielona;

			odlegloscX = Math.abs(
					Miasto.miasto[samolot.idMiastaObecne - 1].osX - Miasto.miasto[samolot.idMiastaNastepne - 1].osX);
			odlegloscY = Math.abs(
					Miasto.miasto[samolot.idMiastaObecne - 1].osY - Miasto.miasto[samolot.idMiastaNastepne - 1].osY);

			odlXS = odlX;
			odlYS = odlY;

			System.out.println("Odleg³oœæ dla samolot numer " + samolot.idSamolotu + " z miasta "
					+ Miasto.miasto[samolot.idMiastaObecne - 1].nazwa + " do miasta "
					+ Miasto.miasto[samolot.idMiastaNastepne - 1].nazwa + " wynosi: " + odleglosc);

			try {
				Thread.sleep(SLEEP);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// tankowanie i zamiana na mapie na t
			samolot.samolotG.setText("t" + String.valueOf(samolot.idSamolotu - 1) + "*");

			while (zbiornik < samolot.zbiornik) {

				// wypisywanie ile zatankowalo
				if (zbiornik + TANK > samolot.zbiornik)
					zbiornik = samolot.zbiornik;
				else
					zbiornik = zbiornik + TANK;

				System.out.println("Samolot numer " + samolot.idSamolotu + " zosta³ zatankowany do " + zbiornik + "/"
						+ samolot.zbiornik);

				try {
					Thread.sleep(SLEEP);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			// koniec tankowania
			samolot.samolotG.setText(" " + String.valueOf(samolot.idSamolotu - 1) + "*");
			// wypisanie zbiornika
			System.out.println("Samolot numer " + samolot.idSamolotu + " zosta³ zatankowany do pe³na");

			// wypisanie startowanie + skad dokad
			System.out.println("Samolot numer " + samolot.idSamolotu + " wystartowal z "
					+ Miasto.miasto[samolot.idMiastaObecne - 1].nazwa + " do "
					+ Miasto.miasto[samolot.idMiastaNastepne - 1].nazwa);

			// usuniecie samolotu z maista
			Miasto.miasto[samolot.idMiastaObecne - 1].czySamolot = false;

			// LOT SAMOLOTU
			while (odleglosc >= 0) {

				// wypisanie ile zostalo
				System.out.println("Samolotowi numer " + samolot.idSamolotu + " zostalo do pokonania " + odleglosc);

				try {
					Thread.sleep(SLEEP);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// odejmowanie odleglosci
				odleglosc = odleglosc - PRZESUW;

				// PRZESUWANIE
				if (Miasto.miasto[samolot.idMiastaObecne - 1].osX <= Miasto.miasto[samolot.idMiastaNastepne - 1].osX
						&& Miasto.miasto[samolot.idMiastaObecne - 1].osY <= Miasto.miasto[samolot.idMiastaNastepne
								- 1].osY) {
					if (odleglosc > PRZESUW) {
						samolot.samolotG.setBounds(
								Miasto.miasto[samolot.idMiastaObecne - 1].osX + Samolot.przesX + odlX,
								Miasto.miasto[samolot.idMiastaObecne - 1].osY + Samolot.przesY + odlY, 200, 50);
						odlX = odlX + odlXS;
						odlY = odlY + odlYS;
					} else if (odleglosc <= PRZESUW) {
						while (Miasto.miasto[samolot.idMiastaNastepne - 1].czySamolot == true) {
							samolot.samolotG.setText("c" + String.valueOf(samolot.idSamolotu - 1) + "*");
							try {
								Thread.sleep(SLEEP);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						samolot.samolotG.setBounds(Miasto.miasto[samolot.idMiastaNastepne - 1].osX + Samolot.przesX,
								Miasto.miasto[samolot.idMiastaNastepne - 1].osY + Samolot.przesY, 200, 50);
						
					}
				}

				if (Miasto.miasto[samolot.idMiastaObecne - 1].osX <= Miasto.miasto[samolot.idMiastaNastepne - 1].osX
						&& Miasto.miasto[samolot.idMiastaObecne - 1].osY >= Miasto.miasto[samolot.idMiastaNastepne
								- 1].osY) {
					if (odleglosc > PRZESUW) {
						samolot.samolotG.setBounds(
								Miasto.miasto[samolot.idMiastaObecne - 1].osX + Samolot.przesX + odlX,
								Miasto.miasto[samolot.idMiastaObecne - 1].osY + Samolot.przesY - odlY, 200, 50);
						odlX = odlX + odlXS;
						odlY = odlY + odlYS;
					} else if (odleglosc <= PRZESUW) {
						while (Miasto.miasto[samolot.idMiastaNastepne - 1].czySamolot == true) {
							samolot.samolotG.setText("c" + String.valueOf(samolot.idSamolotu - 1) + "*");
							try {
								Thread.sleep(SLEEP);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						samolot.samolotG.setBounds(Miasto.miasto[samolot.idMiastaNastepne - 1].osX + Samolot.przesX,
								Miasto.miasto[samolot.idMiastaNastepne - 1].osY + Samolot.przesY, 200, 50);
					
					}
				}

				if (Miasto.miasto[samolot.idMiastaObecne - 1].osX >= Miasto.miasto[samolot.idMiastaNastepne - 1].osX
						&& Miasto.miasto[samolot.idMiastaObecne - 1].osY <= Miasto.miasto[samolot.idMiastaNastepne
								- 1].osY) {
					if (odleglosc > PRZESUW) {
						samolot.samolotG.setBounds(
								Miasto.miasto[samolot.idMiastaObecne - 1].osX + Samolot.przesX - odlX,
								Miasto.miasto[samolot.idMiastaObecne - 1].osY + Samolot.przesY + odlY, 200, 50);
						odlX = odlX + odlXS;
						odlY = odlY + odlYS;
					} else if (odleglosc <= PRZESUW) {
						while (Miasto.miasto[samolot.idMiastaNastepne - 1].czySamolot == true) {
							samolot.samolotG.setText("c" + String.valueOf(samolot.idSamolotu - 1) + "*");
							try {
								Thread.sleep(SLEEP);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						samolot.samolotG.setBounds(Miasto.miasto[samolot.idMiastaNastepne - 1].osX + Samolot.przesX,
								Miasto.miasto[samolot.idMiastaNastepne - 1].osY + Samolot.przesY, 200, 50);
						
					}

				}

				if (Miasto.miasto[samolot.idMiastaObecne - 1].osX >= Miasto.miasto[samolot.idMiastaNastepne - 1].osX
						&& Miasto.miasto[samolot.idMiastaObecne - 1].osY >= Miasto.miasto[samolot.idMiastaNastepne
								- 1].osY) {
					if (odleglosc > PRZESUW) {
						samolot.samolotG.setBounds(
								Miasto.miasto[samolot.idMiastaObecne - 1].osX + Samolot.przesX - odlX,
								Miasto.miasto[samolot.idMiastaObecne - 1].osY + Samolot.przesY - odlY, 200, 50);
						odlX = odlX + odlXS;
						odlY = odlY + odlYS;
					} else if (odleglosc <= PRZESUW) {
						while (Miasto.miasto[samolot.idMiastaNastepne - 1].czySamolot == true) {
							samolot.samolotG.setText("c" + String.valueOf(samolot.idSamolotu - 1) + "*");
							try {
								Thread.sleep(SLEEP);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
						samolot.samolotG.setBounds(Miasto.miasto[samolot.idMiastaNastepne - 1].osX + Samolot.przesX,
								Miasto.miasto[samolot.idMiastaNastepne - 1].osY + Samolot.przesY, 200, 50);
						
					}
				}

				// odejmowanie paliwa
				if (odleglosc > PRZESUW)
					zbiornik = zbiornik - PRZESUW;
				else
					zbiornik = zbiornik - odleglosc;

				// czy samolot umarl
				if (zbiornik <= 0) {
					samolot.czyZywy = false;
					break;
				}
			}

			// sprawdzanie czy samolot dolecial do celu

			// dolecial, dodawanie samlotu do miasta, zamiana na nowe miasto
			if (samolot.czyZywy == true) {
				System.out.println("Samolot numer " + samolot.idSamolotu + " dolecial z "
						+ Miasto.miasto[samolot.idMiastaObecne - 1].nazwa + " do "
						+ Miasto.miasto[samolot.idMiastaNastepne - 1].nazwa);
				samolot.idMiastaObecne = samolot.idMiastaNastepne;
				Miasto.miasto[samolot.idMiastaObecne - 1].czySamolot = true;

				// samolot nie dolecial
			} else {
				System.out.println("Samolot nie dolecial :(");
				samolot.samolotG.setText("!" + String.valueOf(samolot.idSamolotu - 1) + "*");
			}
		}

	}

}
