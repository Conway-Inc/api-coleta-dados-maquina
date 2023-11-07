import csv
import mysql.connector


conexao = mysql.connector.connect(user='user_conway', password='urubu100', host='localhost', database='ConWay', auth_plugin = 'mysql_native_password')

cursor = conexao.cursor()

if (conexao.is_connected()):
    print("A Conexão ao MySql foi iniciada ")
    with open(("C:/Users/bruno/Documents/ConWay/api-coleta-dados-maquina/dados/registros.csv")) as f:
        file_content=f.read()
        cr = csv.reader(file_content.splitlines(), delimiter=';')
        my_list = list(cr)
        json = my_list

        for row in json:
            print(row)
            # Obter os dados do arquivo CSV
            idRegistro = row[0]
            valor = row[1]
            dataHora = row[2]
            fkComponente = row[3]
            fkTotem = row[4]

            # Inserir o registro no banco de dados
            cursor.execute(
                "INSERT INTO registro (idRegistro,valor, dataHora, fkComponente, fkTotem) VALUES (%s,%s, %s, %s, %s)",
                (idRegistro, valor, dataHora, fkComponente, fkTotem)
            )
            conexao.commit()
            
    conexao.close()
else:
    print("Houve erro ao conectar")