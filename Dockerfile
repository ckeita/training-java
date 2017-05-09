# Pull base  image.
FROM tomcat:8.0.43-jre8

# Maintainer 
MAINTAINER ckeita@e-biz.fr

# Configure tomcat management
ADD tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml

# Copy war file
COPY /home/cdb/computer-database-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/computer-database-0.0.1-SNAPSHOT.war

# Expose
EXPOSE 8080

# Restart service
CMD ["catalina.sh", "run"]
