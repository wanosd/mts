<!DOCTYPE html>
<html>
<head>
<script src="http://maps.googleapis.com/maps/api/js?sensor=false">
</script>

<script>
function initialize()
{
var mapProp = {
  center:new google.maps.LatLng(52.6565176,-8.5537577),
  zoom:18,
  mapTypeId:google.maps.MapTypeId.ROADMAP
  };
var map=new google.maps.Map(document.getElementById("googleMap")
  ,mapProp);
}

google.maps.event.addDomListener(window, 'load', initialize);
</script>
</head>

<body>
<hr>
<div id="googleMap" style="width:500px;height:380px;" align="center"></div>
<hr>
</body>
</html>