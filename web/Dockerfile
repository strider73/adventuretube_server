FROM openjdk:17
EXPOSE 8888
VOLUME /tmp
COPY build/libs/*.jar   adventuretube.jar
# Make port 5005 available to the world outside this container
EXPOSE 5005
CMD ["java","-jar","/adventuretube.jar"]