<!DOCTYPE html>
<html><body>
<?php
$username = "***";  //login details on discord
$password = "***";
$database = "workmatedb";
$mysqli = new mysqli("localhost", $username, $password, $database);

$sql = "SELECT client_id, client_fname, client_lname, client_email, client_phone, client_addr FROM client_table";
echo "<b> <center>Database Output</center> </b> <br> <br>";

echo '<table cellspacing="5" cellpadding="5">
      <tr>
        <td>ID</td>
        <td>FirstName</td>
        <td>LastName</td>
        <td>Email</td>
        <td>Phone</td>
        <td>Address</td>
      </tr>';

if ($result = $mysqli->query($sql)) {
    while ($row = $result->fetch_assoc()) {
        $row_id = $row["client_id"];
        $row_fname = $row["client_fname"];
        $row_lname = $row["client_lname"];
        $row_email = $row["client_email"];
        $row_phone = $row["client_phone"];
        $row_addr = $row["client_addr"];

        echo '<tr>
                <td>' . $row_id . '</td>
                <td>' . $row_fname . '</td>
                <td>' . $row_lname . '</td>
                <td>' . $row_email . '</td>
                <td>' . $row_phone . '</td>
                <td>' . $row_addr . '</td>
              </tr>';
    }
    $result->free();

}
$mysqli->close();
?>
</table>
</body>
</html>
