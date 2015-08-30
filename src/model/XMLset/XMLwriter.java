package model.XMLset;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import controller.StudentList;
import model.Student;

public class XMLwriter {

	private StudentList studentList;

	public XMLwriter(StudentList studentList) {
		this.studentList = studentList;
	}

	private Document write() {
		DocumentBuilder documentBuilder = null;
		try {
			documentBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		Document document = documentBuilder.newDocument();

		Element studentSetTag = document.createElement("studentSet");

		document.appendChild(studentSetTag);

		Iterator<Student> iterator = studentList.iterator();
		while (iterator.hasNext()) {
			Student student = iterator.next();

			Element studentTag = document.createElement("student");
			Element nameTag = document.createElement("name");
			Element surnameTag = document.createElement("surname");
			Element secondNameTag = document.createElement("secondName");
			Element groupTag = document.createElement("group");
			Element illnessTag = document.createElement("subject");
			Element otherReasonTag = document.createElement("mark");

			studentSetTag.appendChild(studentTag);

			studentTag.appendChild(nameTag);
			nameTag.appendChild(document.createTextNode(student.name()));

			studentTag.appendChild(surnameTag);
			surnameTag.appendChild(document.createTextNode(student.surname()));

			studentTag.appendChild(secondNameTag);
			secondNameTag.appendChild(document.createTextNode(student
					.secondName()));

			studentTag.appendChild(groupTag);
			groupTag.appendChild(document.createTextNode(student.group()));

			studentTag.appendChild(illnessTag);
			illnessTag.appendChild(document.createTextNode(student.subject()));

			studentTag.appendChild(otherReasonTag);
			otherReasonTag.appendChild(document.createTextNode(Integer
					.toString(student.mark())));

		}

		return document;
	}

	public void writeFile(File f) {
		try {
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
					"4");
			try {
				t.transform(new DOMSource(write()), new StreamResult(
						new FileOutputStream(f)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
			}
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
	}
}
