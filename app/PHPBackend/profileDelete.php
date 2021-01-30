<?php 
require "conn.php";
$user_name = $_POST["name"];
$mysql_qry = "delete from profile where name like '$user_name';";
$result = mysqli_query($conn ,$mysql_qry);
 
?>