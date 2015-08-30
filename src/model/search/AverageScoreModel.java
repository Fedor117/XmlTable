package model.search;

import java.util.Iterator;

import controller.StudentList;
import model.FinderModel;
import model.Student;

public class AverageScoreModel extends FinderModel {

	public AverageScoreModel(String inputName, String inputSurname,
			String inputSecondName, String inputGroup, String inputSubject,
			int inputMark) {
		super(inputName, inputSurname, inputSecondName, inputGroup,
				inputSubject, inputMark);
	}

	protected StudentList doSearch(StudentList studentList,
			Iterator<Student> iterator) {
		while (iterator.hasNext()) {
			Student student = iterator.next();
			if (student.mark() >= mark) {
				studentList.add(student);
			}
		}
		return studentList;
	}

}
