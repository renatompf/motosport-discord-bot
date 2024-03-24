## ====== Build Image ====== ##
FROM maven:3.9.5-amazoncorretto-21 AS build_image

WORKDIR /app

COPY pom.xml .
RUN mvn -e -B dependency:resolve

COPY src ./src
RUN mvn package -DskipTests

## ====== Unit Tests ====== ##
FROM maven:3.9.5-amazoncorretto-21 as unit_tests

# set the working directory to /app
ENV APP_HOME /app
WORKDIR $APP_HOME

COPY --from=build_image $APP_HOME $APP_HOME

CMD [ "mvn", "test"]

## ====== Deploy Image ====== ##
FROM amazoncorretto:21 as release_image

# Workdir creation
WORKDIR /app

COPY --from=build_image /app/target/motosport_discord_bot.jar .

# Container entrypoint
CMD [ "java", "-jar", "./motosport_discord_bot.jar" ]