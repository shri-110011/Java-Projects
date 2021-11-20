Java Annotations:
They are there since Java 5 and are used to define metadata about a program.

Annotations can be used for any class, interface, method, field, constructor, local variable etc.

Declaration of an annotation:

[access specifier] @interface <AnnotationName> {
	<DataType> <MemberName>() [default value]
}

Annotations can be classified into 2 broad categories:
1. Standard Annotations 2. Custom Annotations

1. Standard Annotations can be further divided as:
	1.1 General Purpose Annotations: 
		Like @override, @SuppreessWarnings("rawtypes") etc.
		They are declared in the java.lang package.
	1.2 Meta Annotations: 
		They are used to define metadata to annotations. 
		They are declared in the java.lang.annotation package.
		There are 4 meta annotations.
			@Documented
				If we generate documentation for any class or interface using the 'javadoc' command and that 
				class or interface contains an annotation in whose defining class there is the @Documented 
				annotation then the documentation will contain information about that annotation used in that 
				class.
			@Inherited
				If an annotation's defining class contains this @Inherited annotation and we use this 
				annotation over any class then that class's subclass will also inherit this @Inherited 
				annotation.
			@Target
				It is use to specify over which all elements this annotation can be used.
			@RetentionPolicy
				It is used to specify how long the annotation will be retained.
				There are 3 values for RetentionPolicy.
					Source: Means the annotation will be retained till the compilation time.
					Class:  Means the annotation will be retained till the .class file gets generated.
					Runtime: Means the annotation will be retained till the run time.