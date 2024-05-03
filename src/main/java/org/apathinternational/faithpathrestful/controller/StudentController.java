package org.apathinternational.faithpathrestful.controller;

import java.util.ArrayList;
import java.util.List;

import org.apathinternational.faithpathrestful.common.exception.BusinessException;
import org.apathinternational.faithpathrestful.common.exception.CustomAccessDeniedException;
import org.apathinternational.faithpathrestful.entity.Reference;
import org.apathinternational.faithpathrestful.entity.Student;
import org.apathinternational.faithpathrestful.entity.User;
import org.apathinternational.faithpathrestful.entity.UserSecurityQuestion;
import org.apathinternational.faithpathrestful.entity.Volunteer;
import org.apathinternational.faithpathrestful.model.entityDTO.AirportPickupAssignmentDTO;
import org.apathinternational.faithpathrestful.model.entityDTO.StudentCommentDTO;
import org.apathinternational.faithpathrestful.model.entityDTO.StudentDTO;
import org.apathinternational.faithpathrestful.model.entityDTO.StudentFlightInfoDTO;
import org.apathinternational.faithpathrestful.model.entityDTO.StudentProfileDTO;
import org.apathinternational.faithpathrestful.model.entityDTO.StudentTempHousingDTO;
import org.apathinternational.faithpathrestful.model.entityDTO.TempHousingAssignmentDTO;
import org.apathinternational.faithpathrestful.model.entityDTO.UserAccountDTO;
import org.apathinternational.faithpathrestful.model.request.RegisterStudentRequest;
import org.apathinternational.faithpathrestful.model.request.UpdateStudentAirportPickupAssignmentRequest;
import org.apathinternational.faithpathrestful.model.request.UpdateStudentCommentRequest;
import org.apathinternational.faithpathrestful.model.request.UpdateStudentFlightInfoRequest;
import org.apathinternational.faithpathrestful.model.request.UpdateStudentProfileRequest;
import org.apathinternational.faithpathrestful.model.request.UpdateStudentTempHousingAssignmentRequest;
import org.apathinternational.faithpathrestful.model.request.UpdateStudentTempHousingRequest;
import org.apathinternational.faithpathrestful.model.response.GetStudentResponse;
import org.apathinternational.faithpathrestful.model.response.GetStudentsResponse;
import org.apathinternational.faithpathrestful.model.response.MessageReponse;
import org.apathinternational.faithpathrestful.response.ResponseHandler;
import org.apathinternational.faithpathrestful.service.SessionService;
import org.apathinternational.faithpathrestful.service.StudentService;
import org.apathinternational.faithpathrestful.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/student")
@Validated
public class StudentController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StudentService studentService;

    @Autowired
    private VolunteerService volunteerService;

    @Autowired
    private SessionService sessionService;

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<?> register(@Valid @RequestBody RegisterStudentRequest request) {

        UserAccountDTO userAccount = request.getUserAccount();

        StudentProfileDTO studentProfile = request.getStudentProfile();

        StudentFlightInfoDTO studentFlightInfo = request.getStudentFlightInfo();

        StudentTempHousingDTO studentTempHousing = request.getStudentTempHousing();

        StudentCommentDTO studentComment = request.getStudentComment();
        
        User encryptedUser = new User();
        encryptedUser.setUsername(userAccount.getUsername());
        encryptedUser.setEmailAddress(studentProfile.getEmailAddress());
        encryptedUser.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        encryptedUser.setFirstName(studentProfile.getFirstName());
        encryptedUser.setLastName(studentProfile.getLastName());
        encryptedUser.setGender(studentProfile.getGender());

        UserSecurityQuestion userSecurityQuestion1 = new UserSecurityQuestion();
        UserSecurityQuestion userSecurityQuestion2 = new UserSecurityQuestion();
        UserSecurityQuestion userSecurityQuestion3 = new UserSecurityQuestion();

        Reference securityQuestionReference1 = new Reference();
        Reference securityQuestionReference2 = new Reference();
        Reference securityQuestionReference3 = new Reference();

        securityQuestionReference1.setId(userAccount.getSecurityQuestionReferenceId1());
        securityQuestionReference2.setId(userAccount.getSecurityQuestionReferenceId2());
        securityQuestionReference3.setId(userAccount.getSecurityQuestionReferenceId3());
        userSecurityQuestion1.setSecurityQuestionReference(securityQuestionReference1);
        userSecurityQuestion1.setSecurityAnswer(passwordEncoder.encode(userAccount.getSecurityAnswer1().trim().toUpperCase()));
        userSecurityQuestion2.setSecurityQuestionReference(securityQuestionReference2);
        userSecurityQuestion2.setSecurityAnswer(passwordEncoder.encode(userAccount.getSecurityAnswer2().trim().toUpperCase()));
        userSecurityQuestion3.setSecurityQuestionReference(securityQuestionReference3);
        userSecurityQuestion3.setSecurityAnswer(passwordEncoder.encode(userAccount.getSecurityAnswer3().trim().toUpperCase()));

        List<UserSecurityQuestion> securityQuestions = new ArrayList<UserSecurityQuestion>();
        securityQuestions.add(userSecurityQuestion1);
        securityQuestions.add(userSecurityQuestion2);
        securityQuestions.add(userSecurityQuestion3);

        encryptedUser.setSecurityQuestions(securityQuestions);

        Student studentUser = new Student();
        studentUser.setUser(encryptedUser);
        studentUser.setEnglishName(studentProfile.getEnglishName());
        studentUser.setIsNewStudent(studentProfile.getIsNewStudent());
        studentUser.setStudentType(studentProfile.getStudentType());
        studentUser.setGraduatesFrom(studentProfile.getGraduatesFrom());
        studentUser.setCustomMajor(studentProfile.getCustomMajor());
        studentUser.setHasCompanion(studentProfile.getHasCompanion());
        studentUser.setWechatId(studentProfile.getWechatId());
        studentUser.setCnPhoneNumber(studentProfile.getCnPhoneNumber());
        studentUser.setUsPhoneNumber(studentProfile.getUsPhoneNumber());
        studentUser.setAttendsWeekOfWelcome(studentProfile.getAttendsWeekOfWelcome());
        studentUser.setNeedsAirportPickup(studentFlightInfo.getNeedsAirportPickup());
        studentUser.setHasFlightInfo(studentFlightInfo.getHasFlightInfo());
        studentUser.setArrivalFlightNumber(studentFlightInfo.getArrivalFlightNumber());
        studentUser.setCustomArrivalAirline(studentFlightInfo.getCustomArrivalAirline());
        studentUser.setArrivalDatetime(studentFlightInfo.getArrivalDatetime());
        studentUser.setDepartureFlightNumber(studentFlightInfo.getDepartureFlightNumber());
        studentUser.setCustomDepartureAirline(studentFlightInfo.getCustomDepartureAirline());
        studentUser.setDepartureDatetime(studentFlightInfo.getDepartureDatetime());
        studentUser.setNumLgLuggages(studentFlightInfo.getNumLgLuggages());
        studentUser.setNumSmLuggages(studentFlightInfo.getNumSmLuggages());
        studentUser.setNeedsTempHousing(studentTempHousing.getNeedsTempHousing());
        studentUser.setNumNights(studentTempHousing.getNumNights());
        studentUser.setCustomDestinationAddress(studentTempHousing.getCustomDestinationAddress());
        studentUser.setContactName(studentTempHousing.getContactName());
        studentUser.setContactPhoneNumber(studentTempHousing.getContactPhoneNumber());
        studentUser.setContactEmailAddress(studentTempHousing.getContactEmailAddress());
        studentUser.setStudentComment(studentComment.getStudentComment());
        studentUser.setAdminComment(studentComment.getAdminComment());

        if(studentProfile.getMajorReferenceId() != null) {
            Reference majorReference = new Reference();
            majorReference.setId(studentProfile.getMajorReferenceId());
            studentUser.setMajorReference(majorReference);
        }

        if(studentFlightInfo.getArrivalAirlineReferenceId() != null) {
            Reference arrivalAirlineReference = new Reference();
            arrivalAirlineReference.setId(studentFlightInfo.getArrivalAirlineReferenceId());
            studentUser.setArrivalAirlineReference(arrivalAirlineReference);
        }

        if(studentFlightInfo.getDepartureAirlineReferenceId() != null) {
            Reference departureAirlineReference = new Reference();
            departureAirlineReference.setId(studentFlightInfo.getDepartureAirlineReferenceId());
            studentUser.setDepartureAirlineReference(departureAirlineReference);
        }

        if(studentTempHousing.getApartmentReferenceId() != null) {
            Reference apartmentReference = new Reference();
            apartmentReference.setId(studentTempHousing.getApartmentReferenceId());
            studentUser.setApartmentReference(apartmentReference);
        }

        studentService.createStudent(studentUser);

        return ResponseHandler.generateResponse(new MessageReponse("Student User created successfully."));
    }

    @GetMapping("/getStudents")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getStudents(
        @RequestParam(required = false, defaultValue = "false") Boolean includeTempHousingAssignment,
        @RequestParam(required = false, defaultValue = "false") Boolean includeAirportPickupAssignment,
        @RequestParam(required = false, defaultValue = "false") Boolean excludeDisabled,
        @RequestParam(required = false, defaultValue = "false") Boolean needsAirportPickup,
        @RequestParam(required = false, defaultValue = "false") Boolean needsTempHousing
    ) {
        List<Student> students = studentService.getAllStudents();

        if(excludeDisabled == true) {
            students.removeIf(student -> student.getUser().getEnabled() == false);
        }

        if(needsAirportPickup == true) {
            students.removeIf(student -> student.getNeedsAirportPickup() == false);
        }

        if(needsTempHousing == true) {
            students.removeIf(student -> student.getNeedsTempHousing() == false);
        }

        GetStudentsResponse response = new GetStudentsResponse();

        List<StudentDTO> studentDTOs = new ArrayList<StudentDTO>();

        for(Student student : students)
        {
            StudentDTO studentDTO = new StudentDTO(student);

            if(includeAirportPickupAssignment == true) {
                studentDTO.setAirportPickupAssignmentFromStudentEntity(student);
            }

            if(includeTempHousingAssignment == true) {
                studentDTO.setTempHousingAssignmentFromStudentEntity(student);
            }

            studentDTOs.add(studentDTO);
        }

        response.setStudents(studentDTOs);

        return ResponseHandler.generateResponse(response);
    }

    @GetMapping("/getStudent/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT') or hasRole('ROLE_VOLUNTEER')")
    public ResponseEntity<?> getStudent(@PathVariable(required=true, name="userId") Long userId) {
        User authedUser = sessionService.getAuthedUser();

        if(authedUser.isStudent() && !authedUser.getId().equals(userId)) {
            throw new CustomAccessDeniedException("You are not authorized to view this student.");
        }

        Student student = studentService.getStudentByUserId(userId);

        if(student == null) {
            throw new BusinessException("User is found but student data is missing.");
        }
        
        if(authedUser.isVolunteer())
        {
            Volunteer authedVolunteer = volunteerService.getVolunteerByUserId(authedUser.getId());     
            
            if(studentService.isStudentAssignedToVolunteer(student, authedVolunteer) == false) {
                throw new CustomAccessDeniedException("You are not authorized to view this student.");
            }
        }

        StudentDTO studentDTO = new StudentDTO(student);

        // If the user is a volunteer, we don't want to return the user account information
        if(authedUser.isVolunteer())
        {
            UserAccountDTO nullUserAccountDTO = null;
            studentDTO.setUserAccount(nullUserAccountDTO);
        }

        GetStudentResponse response = new GetStudentResponse();

        response.setStudent(studentDTO);

        return ResponseHandler.generateResponse(response);
    }

    @GetMapping("/getAirportPickupNeeds")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VOLUNTEER')")
    public ResponseEntity<?> getAirportPickupNeeds() {
        User authedUser = sessionService.getAuthedUser();

        List<Student> students;

        if(authedUser.isAdmin()){
            // not to check the assignment start
            // include the assigned students
            students = studentService.getStudentsWithAirportPickupNeeds(false, true);
        } else if (authedUser.isVolunteer()) {
            // to check the assignment start
            // not to include the assigned students
            students = studentService.getStudentsWithAirportPickupNeeds(true, false);
        } else  {
            throw new CustomAccessDeniedException("You are not authorized to access this resource.");
        }

        List<StudentDTO> studentDTOs = new ArrayList<StudentDTO>();

        for(Student student : students)
        {
            StudentDTO studentDTO;

            if(authedUser.isAdmin()) {
                studentDTO = new StudentDTO(student);

                studentDTO.setAirportPickupAssignmentFromStudentEntity(student);
                studentDTOs.add(studentDTO);
            }
            // only return the necessary information for the volunteer
            else if (authedUser.isVolunteer()) {
                studentDTO = new StudentDTO();

                UserAccountDTO userAccount = new UserAccountDTO();
                StudentFlightInfoDTO studentFlightInfo = new StudentFlightInfoDTO(student);
                StudentProfileDTO studentProfile = new StudentProfileDTO();
                
                userAccount.setUserId(student.getUser().getId());
    
                if(student.getMajorReference() != null)
                {
                    studentProfile.setMajorReferenceId(student.getMajorReference().getId());
                }
                else
                {
                    studentProfile.setCustomMajor(student.getCustomMajor());
                }
    
                studentProfile.setIsNewStudent(student.getIsNewStudent());
    
                studentDTO.setUserAccount(userAccount);
                studentDTO.setStudentProfile(studentProfile);
                studentDTO.setStudentFlightInfo(studentFlightInfo);

                studentDTOs.add(studentDTO);
            } else {
                throw new CustomAccessDeniedException("You are not authorized to access this resource.");
            }
        }

        GetStudentsResponse response = new GetStudentsResponse();

        response.setStudents(studentDTOs);

        return ResponseHandler.generateResponse(response);
    }

    @GetMapping("/getTempHousingNeeds")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VOLUNTEER')")
    public ResponseEntity<?> getTempHousingNeeds() {
        User authedUser = sessionService.getAuthedUser();

        List<Student> students;

        if(authedUser.isAdmin()){
            // not to check the assignment start
            // include the assigned students
            students = studentService.getStudentsWithTempHousingNeeds(false, true);
        } else if (authedUser.isVolunteer()) {
            // to check the assignment start
            // not to include the assigned students
            students = studentService.getStudentsWithTempHousingNeeds(true, false);
        } else  {
            throw new CustomAccessDeniedException("You are not authorized to access this resource.");
        }

        List<StudentDTO> studentDTOs = new ArrayList<StudentDTO>();

        for(Student student : students)
        {
            StudentDTO studentDTO;

            if(authedUser.isAdmin()) {
                studentDTO = new StudentDTO(student);

                studentDTO.setTempHousingAssignmentFromStudentEntity(student);
                studentDTOs.add(studentDTO);
            }
            // only return the necessary information for the volunteer
            else if (authedUser.isVolunteer()) {
                studentDTO = new StudentDTO();

                UserAccountDTO userAccount = new UserAccountDTO();
                StudentFlightInfoDTO studentFlightInfo = new StudentFlightInfoDTO(student);
                StudentProfileDTO studentProfile = new StudentProfileDTO();
                
                userAccount.setUserId(student.getUser().getId());
    
                if(student.getMajorReference() != null)
                {
                    studentProfile.setMajorReferenceId(student.getMajorReference().getId());
                }
                else
                {
                    studentProfile.setCustomMajor(student.getCustomMajor());
                }
    
                studentProfile.setIsNewStudent(student.getIsNewStudent());
    
                studentDTO.setUserAccount(userAccount);
                studentDTO.setStudentProfile(studentProfile);
                studentDTO.setStudentFlightInfo(studentFlightInfo);

                studentDTOs.add(studentDTO);
            } else {
                throw new CustomAccessDeniedException("You are not authorized to access this resource.");
            }
        }

        GetStudentsResponse response = new GetStudentsResponse();

        response.setStudents(studentDTOs);

        return ResponseHandler.generateResponse(response);
    }

    @GetMapping("/getProfile/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT')")
    public ResponseEntity<?> getProfile(@PathVariable(required=true, name="userId") Long userId) {
        User authedUser = sessionService.getAuthedUser();

        if(authedUser.isStudent() && !authedUser.getId().equals(userId)) {
            throw new CustomAccessDeniedException("You are not authorized to view this student.");
        }

        Student student = studentService.getStudentByUserId(userId);

        if(student == null) {
            throw new BusinessException("User is found but student data is missing.");
        }

        GetStudentResponse response = new GetStudentResponse();

        response.setStudentProfile(student);

        return ResponseHandler.generateResponse(response);
    }

    @GetMapping("/getFlightInfo/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT')")
    public ResponseEntity<?> getFlightInfo(@PathVariable(required=true, name="userId") Long userId) {
        User authedUser = sessionService.getAuthedUser();

        if(authedUser.isStudent() && !authedUser.getId().equals(userId)) {
            throw new CustomAccessDeniedException("You are not authorized to view this student.");
        }

        Student student = studentService.getStudentByUserId(userId);

        if(student == null) {
            throw new BusinessException("User is found but student data is missing.");
        }

        GetStudentResponse response = new GetStudentResponse();

        response.setStudentFlightInfo(student);

        return ResponseHandler.generateResponse(response);
    }

    @GetMapping("/getTempHousing/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT')")
    public ResponseEntity<?> getTempHousing(@PathVariable(required=true, name="userId") Long userId) {
        User authedUser = sessionService.getAuthedUser();

        if(authedUser.isStudent() && !authedUser.getId().equals(userId)) {
            throw new CustomAccessDeniedException("You are not authorized to view this student.");
        }

        Student student = studentService.getStudentByUserId(userId);

        if(student == null) {
            throw new BusinessException("User is found but student data is missing.");
        }

        GetStudentResponse response = new GetStudentResponse();

        response.setStudentTempHousing(student);

        return ResponseHandler.generateResponse(response);
    }

    @GetMapping("/getComment/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT')")
    public ResponseEntity<?> getComment(@PathVariable(required=true, name="userId") Long userId) {
        User authedUser = sessionService.getAuthedUser();

        if(authedUser.isStudent() && !authedUser.getId().equals(userId)) {
            throw new CustomAccessDeniedException("You are not authorized to access this resource.");
        }

        Student student = studentService.getStudentByUserId(userId);

        if(student == null) {
            throw new BusinessException("User is found but student data is missing.");
        }

        GetStudentResponse response = new GetStudentResponse();

        response.setStudentComment(student);

        return ResponseHandler.generateResponse(response);
    }

    @GetMapping("/getAirportPickupAssignment/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT')")
    public ResponseEntity<?> getAirportPickupAssignment(@PathVariable(required=true, name="userId") Long userId) {
        User authedUser = sessionService.getAuthedUser();

        if(authedUser.isStudent() && !authedUser.getId().equals(userId)) {
            throw new CustomAccessDeniedException("You are not authorized to access this resource.");
        }

        Student student = studentService.getStudentByUserId(userId);

        if(student == null) {
            throw new BusinessException("User is found but student data is missing.");
        }

        StudentDTO studentDTO = new StudentDTO(student);

        AirportPickupAssignmentDTO airportPickupAssignmentDTO = new AirportPickupAssignmentDTO();

        if(student.getAirportPickupAssignment() != null)
        {
            airportPickupAssignmentDTO.setStudentUserId(userId);
            airportPickupAssignmentDTO.setVolunteerUserId(student.getAirportPickupAssignment().getVolunteer().getUser().getId());
            airportPickupAssignmentDTO.setVolunteerFromVolunteerEntity(student.getAirportPickupAssignment().getVolunteer());

            if(authedUser.isStudent()) {
                // If the user is a student, we don't want to return the user account information
                airportPickupAssignmentDTO.getVolunteer().setUserAccount(null);
            }
        }

        studentDTO.setAirportPickupAssignment(airportPickupAssignmentDTO);

        GetStudentResponse response = new GetStudentResponse();

        response.setStudent(studentDTO);

        return ResponseHandler.generateResponse(response);
    }

    @GetMapping("/getTempHousingAssignment/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT')")
    public ResponseEntity<?> getTempHousingAssignment(@PathVariable(required=true, name="userId") Long userId) {
        User authedUser = sessionService.getAuthedUser();

        if(authedUser.isStudent() && !authedUser.getId().equals(userId)) {
            throw new CustomAccessDeniedException("You are not authorized to access this resource.");
        }

        Student student = studentService.getStudentByUserId(userId);

        if(student == null) {
            throw new BusinessException("User is found but student data is missing.");
        }

        StudentDTO studentDTO = new StudentDTO(student);

        TempHousingAssignmentDTO tempHousingAssignmentDTO = new TempHousingAssignmentDTO();

        if(student.getTempHousingAssignment() != null)
        {
            tempHousingAssignmentDTO.setStudentUserId(userId);
            tempHousingAssignmentDTO.setVolunteerUserId(student.getTempHousingAssignment().getVolunteer().getUser().getId());
            tempHousingAssignmentDTO.setVolunteerFromVolunteerEntity(student.getTempHousingAssignment().getVolunteer());

            if(authedUser.isStudent()) {
                // If the user is a student, we don't want to return the user account information
                tempHousingAssignmentDTO.getVolunteer().setUserAccount(null);
            }
        }

        studentDTO.setTempHousingAssignment(tempHousingAssignmentDTO);

        GetStudentResponse response = new GetStudentResponse();

        response.setStudent(studentDTO);

        return ResponseHandler.generateResponse(response);
    }

    @PutMapping("/updateProfile/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT')")
    @Transactional
    public ResponseEntity<?> updateProfile(@PathVariable(required=true, name="userId") Long userId, @Valid @RequestBody UpdateStudentProfileRequest request) {
        User authedUser = sessionService.getAuthedUser();

        if(authedUser.isStudent() && !authedUser.getId().equals(userId)) {
            throw new CustomAccessDeniedException("You are not authorized to modify this resource.");
        }

        StudentProfileDTO studentProfile = request.getStudentProfile();

        Student student = studentService.getStudentByUserId(userId);

        if(student == null) {
            throw new BusinessException("User is found but student data is missing.");
        }

        User studentUser = student.getUser();

        // changes will be automatically saved to the database because of the @Transactional annotation
        studentUser.setFirstName(studentProfile.getFirstName());
        studentUser.setLastName(studentProfile.getLastName());
        studentUser.setGender(studentProfile.getGender());
        studentUser.setEmailAddress(studentProfile.getEmailAddress());

        student.setEnglishName(studentProfile.getEnglishName());
        student.setIsNewStudent(studentProfile.getIsNewStudent());
        student.setStudentType(studentProfile.getStudentType());
        student.setGraduatesFrom(studentProfile.getGraduatesFrom());
        student.setHasCompanion(studentProfile.getHasCompanion());
        student.setWechatId(studentProfile.getWechatId());
        student.setCnPhoneNumber(studentProfile.getCnPhoneNumber());
        student.setUsPhoneNumber(studentProfile.getUsPhoneNumber());
        student.setAttendsWeekOfWelcome(studentProfile.getAttendsWeekOfWelcome());

        if(studentProfile.getMajorReferenceId() != null) {
            Reference majorReference = new Reference();
            majorReference.setId(studentProfile.getMajorReferenceId());
            student.setMajorReference(majorReference);
            student.setCustomMajor(null);
        } else if (studentProfile.getCustomMajor() != null) {
            student.setMajorReference(null);
            student.setCustomMajor(studentProfile.getCustomMajor());
        }

        return ResponseHandler.generateResponse(new MessageReponse("Profile updated successfully."));
    }
    
    
    @PutMapping("/updateFlightInfo/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT')")
    @Transactional
    public ResponseEntity<?> updateFlightInfo(@PathVariable(required=true, name="userId") Long userId, @Valid @RequestBody UpdateStudentFlightInfoRequest request) {
        User authedUser = sessionService.getAuthedUser();

        if(authedUser.isStudent() && !authedUser.getId().equals(userId)) {
            throw new CustomAccessDeniedException("You are not authorized to modify this resource.");
        }

        StudentFlightInfoDTO studentFlightInfo = request.getStudentFlightInfo();

        Student student = studentService.getStudentByUserId(userId);

        if(student == null) {
            throw new BusinessException("User is found but student data is missing.");
        }

        // changes will be automatically saved to the database because of the @Transactional annotation
        student.setNeedsAirportPickup(studentFlightInfo.getNeedsAirportPickup());
        student.setHasFlightInfo(studentFlightInfo.getHasFlightInfo());
        student.setArrivalFlightNumber(studentFlightInfo.getArrivalFlightNumber());
        student.setArrivalDatetime(studentFlightInfo.getArrivalDatetime());
        student.setDepartureFlightNumber(studentFlightInfo.getDepartureFlightNumber());
        student.setDepartureDatetime(studentFlightInfo.getDepartureDatetime());
        student.setNumLgLuggages(studentFlightInfo.getNumLgLuggages());
        student.setNumSmLuggages(studentFlightInfo.getNumSmLuggages());

        if(studentFlightInfo.getArrivalAirlineReferenceId() != null) {
            Reference arrivalAirlineReference = new Reference();
            arrivalAirlineReference.setId(studentFlightInfo.getArrivalAirlineReferenceId());
            student.setArrivalAirlineReference(arrivalAirlineReference);
            student.setCustomArrivalAirline(null);
        } else if (studentFlightInfo.getCustomArrivalAirline() != null) {
            student.setArrivalAirlineReference(null);
            student.setCustomArrivalAirline(studentFlightInfo.getCustomArrivalAirline());
        }

        if(studentFlightInfo.getDepartureAirlineReferenceId() != null) {
            Reference departureAirlineReference = new Reference();
            departureAirlineReference.setId(studentFlightInfo.getDepartureAirlineReferenceId());
            student.setDepartureAirlineReference(departureAirlineReference);
            student.setCustomDepartureAirline(null);
        } else if (studentFlightInfo.getCustomDepartureAirline() != null) {
            student.setDepartureAirlineReference(null);
            student.setCustomDepartureAirline(studentFlightInfo.getCustomDepartureAirline());
        }

        return ResponseHandler.generateResponse(new MessageReponse("Flight info updated successfully."));
    }

    @PutMapping("/updateTempHousing/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT')")
    @Transactional
    public ResponseEntity<?> updateTempHousing(@PathVariable(required=true, name="userId") Long userId, @Valid @RequestBody UpdateStudentTempHousingRequest request) {
        User authedUser = sessionService.getAuthedUser();

        if(authedUser.isStudent() && !authedUser.getId().equals(userId)) {
            throw new CustomAccessDeniedException("You are not authorized to modify this resource.");
        }

        Student student = studentService.getStudentByUserId(userId);

        if(student == null) {
            throw new BusinessException("User is found but student data is missing.");
        }

        StudentTempHousingDTO studentTempHousing = request.getStudentTempHousing();

        // changes will be automatically saved to the database because of the @Transactional annotation
        student.setNeedsTempHousing(studentTempHousing.getNeedsTempHousing());
        student.setNumNights(studentTempHousing.getNumNights());
        student.setContactName(studentTempHousing.getContactName());
        student.setContactPhoneNumber(studentTempHousing.getContactPhoneNumber());
        student.setContactEmailAddress(studentTempHousing.getContactEmailAddress());

        if(studentTempHousing.getApartmentReferenceId() != null) {
            Reference apartmentReference = new Reference();
            apartmentReference.setId(studentTempHousing.getApartmentReferenceId());
            student.setApartmentReference(apartmentReference);
            student.setCustomDestinationAddress(null);
        } else if (studentTempHousing.getCustomDestinationAddress() != null) {
            student.setApartmentReference(null);
            student.setCustomDestinationAddress(studentTempHousing.getCustomDestinationAddress());
        }

        if(studentTempHousing.getApartmentReferenceId() != null) {
            Reference apartmentReference = new Reference();
            apartmentReference.setId(studentTempHousing.getApartmentReferenceId());
            student.setApartmentReference(apartmentReference);
        }

        return ResponseHandler.generateResponse(new MessageReponse("Temp housing updated successfully."));
    }

    @PutMapping("/updateComment/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT')")
    @Transactional
    public ResponseEntity<?> updateComment(@PathVariable(required=true, name="userId") Long userId, @Valid @RequestBody UpdateStudentCommentRequest request) {
        User authedUser = sessionService.getAuthedUser();

        if(authedUser.isStudent() && !authedUser.getId().equals(userId)) {
            throw new CustomAccessDeniedException("You are not authorized to modify this resource.");
        }

        Student student = studentService.getStudentByUserId(userId);

        if(student == null) {
            throw new BusinessException("User is found but student data is missing.");
        }

        StudentCommentDTO studentComment = request.getStudentComment();

        student.setStudentComment(studentComment.getStudentComment());

        // Only admin can update the admin comment
        if(authedUser.isAdmin()) {
            student.setAdminComment(studentComment.getAdminComment());
        }

        return ResponseHandler.generateResponse(new MessageReponse("Comment updated successfully."));
    }

    @PutMapping("/updateAirportPickupAssignment/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public ResponseEntity<?> updateAirportPickupAssignment(@PathVariable(required=true, name="userId") Long userId, @Valid @RequestBody UpdateStudentAirportPickupAssignmentRequest request) {
        User authedUser = sessionService.getAuthedUser();

        if(!authedUser.isAdmin()) {
            throw new CustomAccessDeniedException("You are not authorized to modify this resource.");
        }

        Student student = studentService.getStudentByUserId(userId);

        if(student == null) {
            throw new BusinessException("User is found but student data is missing.");
        }

        Long volunteerUserId = request.getVolunteerUserId();

        Volunteer volunteerUser = null;

        if(volunteerUserId != null) {
            volunteerUser = volunteerService.getVolunteerByUserId(volunteerUserId);
        } 

        studentService.updateAirportPickupAssignment(student, volunteerUser);

        return ResponseHandler.generateResponse(new MessageReponse("Airport pickup assignment updated successfully."));
    }

    @PutMapping("/updateTempHousingAssignment/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public ResponseEntity<?> updateTempHousingAssignment(@PathVariable(required=true, name="userId") Long userId, @Valid @RequestBody UpdateStudentTempHousingAssignmentRequest request) {
        User authedUser = sessionService.getAuthedUser();

        if(!authedUser.isAdmin()) {
            throw new CustomAccessDeniedException("You are not authorized to modify this resource.");
        }

        Student student = studentService.getStudentByUserId(userId);

        if(student == null) {
            throw new BusinessException("User is found but student data is missing.");
        }

        Long volunteerUserId = request.getVolunteerUserId();

        Volunteer volunteerUser = null;

        if(volunteerUserId != null) {
            volunteerUser = volunteerService.getVolunteerByUserId(volunteerUserId);
        } 

        studentService.updateTempHousingAssignment(student, volunteerUser);

        return ResponseHandler.generateResponse(new MessageReponse("Airport pickup assignment updated successfully."));
    }
    
}
