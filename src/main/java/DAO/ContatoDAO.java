package DAO;

import model.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    private Connection connection;

    public ContatoDAO(Connection connection){
        this.connection = connection;
    }

    public List<Contato> listar(){
        List<Contato> listaContato = new ArrayList<>();
        String sql = "SELECT * FROM tbcontato";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.execute();

            try(ResultSet resultSet = preparedStatement.getResultSet()){
                while(resultSet.next()){
                    Contato contato = new Contato(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3)
                    );
                    //resultSet.getString(contato.getEmail());
                    //resultSet.getString(contato.getNome());

                    listaContato.add(contato);
                }
                return listaContato;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
