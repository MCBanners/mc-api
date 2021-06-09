# Prepare environment
FROM alpine:3.13
RUN apk add openjdk16

# Download source code
RUN git clone https://github.com/MCBanners/mc-api.git /app
WORKDIR /app

# Build the source into a binary
RUN ./gradlew clean build shadowJar

# Package the application
CMD /bin/sh -c "java -Xms128M -Xmx128M -jar build/libs/mcapi.jar"