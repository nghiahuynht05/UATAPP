# 1. Document
https://cucumber.io/docs/guides/10-minute-tutorial/

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
