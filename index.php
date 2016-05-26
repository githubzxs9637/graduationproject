<!DOCTYPE html>
<html>
<body>

<form id="fileForm" name="fileForm" enctype="multipart/form-data">
    Select image to upload:
    <input type="file" name="fileToUpload" id="fileToUpload">
    <input type="submit" id="uploadButton" value="Upload" name="submit">
</form>

<div id="contents"></div>
<button onclick="processFile()">Process File</button>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script>
	$(document).ready(function () {
		//Program a custom submit function for the form
		$("form#fileForm").submit(function(event){
		 
		  //disable the default form submission
		  event.preventDefault();
		 
		  //grab all form data  
		  var formData = new FormData($(this)[0]);
		 
		  $.ajax({
			url: 'upload.php',
			type: 'POST',
			data: formData,
			async: false,
			cache: false,
			contentType: false,
			processData: false,
			success: function (fileName) {
				showFile("uploads/"+fileName.trim());
			}
		  });
		 
		  return false;
		});
	});
	function showFile(url) {
		var xhr = new XMLHttpRequest();
		xhr.onload = function () {
			document.getElementById('contents').textContent = this.responseText;
		};
		xhr.open('GET', url);
		xhr.send();
	}
	function processFile(){
		var text = $("#contents").text().trim().split(" ");
		for(var i=0;i<text.length;i++){
			alert(text[i]);
		}
	}
</script>
</head>
</body>
</html>