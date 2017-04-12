<%-- 
    Document   : index
    Created on : Apr 1, 2017, 8:22:59 PM
    Author     : Perlin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="res" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <!-- Latest compiled and minified CSS -->
        <link href="<res:url value="/res/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css"/>
        <!-- Optional theme -->
        <link href="<res:url value="/res/css/bootstrap-theme.min.css"/>" rel="stylesheet" type="text/css"/>
        <!-- Latest compiled and minified JavaScript -->
        <script src="<res:url value="/res/js/jquery-2.2.4.min.js"/>" type="text/javascript"></script>
        <script src="<res:url value="/res/js/bootstrap.min.js"/>" type="text/javascript"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>Cadastro de Tarefas</title>
    </head>
    <body>
        <nav class="navbar-fixed-top navbar-default">
            <div class="container-fluid">
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li><a href="/Tasks/createPerson">Cadastrar Pessoas
                                <span class="sr-only">    
                                </span>
                            </a>
                        </li>
                        <li class="active">
                            <a href="/Tasks/createTask">Cadastrar Tarefas</a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Tarefas
                                <span class="caret"> 
                                </span>
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="/Tasks/listAllTasks">Listar todas</a>
                                </li>
                                <li>
                                    <a href="/Tasks/listOpenTasks">Listar em aberto</a>
                                </li>
                                <li>
                                    <a href="/Tasks/listClosedTasks">Listar encerradas</a>
                                </li>
                                <li role="separator" class="divider">  
                                </li>
                                <li>
                                    <a href="/Tasks/searchTasksByPerson">Buscar por pessoa</a>
                                </li>
                                <li>
                                    <a href="/Tasks/searchTasksByDate">Buscar por data</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div id="container" class="container" style="width: 90%; margin-top: 60px;">
            <res:if test="${created != null}">
                <res:if test="${created == true}">
                    <div class="row bg-info">
                        <div class="col-md-12 text-center" style="height: 50px;">
                            <h3>TAREFA CADASTRADA COM SUCESSO</h3>
                        </div>
                    </div>
                </res:if>
                <res:if test="${created == false}">
                    <div class="row  bg-danger">
                        <div class="col-md-12 text-center"  style="height: 50px;">
                            <h3>ERRO AO CADASTRAR TAREFA</h3>
                        </div>
                    </div>
                </res:if>
            </res:if>
            <div class="row" style="margin-top: 10px;">
                <form action="/Tasks/createdTask" method="POST">
                    <div class="form-group">
                        <label for="name">Tarefa:</label>
                        <input type="text" class="form-control" name="name" id="name" placeholder="Digite a tarefa" required>
                    </div>
                    <div class="form-group">
                        <label for="personId">Pessoa &mdash; Função:</label></br>
                        <select name="personId" class="form-control" required>
                            <res:forEach items="${persons}" var="person">
                                <option value="${person.getId()}">${person.getName()} &mdash; ${person.getRole()}</option>
                            </res:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="length">Duração:</label>
                        <input type="number" class="form-control" name="length" id="length" placeholder="Digite a duração, em dias, da tarefa" required>
                    </div>
                    <div class="form-group">
                        <label for="date">Data:</label>
                        <input type="date" class="form-control" name="date" id="date" required>
                    </div>
                    <div class="form-group">
                        <label for="field">Área:</label>
                        <input type="text" class="form-control" name="field" id="field" placeholder="Digite a área da tarefa" required>
                    </div>
                    <div class="row">
                        <div class="col-md-2 pull-right">
                            <input type="reset" class="btn btn-block btn-default" value="Limpar">
                        </div>
                        <div class="col-md-2 pull-right">
                            <input type="submit" class="btn btn-block btn-primary" value="Cadastrar">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
