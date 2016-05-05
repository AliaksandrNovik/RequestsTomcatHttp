<?php 

	if($_SERVER['REQUEST_METHOD']=='GET'){
		
		$id  = $_GET['id'];
		
		require_once('test.php');
		
		$sql = "SELECT * FROM message";
		
		$r = mysqli_query($con,$sql);
		
		$res = mysqli_fetch_array($r);
		
		$result = array();
		
		array_push($result,array(
			"date"=>$res['date'],
			"time"=>$res['time'],
			"messageType"=>$res['messageType'],
			"messsageText"=>$res['messageText']
			)
		);
		
		echo json_encode(array("result"=>$result));
		
		mysqli_close($con);
		
	}