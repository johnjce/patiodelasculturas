<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="card col-sm-12 col-md-12 col-lg-12 mt-10" >

    <div class="form-inline">
        <br/><br/><br/>
        <button class="btn btn-outline-primary col-sm-4" id="bailes" onclick="getList('bailes')">Bailes</button>
        <button class="btn btn-outline-primary col-sm-4" id="talleres" onclick="getList('talleres')">Talleres</button>
        <button class="btn btn-outline-primary col-sm-4" id="eventos" onclick="getList('eventos')">Eventos</button>
    </div>

    <h3 class="card-header" id="monthAndYear"></h3>
    <table class="table table-bordered table-responsive-sm table-responsive-md table-responsive-lg" id="calendar">
        <thead>
            <tr>
                <th>Domingo</th>
                <th>Lunes</th>
                <th>Martes</th>
                <th>Miercoles</th>
                <th>Jueves</th>
                <th>Viernes</th>
                <th>Sabado</th>
            </tr>
        </thead>

        <tbody id="calendar-body">

        </tbody>
    </table>

    <div class="form-inline">

        <button class="btn btn-outline-primary col-sm-6" id="previous" onclick="previous()">Anterior</button>

        <button class="btn btn-outline-primary col-sm-6" id="next" onclick="next()">Siguiente</button>
    </div>
    <br/>
    <form class="form-inline">
        <label class="lead mr-2 ml-2" for="month">Ir a: </label>
        <select class="form-control col-sm-4" name="month" id="month" onchange="jump()">
            <option value=0>Enero</option>
            <option value=1>Febrero</option>
            <option value=2>Marzo</option>
            <option value=3>Abril</option>
            <option value=4>Mayo</option>
            <option value=5>Junio</option>
            <option value=6>Julio</option>
            <option value=7>Agosto</option>
            <option value=8>Septiembre</option>
            <option value=9>Octubre</option>
            <option value=10>Noviembre</option>
            <option value=11>Diciembre</option>
        </select>


        <label for="year"> - </label>
        <select class="form-control col-sm-4" name="year" id="year" onchange="jump()">
            <option value=2019>2019</option>
            <option value=2020>2020</option>
            <option value=2021>2021</option>
            <option value=2022>2022</option>
            <option value=2023>2023</option>
            <option value=2024>2024</option>
            <option value=2025>2025</option>
            <option value=2026>2026</option>
            <option value=2027>2027</option>
            <option value=2028>2028</option>
            <option value=2029>2029</option>
            <option value=2030>2030</option>
        </select></form><br/>
</div>
<script>
    let today = new Date();
    let currentMonth = today.getMonth();
    let currentYear = today.getFullYear();
    let selectYear = document.getElementById("year");
    let selectMonth = document.getElementById("month");
    let months = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
    let sortMonths = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
    let monthAndYear = document.getElementById("monthAndYear");

    showCalendar(currentMonth, currentYear);

    function next() {
        currentYear = (currentMonth === 11) ? currentYear + 1 : currentYear;
        currentMonth = (currentMonth + 1) % 12;
        showCalendar(currentMonth, currentYear);
    }

    function previous() {
        currentYear = (currentMonth === 0) ? currentYear - 1 : currentYear;
        currentMonth = (currentMonth === 0) ? 11 : currentMonth - 1;
        showCalendar(currentMonth, currentYear);
    }

    function getList(listName) {
        $.ajax({
            data: {"command": "EventsCommand", "listName": listName, "act": "getList"},
            url: "Home",
            type: "post",
            success: function () {
                location.reload();
            }
        });
    }

    function jump() {
        currentYear = parseInt(selectYear.value);
        currentMonth = parseInt(selectMonth.value);
        showCalendar(currentMonth, currentYear);
    }

    function showCalendar(month, year) {
        let eventDates = new Array(0);
    <%  int i;
        List dateList = (List) session.getAttribute("dateList");
        if (dateList != null) { %>
            eventDates = new Array(${sessionScope.dateList.size()});
        <%  for (i = 0; i < dateList.size(); i++) {%>
                eventDates[<%=i%>] = "<%= dateList.get(i)%>";
        <%  } 
        }%>

        let firstDay = (new Date(year, month)).getDay();
        let daysInMonth = 32 - new Date(year, month, 32).getDate();
        let tbl = document.getElementById("calendar-body");

        // clearing all previous cells
        tbl.innerHTML = "";
        // filing data about month and in the page via DOM.
        monthAndYear.innerHTML = months[month] + " " + year;
        selectYear.value = year;
        selectMonth.value = month;
        // creating all cells
        let date = 1;
        for (let i = 0; i < 6; i++) {
            // creates a table row
            let row = document.createElement("tr");
            //creating individual cells, filing them up with data.
            for (let j = 0; j < 7; j++) {
                if (i === 0 && j < firstDay) {
                    let cell = document.createElement("td");
                    let cellText = document.createTextNode("");
                    cell.appendChild(cellText);
                    row.appendChild(cell);
                } else if (date > daysInMonth) {
                    break;
                } else {
                    let cell = document.createElement("td");
                    let cellText = document.createTextNode(date);
                    if (date === today.getDate() && year === today.getFullYear() && month === today.getMonth()) {
                        cell.classList.add("bg-dark");
                        cell.classList.add("text-white");
                    }

                    if (eventDates.length > 0) {
                        for (let da = 0; da < eventDates.length; da++) {
                            var rMonth = eventDates[da].substring(4, 7);
                            var r2Month = sortMonths[month];
                            var rDate = eventDates[da].substring(8, 10);
                            var rYear = eventDates[da].substring(24, 28);
                            if (date == rDate && r2Month == rMonth && year == rYear) {
                                cell.classList.add("bg-info");
                                cell.classList.add("text-white");
                            }
                        }
                    }
                    cell.appendChild(cellText);
                    row.appendChild(cell);
                    date++;
                }


            }

            tbl.appendChild(row); // appending each row into calendar body.
        }

    }
</script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"
        integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ"
crossorigin="anonymous"></script>