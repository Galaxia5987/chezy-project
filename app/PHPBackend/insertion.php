<?php
require('conn.php');
$pass = $_POST["email"];
$pass = $_POST["pass"];
$query = "insert into login 
	values('$email' , '$pass')";
$result = mysqli_query($conn, $query);

?>