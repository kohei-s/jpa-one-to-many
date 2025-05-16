package com.koheis.jpaonetomany.dao;

import com.koheis.jpaonetomany.entity.Instructor;
import com.koheis.jpaonetomany.entity.InstructorDetail;

public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetailById(int theId);
}
