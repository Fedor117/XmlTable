package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainWindow extends JFrame {

	public MainWindow() {
		setTitle("Table");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenDimension = toolkit.getScreenSize();
		setLocation(screenDimension.width / 5, screenDimension.height / 5);
		setSize(screenDimension.width / 5 * 3, screenDimension.height / 5 * 3);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
