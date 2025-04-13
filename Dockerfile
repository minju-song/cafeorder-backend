# OpenJDK 17 기반의 스프링 부트 애플리케이션 실행 환경
FROM openjdk:17-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# 로컬 Gradle 빌드 결과물을 컨테이너로 복사
COPY build/libs/*SNAPSHOT.jar app.jar

# 컨테이너 실행 시 자동으로 실행할 명령어
CMD ["java", "-jar", "app.jar"]
