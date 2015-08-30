package model.XMLset;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import controller.StudentList;
import model.Student;

public class XMLReader {

	private Student student;
	private StudentList studentList;
	private String name;
	private String surname;
	private String secondname;
	private String group;
	private String subject;
	private String mark;

	public XMLReader() {
	}

	public StudentList read(File f) {
		DefaultHandler handler = new DefaultHandler() {

			private String localName;

			public void endElement(String uri, String localName, String qName)
					throws SAXException {
				if (qName == "student") {
					student = new Student(name, surname, secondname, group);
					student.addExam(subject, Student.SUBJECT);
					student.addExam(mark, Student.MARK);
					studentList.add(student);
				}
				this.localName = "\\" + qName;
			}

			public void characters(char[] ch, int start, int length)
					throws SAXException {
				String data = new String();
				int symbolCount = 0;
				while (symbolCount < length) {
					data += ch[start];
					symbolCount++;
					start++;
				}
				data = data.trim();

				if (localName == "name") {
					name = data;
				}
				if (localName == "surname") {
					surname = data;
				}
				if (localName == "secondName") {
					secondname = data;
				}
				if (localName == "group") {
					group = data;
				}
				if (localName == "subject") {
					subject = data;
				}
				if (localName == "mark") {
					mark = data;
				}

			}

			public void startElement(String uri, String localName,
					String qName, Attributes atts) throws SAXException {
				if (qName == "studentSet") {
					studentList = new StudentList();
				}
				this.localName = qName;
			}
		};

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = null;
		try {
			saxParser = factory.newSAXParser();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		InputStream in = null;
		try {
			in = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			saxParser.parse(in, handler);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return studentList;
	}
}
