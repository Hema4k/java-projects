
import java.util.*;

/**
 * The Main class is the entry point of the Student Result System. It provides the user interface for interacting with the system,
 * including inputting student data, processing semester information, and calculating results.
 */
public class StudentsResultsystem {

		/**
		 * The main method provides a menu-driven interface for the user to interact with the Student Result System.
		 * It allows the user to input student data, process the results, and display the results.
		 *
		 * @param args command-line arguments (not used in this application)
		 */
		public static void main(String[] args) {
			Scanner input = new Scanner(System.in);
			ArrayList<Subject> delayed = new ArrayList<Subject>();
			ArrayList<String> subjectList = new ArrayList<String>(Arrays.asList(
				"ARABIC1", "2", "ENGLISH1", "2", "ISLAMIC_CULTURE1", "2", "MATHEMATICS1", "4",
				"PHYSICS1", "4", "HUMAN_COMMUNICATION", "3", "INTRODUCTION_TO_COMPUTERSCICNE", "4",
				"semester", "2", "ARABIC2", "2", "ENGLISH2", "2", "ISLAMIC_CULTURE2", "2",
				"MATHEMATICS2", "4", "PHYSICS2", "4", "ACCOUNTING_PRINCIPLES", "3", "PROGRAMMIN_FUNDAMENTALS", "4",
				"semester", "3", "ARABIC3", "2", "ENGLISH3", "2", "ISLAMIC_CULTURE3", "2", "MANAGEMENT_PRINCIPLES", "2",
				"DISCRETE_MATH", "3", "SOFTWARE_ENGINEERING", "3", "PROGRAMMIN_METHODS1", "4", "semester", "4",
				"MATHEMATICS3", "4", "STATISTICS&PROBABILITY", "3", "MATERIAL_COMPONENT", "4", "SOFTWARE_ENGEERING2", "4",
				"DISCRETE_MATH2", "3", "DATA_STRUCTURE_&ALGO", "4", "PROGRAMMIN_METHODS2", "4", "semester", "5",
				"SEARCH_METHODS", "2", "COMPUTER_ARCHITECTURE", "3", "OPERATING_SYSTEMES", "4", "REQUIREMENTS_ANALYSIS", "4",
				"ARTIFICIAL_INTELLIGENCE", "4", "DATA_BASE1", "3", "HUMAN-MACHINE_INTERRACTION", "3", "semester", "6",
				"MORAL&PROFESSIONAL_ISSUES", "2", "SOFTWARE_BULDING", "4", "COMPUTER_NETWORKS", "4", "SOFTWARE_DESIGN_&ARCHITECTURE", "4",
				"ECONOMICS", "3", "DATA_BASE2", "4", "semester", "7", "INTERNET_APPLICATIONS", "4", "SOFTWARE_TESTING", "4",
				"INFORMATION_SYSTEMS_SECURITY", "4", "OPTIONAL_SUBJECT_1", "4", "semester", "8", "SOFTWARE_MAINTAINANCE", "4",
				"SOFTWARE_QA", "4", "PROJECT_MANAGEMENT", "3", "OPTIONAL_SUBJECT2", "4", "GRADUATION_PROJECT", "6"));

			String studentName;
			String invalidMessage = "\n -------------invalid Input, please Enter either 1 OR 2 ONLY ---------------\n";
			String menue = "________________________Students Result System_______________\n1- Continue to the System (press 1) \n2- Exit the System (press 2)\nEnter your choice: ";
			int choice;

			int currentSemesterNumber = 0;

			while (true) {

				System.out.println(menue);

				try {

					choice = input.nextInt();

					if (isValidInt(choice, 2, 1)) {
						if (choice == 1) {
							Semester currentSemester = new Semester();
							System.out.println("Input the Student name please:");
							studentName = input.next();
							Student student = new Student(studentName);

							for (int i = 0; i < subjectList.size(); i += 2) {
								int points;
								int totalHours = 0; // Total hours for the current semester

								try {

									if (subjectList.get(i).equalsIgnoreCase("semester")) {
										student.addSemester(currentSemester);
										currentSemesterNumber += 1;
										currentSemester = new Semester();

										for (int s = 0; s < delayed.size(); s++) {
											if (delayed.get(s).getSemesterOfDelay() + 1 == currentSemesterNumber) {
												if (totalHours + delayed.get(s).getHours() <= 12) {
													System.out.println("Enter your points in the substitute or Failed " + delayed.get(s).getName() + " subject");
													points = input.nextInt();
													totalHours += delayed.get(s).getHours(); // Add the hours to the total

													if (points < 42) {
														delayed.get(s).setSemesterOfDelay(delayed.get(s).getSemesterOfDelay() + 1);
														delayed.get(s).setFailureTimes(delayed.get(s).getFailureTimes() + 1);
													}

													Subject currentSub = new Subject(delayed.get(s).getName(), delayed.get(s).getHours(), points);
													currentSemester.addSubject(currentSub);
												} else {
													System.out.println("Total hours for this semester cannot exceed 12. Skipping " + delayed.get(s).getName());
													delayed.get(s).setSemesterOfDelay(delayed.get(s).getSemesterOfDelay() + 1);
												}
											}
										}

										continue;
									}

									System.out.println("Enter your points in " + subjectList.get(i) + " subject");
									points = input.nextInt();
									int subjectHours = Integer.parseInt(subjectList.get(i + 1));
									totalHours += subjectHours; // Add the hours to the total

									Subject currentSub = new Subject(subjectList.get(i), subjectHours, points);
									currentSemesterNumber = student.getAllSemesters().size();

									if (isValidInt(points, 100, -1)) {
										if (points < 42 && points >= -1) {
											currentSub.setSemesterOfDelay(currentSemesterNumber);
											delayed.add(currentSub);
										}
										if (points != -1) {
											currentSemester.addSubject(currentSub);
										}
									} else {
										i -= 2;
										input.next();
										totalHours -= subjectHours; // Subtract the hours as it was invalid input
										continue;
									}
								} catch (InputMismatchException ime) {
									System.out.println("Invalid Input: please enter a number between 100 and 0 ONLY");
									input.next();
									i -= 2;
									totalHours -= Integer.parseInt(subjectList.get(i + 1)); // Subtract the hours as it was invalid input
								}
							}

							student.addSemester(currentSemester);
							calcResults(student);
							calcResultsInLetters(student);
							break;
						} else if (choice == 2) {
							break;
						}
					} else {
						System.out.println(invalidMessage);
						System.out.println(menue);
						input.next();
					}
				} catch (InputMismatchException e) {
					System.out.println(invalidMessage);
					input.next();
				}
			}

			input.close();
		}

