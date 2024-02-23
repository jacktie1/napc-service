package org.apathinternational.faithpathrestful.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apathinternational.faithpathrestful.common.exception.BusinessException;
import org.apathinternational.faithpathrestful.common.exception.ValidationException;
import org.apathinternational.faithpathrestful.entity.AirportPickupAssignment;
import org.apathinternational.faithpathrestful.entity.Management;
import org.apathinternational.faithpathrestful.entity.Reference;
import org.apathinternational.faithpathrestful.entity.Role;
import org.apathinternational.faithpathrestful.entity.Student;
import org.apathinternational.faithpathrestful.entity.TempHousingAssignment;
import org.apathinternational.faithpathrestful.entity.User;
import org.apathinternational.faithpathrestful.entity.Volunteer;
import org.apathinternational.faithpathrestful.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ManagementService managementService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ReferenceService referenceService;

    @Autowired
    private UserService userService;

    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

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

    public List<Student> getStudentsWithAirportPickupNeeds(Boolean checkAssignmentStart, Boolean includeAssigned) {
        // Get the management to check if the assignment has started

        if(checkAssignmentStart)
        {   
            Management management = managementService.getManagement();

            if(management == null)
            {
                throw new BusinessException("Management not found. Please check the management and try again.");
            }

            if(!management.getDoesAssignmentStart())
            {
                List<Student> emptyList = List.of();
                return emptyList;
            }
        }

        if(includeAssigned)
        {
            return studentRepository.findStudentsWithAirportPickupNeeds();
        }
        else
        {
            return studentRepository.findUnassignedStudentsWithAirportPickupNeeds();
        }
    }

    public List<Student> getStudentsWithTempHousingNeeds(Boolean checkAssignmentStart, Boolean includeAssigned) {
        // Get the management to check if the assignment has started
        if(checkAssignmentStart)
        {   
            Management management = managementService.getManagement();

            if(management == null)
            {
                throw new BusinessException("Management not found. Please check the management and try again.");
            }

            if(!management.getDoesAssignmentStart())
            {
                List<Student> emptyList = List.of();
                return emptyList;
            }
        }

        if(includeAssigned)
        {
            return studentRepository.findStudentsWithTempHousingNeeds();
        }
        else
        {
            return studentRepository.findUnassignedStudentsWithTempHousingNeeds();
        }
    }

    public Student getStudentByUserId(Long userId) {
        User studentUser = userService.getUserById(userId);

        if(studentUser == null)
        {
            throw new BusinessException("User with id " + userId + " not found. Please check the user and try again.");
        }

        if(studentUser.isStudent())
        {
            return studentUser.getStudent();
        }
        else
        {
            throw new BusinessException("User with id " + userId + " is not a student. Please check the user and try again.");
        }
    }

    public void updateAirportPickupAssignment(Student student, Volunteer newAssignedVolunteer) {
        if(newAssignedVolunteer == null)
        {
            student.setAirportPickupAssignment(null);
        }
        else
        {
            AirportPickupAssignment newAirportPickupAssignment = new AirportPickupAssignment();
            newAirportPickupAssignment.setStudent(student);
            newAirportPickupAssignment.setVolunteer(newAssignedVolunteer);
            student.setAirportPickupAssignment(newAirportPickupAssignment);
        }
        
        studentRepository.save(student);
    }

    public void updateTempHousingAssignment(Student student, Volunteer newAssignedVolunteer) {
        if(newAssignedVolunteer == null)
        {
            student.setTempHousingAssignment(null);
        }
        else
        {
            TempHousingAssignment newTempHousingAssignment = new TempHousingAssignment();
            newTempHousingAssignment.setStudent(student);
            newTempHousingAssignment.setVolunteer(newAssignedVolunteer);
            student.setTempHousingAssignment(newTempHousingAssignment);
        }
        
        studentRepository.save(student);
    }

    public void deleteStudents(List<Long> studentIds) {
        studentRepository.deleteAllById(studentIds);
    }

    public Boolean isStudentAssignedToVolunteer(Student student, Volunteer volunteer) {
        if(student.getAirportPickupAssignment() != null && student.getAirportPickupAssignment().getVolunteer().getId() == volunteer.getId())
        {
            return true;
        }

        if(student.getTempHousingAssignment() != null && student.getTempHousingAssignment().getVolunteer().getId() == volunteer.getId())
        {
            return true;
        }

        return false;
    }

}
