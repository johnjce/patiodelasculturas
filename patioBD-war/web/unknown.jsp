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
    sb.addPage("unknown.jsp");
    SingletonBeanLog lb = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/SingletonBeanLog!ejb.SingletonBeanLog");
    lb.setUser(user.toString());
    lb.add(Arrays.asList("unknown.jsp", user.toString()),"pageUnknown");
%>
<%@include file="templateBase/header.jsp" %>
<section class="banner-area relative" id="home">
    <div class="overlay overlay-bg"></div>
    <div class="container">
        <div class="row fullscreen align-items-center justify-content-start" style="height: 915px;">
            <div class="banner-content col-lg-9 col-md-12">
                <h1>Error 404. </h1> <br/><p>La p&aacute;gina solicitada no existe, por favor revise si est&aacute; bien escrito e intentelo de nuevo. </p>
            </div>
        </div>
    </div>
</section>
<%@include file="templateBase/footer.jsp" %>