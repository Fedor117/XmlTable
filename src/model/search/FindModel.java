package model.search;

import controller.StudentList;
import model.FinderModel;

public class FindModel {

	public final static String AVERAGE_SCORE = "AverageScore";
	public final static String SURNAME_SCORE = "SurnameScore";
	public final static String SURNAME_GROUP = "SurnameGroup";

	private FinderModel finder;

	public FindModel() {
	}

	public void set(String inputName, String inputSurname,
			String inputSecondName, String inputGroup, String inputSubject,
			int inputMark, String type) {
		switch (type) {
		case AVERAGE_SCORE:
			finder = new AverageScoreModel(inputName, inputSurname,
					inputSecondName, inputGroup, inputSubject, inputMark);

			break;
		case SURNAME_SCORE:
			finder = new SurnameAndScoreModel(inputName, inputSurname,
					inputSecondName, inputGroup, inputSubject, inputMark);

			break;
		case SURNAME_GROUP:
			finder = new GroupAndSurnameModel(inputName, inputSurname,
					inputSecondName, inputGroup, inputSubject, inputMark);

			break;
		default:
			break;
		}

	}

	public void setSourceStudentList(StudentList studentList) {
		finder.setSourceList(studentList);
	}

	public StudentList find() {
		return finder.find();
	}
}
