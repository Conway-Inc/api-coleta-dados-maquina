import psutil
from tkinter import *
from tkinter import ttk
import platform
import mysql.connector as ms
import requests
import json

conection = ms.connect(
    user='urubu100', password='Urubu@100',
    database='bd_conway',
    raise_on_warnings=True
)
cursor = conection.cursor();
comando = 'INSERT INTO metrica (valor, tipoValor) VALUES(%s, %s), (%s, %s), (%s, %s)'

slack_token = 'xoxb-5783422187908-5820721618452-6dzFbJzI3nzpYtZEEi8JI7O0'
slack_channel = '#notificacoes'

def postar_mensagem(text, blocks = None):
    return requests.post('https://slack.com/api/chat.postMessage', {
        'token': slack_token,
        'channel': slack_channel,
        'text': text,
        'blocks': json.dumps(blocks) if blocks else None
    }).json()


if(platform.system() == 'Linux'):
    so_processador = 'coretemp'
elif(platform.system() == 'Windows'):
    so_processador = 'nvme'

def update():
    porcentagem_cpu = psutil.cpu_percent()
    memoria = psutil.virtual_memory().percent
    frequencia_cpu = psutil.cpu_freq().current
    temperatura_processador = psutil.sensors_temperatures().get(so_processador)[0].current
    qtd_arquivos_sistema = len(psutil.disk_partitions())

    valor_porcentagem_cpu.set(f"Uso de CPU: {porcentagem_cpu:.2f}%")
    valor_frequencia_cpu.set(f"Frequência da CPU: {frequencia_cpu:.2f} MHz")
    valor_memoria.set(f"Memória RAM em uso: {memoria:.2f}%")
    valor_temperatura_processador.set(f"Temperatura do processador: {temperatura_processador:.2f}°C")
    valor_qtd_arquivos_sistema.set(f"Partições do sistema: {qtd_arquivos_sistema} arquivos de sistema")
    
    parametros = (frequencia_cpu ,'MHz' ,porcentagem_cpu, '%', memoria, '%')
    cursor.execute(comando, parametros)
    conection.commit()
    if(porcentagem_cpu >=50.0 and porcentagem_cpu < 75.0):
        postar_mensagem('CPU com 50% de uso!!!')
    elif(porcentagem_cpu >= 75.0 and porcentagem_cpu < 90.0):
        postar_mensagem('CPU com 75% de uso!!!!!!!!!!!!!!')
    elif(porcentagem_cpu >= 90.):
        postar_mensagem('CPU com 90% de uso!!!!!!!!!!!!!')

    if(memoria >= 40.0):
        postar_mensagem('40% de memória ram em uso!!!!!')

    if(temperatura_processador >= 50.0):
        postar_mensagem('Processador com 50ºC!!')
        
    root.after(1500, update)

root = Tk()
root.title("Monitor de sistema")

style = ttk.Style()
style.configure("normal.TLabel", background="#44FF00")
style.configure("alerta.TLabel", background="#FFFF44")
style.configure("perigo.TLabel", background="#FF0000")


mainframe = ttk.Frame(root, padding="40 80")
mainframe.grid(column=0, row=0, sticky=(N, W, E, S))
root.columnconfigure(0, weight=1)
root.rowconfigure(0, weight=1)

valor_porcentagem_cpu = StringVar()
valor_frequencia_cpu = StringVar()
valor_memoria = StringVar()
valor_temperatura_processador = StringVar()
valor_qtd_arquivos_sistema = StringVar()

porcentagem_cpu_label = ttk.Label(mainframe, textvariable=valor_porcentagem_cpu).grid(column=1, row=2, sticky=(W, E))

frequencia_cpu_label = ttk.Label(mainframe, textvariable=valor_frequencia_cpu).grid(column=2, row=2, sticky=(W, E))

memoria_label = ttk.Label(mainframe, textvariable=valor_memoria).grid(column=3, row=2, sticky=(W, E))

temperatura_processador_label = ttk.Label(mainframe, textvariable=valor_temperatura_processador).grid(column=1, row=4, sticky=(W, E))

qtd_arquivos_sistema_label = ttk.Label(mainframe, textvariable=valor_qtd_arquivos_sistema).grid(column=3, row=4, sticky=(W, E))

update()
root.mainloop()
cursor.close()
conection.close()