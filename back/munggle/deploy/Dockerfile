FROM openjdk:17-jdk As builder

WORKDIR /work
COPY . /work

#파이프라인 재생성이 안되서 넣는 주석
RUN chmod +x ./gradlew
RUN ./gradlew clean build

FROM openjdk:17-jdk
ENV TZ="Asia/Seoul"

WORKDIR /work
COPY --from=builder build/libs/*.jar /work/app.jar
COPY ./start-app.sh /work/start-app.sh

RUN chmod +x start-app.sh
ENTRYPOINT ["./start-app.sh"]
