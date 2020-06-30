FROM java:8
EXPOSE 8912
ADD /target/marketdataapi-0.0.1-SNAPSHOT.jar marketdataapi-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","marketdataapi-0.0.1-SNAPSHOT.jar"]
