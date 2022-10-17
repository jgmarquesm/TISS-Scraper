package tissscraper.gerenciamento.email

import static tissscraper.DAO.EmailDAO.update

class AtualizacaoDeEmail {

    static void atualizar(){

        Scanner entrada = new Scanner(System.in)

        println "Digite o seu e-mail: "
        String email = entrada.nextLine()
        println "Digite o seu novo e-mail: "
        String novoEmail = entrada.next()

        update(email, novoEmail)
        println "Cadastro atualizado com sucesso!"
    }
}
