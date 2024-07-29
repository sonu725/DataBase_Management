package com.spring.orm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.orm.dao.StudentDao;
import com.spring.orm.enities.Student;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		System.out.println("programm started......");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/orm/config.xml");

		StudentDao studentDao = context.getBean("stDao", StudentDao.class);

		boolean flag = true;
		while (flag) {
			System.out.println("********************Welcome to Spring-ORM Project***********************");
			System.out.println("Press 1 for add new student");
			System.out.println("Press 2 for Display all students");
			System.out.println("Press 3 for get details of single student");
			System.out.println("Press 4 for delete student");
			System.out.println("Press 5 for Update Student");
			System.out.println("Press 6 for exit");

			/*	**************************************************************/

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int ch;
			try {
				ch = Integer.parseInt(br.readLine());

				switch (ch) {
				case 1:
					Student obj = new Student();
					System.out.println("Enter the student Id number");
					obj.setId(Integer.parseInt(br.readLine()));
					System.out.println("Enter the Student Name");
					obj.setName(br.readLine());
					try {
						int ans = studentDao.insert(obj);
						System.out.println("student inserted successfully with id number: " + ans);
					} catch (Exception e) {
						System.out.println("Student not inserted....." + e.getMessage());
					}
					break;
				case 2:
					try {
						List<Student> li = studentDao.getAllStudents();
						for (Student itr : li) {
							System.out.println("Student id:  " + itr.getId() + " " + "Student Name: " + itr.getName());
						}
					} catch (Exception e) {
						System.out.println("Student not found " + e.getMessage());
					}
					break;
				case 3:
					try {
						System.out.println("Enter the student id to show student details");
						int id = Integer.parseInt(br.readLine());
						Student obj1 = studentDao.getStudent(id);
						System.out.println("Student id:  " + obj1.getId() + " " + "Student Name: " + obj1.getName());
					} catch (Exception e) {
						System.out.println("Student not found " + e.getMessage());
					}

					break;
				case 4:
					try {
						System.out.println("Enter the student id to delete student details");
						int id = Integer.parseInt(br.readLine());
						Student obj1 = studentDao.getStudent(id);
						studentDao.delete(id);
						System.out.println("Student id:  " + obj1.getId() + " " + "Student Name: " + obj1.getName()
								+ " is deleted");
					} catch (Exception e) {
						System.out.println("Not Deleted " + e.getMessage());
					}
					break;
				case 5:
					try {
						System.out.println("Enter the student details to update the table");
						int id = Integer.parseInt(br.readLine());
						System.out.println("Enter the name w.r.t id wanting to change");
						String name = br.readLine();
						Student obj2 = new Student();
						obj2.setId(id);
						obj2.setName(name);
						studentDao.update(obj2);
						System.out.println("Student id:  " + obj2.getId() + " " + "Student Name: " + obj2.getName()
								+ " is Updated");
					} catch (Exception e) {
						System.out.println("Not Updated " + e.getMessage());
					}
					break;
				case 6:
					flag = false;
					break;

				default:
					System.out.println("you Entered Wrong Number, Enter Appropriate number");
					break;
				}

			} catch (Exception e) {
				System.out.println("Invalid input try with another one");
				System.out.println(e.getMessage());
			}
		}
		
		System.out.println("Thank You for using this application");
		context.close();

	}
}
