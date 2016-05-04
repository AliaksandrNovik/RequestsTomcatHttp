	<?php
$header = "Content-Type: application/json";
header($header);
 
$dbLink = mysqli_connect('146.185.164.93','root','7Ea4fae339','tomcat7');
 
if (!$dbLink) {
    $row = array("siteStatus" => "Database Error");
    print json_encode($row);
} else {
    $query = "SELECT * FROM message ";
 
    if ($result = mysqli_query($dbLink,$query)) {
        $row = $result->fetch_array(MYSQLI_ASSOC);
        if (is_null($row)) {
            $row = array("siteStatus" => "Error - Site Not Found");
        }
    } else {
        $row = array("siteStatus" => "General Error");
    }
 
    print json_encode($row);
    mysqli_close($dbLink);
} // End else condition (for database connection)
 
?>