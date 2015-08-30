package model;

import java.io.File;
import java.util.Iterator;

import controller.StudentList;
import model.XMLset.XMLReader;
import model.XMLset.XMLwriter;
import model.search.FindModel;

public class LocalDataProvider implements DataProvider {

	private StudentList studentList;
	private int numberOfVisibleRows;
	private int numberOfPages;
	private int pageNumber;

	public LocalDataProvider(StudentList studentList) {
		this.studentList = studentList;
		numberOfVisibleRows = studentList.size();
	}

	public void open(File file) {
		XMLReader xmlReader = new XMLReader();
		studentList = xmlReader.read(file);
		numberOfVisibleRows = studentList.size();
	}

	public void save(File file) {
		XMLwriter xmLwriter = new XMLwriter(studentList);
		xmLwriter.writeFile(file);
	}

	public void addStudent(Student stud) {
		studentList.add(stud);
	}

	public void setNumberOfVisibleRows(int num) {
		int newPageNumber = pageNumber * numberOfVisibleRows / num;
		if (newPageNumber > 0)
			pageNumber = newPageNumber;
		else
			pageNumber = 1;
		numberOfVisibleRows = num;
		numberOfPages = studentList.size() / numberOfVisibleRows + 1;
	}

	public StudentList firstPage() {
		StudentList stList = new StudentList();
		Iterator<Student> iterator = studentList.iterator();
		for (int numberOfStudent = 0; numberOfStudent < numberOfVisibleRows; numberOfStudent++) {
			if (iterator.hasNext()) {
				Student stud = iterator.next();
				stList.add(stud);
			} else
				break;
		}
		pageNumber = 1;
		return stList;
	}

	public StudentList previousPage() {
		if (pageNumber - 1 <= 0)
			return firstPage();
		StudentList stList = new StudentList();
		Iterator<Student> iterator = studentList.iterator();
		for (int previousStud = 0; previousStud < numberOfVisibleRows
				* (pageNumber - 2); previousStud++) {
			if (iterator.hasNext()) {
				iterator.next();
			} else
				return null;
		}
		for (int numberOfStudent = 0; numberOfStudent < numberOfVisibleRows; numberOfStudent++) {
			if (iterator.hasNext()) {
				Student stud = iterator.next();
				stList.add(stud);
			} else
				break;
		}
		pageNumber--;
		return stList;

	}

	public StudentList nextPage() {
		if (pageNumber + 1 > numberOfPages)
			return lastPage();
		StudentList stList = new StudentList();
		Iterator<Student> iterator = studentList.iterator();
		for (int previousStud = 0; previousStud < numberOfVisibleRows
				* pageNumber; previousStud++) {
			if (iterator.hasNext()) {
				iterator.next();
			} else
				return null;
		}
		for (int numberOfStudent = 0; numberOfStudent < numberOfVisibleRows; numberOfStudent++) {
			if (iterator.hasNext()) {
				Student stud = iterator.next();
				stList.add(stud);
			} else
				break;
		}
		pageNumber++;
		return stList;
	}

	public StudentList lastPage() {
		StudentList stList = new StudentList();
		Iterator<Student> iterator = studentList.iterator();
		for (int previousStud = 0; previousStud < numberOfVisibleRows
				* (numberOfPages - 1); previousStud++) {
			if (iterator.hasNext()) {
				iterator.next();
			} else
				return null;
		}
		for (int numberOfStudent = 0; numberOfStudent < numberOfVisibleRows; numberOfStudent++) {
			if (iterator.hasNext()) {
				Student stud = iterator.next();
				stList.add(stud);
			} else
				break;
		}
		pageNumber = numberOfPages;
		return stList;
	}

	public int numberOfPages() {
		if ((double) studentList.size() / (double) numberOfVisibleRows > studentList
				.size() / numberOfVisibleRows)
			numberOfPages = studentList.size() / numberOfVisibleRows + 1;
		else
			numberOfPages = studentList.size() / numberOfVisibleRows;
		return numberOfPages;
	}

	public int countOfPage() {
		return pageNumber;
	}

	public void addExam(String surname, String h, int type) {
		Iterator<Student> iterator = studentList.iterator();
		while (iterator.hasNext()) {
			Student student = iterator.next();
			if (student.surname().equals(surname)) {
				student.addExam(h, type);
			}
		}
	}

	public void remove(StudentList studentList) {
		Iterator<Student> iter1 = studentList.iterator();
		while (iter1.hasNext()) {
			Student stud1 = iter1.next();
			Iterator<Student> iter2 = this.studentList.iterator();
			while (iter2.hasNext()) {
				Student stud2 = iter2.next();
				if (stud1.surname().equals(stud2.surname())) {
					iter2.remove();
				}
			}
		}
	}

	@Override
	public StudentList find(Student student, StudentList studentList,
			String type) {
		FindModel finder = new FindModel();
		finder.set(student.name(), student.surname(), student.secondName(),
				student.group(), student.subject(), student.mark(), type);
		if (studentList != null) {
			finder.setSourceStudentList(studentList);
		} else {
			finder.setSourceStudentList(this.studentList);
		}
		return finder.find();
	}
}
