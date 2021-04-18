<?php

$link = mysqli_connect("localhost", "***", "***", "workmatedb");

// Check connection
if($link === false){
    die("ERROR: Could not connect. " . mysqli_connect_error());
}
$sql = $link->prepare("SELECT supplier_id, supplier_fname, supplier_lname, supplier_email, supplier_phone, supplier_addr, supplier_service, supplier_rating FROM supplier_table;");
$sql->execute();

$sql->bind_result($supplier_id, $supplier_fname, $supplier_lname, $supplier_email, $supplier_phone, $supplier_addr, $supplier_service, $supplier_rating);

$suppliers = array();

while($sql->fetch()) {
  $temp = array();
  $temp['supplier_id'] = $supplier_id;
  $temp['supplier_fname'] = $supplier_fname;
  $temp['supplier_lname'] = $supplier_lname;
  $temp['supplier_email'] = $supplier_email;
  $temp['supplier_phone'] = $supplier_phone;
  $temp['supplier_addr'] = $supplier_addr;
  $temp['supplier_service'] = $supplier_service;
  $temp['supplier_rating'] = $supplier_rating;
  array_push($suppliers, $temp);
}
echo json_encode($suppliers);



 $link->close();

?>
