# Use uma imagem base do OpenJDK
FROM openjdk:21-jdk


# Defina o diretório de trabalho dentro do container
WORKDIR /app

# Copie o JAR gerado para o diretório de trabalho dentro do container
COPY target/ORDEM_SERVICO-0.0.1-SNAPSHOT.jar app.jar

# Comando para executar o JAR
ENTRYPOINT ["java", "-jar", "app.jar","--spring.config.location=classpath:/application-dev.properties"]
