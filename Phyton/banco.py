import mysql.connector as ms

class conectar:
    def __init__(self):
        self.host = "localhost"
        self.bd = "ConWay"
        self.password = "Urubu@100"
        self.user = "urubu100"
        self.iniciar()
    
    def iniciar(self):
        conexao = ms.connect(
            host="localhost",
            user="urubu100",
            password="Urubu@100",
            database="ConWay",
            port=3306
        )
        return conexao