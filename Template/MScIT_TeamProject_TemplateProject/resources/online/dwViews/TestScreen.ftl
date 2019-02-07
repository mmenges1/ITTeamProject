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
		
		var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/userChoice?choice="+userChoice); // Request type and URL+parameters
					
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
	
	/* NEW FUNCTIONS BELOW */
	
	function setUpGame(){
		numberOfPlayers = document.getElementById("setPlayers").value;
		var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/setUpGame?numberOfPlayers="+numberOfPlayers); // Request type and URL+parameters
					
		// Message is not sent yet, but we can check that the browser supports CORS
		if (!xhr) {
			alert("CORS not supported");
		}

		// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
		// to do when the response arrives 
		xhr.onload = function(e) {
			var responseText = xhr.response; // the text of the response
			alert(numberOfPlayers); // lets produce an alert
		};
		
		// We have done everything we need to prepare the CORS request, so send it
		xhr.send();		
	}
	
	function nextPlayerHuman(){
	
		var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/isNextPlayerHuman"); // Request type and URL+parameters
					
		// Message is not sent yet, but we can check that the browser supports CORS
		if (!xhr) {
			alert("CORS not supported");
		}

		// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
		// to do when the response arrives 
		xhr.onload = function(e) {
			var responseText = xhr.response; // the text of the response
			//alert(responseText); // lets produce an alert
			populateNextPlayerText(responseText)
		};
		
		// We have done everything we need to prepare the CORS request, so send it
		xhr.send();		
	
	}
	
	function populateNextPlayerText(str){
		document.getElementById("nextPlayerDisplay").innerHTML = str
	}
	
	function playRound(){
		var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/playRound"); // Request type and URL+parameters
					
		// Message is not sent yet, but we can check that the browser supports CORS
		if (!xhr) {
			alert("CORS not supported");
		}

		// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
		// to do when the response arrives 
		xhr.onload = function(e) {
			var responseText = xhr.response; // the text of the response
			console.log(responseText); // lets produce an alert
			populatePlayRoundDisplay(responseText)
		};
		
		// We have done everything we need to prepare the CORS request, so send it
		xhr.send();		
		
	}
	
	function populatePlayRoundDisplay(str){
	
		var jsonObject = JSON.parse(str);
		
		const statsString = JSON.stringify(jsonObject, null, '\t');
			
		document.getElementById("playRoundDisplay").innerHTML = "<pre> " + statsString + " </pre>";
		
	}
	
		function displayCards(){
		var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/displayCards"); // Request type and URL+parameters
					
		// Message is not sent yet, but we can check that the browser supports CORS
		if (!xhr) {
			alert("CORS not supported");
		}

		// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
		// to do when the response arrives 
		xhr.onload = function(e) {
			var responseText = xhr.response; // the text of the response
			console.log(responseText); // lets produce an alert
			populateDisplayCardsDisplay(responseText)
		};
		
		// We have done everything we need to prepare the CORS request, so send it
		xhr.send();		
		
	}
	
		function populateDisplayCardsDisplay(str){
	
		var jsonObject = JSON.parse(str);
		
		const statsString = JSON.stringify(jsonObject, null, '\t');
			
		document.getElementById("displayCardsDisplay").innerHTML = "<pre> " + statsString + " </pre>";
		
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
	  <select id="userChoiceOld"  >
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
		<option value="4">4</option>
        <option value="5">5</option>
      </select><br><br><br><br>
	  
	  <input type="button" onclick="sendToUserChoice()" value="Go!" />
	  </form>
	  
<hr>
<div>
	<h2> Below is the updated rest API</h2>
</div>

<div>
<form id="myForm">	  

      STEP 1 - /setUpGame - set up game and select the number of AI players
		This is NOT safe to call in the middle of a game, it will restart
	  <select id="setPlayers">
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
		<option value="4">4</option>
      </select>
	  
	  <input type="button" onclick="setUpGame()" value="Go!" />
      </form>
	  
<form>
<br><br><br><br>
	  STEP 2 - /isNextPlayerHuman returns true if is human and false if AI. <br>
	  Calling many times is safe
	  
	  <input type= "button" onclick="nextPlayerHuman()" value="Go!" />
	  
	  <p id="nextPlayerDisplay"> next player boolean should populate here </p><br>
	  </form>
	  

	  
<br><br><br><br>
<form>
	  STEP 3 - /displayCards return a JSON of the key data when beginning the round<br>
	  Round Number, isHumanChoice and nameOfNextPlayer, and card data per player!
	  Safe to call as many times
	  <input type= "button" onclick="displayCards()" value="Go!" />
	  
	  <p id="displayCardsDisplay"> display cards here </p><br>
	  </form>
</div>
<br><br><br><br>
<form id="myForm">
      STEP 4 - /userChoice - if /isNextPlayerHuman is true, then choose which card you want to play.<br> 
	  This should be safe to call several times in a row
	  <select id="userChoice"  >
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
		<option value="4">4</option>
        <option value="5">5</option>
      </select>
	  
	  <input type="button" onclick="sendToUserChoice()" value="Go!" />
	  </form>
	  
<br><br><br><br>
<form>
	  STEP 5 - /playRound plays the round assuming the user choice has been made..<br>
	  if it is the users turn but the API is not used to make the choice it will default to the attribute choice<br>
	  that was made last round
	  returns a json file of the current round <br>
	  This is NOT safe to call several times, 1 call = 1 turn :)
	  If the game is over, it will return GAME : OVER, and thereafter it will return an error 
	  
	  
	  <input type= "button" onclick="playRound()" value="Go!" />
	  
	  <p id="playRoundDisplay"> display the round json here </p><br>
	  </form>
</div>
<br><br><br><br>
	  
<section id="display">
<input type="button" onclick="getTurnStats()" value="Get Turn Stats!" />
<div id="textdisplay">

</div>

</section>



</font>
</html>
	 