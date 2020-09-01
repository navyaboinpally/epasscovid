<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=.5">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="/css/democss.css">
    </head>
    <body>
        <div class="container-fluid">
            <div class="row row1">
                <div class="col-lg-12 col-sm-12 col-xs-12">
                    <div class="dropdown">
                        <i class="dropdown-toggle"  data-toggle="dropdown">Language
                            <span class="caret"></span></i>
                            <ul class="dropdown-menu">
                                <li><a href="#">English</a></li>
                                <li><a href="#">Telugu</a></li>
                                <li><a href="#">Hindi</a></li>
                            </ul>
					 </div>
                </div>
            </div>
            <div class="row row2">
                <div class="col-lg-6 col-sm-12 col-xs-12">
                    <div class="div1">    
                        <center>
                            <img src="/img/logo3.jpg" style="width:200px;height:200px;"/><br>
                            <b style="color:#ff5e13">E-pass For Traveling Inside State</b><br>
                           <b style="color:#ffffff">Government Of India</b>
                        </center>
                    </div>
                </div>
                <div class="col-lg-6 col-sm-12 col-xs-12">
                    <div class="div2">
                        <button style="background-color:#5db2ff;color:white">application received</button>
                        <button style="background-color:#4caf50;color:white">E-pass Issued</button><br><br>
                        <button style="background-color:#ff7b5d;color:white">Application under process</button>
                        <button style="background-color:#ce0c42;color:white">E-pass Registration</button><br><br><br>
                        <p>This Services is being used by various states to provide citizen centric e-services to the applicants. Currently, this framework is being used by two Telugu states of India to provide movement e-Pass services during COVID-19 pandemic.</p>
                    </div>
                </div>
            </div>
            <div class="row row1">
                <div class="col-lg-12 col-sm-12 col-xs-12">
                    <h1 style="background-color:pink"></h1>
                </div>
            </div>
            <div class="row row3">
		<div class="col-lg-6 col-sm-12 col-xs-12">
                    <div class="carousel slide" id="myimages" data-ride="carousel">
                        <ol class="carousel-indicators">
                                <li class="active" data-target="#myimages" data-slide-to="0"></li>
                                <li data-target="#myimages" data-slide-to="1"></li>
                                <li data-target="#myimages" data-slide-to="2"></li>
                                <li data-target="#myimages" data-slide-to="3"></li>
                        </ol>
                        <div class="carousel-inner">
                                <div class="item active">
                                        <img src="/img/4.jpg" class="img-responsive" style="width:100%;height:330px">
                                </div>
                                <div class="item">
                                        <img src="/img/2.jpg" class="img-responsive" style="width:100%;height:330px">
                                </div>
                                <div class="item">
                                        <img src="/img/3.png" class="img-responsive" style="width:100%;height:330px">
                                </div>
                                <div class="item">
                                        <img src="/img/1.png" class="img-responsive" style="width:100%;height:330px">
                                </div>
                        </div>
                        <a href="#myimages" data-slide="prev" class="left carousel-control"><i class="glyphicon glyphicon-chevron-left" ></i></a>
                        <a href="#myimages" data-slide="next" class="right carousel-control"><i class="glyphicon glyphicon-chevron-right"></i></a>
                     </div> 
                </div>
                <div class="col-lg-6 col-sm-12 col-xs-12">
                    <div class="list bg-white row7">
                        <ul class="nav nav-pills nav-justified course">
                            <li class="active" style="background-color:#f7f7f7"><a href="#msg" data-toggle="pill">Apply For Epass</a></li>
                            <li style="background-color:#f7f7f7"><a href="#msg1" data-toggle="pill">Track Your Application</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="msg">
                               <div class="jumbotron">
                                   <ul class="list">
                                        <li>Fill in all the mandatory details carefully and submit.</li>
                                        <li>Keep the scanned copy of the requisite documents before applying.</li>
                                        <li>Please use an active mobile number to receive verification OTP.</li>
                                        <li>After successful submission of your application, an application reference number will
                                          get generated. Please note it to track the application status.</li>
                                        <li>Keep a soft/hard copy of the e-pass while traveling and show it to the security personnel if asked.</li>
                                    </ul>
                     					<center>
                      					<a href="/login" class="btn btn-success">Apply</a>	
                    					</a></center> 
 
                               </div>
                            </div>
                            <div class="tab-pane" id="msg1">
                                <div class="jumbotron">
                                    <ul class="list1">
                                        <li>Follow this steps to Track Your Application</li>
                                        <li>Login Using Mailid And Password </li>
                                        <li>Check Dashboard To see Your Application Details</li>
                                        <li>Ones Approved You Can Download Epass Id Card </li>
                                        <li>After Downlaod Take Xerox and Carry Along With You For proof</li>
                                    </ul>
                                     
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row lastrow" style="background-color:black">
               <div class="col-lg-12 col-sm-12 col-xs-12">
                   <center>
                    <i class="fa fa-phone  phone" ></i>
                    <span>:</span>
                    <b style="color:white">Helpline Number:</b>
                    <a class="a" href="#">+91-9000059706||9995557777||404-6665544335</a>
                   </center>
               </div>
            </div>  
        </div>
    </body>
</html>
