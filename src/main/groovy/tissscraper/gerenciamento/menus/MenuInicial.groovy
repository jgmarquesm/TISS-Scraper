package tissscraper.gerenciamento.menus

import static tissscraper.gerenciamento.menus.MenuCRUDEmail.crudEmail
import static tissscraper.gerenciamento.menus.MenuColetaDeDados.coletaDeDados
import static tissscraper.gerenciamento.relatorio.EnviarRelatorio.enviarRelatorio

class MenuInicial {

    static void inicial(){
        Scanner entrada = new Scanner(System.in)
        println """>>>>>>>>>>>>>>>>>>>>>>> Menu <<<<<<<<<<<<<<<<<<<<<<<
> 1 - Coleta de dados
> 2 - Opções da lista envios por e-mail
> 3 - Enviar relatório da coleta de dados por e-mail
> 4 - Sair
"""
        int opcao = entrada.nextInt()

        switch (opcao) {
            case 1:
                coletaDeDados()
                break
            case 2:
                crudEmail()
                break
            case 3:
                enviarRelatorio()
                break
            case 4: break
        }
        System.exit(0)
    }
}

