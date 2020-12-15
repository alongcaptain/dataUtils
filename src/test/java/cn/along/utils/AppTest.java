package cn.along.utils;

import cn.along.bean.Classroom;
import cn.along.bean.Student;
import cn.along.bean.Teacher;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public static void main(String[] args) {
        List<Classroom> wholeClassRoomList = wholeClassRoomList();
        List<Classroom> partClassRoomList = partClassRoomList();
        List<Teacher> partTeacherList = partTeacherList();
        List<Classroom> adList = BeanMathUtils.substract(wholeClassRoomList, partClassRoomList, (w)->{return w.getId() + w.getName();}, (p)->{return p.getId()+ p.getName();});
        System.out.println("wholeClassRoomList(id+name) - partClassRoomList(id+name) = adList : " + adList);
        List<Classroom> aList = BeanMathUtils.substract(wholeClassRoomList, partClassRoomList, Classroom::getName, Classroom::getName);
        System.out.println("wholeClassRoomList(name) - partClassRoomList(name) = aList is "+ aList);
        List<Classroom> dList = BeanMathUtils.substract(wholeClassRoomList, partTeacherList, Classroom::getId, Teacher::getId);
        System.out.println("wholeClassRoomList(id) - partTeacherList(id) = dList is "+ dList);
        List<Classroom> bcList = BeanMathUtils.retain(wholeClassRoomList, partClassRoomList, Classroom::getName, Classroom::getName);
        System.out.println("wholeClassRoomList(name) ∩ partClassRoomList(name) = bcList is "+ bcList);

        List<Student> studentList = partStudentList();
        List<Student> oneKeyDistinct = BeanMathUtils.distinct(studentList, o -> o.getClassId());
        System.out.println("oneKey distinct list is " + oneKeyDistinct);

        List<Student> twoKeyDisstinct = BeanMathUtils.distinct(studentList, o -> o.getId() + o.getClassId());
        System.out.println("two key distinct list is " + twoKeyDisstinct);

    }

    private static List<Classroom> partClassRoomList() {
        List<Classroom> partClassRoom = new ArrayList<>();
        partClassRoom.add(Classroom.builder().id("2").name("B").build());
        partClassRoom.add(Classroom.builder().id("3").name("C").build());
        partClassRoom.add(Classroom.builder().id("5").name("D").build());
        return partClassRoom;
    }

    private static List<Classroom> wholeClassRoomList() {
        List<Classroom> wholeClassRoom = new ArrayList<>();
        wholeClassRoom.add(Classroom.builder().id("1").name("A").build());
        wholeClassRoom.add(Classroom.builder().id("2").name("B").build());
        wholeClassRoom.add(Classroom.builder().id("3").name("C").build());
        wholeClassRoom.add(Classroom.builder().id("4").name("D").build());
        return wholeClassRoom;
    }

    private static List<Teacher> partTeacherList() {
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(Teacher.builder().id("1").name("John").build());
        teacherList.add(Teacher.builder().id("2").name("Sam").build());
        teacherList.add(Teacher.builder().id("3").name("James").build());
        return teacherList;
    }

    private static List<Student> partStudentList() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(Student.builder().id("1").classId("1").name("Kobe").build());
        studentList.add(Student.builder().id("2").classId("1").name("Shark").build());
        studentList.add(Student.builder().id("3").classId("2").name("James").build());
        studentList.add(Student.builder().id("4").classId("2").name("Love").build());
        studentList.add(Student.builder().id("5").classId("3").name("Green").build());
        studentList.add(Student.builder().id("5").classId("3").name("Deny").build());

        return studentList;
    }





}
