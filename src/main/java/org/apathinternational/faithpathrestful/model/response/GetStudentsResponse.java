package org.apathinternational.faithpathrestful.model.response;

import java.util.ArrayList;
import java.util.List;

import org.apathinternational.faithpathrestful.entity.Student;
import org.apathinternational.faithpathrestful.model.entityDTO.StudentDTO;


public class GetStudentsResponse {
    private List<StudentDTO> students;

    public GetStudentsResponse() {
    }

    public List<StudentDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDTO> students) {
        this.students = students;
    }

    public void setStudentsFromStudentList(List<Student> students) {
        this.students = new ArrayList<StudentDTO>();
        
        for(Student student : students)
        {
            StudentDTO studentDTO = new StudentDTO(student);
            this.students.add(studentDTO);
        }
    }
    
}
