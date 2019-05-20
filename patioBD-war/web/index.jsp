<%@page import="ejb.SingletonBeanLog"%>
<%@page import="java.util.Arrays"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="ejb.StaticsBean"%>
<%
    Object user = session.getAttribute("nickName");
    if(user==null)
        user = "Invitado";
    StaticsBean sb = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/StaticsBean!ejb.StaticsBean");
    sb.setUser(user.toString());
    sb.addPage("index.jsp");
    SingletonBeanLog lb = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/SingletonBeanLog!ejb.SingletonBeanLog");
    lb.setUser(user.toString());
    lb.add(Arrays.asList("index.jsp", user.toString()),"pageIndex");
%>
<%@include file="templateBase/header.jsp" %>
<%@include file="templateBase/banner.jsp" %>
<%@include file="templateBase/projects.jsp" %>
<%@include file="templateBase/team.jsp" %>
<%@include file="templateBase/donations.jsp" %>
<%@include file="templateBase/footer.jsp" %>