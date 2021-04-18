<?php

$link = mysqli_connect("localhost", "***", "***", "workmatedb");

// Check connection
if($link === false){
    die("ERROR: Could not connect. " . mysqli_connect_error());
}
$sql = $link->prepare("SELECT client_id, client_fname, client_lname, client_email, client_phone, client_addr FROM client_table;");
$sql->execute();

$sql->bind_result($client_id, $client_fname, $client_lname, $client_email, $client_phone, $client_addr);

$clients = array();

while($sql->fetch()) {
  $temp = array();
  $temp['client_id'] = $client_id;
  $temp['client_fname'] = $client_fname;
  $temp['client_lname'] = $client_lname;
  $temp['client_email'] = $client_email;
  $temp['client_phone'] = $client_phone;
  $temp['client_addr'] = $client_addr;
  array_push($clients, $temp);
}
echo json_encode($clients);



 $link->close();

?>
