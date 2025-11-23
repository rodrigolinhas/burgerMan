@echo off
echo Creating directories...
if not exist "bin" mkdir bin
if not exist "lib" mkdir lib

if not exist "lib\annotations-24.0.0.jar" (
    echo Downloading dependencies...
    curl -L -o lib\annotations-24.0.0.jar https://repo1.maven.org/maven2/org/jetbrains/annotations/24.0.0/annotations-24.0.0.jar
)

echo Compiling...
javac -d bin -cp "lib/*" -sourcepath Game/src Game/src/gameEngine/Client.java
if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b %errorlevel%
)

echo Copying resources...
xcopy "Game\src\images" "bin\images" /E /I /Y /Q
xcopy "Game\src\sounds" "bin\sounds" /E /I /Y /Q
xcopy "Game\src\fonts" "bin\fonts" /E /I /Y /Q

echo Starting BurgerMan...
java -cp "bin;lib/*" gameEngine.Client
pause
