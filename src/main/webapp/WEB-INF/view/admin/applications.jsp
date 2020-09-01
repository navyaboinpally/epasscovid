<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Admin - Dashboard</title>

<%@include file="../common/head.jsp" %>
</head>
<body id="page-top">
<input type="hidden" id="status" value="${status}">

  <!-- Page Wrapper -->
  <div id="wrapper">
  <%@include file="../common/admin-sidebar.jsp" %>
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
                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Navya</span>
                <img class="img-profile rounded-circle" src="https://source.unsplash.com/QAB-WJcbgJk/60x60">
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
        <div class="container-fluid">

          <!-- Page Heading -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Applications</h1>
          </div>

          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">e-Pass Applications</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>Sr No.</th>
                      <th>FullName</th>
                      <th>Gender</th>
                      <th>Phone</th>
                      <th>Application date</th>
                      <th>From State</th>
                      <th>From Active</th>
                      <th>To State</th>
                      <th>To Active</th>
                      <th>Status</th>
                      <th>Action</th>
                    </tr>
                  </thead>
                  <tbody>
                  </tbody>
                </table>
              </div>
            </div>
          </div>



        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

      <%@include file="../common/footer.jsp" %>
      

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

  <!-- Page level custom scripts -->
<!--   <script src="/js/demo/datatables-demo.js"></script> -->
<script type="text/javascript">
  var x;
  $(document).ready(function () {
    $.get("https://api.covid19india.org/state_district_wise.json", function (data) {
      x = data;
      calltable();
    });
  });

  function calltable() {
    $('#dataTable').DataTable(
            {
              responsive: !0,
              pageLength: 10,
              searchDelay: 500,
              processing: !0,
              serverSide: true,
              ajax: {
                url: "<%=request.getContextPath()%>/admin/applications/datatable",
                type: "POST",
                "data": function (d) {
                  return $.extend({}, d, {
                    "status": $('#status').val()


                  });
                }

              },
              columns: [
                {
                  data: "epassApplicationId"
                }, {
                  data: "fullname"
                }, {
                  data: "gender"
                }, {
                  data: "phonenumber"
                }, {
                  data: "createdOn"
                }, {
                  data: "presentDistrict"
                }, {
                  data: "presentDistrict"
                }, {
                  data: "destinationDistrict"
                }, {
                  data: "destinationDistrict"
                }, {
                  data: "applicationStatus"
                }, {
                  data: "epassApplicationId"
                }],
              columnDefs: [
                {
                  targets: 0,
                  orderable: !1,
                  render: function (a, e, t, n) {
                    return (n.row + n.settings._iDisplayStart + 1);
                  }
                }
                , {
                  targets: 4,
                  orderable: !1,
                  render: function (a, e, t, n) {
                    return moment(a).format('DD/MM/YYYY  hh:mm:ss A');

                  }
                }, {
                  targets: 6,
                  orderable: !1,
                  render: function (a, e, t, n) {
                    y = a;

                    return x.Telangana.districtData[y].active;//moment(a).format('DD/MM/YYYY  hh:mm:ss A');

                  }
                }, {
                  targets: 8,
                  orderable: !1,
                  render: function (a, e, t, n) {
                    y = a;

                    return x.Telangana.districtData[y].active;//moment(a).format('DD/MM/YYYY  hh:mm:ss A');

                  }
                },
                {
                  targets: 9,
                  orderable: !1,
                  render: function (a, e, t, n) {
                    var str;
                    if (a == 0) {
                      str = '<span class="badge badge-warning">Pending</span>';
                    } else if (a == 1) {
                      str = '<span class="badge badge-success">Accepted</span>';
                    } else if (a == 2) {
                      str = '<span class="badge badge-danger">Rejected</span>';
                    }
                    return str;
                    //return moment(a).format('DD/MM/YYYY  hh:mm:ss A');

                  }
                }, {
                  targets: 10,
                  orderable: !1,
                  render: function (a, e, t, n) {
                    var str = '';
                    if (t.applicationStatus == 0) {
                      str = '<a href="/admin/applications/' + a + '/update/1" class="btn btn-success btn-circle btn-sm"><i class="fas fa-check"></i></a><a href="/admin/applications/' + a + '/update/2" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
                    } else if (t.applicationStatus == 1) {
                      str = '<a href="/admin/applications/' + a + '/update/0" class="btn btn-warning btn-circle btn-sm"><i class="fas fa-exclamation-triangle"></i></a><a href="/admin/applications/' + a + '/update/2" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
                    } else if (t.applicationStatus == 2) {
                      str = '<a href="/admin/applications/' + a + '/update/1" class="btn btn-success btn-circle btn-sm"><i class="fas fa-check"></i></a><a href="/admin/applications/' + a + '/update/0" class="btn btn-warning btn-circle btn-sm"><i class="fas fa-exclamation-triangle"></i></a>';
                    }
                    return str;


                  }
                }
              ],
            }
    );
  }

</script>

</html>