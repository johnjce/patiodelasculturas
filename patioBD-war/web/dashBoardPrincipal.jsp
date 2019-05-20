<div class="t1" data-toggle="collapse" href="#homeModule" role="button" aria-expanded="false" aria-controls="homeModule"><i class="fas fa-chevron-down"></i> Administrar página príncipal.</div>
<div id="homeModule" class="collapse show">
    <div class="t2" data-toggle="collapse" href="#homeModuleHome" role="button" aria-expanded="false" aria-controls="homeModuleHome"><i class="fas fa-chevron-down"></i> Home</div>
    <div id="homeModuleHome" class="collapse show">
        <form action="./Home?command=principalCommand" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="submit" name="btn" value="Guardar" class="btn btn-outline-danger float-right login_btn">
            </div>
            <div class="input-group form-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><i class="fa fa-keyboard-o"></i></span>
                </div>
                <input type="hidden" name="form" value="cuHome"/>
                <input type="text" name="leyenda" placeholder="Leyenda"/>
            </div>
            <div class="input-group form-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><i class="fa fa-picture-o"></i></span>
                </div>
                <input name="media" type="file" accept="image/*" />
            </div>
        </form>
    </div>
    <div class="collapsed t2" data-toggle="collapse" href="#homeModuleProjects" role="button" aria-expanded="false" aria-controls="homeModuleProjects"><i class="fas fa-chevron-down"></i> Proyectos</div>
    <div id="homeModuleProjects" class="collapse">
        <form action="./Home?command=principalCommand" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="submit" name="btn" value="Guardar" class="btn btn-outline-danger float-right login_btn">
            </div>

            <div class="input-group form-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><i class="fa fa-keyboard-o"></i></span>
                </div>
                <input type="hidden" name="form" value="cuHome"/>
                <input type="text" name="titulo" placeholder="Titulo"/>
            </div>
            <div class="input-group form-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><i class="fa fa-keyboard-o"></i></span>
                </div>
                <input type="text" name="descripcion" placeholder="Descripción"/>
            </div>
            <div class="input-group form-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><i class="fa fa-picture-o"></i></span>
                </div>
                Bailes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="media" type="file" accept="image/*" />
            </div>
            <div class="input-group form-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><i class="fa fa-picture-o"></i></span>
                </div>
                Talleres&nbsp;&nbsp;&nbsp;<input name="media" type="file" accept="image/*" />
            </div>
            <div class="input-group form-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><i class="fa fa-picture-o"></i></span>
                </div>
                Eventos&nbsp;&nbsp;&nbsp;<input name="media" type="file" accept="image/*" />
            </div>
        </form>
    </div>
    <div class="collapsed t2" data-toggle="collapse" href="#homeModuleProposal" role="button" aria-expanded="false" aria-controls="homeModuleProposal"><i class="fas fa-chevron-down"></i> Propuesta</div>
    <div id="homeModuleProposal" class="collapse">
        <form action="./Home?command=principalCommand" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="submit" name="btn" value="Guardar" class="btn btn-outline-danger float-right login_btn">
            </div>

            <div class="input-group form-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><i class="fa fa-keyboard-o"></i></span>
                </div>
                <input type="hidden" name="form" value="cuHome"/>
                <input type="text" name="titulo" placeholder="Titulo"/>
            </div>
            <div class="input-group form-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><i class="fa fa-keyboard-o"></i></span>
                </div>
                <input type="text" name="descripcion" placeholder="Descripción"/>
            </div>
        </form>
    </div>
    <div class="collapsed t2" 
         data-toggle="collapse" 
         href="#homeModuleTeam" 
         role="button" 
         aria-expanded="false" 
         aria-controls="homeModuleTeam">
        <i class="fas fa-chevron-down"></i> Detras del patio
    </div>
    <div id="homeModuleTeam" class="collapse">
        <form action="./Home?command=principalCommand" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="submit" name="btn" value="Guardar" class="btn btn-outline-danger float-right login_btn">
            </div>

            <div class="input-group form-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><i class="fa fa-keyboard-o"></i></span>
                </div>
                <input type="hidden" name="form" value="cuHome"/>
                <input type="text" name="titulo" placeholder="Titulo"/>
            </div>
            <div class="input-group form-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><i class="fa fa-keyboard-o"></i></span>
                </div>
                <input type="text" name="descripcion" placeholder="Descripción"/>
            </div>
            <div class="input-group form-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><i class="fa fa-chevron-down"></i></span>
                </div>
                <select name="D3[]" size="1" multiple>
                    <option selected  value="http://www.wcostasol.es/vbonline/">Crear Taller</option>
                    <option value="http://www.wcostasol.es/guiller/">Crear Evento</option>
                    <option value="http://www.wcostasol.es/comsur/">Crear Baile</option>
                </select>
            </div>
        </form>
    </div>
</div>