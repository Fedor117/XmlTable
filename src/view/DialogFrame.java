package view;

import java.awt.Dimension;

import javax.swing.JFrame;

import controller.StudentTableController;

public class DialogFrame extends JFrame {

	private StudentTableController controller;

	public DialogFrame(MainWindow inputMainWindow,
			StudentTableController inputController) {
		Dimension dimension = inputMainWindow.getSize();
		setPreferredSize(dimension);
		setLocation(inputMainWindow.getLocation());
		controller = inputController;
	}

	public StudentTableController getController() {
		return controller;
	}

}
