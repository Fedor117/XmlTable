package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import Java2com.swing.JTableGroupHeader.GroupableTableHeader;
import controller.StudentList;
import controller.StudentTableController;
import model.LocalDataProvider;
import model.Table.StudentTableModel;
import model.Table.TablePane;
import view.NavigationPanel;

public abstract class FindRemoveDialog extends JDialog {

	protected JPanel dialogPanel;
	protected StudentTableController studentTableController;
	protected JTable jTable;
	private StudentList studentList;
	private JFrame jFrame;
	private JPanel navPanel;
	private StudentTableController myOwnStudentTableController;

	public FindRemoveDialog(JFrame inputDialogFrame,
			StudentTableController studentTableController) {
		jFrame = inputDialogFrame;
		localeAndSize(inputDialogFrame, studentTableController);
		this.studentTableController = studentTableController;
		elements();
		addRemove();
		validate();
		setVisible(true);
	}

	protected abstract void elements();

	private void localeAndSize(JFrame inputDialogFrame,
			StudentTableController studentTableController) {
		Dimension dimension = inputDialogFrame.getPreferredSize();
		setSize(new Dimension(dimension.width, dimension.height));
		setLocation(inputDialogFrame.getLocation().x,
				inputDialogFrame.getLocation().y);

		dialogPanel = new JPanel();
		dialogPanel.setPreferredSize(new Dimension(dimension.width,
				dimension.height / 5));
		myOwnStudentTableController = new StudentTableController();
		JPanel navigationPanel = new NavigationPanel(jFrame,
				myOwnStudentTableController);
		navigationPanel.setPreferredSize(new Dimension(dimension.width / 3,
				dimension.height));
		add(navigationPanel, BorderLayout.EAST);
		add(dialogPanel, BorderLayout.SOUTH);

	}

	protected void addScrollPane(JTable jTable) {
		TablePane tablePane = new TablePane(jTable);
		JScrollPane scrollPane = tablePane.getScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		validate();
	}

	protected JTable createTable(StudentList studentList) {
		myOwnStudentTableController
				.initializeDataProvider(new LocalDataProvider(studentList));
		this.studentList = studentList;
		JTable jTable = new JTable() {
			protected JTableHeader createDefaultTableHeader() {
				return new GroupableTableHeader(columnModel);
			}
		};
		StudentTableModel studentTableModel = myOwnStudentTableController
				.getStudentTableModel();
		jTable.setModel(studentTableModel);
		return jTable;
	}

	private void addRemove() {
		JButton jButton = new JButton("Remove");
		jButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentTableController.remove(studentList);
			}
		});
		dialogPanel.add(jButton);
	}

}
