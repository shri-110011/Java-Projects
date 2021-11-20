Java Reflection:
It is a process of analyzing and modifying all the capabilities of a class at runtime.

java.lang.Class: 
	It is the backbone of Reflection.
	Find and load any class.
	Instances of this class represent classes and interfaces in a running java application.

Whenever a class gets loaded by jvm, along with that class a Class object(Class<class>) for that class also gets 
generated.

There are 3 ways to manually instantiate the Class object.
1. Class.forName(String str)
Note: All Class objects for the same class share the same object.

2. obj.getClass()
Here obj is the class object whose class Class object we want.

3. ClassName.class

Some of the main classes inside java.reflect package are:
	Field: This class is used to get the meta data about the variables declared in a class.
	Method: This class is used to get the meta data about the methods declared in a class.
	Constructor: This class is used to get the meta data about the constructors declared in a class.
	Modifier: This class is used to get the meta data about the modifiers of a class.
	
	3aVd67dPl0