		/**
		 * Validates if the given choice is either 1 or 2.
		 *
		 * @param choice the choice to validate
		 * @return true if the choice is 1 or 2, false otherwise
		 */
		public static boolean isValidChoice(int choice) {
			return choice == 1 || choice == 2;
		}

		/**
		 * Validates if the given integer is within the specified range.
		 *
		 * @param num the integer to validate
		 * @param upperRange the upper bound of the valid range
		 * @param lowerRange the lower bound of the valid range
		 * @return true if the number is within the range, false otherwise
		 */
		public static boolean isValidInt(int num, int upperRange, int lowerRange) {
			if (num >= lowerRange && num <= upperRange) {
				return true;
			} else { 
				System.out.println("Please input a valid number between " + lowerRange + " and " + upperRange + " OR Enter -1 to input the points later");
				return false; 
			}
    }








/**
 * 
 * including GPA, CGPA, and academic positions. It processes each semester's subjects, handles failed subjects,
 * and tracks cumulative hours and points.
 */
static ArrayList<Double> gpa = new ArrayList<>();
    static ArrayList<Double> cgpa = new ArrayList<>();
    static ArrayList<Double> semesterPoints = new ArrayList<>();
    static ArrayList<Double> semesterHours = new ArrayList<>();
    static ArrayList<Double> semesterCHours = new ArrayList<>();
    static ArrayList<String> results = new ArrayList<>();
    static ArrayList<String> academicPosition = new ArrayList<>();

    static int warnings = 0;

