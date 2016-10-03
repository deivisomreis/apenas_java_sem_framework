Este projeto faz parte de meu portifólio de Aplicações WEB Java. <br/>
Neste projeto em especifico não utilizo nem um tipo de framework tanto para persistência como produtividade MVC (como SpringMVC / JSF)<br/>
<br/>
Tecnologias envolvidas:<br/>
<br/>
1 - Java 8 <br/>
2 - Servlet <br/>
3 - BD  MySQL <br/>
4 - Filtros (controle de sessão e acesso a endereços relativos / bloquear acessos diretos / e redirecionamento em caso de acesso sem login) <br/>
5 - Web-Container TomCat8 <br/>
6 - UI - BootStrap 3 <br/>
<br/>
Neste projeto como os demais, estão empregados conceitos de padrões de projetos (MVC), irei descriminar abaixo os atores e suas responsabilidades, as URL relativas para acesso e um
breve comentário sobre o que faz e quem a trata!
<br/>
<br/>
Atores do sistema: adminstrador / Usuários
<br/>
URLS ADMIN:<br/>
<br/>
localhost:8080/login/admin (LoginServlet) <br/>
localhost:8080/logout/admin (LogoutServlet) <br/>
localhost:8080/cadastro/usuario (CadastroServlet) <br/>
localhost:8080/admin/show (AdminServlet) <br/>
localhost:8080/admin/edit (AdminServlet) <br/>
localhost:8080/admin/home (AdminServlet) <br/>
localhost:8080/admin/user/list (AdminServlet) <br/>
localhost:8080/admin/user/active (AdminServlet) <br/>
localhost:8080/admin/user/disable (AdminServlet) <br/>
localhost:8080/admin/user/show (AdminServlet) <br/>

<br/>
<br/>


URLS USUÁRIO: <br/>
localhost:8080/login/usuario (LoginServlet) <br/>
localhost:8080/logout/usuario (LogoutServlet) <br/>

<br/>

Como observado, é um sistema simples, mostrando em execução um CRUD. Cadastro de Usuários, Ativação de Acesso.
