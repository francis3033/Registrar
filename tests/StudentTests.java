package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import registrar.Registrar;

public class StudentTests {

  // write your student tests in this class
   
   
   //Testing if it doesn't add the same course twice.
   @Test
   public void testAddNewCourse1() {
      Registrar registrar = new Registrar();

      registrar.addNewCourse("CMSC", 131, 10);
      registrar.addNewCourse("CMSC", 131, 100);
      
      assertEquals(1, registrar.numCourses());
   }
   
   /* Testing if different departments can have the same number, and if the same 
   departments can have different numbers. */
   @Test
   public void testAddNewCourse2() {
      Registrar registrar = new Registrar();
      
      registrar.addNewCourse("CMSC", 131, 10).addNewCourse("CMSC", 132, 12);
      registrar.addNewCourse("PHYS", 131, 5);
      
      assertEquals(3, registrar.numCourses());
   }
   
   /* Testing if every registrar has its own set of courses, and that it doesn't
    * affect the other registrars.
    */
   @Test
   public void testAddNewCourse3() {
      Registrar registrar1 = new Registrar();
      Registrar registrar2 = new Registrar();
      
      registrar1.addNewCourse("PHYS", 131, 5);
      registrar2.addNewCourse("CMSC", 132, 10).addNewCourse("PHYS", 131, 5);
      
      assertEquals(2, registrar2.numCourses());
   }
   
   // Tests that you cant input 0 or a negative number for 'number' or 'numSeats'
   @Test
   public void testAddNewCourse4() {
      Registrar registrar = new Registrar();
      
      registrar.addNewCourse("PHYS", 131, 5).addNewCourse("MATH", 0,-5);
      
      assertEquals(1, registrar.numCourses());
   }
   
   // Tests if cancel course works for orginal case 
   @Test
   public void testCancelCourse() {
      Registrar registrar = new Registrar();
      
      registrar.addNewCourse("PHYS", 161, 10).addNewCourse("MATH", 141, 5);
      registrar.cancelCourse("MATH", 141);
      
      assertEquals(1, registrar.numCourses());
   }

   /* Tests if the method wouldn't work if the course doesn't exist in the
    * registrar.
    */
   @Test
   public void testCancelCourse2() {
      Registrar registrar = new Registrar();
      
      registrar.addNewCourse("PHYS", 161, 10).addNewCourse("MATH", 141, 5);
      registrar.cancelCourse("CMSC", 131);
      
      assertEquals(2, registrar.numCourses());
   }
   
   /* Tests if the method should allow me to add the same person object twice
    * to the same class.
    */
   @Test
   public void testAddToCourse1() {
      Registrar registrar = new Registrar();

      registrar.addNewCourse("PHYS", 161, 10);
      
      assertTrue(registrar.addToCourse("PHYS", 161, "Funny", "Frank"));
      assertFalse(registrar.addToCourse("PHYS", 161, "Funny", "Frank"));
   }
   
   /* Tests if the method should return false if a requested class is not a part
    * of the registrar.
    */
   @Test
   public void testAddToCourse2() {
      Registrar registrar = new Registrar();

      registrar.addNewCourse("PHYS", 161, 10).addNewCourse("MATH", 141, 5);
      
      assertFalse(registrar.addToCourse("CMSC", 131, "Funny", "Frank"));
   }
   
   /* Tests if the method doesn't allow the given student to add more than 5 
    * classes.
    */
   @Test
   public void testAddToCourse3() {
      Registrar registrar = new Registrar();

      registrar.addNewCourse("PHYS", 161, 10).addNewCourse("MATH", 141, 5);
      registrar.addNewCourse("CMSC", 131, 20).addNewCourse("ENGL", 101, 20);
      registrar.addNewCourse("ENES", 100, 12).addNewCourse("STAT", 100, 2);
      
      registrar.addToCourse("PHYS", 161, "Funny", "Frank");
      registrar.addToCourse("MATH", 141, "Funny", "Frank");
      registrar.addToCourse("CMSC", 131, "Funny", "Frank");
      registrar.addToCourse("ENGL", 101, "Funny", "Frank");
      registrar.addToCourse("ENES", 100, "Funny", "Frank");
      
      assertFalse(registrar.addToCourse("STAT", 100, "Funny", "Frank"));
   }
   
