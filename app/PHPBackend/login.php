<?php 
require "conn.php";
$user_name = $_POST["email"];
$user_pass = $_POST["pass"];
$mysql_qry = "select * from login where email like '$user_name' and pass like '$user_pass';";
$result = mysqli_query($conn ,$mysql_qry);
if(mysqli_num_rows($result) > 0) {
echo "success";
}
else {
echo "password or user name are incorrect";
}
 
?>