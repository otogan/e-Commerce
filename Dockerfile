FROM openjdk:8
COPY . /usr/src/myapp/
WORKDIR /usr/src/myapp
EXPOSE 8888
ENTRYPOINT ["./mvnw","spring-boot:run"]