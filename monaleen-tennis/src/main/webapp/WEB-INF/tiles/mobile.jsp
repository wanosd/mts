<script type="text/javascript">

function displayDay(data){
	alert(data.day);
}

function onLoad(){
	$.getJSON('${pageContext.request.contextPath }/jsoncurrentday', displayDay);
}

$(document).ready(onLoad);

</script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/script/jquery.js"></script>
