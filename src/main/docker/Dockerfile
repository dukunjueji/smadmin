FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD smadmin.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Dspring.profiles.active=dev","/app.jar"]
