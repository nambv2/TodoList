<!DOCTYPE html>
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>
$(document).ready(function(){
  $(":checkbox").wrap("<span style='background-color:red' />");
});
</script>
</head>
<body>

<form action="">
Name: <input type="text" name="user"><br>
I have a bike:
<input type="checkbox" name="vehicle" value="Bike"><br>
I have a car:
<input type="checkbox" name="vehicle" value="Car"><br>
I have an airplane:
<input type="checkbox" name="vehicle" value="Airplane"><br>
<input type="submit">
</form>

<p>Note: We use jQuerys .wrap method to highlight the selected elements, because Firefox does not support background color on checkboxes.</p>

</body>
</html>