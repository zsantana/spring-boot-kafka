#!/bin/bash

mvn clean package

# Script para iniciar múltiplas instâncias da aplicação Java

# Configurações comuns de JVM
JVM_OPTS="
-Xms512M 
-Xmx1024M 
-Xss256k
-XX:ActiveProcessorCount=4
-XX:+UseG1GC 
-XX:+UseContainerSupport 
-XX:+ExitOnOutOfMemoryError 
-XX:+UseStringDeduplication 
-XX:+UseCompressedOops 
-XX:+UseCompressedClassPointers 
-XX:MaxHeapFreeRatio=20 
-XX:MinHeapFreeRatio=10"

# Caminho para o JAR
JAR_PATH="target/demo-0.0.1-SNAPSHOT.jar"

# Iniciar instância na porta 8090
java -Dserver.port=8090 $JVM_OPTS -jar $JAR_PATH &
echo "Aplicação iniciada na porta 8090"

# Iniciar outra instância na porta 8091 (exemplo duplicado, ajuste se necessário)
java -Dserver.port=8091 $JVM_OPTS -jar $JAR_PATH &
echo "Aplicação duplicada iniciada na porta 8091"

# Iniciar instância na porta 8092
java -Dserver.port=8092 $JVM_OPTS -jar $JAR_PATH &
echo "Aplicação iniciada na porta 8092"

# Aguardar alguns segundos para garantir inicialização
sleep 2

# Verificar processos em execução
ps -ef | grep "$JAR_PATH"
