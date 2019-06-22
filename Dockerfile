FROM openjdk:8
EXPOSE 8080
WORKDIR /app/tafel
COPY ./build/libs/*.jar tafel.jar
CMD ["java","-jar","tafel.jar"]