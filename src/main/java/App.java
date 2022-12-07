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

        /*Envia email para para todo mundo de uma vez só, atravês de uma string acumuladora*/
        /*String formatadorEmail = "";
        for (Contato listaTodosContato : listaTodosContatos) {
            formatadorEmail += listaTodosContato.getEmail().concat(",");
        }*/

        /*Envia email 1 por 1*/
        listaTodosContatos.forEach(contato -> {
            emailController.setDestinatarios(contato.getEmail());
            emailController.setCabecalho("Fala! " + contato.getNome());
            emailController.setAssunto(stringBuilder.toString());

            try{
                emailController.enviar(true);
                    //verificadora = false;
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public static void criarTemplateEmail(StringBuilder stringBuilder){
        /*stringBuilder.append("<div style='border:solid 2px cyan; background:whitesmoke; width:700px; height:400px; margin:5% auto'>");
        stringBuilder.append("<h1 style='font-size:2.5rem; color:#000; text-align:center;'>Oiee tudo bem?!</h1>");
        stringBuilder.append("<p style='font-size:20px; font-weight:bold; font-family:Verdana, sanserif;'> Você está recebendo esse email automático, do programa em java do igor</p> <br>");
        stringBuilder.append("<p>ola </p>");
        stringBuilder.append("<img width='200px' src='https://imagens.brasil.elpais.com/resizer/sdzoaMy4wqjKtOQzl9jh_9rHdkw=/1960x0/arc-anglerfish-eu-central-1-prod-prisa.s3.amazonaws.com/public/WQGOZS5S7D3DCLWMVVJM7S5VAQ.jpg'> <br>");
        stringBuilder.append("<a href='https://www.google.com.br' target='_blank'>Clique aqui</a>");
        stringBuilder.append("</div>");*/
        stringBuilder.append("<div style='position: relative; background:#14081f; width:800px; height:500px; margin:0 auto; border-bottom: solid 5px #5c65c0; border-radius: 0 0 20px 20px; border-top: solid 3px #5c65c0;'>");
            stringBuilder.append("<h1 style='font-size: 1.35rem; color: white; font-family: Verdana, Geneva, Tahoma, sans-serif; text-align: center; position: relative; top: 6rem;'>Promoção especial para você!!!</h1>");
            stringBuilder.append("<div style='display: flex; justify-content: center;'>");

                stringBuilder.append("<div style='text-align:center;'>");
                    stringBuilder.append("<img align='center' style='width: 150px;' src='https://images.kabum.com.br/produtos/fotos/sync_mirakl/347088/CPU-PC-Gamer-Computador-Intel-I5-2400-Mem-8GB-SSD-240GB-Onboard-Windows-10-trial_1668201514_g.jpg'>");
                    stringBuilder.append("<span style='display: block; color: white; text-align: center; margin-top: 10px;'>R$ 4.500</span>");
                stringBuilder.append("</div>");

                stringBuilder.append("<div>");
                    stringBuilder.append("<img style='width: 150px;' src='https://www.imagensempng.com.br/wp-content/uploads/2020/11/005.png'>");
                    stringBuilder.append("<span style='display: block; color: white; text-align: center; margin-top: 10px;'>R$ 3.700</span>");
                stringBuilder.append("</div>");

                //stringBuilder.append("<img style='position: absolute; bottom: 50px; width: 300px;' src='https://www.campaignmonitor.com/wp-content/uploads/2019/09/CountdownGIF.gif'>");
            stringBuilder.append("</div>");
        stringBuilder.append("</div>");
    }
}

