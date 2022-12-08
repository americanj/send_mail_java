<h1 align="center" style="font-weight:bold">Java Send Email</h1>

<p align="center">
<img src="https://img.shields.io/badge/STATUS-completo-blue?style=for-the-badge">
</p>

<br>

# **Tópicos**
* [Descrição do Projeto](#descrição-do-projeto)
* [Funcionalidades](#funcionalidades)
* [Aplicação](#aplicação)
* [Técnicas e tecnologias utilizadas](#técnicas-e-tecnologias-utilizadas)
* [Acesso ao Projeto](#acesso-ao-projeto)
* [Abrir e rodar o projeto](#abrir-e-rodar-o-projeto)

## **Descrição do projeto**
<p>O projeto visa enviar email personalizado para uma lista de contatos, definidos em um banco de dados pelo administrador.</p>

## **Funcionalidades**

1. Realiza envio de email de toda uma base de dados.
2. Permite custimizar o email que enviará.
3. Permite ser incluso a outros projetos como uma extensão.

## **Aplicação**

![](https://i.imgur.com/XnEv2S1.gif)

## **Técnicas e tecnologias utilizadas**
- Java
- MariaDB
- Paradigma de orientação a objetos
- InteliJ IDEA
- MySQL Workbench
- javax.mail

## **Acesso ao projeto**
Você pode <a href="https://github.com/oliveiradeigor/send_mail_java">acessar o código fonte do projeto</a> ou <a href="https://github.com/oliveiradeigor/send_mail_java/archive/refs/heads/main.zip">baixá-lo</a>


## **Abrir e rodar o projeto**

1. Primeiramente precisamos gerar uma senha para você utilizar com o java assista esse vídeo: [gerar senha](https://www.youtube.com/watch?v=rGN_favALws) e obtenha sua senha.
2. Vá na classe principal `App.java` e faça o mostrado abaixo:
![](https://i.imgur.com/nnuUXQU.gif)
No meu exemplo de código ele só não está em string, porque meu email e senha estão em uma classe privada, não rastreada pelo git.
3. Vamos criar a base de dados, copie e cole as intruções SQL.
```sql
CREATE SCHEMA only_email_db;
USE only_email_db;

CREATE TABLE tbcontato(
	id INT AUTO_INCREMENT,
    nome VARCHAR(200),
    email VARCHAR(300),
    PRIMARY KEY (id)
);

```
4. Com o banco de dados selecionado, se não tiver dê o comando `USE only_email_db;` e vamos inserir os emails no banco. Dê o comando:
```sql
INSERT INTO tbcontato (nome, email) VALUES ('oliver', 'seuemail@gmail.com');
```
5. Vá na classe `ConnectionFactory` e mude o seu login e senha do banco de dados. Exemplo:
![](https://i.imgur.com/wAzBiYl.gif)

    ### Seu banco não é o MariaDB ?
    1. Vá no arquivo `pom.xml` e adicione a dependência do Mysql em seu projeto. Ex:
    ![](https://i.imgur.com/0td54Ac.gif)

    2. Depois de adicionar a dependencia, digite a url do pacote. Ex:
    ![](https://i.imgur.com/AOojZLG.gif)

6. Tudo pronto, agora você pode entrar na classe `App.class` e começar a modificar os campos para enviar para seus emails. ^^