package registrar;

import java.util.ArrayList;

public class Student {
   private String firstName;
   private String lastName;
   private ArrayList<Courses> studentCourses;

   public Student(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
      studentCourses = new ArrayList<Courses>();
   }

   public boolean addCourse(Courses course) {
      if (studentCourses.size() >= 5) {
         return false;
      } else {
         studentCourses.add(course);
         return true;
      }
   }

   public int numOfCourses() {
      return studentCourses.size();
   }

   public boolean sameLast(String lastName) {
      if (lastName.equals(this.lastName)) {
         return true;
      }
      return false;
   }

  
   @Override
   public boolean equals(Object o) {
      if (o == this) {
         return true;

      } else if (!(o instanceof Student)) {
         return false;
      }

      Student s = (Student) o;
      return (s.firstName.equals(this.firstName)) &&
            (s.lastName.equals(this.lastName));
   }

   public void studentDropCourse(Courses course) {
      studentCourses.remove(course);
//      for (Courses c : studentCourses) {
//         if (course.equals(c)) {
//            studentCourses.remove(course);
//            return true;
//         }
//      }
//      return false;
   }

  // public void removeAllCourses() {
     
         
  //       studentCourses.remove(c);
     
 //  }

 
 public void removeAnyCourse() {
    if (!(studentCourses.isEmpty())) {
       for (Courses c : studentCourses) {
          studentCourses.remove(0);
          c.dropStudent(this);
       }
    }
 }

 public void studentDropAllCourse() {
    for (Courses c : studentCourses) {
       c.removeStudent(this);
    }
 }
}
