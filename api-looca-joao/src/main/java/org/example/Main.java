package org.example;

import com.github.britooo.looca.api.core.Looca;
import org.example.DAO.*;
import org.example.jira.Chamado;
import org.example.jira.CredencialJira;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    static int idTotem = 0;
    static String urlJira;
    static String usuarioJira;
    static String tokenJira;
    static String projectKeyJira;

    static int metricaAtencaoCpu = 85;
    static int metricaCriticoCpu = 95;

    static int metricaAtencaoMemoria = 85;
    static int metricaCriticoMemoria = 95;

    static int metricaAtencaoDisco = 0;
    static int metricaCriticoDisco = 0;

    static boolean alertaAtencaoCpuExibido = false;
    static boolean alertaCriticoCpuExibido = false;

    static boolean alertaAtencaoMemoriaExibido = false;
    static boolean alertaCriticoMemoriaExibido = false;

    static boolean alertaAtencaoDiscoExibido = false;
    static boolean alertaCriticoDiscoExibido = false;

    public static void main(String[] args) {

        Timer timer = new Timer();

        Looca looca = new Looca();

        Cpu cpu = new Cpu();
        Memoria memoria = new Memoria();
        Disco disco = new Disco();

        RegistroDAO registroDAO = new RegistroDAO();
        AlertaDAO alertaDAO = new AlertaDAO();

        Alerta alertaCritico = new Alerta(null, 1, "CRITICO", null);
        Alerta alertaAtencao = new Alerta(null, 2, "ATENCAO", null);

        urlJira = "https://conway-airway.atlassian.net/rest/api/3/issue";
        usuarioJira = "conway.sptech@gmail.com";
        tokenJira = "ATATT3xFfGF0n8TbSQX5WH2TzqL0CjrHe6nXvDu7V301PPG_Dhu8br_L9SNISqg0jSXtELnx84KSLSm34PtqmgLsuFAPZ9EajesQPdP2iSDPgA3h-8pqHxmPb7BBT9T4W0x_tbegI4rskBdut8JU4lEEK-EHEzUprrQZ7Qjp41-SH7v7q6VfZ_Y=23D1A62D";
        projectKeyJira = "AWLATAM";

        CredencialJira credencialJira = new CredencialJira(urlJira, usuarioJira, tokenJira, projectKeyJira);

        TimerTask getUsoComponentes = new TimerTask() {
            @Override
            public void run() {

                int usoDiscos = disco.getUso();
                int usoCpu = cpu.getUso();
                int usoMemoria = memoria.getUso();

                System.out.println("----------------------------------------------------------------");
                System.out.println("CPU: " + usoCpu + "%");
                System.out.println("Memória: " + usoMemoria + "%");
                System.out.println("Disco: " + usoDiscos + "%");

                registroDAO.inserirRegistrosTotem(idTotem, usoCpu, usoMemoria, usoDiscos);
                
                if (usoCpu >= metricaAtencaoCpu && usoCpu < metricaCriticoCpu){

                    System.out.println("CPU EM ATENÇÃO!!!");
                    alertaDAO.inserirAlerta(alertaAtencao.getTipo(), alertaAtencao.getDescricao(), idTotem, 1);

                    if (!alertaAtencaoCpuExibido){

                        Chamado chamado = new Chamado("CPU ATENÇÃO", "Ultrapassou métrica");

                        chamado.abrirChamado(credencialJira);

                        alertaAtencaoCpuExibido = true;
                    }

                } else if (usoCpu >= metricaCriticoCpu) {

                    System.out.println("CPU CRÍTICA!!!");
                    alertaDAO.inserirAlerta(alertaCritico.getTipo(), alertaCritico.getDescricao(), idTotem, 1);

                    if (!alertaCriticoCpuExibido){

                        Chamado chamado = new Chamado("CPU CŔITICO", "Ultrapassou métrica");

                        chamado.abrirChamado(credencialJira);

                        alertaCriticoCpuExibido = true;
                    }


                }

                if (usoMemoria >= metricaAtencaoMemoria && usoMemoria < metricaCriticoMemoria){

                    System.out.println("MEMÓRIA EM ATENÇÃO!!!");
                    alertaDAO.inserirAlerta(alertaAtencao.getTipo(), alertaAtencao.getDescricao(), idTotem, 2);

                    if (!alertaAtencaoMemoriaExibido){

                        Chamado chamado = new Chamado("MEMÓRIA ATENÇÃO", "Ultrapassou métrica");

                        chamado.abrirChamado(credencialJira);

                        alertaAtencaoMemoriaExibido = true;
                    }

                } else if (usoMemoria >= metricaCriticoMemoria) {

                    System.out.println("MEMÓRIA CŔITICA!!!");
                    alertaDAO.inserirAlerta(alertaCritico.getTipo(), alertaCritico.getDescricao(), idTotem, 2);

                    if (!alertaCriticoMemoriaExibido){

                        Chamado chamado = new Chamado("MEMÓRIA CRITICO", "Ultrapassou métrica");

                        chamado.abrirChamado(credencialJira);

                        alertaCriticoMemoriaExibido = true;
                    }

                }

                System.out.println("----------------------------------------------------------------");


            }
        };

        TimerTask resetarAlertas = new TimerTask() {
            @Override
            public void run() {
                alertaAtencaoCpuExibido = false;
                alertaCriticoCpuExibido = false;

                alertaAtencaoMemoriaExibido = false;
                alertaCriticoMemoriaExibido = false;

                alertaAtencaoDiscoExibido = false;
                alertaCriticoDiscoExibido = false;
            }
        };

        // MENU

        Scanner scanNum = new Scanner(System.in);
        Scanner scanStr = new Scanner(System.in);

        Integer opcaoMenu = 0;

        System.out.println("Seja bem vindo ao sistema de monitoramento AirWay!");

        Funcionario funcionarioLogin;

        do {

            System.out.println("Digite seu email");
            /*String emailLogin = scanStr.nextLine();*/
            String emailLogin = "ana.carolina@latam.com";

            System.out.println("Digite sua senha");
            /*String senhaLogin = scanStr.nextLine();*/
            String senhaLogin = "12345";

            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

            funcionarioLogin = funcionarioDAO.autenticar(emailLogin, senhaLogin);

        } while (funcionarioLogin == null);

        Integer idEmpresa = funcionarioLogin.getFkEmpresa();

        System.out.println("Digite a url do projeto Jira para abertura de chamados:");
        urlJira = scanStr.nextLine();

        System.out.println("Digite o usuario do projeto Jira:");
        usuarioJira = scanStr.nextLine();

        System.out.println("Digite o token do projeto Jira:");
        tokenJira = scanStr.nextLine();

        System.out.println("Digite a key do projeto Jira;");
        projectKeyJira = scanStr.nextLine();


        do {

            System.out.println("""
                    Esse totem já está cadastrado?
                                    
                    1 - Sim
                    2 - Nao
                                    
                    0 - Sair         
                                    
                    """);

            opcaoMenu = scanNum.nextInt();

            switch (opcaoMenu){

                case 1:

                    TotemDAO totemDAO = new TotemDAO();

                    int idTotemSelecionado = 0;

                    do {

                        System.out.println("Selecione o ID do que corresponde a esse totem:");

                        System.out.println(totemDAO.getTotens(idEmpresa));

                        idTotemSelecionado = scanNum.nextInt();

                    } while (totemDAO.getTotemID(idTotemSelecionado, idEmpresa) == null);

                    idTotem = idTotemSelecionado;

                    timer.schedule(getUsoComponentes, 0, 3000);
                    timer.schedule(resetarAlertas, 0, 60000);

                    opcaoMenu = scanNum.nextInt();

                    break;

                case 2:

                    System.out.println("Você deve cadastrar esse totem via web-aplication para prosseguir com o monitoramento!");

            }

        } while (opcaoMenu != 0);





    }
}