<?php

DEFINE  ('DB_USER', 'reachWeb@reachdb');
DEFINE ('DB_PASS', 'reachWLU3@');
DEFINE ('DB_HOST', 'reachdb.mysql.database.azure.com');
DEFINE ('DB_NAME', 'reachdb');

$dbc = @mysqli_connect(DB_HOST, DB_USER, DB_PASS, DB_NAME)
OR die('Could not connect to MySQL: ' .
mysqli_connect_error());
?>
