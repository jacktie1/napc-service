package org.apathinternational.faithpathrestful.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apathinternational.faithpathrestful.common.exception.BusinessException;
import org.apathinternational.faithpathrestful.common.exception.ValidationException;
import org.apathinternational.faithpathrestful.entity.Reference;
import org.apathinternational.faithpathrestful.entity.Role;
import org.apathinternational.faithpathrestful.entity.Student;
import org.apathinternational.faithpathrestful.entity.User;
import org.apathinternational.faithpathrestful.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ReferenceService referenceService;

    @Autowired
    private UserService userService;

    public Student createStudent(Student student) {
        Map<String, String> fieldErrors = new HashMap<String, String>();

        User studentUser = student.getUser();

        Role role = roleService.getRoleByName("Student");

        if(role == null)
        {
            throw new BusinessException("Invalid role. Please check the role and try again.");
        }

        Reference majorReference = student.getMajorReference();
        Reference arrivalAirlineReference = student.getArrivalAirlineReference();
        Reference departureAirlineReference = student.getDepartureAirlineReference();
        Reference apartmentReference = student.getApartmentReference();

        if(majorReference != null && referenceService.findById(majorReference.getId()) == null)
        {
            Reference savedMajorReferenced = referenceService.findById(majorReference.getId());

            if(savedMajorReferenced != null)
            {
                student.setMajorReference(savedMajorReferenced);
            }
            else
            {
                fieldErrors.put("majorReferenceId", "Invalid major reference. Please check the major reference and try again.");
            }
        }

        if(arrivalAirlineReference != null)
        {
            Reference savedArrivalAirlineReference = referenceService.findById(arrivalAirlineReference.getId());

            if(savedArrivalAirlineReference != null)
            {
                student.setArrivalAirlineReference(savedArrivalAirlineReference);
            }
            else
            {
                fieldErrors.put("arrivalAirlineReferenceId", "Invalid arrival airline reference. Please check the arrival airline reference and try again.");
            }
        }

        if(departureAirlineReference != null)
        {
            Reference savedDepartureAirlineReference = referenceService.findById(departureAirlineReference.getId());

            if(savedDepartureAirlineReference != null)
            {
                student.setDepartureAirlineReference(savedDepartureAirlineReference);
            }
            else
            {
                fieldErrors.put("departureAirlineReferenceId", "Invalid departure airline reference. Please check the departure airline reference and try again.");
            }
        }

        if(apartmentReference != null)
        {
            Reference savedApartmentReference = referenceService.findById(apartmentReference.getId());

            if(savedApartmentReference != null)
            {
                student.setApartmentReference(savedApartmentReference);
            }
            else
            {
                fieldErrors.put("apartmentReferenceId", "Invalid apartment reference. Please check the apartment reference and try again.");
            }
        }

        if(!fieldErrors.isEmpty())
        {
            throw new ValidationException("Validation error(s) encountered during student creation", fieldErrors);
        }

        studentUser.setRole(role);

        User savedUser = userService.createUser(studentUser);
    
        student.setUser(savedUser);
        
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentByUserId(Long userId) {
        User studentUser = userService.getUserById(userId);

        if(studentUser == null)
        {
            throw new BusinessException("User not found. Please check the user and try again.");
        }

        if(studentUser.isStudent())
        {
            return studentUser.getStudent();
        }
        else
        {
            throw new BusinessException("User is not a student. Please check the user and try again.");
        }
    }

    public void deleteStudents(List<Long> studentIds) {
        studentRepository.deleteAllById(studentIds);
    }
}
