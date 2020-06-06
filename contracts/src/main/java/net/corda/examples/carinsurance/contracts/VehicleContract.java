package net.corda.examples.carinsurance.contracts;

import net.corda.core.contracts.*;
import net.corda.core.identity.AbstractParty;
import net.corda.core.transactions.LedgerTransaction;
import net.corda.examples.carinsurance.states.VehicleState;

import java.security.PublicKey;
import java.util.HashSet;
import java.util.List;

import static net.corda.core.contracts.ContractsDSL.requireSingleCommand;
import static net.corda.core.contracts.ContractsDSL.requireThat;

// ************
// * Contract *
// ************
public class VehicleContract implements Contract {
    // This is used to identify our contract when building a transaction.
//    public static final String ID = "net.corda.examples.carinsurance.contracts.InsuranceContract";
    public static final String ID = VehicleContract.class.getCanonicalName();


    // Used to indicate the transaction's intent.
    public interface Commands extends CommandData {
        class Issue implements Commands {}
        class Move implements Commands {}
    }

    // A transaction is valid if the verify() function of the contract of all the transaction's input and output states
    // does not throw an exception.
    @Override
    public void verify(LedgerTransaction tx) {
      final CommandWithParties<Commands> command = requireSingleCommand(tx.getCommands(), VehicleContract.Commands.class);
      final Commands commandData = command.getValue();


      if(commandData.equals(new Commands.Issue())) {
          requireThat(require -> {
              require.using("There should be no input state", tx.getInputStates().isEmpty());
              require.using("Only one output state should be created when issuing", tx.getOutputStates().size() == 1);

              final VehicleState outputState = tx.outputsOfType(VehicleState.class).get(0);
              require.using("A license plate number should be not be more than 6 chars",
                      outputState.getLicensePlateNumber().length() == 6);
              require.using("VIN number must be 15 chars long",
                      outputState.getChasisNumber().length() == 15);

              List<PublicKey> signers = tx.getCommands().get(0).getSigners();
              HashSet<PublicKey> signersSet = new HashSet<>();
              for(PublicKey key: signers) {
                  signersSet.add(key);
              }

              List<AbstractParty> participants = tx.getOutputStates().get(0).getParticipants();
              HashSet<PublicKey> partcipantKeys = new HashSet<>();
              for (AbstractParty party: participants) {
                  partcipantKeys.add(party.getOwningKey());
              }

              require.using("Only owner/applicant may sign VehicleState issue transaction",
                      signersSet.containsAll(partcipantKeys) && signersSet.size() == 1);

              return null;
          });
      }

      else if (commandData.equals(new Commands.Move())) {
          requireThat(require -> {
              require.using("An VehicleState transfer/move transaction should only consume one input state", tx.getInputStates().size() == 1);
              require.using("An VehicleState transaction should only create one output state", tx.getOutputStates().size() == 1);

              //Copy of input with new Owner;
              VehicleState inputState = tx.inputsOfType(VehicleState.class).get(0);
              VehicleState outputState = tx.outputsOfType(VehicleState.class).get(0);
              //VehicleState checkOuputState

              return null;
          });
      }




    }
}