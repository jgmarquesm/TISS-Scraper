package tissscraper.gerenciamento.menus

import tissscraper.App
import static tissscraper.gerenciamento.email.AtualizacaoDeEmail.atualizar
import static tissscraper.gerenciamento.email.CadastroDeEmail.cadastrar
import static tissscraper.gerenciamento.email.ExclusaoDeEmail.excluir

class MenuCRUDEmail {

    static void crudEmail(){
        Scanner entrada = new Scanner(System.in)
        println """>>>>>>>>>> Selecione uma opção <<<<<<<<<<
> 1 - Cadastrar e-mail 
> 2 - Atualizar e-mail
> 3 - Excluir e-mail
> 4 - Voltar
> 5 - Sair
"""
        int opcao = entrada.nextInt()
        switch (opcao){
            case 1:
                cadastrar()
                App.main()
                break
            case 2:
                atualizar()
                App.main()
                break
            case 3:
                excluir()
                App.main()
                break
            case 4:
                App.main()
                break
            case 5:
                break
        }
    }
}
