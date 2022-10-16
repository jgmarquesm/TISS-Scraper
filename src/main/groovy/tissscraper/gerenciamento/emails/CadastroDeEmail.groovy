package tissscraper.gerenciamento.emails

import static tissscraper.DAO.EmailDAO.create

class CadastroDeEmail {

    static void cadastrar(){

        Scanner entrada = new Scanner(System.in)

        println "Digite seu nome: "
        String nome = entrada.nextLine()
        println "Digite o seu melhor e-mail: "
        String email = entrada.next()

        create(nome, email)
    }
}
