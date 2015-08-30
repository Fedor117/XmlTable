package view.dialogs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.StudentTableController;
import model.search.FindModel;

public class SurnameAndScoreDialog extends FindRemoveDialog {

	public SurnameAndScoreDialog(JFrame inputDialogFrame,
			StudentTableController studentTableController) {
		super(inputDialogFrame, studentTableController);
	}

	protected void elements() {

		JLabel surnameLabel = new JLabel("Surname");
		surnameLabel.setHorizontalAlignment(JLabel.RIGHT);
		dialogPanel.add(surnameLabel, BorderLayout.CENTER);

		JTextField surnameTextField = new JTextField("data", 10);
		dialogPanel.add(surnameTextField, BorderLayout.CENTER);

		JLabel scoreLabel = new JLabel("Score");
		scoreLabel.setHorizontalAlignment(JLabel.RIGHT);
		dialogPanel.add(scoreLabel, BorderLayout.CENTER);

		JTextField scoreTextField = new JTextField("data", 10);
		dialogPanel.add(scoreTextField, BorderLayout.CENTER);

		JButton findButton = new JButton("Find");
		dialogPanel.add(findButton);
		findButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				addScrollPane(createTable(studentTableController
						.find(null, surnameTextField.getText(), null, null,
								null, scoreTextField.getText(), null,
								FindModel.SURNAME_SCORE)));
			}
		});
	}

}
