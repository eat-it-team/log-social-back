FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dintegration_service.dadata.apiKey=72ccf7cb1333800d801baf408f80097a498f0dcd","-Dintegration_service.dadata.secretKey=938d053ec2cf462d4fd46398ef9149b05e64b42d","-Dpoor_registry.mongo.connection=mongodb://test:test@mongodb:27017/poor_registry","-Dpoor_registry.mongo.db_name=poor_registry","-jar","/app.jar"]

