FROM openjdk:17-alpine
EXPOSE 8888
VOLUME /tmp
COPY build/libs/*.jar   adventuretube.jar
CMD ["java","-jar","/adventuretube.jar"]