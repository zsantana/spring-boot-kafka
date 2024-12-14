#!/bin/bash

# Script para iniciar múltiplas instâncias da aplicação Java

# Diretórios dos projetos
PROJECTS=("consumer" "consumer-email" "consumer-webhook" "consumer-email" "consumer-whatsapp" "consumer-telegram" "dashboard" "produtor")

# Itera sobre cada projeto e realiza o build
for PROJECT in "${PROJECTS[@]}"; do
    echo "Iniciando build do projeto: $PROJECT"
    cd "$PROJECT" || exit
    mvn clean package
    if [ $? -ne 0 ]; then
        echo "Build falhou no projeto $PROJECT. Abortando..."
        exit 1
    fi
    echo "Build concluído com sucesso para o projeto: $PROJECT"
    cd ..
done

echo "Build finalizado para todos os projetos."