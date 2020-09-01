<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>user - Dashboard</title>

<%@include file="../common/head.jsp" %>
<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css" rel="stylesheet" />
</head>
<body id="page-top">
<input type="hidden" id="status" value="${status}">

  <!-- Page Wrapper -->
  <div id="wrapper">
  <%@include file="../common/user-sidebar.jsp" %>
      <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

          <!-- Sidebar Toggle (Topbar) -->
          <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
          </button>

          <!-- Topbar Search -->
          <!-- <form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
            <div class="input-group">
              <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
              <div class="input-group-append">
                <button class="btn btn-primary" type="button">
                  <i class="fas fa-search fa-sm"></i>
                </button>
              </div>
            </div>
          </form> -->

          <!-- Topbar Navbar -->
          <ul class="navbar-nav ml-auto">

            <!-- Nav Item - Search Dropdown (Visible Only XS) -->
            <li class="nav-item dropdown no-arrow d-sm-none">
              <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-search fa-fw"></i>
              </a>
              <!-- Dropdown - Messages -->
              <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
                <form class="form-inline mr-auto w-100 navbar-search">
                  <div class="input-group">
                    <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                      <button class="btn btn-primary" type="button">
                        <i class="fas fa-search fa-sm"></i>
                      </button>
                    </div>
                  </div>
                </form>
              </div>
            </li>


            <div class="topbar-divider d-none d-sm-block"></div>

            <!-- Nav Item - User Information -->
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${name}</span>
<!--                 <img class="img-profile rounded-circle" src="https://source.unsplash.com/QAB-WJcbgJk/60x60"> -->
              </a>
              <!-- Dropdown - User Information -->
              <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                <!-- <a class="dropdown-item" href="#">
                  <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                  Profile
                </a>
                <a class="dropdown-item" href="#">
                  <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                  Settings
                </a>
                <a class="dropdown-item" href="#">
                  <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                  Activity Log
                </a> -->
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="/logout">
                  <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                  Logout
                </a>
              </div>
            </li>

          </ul>

        </nav>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid ">

          <!-- Page Heading -->
          
	          <div class="d-sm-flex align-items-center justify-content-between mb-4">
	            <h1 class="h3 mb-0 text-gray-800" style="padding-left:200px;">Application Form</h1>
	          </div>
	          
	         <div class="row">
	          <div class="col-lg-7">
	            <div class="p-5">
					<form class="user" method="post" action="/user/applications/new" enctype="multipart/form-data">
	                 <label>Full Name:</label><br>
	              	 <div class="form-group">
	                 	<input type="text" name="fullname" class="form-control" id="exampleInputName" placeholder="Full Name">
	                </div>
	               <label>Email:</label><br>
	                <div class="form-group">
	                  <input type="email" name="email" class="form-control" id="exampleInputEmail" placeholder="Email Address">
	                </div>
	                <label>Phone Number:</label><br>
	                <div class="form-group">
	                  <input type="number" name="phonenumber" class="form-control" id="exampleInputNumber" placeholder="Phone Number">
	                </div>
	                <label>Gender:</label><br>
	                <div class="form-group">
	                <select class="form-control"  name="gender" style="padding:10px;border-radius:8px">
	                	   <option></option>
	                       <option>Female</option>
	                       <option>Male</option>
	                       <option>Other</option>
	                 </select>
	                </div>
	                <label>Present Address:</label><br>
	                <div class="form-group ">
	                    <input type="text" name="sourceaddress" class="form-control" id="exampleInputPassword" placeholder="Enter Full Address">
	               </div>
	               <label>Present District</label><br>

						<select class="js-example-basic-single form-control form-control-user" style="overflow:hidden"
								name="presentDistrict">
						<c:forEach items="${districts}" var="d">
							<option value="${d.districtName}">${d.districtName}</option>
						
						</c:forEach>
						
					  </select>
					 
					  <label></label><br>
					<label>Destination Address:</label><br>
	                <div class="form-group">
	                    <input type="text" name="destinationaddress" class="form-control" id="exampleInputPassword" placeholder="Enter Full Address">
	               </div>
	               <label>Destination District</label><br>
						<select class="js-example-basic-single form-control" style="overflow:hidden"
								name="destinationDistrict">
						<c:forEach items="${districts}" var="d">
							<option value="${d.districtName}">${d.districtName}</option>
						
						</c:forEach>
						
					  </select>
					  <label></label><br>
					 <label>Identity Proof Type:</label><br>
	                     <select class="form-control" placeholder="select" name="idproofselect" style="padding:10px;border-radius:8px">
	                       <option></option>
							 <option value="Pan Card">Pan Card</option>
							 <option value="Driving License">Driving License</option>
							 <option value="Aadhaar Card">Aadhaar Card</option>
							 <option value="Voter Id">Voter Id</option>
	                     </select>
	                 <label></label><br>
	                     
	                   <label>Id Number:</label><br>
	                   <input type="text" name="idproofnumber" class="form-control" id="exampleInputPassword" placeholder="Enter Id Number"><br>
	                    <label>Applicant Id proof:</label><br>
	                     <i style="color:blue">Send clear scanned picture of your photo </i><br>
	                     <input type="file" class="file-input" name="picid" ><br>
	                     <i style="color:blue">scanned copy of your Id proof</i><br>
	                     <input type="file" class="file-input" name="idpic" ><br><br>
	                     <strong class="last">I hereby declare that above details are true and correct to the best of my knowledge. Also, I undertake that if anything is found untrue I shall be liable for punishment/penalty as per law.</strong><br>
                        <br>
                         <input type="checkbox" class="checkbox-inline" value="" required><span>I Agree</span> 
	                      <br><br>
						 <center> <input type="submit" value="Submit" class="btn btn-success" ></center>
					
	                  
	           	</div>
	                 
	                
	           </form>
	       </div>
	      </div>
	   </div>
	          
	
	
	        </div>
	        
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

    <!-- -   <%@include file="../common/footer.jsp" %>--->
      

    </div>
    <!-- End of Content Wrapper -->
     </div>
  <!-- End of Page Wrapper -->

  <!-- Bootstrap core JavaScript-->
  <script src="/vendor/jquery/jquery.min.js"></script>
  <script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="/js/sb-admin-2.min.js"></script>
  <script src="/js/jquery.spring-friendly.js"></script>

  <!-- Page level plugins -->
  <script src="/vendor/datatables/jquery.dataTables.min.js"></script>
  <script src="/vendor/datatables/dataTables.bootstrap4.min.js"></script>
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.27.0/moment.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>

  <!-- Page level custom scripts -->
<!--   <script src="/js/demo/datatables-demo.js"></script> -->
<script type="text/javascript">
$(document).ready(function() {
    $('.js-example-basic-single').select2();
});

</script>
</html>