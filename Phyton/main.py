from time import sleep as s
from tkinter import *
from banco import conectar
from inserir import Insert
from tkinter import messagebox
import psutil
import smtplib
from email.message import EmailMessage
import requests
import json


slack_token = 'xoxb-5783422187908-5820721618452-9XjNfgui3pjqvLp9UeuDuNXm'
slack_channel = '#teste'
# slack_channel = '#notificações'


me = 'conway.sptech@gmail.com'
you = 'help@conway-inc.on.spiceworks.com'

msg = EmailMessage()

msg['Subject'] = f'Chamado automático'
msg['From'] = me
msg['To'] = you

smtp_server = 'smtp.gmail.com'
smtp_port = 587  # Porta TLS/STARTTLS do Gmail
username = 'conway.sptech@gmail.com'
password = 'mipjexlgaukookth'


send = smtplib.SMTP(smtp_server, smtp_port)
send.starttls()  # Inicia a conexão TLS
send.login(username, password)

def postar_mensagem(text, blocks = None):
    return requests.post('https://slack.com/api/chat.postMessage', {
        'token': slack_token,
        'channel': slack_channel,
        'text': text,
        'blocks': json.dumps(blocks) if blocks else None
    }).json()


class tela:
    def __init__(self, master=None):
         
         self.globalBanc = None
         self.fonte = ("Verdana", "16")
         self.container1 = Frame(master)
         self.container1["pady"] = 10
         self.container1.grid(column=1, row=1)

         self.container2 = Frame(master)
         self.container2["padx"] = 20
         self.container2["pady"] = 5
         self.container2.grid(column=1, row=2)

         self.container3 = Frame(master)
         self.container3["padx"] = 20
         self.container3["pady"] = 5
         self.container3.grid(column=1, row=3)

         self.container4 = Frame(master)
         self.container4["padx"] = 20
         self.container4["pady"] = 5
         self.container4.grid(column=1, row=4)

         self.container5 = Frame(master)
         self.container5["padx"] = 20
         self.container5["pady"] = 5
         self.container5.grid(column=1, row=6)

         self.container6 = Frame(master)
         self.container6["padx"] = 60
         self.container6["pady"] = 10
         self.container6.grid(column=1, row=5)


         self.lblemail= Label(self.container1, text="E-mail:", font=self.fonte, width=10)
         self.lblemail.grid()

         self.inputEmail = Entry(self.container2)
         self.inputEmail["width"] = 25
         self.inputEmail["font"] = self.fonte
         self.inputEmail.grid()

         self.lblsenha= Label(self.container3, text="Senha:", font=self.fonte, width=10)
         self.lblsenha.grid()

         self.inputSenha = Entry(self.container4)
         self.inputSenha["width"] = 25
         self.inputSenha["show"] = "*"
         self.inputSenha["font"] = self.fonte
         self.inputSenha.grid()
         
         self.btnEntrar = Button(self.container5, text="Entrar", font=self.fonte, width=10)
         self.btnEntrar["command"] = self.consultarUser
         self.btnEntrar.grid ()

         self.lblmsg = Label(self.container6, text="", font=self.fonte, width=10)
         self.lblmsg.grid()

        
         


    def consultarUser(self):

        self.globalBanc = Insert()
        

        self.globalBanc.email = self.inputEmail.get()
        self.globalBanc.senha = self.inputSenha.get()
        
        resposta = self.globalBanc.consultar()

        self.lblmsg["text"] = resposta
        s(3)
        if resposta == "OK":
            self.trocar()

        self.inputEmail.delete(0, END)
        self.inputSenha.delete(0, END)
        
    def trocar(self):
        self.lblemail["width"] = 50
        self.lblsenha["width"] = 50
        self.lblmsg["width"] = 50
        self.btnEntrar["width"] = 1
        self.btnEntrar["height"] = 1
        self.inputEmail.grid_forget()
        self.inputSenha.grid_forget()
        self.btnEntrar.grid_forget()
        while True:
            porcentagem_cpu = psutil.cpu_percent()
            memoria = psutil.virtual_memory()
            porcentagem_memoria = memoria.percent
            frequencia_cpu = psutil.cpu_freq().current / 1000

            if(porcentagem_cpu >=50.0 and porcentagem_cpu < 75.0):
                postar_mensagem('CPU com 50% de uso')
                msg.set_content('CPU com 50% de uso')
                send.send_message(msg)
            
            elif(porcentagem_cpu >= 75.0 and porcentagem_cpu < 90.0):
                postar_mensagem('CPU com 75% de uso')
                msg.set_content('CPU com 75% de uso')
                send.send_message(msg)
            
            elif(porcentagem_cpu >= 90.):
                postar_mensagem('CPU com 90% de uso')
                msg.set_content('CPU com 90% de uso')
                send.send_message(msg)


            if(porcentagem_memoria >= 50.0):
                postar_mensagem('50% de memória ram em uso')
                msg.set_content('50% de memória ram em uso')
                send.send_message(msg)
                
            elif(porcentagem_memoria >= 60.0):
                postar_mensagem('40% de memória ram em uso')
                msg.set_content('40% de memória ram em uso')
                send.send_message(msg)

            self.lblemail["text"] = (f"Porcentagem de cpu é {porcentagem_cpu:.2f}%")
            self.lblsenha["text"] = (f"Frequencia de cpu é {frequencia_cpu:.2f}GHz")
            self.lblmsg["text"] = (f"Porcentagem de memoria é {porcentagem_memoria:.2f}%")

            print(f"A porcentagem de memoria é {porcentagem_memoria:.2f}%")
            print(f"A porcentagem de cpu é {porcentagem_cpu:.2f}%")
            print(f"A frequencia da sua cpu é {frequencia_cpu:.2f}GHz")
            print("========================================")


            self.globalBanc.percCPU = porcentagem_cpu
            self.globalBanc.memoria = porcentagem_memoria
            self.globalBanc.freq = frequencia_cpu

            self.globalBanc.inserirMetricas()

            root.update()
            s(5)
        
root = Tk()
tela(root)
root.mainloop()
send.quit()