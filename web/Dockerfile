FROM openjdk:19-alpine
EXPOSE 8080
VOLUME /tmp
COPY build/libs/*.jar   adventuretube.jar
CMD ["java","-jar","/adventuretube.jar"]