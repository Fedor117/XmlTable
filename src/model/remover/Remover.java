package model.remover;

import java.util.Iterator;

import controller.StudentList;
import model.Student;

public class Remover {

	private StudentList sourceStudentList;
	private StudentList inputStudentList;

	public Remover(StudentList sourceStudentList, StudentList inputStudentList) {
		this.sourceStudentList = sourceStudentList;
		this.inputStudentList = inputStudentList;
	}

	public StudentList remove() {

		Iterator<Student> inputIterator = inputStudentList.iterator();
		while (inputIterator.hasNext()) {

			Student inputStudent = inputIterator.next();

			Iterator<Student> sourceIterator = sourceStudentList.iterator();
			while (sourceIterator.hasNext()) {
				Student sourceStudent = sourceIterator.next();
				if (sourceStudent.surname().equals(inputStudent.surname())) {
					if (sourceStudent.name().equals(inputStudent.name())) {
						sourceIterator.remove();
					}
				}
			}
		}

		return sourceStudentList;
	}
}
