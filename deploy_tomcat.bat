set CATALINA_HOME=%1

rd webapp\WEB-INF\classes /s /q
xcopy %3 webapp\WEB-INF\classes /e /y /i
cd webapp
jar -cvf %2.war .
xcopy %2.war %CATALINA_HOME%\webapps /y /i
::rd WEB-INF\classes /s /q
del %2.war /q

%CATALINA_HOME%\bin\catalina.bat run