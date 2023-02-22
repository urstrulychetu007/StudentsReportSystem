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

 <div class="user-profile" style="display: flex; justify-content: space-between;">
    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcxYm4FfGa3yNBgy8Qf76dfzNAjNe6TD8BdQ&usqp=CAU"
              width="80px" height="80px" alt="">
          <h3>${name}</h3>
          
 </div>

  <table class="table table-bordered" style="margin-top: 2%;">
    <thead>
      <tr>
        <th>Marks in English</th>
        <th>Marks in Maths</th>
        <th>Marks in Science</th>
        <th>Semester</th>
        <th>Total</th>
      </tr>
    </thead>
    <tbody class="table-group-divider">
      <c:forEach var="data" items="${array}">
        <tr>
    
          <td>${data.getInt("marks_in_english")}</td>
          <td>${data.getInt("marks_in_maths")}</td>
          <td>${data.getInt("marks_in_science")}</td>
          <td>${data.getInt("semester")}</td>
          <td>${data.getInt("total")}</td>
        </tr>

      </c:forEach>
    </tbody>


  </table>
  <pre></pre>

<pre></pre>
  <div class="line-1"></div>
<pre></pre>
<pre></pre>
  <button id="opener" class="btn btn-lg btn-primary" data-bs-toggle="modal" data-bs-target="#dialog1" ${heading}>Add Marks</button>



  <!-- <c:forEach var="data" items="${array}">
        <li>${skill.getString("student_name")}</li>
    </c:forEach> -->

  <!-- Button trigger modal -->

  <div id="dialog1" class="modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div>
          <h3 class="modal-title fs-5" style="background-color: #282828;color: white;" id="exampleModalLabel">
            <center>Add Marks</center>
          </h3>

        </div>
        <div class="modal-body" style="background-color: #1e1e1e;color: white;">
          <form:form action="addstudentdetails" method="post" modelAttribute="StudentsData">
            <div class="mb-3">
                <form:label path="id" hidden="true">${id}</form:label>
              <form:input path="id" value="${id}" hidden="true" /><br />
            </div> 
             <div class="mb-3">
                <form:label path="usn" hidden="true">${usn}</form:label>
              <form:input path="usn" value="${usn}" hidden="true" /><br />
            </div>
           
            <div class="mb-3">
              <form:label path="marks_in_english" class="col-form-label">Marks in English</form:label>
              <form:input path="marks_in_english" class="form-control" /><br />
            </div>
            <div class="mb-3">
                <form:label path="marks_in_maths" class="col-form-label">Marks in Maths</form:label>
                <form:input path="marks_in_maths" class="form-control" /><br />
              </div>
              <div class="mb-3">
                <form:label path="marks_in_science" class="col-form-label">Marks in Science</form:label>
                <form:input path="marks_in_science" class="form-control" /><br />
              </div>
              <div class="mb-3">
                <form:label path="semester" >Semester</form:label>
                <form:label path="semester">${semester}</form:label>
              <form:input path="semester" value="${semester}" hidden="true" /><br />
            </div>
            <form:button id="closer" class="btn btn-danger" type="button">Close</form:button>

            <form:button type="submit" class="btn btn-primary">Save</form:button>
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
  </script>
</body>

</html>