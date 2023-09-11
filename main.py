import tkinter as tki
from time import sleep as s
from tkinter import *
from banco import conectar
from inserir import Insert
from tkinter import messagebox
import psutil
import matplotlib.pyplot as plt


class tela:
    def __init__(self, master=None):
         self.globalBanc = None
         self.fonte = ("Verdana", "8")
         self.container1 = Frame(master)
         self.container1["pady"] = 10
         self.container1.pack()
         self.container2 = Frame(master)
         self.container2["padx"] = 20
         self.container2["pady"] = 5
         self.container2.pack()
         self.container3 = Frame(master)
         self.container3["padx"] = 20
         self.container3["pady"] = 5
         self.container3.pack()
         self.container4 = Frame(master)
         self.container4["padx"] = 20
         self.container4["pady"] = 5
         self.container4.pack()
         self.container5 = Frame(master)
         self.container5["padx"] = 20
         self.container5["pady"] = 5
         self.container5.pack()
         self.container6 = Frame(master)
         self.container6["padx"] = 60
         self.container6["pady"] = 10
         self.container6.pack()


         self.lblemail= Label(self.container1, text="E-mail:",
            font=self.fonte, width=10)
         self.lblemail.pack(side=LEFT)

         self.txtemail = Entry(self.container2)
         self.txtemail["width"] = 25
         self.txtemail["font"] = self.fonte
         self.txtemail.pack(side=LEFT)

         self.lblsenha= Label(self.container3, text="Senha:",
         font=self.fonte, width=10)
         self.lblsenha.pack(side=LEFT)

         self.txtsenha = Entry(self.container4)
         self.txtsenha["width"] = 25
         self.txtsenha["show"] = "*"
         self.txtsenha["font"] = self.fonte
         self.txtsenha.pack(side=LEFT)

         self.lblmsg = Label(self.container6, text="")
         self.lblmsg["font"] = ("Verdana", "9", "italic")
         self.lblmsg.pack()
         
         self.bntInsert = Button(self.container5, text="Consultar",
         font=self.fonte, width=12)
         self.bntInsert["command"] = self.consultarUser
         self.bntInsert.pack (side=LEFT)


    def consultarUser(self):

        self.globalBanc = Insert()
        
        messagebox.showinfo("Title", "Message")

        self.globalBanc.email = self.txtemail.get()
        self.globalBanc.senha = self.txtsenha.get()
        
        resposta = self.globalBanc.consultar()

        self.lblmsg["text"] = resposta
        s(3)
        if resposta == "Dados existem":
            self.trocar()

        self.txtemail.delete(0, END)
        self.txtsenha.delete(0, END)
        
    def trocar(self):
        self.lblemail["width"] = 100
        self.lblsenha["width"] = 100
        self.lblmsg["width"] = 140
        self.txtemail.pack_forget()
        self.txtsenha.pack_forget()
        self.bntInsert.pack_forget()
        while True:
            porcentagem_cpu = psutil.cpu_percent()
            memoria = psutil.virtual_memory()
            porcentagem_memoria = memoria.percent
            frequencia_cpu = psutil.cpu_freq().current

            self.lblemail["text"] = (f"Porcentagem de cpu é {porcentagem_cpu}%")
            self.lblsenha["text"] = (f"Frequencia de cpu é {frequencia_cpu}")
            self.lblmsg["text"] = (f"Porcentagem de memoria é {porcentagem_memoria}%")

            print(f"A porcentagem de memoria é {porcentagem_memoria}")
            print(f"A porcentagem de cpu é {porcentagem_cpu}")
            print(f"A frequencia da sua cpu é {frequencia_cpu}")
            print("========================================")


            self.globalBanc.percCPU = porcentagem_cpu
            self.globalBanc.memoria = porcentagem_memoria
            self.globalBanc.freq = frequencia_cpu

            self.globalBanc.inserirMetricas()

           

            root.update()
            s(5)
        
root = tki.Tk()
tela(root)
root.mainloop()