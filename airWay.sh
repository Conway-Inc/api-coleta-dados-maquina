#! /bin/bash

echo "Iniciando instalação do sistema de monitoramento de EC2 AirWay"

sudo apt update && sudo apt upgrade

sudo apt install openjdk-19-jdk -y
java -version

sudo apt install python3
python3 --version
sudo apt install python3-pip
python3 -m pip install psutil
python3 -m pip install mysql.connector
sudo apt install mysql-server

git clone https://github.com/Conway-Inc/api-coleta-dados-maquina.git
cd api-coleta-dados-maquina
service mysql start

sudo mysql -u root -purubu100 -e "source script-conway.sql"
cd Phyton/
python3 api-cliente.py
