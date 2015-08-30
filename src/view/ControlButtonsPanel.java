package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.StudentTableController;
import view.dialogs.AverageScoreDialog;
import view.dialogs.FindRemoveDialog;
import view.dialogs.GroupAndSurnameDialog;
import view.dialogs.SurnameAndScoreDialog;

public class ControlButtonsPanel extends JPanel {

	private DialogFrame dialogFrame;

	private JButton addStudentButton;

	public ControlButtonsPanel(DialogFrame inputDialogFrame,
			StudentTableController studentTableController) {
		dialogFrame = inputDialogFrame;

		JButton openButton = new JButton("Read XML base");
		add(openButton);
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				studentTableController.read("table.xml");
			}
		});

		add(Box.createVerticalStrut(10));

		JButton saveButton = new JButton("Save to XML base");
		add(saveButton);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				studentTableController.write("table.xml");
			}

		});

		add(Box.createVerticalStrut(10));

		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(boxLayout);
		addStudentButton = new JButton();
		addStudentButton.setText("Add new student");
		addStudentButton.addActionListener(new AddStudentButtonListener());
		add(addStudentButton);

		add(Box.createVerticalStrut(10));

		JButton groupSurnameButton = new JButton("The average score");
		add(groupSurnameButton);
		groupSurnameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindRemoveDialog findRemoveDialog = new AverageScoreDialog(
						dialogFrame, studentTableController);
			}
		});

		add(Box.createVerticalStrut(10));

		JButton surnameKindButton = new JButton("Group and surname");
		add(surnameKindButton);
		surnameKindButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindRemoveDialog findRemoveDialog = new GroupAndSurnameDialog(
						dialogFrame, studentTableController);
			}
		});

		add(Box.createVerticalStrut(10));

		JButton surnameCountersButton = new JButton("Surname and average score");
		add(surnameCountersButton);
		surnameCountersButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				FindRemoveDialog findRemoveDialog = new SurnameAndScoreDialog(
						dialogFrame, studentTableController);
			}
		});

	}

	private class AddStudentButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			AddingStudentDialogView addingStudentDialogView = new AddingStudentDialogView(
					dialogFrame);
		}
	}
}