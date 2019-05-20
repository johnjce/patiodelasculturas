<%@page import="java.util.Map"%>
<%@page import="ejb.SingletonBeanLog"%>
<%@page import="java.util.Arrays"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="ejb.StaticsBean"%>
<%
    Object user = session.getAttribute("nickName");
    if (user == null) {
        user = "Invitado";
    }
    StaticsBean sb = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/StaticsBean!ejb.StaticsBean");
    sb.setUser(user.toString());
    sb.addPage("dashBoard.jsp");
    SingletonBeanLog lb = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/SingletonBeanLog!ejb.SingletonBeanLog");
    lb.setUser(user.toString());
    lb.add(Arrays.asList("dashBoard.jsp", user.toString()), "pageDashBoar");
%>
<div class="t1" data-toggle="collapse" href="#staticsContent" role="button" aria-expanded="false" aria-controls="staticsContent"><i class="fa fa-chevron-down"></i> Estadisticas.</div>
<div id="staticsContent" class="collapse show">
    <div class="row input-group form-group">  
        <div class="col-sm-12 col-md-12 col-lg-4">
            <h2>Total Usuarios</h2>
            <%=sb.getTotalUsers()%>
            <table class="table">
                <tr>
                    <th>Users</th>
                </tr>
                <%
                for (String useri : sb.getArrayUsers()) {%>
                <tr><td> <%= useri%> </td></tr>
                <%}%>

            </table>    
        </div>
        <div class="col-sm-12 col-md-12 col-lg-4">
            <h2>Pag. Visitadas</h2>
            <table class="table">
                <tr>
                    <th>Pag.</th>
                    <th>Visitas</th>
                </tr>
                <%
                for (Map.Entry<String, Integer> entry : sb.getPageCount().entrySet()) {%>
                <tr> <td><%=entry.getKey()%></td> <td><%=entry.getValue()%></td></tr>
                <%}%> 
            </table>
        </div>
        <div class="col-sm-12 col-md-12 col-lg-4">
            <h2>Componentes</h2>
            <table class="table">
                <tr>
                    <th>Componente</th>
                    <th>Visitas</th>
                </tr>
                <%
                for (Map.Entry<String, Integer> entry : sb.getComponentCount().entrySet()) {%>
                <tr> <td><%=entry.getKey()%></td> <td><%=entry.getValue()%></td></tr>
                <%}%> 
            </table>
        </div>
    </div>
</div>