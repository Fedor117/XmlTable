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

public class AverageScoreDialog extends FindRemoveDialog {

	public AverageScoreDialog(JFrame inputDialogFrame,
			StudentTableController studentTableController) {
		super(inputDialogFrame, studentTableController);
	}

	protected void elements() {

		JLabel firstLable = new JLabel("First mark:");
		firstLable.setHorizontalAlignment(JLabel.RIGHT);
		dialogPanel.add(firstLable, BorderLayout.CENTER);

		JTextField firstTextField = new JTextField("data", 10);
		dialogPanel.add(firstTextField, BorderLayout.CENTER);

		JLabel secondLabel = new JLabel("Second Mark:");
		secondLabel.setHorizontalAlignment(JLabel.RIGHT);
		dialogPanel.add(secondLabel, BorderLayout.CENTER);

		JTextField secondTextField = new JTextField("data", 10);
		dialogPanel.add(secondTextField, BorderLayout.CENTER);

		JButton findButton = new JButton("Find");
		dialogPanel.add(findButton);
		findButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				addScrollPane(createTable(studentTableController.find(null,
						null, null, null, null, firstTextField.getText(), null,
						FindModel.AVERAGE_SCORE)));
			}
		});
	}
}