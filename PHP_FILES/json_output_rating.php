<?php

$link = mysqli_connect("localhost", "***", "***", "workmatedb");

// Check connection
if($link === false){
    die("ERROR: Could not connect. " . mysqli_connect_error());
}
$sql = $link->prepare("SELECT rating_id, client_email, supplier_email, rating_number, rating_comment FROM rating_table;");
$sql->execute();

$sql->bind_result($rating_id, $client_email, $supplier_email, $rating_number, $rating_comment);

$ratings = array();

while($sql->fetch()) {
  $temp = array();
  $temp['rating_id'] = $rating_id;
  $temp['client_email'] = $client_email;
  $temp['supplier_email'] = $supplier_email;
  $temp['rating_number'] = $rating_number;
  $temp['rating_comment'] = $rating_comment;

  array_push($ratings, $temp);
}
echo json_encode($ratings);



 $link->close();

?>
