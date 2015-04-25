<?php

$file = $_GET['file'];

$file_arr = explode('/',$file);

$file_name = $file_arr[count($file_arr) - 1];

header ("Content-type: octet/stream");

header ("Content-disposition: attachment; filename=".$file_name.";");

header("Content-Length: ".filesize($file));

readfile($file);

exit;

?>



