package mars.mips.instructions.customlangs;
import mars.simulator.*;
import mars.mips.hardware.*;
import mars.mips.instructions.syscalls.*;
import mars.*;
import mars.util.*;
import java.util.*;
import java.io.*;
import mars.mips.instructions.*;
import java.util.Random;


public class Car extends CustomAssembly {
    @Override
    public String getName() {
        return "Car";
    }

    @Override
    public String getDescription() {
        return "Turn your computer to a car simulator.";
    }

    @Override
    protected void populate() {


        instructionList.add(
                new BasicInstruction("ign $t0",
                        "Toggle the ignition of the car to a dedicated register ($t0)",
                        BasicInstructionFormat.R_FORMAT,
                        "000000 00000 00000 fffff 00000 000001",
                        new SimulationCode() {
                            public void simulate(ProgramStatement statement) throws ProcessingException {
                                int[] operands = statement.getOperands();
                                int currentState = RegisterFile.getValue(operands[0]);
                                int newState;
                                if (currentState == 0) {
                                    newState = 1;
                                } else {
                                    newState = 0;
                                }

                                if (newState == 0) {
                                    SystemIO.printString("Car is now off.\n");
                                } else {
                                    SystemIO.printString("Car is now on.\n");
                                }
                                RegisterFile.updateRegister(operands[0], newState);

                            }
                        }));


        // square
        instructionList.add(
                new BasicInstruction("gas $t1, 50",
                        "Add to the value of the speed of the car.",
                        BasicInstructionFormat.I_FORMAT,
                        "000010 sssss fffff tttttttttttttttt",
                        new SimulationCode() {
                            public void simulate(ProgramStatement statement) throws ProcessingException {
                                int[] operands = statement.getOperands();
                                int rs = RegisterFile.getValue(operands[0]);
                                int immediate = operands[1];

                                int result = rs + immediate;
                                SystemIO.printString("You are now going " + result + ".\n");
                                RegisterFile.updateRegister(operands[0], result);
                            }
                        }));

        instructionList.add(
                new BasicInstruction("brke $t1, 50",
                        "Hit the brakes! Subtract from the speed of the car.",
                        BasicInstructionFormat.I_FORMAT,
                        "000011 sssss fffff tttttttttttttttt",
                        new SimulationCode() {
                            public void simulate(ProgramStatement statement) throws ProcessingException {
                                int[] operands = statement.getOperands();
                                int rs = RegisterFile.getValue(operands[0]);
                                int immediate = operands[1];

                                int result = rs - immediate;
                                RegisterFile.updateRegister(operands[0], result);
                            }
                        }));

        instructionList.add(
                new BasicInstruction("tr $t2, 30",
                        "Add to the angle of the car (right is positive).",
                        BasicInstructionFormat.I_FORMAT,
                        "000100 sssss fffff tttttttttttttttt",
                        new SimulationCode() {
                            public void simulate(ProgramStatement statement) throws ProcessingException {
                                int[] operands = statement.getOperands();
                                int rs = RegisterFile.getValue(operands[0]);
                                int immediate = operands[1];

                                int result = rs + immediate;
                                SystemIO.printString("Turning right.");
                                RegisterFile.updateRegister(operands[0], result);
                            }
                        }));

        instructionList.add(
                new BasicInstruction("tl $t2, 30",
                        "Subtract from the angle of the car (left is negative).",
                        BasicInstructionFormat.I_FORMAT,
                        "000101 sssss fffff tttttttttttttttt",
                        new SimulationCode() {
                            public void simulate(ProgramStatement statement) throws ProcessingException {
                                int[] operands = statement.getOperands();
                                int rs = RegisterFile.getValue(operands[0]);
                                int immediate = operands[1];

                                int result = rs - immediate;
                                RegisterFile.updateRegister(operands[0], result);
                            }
                        }));

        instructionList.add(
                new BasicInstruction("cltch $t3",
                        "Toggle the clutch of the car.",
                        BasicInstructionFormat.R_FORMAT,
                        "000000 00000 00000 fffff 00000 000010",
                        new SimulationCode() {
                            public void simulate(ProgramStatement statement) throws ProcessingException {
                                int[] operands = statement.getOperands();
                                int currentState = RegisterFile.getValue(operands[0]);
                                int newState;
                                if (currentState == 0) {
                                    newState = 1;
                                } else {
                                    newState = 0;
                                }

                                if (newState == 0) {
                                    SystemIO.printString("Clutch is disengaged.\n");
                                } else {
                                    SystemIO.printString("Clutch is engaged.\n");
                                }
                                RegisterFile.updateRegister(operands[0], newState);

                            }
                        }));

        instructionList.add(
                new BasicInstruction("lock $t4",
                        "Toggle the lock on the car.",
                        BasicInstructionFormat.R_FORMAT,
                        "000000 00000 00000 fffff 00000 000011",
                        new SimulationCode() {
                            public void simulate(ProgramStatement statement) throws ProcessingException {
                                int[] operands = statement.getOperands();
                                int currentState = RegisterFile.getValue(operands[0]);
                                int newState;
                                if (currentState == 0) {
                                    newState = 1;
                                } else {
                                    newState = 0;
                                }

                                if (newState == 0) {
                                    SystemIO.printString("Car is unlocked.\n");
                                } else {
                                    SystemIO.printString("Car is locked.\n");
                                }
                                RegisterFile.updateRegister(operands[0], newState);

                            }
                        }));

        instructionList.add(
                new BasicInstruction("lght $t5",
                        "Switch through the light intensity settings.",
                        BasicInstructionFormat.R_FORMAT,
                        "000000 00000 00000 fffff 00000 000100",
                        new SimulationCode() {
                            public void simulate(ProgramStatement statement) throws ProcessingException {
                                int[] operands = statement.getOperands();
                                int currentState = RegisterFile.getValue(operands[0]);
                                int newState;
                                if (currentState == 0 || currentState == 1) {
                                    newState = currentState + 1;
                                } else {
                                    newState = 0;
                                }
                                if (newState == 0) {
                                    SystemIO.printString("Parking lights on.\n");
                                } else if (newState == 1) {
                                    SystemIO.printString("Lowbeams on.\n");
                                } else if (newState == 2) {
                                    SystemIO.printString("Highbeams on.\n");
                                }

                                RegisterFile.updateRegister(operands[0], newState);

                            }
                        }));

        instructionList.add(
                new BasicInstruction("ison $t0, label",
                        "Check if your car is on and jump to label to execute actions.",
                        BasicInstructionFormat.I_FORMAT,
                        "001010 sssss 00000 tttttttttttttttt",
                        new SimulationCode() {
                            public void simulate(ProgramStatement statement) throws ProcessingException {
                                int[] operands = statement.getOperands();
                                int rs = RegisterFile.getValue(operands[0]);

                                if (rs == 1) {
                                    int targetAddress = operands[1];
                                    RegisterFile.setProgramCounter(targetAddress);
                                }
                            }
                        }));

        instructionList.add(
                new BasicInstruction("isoff $t0, label",
                        "Check if your car is off and jump to label to execute actions.",
                        BasicInstructionFormat.I_FORMAT,
                        "001011 sssss 00000 tttttttttttttttt",
                        new SimulationCode() {
                            public void simulate(ProgramStatement statement) throws ProcessingException {
                                int[] operands = statement.getOperands();
                                int rs = RegisterFile.getValue(operands[0]);

                                if (rs == 0) {
                                    int targetAddress = operands[1];
                                    RegisterFile.setProgramCounter(targetAddress);
                                }
                            }
                        }));

        instructionList.add(
                new BasicInstruction("vrm",
                        "Print 'Vroom'.",
                        BasicInstructionFormat.R_FORMAT,
                        "000000 00000 00000 00000 000000 001100",
                        new SimulationCode() {
                            public void simulate(ProgramStatement statement) throws ProcessingException {


                                SystemIO.printString("Vroooom\n");
                            }
                        }));

        instructionList.add(
                new BasicInstruction("crsh $t1",
                        "Oh no! Speed set to 0",
                        BasicInstructionFormat.R_FORMAT,
                        "000000 00000 00000 fffff 00000 000101",
                        new SimulationCode() {
                            public void simulate(ProgramStatement statement) throws ProcessingException {
                                int[] operands = statement.getOperands();
                                int speed = RegisterFile.getValue(operands[0]);
                                speed = 0;

                                RegisterFile.updateRegister(operands[0], speed);
                            }
                        }));

        instructionList.add(
                new BasicInstruction("stvl $t13, 4",
                        "Set a value necessary for any register.",
                        BasicInstructionFormat.I_FORMAT,
                        "001110 sssss fffff tttttttttttttttt",
                        new SimulationCode() {
                            public void simulate(ProgramStatement statement) throws ProcessingException {
                                int[] operands = statement.getOperands();
                                int rs = RegisterFile.getValue(operands[0]);
                                int immediate = operands[1];

                                int result = rs + immediate;
                                RegisterFile.updateRegister(operands[0], result);
                            }
                        }));

        instructionList.add(
                new BasicInstruction("haz $t8",
                        "Toggle the hazard lights on the car.",
                        BasicInstructionFormat.R_FORMAT,
                        "000000 00000 00000 fffff 00000 000110",
                        new SimulationCode() {
                            public void simulate(ProgramStatement statement) throws ProcessingException {
                                int[] operands = statement.getOperands();
                                int currentState = RegisterFile.getValue(operands[0]);
                                int newState;
                                if (currentState == 0) {
                                    newState = 1;
                                } else {
                                    newState = 0;
                                }

                                if (newState == 0) {
                                    SystemIO.printString("Hazards are off.\n");
                                } else {
                                    SystemIO.printString("Hazards are on.\n");
                                }
                                RegisterFile.updateRegister(operands[0], newState);

                            }
                        }));


        instructionList.add(
                new BasicInstruction("gtg $t10, 50",
                        "Add fuel to the car.",
                        BasicInstructionFormat.I_FORMAT,
                        "001111 sssss fffff tttttttttttttttt",
                        new SimulationCode() {
                            public void simulate(ProgramStatement statement) throws ProcessingException {
                                int[] operands = statement.getOperands();
                                int rs = RegisterFile.getValue(operands[0]);
                                int immediate = operands[1];

                                int result = rs + immediate;
                                SystemIO.printString("There is now " + result + " liters of fuel in the car.\n");
                                RegisterFile.updateRegister(operands[0], result);
                            }
                        }));

        instructionList.add(
                new BasicInstruction("pb $t9",
                        "Toggle the parking brake of the car.",
                        BasicInstructionFormat.R_FORMAT,
                        "000000 00000 00000 fffff 00000 000111",
                        new SimulationCode() {
                            public void simulate(ProgramStatement statement) throws ProcessingException {
                                int[] operands = statement.getOperands();
                                int currentState = RegisterFile.getValue(operands[0]);
                                int newState;
                                if (currentState == 0) {
                                    newState = 1;
                                } else {
                                    newState = 0;
                                }

                                if (newState == 0) {
                                    SystemIO.printString("Parking brake is off.\n");
                                } else {
                                    SystemIO.printString("Parking brake is on.\n");
                                }
                                RegisterFile.updateRegister(operands[0], newState);

                            }
                        }));

        instructionList.add(
                new BasicInstruction("uf $t10",
                        "Use the fuel in the car, subtracting a quarter of the added fuel.",
                        BasicInstructionFormat.R_FORMAT,
                        "000000 00000 00000 fffff 00000 001000",
                        new SimulationCode() {
                            public void simulate(ProgramStatement statement) throws ProcessingException {
                                int[] operands = statement.getOperands();
                                int rs = RegisterFile.getValue(operands[0]);
                                int quarter = rs / 4;

                                int result = rs - quarter;
                                if (result < 0) {
                                    result = 0;
                                }

                                SystemIO.printString("There is now " + result + " liters of fuel in the car.\n");
                                RegisterFile.updateRegister(operands[0], result);
                            }
                        }));

        instructionList.add(
                new BasicInstruction("nos $t1",
                        "Double the speed of the car.",
                        BasicInstructionFormat.R_FORMAT,
                        "000000 00000 00000 fffff 00000 001001",
                        new SimulationCode() {
                            public void simulate(ProgramStatement statement) throws ProcessingException {
                                int[] operands = statement.getOperands();
                                int rs = RegisterFile.getValue(operands[0]);

                                int result = rs * 2;

                                SystemIO.printString("Your speed is now " + result + ".\n");
                                RegisterFile.updateRegister(operands[0], result);
                            }
                        }));

        instructionList.add(
                new BasicInstruction("skid $t2, $t12, label ",
                        "If the angle ($t2) is larger than a certain angle value ($t12), car will skid. After skid, jump to label.",
                        BasicInstructionFormat.I_FORMAT,
                        "010000 sssss fffff tttttttttttttttt",
                        new SimulationCode() {
                            public void simulate(ProgramStatement statement) throws ProcessingException {
                                int[] operands = statement.getOperands();
                                int rs = RegisterFile.getValue(operands[0]);
                                int rt = RegisterFile.getValue(operands[1]);


                                if (rs > rt) {
                                    int targetAddress = operands[2];
                                    RegisterFile.setProgramCounter(targetAddress);
                                }

                            }
                        }));
        instructionList.add(
                new BasicInstruction("rndc",
                        "Recieve a random car.",
                        BasicInstructionFormat.R_FORMAT,
                        "000000 00000 00000 00000 00000 100000",
                        new SimulationCode()
                        {

                            public void simulate(ProgramStatement statement) throws ProcessingException
                            {


                                Random random = new Random();
                                int roll = random.nextInt(7);


                                RegisterFile.updateRegister(2, roll);


                                switch (RegisterFile.getValue(2)){
                                    case 0:
                                        SystemIO.printString("You got a Porsche 911 GT3 RS!\n");
                                        break;
                                    case 1:
                                        SystemIO.printString("You got a Toyota Supra MKIV!\n");
                                        break;
                                    case 2:
                                        SystemIO.printString("You got a Mclaren 720s!\n");
                                        break;
                                    case 3:
                                        SystemIO.printString("You got a Mazda Rx7!\n");
                                        break;
                                    case 4:
                                        SystemIO.printString("You got a Ferrari 499P!\n");
                                        break;
                                    case 5:
                                        SystemIO.printString("You got a Toyota Prius...\n");
                                        break;
                                    case 6:
                                        SystemIO.printString("You got a Nissan GTR!\n");
                                        break;
                                    default:
                                        SystemIO.printString("Uh oh. No car!\n");

                                }
                            }
                        }));
    }
}