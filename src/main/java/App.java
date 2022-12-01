import Controller.EmailController;
import DAO.ContatoDAO;
import factory.ConnectionFactory;
import model.Contato;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.List;

public class App {
    public static void main(String[] args) throws AddressException, UnsupportedEncodingException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recoveryConnection();

        ContatoDAO contatoDAO = new ContatoDAO(connection);

        StringBuilder stringBuilder = new StringBuilder();
        criarTemplateEmail(stringBuilder);

        EmailController emailController = new EmailController();

        //Como é uma senha privada eu criei uma classe e passei como valor do atributo a senha, porém
        //a classe vai ficar oculta pra você, mostrando somente o método responsável por pegar a senha e o usuário.
        ConfiguracaoPrivada configuracaoPrivada = new ConfiguracaoPrivada();
        emailController.setEmail(configuracaoPrivada.getUsuario());
        emailController.setSenha(configuracaoPrivada.getSenha());
        emailController.setRemetente("Oliver");

        List<Contato> listaTodosContatos = contatoDAO.listar();

        String formatadorEmail = "";
        for (Contato listaTodosContato : listaTodosContatos) {
            formatadorEmail += listaTodosContato.getEmail().concat(",");
        }

        emailController.setDestinatarios(formatadorEmail);
        emailController.setCabecalho("Fala Dev!");
        emailController.setAssunto(stringBuilder.toString());

        try{
            emailController.enviar(true);
            //verificadora = false;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    public static void criarTemplateEmail(StringBuilder stringBuilder){
        stringBuilder.append("<div style='border:solid 2px cyan; background:whitesmoke; width:700px; height:400px; margin:5% auto'>");
        stringBuilder.append("<h1 style='font-size:2.5rem; color:#000; text-align:center;'>Oiee tudo bem?!</h1>");
        stringBuilder.append("<p style='font-size:20px; font-weight:bold; font-family:Verdana, sanserif;'> Você está recebendo esse email automático, do programa em java do igor</p> <br>");
        stringBuilder.append("<p>ola </p>");
        stringBuilder.append("<img width='200px' src='https://imagens.brasil.elpais.com/resizer/sdzoaMy4wqjKtOQzl9jh_9rHdkw=/1960x0/arc-anglerfish-eu-central-1-prod-prisa.s3.amazonaws.com/public/WQGOZS5S7D3DCLWMVVJM7S5VAQ.jpg'> <br>");
        stringBuilder.append("<a href='https://www.google.com.br' target='_blank'>Clique aqui</a>");
        stringBuilder.append("</div>");
    }
}