# 1. Document
https://cucumber.io/docs/guides/10-minute-tutorial/
https://github.com/selenium-cucumber/selenium-cucumber-java-maven-example

# 2. Create new project by MVN
mvn archetype:generate "-DarchetypeGroupId=io.cucumber" "-DarchetypeArtifactId=cucumber-archetype" "-DarchetypeVersion=4.2.6.1" "-DgroupId=UATPAXPEGASUS" "-DartifactId=UATPAXPEGASUS" "-Dpackage=UATPAXPEGASUS" "-Dversion=1.0.0-SNAPSHOT" "-DinteractiveMode=false"

**Notes**: DgroupId, DartifactId, Dpackage: not supoprt ".-_,..."
# 3. Run
Open termial: mvn test

# 4. About Project

## src/java/UATPAXPEGASUS**
- RunCucumberTest: define folder start test
- Setpdefs: define code with GIVEN, WHEN, THEN

## src/resources/UATPAXPEGASUS**: define scenario with file .feature

https://github.com/selenium-cucumber/selenium-cucumber-java-maven-example

# 5. Module project
- commons: save is all login module
- interfaces: save is define key, value is UI APP
- pasgesObject: it is interface package, declare all class using in project
