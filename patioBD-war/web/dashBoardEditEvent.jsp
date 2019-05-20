
<div class="t1">Administrar Eventos, Bailes, Talleres.</div>
<script lang="text/javascript">
    $('#eventForm').bootstrapValidator({

        message: 'Este valor no es valido',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },

        fields: {
            titulo: {
                validators: {
                    notEmpty: {
                        message: 'El titulo es requerido'
                    }
                }
            },
            descripcion:  {
                validators: {
                    notEmpty: {
                        message: 'La descripción es requerida'
                    }
                }
            },
            lugar:  {
                validators: {
                    notEmpty: {
                        message: 'El lugar requerido'
                    }
                }
            },
            fecha: {
                validators: {
                    notEmpty: {
                        message: 'La fecha es requerida'
                    }
                }
            },
            cartel: {
                validators: {
                    notEmpty: {
                        message: 'El cartel es requerido'
                    }
                }
            }

        }

    });
</script>
<form action="./Home?command=EventsCommand" method="post" enctype="multipart/form-data" name="editForm" id="eventForm">
    <div class="input-group form-group">
        <div class="input-group-prepend">
            <span class="input-group-text"><i class="fa fa-chevron-down"></i></span>
        </div>
        <select name="tipo" size="1">
            <option value="talleres">Crear Taller</option>
            <option selected  value="eventos">Crear Evento</option>
            <option value="bailes">Crear Baile</option>
        </select>
    </div>

    <div class="input-group form-group">
        <div class="input-group-prepend">
            <span class="input-group-text"><i class="fa fa-keyboard-o"></i></span>
        </div>
        <input type="text" name="titulo" placeholder="Titulo" value="${sessionScope.evento.getTitle()}" required/>
        <input type="hidden" name="act" value="updateEvent"/>
        <input type="hidden" name="id" value="${sessionScope.evento.getId()}"/>
    </div>

    <div class="input-group form-group">
        <div class="input-group-prepend">
            <span class="input-group-text"><i class="fa fa-keyboard-o"></i></span>
        </div>
        <input type="text" name="descripcion" placeholder="Descripción" value="${sessionScope.evento.getDescription()}" required/>
    </div>

    <div class="input-group form-group">
        <div class="input-group-prepend">
            <span class="input-group-text"><i class="fa fa-map-o"></i></span>
        </div>
        <input type="text" name="lugar" placeholder="Lugar" value="${sessionScope.evento.getPlaceEvent()}" required/>
    </div>

    <div class="input-group form-group">
        <div class="input-group-prepend">
            <span class="input-group-text"><i class="fa fa-clock-o"></i></span>
        </div>
        <input type="date" name="fecha" placeholder="Fecha" value="${sessionScope.evento.getEventDate()}" pattern="dd-MMM-yyyy" required/>
    </div>

    <div class="input-group form-group">
        <div class="input-group-prepend">
            <span class="input-group-text"><i class="fa fa-picture-o"></i></span>
        </div>
        <input name="media" type="file" accept="image/jpg" required/>
    </div>

    <div class="form-group">
        <input type="submit" name="btn" value="Guardar" class="btn btn-outline-danger float-right login_btn">
    </div>
</form>