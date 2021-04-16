//logs a client in 
<?php

if(isset($_POST['email'])) {
  require_once "conn.php";
  require_once "validate.php";
  $email = validate($_POST['email']);
  $sql = "SELECT * FROM client_table WHERE client_email LIKE '$email';";
  $result = $conn->query($sql);
  if($result->num_rows>0) {
    echo "success";
  } else {
    echo "failure";
  }
}

 ?>
