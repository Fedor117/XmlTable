package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.StudentTableController;
import model.Student;

public class AddingStudentDialogView extends JDialog {

	private JTextField nameTextField;
	private JTextField surNameTextField;
	private JTextField secondNameTextField;
	private JTextField groupTextField;
	private StudentTableController studentTableController;

	public AddingStudentDialogView(DialogFrame inputDialogFrame) {

		super(inputDialogFrame, "Add a student", true);

		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);

		GridBagConstraints gridBagConstraints = new GridBagConstraints();

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1;
		gridBagConstraints.weighty = 1;
		JLabel nameLable = new JLabel("Name:");
		nameLable.setHorizontalAlignment(JLabel.RIGHT);
		add(nameLable, gridBagConstraints);

		gridBagConstraints.gridx++;
		nameTextField = new JTextField("data", 10);
		add(nameTextField, gridBagConstraints);

		gridBagConstraints.gridx++;
		JLabel surnameLable = new JLabel("Surname:");
		surnameLable.setHorizontalAlignment(JLabel.RIGHT);
		add(surnameLable, gridBagConstraints);

		gridBagConstraints.gridx++;
		surNameTextField = new JTextField("data", 10);
		add(surNameTextField, gridBagConstraints);

		gridBagConstraints.gridx++;
		JLabel secondNameLabel = new JLabel("Second Name:");
		secondNameLabel.setHorizontalAlignment(JLabel.RIGHT);
		add(secondNameLabel, gridBagConstraints);

		gridBagConstraints.gridx++;
		secondNameTextField = new JTextField("data", 10);
		add(secondNameTextField, gridBagConstraints);

		gridBagConstraints.gridx++;
		JLabel groupLabel = new JLabel("Group:");
		groupLabel.setHorizontalAlignment(JLabel.RIGHT);
		add(groupLabel, gridBagConstraints);

		gridBagConstraints.gridx++;
		groupTextField = new JTextField("data", 10);
		add(groupTextField, gridBagConstraints);

		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 2;
		JButton okButton = new JButton("Ok");
		add(okButton, gridBagConstraints);
		okButton.addActionListener(new OkButtonActionListener());

		gridBagConstraints.gridx = 4;
		JButton closeButton = new JButton("Close");
		add(closeButton, gridBagConstraints);
		closeButton.addActionListener(new CloseButtonActionListener(this));

		setSize(new Dimension(inputDialogFrame.getPreferredSize().width,
				inputDialogFrame.getPreferredSize().height / 2));
		setLocation(
				inputDialogFrame.getLocation().x,
				inputDialogFrame.getLocation().y
						+ inputDialogFrame.getPreferredSize().height / 2);

		studentTableController = inputDialogFrame.getController();

		validate();
		setVisible(true);
	}

	private void readInform() {
		String inputName = nameTextField.getText();
		String inputSurname = surNameTextField.getText();
		String inputSecondName = secondNameTextField.getText();
		String inputGroup = groupTextField.getText();

		Student student = new Student(inputName, inputSurname, inputSecondName,
				inputGroup);

		studentTableController.addStudent(student);

	}

	private class OkButtonActionListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			readInform();
		}
	}

	private class CloseButtonActionListener implements ActionListener {

		private JDialog dialogFrame;

		public CloseButtonActionListener(JDialog dialog) {
			dialogFrame = dialog;
		}

		public void actionPerformed(ActionEvent event) {
			dialogFrame.setVisible(false);
		}
	}
}
