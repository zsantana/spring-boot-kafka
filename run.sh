#!/bin/bash

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

# Caminho para o JAR do DashBoad

JAR_PATH="dashboard/target/demo-0.0.1-SNAPSHOT.jar"
java -Dserver.port=8080 $JVM_OPTS -jar $JAR_PATH &
echo "Aplicação iniciada na porta 8080"


# Caminho para o JAR do Produtor Kafka
JAR_PATH="produtor/target/demo-0.0.1-SNAPSHOT.jar"
java -Dserver.port=8010 $JVM_OPTS -jar $JAR_PATH &
echo "Aplicação iniciada na porta 8010"

# Caminho para o JAR do Consumidor Kafka
JAR_PATH="consumer/target/demo-0.0.1-SNAPSHOT.jar"
java -Dserver.port=8020 $JVM_OPTS -jar $JAR_PATH &
echo "Aplicação iniciada na porta 8020"

JAR_PATH="consumer/target/demo-0.0.1-SNAPSHOT.jar"
java -Dserver.port=8021 $JVM_OPTS -jar $JAR_PATH &
echo "Aplicação iniciada na porta 8021"

JAR_PATH="consumer/target/demo-0.0.1-SNAPSHOT.jar"
java -Dserver.port=8022 $JVM_OPTS -jar $JAR_PATH &
echo "Aplicação iniciada na porta 8022"

# Caminho para o JAR do Consumidor Kafka WEBHOOK
JAR_PATH="consumer-webhook/target/demo-0.0.1-SNAPSHOT.jar"
java -Dserver.port=8030 $JVM_OPTS -jar $JAR_PATH &
echo "Aplicação iniciada na porta 8030"

# Caminho para o JAR do Consumidor Kafka EMAIL
JAR_PATH="consumer-email/target/demo-0.0.1-SNAPSHOT.jar"
java -Dserver.port=8040 $JVM_OPTS -jar $JAR_PATH &
echo "Aplicação iniciada na porta 8040"

# Caminho para o JAR do Consumidor Kafka WHATSAPP
JAR_PATH="consumer-whatsapp/target/demo-0.0.1-SNAPSHOT.jar"
java -Dserver.port=8050 $JVM_OPTS -jar $JAR_PATH &
echo "Aplicação iniciada na porta 8050"

# Caminho para o JAR do Consumidor Kafka TELEGRAM
JAR_PATH="consumer-telegram/target/demo-0.0.1-SNAPSHOT.jar"
java -Dserver.port=8060 $JVM_OPTS -jar $JAR_PATH &
echo "Aplicação iniciada na porta 8060"


# Aguardar alguns segundos para garantir inicialização
sleep 2

# Verificar processos em execução
ps -ef | grep "$JAR_PATH"
