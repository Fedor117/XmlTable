package view;

import java.io.Serializable;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import Java2com.swing.JTableGroupHeader.ColumnGroup;
import Java2com.swing.JTableGroupHeader.GroupableTableHeader;
import model.Table.StudentTableModel;

public class TablePane implements Serializable {

	JTable jTable;

	public TablePane(JTable jT) {

		jTable = jT;

		TableColumnModel tableColumnModel = jTable.getColumnModel();
		ColumnGroup examsGroup = new ColumnGroup("Exam");
		ColumnGroup exam = new ColumnGroup("1");
		exam.add(tableColumnModel.getColumn(StudentTableModel.SUBJECT));
		exam.add(tableColumnModel.getColumn(StudentTableModel.MARK));
		examsGroup.add(exam);

		GroupableTableHeader groupableTableHeader = (GroupableTableHeader) jTable
				.getTableHeader();
		groupableTableHeader.addColumnGroup(examsGroup);
	}

	public JScrollPane getScrollPane() {
		JScrollPane scrollPane = new JScrollPane(jTable);
		return scrollPane;
	}

}
