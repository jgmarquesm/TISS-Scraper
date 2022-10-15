package tissscraper.DAO

import groovy.sql.Sql

import java.sql.SQLException

class EmailDAO {

    static def url = 'jdbc:postgresql://localhost:5432/TISS-Scraper'
    static def user = 'jgmarquesm'
    static def password = 'postgres'
    static def driver = 'org.postgresql.Driver'

    private static Sql conectar() { Sql sql = Sql.newInstance url, user, password, driver }

    private static void desconectar(connection) { connection.close() }

    static void create(String email) {
        Sql create = conectar()
        List<String> params = [email]
        create.executeInsert('INSERT INTO listaDeEnvios (email) VALUES (?)', params)
        desconectar(create)
    }

    static List<String> read(){
        Sql read = conectar()
        String email
        ArrayList<String> emails = new ArrayList<>()
        read.query("SELECT email FROM listaDeEnvios"){
            while(it.next()){
                emails.add(it.getString('email'))
            }
        }
        desconectar(read)
        emails
    }

    static void update(String email, String novoEmail){
        Sql update = conectar()
        try {
            update.executeUpdate "UPDATE listaDeEnvios SET email  = " + """'${novoEmail}'""" + " WHERE email = " + """'${email}'"""
            println "E-mail atualizado com sucesso"
        } catch (SQLException e) {
                println "Este e-mail ainda não está cadastrado!"
        } finally {
            desconectar(update)
        }
    }

    static void delete(email){
        Sql delete = conectar()
        try {
            delete.execute "DELETE FROM listaDeEnvios WHERE email = $email"
            println "E-mail excluído com sucesso"
        } catch (SQLException e) {
            println "Este e-mail ainda não está cadastrado!"
        } finally {
            delete.close()
        }
    }
}
