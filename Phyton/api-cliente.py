import psutil
from datetime import datetime
import mysql.connector
import platform
import time
import requests
from requests.auth import HTTPBasicAuth
import json

url = "https://conway-airway.atlassian.net/rest/api/3/issue"

token = "ATATT3xFfGF0FtTpqZ1Brbu8CoU7j0GQBJY-PxNoAM73cA0KPrFNA0pbDdWCgIzJlqPd8g8DEx26PpcUERNOk_C_lUQnT2uQlohoNbKy6H8P260ZYxq4fmW5eKXADoXUqeMYawdZdvKsmYeiGFTpLGmE8zPUgm7XU4L8AvILBNP8YK_DeKHX9fg=6A11A56A"

auth = HTTPBasicAuth("conway.sptech@gmail.com", token)

headers = {
      "Accept": "application/json",
      "Content-Type": "application/json"
}
 
conexao = mysql.connector.connect(user='user_conway', password='urubu100', host='localhost', database='ConWay', auth_plugin = 'mysql_native_password')

cursor = conexao.cursor()

def main():
    cont = 0
    isExibiuAlertaRam = False
    isExibiuAlertaCpu = False
    isExibiuCriticoRam = False
    isExibiuCriticoCpu = False
    
    qtd_core = psutil.cpu_count(logical=False)
    cpu_um_speed_max = psutil.cpu_freq().max / pow(10,3)
    ram_um_total = (psutil.virtual_memory().total) / pow(10,9)
    so = platform.system()
    if (so == 'Windows'):
        disco_um_total = psutil.disk_usage('C:\\').total / pow(10,9)
    elif (so == 'Linux'):
        disco_um_total = psutil.disk_usage('/bin').total / pow(10,9)
    
    while True:
        data = datetime.now()
        data = data.strftime('%Y/%m/%d %H:%M:%S')
        cpu_m1 = psutil.cpu_percent(interval=1)
        cpu_m1_speed = psutil.cpu_freq().current / pow(10,3)
        ram_m1_used = (psutil.virtual_memory().used) / pow(10,9)
        ram_m1 = psutil.virtual_memory().percent
        if (so == 'Windows'):
            disco_m1 = psutil.disk_usage('C:\\').percent
        elif (so == 'Linux'):
            disco_m1 = psutil.disk_usage('/bin').percent
        
            

        cursor.execute(f"INSERT INTO Registro (valor, dataHora, fkComponente, fkTotem) VALUES ({cpu_m1}, NOW(), 1, 1), ({ram_m1}, NOW(), 2, 1),({disco_m1}, NOW(), 3, 1);")
            
        conexao.commit()

        if (ram_m1 >= 85) and (ram_m1 < 95):
            cursor.execute(f"INSERT INTO Alerta (tipo, descricao, fkRegistro) VALUES (2,{ram_m1},(SELECT idRegistro FROM Registro WHERE fkTotem = 1 AND fkComponente = 2 ORDER BY dataHora DESC LIMIT 1));")
            conexao.commit()
            print("Alerta Atenção: Memória")
            if (isExibiuAlertaRam == False):
                abrirChamadoRAM(2)    
                isExibiuAlertaRam = True
        elif (ram_m1 >= 95):
            cursor.execute(f"INSERT INTO Alerta (tipo, descricao, fkRegistro) VALUES (1,{ram_m1},(SELECT idRegistro FROM Registro WHERE fkTotem = 1 AND fkComponente = 2 ORDER BY dataHora DESC LIMIT 1));")
            conexao.commit()
            print("Alerta Crítico: Memória")
            if (isExibiuCriticoRam == False):
                abrirChamadoRAM(1)
                isExibiuCriticoRam = True 
        elif (ram_m1 < 75): 
            isExibiuAlertaRam = False 
            isExibiuCriticoRam = False

        if (cpu_m1 >= 85) and (cpu_m1 < 95):
            cursor.execute(f"INSERT INTO Alerta (tipo, descricao, fkRegistro) VALUES (2,{cpu_m1},(SELECT idRegistro FROM Registro WHERE fkTotem = 1 AND fkComponente = 1 ORDER BY dataHora DESC LIMIT 1));")
            conexao.commit()
            print("Alerta Atenção: CPU")

            if (isExibiuAlertaCpu == False):

                abrirChamadoCPU(2)
                isExibiuAlertaCpu = True

        elif (cpu_m1 >= 95):
            cursor.execute(f"INSERT INTO Alerta (tipo, descricao, fkRegistro) VALUES (1,{cpu_m1},(SELECT idRegistro FROM Registro WHERE fkTotem = 1 AND fkComponente = 1 ORDER BY dataHora DESC LIMIT 1));")
            conexao.commit()
            print("Alerta Crítico: CPU")

            if (isExibiuCriticoCpu == False):
                abrirChamadoCPU(1)
                isExibiuCriticoCpu = True 
        elif (cpu_m1 < 75): 
            isExibiuAlertaCpu = False 
            isExibiuCriticoCpu = False
        cont+=1
        print(cont)
        time.sleep(1)


