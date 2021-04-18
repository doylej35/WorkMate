<?php

$link = mysqli_connect("localhost", "***", "***", "workmatedb");

// Check connection
if($link === false){
    die("ERROR: Could not connect. " . mysqli_connect_error());
}

// Escape user inputs for security
$client_email = mysqli_real_escape_string($link, $_REQUEST['client_email']);
$supplier_email = mysqli_real_escape_string($link, $_REQUEST['supplier_email']);
$supplier_service = mysqli_real_escape_string($link, $_REQUEST['supplier_service']);
$order_date = mysqli_real_escape_string($link, $_REQUEST['order_date']);
$order_hours = (int)mysqli_real_escape_string($link, $_REQUEST['order_hours']);
$order_location = mysqli_real_escape_string($link, $_REQUEST['order_location']);
$order_cost = (double)mysqli_real_escape_string($link, $_REQUEST['order_cost']);

// Attempt insert query execution
$sql = "INSERT INTO order_table (client_email, supplier_email, supplier_service, order_date, order_hours, order_location, order_cost)
                    VALUES ('$client_email', '$supplier_email', '$supplier_service', '$order_date', '$order_hours', '$order_location', '$order_cost')";
if(mysqli_query($link, $sql)){
    echo "Records added successfully.";
} else{
    echo "ERROR: Could not able to execute $sql. " . mysqli_error($link);
}

// Close connection
mysqli_close($link);
?>
