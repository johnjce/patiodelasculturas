<%@include file="templateBase/header.jsp" %>
<div class="container">
    <c:if test="${sessionScope.privilegeLevel == 0 || sessionScope.privilegeLevel == 1}">
        <div class="row rows">  
            <div class="col-12 col-md-4 col col-admin">
                <%@include file="dashBoardUsers.jsp" %>    
                <%@include file="dashBoardEvents.jsp" %>   
            </div>
            <div class="col-12 col-md-8 col col-admin">
                <%@include file="dashBoardPrincipal.jsp" %>  
            </div>
        </div>
        <%@include file="dashBoardStatics.jsp" %>   
    </c:if>   
</div>
<%@include file="templateBase/footer.jsp" %>