<%@page import="java.util.*"%>
<%@page import="java.lang.*"%>
<%@page import="java.io.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Students Data</title>
  <link rel="stylesheet" href="http://localhost:8080/css/bootstrap.css" />
  <style>
    @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap');

    * {
      font-family: 'Poppins', sans-serif;
    }

    .line-1 {
      height: 2px;
      background: dodgerblue;
    }

    /* Style The Dropdown Button */
    .dropbtn {
      background-color: dodgerblue;
      color: white;
      padding: 16px;
      font-size: 16px;
      border: none;
      cursor: pointer;

    }

    /* The container <div> - needed to position the dropdown content */
    .dropdown {
      position: relative;
      display: inline-block;
    }

    /* Dropdown Content (Hidden by Default) */
    .dropdown-content {
      display: none;
      position: absolute;
      background-color: #1e1e1e;
      min-width: 160px;
      color: white;
      box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
      z-index: 1;
    }

    /* Links inside the dropdown */
    .dropdown-content a {
      color: white;
      padding: 12px 16px;
      text-decoration: none;
      display: block;
    }

    /* Change color of dropdown links on hover */
    .dropdown-content a:hover {
      background-color: #a1a1a1
    }

    /* Show the dropdown menu on hover */
    .dropdown:hover .dropdown-content {
      display: block;
    }

    /* Change the background color of the dropdown button when the dropdown content is shown */
    .dropdown:hover .dropbtn {
      background-color: dodgerblue;
    }
  </style>
</head>

<body class="p-3 mb-2 bg-dark text-white">

  <div class="btn-group">
    <div class="dropdown">
      <button class="dropbtn btn btn-lg btn-primary">${heading}</button>
      <div class="dropdown-content">
        <a href="/sem/1">Semester 1</a>
        <a href="/">Semester 2</a>
      </div>
    </div>
  </div>
<pre></pre>
  <h3>Semester Wise Data</h3>
  <table class="table table-bordered" style="margin-top: 2%;">
    <thead>
      <tr>
        <th>Profile</th>
        <th>Student ID</th>
        <th>Name</th>
        <th>Marks in English</th>
        <th>Marks in Maths</th>
        <th>Marks in Science</th>
        <th>Total</th>
      </tr>
    </thead>
    <tbody class="table-group-divider">
      <c:forEach var="data" items="${array}">
        <tr>
          <td><img
              src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcxYm4FfGa3yNBgy8Qf76dfzNAjNe6TD8BdQ&usqp=CAU"
              width="40px" height="40px" alt=""></td>
          <td>${data.getInt("usn")}</td>
          <td>${data.getString("student_name")}</td>
          <td>${data.getInt("marks_in_english")}</td>
          <td>${data.getInt("marks_in_maths")}</td>
          <td>${data.getInt("marks_in_science")}</td>
          <td>${data.getInt("total")}</td>


        </tr>

      </c:forEach>
    </tbody>


  </table>
  <pre></pre>
  <h4>Class average of ${heading} is ${average}</h4>
<pre></pre>
  <div class="line-1"></div>

  <pre></pre>
  <h3>Leaderboard of All Semesters</h3>
  <pre></pre>
  <table class="table table-bordered" style="margin-top: 2%;">
    <thead>
      <tr>
        <th>Profile</th>
        <th>Student ID</th>
        <th>Name</th>
        <th>Total</th>
        <th>Average of All Semesters</th>
      </tr>
    </thead>
    <tbody class="table-group-divider">
      <c:forEach var="data3" items="${array3}">
        <tr>
          <td><img
              src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcxYm4FfGa3yNBgy8Qf76dfzNAjNe6TD8BdQ&usqp=CAU"
              width="40px" height="40px" alt=""></td>
          <td>${data3.getInt("usn")}</td>
          <td>${data3.getString("student_name")}</td>
          <td>${data3.getInt("total")}</td>
          <td>${data3.getInt("average")}</td>


        </tr>

      </c:forEach>
    </tbody>


  </table>
<pre></pre>
<h5>Congratulations Top 2 Students <em>${array3.get(0).getString("student_name")}</em> and <em>${array3.get(1).getString("student_name")} </em>  </h5>
<pre></pre>
  <div class="line-1"></div>


  <pre></pre>
  <h3>All Students</h3>
  <pre></pre>
  <button id="opener" class="btn btn-lg btn-primary" data-bs-toggle="modal" data-bs-target="#dialog1">Add Students</button>

  <table class="table table-bordered" style="margin-top: 2%;">
    <thead>
      <tr>
        <th>Profile</th>
        <th>Student ID</th>
        <th>Name</th>

      </tr>
    </thead>
    <tbody class="table-group-divider">
      <c:forEach var="data1" items="${array1}">
        <tr>
          <td><img
              src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcxYm4FfGa3yNBgy8Qf76dfzNAjNe6TD8BdQ&usqp=CAU"
              width="40px" height="40px" alt=""></td>
          <td>${data1.getString("id")}</td>
          <td>${data1.getString("student_name")}</td>
          <td><button class="btn btn-primary" onclick='viewpage(${data1.getString("id")} ," ${data1.getString("student_name")}" ) ' > View</button></td>

        </tr>

      </c:forEach>
    </tbody>

  </table>


  <!-- <c:forEach var="data" items="${array}">
        <li>${skill.getString("student_name")}</li>
    </c:forEach> -->

  <!-- Button trigger modal -->

  <div id="dialog1" class="modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div>
          <h3 class="modal-title fs-5" style="background-color: #282828;color: white;" id="exampleModalLabel">
            <center>Add User</center>
          </h3>

        </div>
        <div class="modal-body" style="background-color: #1e1e1e;color: white;">
          <form:form action="addstudent" method="post" modelAttribute="Students">
            <div class="mb-3">
              <form:label path="id" class="col-form-label">Student ID</form:label><br />
              <form:label path="id">${data_count1+1}</form:label>
              <form:input path="id" value="${data_count+1}" hidden="true" /><br />
            </div>
            <div class="mb-3">
              <form:label path="student_name" class="col-form-label">Student Name</form:label>
              <form:input path="student_name" class="form-control" /><br />

            </div>

            <form:button id="closer" class="btn btn-danger" type="button">Close</form:button>

            <form:button type="submit" class="btn btn-primary">Register</form:button>
          </form:form>
        </div>

      </div>
    </div>
  </div>


  <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>

  <script src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>

  <script src="http://localhost:8080/js/bootstrap.js"></script>
  <script src="http://localhost:8080/js/bootstrap.bundle.js"></script>

  <!-- <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css" /> -->
  <script>
    $(function () {
      $("#dialog1").dialog({
        autoOpen: false,
        modal: true
      });

      $("#opener").click(function () {
        $("#dialog1").dialog('open');
        document.getElementsByClassName("ui-button-text")[0].innerHTML = ''
        // (document.getElementsByClassName("ui-button-text")[0].innerHTML = '')


      });

      $("#closer").click(function () {
        $("#dialog1").dialog('close');
      });
    });


    function viewpage(pg,name)
    {
      window.location = "/viewdetails/"+pg+'/'+name;
    }

  </script>
</body>

</html>