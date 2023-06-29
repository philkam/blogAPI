FROM openjdk:19-jdk
VOLUME /tmp
EXPOSE 8080
COPY target/blog-api-app.jar blog-api-app.jar
ENTRYPOINT ["java","-jar","/blog-api-app.jar"]