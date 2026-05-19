@echo off
setlocal

set APP_JAR=IXCWatchTask-0.1.0.jar

if not exist "%APP_JAR%" (
    echo Arquivo %APP_JAR% nao encontrado.
    pause
    exit /b 1
)

java -jar "%APP_JAR%"

endlocal
