<?php
require('conn.php');
$name = $_POST["name"];
$loc = $_POST["loc"];
$link = $_POST["link"];
$des = $_POST["des"]
$query = "insert into profile 
	values('$name' , '$loc', '$link', '$des')";
$result = mysqli_query($conn, $query);

?>