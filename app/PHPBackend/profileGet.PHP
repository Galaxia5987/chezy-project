<?php
require "conn.php";
$user_name = $_POST["name"];
$mysql_qry = "select * from profile where name like '$user_name';";
$result = mysqli_query($conn ,$mysql_qry);
$row = mysqli_fetch_assoc($result);
if(mysqli_num_rows($result) >= 0){
echo json_encode($row);

}
?>