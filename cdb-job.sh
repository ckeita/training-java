# src directory
SRC_DIR="/media/Data/My_Projects/training-java/computer-database"

# Build cdb package
docker run -i --rm --name cdb-project -v "$SRC_DIR":/home/cdb -w /home/cdb maven:3.5.0-jdk-8 mvn clean package

# Build tomcat image
docker built -t tomcat-cdb .

# run tomcat cdb
docker run -i --rm --name tomcat-cdb -p 8081:8080 tomcat:8.0.43-jre8

