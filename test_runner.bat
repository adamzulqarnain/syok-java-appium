set projectLocation=C:\Users\senheng\Documents\repo\syok-java-appium
 
cd %projectLocation%
 
set classpath=%projectLocation%\bin;%projectLocation%\lib\*;%projectLocation%\target\test-classes\*
 
java org.testng.TestNG %projectLocation%\testng.xml
 
pause