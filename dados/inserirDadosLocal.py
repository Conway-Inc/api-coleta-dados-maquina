import csv
import mysql.connector

conexao = mysql.connector.connect(user='user_conway', password='urubu100', host='localhost', database='ConWay', auth_plugin = 'mysql_native_password')

cursor = conexao.cursor()

brunoAirports = "C:/Users/bruno/Documents/ConWay/api-coleta-dados-maquina/dados/airports.csv"
brunoRegistros = "C:/Users/bruno/Documents/ConWay/api-coleta-dados-maquina/dados/registros.csv"
brunoAlertas = "C:/Users/bruno/Documents/ConWay/api-coleta-dados-maquina/dados/alertas.csv"
kauan = "D:/Faculdade/ConWay/api-coleta-dados-maquina/dados/airports.csv"

if (conexao.is_connected()):
    print("A Conexão ao MySql foi iniciada ")

    with open((brunoAirports)) as f:
        file_content=f.read()
        cr = csv.reader(file_content.splitlines(), delimiter=';')
        my_list = list(cr)

        for row in my_list:
            print(row)
            idAeroporto, nome, estado, municipio = row

            cursor.execute(
                "INSERT INTO Aeroporto (idAeroporto, nome, estado, municipio) VALUES (%s,%s,%s,%s)",
                (idAeroporto, nome, estado, municipio)
            )
            conexao.commit()
            
    with open((brunoRegistros)) as f:
        file_content=f.read()
        cr = csv.reader(file_content.splitlines(), delimiter=';')
        my_list = list(cr)

        for row in my_list:
            print(row)
            idRegistro, valor, dataHora, fkComponente, fkTotem = row

            cursor.execute(
                "INSERT INTO Registro (idRegistro,valor, dataHora, fkComponente, fkTotem) VALUES (%s,%s,%s,%s,%s)",
                (idRegistro, valor, dataHora, fkComponente, fkTotem)
            )
            conexao.commit()

    with open((brunoAlertas)) as f:
        file_content=f.read()
        cr = csv.reader(file_content.splitlines(), delimiter=';')
        my_list = list(cr)

        for row in my_list:
            print(row)
            idAlerta, tipo, descricao, fkRegistro = row

            cursor.execute(
                "INSERT INTO Alerta (idAlerta, tipo, descricao, fkRegistro) VALUES (%s,%s,%s,%s)",
                (idAlerta, tipo, descricao, fkRegistro)
            )
            conexao.commit()
    conexao.close()
else:
    print("Houve erro ao conectar")