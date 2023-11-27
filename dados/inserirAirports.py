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

administrator = "C:/Users/Administrator/Desktop/Projeto/api-coleta-dados-maquina/dados/airports.csv"
bruno = "C:/Users/bruno/Documents/ConWay/api-coleta-dados-maquina/dados/airports.csv"
kauan = "D:/Faculdade/ConWay/api-coleta-dados-maquina/dados/airports.csv"
bia = "C:/Projeto/api-coleta-dados-maquina/dados/airports.csv"

if (pyodbc.connect(connectionString)):
    print("A Conexão ao MySql foi iniciada ")

    cursor.execute("SET IDENTITY_INSERT Aeroporto ON")

    with open((administrator)) as f:
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

                         
    cursor.close()
    conn.close()

else:
    print("Houve erro ao conectar")