echo "Atualizando pacotes apt"
sudo apt update && sudo apt upgrade -y


echo "Instalando jdk-java"
sudo apt install openjdk-19-jdk -y
java -version

echo "Instalando python"
sudo apt install python3
python3 --version
echo "Instalando pip"
sudo apt install python3-pip -y
python3 -m pip install psutil
echo "Instalando pip mysql"
python3 -m pip install mysql.connector
sudo apt install mysql-server


echo "Instalando docker"
sudo apt install docker.io
echo "Ativando docker para uso"
sudo systemctl start docker
echo "Ativando docker iniciando junto com o SO"
sudo systemctl enable docker

echo "Clonando API do grupo"
git clone https://github.com/Conway-Inc/api-coleta-dados-maquina.git
cd api-coleta-dados-maquina

echo "Criando container"
sudo docker pull mysql:5.7
sudo docker run -d -p 3306:3306 --name ContainerBD -e "MYSQL_DATABASE=ConWay" -e "MYSQL_ROOT_PASSWORD=urubu100" mysql:5.7
sudo docker exec -it ContainerBD mysql -u root -p -e "script-conway.sql"

cd java/

echo "Executando API"
api-bruno.jar
