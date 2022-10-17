package tissscraper.gerenciamento.relatorio

import tissscraper.App

import static tissscraper.DAO.EmailDAO.read
import static tissscraper.gerenciamento.email.EnviarEmail.enviarEmail

class EnviarRelatorio {

    static void enviarRelatorio(){
        ArrayList<ArrayList<String>> pessoas = read()

        for (ArrayList<String> pessoa : pessoas){
            enviarEmail(pessoa[0], pessoa[1])
        }
        App.main()
    }
}
