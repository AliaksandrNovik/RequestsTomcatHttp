<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tomcat 7 Server</title>
<link href='http://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
<style type="text/css">
table, td, th
{
border:1px solid blue;
font-family: 'Oxygen', sans-serif;
}
th
{
background-color:blue;
color:white;
}
body
{
 text-align: center;
}
.container
{
 margin-left: auto;
 margin-right: auto;
 width: 40em;
}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
 $("#tablediv").hide();
     $("#showTable").click(function(event){
           $.get('Requests',function(responseJson) {
            if(responseJson!=null){
                $("#message").find("tr:gt(0)").remove();
                var table1 = $("#message");
                $.each(responseJson, function(key,value) { 
                     var rowNew = $("<tr><td></td><td></td><td></td><td></td><td></td></tr>");
                        rowNew.children().eq(0).text(value['date']); 
                        rowNew.children().eq(1).text(value['time']); 
                        rowNew.children().eq(2).text(value['messageType']); 
                        rowNew.children().eq(3).text(value['messageText']); 
                        rowNew.children().eq(4).text(value['id']); 
                        rowNew.appendTo(table1);
                });
                }
            });
            $("#tablediv").show();          
  });      
});
</script>
</head>
<body class="container">
<h1>AJAX Retrieve Data from Database in Servlet and JSP using JSONArray</h1>
<input type="button" value="Show Table" id="showTable"/>
<div id="tablediv">
<table cellspacing="0" id="message"> 
    <tr> 
        <th scope="col">Date</th> 
        <th scope="col">Time</th> 
        <th scope="col">MessageType</th> 
        <th scope="col">MessageText</th> 
        <th scope="col">Id</th> 
    </tr> 
</table>
</div>
</body>
</html>