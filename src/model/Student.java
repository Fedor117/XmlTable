package model;

import java.io.Serializable;

public class Student implements Serializable {

	public static final int SUBJECT = 24468468;
	public static final int MARK = 89874165;

	protected String name;
	protected String surname;
	protected String secondName;
	protected String group;
	protected String subject;
	protected int mark;

	public Student(String inputName, String inputSurname,
			String inputSecondName, String inputGroup) {
		name = inputName;
		surname = inputSurname;
		secondName = inputSecondName;
		group = inputGroup;
		subject = "";
		mark = 0;
	}

	public String name() {
		return name;
	}

	public String surname() {
		return surname;
	}

	public String secondName() {
		return secondName;
	}

	public String group() {
		return group;
	}

	public String subject() {
		return subject;
	}

	public int mark() {
		return mark;
	}

	public void addExam(String inputData, int type) {

		switch (type) {
		case SUBJECT:
			subject = inputData;
			break;
		case MARK:
			if (inputData == null) {
				mark += 0;
			} else {
				mark += Integer.parseInt(inputData);
			}
			break;
		}

	}
}
