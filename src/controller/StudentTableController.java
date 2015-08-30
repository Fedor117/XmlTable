package controller;

import java.io.File;
import java.io.Serializable;

import model.DataProvider;
import model.Student;
import model.Table.StudentTableModel;

public class StudentTableController implements Serializable {

	public DataProvider dataProvider;
	public StudentTableModel studentTableModel;

	public StudentTableController() {
		studentTableModel = new StudentTableModel();
	}

	public void initializeDataProvider(DataProvider dataProvider) {
		this.dataProvider = dataProvider;
	}

	public void write(String filename) {
		File f = new File(filename);
		dataProvider.save(f);
	}

	public void read(String filename) {
		File f = new File(filename);
		dataProvider.open(f);
		studentTableModel.setNewSource(dataProvider.firstPage());
		studentTableModel.fireTableDataChanged();
	}

	public void addStudent(Student student) {
		dataProvider.addStudent(student);
		dataProvider.nextPage();
		studentTableModel.setNewSource(dataProvider.previousPage());
	}

	public void firstPage() {
		studentTableModel.setNewSource(dataProvider.firstPage());
		studentTableModel.fireTableDataChanged();
	}

	public void nextPage() {
		studentTableModel.setNewSource(dataProvider.nextPage());
		studentTableModel.fireTableDataChanged();
	}

	public void previousPage() {
		studentTableModel.setNewSource(dataProvider.previousPage());
		studentTableModel.fireTableDataChanged();
	}

	public void lastPage() {
		studentTableModel.setNewSource(dataProvider.lastPage());
		studentTableModel.fireTableDataChanged();
	}

	public void setNumberOfVisibleRows(int num) {
		dataProvider.setNumberOfVisibleRows(num);
		dataProvider.nextPage();
		studentTableModel.setNewSource(dataProvider.previousPage());
		studentTableModel.fireTableDataChanged();
	}

	public StudentList find(String inputName, String inputSurname,
			String inputSecondName, String inputGroup, String inputSubject,
			String inputMark, StudentList studentList, String type) {
		Student student = new Student(inputName, inputSurname, inputSecondName,
				inputGroup);
		student.addExam(inputSubject, Student.SUBJECT);
		student.addExam(inputMark, Student.MARK);
		return dataProvider.find(student, studentList, type);
	}

	public void addExam(String surname, String m, int type) {
		dataProvider.addExam(surname, m, type);
		studentTableModel.fireTableDataChanged();
	}

	public int getNumberOfPages() {
		return dataProvider.numberOfPages();
	}

	public int getPageCountNumber() {
		return dataProvider.countOfPage();
	}

	public StudentTableModel getStudentTableModel() {
		return studentTableModel;
	}

	public void remove(StudentList studentList) {
		dataProvider.remove(studentList);
	}
}