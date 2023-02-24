FROM openjdk:11
LABEL maintainer="Award Management"
ADD target/demo-0.0.1-SNAPSHOT.jar awardservice.jar
ENTRYPOINT ["java", "-jar", "awardservice.jar"]