    static ArrayList<Integer> failedHours = new ArrayList<>();
    static Map<String, Integer> failureCount = new HashMap<>(); /* Keep track of failure in subjects in a hashmap */


 
 public static void calcResults(Student stud) {
	

	 failedHours.add(0);
	 double tempSemesterCHours = 0;
	 double tempCGpa = 0;

	 System.out.printf("%-15s %-15s %-10s %-20s %-20s  %-30s %-10s%n", 
		 "Semester Hours", "Semester Points", "GPA", "Academic position",
		 "Cumulative Hours", "Results", "CGPA"); /* The upper row in the complete result certificate */

	 for (int i = 0; i < stud.getAllSemesters().size(); i++) {

		 int tempFailedHours = 0;

		 double tempSemesterHours = 0;
		 double tempSemesterPoints = 0;

		 StringBuilder res = new StringBuilder(); /* Current semester results */
		 boolean hasFailedSubjects = false;

		 Semester currentSemester = stud.getSemester(i);

		 for (int j = 0; j < currentSemester.getAllSubjects().size(); j++) {

			 Subject currentSubject = currentSemester.getSubject(j);

			 if (currentSubject.getDelayed()) {
				 tempFailedHours += currentSubject.getHours();
			 } /* To remove additional failed hours in the printf for the cumulative hours */

			 if (currentSubject.getFailed()) {

				 tempFailedHours += currentSubject.getHours();
				 String subjectName = currentSubject.getName();
				 failureCount.put(subjectName, failureCount.getOrDefault(subjectName, 0) + 1); /* Set the subject's fail count in the hashmap */

				 int failures = failureCount.get(subjectName);
				 String failureMarker;

				 if (failures % 2 == 1) {

					 failureMarker = (failures / 2 + 1) + "F";
				 } else {

					 failureMarker = (failures / 2) + "R";
				 } /* Check how many times the subject has been failed, and assign R or F depending on that */

				 res.append(failureMarker).append(" ").append(subjectName).append(" "); /* The semester result section where it shows the failed subjects or pass */

				 hasFailedSubjects = true;
			 }

			 tempSemesterHours += currentSubject.getHours(); /* Add the current subject hours to the accumulated hours variable */
			 tempSemesterPoints += currentSubject.getCoursePoints(); /* Add the current subject points to the accumulated points variable */

		 }
		 failedHours.add(tempFailedHours);

		 if (!hasFailedSubjects) {
			 res.append("PASS");
		 }

		 tempSemesterCHours += tempSemesterHours;

		 semesterCHours.add(tempSemesterCHours); /* Add cumulative hours for each semester */
		 semesterHours.add(tempSemesterHours); /* Add the sum of all subject hours in a single semester */
		 semesterPoints.add(tempSemesterPoints); /* Add the sum of all subjects' points of a certain semester to the semesterPoints attribute */

		 gpa.add(semesterPoints.get(i) / semesterHours.get(i)); /* Calculate the GPA for each semester by dividing semester points and semester hours */
		 tempCGpa += semesterPoints.get(i);
		 cgpa.add((tempCGpa / semesterCHours.get(i))); /* Calculate CGPA by dividing the sum of semester points by the sum of semester hours */

		 results.add(res.toString().trim());

		 if (gpa.get(i) < 2.0) {
			 warnings += 1;
			 academicPosition.add(" " + Integer.toString(warnings) + " W ");
		 } /* Check warnings and add them if they exist */
		 else {

			 if (i == stud.getAllSemesters().size() - 1) { /* If last semester assign an Academic Grade */
				 double lastCgpa = cgpa.get(i);

				 if (lastCgpa >= 3.0) {
					 academicPosition.add("First Grade");

				 } else if (lastCgpa >= 2.70 && lastCgpa < 3.0) {
					 academicPosition.add("Second Grade- sec 1");

				 } else if (lastCgpa >= 2.40 && lastCgpa < 2.70) {
					 academicPosition.add("Second Grade- sec 2");

				 } else if (lastCgpa >= 2.0 && lastCgpa < 2.40) {
					 academicPosition.add("Third Grade"); 

				 }
			 } else {
				 academicPosition.add(" __ "); /* Set blank if no warning */
			 }
		 }

		 System.out.printf("%-15.2f  %-15.2f %-10.2f %-20s %-20.2f %-30s %-10.2f%n",
			 semesterHours.get(i),
			 semesterPoints.get(i), 
			 gpa.get(i),
			 academicPosition.get(i),
			 semesterCHours.get(i) - failedHours.get(i),
			 results.get(i),
			 cgpa.get(i));
	 }

 }

