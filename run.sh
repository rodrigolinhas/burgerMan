#!/bin/bash

# Create necessary directories
mkdir -p bin
mkdir -p lib

# Download dependencies if not present
if [ ! -f "lib/annotations-24.0.0.jar" ]; then
    echo "Downloading dependencies..."
    if command -v curl >/dev/null 2>&1; then
        curl -L -o lib/annotations-24.0.0.jar https://repo1.maven.org/maven2/org/jetbrains/annotations/24.0.0/annotations-24.0.0.jar
    elif command -v wget >/dev/null 2>&1; then
        wget -O lib/annotations-24.0.0.jar https://repo1.maven.org/maven2/org/jetbrains/annotations/24.0.0/annotations-24.0.0.jar
    else
        echo "Error: Neither curl nor wget found. Please download annotations-24.0.0.jar manually."
        exit 1
    fi
fi

# Compile the game
echo "Compiling..."
javac -d bin -cp "lib/*" -sourcepath Game/src Game/src/gameEngine/Client.java

# Check if compilation succeeded
if [ $? -ne 0 ]; then
    echo "Compilation failed!"
    exit 1
fi

# Copy resources
echo "Copying resources..."
cp -r Game/src/images Game/src/sounds Game/src/fonts bin/

# Run the game
echo "Starting BurgerMan..."
java -cp "bin:lib/*" gameEngine.Client
