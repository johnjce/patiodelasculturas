<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Start donate Area -->
<section class="donate-area relative section-gap" id="donate">
    <div class="overlay overlay-bg"></div>
    <div class="container">
        <div class="row d-flex justify-content-center">
            <div class="col-lg-6 contact-left">
                <div class="single-info">
                    <h4>Donaciones Físicas</h4>
                    <p>
                        Si tienes trajes, artículos, u objetos típicos  de alg&uacute;n pa&iacute;s que nos quieras donar para los artistas, te lo agradecerán y lo portarán en sus presentaciones con orgullo para representar tu país.
                    </p>
                </div>
                <div class="single-info">
                    <h4>Donaciones económicas</h4>
                    <p>
                        También nos puedes ayudar realizando una pequeña donación haciendo clic aquí.
                    </p>
                </div>
                <div class="single-info">
                    <h4>Hacerte voluntario</h4>
                    <p>
                        El voluntariado en el patio de las culturas siempre es bien recibido ya que sin voluntarios no seríamos nada, si deseas colaborar en la asociación con tu trabajo, si tienes alguna duda, si quieres participar como artista o grupo de artistas, puedes enviarnos tu consulta aquí.</p>
                </div>
            </div>
            <script>
                function option() {
                    if ($("#type option:selected").val() === "Economica") {
                        $("#amount").prop('disabled', false);
                    } else {
                        $("#amount").prop('disabled', true);
                        $("#amount").val("");
                    }
                }
            </script>
            <div class="col-lg-6 contact-right">
                <form class="booking-form" id="contact" method="get" action="">
                    <div class="row">
                        <div class="col-lg-12 d-flex flex-column">
                            <select name="type" id="type" class="app-select form-control" required onChange="option()">
                                <option data-display="¿Cómo nos quieres ayudar?">¿Cómo nos quieres ayudar?</option>
                                <option value="Fisica">Fisica</option>
                                <option value="Economica" disabled>Economica</option>
                                <option value="Voluntariado">Voluntariado</option>
                                <option value="Contactar">Contactar</option>
                            </select>
                            <input type="hidden" name="ema" value="nada" />
                        </div>
                        <div class="col-lg-6 d-flex flex-column">
                            <input name="fname" placeholder="Tu nombre" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Tu nombre'" class="form-control mt-20" required="" type="text" required>
                        </div>
                        <div class="col-lg-6 d-flex flex-column">
                            <input name="email" placeholder="Tu email" pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{1,63}$" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Tu email'" class="form-control mt-20" required="" type="email">
                        </div>
                        <div class="col-lg-12 d-flex flex-column">
                            <input name="amount" id="amount" placeholder="Cantidad en &euro;(EUR)" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Cantidad en &euro;(EUR)'" class="form-control mt-20" type="text" disabled>

                            <textarea class="form-control mt-20" name="message" placeholder="Mensaje" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Mensaje'" required=""></textarea>
                        </div>

                        <div class="col-lg-12 d-flex justify-content-end send-btn">
                            <button class="submit-btn primary-btn mt-20 text-uppercase ">Adelante<span class="lnr lnr-arrow-right"></span></button>
                        </div>

                        <div class="alert-msg"></div>
                    </div>
                </form>
                <p class="payment-method">
                    No podemos recibir temporalmente:   <img src="assets/img/payment.png" alt=""></br>
                    Si desea contactar puede también hacerlo mediante:</br><b>928 33 44 93 - 678 22 21 68 - contactoweb@elpatiodelasculturas.org.es</b>
                </p>
            </div>
        </div>
    </div>
</section>
<!-- End donate Area -->