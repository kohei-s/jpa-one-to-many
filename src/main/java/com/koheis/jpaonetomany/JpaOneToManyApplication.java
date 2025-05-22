package com.koheis.jpaonetomany;

import com.koheis.jpaonetomany.dao.AppDAO;
import com.koheis.jpaonetomany.entity.Course;
import com.koheis.jpaonetomany.entity.Instructor;
import com.koheis.jpaonetomany.entity.InstructorDetail;
import com.koheis.jpaonetomany.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class JpaOneToManyApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaOneToManyApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {

		return runner -> {
			// createCourseAndReviews(appDAO);
			// retrieveCourseAndReviews(appDAO);

			deleteCourseAndReviews(appDAO);
		};
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {

		int theId = 10;
		System.out.println("Deleting course id: " + theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		// get the course and reviews
		int theId = 10;
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

		// print the course
		System.out.println(tempCourse);

		// print the reviews
		System.out.println(tempCourse.getReviews());
		System.out.println("Done!");
	}

	private void createCourseAndReviews(AppDAO appDAO) {

		// create a course
		Course tempCourse = new Course("Pacman - How to Score One Million Points");

		// add some reviews
		tempCourse.addReview(new Review("Great course!!"));
		tempCourse.addReview(new Review("Cool course, job well done."));
		tempCourse.addReview(new Review("What a dumb course!"));

		// save the course ... and leverage the cascade all
		System.out.println("Saving the course: " + tempCourse);
		System.out.println("The reviews: " + tempCourse.getReviews());

		appDAO.save(tempCourse);
		System.out.printf("Done!");
	}

	private void deleteCourse(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Deleting course id: " + theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done!");
	}

	private void updateCourse(AppDAO appDAO) {

		int theId = 10;

		// find the course
		System.out.println("Finding course id: " + theId);
		Course tempCourse = appDAO.findCourseById(theId);

		// update the course
		System.out.println("Updating course id: " + theId);
		tempCourse.setTitle("TESTING");

		appDAO.update(tempCourse);
		System.out.println("Done!");
	}

	private void updateInstructor(AppDAO appDAO) {

		int theId = 2;

		// find the instructor
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		// update the instructor
		System.out.println("Updating instructor id: " + theId);
		tempInstructor.setLastName("TESTER");

		appDAO.update(tempInstructor);
		System.out.println("Done!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

		int theId = 2;
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);
		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());
		System.out.println("Done!");

	}

	private void findCousesForInstructor(AppDAO appDAO) {
		int theId = 2;

		// find instructor
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: " + tempInstructor);

		// find courses for instructor
		System.out.println("Finding courses for instructor id: " + theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		// associate the objects
		tempInstructor.setCourses(courses);
		System.out.println("the associated courses: " + tempInstructor.getCourses());
		System.out.println("Done!");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId = 2;
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());
		System.out.println("Done!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {

		// create the instructor
		Instructor tempInstructor = new Instructor("Susan", "Public", "susan@test.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail(
				"Susa's Youtube Channel",
				"Yoga");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// create some courses
		Course tempCourse1 = new Course("Air Guitar");
		Course tempCourse2 = new Course("The Pinball Masterclass");

		// add courses to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		// save the instructor
		// NOTE: this will ALSO save the courses
		// because of the CascadeType.PERSIST
		System.out.println("Saving instructor: " + tempInstructor);
		System.out.println("The courses: " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);

		System.out.println("Done!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId = 3;
		System.out.println("Deleting instructor detail id: " + theId);
		appDAO.deleteInstructorDetailById(theId);
		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int theId = 2;
		System.out.println("Finding instructor detail id: " + theId);
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);
		System.out.println("tempInstructordetail: " + tempInstructorDetail);
		System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());
		System.out.println("Done!");
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 2;
		System.out.println("Deleting instructor id: " + theId);
		appDAO.deleteInstructorById(theId);

		System.out.println("Done!");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId = 2;
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated instructorDetail only: " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {

        /*
        // create the instructor
        Instructor tempInstructor = new Instructor("Chad", "Derby", "derby@test.com");

        // create the instructor detail
        InstructorDetail tempInstructorDetail = new InstructorDetail(
                "Chad's Youtube Channel",
                "Coding");
         */

		// create the instructor
		Instructor tempInstructor = new Instructor("Madhu", "Patel", "madhu@test.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail(
				"Madhu's Youtube Channel",
				"Guitar");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor
		// NOTE: this will ALSO save the detail object
		// because of the CascadeType.ALL
		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done!");
	}

}
