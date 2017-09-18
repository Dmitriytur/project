set APP_FOLDER="C:\Files\tools\ApacheTomcat\apache-tomcat-8.5.20\webapps\ROOT"

rd %APP_FOLDER% /s /q
xcopy . %APP_FOLDER% /e /y /i