 /**
  * Displays the results of all semesters in a formatted manner.
  *
  * @param stud the student whose semester results are to be displayed.
  */
 public static void calcResultsInLetters(Student stud) {
	 for (Semester sem : stud.getAllSemesters()) {
		 System.out.println(sem); 
	 }
 }









	
}// end of main class



















/**
 * This class represents a subject taken by a student.
 * It contains the subject's name, points, grade, course points, 
 * failure status, hours, delay status, and other relevant information.
 */
 class Subject {
    private String name = "None";
    private double points = 0;
    private String grade = "None";
    private double coursePoints = 0;

    private boolean isFailed = false;
    private int hours = 0;
    private boolean isDelayed = false;
    private int semesterOfDelay;
    private int failureTimes = 0;
  
    /**
     * Constructor to initialize a subject with a name and hours.
     * @param name the name of the subject
     * @param hours the number of hours for the subject
     */
    Subject(String name, int hours) {
        this.name = name;
        this.hours = hours;
    }

    /** 
     * Constructor to initialize a subject with a name, hours, and points.
     * Also calculates the course points and sets the grade.
     * @param name the name of the subject
     * @param hours the number of hours for the subject
     * @param points the points achieved in the subject
     */
    Subject(String name, int hours, double points) {
        this.name = name;
        this.hours = hours;
        this.points = points;
        this.grade = getGrade();
        if (!(isFailed)) {
            coursePoints = (points / 25.0) * this.hours;
        } else {
            coursePoints = 0;
            setFailureTimes(getFailureTimes() + 1);
        }
    }

    /**
     * Gets the name of the subject.
     * @return the name of the subject
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the subject.
     * @param name the new name of the subject
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Checks if the subject is failed.
     * @return true if the subject is failed, false otherwise
     */
    public boolean getFailed() {
        return this.isFailed;
    }

    /**
     * Sets the points for the subject and recalculates the course points.
     * @param points the new points for the subject
     */
    public void setPoints(double points) {
        this.points = points;
        if (isFailed) {

            coursePoints = (points / 30.0) * this.hours;
        } else if (!(isDelayed)) {

            coursePoints = (points / 25.0) * this.hours;
        } else {
            
            coursePoints = 0;
        }
    }

    /**
     * Gets the points achieved in the subject.
     * @return the points achieved in the subject
     */
    public double getPoints() {
        return points;
    }

    /**
     * Determines and gets the grade for the subject based on the points.
     * @return the grade for the subject
     */
    public String getGrade() {
        if (this.points >= 90) {

            grade = "A+";
        }
        if (this.points >= 80 && this.points <= 89) {

            grade = "A";
        }
        if (this.points >= 70 && this.points <= 79) {
            
            grade = "B+";
        }
        if (this.points >= 60 && this.points <= 69) {
            grade = "B";
        }
        if (this.points >= 50 && this.points <= 59) {
            grade = "C";
        }
        if (this.points >= 42 && this.points <= 49) {
            grade = "D";
        }
        if (this.points < 42 && this.points >= 0) {
            grade = "F";
            isFailed = true;
        }
        if (this.points == -1.0) {
            grade = "delayed";
            isDelayed = true;
        }
        return grade;

    }

    /**
     * Gets the course points for the subject.
     * @return the course points for the subject
     */
    public double getCoursePoints() {

        return coursePoints;
    }

    /**
     * Gets the number of hours for the subject.
     * @return the number of hours for the subject
     */
    public int getHours() {
        return hours;
    }

    /**
     * Gets the semester of delay for the subject.
     * @return the semester of delay for the subject
     */
    public int getSemesterOfDelay() {
        return semesterOfDelay;
    }

    /**
     * Sets the semester of delay for the subject.
     * @param semesterOfDelay the new semester of delay for the subject
     */
    public void setSemesterOfDelay(int semesterOfDelay) {
        this.semesterOfDelay = semesterOfDelay;
    }

    /**
     * Gets the number of times the subject has been failed.
     * @return the number of times the subject has been failed
     */
    public int getFailureTimes() {
        return failureTimes;
    }

    /**
     * Sets the number of times the subject has been failed.
     * @param failureTimes the new number of times the subject has been failed
     */
    public void setFailureTimes(int failureTimes) {
        this.failureTimes = failureTimes;
    }

    /**
     * Checks if the subject is delayed.
     * @return true if the subject is delayed, false otherwise
     */
    public boolean getDelayed() {
        return this.isDelayed;

    }


    
}/*end of subject class */














