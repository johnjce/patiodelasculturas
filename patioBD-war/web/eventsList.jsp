<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
<%@page import="ejb.SingletonBeanLog"%>
<%@page import="ejb.StaticsBean"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Commands.EventsCommand"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="templateBase/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    Object user = session.getAttribute("nickName");
    if(user==null)
        user = "Invitado";
    StaticsBean sb = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/StaticsBean!ejb.StaticsBean");
    sb.setUser(user.toString());
    sb.addPage("eventsList.jsp");
    SingletonBeanLog lb = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/SingletonBeanLog!ejb.SingletonBeanLog");
    lb.setUser(user.toString());
    lb.add(Arrays.asList("eventsList.jsp", user.toString()),"pageEventLis");
%>


<script lang="text/javascript">
    function dele(id, act) {
        $.ajax({
            data: {"id": id, "command": "EventsCommand", "act": act},
            url: "Home",
            type: "post",
            success: function () {
                $("#row" + id).empty();
            }
        });
    }

    function edit(id, act) {
        document.location.href = 'Home?command=EventsCommand&act=' + act + '&id=' + id;
    }
</script>



<div class="container">
    <div class="row rows" style="width:100%">
        <table id="example" class="table table-striped table-bordered" style="width:100%">
            <thead>
                <tr>
                    <th>Titulo</th>
                    <th>Descripción</th>
                    <th>Lugar</th>
                    <th>Fecha</th>
                    <th>Cartel</th>
                        <c:if test="${sessionScope.privilegeLevel == 0 || sessionScope.privilegeLevel == 1}">
                        <th colspan="2">Acciones</th>
                        </c:if>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${sessionScope.eventsList}" var="event">
                    <input type="hidden" name="${sessionScope.dateList.add(event.getEventDate())}"/>
                    <tr id="row${event.getId()}">
                        
                        <td><c:out value="${event.getTitle() }"/></td>
                        <td><c:out value="${event.getDescription()   }"/></td>
                        <td><c:out value="${event.getPlaceEvent()}"/></td>
                        <td><fmt:formatDate value="${event.getEventDate()}" pattern="dd/MM/yyyy" /></td>
                        <td><img src="Home?command=EventsCommand&id=${event.getId()}"></td>
                            <c:if test="${sessionScope.privilegeLevel == 0 || sessionScope.privilegeLevel == 1}">
                            <td>
                                <button class='btn btn-lg ' style='background-color:transparent;' onclick="edit(${event.getId()}, 'edit')"> 
                                    <div style='text-align:center;'><i class="fas fa-edit"></i></div>  
                                </button>
                            </td>
                            <td>
                                <button class='btn btn-lg ' style='background-color:transparent;' onclick="dele(${event.getId()}, 'del')"> 
                                    <div style='text-align:center;'><i class="fas fa-trash-alt"></i></div>  
                                </button>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <c:if test="${sessionScope.total != null && param.page != null}">                
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:if test="${param.page > 1}">
                        <li class="page-item">
                            <a class="page-link" href="Home?command=EventsCommand&filter=${param.filter}&page=${param.page - 1}">Anterior</a>
                        </li>
                    </c:if>
                    <c:forEach begin="1" end="${(sessionScope.total/10)+1}" var="page">
                        <c:if test="${page != param.page}">
                            <li class="page-item"><a class="page-link" href="Home?command=EventsCommand&filter=${param.filter}&page=${page}">${page}</a></li>
                            </c:if>
                        </c:forEach>
                        <c:if test="${sessionScope.total/10 > param.page}">
                        <li class="page-item"><a class="page-link" href="Home?command=EventsCommand&filter=${param.filter}&page=${param.page + 1}">Siguiente</a></li>
                        </c:if>
                </ul>
            </nav>
        </c:if>

    </div>
</div>

<%@include file="templateBase/footer.jsp" %>