from banco import conectar
import math

class Insert(object):
    def __init__(self, email = "", senha = "", percCPU = 0, memoria = 0, freq = 0):
        self.idUsuario = ''
        self.idTotem = ''
        self.idEmpresa = ''
        self.idAeroporto = ''
        self.email = email
        self.senha = senha
        self.freq = 0
        self.memoria = 0
        self.percCPU = 0
        self.banco = conectar.iniciar(self)

    def consultar(self):
        bancovar = conectar.iniciar(self)
        self.banco = bancovar
        bancoexec = bancovar.cursor(buffered=True)
        resultado = []
        bancoexec.execute(f"SELECT idFuncionario, idTotem, fkEmpresa, fkAeroporto FROM Funcionario JOIN Empresa ON fkEmpresa = idEmpresa JOIN Totem ON fkEmpresa = idEmpresa WHERE emailFunc = '{self.email}' AND senhaFunc = '{self.senha}'")
        for linha in bancoexec:
          self.idUsuario = linha[0]
          self.idTotem = linha[1]
          self.idEmpresa = linha[2]
          self.idAeroporto = linha[3] 
          for coisa in linha:
              resultado.append(coisa)

        
        res = bancovar.commit()

        if len(resultado) > 0:
            return "OK"
        else:
            return "ERRO"

    def inserirMetricas(self):
        
        execucao = self.banco.cursor()
        varPerc = "%"
        varMz = "Mhz"
      
        addFreq = "INSERT INTO Dados (valor, fkComponente, fkTotem, dataHoraDados) VALUES (%s, %s, %s, now())"
        dataFreq = (float(self.freq), 1, self.idTotem)
        addPerc = "INSERT INTO Dados (valor, fkComponente, fkTotem, dataHoraDados) VALUES (%s, %s, %s, now())"
        dataPerc = (float(self.percCPU), 2, self.idTotem)
        addMemory = "INSERT INTO Dados (valor, fkComponente, fkTotem, dataHoraDados) VALUES (%s, %s, %s, now())"
        dataMemory = (self.memoria, 3, self.idTotem)

 
        execucao.execute(addFreq, dataFreq)
        self.banco.commit()
        execucao.execute(addPerc, dataPerc)
        self.banco.commit()
        execucao.execute(addMemory, dataMemory)
        self.banco.commit()

        return True
       
     
            
    