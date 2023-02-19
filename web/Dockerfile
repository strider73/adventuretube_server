FROM openjdk:17-alpine
VOLUME /tmp
COPY build/libs/*.jar web.jar
ENTRYPOINT ["java","-jar","/web.jar"]