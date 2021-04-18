<?php

$link = mysqli_connect("localhost", "***", "***", "workmatedb");

// Check connection
if($link === false){
    die("ERROR: Could not connect. " . mysqli_connect_error());
}
$sql = $link->prepare("SELECT order_id, client_email, supplier_email, supplier_service, order_date, order_hours, order_cost FROM order_table;");
$sql->execute();

$sql->bind_result($order_id, $client_email, $supplier_email, $supplier_service, $order_date, $order_hours, $order_cost);

$orders = array();

while($sql->fetch()) {
  $temp = array();
  $temp['order_id'] = $order_id;
  $temp['client_email'] = $client_email;
  $temp['supplier_email'] = $supplier_email;
  $temp['supplier_service'] = $supplier_service;
  $temp['order_date'] = $order_date;
  $temp['order_hours'] = $order_hours;
  $temp['order_cost'] = $order_cost;
  array_push($orders, $temp);
}
echo json_encode($orders);



 $link->close();

?>
