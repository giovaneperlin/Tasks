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
        <title>Listar em aberto</title>
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
                        <li>
                            <a href="/Tasks/createTask">Cadastrar Tarefas</a>
                        </li>
                        <li class="dropdown active">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Tarefas
                                <span class="caret"> 
                                </span>
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="/Tasks/listAllTasks">Listar todas</a>
                                </li>
                                <li class="active">
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
            <div class="row" style="margin-top: 10px;">
                <div class="table-responsive">
                    <table class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <tr>
                                <th class="cold-md-4">
                                    NOME DA PESSOA
                                </th>
                                <th class="cold-md-3" style="text-align: center;">
                                    TAREFA
                                </th>
                                <th class="cold-md-1" style="text-align: center;">
                                    DURAÇÃO
                                </th>
                                <th class="cold-md-1" style="text-align: center;">
                                    DATA
                                </th>
                                <th class="cold-md-2" style="text-align: center;">
                                    ÁREA
                                </th>
                                <th class="cold-md-1" style="text-align: center;">
                                    FINALIZADA
                                </th>
                            </tr>
                            </tr>
                        </thead>
                        <tbody>
                            <res:forEach items="${persons}" var="person">
                                <res:forEach items="${person.getTasks()}" var="personTask">
                                    <tr>
                                        <td class="cold-md-4">
                                            ${person.getName()}
                                        </td>
                                        <td class="cold-md-3" style="text-align: center;">
                                            ${personTask.getName()}
                                        </td>
                                        <td class="cold-md-1" style="text-align: center;">
                                            ${personTask.getLength()}
                                        </td>
                                        <td class="cold-md-1" style="text-align: center;">
                                            ${personTask.getDate()}
                                        </td>
                                        <td class="cold-md-2" style="text-align: center;">
                                            ${personTask.getField()}
                                        </td>
                                        <td class="cold-md-1" style="text-align: center;">
                                            <res:if test="${personTask.isDone() == true}">
                                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                                            </res:if>
                                            <res:if test="${personTask.isDone() == false}">
                                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                            </res:if>
                                        </td>
                                    </tr>
                                </res:forEach>
                            </res:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
