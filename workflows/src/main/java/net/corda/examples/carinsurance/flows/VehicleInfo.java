package net.corda.examples.carinsurance.flows;

import net.corda.core.serialization.CordaSerializable;

@CordaSerializable
public class VehicleInfo {

    private final String chasisNumber;
    private final String licensePlateNumber;
    private final String make;
    private final String model;
    private final String variant;
    private final String color;
    private final String fuelType;

    public VehicleInfo(String chasisNumber, String licensePlateNumber, String make, String model, String variant,
                       String color, String fuelType) {
        this.chasisNumber = chasisNumber;
        this.licensePlateNumber = licensePlateNumber;
        this.make = make;
        this.model = model;
        this.variant = variant;
        this.color = color;
        this.fuelType = fuelType;
    }

    public String getChasisNumber() {
        return chasisNumber;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getVariant() {
        return variant;
    }

    public String getColor() {
        return color;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }
}
