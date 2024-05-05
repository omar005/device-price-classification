package com.example.Devices.Entity;
import com.example.Devices.Service.DeviceService;
import com.example.Devices.DTO.PredictedPriceResponse;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer batteryPower;
    private Boolean hasBluetooth;
    private Double clockSpeed;
    private Boolean dualSim;
    private Integer frontCameraMP;
    private Integer has2or3or4G;
    private Integer internalMemory;
    private Double mobileDepth;
    private Double mobileWeight;
    private Integer numberOfCores;
    private Integer primaryCameraMP;
    private Double pixelDensity;
    private Integer ram;
    private Double diagonalScreenSize;
    private Double talkTime;
    private Boolean hasTouchScreen;
    private Boolean hasWifi;
    private Double predictedPrice; 


    // Getters and Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBatteryPower() {
        return batteryPower;
    }

    public void setBatteryPower(Integer batteryPower) {
        this.batteryPower = batteryPower;
    }

    public Boolean getHasBluetooth() {
        return hasBluetooth;
    }

    public void setHasBluetooth(Boolean hasBluetooth) {
        this.hasBluetooth = hasBluetooth;
    }

    public Double getClockSpeed() {
        return clockSpeed;
    }

    public void setClockSpeed(Double clockSpeed) {
        this.clockSpeed = clockSpeed;
    }

    public Boolean getDualSim() {
        return dualSim;
    }

    public void setDualSim(Boolean dualSim) {
        this.dualSim = dualSim;
    }

    public Integer getFrontCameraMP() {
        return frontCameraMP;
    }

    public void setFrontCameraMP(Integer frontCameraMP) {
        this.frontCameraMP = frontCameraMP;
    }

    public Integer getHas2or3or4G() {
        return has2or3or4G;
    }

    public void setHas2or3or4G(Integer has2or3or4G) {
        this.has2or3or4G = has2or3or4G;
    }

    public Integer getInternalMemory() {
        return internalMemory;
    }

    public void setInternalMemory(Integer internalMemory) {
        this.internalMemory = internalMemory;
    }

    public Double getMobileDepth() {
        return mobileDepth;
    }

    public void setMobileDepth(Double mobileDepth) {
        this.mobileDepth = mobileDepth;
    }

    public Double getMobileWeight() {
        return mobileWeight;
    }

    public void setMobileWeight(Double mobileWeight) {
        this.mobileWeight = mobileWeight;
    }

    public Integer getNumberOfCores() {
        return numberOfCores;
    }

    public void setNumberOfCores(Integer numberOfCores) {
        this.numberOfCores = numberOfCores;
    }

    public Integer getPrimaryCameraMP() {
        return primaryCameraMP;
    }

    public void setPrimaryCameraMP(Integer primaryCameraMP) {
        this.primaryCameraMP = primaryCameraMP;
    }

    public Double getPixelDensity() {
        return pixelDensity;
    }

    public void setPixelDensity(Double pixelDensity) {
        this.pixelDensity = pixelDensity;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Double getDiagonalScreenSize() {
        return diagonalScreenSize;
    }

    public void setDiagonalScreenSize(Double diagonalScreenSize) {
        this.diagonalScreenSize = diagonalScreenSize;
    }

    public Double getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(Double talkTime) {
        this.talkTime = talkTime;
    }

    public Boolean getHasTouchScreen() {
        return hasTouchScreen;
    }

    public void setHasTouchScreen(Boolean hasTouchScreen) {
        this.hasTouchScreen = hasTouchScreen;
    }

    public Boolean getHasWifi() {
        return hasWifi;
    }

    public void setHasWifi(Boolean hasWifi) {
        this.hasWifi = hasWifi;
    }

    public void setPredictedPrice(Double predictedPrice) {
        this.predictedPrice = predictedPrice;
    }

    // Getter for predicted price
    public Double getPredictedPrice() {
        return predictedPrice;
    }
}

