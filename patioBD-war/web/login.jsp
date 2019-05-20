<%@page import="ejb.SingletonBeanLog"%>
<%@page import="java.util.Arrays"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="ejb.StaticsBean"%>
<%
    StaticsBean sb = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/StaticsBean!ejb.StaticsBean");
    sb.setUser("Invitado");
    sb.addPage("login.jsp");
    SingletonBeanLog lb = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/SingletonBeanLog!ejb.SingletonBeanLog");
    lb.setUser("Invitado");
    lb.add(Arrays.asList("login.jsp", "Invitado"),"pageLogin");
%>
<%@include file="templateBase/header.jsp" %>

<div class="container">
    <div class="card card-login mx-auto text-center">
        <div class="card-header mx-auto">
            <span> <img src="assets/img/logo.png" class="w-75" alt="Logo"> </span><br/>
            <span class="logo_title mt-5"> Login Dashboard </span>
        </div>
        <div class="card-body">
            <form action="Home" method="POST">
                <div class="input-group form-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-grin-tongue-squint"></i></span>
                    </div>
                    <input type="text" name="nickname" class="form-control" placeholder="nickName">
                    <input type="hidden" name="param" value="login">
                    <input type="hidden" name="command" value="LoginCommand">
                </div>

                <div class="input-group form-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fab fa-keycdn"></i></span>
                    </div>
                    <input type="password" name="password" class="form-control" placeholder="Password">
                </div>

                <div class="form-group">
                    <input type="submit" name="btn" value="Login" class="btn btn-outline-danger float-right login_btn">
                </div>

            </form>
        </div>
    </div>
</div>
<%@include file="templateBase/footer.jsp" %>