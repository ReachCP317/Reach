<?php

require_once('../mysqli_connect.php');


$query = "SELECT eventID, hostID, address FROM event";

$resp = @mysqli_query($dbc, $query);

if($resp) {
  echo '<table align="left"
  cellspacing="5" cellpadding = "8">
  <tr><td align="left"><b>EventID</b></td></tr>
  <tr><td align="left"><b>hostID</b></td></tr>
  <tr><td align="left"><b>address</b></td></tr>';

  while ($row = mysqli_fetch_array($resp)) {
    echo '<tr><td align="left">'.
    $row['eventID']. '</td><td align="left">'.
    $row['hostID']. '</td><td align="left">'.
    $row['address']. '</td><td align="left">';
    echo '</tr>';
  }
  echo "</table>";

} else {
  echo "No results";
}

mysqli_close($dbc);
 ?>
