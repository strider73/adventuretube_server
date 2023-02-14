FROM openjdk:17-alpine
COPY src/main/java/ /tmp
WORKDIR /tmp
CMD java com.adventuretube.AdventuretubeMain