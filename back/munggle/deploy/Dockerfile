FROM openjdk:17-jdk As builder

WORKDIR /work
COPY . /work

RUN chmod + x gradlew
RUN ./gradlew clean build

FROM openjdk:17-jdk
ENV TZ="Asia/Seoul"

WORKDIR /work
COPY --from=builder build/libs/*.jar /work/app.jar
COPY ./deploy/start-app.sh /work/start-app.sh

RUN chmod +x start-app.sh
ENTRYPOINT ["./start-app.sh"]
