FROM eclipse-temurin:21

WORKDIR /app

COPY . .

RUN chmod +x ./gradlew

RUN ./gradlew build -x test

CMD ["java", "-jar", "build/libs/selfPostProject-0.0.1-SNAPSHOT.jar"]
