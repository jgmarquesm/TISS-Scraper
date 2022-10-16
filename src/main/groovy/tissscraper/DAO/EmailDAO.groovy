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

    static void create(String nome, String email) {
        Sql create = conectar()
        List<String> params = [email, nome]
        create.executeInsert('INSERT INTO listaDeEnvios (email, nome) VALUES (?, ?)', params)
        desconectar(create)
    }

    static ArrayList<String> read(){
        Sql read = conectar()
        ArrayList<List<String>> pessoas = new ArrayList<>()
        read.query("SELECT nome, email FROM listaDeEnvios"){
            while(it.next()){
                pessoas.add([it.getString('nome'), it.getString('email')])
            }
        }
        desconectar(read)
        pessoas
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
