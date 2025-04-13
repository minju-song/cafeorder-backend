# ✅ 1단계: Gradle로 빌드
FROM gradle:7.6.3-jdk17 AS builder
WORKDIR /app
COPY . .
RUN gradle build -x test

# ✅ 2단계: JAR 실행 환경만 추출
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/*SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
