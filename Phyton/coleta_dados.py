import psutil
from datetime import datetime
import os
import mysql.connector
import requests 
import json
import platform
import time
 

#  CONEXAO LOCAL
conexao = mysql.connector.connect(
    user='user_conway', 
    password='urubu100', 
    host='localhost', 
    database='ConWay',
    auth_plugin="mysql_native_password")

# CONEXAO AWS
conexao = mysql.connector.connect(
    user='root', 
    password='urubu100', 
    host='44.212.3.214', 
    database='ConWay',
    auth_plugin="mysql_native_password")

cursor = conexao.cursor()

while True:
    if (conexao.is_connected()):
        print("A Conexão ao MySql foi iniciada ")
    else:
        print("Houve erro ao conectar")
    
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

        
        cpu_m2 = cpu_m1 * 1.05
        cpu_m2 = 100 if cpu_m2 >= 100 else cpu_m2
        ram_m2 = ram_m1 * 0.875
        ram_m2 = 0 if ram_m2 <= 0 else ram_m2
        disco_m2 = disco_m1 * 1.05
        disco_m2 = 100 if disco_m2 >= 100 else disco_m2

        cpu_m3 = cpu_m1 * 0.95
        cpu_m3 = 0 if cpu_m3 <= 0 else cpu_m3
        ram_m3 = ram_m1 * 0.85
        cpu_m3 = 0 if cpu_m3 <= 0 else cpu_m3
        disco_m3 = disco_m1 * 0.95
        disco_m3 = 0 if disco_m3 <= 0 else disco_m3

        print("""
            Totem 1: CPU, {:.1f}, Memória, {:.1f}, Disco, {:.1f}
            Totem 2: CPU, {:.1f}, Memória, {:.1f}, Disco, {:.1f}
            Totem 3: CPU, {:.1f}, Memória, {:.1f}, Disco, {:.1f}
            """.format(cpu_m1, ram_m1, disco_m1,
                       cpu_m2, ram_m2, disco_m2,
                       cpu_m3, ram_m3, disco_m3)
            )

        cursor.execute(f"CALL inserirDadosTotem ('TLT-1', 'Memória', {ram_m1:.1f}, 'CPU', {cpu_m1:.1f}, 'Disco', {disco_m1:.1f}, NOW());")
        cursor.execute(f"CALL inserirDadosTotem ('TLT-2', 'Memória', {ram_m2:.1f}, 'CPU', {cpu_m2:.1f}, 'Disco', {disco_m2:.1f}, NOW());")
        cursor.execute(f"CALL inserirDadosTotem ('TLT-3', 'Memória', {ram_m3:.1f}, 'CPU', {cpu_m3:.1f}, 'Disco', {disco_m3:.1f}, NOW());")
            
        conexao.commit()

        # time.sleep(1)
 

