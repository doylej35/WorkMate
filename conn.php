//connects to the database using credentials
<?php
$db_name = "workmateDB";
$php_username = "***";  //username and password on discord private channel
$php_password = "***";
$server_name = "localhost";
$conn = mysqli_connect($server_name, $php_username, $php_password, $db_name);
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
else {
        echo "Connected succesfully";
}
 ?>
