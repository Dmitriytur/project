set APP_FOLDER="C:\Files\tools\ApacheTomcat\apache-tomcat-8.5.20\webapps\ROOT"
rd %APP_FOLDER%\css /s /q
rd %APP_FOLDER%\js /s /q
rd %APP_FOLDER%\fonts /s /q
rd %APP_FOLDER%\img /s /q

xcopy css  %CATALINA_HOME%\webapps\ROOT\css /e /y /i
xcopy js  %CATALINA_HOME%\webapps\ROOT\js /e /y /i
xcopy fonts  %CATALINA_HOME%\webapps\ROOT\fonts /e /y /i
xcopy img  %CATALINA_HOME%\webapps\ROOT\img /e /y /i
