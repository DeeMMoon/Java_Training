rm -rf target

mkdir target

javac src/java/edu/school21/printer/*/Program.java
src/java/edu/school21/printer/logic/Logic.java -d ./target

java -classpath target edu.school21.printer.app.Program . 0 /Users/gantedil/Downloads/it.bmp