/**
 * The Semester class represents a semester that contains a list of subjects, semester points,
 * academic position, and other relevant details.
 */
 class Semester {

    private int sNumber = 0;
    private ArrayList<Subject> semSubjects = new ArrayList<Subject>();
    private String academicPosition = " _ ";
    private String isPass = "";
    private double semseterPoints = 0;

    /**
     * Default constructor.
     */
    public Semester() { }

    /**
     * Constructor that initializes the semester number.
     *
     * @param sNumber the number of the semester.
     */
    public Semester(int sNumber) {
        this.sNumber = sNumber;
    }

    /**
     * Adds a subject to the list of subjects for this semester.
     *
     * @param sub the subject to be added.
     */
    public void addSubject(Subject sub) {
        semSubjects.add(sub);
    }

    /**
     * Gets the list of all subjects in this semester.
     *
     * @return the list of subjects.
     */
    public ArrayList<Subject> getAllSubjects() {
        return semSubjects;
    }

    /**
     * Gets a specific subject from the list of subjects by index.
     *
     * @param subNumber the index of the subject to retrieve.
     * @return the subject at the specified index.
     */
    public Subject getSubject(int subNumber) {
        return semSubjects.get(subNumber);
    }

    /**
     * Gets the total points for this semester.
     *
     * @return the total points for this semester.
     */
    public double getSemseterPoints() {
        return semseterPoints;
    }

    /**
     * Sets the total points for this semester.
     *
     * @param semseterPoints the total points to set.
     */
    public void setSemseterPoints(double semseterPoints) {
        this.semseterPoints = semseterPoints;
    }

    /**
     * Gets the academic position for this semester.
     *
     * @return the academic position.
     */
    public String getAcademicPosition() {
        return academicPosition;
    }

    /**
     * Sets the academic position for this semester.
     *
     * @param academicPosition the academic position to set.
     */
    public void setAcademicPosition(String academicPosition) {
        this.academicPosition = academicPosition;
    }

    /**
     * Returns a string representation of the semester, including subject names and grades.
     *
     * @return a string representing the semester's subjects and their grades.
     */

    public String toString() {
        StringBuilder subNames = new StringBuilder();
        StringBuilder subGrades = new StringBuilder();

        for (Subject subject : semSubjects) {
            subNames.append(String.format("%-25s", subject.getName())); // 15 characters wide for alignment
            subGrades.append(String.format("%-25s", subject.getGrade())); // Align grades with names
        }

        return subNames.toString() + "\n" + subGrades.toString();
    }
}// end of class Semester
















class Student {

    private String name = "";
    private ArrayList<Semester> semesters = new ArrayList<Semester>();

    /**
     * Default constructor.
     */
    public Student() {}

    /**
     * Constructor with the student's name.
     *
     * @param name the name of the student.
     */
    public Student(String name) {
        this.name = name;
    }

    /**
     * Constructor with the student's name and a list of semesters.
     *
     * @param name the name of the student.
     * @param semesters the list of semesters associated with the student.
     */
    public Student(String name, ArrayList<Semester> semesters) {
        this.name = name;
        this.semesters = semesters;
    }

    /**
     * Gets the name of the student.
     *
     * @return the name of the student.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the student.
     *
     * @param name the new name of the student.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds a semester to the student's list of semesters.
     *
     * @param oneSemester the semester to add.
     */
    public void addSemester(Semester oneSemester) {
        semesters.add(oneSemester);
    }


    /**
     * Gets a specific semester from the student's list of semesters.
     *
     * @param sem the index of the semester to retrieve.
     * @return the semester at the specified index.
     */
    public Semester getSemester(int sem) {
        return semesters.get(sem);
    }

    /**
     * Gets all semesters associated with the student.
     *
     * @return a list of all semesters.
     */
    public ArrayList<Semester> getAllSemesters() {
        return semesters;
    }


} // end of class Student




