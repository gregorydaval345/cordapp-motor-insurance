package net.corda.examples.carinsurance.states;


import com.google.common.collect.ImmutableList;
import net.corda.core.contracts.*;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;
import net.corda.core.serialization.CordaSerializable;
import net.corda.examples.carinsurance.contracts.VehicleContract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Simple POJO class for the vehicle details.
 * Corda uses its own serialization framework hence the class needs to be annotated with @CordaSerializable, so that
 * the objects of the class can be serialized to be passed across different nodes.
 */
@CordaSerializable
@BelongsToContract(VehicleContract.class)
public class VehicleState implements ContractState, LinearState {

    private final String chasisNumber;
    private final String licensePlateNumber;
    private final String make;
    private final String model;
    private final String variant;
    private final String color;
    private final String fuelType;
    private final UniqueIdentifier linearId;
    //Owner same as Applicant
    public final Party Applicant;



    public VehicleState(String chasisNumber, String licensePlateNumber, String make, String model, String variant,
                        String color, String fuelType, UniqueIdentifier linearId, Party applicant) {
        this.chasisNumber = chasisNumber;
        this.licensePlateNumber = licensePlateNumber;
        this.make = make;
        this.model = model;
        this.variant = variant;
        this.color = color;
        this.fuelType = fuelType;
        this.linearId = linearId;
        this.Applicant = applicant;
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

    public Party getApplicant() { return Applicant; }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    @Override
    public UniqueIdentifier getLinearId() {
        return linearId;
    }

    @NotNull
    @Override
    public List<AbstractParty> getParticipants() {
        return ImmutableList.of(Applicant);
    }

//    public VehicleState withNewOwner(Party newOwner) {
//
//    }


}