# Etapa 1: Imagem base do Maven para compilar e testar a aplicação
FROM FROM openjdk:17-alpin as build

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo pom.xml e baixar as dependências Maven sem o código-fonte
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
COPY target/AtividadeLaboratorioEng.jar /app/AtividadeLaboratorioEng.jar

# Instalar as dependências do Maven para cache
RUN mvn dependency:go-offline -B

# Copiar o código-fonte da aplicação para dentro do container
COPY src ./src

# Compilar o código e rodar os testes
RUN mvn clean install

# Etapa 2: Imagem final para rodar a aplicação
FROM openjdk:17-jdk-alpine

# Definir o diretório de trabalho para a aplicação empacotada
WORKDIR /app

# Copiar o JAR gerado na etapa de build para a imagem final
COPY --from=build /app/target/AtividadeLaboratorioEng.jar /app/AtividadeLaboratorioEng.jar

# Expor a porta que a aplicação utilizará
EXPOSE 8083

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "AtividadeLaboratorioEng.jar"]
