# src directory
SRC_DIR="/home/ebiz/Projects/training-java/computer-database"

# Build package
docker run -it --rm --name cdb-project -v "$SRC_DIR":/home/cdb -w /home/cdb maven:3.5.0-jdk-8 mvn clean package
