<!DOCTYPE html>
<html>
<head>

</head>
<body>

<p id="mainPassage"></p>
<button onclick="hiliter(4,15)">YOLO</button>
<button onclick="hiliter(16,22)">YOLO</button>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>

$(document).ready(function(){
	
	$.get("store.php?task=getPassage", function(data, status){
        $("#mainPassage").text(data);
    });
	
});

function hiliter(start, end) {
    
	var passageHTML = $("#mainPassage");
	var passage = $("#mainPassage").text();
	
	var substring = passage.substring(start, end);
	var first = passage.substring(0, start);
	var second = passage.substring(end, passage.length-1);
	
	first = first+"<span style='color:red'>";
	second = "</span>"+second;
	
	passageHTML.html(first+substring+second);
	
}
</script>
</body>
</html>

