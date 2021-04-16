//registers a client
<?php

 if(isset($_POST['fname']) && isset($_POST['lname']) && isset($_POST['email']) && isset($_POST['phone']) && isset($_POST['addr'])) {
   require_once "conn.php";
   require_once "validate.php";
   $fname = validate($_POST['fname']);
   $lname = validate($_POST['lname']);
   $email = validate($_POST['email']);
   $phone = validate($_POST['phone']);
   $addr = validate($_POST['addr']);

   $sql = "INSERT INTO `client_table`(`client_id`,`client_fname`, `client_lname`,`client_email`,`client_phone`, 'client_addr') values (NULL,'".$fname."','".$Lname."','".$email."','".$phone."','".$addr."')";
   if(!$conn->query($sql)) {
     echo "failure to register";
   } else {
     echo "success! Registered.";
   }
 }
 ?>
