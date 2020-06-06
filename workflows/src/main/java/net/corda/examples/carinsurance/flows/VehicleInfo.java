package net.corda.examples.carinsurance.flows;

import net.corda.core.contracts.ContractState;
import net.corda.core.contracts.LinearState;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;
import net.corda.core.serialization.CordaSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@CordaSerializable
public class VehicleInfo implements ContractState, LinearState {

    private final String chasisNumber;
    private final String licensePlateNumber;
    private final String make;
    private final String model;
    private final String variant;
    private final String color;
    private final String fuelType;
    private final UniqueIdentifier linearId;

    public final Party Applicant;


    public VehicleInfo(String chasisNumber, String licensePlateNumber, String make, String model, String variant,
                       String color, String fuelType, UniqueIdentifier linearId, Party applicant) {
        this.chasisNumber = chasisNumber;
        this.licensePlateNumber = licensePlateNumber;
        this.make = make;
        this.model = model;
        this.variant = variant;
        this.color = color;
        this.fuelType = fuelType;
        this.linearId = linearId;

        Applicant = applicant;
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

    public Party getApplicant() { return Applicant; }

    @Override
    public UniqueIdentifier getLinearId() {
        return linearId;
    }

    @NotNull
    @Override
    public List<AbstractParty> getParticipants() {
        return null;
    }
}
