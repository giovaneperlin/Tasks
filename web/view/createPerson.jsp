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
        <title>Cadastro de Pessoas</title>
    </head>
    <body>
        <nav class="navbar-fixed-top navbar-default">
            <div class="container-fluid">
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="/Tasks/createPerson">Cadastrar Pessoas
                                <span class="sr-only">    
                                </span>
                            </a>
                        </li>
                        <li>
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
                            <h3>PESSOA CADASTRADA COM SUCESSO</h3>
                        </div>
                    </div>
                </res:if>
                <res:if test="${created == false}">
                    <div class="row  bg-danger">
                        <div class="col-md-12 text-center"  style="height: 50px;">
                            <h3>ERRO AO CADASTRAR PESSOA</h3>
                        </div>
                    </div>
                </res:if>
            </res:if>
            <div class="row" style="margin-top: 10px;">
                <form action="/Tasks/createdPerson" method="POST">
                    <div class="form-group">
                        <label for="name">Nome:</label>
                        <input type="text" class="form-control" name="name" id="name" placeholder="Digite o nome da pessoa" required>
                    </div>
                    <div class="form-group">
                        <label for="age">Idade:</label>
                        <input type="number" class="form-control" name="age" id="age" placeholder="Digite a idade da pessoa" required>
                    </div>
                    <div class="form-group">
                        <label for="role">Função:</label>
                        <input type="text" class="form-control" name="role" id="role" placeholder="Digite a função da pessoa" required>
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
