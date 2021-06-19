# UNIX-like Bash Shell codebase

This project implements and tests a shell and a set of applications which are implemented in JAVA programming language.
The specification was designed in such a way that it maximally resembles the behaviour of Bash shell in GNU/Linux and Mac OS.

JVM is used instead of OS Kernel/drivers to provide required services. 

Please use JDK 11 when running this shell. 
Developing and testing dependencies have been specified in pom.xml file. Please fetch them before running the tests.

## Shell features & Application functionalities

• Shell: calling applications, quoting, IO-redirection, globbing, pipe operator, command substitution, semicolon operator
• Applications: echo, ls, wc, cat, grep, exit, tee, cd, split, mv, uniq, rm, paste, cp