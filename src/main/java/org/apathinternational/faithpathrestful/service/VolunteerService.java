package org.apathinternational.faithpathrestful.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.apathinternational.faithpathrestful.common.exception.BusinessException;
import org.apathinternational.faithpathrestful.common.exception.ValidationException;
import org.apathinternational.faithpathrestful.entity.AirportPickupAssignment;
import org.apathinternational.faithpathrestful.entity.AirportPickupPreference;
import org.apathinternational.faithpathrestful.entity.Role;
import org.apathinternational.faithpathrestful.entity.Student;
import org.apathinternational.faithpathrestful.entity.TempHousingAssignment;
import org.apathinternational.faithpathrestful.entity.Volunteer;
import org.apathinternational.faithpathrestful.entity.User;
import org.apathinternational.faithpathrestful.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VolunteerService {

    @Autowired
    VolunteerRepository volunteerRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    private final EmailService emailService;

    @Autowired
    public VolunteerService(EmailService emailService) {
        this.emailService = emailService;
    }

    public Volunteer createVolunteer(Volunteer volunteer) {
        Map<String, String> fieldErrors = new HashMap<String, String>();

        User volunteerUser = volunteer.getUser();

        Role role = roleService.getRoleByName("Volunteer");

        if(role == null)
        {
            throw new BusinessException("Invalid role. Please check the role and try again.");
        }

        if(!fieldErrors.isEmpty())
        {
            throw new ValidationException("Validation error(s) encountered during volunteer creation", fieldErrors);
        }

        volunteerUser.setRole(role);

        User savedUser = userService.createUser(volunteerUser);
    
        volunteer.setUser(savedUser);
        
        return volunteerRepository.save(volunteer);
    }

    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    public Volunteer getVolunteerByUserId(Long userId) {
        User volunteerUser = userService.getUserById(userId);

        if(volunteerUser == null)
        {
            throw new BusinessException("User with id " + userId + " not found. Please check the user and try again.");
        }

        if(volunteerUser.isVolunteer())
        {
            return volunteerUser.getVolunteer();
        }
        else
        {
            throw new BusinessException("User with id " + userId + " is not a volunteer. Please check the user and try again.");
        }
    }

    public void updateAirportPickupPreferences(Volunteer volunteer, List<Student> newPreferredStudents) {
        // Get the new preference list
        // Use HashSet and HashMap to make some operations O(1) instead of O(n)
        HashSet<Long> newPreferredStudentIds = new HashSet<Long>();
        Map<Long, Student> newPreferredStudentMap = new HashMap<Long, Student>();

        for (Student student : newPreferredStudents) {
            newPreferredStudentIds.add(student.getId());
            newPreferredStudentMap.put(student.getId(), student);
        }
        
        // Get the current preference list
        // Use HashSet and HashMap to make some operations O(1) instead of O(n)
        HashSet<Long> currentPreferredStudentIds = new HashSet<Long>();
        Map<Long, AirportPickupPreference> currentPreferredStudentMap = new HashMap<Long, AirportPickupPreference>();

        List<AirportPickupPreference> currentAirportPickupPreferences = volunteer.getAirportPickupPreferences();

        for (AirportPickupPreference airportPickupPreference : currentAirportPickupPreferences) {
            currentPreferredStudentIds.add(airportPickupPreference.getStudent().getId());
            currentPreferredStudentMap.put(airportPickupPreference.getStudent().getId(), airportPickupPreference);
        }

        HashSet<Long> studentsToRemove = new HashSet<Long>(currentPreferredStudentIds);
        studentsToRemove.removeAll(newPreferredStudentIds);

        for (Long studentId : studentsToRemove) {
            AirportPickupPreference airportPickupPreferenceToRemove = currentPreferredStudentMap.get(studentId);
            currentAirportPickupPreferences.remove(airportPickupPreferenceToRemove);
        }

        HashSet<Long> studentsToAdd = new HashSet<Long>(newPreferredStudentIds);
        studentsToAdd.removeAll(currentPreferredStudentIds);

        for (Long studentId : studentsToAdd) {
            Student student = newPreferredStudentMap.get(studentId);
            AirportPickupPreference airportPickupPreferenceToAdd = new AirportPickupPreference();
            airportPickupPreferenceToAdd.setStudent(student);
            airportPickupPreferenceToAdd.setVolunteer(volunteer);
            currentAirportPickupPreferences.add(airportPickupPreferenceToAdd);
        }

        if(studentsToAdd.isEmpty() && studentsToRemove.isEmpty())
        {
            return;
        }

        volunteerRepository.save(volunteer);
    }

    public void updateAirportPickupAssignments(Volunteer volunteer, List<Student> newAssignedStudents) {
        List<User> usersToNotify = new ArrayList<User>();

        // Get the new preference list
        // Use HashSet and HashMap to make some operations O(1) instead of O(n)
        HashSet<Long> newAssignedStudentIds = new HashSet<Long>();
        Map<Long, Student> newAssignedStudentMap = new HashMap<Long, Student>();

        for (Student student : newAssignedStudents) {
            newAssignedStudentIds.add(student.getId());
            newAssignedStudentMap.put(student.getId(), student);
        }
        
        // Get the current preference list
        // Use HashSet and HashMap to make some operations O(1) instead of O(n)
        HashSet<Long> currentAssignedStudentIds = new HashSet<Long>();
        Map<Long, AirportPickupAssignment> currentAssignedStudentMap = new HashMap<Long, AirportPickupAssignment>();

        List<AirportPickupAssignment> currentAirportPickupAssignments = volunteer.getAirportPickupAssignments();

        for (AirportPickupAssignment airportPickupAssignment : currentAirportPickupAssignments) {
            currentAssignedStudentIds.add(airportPickupAssignment.getStudent().getId());
            currentAssignedStudentMap.put(airportPickupAssignment.getStudent().getId(), airportPickupAssignment);
        }

        HashSet<Long> studentsToRemove = new HashSet<Long>(currentAssignedStudentIds);
        studentsToRemove.removeAll(newAssignedStudentIds);

        for (Long studentId : studentsToRemove) {
            AirportPickupAssignment airportPickupAssignmentToRemove = currentAssignedStudentMap.get(studentId);
            // Remove the assignment from both the student and the volunteer to make sure the relationship is orphaned
            // and thus removed
            airportPickupAssignmentToRemove.getStudent().setAirportPickupAssignment(null);
            currentAirportPickupAssignments.remove(airportPickupAssignmentToRemove);

            usersToNotify.add(airportPickupAssignmentToRemove.getStudent().getUser());
        }

        HashSet<Long> studentsToAdd = new HashSet<Long>(newAssignedStudentIds);
        studentsToAdd.removeAll(currentAssignedStudentIds);

        for (Long studentId : studentsToAdd) {
            Student student = newAssignedStudentMap.get(studentId);
            AirportPickupAssignment airportPickupAssignmentToAdd = new AirportPickupAssignment();
            airportPickupAssignmentToAdd.setStudent(student);
            airportPickupAssignmentToAdd.setVolunteer(volunteer);
            currentAirportPickupAssignments.add(airportPickupAssignmentToAdd);

            usersToNotify.add(student.getUser());
        }

        if(studentsToAdd.isEmpty() && studentsToRemove.isEmpty())
        {
            return;
        }

        usersToNotify.add(volunteer.getUser());

        for (User user : usersToNotify) {
            emailService.sendAirportPickupAssignmentUpdateMessage(user);
        }
    }

    public void updateTempHousingAssignments(Volunteer volunteer, List<Student> newAssignedStudents) {
        List<User> usersToNotify = new ArrayList<User>();

        // Get the new preference list
        // Use HashSet and HashMap to make some operations O(1) instead of O(n)
        HashSet<Long> newAssignedStudentIds = new HashSet<Long>();
        Map<Long, Student> newAssignedStudentMap = new HashMap<Long, Student>();

        for (Student student : newAssignedStudents) {
            newAssignedStudentIds.add(student.getId());
            newAssignedStudentMap.put(student.getId(), student);
        }
        
        // Get the current preference list
        // Use HashSet and HashMap to make some operations O(1) instead of O(n)
        HashSet<Long> currentAssignedStudentIds = new HashSet<Long>();
        Map<Long, TempHousingAssignment> currentAssignedStudentMap = new HashMap<Long, TempHousingAssignment>();

        List<TempHousingAssignment> currentTempHousingAssignments = volunteer.getTempHousingAssignments();

        for (TempHousingAssignment tempHousingAssignment : currentTempHousingAssignments) {
            currentAssignedStudentIds.add(tempHousingAssignment.getStudent().getId());
            currentAssignedStudentMap.put(tempHousingAssignment.getStudent().getId(), tempHousingAssignment);
        }

        HashSet<Long> studentsToRemove = new HashSet<Long>(currentAssignedStudentIds);
        studentsToRemove.removeAll(newAssignedStudentIds);

        for (Long studentId : studentsToRemove) {
            TempHousingAssignment tempHousingAssignmentToRemove = currentAssignedStudentMap.get(studentId);
            // Remove the assignment from both the student and the volunteer to make sure the relationship is orphaned
            // and thus removed
            tempHousingAssignmentToRemove.getStudent().setTempHousingAssignment(null);
            currentTempHousingAssignments.remove(tempHousingAssignmentToRemove);

            usersToNotify.add(tempHousingAssignmentToRemove.getStudent().getUser());
        }

        HashSet<Long> studentsToAdd = new HashSet<Long>(newAssignedStudentIds);
        studentsToAdd.removeAll(currentAssignedStudentIds);

        for (Long studentId : studentsToAdd) {
            Student student = newAssignedStudentMap.get(studentId);
            TempHousingAssignment tempHousingAssignmentToAdd = new TempHousingAssignment();
            tempHousingAssignmentToAdd.setStudent(student);
            tempHousingAssignmentToAdd.setVolunteer(volunteer);
            currentTempHousingAssignments.add(tempHousingAssignmentToAdd);

            usersToNotify.add(student.getUser());
        }

        if(studentsToAdd.isEmpty() && studentsToRemove.isEmpty())
        {
            return;
        }

        usersToNotify.add(volunteer.getUser());

        volunteerRepository.save(volunteer);

        for (User user : usersToNotify) {
            emailService.sendTempHousingAssignmentUpdateMessage(user);
        }
    }
}