   /* Tests if the method doesn't allow for more students to be added when the
    * course has reached the seat limit.
    */
   @Test
   public void testAddToCourse4() {
      Registrar registrar = new Registrar();

      registrar.addNewCourse("PHYS", 161, 3);
      
      registrar.addToCourse("PHYS", 161, "Funny", "Frank");
      registrar.addToCourse("PHYS", 161, "Happy", "Harry");
      registrar.addToCourse("PHYS", 161, "Nervous", "Nancy");
      
      assertFalse(registrar.addToCourse("PHYS", 161, "Silly", "Sam"));
   }
   
   /* Tests if the method would return -1 if the course doesn't exist in the 
    * registrar. Also tests if the method counts the number of students in a 
    * course correctly too.
    */
   @Test
   public void testNumOfStudentsInCourse() {
      Registrar registrar = new Registrar();

      registrar.addNewCourse("PHYS", 161, 10);
      
      registrar.addToCourse("PHYS", 161, "Funny", "Frank");
      registrar.addToCourse("PHYS", 161, "Happy", "Harry");
      registrar.addToCourse("PHYS", 161, "Nervous", "Nancy");
      
      assertEquals(3, registrar.numStudentsInCourse("PHYS", 161));
      assertEquals(-1, registrar.numStudentsInCourse("CMSC", 250));
   }
   
   /* Tests if the method outputs the right number of students in the course 
    * with the target last name. It also checks if the method returns -1 if the
    * course doesn't exist in the registrar.
    */
   @Test
   public void testNumOfLastNames() {
      Registrar registrar = new Registrar();

      registrar.addNewCourse("PHYS", 161, 10);
      
      registrar.addToCourse("PHYS", 161, "Funny", "Frank");
      registrar.addToCourse("PHYS", 161, "Sam", "Silly");
      registrar.addToCourse("PHYS", 161, "Sarah", "Silly");
      
      assertEquals(2, registrar.numStudentsInCourseWithLastName("PHYS", 161, "Silly"));
      assertEquals(-1, registrar.numStudentsInCourseWithLastName("CMSC", 131, "Silly"));
   }
   
   /* Tests if the method checks if there's a course, but the student isn't in
    * it. It also checks if the method works how it's supposed to.
    */
   @Test
   public void testIsInCourse1() {
      Registrar registrar = new Registrar();

      registrar.addNewCourse("PHYS", 161, 10);
      registrar.addNewCourse("CMSC", 216, 5);
      
      registrar.addToCourse("PHYS", 161, "Funny", "Frank");
      
      assertFalse(registrar.isInCourse("CMSC", 216, "Funny", "Frank"));
      assertTrue(registrar.isInCourse("PHYS", 161, "Funny", "Frank"));
      
   }
   
   /* Tests if the method would return false if there isn't a course that matches
    * the parameters requirements.
    */
   @Test
   public void testIsInCourse2() {
      Registrar registrar = new Registrar();

      registrar.addNewCourse("PHYS", 161, 10);
      
      registrar.addToCourse("PHYS", 161, "Funny", "Frank");
      
      assertFalse(registrar.isInCourse("MATH", 142, "Funny", "Frank"));
   }
   
