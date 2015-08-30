package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.StudentTableController;

public class NavigationPanel extends JPanel {

	private StudentTableController studentTableController;
	private JLabel counter;
	private JSpinner numberOfRowsSpinner;

	public NavigationPanel(JFrame inputMainWindow,
			StudentTableController controller) {
		super();
		Dimension dimension = inputMainWindow.getSize();
		setPreferredSize(new Dimension(dimension.width, dimension.height / 8));

		JLabel numberOfRowLabel = new JLabel("Rows per page: ");
		numberOfRowLabel.setHorizontalAlignment(JLabel.RIGHT);
		add(numberOfRowLabel, BorderLayout.CENTER);

		numberOfRowsSpinner = new JSpinner(
				new SpinnerNumberModel(10, 1, 100, 1));
		add(numberOfRowsSpinner, BorderLayout.CENTER);
		numberOfRowsSpinner.addChangeListener(new RowsSpinnerListener());

		JButton firstPageButton = new JButton("First Page");
		add(firstPageButton, BorderLayout.CENTER);
		firstPageButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				studentTableController.firstPage();
				patchCounter();
			}
		});

		JButton previousPageButton = new JButton("<< Previous Page");
		add(previousPageButton, BorderLayout.CENTER);
		previousPageButton.addActionListener(new PreviousPageListener());

		counter = new JLabel("1 of 1");
		add(counter, BorderLayout.CENTER);

		JButton nextPageButton = new JButton("Next Page >>");
		add(nextPageButton, BorderLayout.CENTER);
		nextPageButton.addActionListener(new NextPageButtonListener());

		JButton lastPageButton = new JButton("Last Page");
		add(lastPageButton, BorderLayout.CENTER);
		lastPageButton.addActionListener(new LastPageButtonListener());

		studentTableController = controller;
	}

	private void patchCounter() {
		int pages = studentTableController.getNumberOfPages();
		int number = studentTableController.getPageCountNumber();
		counter.setText(number + " of " + pages);
	}

	private class RowsSpinnerListener implements ChangeListener {
		public void stateChanged(ChangeEvent event) {
			JSpinner spinner = (JSpinner) event.getSource();
			int num = (int) spinner.getValue();
			studentTableController.setNumberOfVisibleRows(num);
			patchCounter();
		}
	}

	private class NextPageButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			studentTableController.nextPage();
			patchCounter();
		}
	}

	private class PreviousPageListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			studentTableController.previousPage();
			patchCounter();
		}
	}

	private class LastPageButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			studentTableController.lastPage();
			patchCounter();
		}
	}

}
