<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Otp Form</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="card" style="width: 25rem; margin-top: 150px; margin-left:  600px;"><br>
    <h3 class="card-title"><center>OTP</center></h3><hr>
    <div class="card body" style="width: 23rem; margin-left: 15px; margin-bottom: 15px; border: 0px; ">
        <form th:action="@{/validateOtp}" method="POST" id="reg" class="form-vertical">
            <div class="form-group">
                <label>OTP:</label>
                <input type="number" name="otpnum" placeholder="Enter OTP" class="form-control" id="otpnum"  style=" width: 22rem; margin-left: 5px;" required>
                <br> <br> <center><input type="submit" value="Submit" class="btn btn-success"></center>
            </div>
            <p th:text="${msg}"></p>
        </form>
        <hr>
    </div>
</div>
</body>
</html>