def abrirChamadoRAM(tipoAlerta):

    if (tipoAlerta == 1):
        payload = json.dumps({
            "fields":{
                "summary": "TLT-1 - RAM EM ESTADO CRÍTICO",
                "project":{"key":"AWLATAM"},
                'issuetype': {'name': 'General request'},
                "description": {"content": [{"content": [
                                            {
                                                "text": "A memória RAM do totem TLT-1 está em estado crítico!",
                                                "type": "text"
                                            }],
                                            "type": "paragraph"}],
                                            "type": "doc",
                                            "version": 1}
            }
        })

    else:
        payload = json.dumps({
            "fields":{
                "summary": "TLT-1 - RAM EM ESTADO DE ATENÇÃO",
                "project":{"key":"AWLATAM"},
                'issuetype': {'name': 'General request'},
                "description": {"content": [{"content": [
                                            {
                                                "text": "A memória RAM do totem TLT-1 está acima de 75%!",
                                                "type": "text"
                                            }],
                                            "type": "paragraph"}],
                                            "type": "doc",
                                            "version": 1}
            }
        })
    

    response = requests.request(
        "POST",
        url,
        data=payload,
        headers=headers,
        auth=auth
        )

    print(json.dumps(json.loads(response.text), sort_keys=True, indent=4, separators=(",", ": ")))

def abrirChamadoCPU(tipoAlerta):
    if (tipoAlerta == 1):
        payload = json.dumps({
            "fields":{
                "summary": "TLT-1 - CPU EM ESTADO CRÍTICO",
                "project":{"key":"AWLATAM"},
                'issuetype': {'name': 'General request'},
                "description": {"content": [{"content": [
                                            {
                                                "text": "A CPU do totem TLT-1 está em estado crítico!",
                                                "type": "text"
                                            }],
                                            "type": "paragraph"}],
                                            "type": "doc",
                                            "version": 1}
            }
        })

    else:
        payload = json.dumps({
            "fields":{
                "summary": "TLT-1 - CPU EM ESTADO DE ATENÇÃO",
                "project":{"key":"AWLATAM"},
                'issuetype': {'name': 'General request'},
                "description": {"content": [{"content": [
                                            {
                                                "text": "A CPU do totem TLT-1 está acima de 75%!",
                                                "type": "text"
                                            }],
                                            "type": "paragraph"}],
                                            "type": "doc",
                                            "version": 1}
            }
        })
    

    response = requests.request(
        "POST",
        url,
        data=payload,
        headers=headers,
        auth=auth
        )

    print(json.dumps(json.loads(response.text), sort_keys=True, indent=4, separators=(",", ": ")))


if (conexao.is_connected()):
    print("A Conexão ao MySql foi iniciada ")
    main()
else:
    print("Houve erro ao conectar")