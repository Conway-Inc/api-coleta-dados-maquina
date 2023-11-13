import csv
import mysql.connector

# CONEXAO LOCAL 
conexao = mysql.connector.connect(user='user_conway', password='urubu100', host='localhost', database='ConWay', auth_plugin = 'mysql_native_password')

# CONEXAO AWS
# conexao = mysql.connector.connect(user='root', password='urubu100', host='44.212.3.214', database='ConWay', auth_plugin = 'mysql_native_password')

cursor = conexao.cursor()

bruno = "C:/Users/bruno/Documents/ConWay/api-coleta-dados-maquina/dados/alertas.csv"
kauan = "D:/Faculdade/ConWay/api-coleta-dados-maquina/dados/alertas.csv"

if (conexao.is_connected()):
    print("A Conexão ao MySql foi iniciada ")
    with open(("C:/Users/bruno/Documents/ConWay/api-coleta-dados-maquina/dados/alertas.csv")) as f:
        file_content=f.read()
        cr = csv.reader(file_content.splitlines(), delimiter=';')
        my_list = list(cr)
        json = my_list

        for row in json:
            print(row)
            # Obter os dados do arquivo CSV
            idAlerta = row[0]
            tipo = row[1]
            descricao = row[2]
            fkRegistro = row[3]

            # Inserir o registro no banco de dados
            cursor.execute(
                "INSERT INTO Alerta (idAlerta, tipo, descricao, fkRegistro) VALUES (%s, %s, %s, %s)",
                (idAlerta, tipo, descricao, fkRegistro)
            )
            conexao.commit()
            
    conexao.close()
else:
    print("Houve erro ao conectar")