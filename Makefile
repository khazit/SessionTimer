SRCFILES := $(shell find ./src -name *.java)

default:
	javac -sourcepath ./src -classpath ./classes -d ./classes $(SRCFILES)