   /* Tests if the method would return 0 if the student described doesn't exist
    * in the registrar. It also tests if the method adds the courses to the
    * students lists of classes correctly.
    */
   @Test
   public void testHowManyCoursesTaking() {
      Registrar registrar = new Registrar();
      
      registrar.addNewCourse("PHYS", 161, 10).addNewCourse("MATH", 141, 5);
      registrar.addNewCourse("CMSC", 131, 20).addNewCourse("ENGL", 101, 20);
      registrar.addNewCourse("ENES", 100, 12);
      
      registrar.addToCourse("PHYS", 161, "Funny", "Frank");
      registrar.addToCourse("MATH", 141, "Funny", "Frank");
      registrar.addToCourse("CMSC", 131, "Funny", "Frank");
      registrar.addToCourse("ENGL", 101, "Funny", "Frank");
      
     assertEquals(0, registrar.howManyCoursesTaking("Silly", "Sam"));
     assertEquals(4, registrar.howManyCoursesTaking("Funny", "Frank"));
   }
   
   /* Tests if the method would return false if the course doesn't exist or if
    * the student isn't in the course.
    */
   @Test
   public void testDropCourse1() {
      Registrar registrar = new Registrar();

      registrar.addNewCourse("PHYS", 161, 10).addNewCourse("MATH", 141, 5);
      registrar.addNewCourse("CMSC", 131, 20).addNewCourse("ENGL", 101, 20);
      
      
      assertFalse(registrar.dropCourse("ENES", 140, "Funny", "Frank"));
      assertFalse(registrar.dropCourse("MATH", 141, "Funny", "Frank"));
   }
   
   /* Tests if the method takes the student out of the ocurse correctly and
    * returns true while doing so.
    */
   @Test
   public void testDropCourse2() {
      Registrar registrar = new Registrar();

      registrar.addNewCourse("PHYS", 161, 10).addNewCourse("MATH", 141, 5);
      registrar.addNewCourse("CMSC", 131, 20).addNewCourse("ENGL", 101, 20);
      
      registrar.addToCourse("PHYS", 161, "Funny", "Frank");
      
      assertTrue(registrar.dropCourse("PHYS", 161, "Funny", "Frank"));
      assertEquals(0, registrar.howManyCoursesTaking("Funny", "Frank"));
      
   }
   
   
   /* Tests if the method doesn't affect any other student in the class while 
    * dropping someone. It also checks if dropping a course doesn't affect any
    * of the other courses that the student is enrolled in.
    */
   @Test
   public void testDropCourse3() {
      Registrar registrar = new Registrar();

      registrar.addNewCourse("PHYS", 161, 10).addNewCourse("MATH", 141, 5);
      registrar.addNewCourse("CMSC", 131, 20).addNewCourse("ENGL", 101, 20);
      
      registrar.addToCourse("PHYS", 161, "Funny", "Frank");
      registrar.addToCourse("CMSC", 131, "Funny", "Frank");
      registrar.addToCourse("CMSC", 131, "Silly", "Sam");
      
      assertTrue(registrar.dropCourse("PHYS", 161, "Funny", "Frank"));
      assertTrue(registrar.isInCourse("CMSC", 131, "Silly", "Sam"));
      assertEquals(1, registrar.howManyCoursesTaking("Funny", "Frank"));
      
   }
   
   /* Tests if the method checks if it's possible to add students to the course
    * after someone has be dropped.
    */
   @Test
   public void testDropCourse4() {
      Registrar registrar = new Registrar();

      registrar.addNewCourse("PHYS", 161, 10).addNewCourse("MATH", 141, 5);
      registrar.addNewCourse("CMSC", 131, 1).addNewCourse("ENGL", 101, 20);
      
      registrar.addToCourse("PHYS", 161, "Funny", "Frank");
      registrar.addToCourse("CMSC", 131, "Funny", "Frank");
      
      registrar.dropCourse("CMSC", 131, "Funny", "Frank");
      
      assertTrue(registrar.addToCourse("CMSC", 131, "Silly", "Sam"));
   }
   
   @Test
   public void testing() {
      ArrayList<String> intList = new ArrayList<>();
      intList.add("YES");
      intList.add("NO");
      
      System.out.println(intList.size());
      
      intList.remove("YES");
      
      System.out.println(intList.size());
   }
}
