1. Some useful links for learning Cucumber and TestNg:

https://support.smartbear.com/testleft/docs/bdd/tutorial/cucumber/get-stubs.html

https://cucumber.io/docs/guides/parallel-execution/?lang=java#testng

https://testng.org/doc/documentation-main.html#introduction

https://www.toolsqa.com/selenium-cucumber-framework/cucumber-reports/

2. MasterThought plugin is used to generate beautiful and ready to publish html reports.

3. If we have multiple TestRunner files and we want to get the combined Masterthought report using the multiple json
files that cucumber creates after all the tests associated with a TestTunner is executed, then use the following 
plugin configuration:
<plugin>
	<groupId>net.masterthought</groupId>
	<artifactId>maven-cucumber-reporting</artifactId>
	<version>4.5.0</version>
	<executions>
		<execution>
			<id>execution</id>
			<phase>verify</phase>
			<goals>
				<goal>generate</goal>
			</goals>
			<configuration>
				<skip>false</skip>
				<inputDirectory>${project.build.directory}/json-report</inputDirectory>
				<outputDirectory>${project.build.directory}/masterthought-report</outputDirectory>
				<jsonFiles>
                <!-- supports wildcard or name pattern -->
                	<param>**/*.json</param>
            	</jsonFiles>
				<suiteXmlFiles>
					<suiteXmlFile>testng.xml</suiteXmlFile>
				</suiteXmlFiles>
			</configuration>
		</execution>
	</executions>
</plugin>

Note: "maven-cucumber-reporting" plugin mentioned above has the "cucumber-reporting" as its compile time 
dependency. So also include it.
<dependency>
	<groupId>net.masterthought</groupId>
	<artifactId>cucumber-reporting</artifactId>
	<version>4.5.1</version>
</dependency>

4. If we want to run a single TestRunner file(say TestRunner1.java) and not other TestRunner files if available then 
we use:
mvn test -Dtest="TestRunner1"

5. If we want to run a single TestRunner file(say TestRunner1.java) and not other TestRunner files if available and
we also want to generate the MasterThought report then we use:
clean install -Dtest="TestRunner1"

6. If there is a mismatch in the path of cucumber.json report mentioned in the TestRunner file and in the 
MasterThought plugin <inputDirectory> section in pom.xml then we will get:
net.masterthought.cucumber.ValidationException: None report file was added!

7. <jsonFiles> is used to define which json files present in the path specified at <inputDirectory> section
should be included in the MasterThought report.
<jsonFiles>
    <!-- supports wildcard or name pattern -->
    <param>json-report/*.json</param>
</jsonFiles>

8. For <cucumberOutput> and <inputDirectory> tags issue in MasterThought plugin see this:
https://github.com/damianszczepanik/maven-cucumber-reporting/issues/111

Note: When we say:
<cucumberOutput>${project.build.directory}/json-report/cucumber1.json</cucumberOutput>
All the .json files in the provided path will be considered for the MasterThought report. And the <jsonFiles>
section will be ignored.
When we say:
<inputDirectory>${project.build.directory}/json-report</inputDirectory>
The <jsonFiles> section will be considered.
