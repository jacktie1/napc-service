package org.apathinternational.faithpathrestful.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "student")
public class Student extends AuditableEntity {
    @Id
    @Column(name = "student_id", updatable = false, nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "english_name", nullable = false)
    private String englishName;

    @Column(name = "is_new_student", nullable = false)
    private Boolean isNewStudent;

    @Column(name = "student_type", nullable = false)
    private String studentType;

    @Column(name = "graduates_from")
    private String graduatesFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_reference_id")
    private Reference majorReference;

    @Column(name = "custom_major")
    private String customMajor;

    @Column(name = "has_companion", nullable = false)
    private Boolean hasCompanion;

    @Column(name = "wechat_id", nullable = false)
    private String wechatId;

    @Column(name = "cn_phone_number")
    private String cnPhoneNumber;

    @Column(name = "us_phone_number")
    private String usPhoneNumber;

    @Column(name = "needs_airport_pickup", nullable = false)
    private Boolean needsAirportPickup;

    @Column(name = "has_flight_information")
    private Boolean hasFlightInfo;

    @Column(name = "arrival_flight_number")
    private String arrivalFlightNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "arrival_airline_reference_id")
    private Reference arrivalAirlineReference;

    @Column(name = "custom_arrival_airline")
    private String customArrivalAirline;

    @Column(name = "arrival_datetime")
    private LocalDateTime arrivalDatetime;

    @Column(name = "departure_flight_number")
    private String departureFlightNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departure_airline_reference_id")
    private Reference departureAirlineReference;

    @Column(name = "custom_departure_airline")
    private String customDepartureAirline;

    @Column(name = "departure_datetime")
    private LocalDateTime departureDatetime;

    @Column(name = "num_lg_luggages")
    private Integer numLgLuggages;

    @Column(name = "num_sm_luggages")
    private Integer numSmLuggages;

    @Column(name = "needs_temp_housing", nullable = false)
    private Boolean needsTempHousing;

    @Column(name = "num_nights")
    private Integer numNights;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_reference_id")
    private Reference apartmentReference;

    @Column(name = "custom_destination_address")
    private String customDestinationAddress;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_phone_number")
    private String contactPhoneNumber;

    @Column(name = "contact_email_address")
    private String contactEmailAddress;

    @Column(name = "student_comment")
    private String studentComment;

    @Column(name = "admin_comment")
    private String adminComment;

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getEnglishName() {
        return englishName;
    }

    public Boolean getIsNewStudent() {
        return isNewStudent;
    }

    public String getStudentType() {
        return studentType;
    }

    public String getGraduatesFrom() {
        return graduatesFrom;
    }

    public Reference getMajorReference() {
        return majorReference;
    }

    public String getCustomMajor() {
        return customMajor;
    }

    public Boolean getHasCompanion() {
        return hasCompanion;
    }

    public String getWechatId() {
        return wechatId;
    }

    public String getCnPhoneNumber() {
        return cnPhoneNumber;
    }

    public String getUsPhoneNumber() {
        return usPhoneNumber;
    }

    public Boolean getNeedsAirportPickup() {
        return needsAirportPickup;
    }

    public Boolean getHasFlightInfo() {
        return hasFlightInfo;
    }

    public String getArrivalFlightNumber() {
        return arrivalFlightNumber;
    }

    public Reference getArrivalAirlineReference() {
        return arrivalAirlineReference;
    }

    public String getCustomArrivalAirline() {
        return customArrivalAirline;
    }

    public LocalDateTime getArrivalDatetime() {
        return arrivalDatetime;
    }

    public String getDepartureFlightNumber() {
        return departureFlightNumber;
    }

    public Reference getDepartureAirlineReference() {
        return departureAirlineReference;
    }

    public String getCustomDepartureAirline() {
        return customDepartureAirline;
    }

    public LocalDateTime getDepartureDatetime() {
        return departureDatetime;
    }

    public Integer getNumLgLuggages() {
        return numLgLuggages;
    }

    public Integer getNumSmLuggages() {
        return numSmLuggages;
    }

    public Boolean getNeedsTempHousing() {
        return needsTempHousing;
    }

    public Integer getNumNights() {
        return numNights;
    }

    public Reference getApartmentReference() {
        return apartmentReference;
    }

    public String getCustomDestinationAddress() {
        return customDestinationAddress;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public String getContactEmailAddress() {
        return contactEmailAddress;
    }

    public String getStudentComment() {
        return studentComment;
    }

    public String getAdminComment() {
        return adminComment;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public void setIsNewStudent(Boolean isNewStudent) {
        this.isNewStudent = isNewStudent;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public void setGraduatesFrom(String graduatesFrom) {
        this.graduatesFrom = graduatesFrom;
    }

    public void setMajorReference(Reference majorReference) {
        this.majorReference = majorReference;
    }

    public void setCustomMajor(String customMajor) {
        this.customMajor = customMajor;
    }

    public void setHasCompanion(Boolean hasCompanion) {
        this.hasCompanion = hasCompanion;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public void setCnPhoneNumber(String cnPhoneNumber) {
        this.cnPhoneNumber = cnPhoneNumber;
    }

    public void setUsPhoneNumber(String usPhoneNumber) {
        this.usPhoneNumber = usPhoneNumber;
    }

    public void setNeedsAirportPickup(Boolean needsAirportPickup) {
        this.needsAirportPickup = needsAirportPickup;
    }

    public void setHasFlightInfo(Boolean hasFlightInfo) {
        this.hasFlightInfo = hasFlightInfo;
    }

    public void setArrivalFlightNumber(String arrivalFlightNumber) {
        this.arrivalFlightNumber = arrivalFlightNumber;
    }

    public void setArrivalAirlineReference(Reference arrivalAirlineReference) {
        this.arrivalAirlineReference = arrivalAirlineReference;
    }

    public void setCustomArrivalAirline(String customArrivalAirline) {
        this.customArrivalAirline = customArrivalAirline;
    }

    public void setArrivalDatetime(LocalDateTime arrivalDatetime) {
        this.arrivalDatetime = arrivalDatetime;
    }

    public void setDepartureFlightNumber(String departureFlightNumber) {
        this.departureFlightNumber = departureFlightNumber;
    }

    public void setDepartureAirlineReference(Reference departureAirlineReference) {
        this.departureAirlineReference = departureAirlineReference;
    }

    public void setCustomDepartureAirline(String customDepartureAirline) {
        this.customDepartureAirline = customDepartureAirline;
    }

    public void setDepartureDatetime(LocalDateTime departureDatetime) {
        this.departureDatetime = departureDatetime;
    }

    public void setNumLgLuggages(Integer numLgLuggages) {
        this.numLgLuggages = numLgLuggages;
    }

    public void setNumSmLuggages(Integer numSmLuggages) {
        this.numSmLuggages = numSmLuggages;
    }

    public void setNeedsTempHousing(Boolean needsTempHousing) {
        this.needsTempHousing = needsTempHousing;
    }

    public void setNumNights(Integer numNights) {
        this.numNights = numNights;
    }

    public void setApartmentReference(Reference apartmentReference) {
        this.apartmentReference = apartmentReference;
    }

    public void setCustomDestinationAddress(String customDestinationAddress) {
        this.customDestinationAddress = customDestinationAddress;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public void setContactEmailAddress(String contactEmailAddress) {
        this.contactEmailAddress = contactEmailAddress;
    }

    public void setStudentComment(String studentComment) {
        this.studentComment = studentComment;
    }

    public void setAdminComment(String adminComment) {
        this.adminComment = adminComment;
    }
    
}
