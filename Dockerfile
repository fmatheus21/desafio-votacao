# Etapa de build usando Maven + JDK 17
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Diretório de trabalho
WORKDIR /app

# Copia o pom.xml e as dependências para que o Maven as baixe
COPY pom.xml .

# Baixa as dependências
RUN mvn dependency:go-offline

# Copia o restante do código-fonte
COPY src /app/src

# Executa o comando para gerar o JAR e rodar os testes
RUN mvn clean package

# Usa uma imagem base menor para a aplicação final
#FROM openjdk:17-jdk-slim
# Etapa final de runtime com JDK apenas
FROM eclipse-temurin:17-jdk-jammy

# Define o diretório de trabalho
WORKDIR /app

# Copia o JAR gerado para a nova imagem
COPY --from=build /app/target/desafio-votacao-0.0.1.jar /app/desafio-votacao.jar

# Comando para rodar o JAR
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/desafio-votacao.jar"]
