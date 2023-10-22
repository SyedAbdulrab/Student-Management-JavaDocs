import java.util.Scanner;

/**
 * Represents a Student with a name and CMS ID.
 */
class Student {
    private String name;
    private int CMS;

    /**
     * Constructs a Student object with a given name and CMS ID.
     * @param name The name of the student.
     * @param CMS The CMS ID of the student.
     */
    public Student(String name, int CMS) {
        this.name = name;
        this.CMS = CMS;
    }

    /**
     * Gets the name of the student.
     * @return The name of the student.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the student.
     * @param name The new name of the student.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the CMS ID of the student.
     * @return The CMS ID of the student.
     */
    public int getCMS() {
        return CMS;
    }

    /**
     * Sets the CMS ID of the student.
     * @param CMS The new CMS ID of the student.
     */
    public void setCMS(int CMS) {
        this.CMS = CMS;
    }
}

/**
 * Represents an Undergraduate (UG) student, which is a type of Student.
 */
class UGstudent extends Student {
    private int UGsemester;

    /**
     * Constructs an UGstudent object with a given name, CMS ID, and UG semester.
     * @param name The name of the UG student.
     * @param CMS The CMS ID of the UG student.
     * @param UGsemester The semester of study for the UG student.
     */
    public UGstudent(String name, int CMS, int UGsemester) {
        super(name, CMS);
        this.UGsemester = UGsemester;
    }

    /**
     * Gets the semester of study for the UG student.
     * @return The semester of study for the UG student.
     */
    public int getUGsemester() {
        return UGsemester;
    }

    /**
     * Sets the semester of study for the UG student.
     * @param UGsemester The new semester of study for the UG student.
     */
    public void setUGsemester(int UGsemester) {
        this.UGsemester = UGsemester;
    }
}

/**
 * Represents a Postgraduate (PG) student, which is a type of Student.
 */
class PGstudent extends Student {

    private int PGsemester;

    /**
     * Constructs a PGstudent object with a given name, CMS ID, and PG semester.
     * @param name The name of the PG student.
     * @param CMS The CMS ID of the PG student.
     * @param PGsemester The semester of study for the PG student.
     */
    public PGstudent(String name, int CMS, int PGsemester) {
        super(name, CMS);
        this.PGsemester = PGsemester;
    }

    /**
     * Gets the semester of study for the PG student.
     * @return The semester of study for the PG student.
     */
    public int getPGsemester() {
        return PGsemester;
    }

    /**
     * Sets the semester of study for the PG student.
     * @param PGsemester The new semester of study for the PG student.
     */
    public void setPGsemester(int PGsemester) {
        this.PGsemester = PGsemester;
    }
}

/**
 * Represents a custom exception for when a student is not found.
 */
class StudentNotFoundException extends Exception {

    /**
     * Constructs a StudentNotFoundException with a given message.
     * @param message The error message.
     */
    public StudentNotFoundException(String message) {
        super(message);
    }
}

/**
 * Represents a class for managing an array of students.
 */
class StudentManagement {
    static Student[] stdArray;

    /**
     * Constructs a StudentManagement object with an array to hold students.
     */
    public StudentManagement() {
        stdArray = new Student[10];
    }

    /**
     * Searches for a student with a given CMS ID.
     * @param id The CMS ID of the student to search for.
     * @throws StudentNotFoundException if the student is not found.
     */
    void searchRecord(int id) throws StudentNotFoundException {
        boolean completed = true;
        for (Student i: stdArray) {
            if (i == null) {
                continue;
            }
            if (i.getCMS() == id) {
                completed = false;
                System.out.println();
                System.out.println("Student's name: " + i.getName());
                System.out.println("CMS ID of student: " + i.getCMS());
                if (i instanceof PGstudent) {
                    System.out.println("Semester of study: " + ((PGstudent) i).getPGsemester());
                    break;
                } else {
                    System.out.println("Semester of study: " + ((UGstudent) i).getUGsemester());
                    break;
                }
            }

        }
        if (completed == true) {
            throw new StudentNotFoundException("No student found!");
        }
    }

    /**
     * Adds a student record to the array.
     * @param student The student to be added.
     * @throws IndexOutOfBoundsException if there is insufficient space in the array.
     */
    void addRecord(Student student) throws IndexOutOfBoundsException {
        boolean completed = false;
        for (int i = 0; i < 10; i++) {
            if (stdArray[i] == null) {
                completed = true;
                stdArray[i] = student;

            }
        }

        if (!completed) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Deletes a student record with a given CMS ID.
     * @param name The CMS ID of the student to be deleted.
     * @throws StudentNotFoundException if the student is not found.
     */
    void DeleteRecord(int name) throws StudentNotFoundException {
        boolean completed = false;
        for (int i = 0; i < 10; i++) {
            if (stdArray[i] == null) {
                continue;
            }
            if (stdArray[i].getCMS() == name) {
                completed = true;
                stdArray[i] = null;
            }
        }
        if (!completed) {
            throw new StudentNotFoundException("No student found!");
        }
    }
}

/**
 * Represents a Student Management System with user interaction.
 */
public class StudentManagementSystem {
    public static void main(String[] args) {

        StudentManagement class1 = new StudentManagement();
        Scanner scan = new Scanner(System.in);

        while (true) {
            try {
                System.out.println();
                System.out.println("Hello!\nType 1 for entering data, 2 to search the database, and 3 to delete an entry");
                int command = scan.nextInt();

                if (command == 1) {
                    System.out.println("Please enter student name: ");
                    String name = scan.next();

                    System.out.println("Please enter CMS ID: ");
                    int id = scan.nextInt();

                    System.out.println("Please enter semester of study: ");
                    int semester = scan.nextInt();

                    System.out.println("Type 1 to store UG, and 2 to store PG");
                    int type = scan.nextInt();

                    if (type == 1) {
                        PGstudent p1 = new PGstudent(name, id, semester);
                        class1.addRecord(p1);

                    } else {
                        UGstudent u1 = new UGstudent(name, id, semester);
                        class1.addRecord(u1);
                    }

                } else if (command == 2) {
                    System.out.println("Please enter desired student's CMS ID: ");
                    int n1Ref = scan.nextInt();
                    class1.searchRecord(n1Ref);
                } else if (command == 3) {
                    System.out.println("Please enter the CMS ID of the entry you wish to delete: ");
                    int n2Ref = scan.nextInt();
                    class1.DeleteRecord(n2Ref);
                }

            } catch (StudentNotFoundException e) {
                System.out.println("No student found!");
            }

            catch (IndexOutOfBoundsException e) {
                System.out.println("Insufficient space!");
            }
        }
    }
}
