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

administrator = "C:/Users/Administrator/Desktop/Projeto/api-coleta-dados-maquina/dados/alertas.csv"
bruno = "C:/Users/bruno/Documents/ConWay/api-coleta-dados-maquina/dados/alertas.csv"

if (pyodbc.connect(connectionString)):
    print("A Conexão ao MySql foi iniciada ")

    cursor.execute("SET IDENTITY_INSERT Alerta ON")

    with open((administrator)) as f:
        file_content=f.read()
        cr = csv.reader(file_content.splitlines(), delimiter=';')
        my_list = list(cr)

        for row in my_list:
            print(row)

            idAlerta, tipo, descricao, fkRegistro = row

            cursor.execute(
                "INSERT INTO Alerta (idAlerta, tipo, descricao, fkRegistro) VALUES (?,?,?,?)",
                (idAlerta, tipo, descricao, fkRegistro)
            )

        cursor.execute("SET IDENTITY_INSERT Alerta OFF")

        conn.commit()
            
    cursor.close()
    conn.close()
else:
    print("Houve erro ao conectar")