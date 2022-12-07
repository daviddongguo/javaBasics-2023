# Java interview

## Q1 Explain Java platform independence
Platform independence is also called build once, run anywhere. Once we compile a java program and build a jar, we can run the jar(compiled java program) in any Operating System – where a JVM is installed.

Java achieves platform independence in a beautiful way. On compiling a java file the output is a class file – which contains an internal java representation called bytecode. JVM coverts bytecode to executable instructions.
The executable instructions are different in different operating systems. So,
there are different JVM’s for different operating system. A JVM for windows 
is different from a JVM for mac. However, both the JVM's understand the 
bytecode and covert it to executable code for the respective operating system.

java bytecode is the instruction set of the java virtual machine.

## q2 JVM vs JRE vs JDK
JVM is Java Virtual Machine that run the java bytecode. JVM makes java portable.
JRE equals JVM + Libraries + Other Components
JDK equals JRE + Compilers + Debuggers

## q3 ClassNotFoundException 
A java program is made up of a number of custom classes(written by 
programmers like us) and core classes(which come pre-packaged with java). 
When a program is executed, JVM needs to load the content of all the needed 
class. JVM uses a ClassLoader to find the classes.

There are three Class Loaders:
System Class Loader - Loads all classes from CLASSPATH
Extension Class Loader - Loads all classes from extension directory
Bootstrap Class Loader - Loads all the java Core files

When JVM needs to find a class, it starts with System Class Loader. If it is 
not found, it checks with Extension Class Loader. If it not found, it goes 
to the BootStrap Class Loader. If a class is still not found, a 
ClassNotFoundException is thrown.

## Implicit Casting vs Explicit Casting
Implicit casting is done by the compiler. Implicit casting are all the 
automatic widening conversions. store smaller values in a larger variable types.
for example cast int to long, long to float.

Explicit Casting is done through code. Good examples of explicit casting the 
narrowing conversions. Storing larger values into smaller variable types.
Explicit casting would cause truncation of value if the value stored is 
greater than the size of the variable. 
for example cast int to byte, only 8 bits remain.

## why String is immutable?
String is a wrapper class. All wrapper class instances are immutable.
Eight primitive wrapper classes provided in the java.lang package to provide 
object methods for the eight primitive types. 
boolean, byte, character, double, float, integer, long, short.

