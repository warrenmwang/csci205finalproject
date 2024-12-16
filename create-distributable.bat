@echo off

set APP_NAME=csci205finalproject
set JAR_FILE_PATH=build\libs\csci205finalproject-1.0-0.1.jar
set MAIN_JAR=csci205finalproject-1.0-0.1.jar
set MAIN_CLASS=main.Main
set OUTPUT_DIR=dist
set MODULES=javafx.graphics,javafx.controls,javafx.fxml

REM use "exe" if you want an installer that installs the program instead of creating a portable exe
set OUTPUT_TYPE=app-image

REM use gradle to get the module for javafx dependency
for /f "delims=" %%i in ('gradle -q printModulePath') do set MODULE_PATH=%%i

REM assuming JAVA_HOME var is set in your PATH
if not exist "%JAVA_HOME%\bin\jpackage.exe" (
    echo jpackage not found in %JAVA_HOME%\bin. Ensure JAVA_HOME is correctly set.
    exit /b 1
)

if not exist "%JAR_FILE_PATH%" (
    echo JAR file not found: %JAR_FILE_PATH%
    exit /b 1
)

if not exist "%OUTPUT_DIR%" (
    mkdir "%OUTPUT_DIR%"
)

if "%OUTPUT_TYPE%"=="exe" (
    "%JAVA_HOME%\bin\jpackage.exe" ^
        --type exe ^
        --input build\libs ^
        --dest "%OUTPUT_DIR%" ^
        --name "%APP_NAME%" ^
        --main-jar "%MAIN_JAR%" ^
        --main-class "%MAIN_CLASS%" ^
        --module-path "%MODULE_PATH%" ^
        --add-modules "%MODULES%" ^
        --java-options "-Xmx512m" ^
        --win-dir-chooser
)

if "%OUTPUT_TYPE%"=="app-image" (
    "%JAVA_HOME%\bin\jpackage.exe" ^
        --type app-image ^
        --input build\libs ^
        --dest "%OUTPUT_DIR%" ^
        --name "%APP_NAME%" ^
        --main-jar "%MAIN_JAR%" ^
        --main-class "%MAIN_CLASS%" ^
        --module-path "%MODULE_PATH%" ^
        --add-modules "%MODULES%" ^
        --java-options "-Xmx512m"
) else (
    echo "output type requested invalid"
    exit /b 1
)

if errorlevel 1 (
    echo jpackage command failed!
    exit /b 1
)

echo fin.
