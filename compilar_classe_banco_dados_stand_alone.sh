#!/bin/bash

#teste unico da classe DatabaseManager para debug 

# Diretórios
SRC_DIR="$(dirname $(find -name 'DatabaseManager.java' ))"
LIB_DIR="$(dirname $(find -name 'mysql-connector-java-9.4.0.jar') |sed -r 's/ $//g')"

#Arquivo a ser compilado. Resolvi deixar assim e não passado por parametro $1 
JAVA_FILE="$(basename $(find -name 'DatabaseManager.java' ))"


# conector de mysql em java
MYSQL_JAR="mysql-connector-java-9.4.0.jar" 

# Verificar se o arquivo Java existe
if [ ! -f "$SRC_DIR/$JAVA_FILE" ]; then
    echo "Arquivo $SRC_DIR/$JAVA_FILE não encontrado"
    exit 1
fi

# verificar se o connector existe
# echo "$LIB_DIR/$MYSQL_JAR" 
if [ ! -f "$LIB_DIR/$MYSQL_JAR" ]; then
    echo "connector mysql não encontrado em $LIB_DIR/$MYSQL_JAR"
    echo "baixei esse connector em https://dev.mysql.com/downloads/connector/j/, mas nao sei como colocar ele no Idea, entao coloquei num diretorio do projeto, depois eu vejo isso"
    exit 1
fi

# compilar
echo "Compilando a classe..."
javac -Xdiags:verbose -cp ".:$LIB_DIR/$MYSQL_JAR" -d . "$SRC_DIR/$JAVA_FILE"

if [[ $? -eq 0 ]]; then
    echo "compilacao bem-sucedida"
    
# executar
    echo "executar o programa..."
    java -cp ".:$LIB_DIR/$MYSQL_JAR" DatabaseManager
    
else
    echo "algo deu errado na compilação"
    exit 1
fi
