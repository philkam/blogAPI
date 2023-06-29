FROM openjdk:19
EXPOSE 8080
COPY target/blog-api-app.jar
ENTRYPOINT ["java","-jar","/blog-api-app.jar"]