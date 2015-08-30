package model;

import java.util.Iterator;

import controller.StudentList;

public abstract class FinderModel extends Student {

	private StudentList studentList;
	private Student etalonStudent;

	public FinderModel(String inputName, String inputSurname,
			String inputSecondName, String inputGroup, String inputSubject,
			int inputMark) {
		super(inputName, inputSurname, inputSecondName, inputGroup);
		addExam(inputSubject, SUBJECT);
		addExam(String.valueOf(inputMark), MARK);
	}

	public void setSourceList(StudentList list) {
		studentList = list;
	}

	private Iterator<Student> getIterator() {
		return studentList.iterator();
	}

	public StudentList find() {
		return doSearch(new StudentList(), getIterator());
	}

	protected abstract StudentList doSearch(StudentList studentList,
			Iterator<Student> iterator);

}
