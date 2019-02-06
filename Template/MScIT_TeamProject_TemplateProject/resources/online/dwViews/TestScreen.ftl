<html>
<font face='monospace'>

Hello world

<br>
<head>
		<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

</head>

<script type="text/javascript">

	function createCORSRequest(method, url) {
  				var xhr = new XMLHttpRequest();
  				if ("withCredentials" in xhr) {

    				// Check if the XMLHttpRequest object has a "withCredentials" property.
    				// "withCredentials" only exists on XMLHTTPRequest2 objects.
    				xhr.open(method, url, true);

  				} else if (typeof XDomainRequest != "undefined") {

    				// Otherwise, check if XDomainRequest.
    				// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
    				xhr = new XDomainRequest();
    				xhr.open(method, url);

 				 } else {

    				// Otherwise, CORS is not supported by the browser.
    				xhr = null;

  				 }
  				 return xhr;
			}
			

	function sendToUserChoice(){
		userChoice = document.getElementById("userChoice").value;
		
		var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/userChoice?Choice="+userChoice); // Request type and URL+parameters
					
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					alert(userChoice); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
	}
	
	function sendToAIPlayers(){
			AIPlayers = document.getElementById("AIPlayers").value;
			
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/AIplayers?AIplayers="+AIPlayers); // Request type and URL+parameters
						
			// Message is not sent yet, but we can check that the browser supports CORS
			if (!xhr) {
				alert("CORS not supported");
			}
	
			// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
			// to do when the response arrives 
			xhr.onload = function(e) {
				var responseText = xhr.response; // the text of the response
				alert(AIPlayers); // lets produce an alert
			};
			
			// We have done everything we need to prepare the CORS request, so send it
			xhr.send();		
	}
	
	function populateDisplay(str){
	
	console.log(str);
	console.log(JSON.parse(str));
	
		var jsonObject = JSON.parse(str);
		
		const statsString = JSON.stringify(jsonObject, null, '\t');
			
		document.getElementById("textdisplay").innerHTML = "<pre> " + statsString + " </pre>";
		
		//$('#textdisplay').text(JSON.stringify(jsonObject, null, '\t'));
		
	}
	

	
	function getTurnStats(){
	
		var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getTurnStats"); // Request type and URL+parameters
		var turnStats;
				
		// Message is not sent yet, but we can check that the browser supports CORS
		if (!xhr) {
			alert("CORS not supported");
		}

		// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
		// to do when the response arrives 
		xhr.onload = function(e) {
				turnStats = xhr.response; // the text of the response
				//alert(turnStats); // lets produce an alert
				console.log(turnStats)
				populateDisplay(turnStats);
			};
		
		// We have done everything we need to prepare the CORS request, so send it
		xhr.send();		
		
		//console.log(JSON.stringify(this.turnStats));
		
		return this.turnStats;
		
	
	}

</script>

<form id="myForm">	  

      Number of AI players:
	  <select id="AIPlayers"  >
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
		<option value="4">4</option>
      </select><br><br><br><br>
	  
	  <input type="button" onclick="sendToAIPlayers()" value="Go!" />
      </form>
      
<form id="myForm">
      User choice is: 
	  <select id="userChoice"  >
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
		<option value="4">4</option>
        <option value="5">5</option>
      </select><br><br><br><br>
	  
	  <input type="button" onclick="sendToUserChoice()" value="Go!" />
	  </form>
	  
<section id="display">
<input type="button" onclick="getTurnStats()" value="Get Turn Stats!" />
<div id="textdisplay">

</div>

</section>

</font>
</html>
	 