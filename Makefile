# Silence all command echoes to the terminal
.SILENT: 

# Define source and binary directories
SRC = src
BIN = bin

# Create the bin directory if it doesn't exist
${BIN}:
	mkdir -p ${BIN}

# Make the class and move it to the bin directory
agent.class: ${BIN} ${SRC}/Agent.java decision.class
	javac -d ${BIN} ${SRC}/Agent.java

box.class: ${BIN} ${SRC}/Box.java
	javac -d ${BIN} ${SRC}/Box.java

decision.class: ${BIN} ${SRC}/Decision.java
	javac -d ${BIN} ${SRC}/Decision.java

environment.class: ${BIN} ${SRC}/Environment.java agent.class box.class decision.class
	javac -d ${BIN} ${SRC}/Environment.java

simulation.class: ${BIN} ${SRC}/Simulation.java agent.class box.class decision.class environment.class
	javac -d ${BIN} ${SRC}/Simulation.java

# Run the class with its dependencies
simulation: ${BIN} simulation.class
	java -cp ${BIN} ${SRC}.Simulation

sim: simulation