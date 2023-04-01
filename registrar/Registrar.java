package registrar;

import java.util.ArrayList;

public class Registrar {
   private ArrayList<Courses> courses;
   private ArrayList<Student> students;

   /* This method is a constructor for the Registrar class, and it creates 
    * a new ArrayList for the courses objects and the student objects
    */
   public Registrar() {
      courses = new ArrayList<Courses>();
      students = new ArrayList<Student>();
   }

   /*This method is supposed to add a new course with the specific parameters 
    * given. First it creates a new Courses object with the parameters. Then, 
    * it should iterate through the courses ArrayList to check if there's already
    * a that course in the Registrar. Next it checks to ensure that both
    * 'number' and 'numSeats' aren't 0 or a negative number. Finally if both of
    * those are not negative or 0, then it will add it to the courses list and 
    * return a reference to the current object.
    */
   public Registrar addNewCourse(String department, int number, int numSeats) {
      Courses course = new Courses(department, number, numSeats);
      
     for (Courses c : courses) {
        if (c.equals(course)) {
           return this;
        }
     }
     
     if ((number > 0) && (numSeats > 0)) {
        courses.add(course);
     }
     
     return this;
  }

   /*This method creates temp course object to check if the list of courses 
    * contains the course. If it contains it, then you remove it from the 
    * courses list and return true. Otherwise, return false and does nothing.
    */
   public boolean cancelCourse(String department, int number) {
      Courses course = new Courses(department, number, 0);
      boolean ans = false;
      
      if (courses.contains(course)) {
         courses.remove(course);
         ans = true;
      }
      return ans;
   }

   /* This method returns the number of courses in the current object, so
    * I returned the size of the ArrayList. The size will also never give me a
    * negative negative number. 
    */
   public int numCourses() {
      return courses.size();
   }

   /* This method creates a new temp course object, and a new student object.
    * Then it checks if the new student object is in the list of students in 
    * the registrar. If the student isn't in the registrar, then it would be 
    * added to the list. Next, the method checks if the course requested for 
    * adding is in the registrar or not. If the course is in the registrar then,
    * it will find the right course in the list, and the right student in the 
    * list, and add the student to the respective course and ultimately return
    * true. If the parameters violate any of the requirements described in the 
    * methods of the Courses and Student classes, then it should return false 
    * and do nothing.
    */
   public boolean addToCourse(String department, int number, String firstName,
         String lastName) {
      Courses course = new Courses(department, number, 0);
      Student student = new Student(firstName, lastName);

      if (!(students.contains(student))) {
         students.add(student);
      }
      if (!(courses.contains(course))) {
         return false;
      } else {
         for (Courses c : courses) {
            if (course.equals(c)) {
               for (Student s : students) {
                  if (student.equals(s)) {
                     if (c.addStudent(s)) {
                        return true;
                     }
                     return false;
                  }
               }
            }
         }
      }
      return false;

   }

   /*This method first creates a temp course object with the parameters given,
    * then it will find the right course in the list of courses and return the 
    * number of students in the course. Also, if the course being requested 
    * isn't in the registrar, then -1 would be returned.
    */
   
   public int numStudentsInCourse(String department, int number) {
      Courses course = new Courses(department, number, 0);
      
      for (Courses c : courses) {
         if (course.equals(c)) {
            return c.numOfStudents();
         }
      }
      return -1;
   }

   /*This method first creates a temp course object with the parameters given,
    * then it will find the right course in the list of courses and return the 
    * number of students with the given last name that is in the course. Also, 
    * if the course being requested isn't in the registrar, then -1 would be
    * returned.
    */
   public int numStudentsInCourseWithLastName(String department, int number,
         String lastName) {
      Courses course = new Courses(department, number, 0);

      for (Courses c : courses) {
         if (course.equals(c)) {
            return c.numOfStudentsLast(lastName);
         }
      }
      return -1;
   }

   /* This method creates new course and student objects then it checks if the 
    * course is in the registrar or not. If it's not it will return false.
    * Otherwise, it will find the right course based on the parameters, and also
    * find the targeted student. If the student is in the specified course, then
    * the method will return true, otherwise, it will return false.
    */
   public boolean isInCourse(String department, int number, String firstName,
         String lastName) {
      Courses course = new Courses(department, number, 0);
      Student student = new Student(firstName, lastName);

      if (!(courses.contains(course))) {
         return false;
      } else {
         for (Courses c : courses) {
            if (course.equals(c)) {
               for (Student s : students) {
                  if (student.equals(s)) {
                     if (c.inCourse(s)) {
                        return true;
                     }
                     return false;
                  }
               }
            }
         }
      }
      return false;

   }

   /* This method creates a new student object with the given parameters. Next, 
    * the method will iterate through the list of students in the Registrar and
    * check if the student with the given parameters is in the list. Once the
    * right student is found, the method will return the number of courses
    * the student is taking. (This number will always be 0 to 5 inclusive)
    * Any other circumstances will have a return of 0.
    */
   public int howManyCoursesTaking(String firstName, String lastName) {
      Student student = new Student(firstName, lastName);
      
      for (Student s : students) {
         if (student.equals(s)) {
           return s.numOfCourses();
         }
      }
      return 0;
   }

   /* This method creates a new temp course and student objects. Checks if the 
    * course is in the registrar. If it's not, then it will return false and do
    * nothing. Otherwise, the method will go through all the courses and students
    * in the registrar to find the targeted course and student based on the 
    * parameters. Next the targted course and student will be called into a 
    * dropStudent method from the courses class. If the statement is true, then
    * the student would drop the course. Otherwise, the method is returning 
    * false.
    */
   public boolean dropCourse(String department, int number, String firstName,
         String lastName) {
      Courses course = new Courses(department, number, 0);
      Student student = new Student(firstName, lastName);

      if (!(courses.contains(course))) {
         return false;
      } else {
         for (Courses c : courses) {
            if (course.equals(c)) {
               for (Student s : students) {
                  if (student.equals(s)) {
                     c.dropStudent(s);
                     return true;
                  }
               }
            }
         }
      }
      return false;

   }

   /*
    */
   public boolean cancelRegistration(String firstName, String lastName) {
      Student student = new Student(firstName, lastName);
      boolean ans = false;
      if (!(students.contains(student))) {
         return false;
      } else {
         Student temp1 = null;
         for (Student s : students) {
            if (student.equals(s)) {
               temp1 = s;
            }
         }
         temp1.studentDropAllCourse();    
         students.remove(temp1);
         ans = true;
         }
      return ans;
   }
}
