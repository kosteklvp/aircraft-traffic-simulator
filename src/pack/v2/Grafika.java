package pack.v2;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Grafika extends JFrame {

	public Grafika() throws IOException {
		//t�o
	    setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File(".\\.\\resources\\radar2.jpg")))));
		//ustawienia aplikacji
	    setSize(836, 740);
	    setTitle("Aircraft traffic simulator");
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		//tworzenie miast w aplikacji
		JLabel[] label = new JLabel[10];

		for (int i = 0; i < 10; i++) {
			label[i] = new JLabel("o" + Miasto.miasto[i].nazwa);
			label[i].setBounds(Miasto.miasto[i].osX + Miasto.przesX, Miasto.miasto[i].osY + Miasto.przesY, 200, 50);
			label[i].setForeground(new Color(255, 255, 255));
			label[i].setFont(new Font("Courier New", Font.BOLD, 21));
			add(label[i]);

		}
		
	}

}
