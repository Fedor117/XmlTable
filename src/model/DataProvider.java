package model;

import java.io.File;

import controller.StudentList;

public interface DataProvider {

	void open(File file);

	void save(File file);

	void addStudent(Student stud);

	void setNumberOfVisibleRows(int num);

	StudentList firstPage();

	StudentList previousPage();

	StudentList nextPage();

	StudentList lastPage();

	int numberOfPages();

	int countOfPage();

	void addExam(String surname, String h, int type);

	void remove(StudentList studList);

	StudentList find(Student student, StudentList studentList, String type);
}
