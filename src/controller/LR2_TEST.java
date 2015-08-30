package controller;

import java.awt.BorderLayout;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import Java2com.swing.JTableGroupHeader.GroupableTableHeader;
import model.LocalDataProvider;
import view.ControlButtonsPanel;
import view.DialogFrame;
import view.MainWindow;
import view.NavigationPanel;
import view.TablePane;

public class LR2_TEST {
	public static void main(String[] args) {
		MainWindow mainWindow = new MainWindow();

		StudentTableController studentTableController = new StudentTableController();
		studentTableController.initializeDataProvider(new LocalDataProvider(
				new StudentList()));

		JTable jTable = new JTable() {
			protected JTableHeader createDefaultTableHeader() {
				return new GroupableTableHeader(columnModel);
			}
		};

		jTable.setModel(studentTableController.getStudentTableModel());

		TablePane tablePane = new TablePane(jTable);
		mainWindow.add(tablePane.getScrollPane(), BorderLayout.CENTER);

		DialogFrame dialogPanel = new DialogFrame(mainWindow,
				studentTableController);

		ControlButtonsPanel controlButtonsPanel = new ControlButtonsPanel(
				dialogPanel, studentTableController);
		mainWindow.add(controlButtonsPanel, BorderLayout.WEST);

		NavigationPanel optionsPanel = new NavigationPanel(mainWindow,
				studentTableController);
		mainWindow.add(optionsPanel, BorderLayout.SOUTH);

		mainWindow.validate();
	}
}
