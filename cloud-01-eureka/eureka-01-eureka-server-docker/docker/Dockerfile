FROM openjdk:8
ENV workdir /home/eureka
WORKDIR ${workdir}
COPY . ${workdir}
EXPOSE 8761
CMD ["java", "-jar", "eureka-server-docker-0.0.1.jar"]