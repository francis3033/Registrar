package registrar;

import java.util.ArrayList;

public class Courses {
   private String department;
   private int number;
   private int numSeats;
   private ArrayList<Student> students;

   public Courses(String department, int number, int numSeats) {
      this.department = department;
      this.number = number;
      this.numSeats = numSeats;
      students = new ArrayList<Student>();
   }

   @Override
   public boolean equals(Object o) {
      if (o == this) {
         return true;

      } else if (!(o instanceof Courses)) {
         return false;
      }

      Courses c = (Courses) o;
      return (c.department.equals(this.department)) && (c.number == this.number);
   }

   public boolean addStudent(Student student) {

      for (Student s : students) {
         if (student.equals(s) || students.size() >= numSeats) {
            return false;
         }
      }

      if (student.addCourse(this)) {
         students.add(student);
         return true;
      }
      return false;
   }

   public int numOfStudents() {
      return students.size();
   }

   public int numOfStudentsLast(String lastName) {
      int count = 0;

      for (Student s : students) {
         if (s.sameLast(lastName)) {
            count++;
         }
      }
      return count;

   }

   public boolean inCourse(Student student) {
      if (students.contains(student)) {
         return true;
      }
      return false;
   }

   public boolean dropStudent(Student student) {
      for (Student s : students) {
         if (student.equals(s)) {
            s.studentDropCourse(this);
               students.remove(s);
               return true;
            }
         }
      return false;
   }

   public void removeStudent(Student student) {
     Student temp = null;
      
      for (Student s : students) {
         if (student.equals(s)) {
            temp = s;
         }
      }
      students.remove(temp);
   }
}
