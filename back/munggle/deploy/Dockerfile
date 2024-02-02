FROM openjdk:17-jdk-alpine As builder

WORKDIR /work
COPY . /work

RUN chmod +x ./gradlew
RUN ./gradlew clean bootJar

FROM openjdk:17-jdk
ENV TZ="Asia/Seoul"


FROM openjdk:17-jdk-slim

WORKDIR /work
COPY --from=builder /work/build/libs/*.jar /work/app.jar
COPY ./deploy/start-app.sh /work/start-app.sh

RUN chmod +x start-app.sh
ENTRYPOINT ["./start-app.sh"]
