package model.Table;

import java.io.Serializable;
import java.util.Iterator;

import javax.swing.table.AbstractTableModel;

import controller.StudentList;
import model.Student;

public class StudentTableModel extends AbstractTableModel implements
		Serializable {

	public static final int NAME = 0;
	public static final int SURNAME = 1;
	public static final int SECONDNAME = 2;
	public static final int GROUP = 3;
	public static final int SUBJECT = 4;
	public static final int MARK = 5;

	protected StudentList studentList;
	protected int numberOfFields;
	protected int numberOfRows;

	public StudentTableModel() {
		studentList = null;
		numberOfFields = 6;
		numberOfRows = 10;
	}

	public int getRowCount() {
		return numberOfRows;
	}

	public int getColumnCount() {
		return numberOfFields;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {

		if (studentList == null)
			return null;
		if (rowIndex >= studentList.size())
			return null;

		Iterator<Student> iterator = studentList.iterator();

		Student student = null;
		int numberOfRow = -1;
		while (numberOfRow != rowIndex && iterator.hasNext()) {
			student = iterator.next();
			numberOfRow++;
		}

		switch (columnIndex) {
		case NAME:
			return student.name();
		case SURNAME:
			return student.surname();
		case SECONDNAME:
			return student.secondName();
		case GROUP:
			return student.group();
		case SUBJECT:
			return student.subject();
		case MARK:
			return student.mark();
		default:
			return null;
		}
	}

	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case NAME:
			return "NAME";
		case SURNAME:
			return "SURNAME";
		case SECONDNAME:
			return "SECONDNAME";
		case GROUP:
			return "GROUP";
		case SUBJECT:
			return "SUBJECT";
		case MARK:
			return "MARK";
		default:
			return null;
		}
	}

	public void setNewSource(StudentList studentList) {
		this.studentList = studentList;
		numberOfRows = studentList.size();
		fireTableDataChanged();
	}

	public void setNumberOfRows(int num) {
		numberOfRows = num;
	}
}
