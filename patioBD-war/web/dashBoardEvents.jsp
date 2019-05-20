
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css"/>
<div class="t1"data-toggle="collapse" href="#eventsModule" role="button" aria-expanded="false" aria-controls="eventsModule"><i class="fas fa-chevron-down"></i> Administrar Eventos, Bailes, Talleres.</div>
<div id="eventsModule" class="collapse show">
    <form action="./Home?command=EventsCommand" method="post" enctype="multipart/form-data" name="editForm" id="eventForm">
        <div class="input-group form-group">
            <div class="input-group-prepend">
                <span class="input-group-text"><i class="fa fa-chevron-down"></i></span>
            </div>
            <select name="tipo" size="1">
                <c:if test="${evento.getType() != null}">
                    <option selected value="${evento.getType()}">${evento.getType()}</option>
                </c:if>
                <option value="talleres">Crear Taller</option>
                <option value="eventos">Crear Evento</option>
                <option value="bailes">Crear Baile</option>
            </select>
        </div>

        <div class="input-group form-group">
            <div class="input-group-prepend">
                <span class="input-group-text"><i class="fa fa-keyboard-o"></i></span>
            </div>
            <input type="text" name="titulo" placeholder="Titulo" value="${evento.getTitle()}" required/>
            <c:if test="${evento.getId() != null}">
                <input type="hidden" name="act" value="updateEvent"/>
                <input type="hidden" name="id" value="${evento.getId()}"/>
            </c:if>
            <c:if test="${evento.getId() == null}">
                <input type="hidden" name="act" value="createEvent"/>
            </c:if>
        </div>

        <div class="input-group form-group">
            <div class="input-group-prepend">
                <span class="input-group-text"><i class="fa fa-keyboard-o"></i></span>
            </div>
            <input type="text" name="descripcion" placeholder="Descripción" value="${evento.getDescription()}" required/>
        </div>

        <div class="input-group form-group">
            <div class="input-group-prepend">
                <span class="input-group-text"><i class="fa fa-map-o"></i></span>
            </div>
            <input type="text" name="lugar" placeholder="Lugar" value="${evento.getPlaceEvent()}" required/>
        </div>

        <div class="input-group form-group">
            <div class="input-group-prepend">
                <span class="input-group-text"><i class="fa fa-clock-o"></i></span>
            </div>
            <fmt:formatDate value="${evento.getEventDate()}" pattern="yyyy-MM-dd" var="eventDate" />
            <input type="date" name="fecha" placeholder="Fecha" value="${eventDate}" pattern="dd-MMM-yyyy" required/>
        </div>

        <div class="input-group form-group">
            <div class="input-group-prepend">
                <span class="input-group-text"><i class="fa fa-picture-o"></i></span>
            </div>
            <input name="cartel" type="file" accept="image/jpg" />
        </div>
        <c:if test="${evento.getCartel() != null}">
            <img src="Home?command=EventsCommand&id=${evento.getId()}">
        </c:if>
        <div class="input-group form-group">
            <input type="submit" name="btn" value="Guardar" class="btn btn-outline-danger float-right login_btn">
        </div>
    </form>
</div>
