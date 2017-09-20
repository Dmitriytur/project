set CATALINA_HOME=%1

rd %CATALINA_HOME%\webapps\%2
rd webapp\WEB-INF\classes /s /q

xcopy %3 webapp\WEB-INF\classes /e /y /i
cd webapp
xcopy .  %CATALINA_HOME%\webapps\%2 /e /y /i

%CATALINA_HOME%\bin\catalina.bat run