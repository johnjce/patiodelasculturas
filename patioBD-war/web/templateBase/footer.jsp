
<!-- start footer Area -->
<footer class="footer-area section-gap">
    <div class="container">
        <div class="row d-flex flex-column justify-content-center">
            <ul class="footer-menu">
                <li><a href="./#home">Inicio</a></li>
                <li><a href="./#project">Proyectos</a></li>
                <li><a href="./#about">Nosotros</a></li>
                <li><a href="./#donate">Donar</a></li>
                <c:if test="${sessionScope.nickName == null}">
                    <li><a href="./Home?command=LoginCommand">Login</a></li>
                </c:if>
            </ul>
            <div class="footer-social">
                <a href="#"><i class="fa fa-facebook"></i></a>
                <a href="#"><i class="fa fa-twitter"></i></a>
                <a href="#"><i class="fa fa-dribbble"></i></a>
                <a href="#"><i class="fa fa-behance"></i></a>
            </div>
            <p class="footer-text m-0">
                Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This web is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by ..::Jhonts::..
            </p>
        </div>
    </div>
</footer>
<!-- End footer Area -->

<script src="assets/js/vendor/jquery-2.2.4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="assets/js/vendor/bootstrap.min.js"></script>
<script src="assets/js/jquery.ajaxchimp.min.js"></script>
<script src="assets/js/jquery.nice-select.min.js"></script>
<script src="assets/js/jquery.sticky.js"></script>
<script src="assets/js/parallax.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="assets/js/jquery.magnific-popup.min.js"></script>
<script src="assets/js/main.js"></script>
</body>
</html>
