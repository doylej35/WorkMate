
<?php

$link = mysqli_connect("localhost", "***", "***", "workmatedb");

// Check connection
if($link === false){
    die("ERROR: Could not connect. " . mysqli_connect_error());
}

// Escape user inputs for security
$client_email = mysqli_real_escape_string($link, $_REQUEST['client_email']);
$supplier_email = mysqli_real_escape_string($link, $_REQUEST['supplier_email']);
$rating_number = (int)mysqli_real_escape_string($link, $_REQUEST['rating_number']);
$rating_comment = mysqli_real_escape_string($link, $_REQUEST['rating_comment']);

// Attempt insert query execution
$sql = "INSERT INTO rating_table (client_email, supplier_email, rating_number, rating_comment)
                    VALUES ('$client_email', '$supplier_email', '$rating_number', '$rating_comment')";
if(mysqli_query($link, $sql)){
    echo "Records added successfully.";
} else{
    echo "ERROR: Could not able to execute $sql. " . mysqli_error($link);
}

// Close connection
mysqli_close($link);
?>
