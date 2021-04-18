<?php
/* Attempt MySQL server connection. Assuming you are running MySQL
server with default setting (user 'root' with no password) */
$link = mysqli_connect("localhost", "***", "***", "workmatedb");

// Check connection
if($link === false){
    die("ERROR: Could not connect. " . mysqli_connect_error());
}

// Escape user inputs for security
$supplier_fname = mysqli_real_escape_string($link, $_REQUEST['supplier_fname']);
$supplier_lname = mysqli_real_escape_string($link, $_REQUEST['supplier_lname']);
$supplier_phone = mysqli_real_escape_string($link, $_REQUEST['supplier_phone']);
$supplier_email = mysqli_real_escape_string($link, $_REQUEST['supplier_email']);
$supplier_addr = mysqli_real_escape_string($link, $_REQUEST['supplier_addr']);
$supplier_service = mysqli_real_escape_string($link, $_REQUEST['supplier_service']);
$supplier_rating = (int)mysqli_real_escape_string($link, $_REQUEST['supplier_rating']);
$supplier_latitude = (double)mysqli_real_escape_string($link, $_REQUEST['supplier_latitude']);
$supplier_longitude = (double)mysqli_real_escape_string($link, $_REQUEST['supplier_longitude']);

// Attempt insert query execution
$sql = "INSERT INTO supplier_table (supplier_fname, supplier_lname, supplier_phone,  supplier_email, supplier_addr, supplier_service, supplier_rating, supplier_latitude, supplier_longitude)
                    VALUES ('$supplier_fname', '$supplier_lname', '$supplier_email', '$supplier_phone', '$supplier_addr', '$supplier_service', '$supplier_rating', '$supplier_latitude', '$supplier_longitude')";
if(mysqli_query($link, $sql)){
    echo "Records added successfully.";
} else{
    echo "ERROR: Could not able to execute $sql. " . mysqli_error($link);
}

// Close connection
mysqli_close($link);
?>
