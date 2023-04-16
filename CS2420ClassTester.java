package assign02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


/**
 * This class contains tests for CS2420Class.
 * 
 * @author Erin Parker, Ben Jones, Isaac Hall, and Bradley Bartelt
 * @version January 23, 2023
 */
public class CS2420ClassTester {

	private CS2420Class emptyClass, verySmallClass, smallClass, veryLargeClass;

	@BeforeEach
	void setUp() throws Exception {
		emptyClass = new CS2420Class();

		verySmallClass = new CS2420Class();
		verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010101, new EmailAddress("hi", "gmail.com")));
		verySmallClass.addStudent(new CS2420Student("Drew", "Hall", 2323232, new EmailAddress("howdy", "gmail.com")));
		verySmallClass.addStudent(new CS2420Student("Riley", "Nguyen", 4545454, new EmailAddress("hello", "gmail.com")));

		smallClass = new CS2420Class();
		smallClass.addAll("a_small_2420_class.txt");

		// FILL IN -- Extend this tester to add more tests for the CS 2420 classes above, as well as to
		// create and test larger CS 2420 classes.
		// (HINT: For larger CS 2420 classes, generate random names, uNIDs, contact info, and scores in a 
		// loop, instead of typing one at a time.)
		veryLargeClass = new CS2420Class();

		for (int i = 0; i < 10000; i++) {
			String firstName = "";
			String lastName = "";
			String userName = "";
			String domainName = "gmail.com";
			int firstNameLength = (int) (Math.random() * 8) + 5;
			int lastNameLength = (int) (Math.random() * 8) + 5;
			int userNameLength = (int) (Math.random() * 8) + 5;
			for (int j = 0; j < firstNameLength; j++) {
				Random rnd = new Random();
				char c = (char) ('a' + rnd.nextInt(26));
				firstName = firstName + c;
			}
			for (int j = 0; j < lastNameLength; j++) {
				Random rnd = new Random();
				char c = (char) ('a' + rnd.nextInt(26));
				lastName = lastName + c;
			}
			for (int j = 0; j < userNameLength; j++) {
				Random rnd = new Random();
				char c = (char) ('a' + rnd.nextInt(26));
				userName = userName + c;
			}

			int uNID = (int) (Math.random() * 8_000_000) + 1_000_000;

			CS2420Student student = new CS2420Student(firstName, lastName, uNID, new EmailAddress(userName, domainName));
			veryLargeClass.addStudent(student);

			student.addScore(70, "assignment");
			student.addScore(70, "lab");
			student.addScore(70, "exam");
			student.addScore(70, "quiz");
		}
		CS2420Student knownStudent = new CS2420Student("Jamie", "Smith", 1379952, new EmailAddress("IsaacHall", "gmail.com"));
		veryLargeClass.addStudent(knownStudent);
		knownStudent.addScore(70, "assignment");
		knownStudent.addScore(70, "lab");
		knownStudent.addScore(70, "exam");
		knownStudent.addScore(70, "quiz");
	}


	// Empty CS 2420 class tests --------------------------------------------------------------------------

	@Test
	public void testEmptyLookupUNID() {
		assertNull(emptyClass.lookup(1234567));
	}

	@Test
	public void testEmptyLookupContactInfo() {
		ArrayList<CS2420Student> students = emptyClass.lookup(new EmailAddress("hello", "gmail.com"));
		assertEquals(0, students.size());
	}

	@Test
	public void testEmptyAddScore() {
		// ensure no exceptions thrown
		emptyClass.addScore(1234567, 100, "assignment");
	}

	@Test
	public void testEmptyClassAverage() {
		assertEquals(0, emptyClass.computeClassAverage(), 0);
	}

	@Test
	public void testEmptyContactList() {
		ArrayList<EmailAddress> contactList = emptyClass.getContactList();
		assertEquals(0, contactList.size());
	}

	// Very small CS 2420 class tests --------------------------------------------------------------------

	@Test
	public void testVerySmallLookupUNID() {
		UofUStudent expected = new UofUStudent("Drew", "Hall", 2323232);
		CS2420Student actual = verySmallClass.lookup(2323232);
		assertEquals(expected, actual);
	}

	@Test
	public void testVerySmallLookupContactInfo() {
		UofUStudent expectedStudent = new UofUStudent("Riley", "Nguyen", 4545454);
		ArrayList<CS2420Student> actualStudents = verySmallClass.lookup(new EmailAddress("hello", "gmail.com"));
		assertEquals(1, actualStudents.size());
		assertEquals(expectedStudent, actualStudents.get(0));
	}

	@Test
	public void testVerySmallAddDuplicateStudent() {
		boolean actual = verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010101,
				new EmailAddress("hi", "gmail.com")));

		assertFalse(actual);
	}

	@Test
	public void testVerySmallAddNewStudent() {
		boolean actual = verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010100,
				new EmailAddress("hi", "gmail.com")));
		assertTrue(actual);
	}

	@Test
	public void testVerySmallStudentFinalScore0() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(89.2, "quiz");
		assertEquals(0, student.computeFinalScore(), 0);
	}

	@Test
	public void testVerySmallStudentFinalGradeNA() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(100, "lab");
		assertEquals("N/A", student.computeFinalGrade());
	}

	@Test
	public void testVerySmallStudentFinalScore() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(55, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		assertEquals(55, student.computeFinalScore(), 0.001);
	}

	@Test
	public void testVerySmallStudentFinalGrade() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		assertEquals("B", student.computeFinalGrade());
	}

	@Test
	public void testVerySmallStudentComputeScoreTwice() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		student.computeFinalScore();
		student.addScore(70, "lab");
		student.addScore(54.5, "exam");
		assertEquals(64.75, student.computeFinalScore(), 0.001);
	}

	@Test
	public void testVerySmallUpdateName() {
		verySmallClass.lookup(1010101).updateName("John", "Doe");
		ArrayList<CS2420Student> students = verySmallClass.lookup(new EmailAddress("hi", "gmail.com"));
		assertEquals("John", students.get(0).getFirstName());
		assertEquals("Doe", students.get(0).getLastName());
	}

	// Small CS 2420 class tests -------------------------------------------------------------------------

	@Test
	public void testSmallLookupContactInfo() {
		UofUStudent expectedStudent1 = new UofUStudent("Kennedy", "Miller", 888888);
		UofUStudent expectedStudent2 = new UofUStudent("Taylor", "Miller", 999999);

		ArrayList<CS2420Student> actualStudents = smallClass.lookup(new EmailAddress("we_love_puppies", "geemail.com"));
		assertEquals(2, actualStudents.size());
		assertTrue(actualStudents.contains(expectedStudent1));
		assertTrue(actualStudents.contains(expectedStudent2));
	}

	@Test
	public void testSmallGetContactList() {
		ArrayList<EmailAddress> actual = smallClass.getContactList();
		assertEquals(9, actual.size());
	}

	@Test
	public void testSmallStudentFinalScore() {
		CS2420Student student = smallClass.lookup(333333);
		assertEquals(95.5345, student.computeFinalScore(), 0.001);
	}

	@Test
	public void testSmallComputeClassAverage() {
		assertEquals(78.356, smallClass.computeClassAverage(), 0.001);
	}

	// Test veryLarge CS2420 class list (we made these)------------------------------------------------------
	@Test
	public void testVeryLargeLookupUNID() {
		UofUStudent expected = new UofUStudent("Jamie", "Smith", 1379952);
		CS2420Student actual = veryLargeClass.lookup(1379952);
		assertEquals(expected, actual);
	}

	@Test
	public void testVeryLargeLookupContactInfo() {
		UofUStudent expectedStudent = new UofUStudent("Jaime", "Smith", 1379952);
		ArrayList<CS2420Student> actualStudents = veryLargeClass.lookup(new EmailAddress("IsaacHall", "gmail.com"));
		assertEquals(1, actualStudents.size());
		assertEquals(expectedStudent, actualStudents.get(0));
	}

	@Test
	public void testVeryLargerAddDuplicateStudent() {
		boolean actual = veryLargeClass.addStudent(new CS2420Student("Jamie", "Smith", 1379952,
				new EmailAddress("IsaacHall", "gmail.com")));

		assertFalse(actual);
	}

	@Test
	public void testVeryLargeAddNewStudent() {
		boolean actual = veryLargeClass.addStudent(new CS2420Student("Jane", "Doe", 1010100,
				new EmailAddress("hi", "gmail.com")));
		assertTrue(actual);
	}

	@Test
	public void testVeryLargeStudentFinalScore() {
		CS2420Student student = veryLargeClass.lookup(1379952);
		assertEquals(70, student.computeFinalScore(), 0);
	}

	@Test
	public void testVeryLargeStudentFinalGrade() {
		CS2420Student student = veryLargeClass.lookup(1379952);
		assertEquals("C-", student.computeFinalGrade());
	}

	@Test
	public void testVeryLargeComputeClassAverage() {
		assertEquals(70, veryLargeClass.computeClassAverage(), 0.001);
	}
	 // Extra tests ----------------------------------------------------

	@Test
	// tests if the lookup method returns null
	public void doesLookupUNIDReturnNull(){
		assertNull(verySmallClass.lookup(1000010));
	}
	@Test
	// tests if the lookup contactInfo return null
	public void doesLookupContactInfoReturnEmpty(){
		assertEquals(0,emptyClass.lookup(new EmailAddress("newAddress","random.com")).size());
	}
	@Test
	//When adding a student that's the same as another will it return false
	public void doesAddStudentWork(){
		assertFalse(verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010101, new EmailAddress("hi", "gmail.com"))));
	}
}