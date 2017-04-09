# Goal #
This program is designed to rename files massively. Very useful when you have to rename pictures taken by a digital camera for example.

It allows files to specific folder to be filtered by:

* Predefined Extensions (These are: jpg, gif, bmp and png).
* Regular Expressions.

These filters can work separately or combined.

Furthermore the tool allows the user to individually select which files wish to rename or select all.

The user also informs, which the prefix will be used in filenames and which will start the numeration sequence.

The program gets the list of files to rename, sort them by date of change. And rename concatenating the prefix, the numerical sequence and the file extension.

The program solves the problem of completing the left with zeros in the numeration, depending on the amount of files. Examples:


* **Example 1**: If 3 files must be renamed with the prefix arq, and jpg extension the names will be: arq1.jpg, arq2.jpg, arq3.jpg.

* **Example 2**: If 12 files must be renamed with the prefix and extension jpg arq the names will be: arq01.jpg, arq02.jpg, arq03.jpg, ..., arq09.jpg, arq10.jpg, arq11.jpg, arq12. jpg

The program also has options such as language Brazilian Portuguese, English and Spanish.

# Installation #

## Prerequisites ##

Java JRE 1.6
Unzip the file "adjust_file_name-1.0_bin.zip" in a subfolder of the system. To run the program double click the file "adjust_file_name-1.0.jar".