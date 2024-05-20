package org.apathinternational.faithpathrestful.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "volunteer")
public class Volunteer extends AuditableEntity {
    @Id
    @Column(name = "volunteer_id", updatable = false, nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "affiliation", nullable = false)
    private String affiliation;

    @Column(name = "primary_phone_number", nullable = false)
    private String primaryPhoneNumber;

    @Column(name = "secondary_phone_number")
    private String secondaryPhoneNumber;

    @Column(name = "wechat_id")
    private String wechatId;

    @Column(name = "provides_airport_pickup", nullable = false)
    private Boolean providesAirportPickup;

    @Column(name = "car_manufacturer")
    private String carManufacturer;

    @Column(name = "car_model")
    private String carModel;

    @Column(name = "num_car_seats")
    private Integer numCarSeats;

    @Column(name = "num_max_lg_luggages")
    private Integer numMaxLgLuggages;

    @Column(name = "num_max_trips")
    private Integer numMaxTrips;

    @Column(name = "airport_pickup_comment")
    private String airportPickupComment;

    @Column(name = "provides_temp_housing", nullable = false)
    private Boolean providesTempHousing;

    @Column(name = "home_address")
    private String homeAddress;

    @Column(name = "num_max_students_hosted")
    private Integer numMaxStudentsHosted;

    @Column(name = "temp_housing_start_date")
    private LocalDate tempHousingStartDate;

    @Column(name = "temp_housing_end_date")
    private LocalDate tempHousingEndDate;

    @Column(name = "num_double_beds")
    private Integer numDoubleBeds;

    @Column(name = "num_single_beds")
    private Integer numSingleBeds;

    @Column(name = "gender_preference")
    private String genderPreference;

    @Column(name = "provides_ride")
    private Boolean providesRide;

    @Column(name = "has_pet")
    private Boolean hasPet;

    @Column(name = "pet_description")
    private String petDescription;

    @Column(name = "temp_housing_comment")
    private String tempHousingComment;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "volunteer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AirportPickupPreference> airportPickupPreferences;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "volunteer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AirportPickupAssignment> airportPickupAssignments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "volunteer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TempHousingAssignment> tempHousingAssignments;

    public Volunteer() {
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public String getPrimaryPhoneNumber() {
        return primaryPhoneNumber;
    }

    public String getSecondaryPhoneNumber() {
        return secondaryPhoneNumber;
    }

    public String getWechatId() {
        return wechatId;
    }

    public Boolean getProvidesAirportPickup() {
        return providesAirportPickup;
    }

    public String getCarManufacturer() {
        return carManufacturer;
    }

    public String getCarModel() {
        return carModel;
    }

    public Integer getNumCarSeats() {
        return numCarSeats;
    }

    public Integer getNumMaxLgLuggages() {
        return numMaxLgLuggages;
    }

    public Integer getNumMaxTrips() {
        return numMaxTrips;
    }

    public String getAirportPickupComment() {
        return airportPickupComment;
    }

    public Boolean getProvidesTempHousing() {
        return providesTempHousing;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public Integer getNumMaxStudentsHosted() {
        return numMaxStudentsHosted;
    }

    public LocalDate getTempHousingStartDate() {
        return tempHousingStartDate;
    }

    public LocalDate getTempHousingEndDate() {
        return tempHousingEndDate;
    }

    public Integer getNumDoubleBeds() {
        return numDoubleBeds;
    }

    public Integer getNumSingleBeds() {
        return numSingleBeds;
    }

    public String getGenderPreference() {
        return genderPreference;
    }

    public Boolean getProvidesRide() {
        return providesRide;
    }

    public Boolean getHasPet() {
        return hasPet;
    }

    public String getPetDescription() {
        return petDescription;
    }

    public String getTempHousingComment() {
        return tempHousingComment;
    }

    public List<AirportPickupPreference> getAirportPickupPreferences() {
        return airportPickupPreferences;
    }

    public List<AirportPickupAssignment> getAirportPickupAssignments() {
        return airportPickupAssignments;
    }

    public List<TempHousingAssignment> getTempHousingAssignments() {
        return tempHousingAssignments;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public void setPrimaryPhoneNumber(String primaryPhoneNumber) {
        this.primaryPhoneNumber = primaryPhoneNumber;
    }

    public void setSecondaryPhoneNumber(String secondaryPhoneNumber) {
        this.secondaryPhoneNumber = secondaryPhoneNumber;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public void setProvidesAirportPickup(Boolean providesAirportPickup) {
        this.providesAirportPickup = providesAirportPickup;
    }

    public void setCarManufacturer(String carManufacturer) {
        this.carManufacturer = carManufacturer;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public void setNumCarSeats(Integer numCarSeats) {
        this.numCarSeats = numCarSeats;
    }

    public void setNumMaxLgLuggages(Integer numMaxLgLuggages) {
        this.numMaxLgLuggages = numMaxLgLuggages;
    }

    public void setNumMaxTrips(Integer numMaxTrips) {
        this.numMaxTrips = numMaxTrips;
    }

    public void setAirportPickupComment(String airportPickupComment) {
        this.airportPickupComment = airportPickupComment;
    }

    public void setProvidesTempHousing(Boolean providesTempHousing) {
        this.providesTempHousing = providesTempHousing;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public void setNumMaxStudentsHosted(Integer numMaxStudentsHosted) {
        this.numMaxStudentsHosted = numMaxStudentsHosted;
    }

    public void setTempHousingStartDate(LocalDate tempHousingStartDate) {
        this.tempHousingStartDate = tempHousingStartDate;
    }

    public void setTempHousingEndDate(LocalDate tempHousingEndDate) {
        this.tempHousingEndDate = tempHousingEndDate;
    }

    public void setNumDoubleBeds(Integer numDoubleBeds) {
        this.numDoubleBeds = numDoubleBeds;
    }

    public void setNumSingleBeds(Integer numSingleBeds) {
        this.numSingleBeds = numSingleBeds;
    }

    public void setGenderPreference(String genderPreference) {
        this.genderPreference = genderPreference;
    }

    public void setProvidesRide(Boolean providesRides) {
        this.providesRide = providesRides;
    }

    public void setHasPet(Boolean hasPet) {
        this.hasPet = hasPet;
    }

    public void setPetDescription(String petDescription) {
        this.petDescription = petDescription;
    }

    public void setTempHousingComment(String tempHousingComment) {
        this.tempHousingComment = tempHousingComment;
    }

    public void setAirportPickupPreferences(List<AirportPickupPreference> airportPickupPreferences) {
        this.airportPickupPreferences = airportPickupPreferences;
    }

    public void setAirportPickupAssignments(List<AirportPickupAssignment> airportPickupAssignments) {
        this.airportPickupAssignments = airportPickupAssignments;
    }

    public void setTempHousingAssignments(List<TempHousingAssignment> tempHousingAssignments) {
        this.tempHousingAssignments = tempHousingAssignments;
    }

}
