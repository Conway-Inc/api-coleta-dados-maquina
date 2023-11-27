import csv

import pyodbc


# CONEXAO LOCAL 
# conexao = mysql.connector.connect(user='user_conway', password='urubu100', host='localhost', database='ConWay', auth_plugin = 'mysql_native_password')

# CONEXAO AWS
server= 'localhost'
database='ConWay'
username='sa'
password='urubu100'

# connectionString = f'DRIVER={{ODBC Driver 18 for SQL Server}};SERVER={server};DATABASE={database};UID={username};PWD={password}'
connectionString = f'DRIVER={{SQL Server}};SERVER={server};DATABASE={database};UID={username};PWD={password}'
conn = pyodbc.connect(connectionString)

cursor = conn.cursor()

administratorAirports = "C:/Users/Administrator/Desktop/Projeto/api-coleta-dados-maquina/dados/airports.csv"
administratorRegistros = "C:/Users/Administrator/Desktop/Projeto/api-coleta-dados-maquina/dados/registros.csv"
administratorAlertas = "C:/Users/Administrator/Desktop/Projeto/api-coleta-dados-maquina/dados/alertas.csv"


brunoAirports = "C:/Users/bruno/Documents/ConWay/api-coleta-dados-maquina/dados/airports.csv"
kauanAirports = "D:/Faculdade/ConWay/api-coleta-dados-maquina/dados/airports.csv"

if (pyodbc.connect(connectionString)):
    print("A Conexão ao MySql foi iniciada ")

    cursor.execute("SET IDENTITY_INSERT Aeroporto ON")
    conn.commit()

    with open((administratorAirports)) as f:
        file_content=f.read()
        cr = csv.reader(file_content.splitlines(), delimiter=';')
        my_list = list(cr)

        for row in my_list:
            print(row)

            idAeroporto, nome, estado, municipio = row

            cursor.execute(
                "INSERT INTO Aeroporto (idAeroporto, nome, estado, municipio) VALUES (?, ?, ?, ?)",
                idAeroporto, nome, estado, municipio
            )
            conn.commit()
        
        cursor.execute("SET IDENTITY_INSERT Aeroporto OFF")
        conn.commit()

    cursor.execute("SET IDENTITY_INSERT Registro ON")
    conn.commit()

    with open((administratorRegistros)) as f:
        file_content=f.read()
        cr = csv.reader(file_content.splitlines(), delimiter=';')
        my_list = list(cr)

        for row in my_list:
            print(row)

            idRegistro, valor, dataHora, fkComponente,fkTotem = row

            cursor.execute(
                "INSERT INTO Registro (idRegistro,valor, dataHora, fkComponente, fkTotem) VALUES (?, ?, ?, ?, ?)",
                (idRegistro, valor, dataHora, fkComponente, fkTotem)
            )
            conn.commit()

        cursor.execute("SET IDENTITY_INSERT Registro OFF")
        conn.commit()


    cursor.execute("SET IDENTITY_INSERT Alerta ON")
    conn.commit()

    with open((administratorAlertas)) as f:
        file_content=f.read()
        cr = csv.reader(file_content.splitlines(), delimiter=';')
        my_list = list(cr)

        for row in my_list:
            print(row)

            idAlerta, tipo, descricao, fkRegistro = row

            cursor.execute(
                "INSERT INTO Alerta (idAlerta, tipo, descricao, fkRegistro) VALUES (?,?,?,?)",
                (idAlerta,tipo, descricao, fkRegistro)
            )
            conn.commit()

        cursor.execute("SET IDENTITY_INSERT Alerta OFF")
        conn.commit()

                         
    cursor.close()
    conn.close()

else:
    print("Houve erro ao conectar")