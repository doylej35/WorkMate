<?php
/* Attempt MySQL server connection. Assuming you are running MySQL
server with default setting (user 'root' with no password) */
$link = mysqli_connect("localhost", "***", "***", "workmatedb");

// Check connection
if($link === false){
    die("ERROR: Could not connect. " . mysqli_connect_error());
}


// Escape user inputs for security
$client_fname = mysqli_real_escape_string($link, $_REQUEST['client_fname']);
$client_lname = mysqli_real_escape_string($link, $_REQUEST['client_lname']);
$client_phone = mysqli_real_escape_string($link, $_REQUEST['client_phone']);
$client_email = mysqli_real_escape_string($link, $_REQUEST['client_email']);
$client_addr = mysqli_real_escape_string($link, $_REQUEST['client_addr']);
$client_latitude = mysqli_real_escape_string($link, $_REQUEST['client_latitude']);
$client_longitude = mysqli_real_escape_string($link, $_REQUEST['client_longitude']);



// Attempt insert query execution
$sql = "INSERT INTO client_table (client_fname, client_lname, client_phone, client_email client_addr, client_latitude, client_longitude)
                    VALUES ('$client_fname', '$client_lname', '$client_phone', '$client_email', '$cli$
if(mysqli_query($link, $sql)){
    echo "Records added successfully.";
} else{
    echo "ERROR: Could not able to execute $sql. " . mysqli_error($link);
}

// Close connection
mysqli_close($link);
?>
