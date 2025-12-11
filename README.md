# MARS
MARS (official) MIPS Assembler and Runtime Simulator

 MARS is a lightweight interactive development environment (IDE) for programming in MIPS assembly language, intended for educational-level use with Patterson and Hennessy's Computer Organization and Design.
 MARS was developed by Pete Sanderson and Ken Vollmar and can be found at https://dpetersanderson.github.io/.

 This fork of MARS is a research project undertaken by John Edelman, Jun Law, and Dominic Dabish in summer 2025, aiming to modernize MARS and specifically to enable students to specify their own custom assembly languages for use with the MARS simulator.


This version of MARS uses an implementation made by the people above. This version specifically includes an implementation of a MIPS language that allows a user to simulate driving a car through simple instructions. Instructions include:
brke - Subtracting from the speed of the car.
cltch - Toggle the clutch of the car.
crsh - Sets the speed of the car to 0 directly.
gtg - Add fuel to the car.
haz - Toggle the hazard lights on the car.
ign - Toggle the ignition of the car.
isoff - Check if the car is off and jump to label to execute instructions.
ifon - check if the car is on and jump to label to execute instructions.
lght - Switch through the intensities of the lights.
lock - Toggle the lock on the car.
nos - Double the speed of the car.
pb - Toggle the parking brake.
rndc - Prints a random car to drive.
skid - If the value of the angle of the car is greater than a set value, then it will jump to a label.
stvl - Set the value of a register that may be necessary for any operation.
tl - Turn left, subtracting from the angle of the car (left is negative).
tr - Turn right, adding to the angle of the car (right is positive).
uf - Use fuel, subtracting a quarter of the added fuel.
vrm - Prints "vroom"

Copy the files from GitHub to your folder. If you already have the MARS files, copy the CustomAssembly.class and the customlangs folder to your MARS folder. Within the MARS application, in the toolbar, there is a tab that says Language Switcher. In the language switcher, you can select the Car language, and the Help page will give all the instructions and way that they can be implemented in your code. 
