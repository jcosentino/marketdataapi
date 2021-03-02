# Market Data API

## Installation

Install [Oracle Java JDK](https://www.java.com/en/).
Verify installation:
```bash
java --version
```
You should see similar output:
```bash
$ java --version
java 11.0.8 2020-07-14 LTS
Java(TM) SE Runtime Environment 18.9 (build 11.0.8+10-LTS)
Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.8+10-LTS, mixed mode)
```

Install [Apache Maven](https://maven.apache.org/).
Verify installation:
```bash
mvn -v
```
You should see similar output:
```bash
$ mvn -v
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: C:\apache-maven-3.6.3
Java version: 11.0.8, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk-11.0.8
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```

Install [Docker](https://www.docker.com/).
Verify installation:
```bash
docker -v
```

You should see similar output:
```bash
$ docker -v
Docker version 20.10.2, build 2291f61
```

## Usage

Clone repository and run:
```bash
mvn install
```

Finally, run:
```bash
java -jar marketdataapi-0.0.1-SNAPSHOT.jar
```

You can also run with Docker:
```bash
docker build -t marketdataapi .
docker run --rm -p 5000:5000 --name marketdataapi:latest
