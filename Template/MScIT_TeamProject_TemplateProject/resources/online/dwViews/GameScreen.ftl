<html>

	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>

    	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
		<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">
    	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
    	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
    	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

	</head>

	<body onload="initalize()"> <!-- Call the initalize method when the page loads -->
		<nav class="navbar navbar-dark fixed-top"><a class="navbar-brand" href="../toptrumps">Top Trumps</a>

				<button type="button" id="catButton" onclick="playRound(); seeCategoryPage();" class="btn btn-primary">See Category Chosen</button>
				<button type="button" id="roundButton" onclick="seeActivePlayer()" class="btn btn-primary">Next Round</button>
				<button type="button" id="mainScreenButton" href="../toptrumps" style="display: none" class="btn btn-primary">Main Screen</button>
		</nav>



		<div class="container">


			<section>
				<div class="jumbotron text-center mt-2">
					<section>
					<div id="EndGameView" class="row">
						<div class="col-md-8 col-sm-12">
							<div class="media">
								<div class="media-body">
									<h5 id="EndGame" class="mt-0"></h5>
								</div>
								</div>
							</div>
							<hr>

								<div id="activePlayer" class="col-md-8 col-sm-12">
									<div class="media">
										<div class="media-body">
												<div id="activePlayer" class="navbar-brand">
												</br></br>
													<hr>
													<h5 id="active"><strong>Who's turn is it?</strong></h5>
													<hr>
													<div id="playerInformation"><p>Information we need to provide the player</p></div>
												</div>
										</div>
										</div>
										<hr>
									</div>
								</div>
							</section>
							<section>
														<div class="row">
						<div class="gridcontainer", "col-sm-4">
							<div class="card" id="Human">
							<h4>Your Card</h4>
								<img class="card-img-top" id="humanImage" src="http://dcs.gla.ac.uk/~richardm/TopTrumps/350r.jpg" alt="Card image">
								<div class="card-img-overlay">
									</br></br></br>
									<h4 id="humanCardName" class="card-title">350r</h4>
									<button type="button" id="humanButton" class="btn btn-primary hcat" onclick="setCategory(1)"> Size: 1</button>
									<section> &nbsp;</section>
									<button type="button" id="humanButton" class="btn btn-primary hcat" onclick="setCategory(2)"> Speed: 9</button>
									<section> &nbsp;</section>
									<button type="button" id="humanButton" class="btn btn-primary hcat" onclick="setCategory(3)"> Range: 2</button>
									<section> &nbsp;</section>
									<button type="button" id="humanButton" class="btn btn-primary hcat" onclick="setCategory(4)"> Firepower: 3</button>
									<section> &nbsp;</section>
									<button type="button" id="humanButton" class="btn btn-primary hcat" onclick="setCategory(5)"> Cargo: 0</button>
								</div>
							</div>
						</div>

						<div class="gridcontainer", "col-sm-4">
							<div class="card" id="AI1">
							<h4>AI 1 Card</h4>
							<img class="card-img-top" id="AI1Image" src="http://dcs.gla.ac.uk/~richardm/TopTrumps/Avenger.jpg" alt="Card image">
								<div class="card-img-overlay">
									</br></br></br>
									<h4 id='AI1CardName' class="card-title">Avenger</h4>
									<button type="button" class="btn btn-primary cat1" disabled> Size: 1</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary cat1"disabled> Speed: 9</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary cat1"disabled> Range: 2</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary cat1"disabled> Firepower: 3</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary cat1"disabled> Cargo: 0</button>
								</div>
							</div>
						</div>


						<div class="gridcontainer", "col-sm-4">
							<div class="card" id="AI2">
							<h4>AI 2 Card</h4>
								<img class="card-img-top" id="AI2Image" src="http://dcs.gla.ac.uk/~richardm/TopTrumps/Carrack.jpg" alt="Card image">
								<div class="card-img-overlay">
									</br></br></br>
									<h4 id='AI2CardName' class="card-title">Carrack</h4>
									<button type="button" class="btn btn-primary cat2"disabled> Size: 1</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary cat2"disabled> Speed: 9</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary cat2"disabled> Range: 2</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary cat2"disabled> Firepower: 3</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary cat2"disabled> Cargo: 0</button>
								</div>
							</div>
						</div>
						<div class="gridcontainer", "col-2">
							<div class="card" id="AI3">
							<h4>AI 3 Card</h4>
								<img class="card-img-top" id="AI3Image" src="http://dcs.gla.ac.uk/~richardm/TopTrumps/Hurricane.jpg" alt="Card image">
								<div class="card-img-overlay">
								</br></br></br>
									<h4 id='AI3CardName' class="card-title">Hurricane</h4>
									<button type="button" class="btn btn-primary cat3"disabled> Size: 1</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary cat3"disabled> Speed: 9</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary cat3"disabled> Range: 2</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary cat3"disabled> Firepower: 3</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary cat3"disabled> Cargo: 0</button>
								</div>
							</div>
						</div>

						<div class="gridcontainer", "col-2">

							<div class="card" id="AI4">
								<h4>AI 4 Card</h4>
								<img class="card-img-top" id="AI4Image" src="http://dcs.gla.ac.uk/~richardm/TopTrumps/Hurricane.jpg" alt="Card image">
								<div class="card-img-overlay">
									</br></br></br>
									<h4 id='AI4CardName' class="card-title" id="shipName">Orion</h4>
									<button type="button" class="btn btn-primary cat4"disabled> Size: 1</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary cat4"disabled> Speed: 9</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary cat4"disabled> Range: 2</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary cat4"disabled> Firepower: 3</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary cat4"disabled> Cargo: 0</button>
								</div>
							</div>
						</div>
				</div>
				</div>
			</section>

			</div>


			<style>

			#activePlayer{
				float: left;
			}
			.card{
				width:250px;
				height:450px;
				border: 1px solid transparent;
			}
			.card-img-top{
				width:100%;
				height:100%;
			}
			.card-title{
				color: #ffffff;
			}
			.gridcontainer{
				display: grid;
				padding: 2% 4.5%;
			}
			.gridcontainer-2{
				display: grid;
				padding-top: 3%;
				padding-left: 5%;
			}
			.gridcontainer-3{
				display: grid;
				padding: 3% 5%;
			}

			.namecontainer{
				background-color: #ffffff;
			}
			.navbar{
				background-image: url("http://dcs.gla.ac.uk/~richardm/TopTrumps/Vanguard.jpg"); /* The image used */
				background-color: #4d0026; /* Used if the image is unavailable */
				height: 100px;
				background-position: center; /* Center the image */
				background-repeat: no-repeat; /* Do not repeat the image */
				background-size: cover; /* Resize the background image to cover the entire container */

			}
			.container, .jumbotron{
				background-color: #ffffff;
			}

			.btn {
				display: inline-block;
				font-weight: 400;
				color: #212529;
				text-align: center;
				vertical-align: middle;
				background-color: transparent;
				border: 1px solid transparent;
				padding: 0.375rem 0.75rem;
				font-size: 1rem;
				line-height: 1.5;
				border-radius: 0.25rem;
				transition: color 0.15s ease-in-out;
				background-color: 0.15s ease-in-out;
				border-color: 0.15s ease-in-out;
				box-shadow: 0.15s ease-in-out;
			}
			.btn:disabled{
				color: #fff;
				background-color: #4d0026;
				border-color: #4d0026;
				opacity: 0.89;
			}
			.btn-primary {
				color: #fff;
				background-color: #4d0026;
				border-color: #4d0026;
			}
			.btn-primary:hover {
				color: #fff;
				background-color: #660033;
				border-color: #660033;
			}
			.btn-primary:disabled {
				color: #fff;
				background-color: #4d0026;
				border-color: #4d0026;
				opacity: 1.0;
			}
			.media-body{
				text-align: left;

			}
		</style>


		<script type="text/javascript">
			var numOpponents;
			var shipImages = ["350r.jpg", "Avenger.jpg", "Carrack.jpg", "Constellation.jpg", "Hawk.jpg", "Hornet.jpg", "Hurricane.jpg", "Merchantman.jpg", "Idris.jpg", "Orion.jpg", "Sabre.jpg", "m50.jpg"];
			var activePlayer = 0;
			var gameState = new Array();
			var categorySelected = 0;
			var opponentCards = new Array();
			var buttonElements;
			var humanCardNameVar;
			var AI1CardNameVar;
			var AI2CardNameVar;
			var AI3CardNameVar;
			var AI4CardNameVar;
			var attributeList = ["size","speed","range","firepower","cargo"];
			var catString;
			var currentPlayer;
			// Method that is called on page load
			function initalize() {
	//			var query = decodeURIComponent(window.location.search);
	//			var queries = query.split("=");
	//			numOpponents = 1; //queries[1]


				buttonElements = document.querySelectorAll('[id="humanButton"]');
				setUpGame();


			}
			function displayEndGame(points)
			{
				document.getElementById('EndGameView').display = 'block';
			//		document.getElementById('EndGameView2').display = 'block';
				if(document.getElementById('Human') != null)
				{
					document.getElementById('Human').remove();
				}
				if(document.getElementById('AI1') != null)
				{
					document.getElementById('AI1').remove();
				}
				if(document.getElementById('AI2') != null)
				{
					document.getElementById('AI2').remove();
				}
				if(document.getElementById('AI3') != null)
				{
					document.getElementById('AI3').remove();
				}
				if(document.getElementById('AI4') != null)
				{
					document.getElementById('AI4').remove();
				}
				document.getElementById('roundButton').remove();
				document.getElementById('mainScreenButton').display = "block";
				document.getElementById('catButton').remove();
				document.getElementById('activePlayer').remove();

				document.getElementById('EndGame').innerHTML =	"</br></brYou won "+points[0].human+" rounds</br><hr>";
				if(numOpponents >= 1){
					document.getElementById('EndGame').innerHTML += "AI 1 won "+points[0].ai1+" rounds</br><hr>";
				}
				if(numOpponents >= 2){
					document.getElementById('EndGame').innerHTML += "AI 2 won "+points[0].ai2+" rounds</br><hr>";
				}
				if(numOpponents >= 3){
					document.getElementById('EndGame').innerHTML += "AI 3 won "+points[0].ai3+" rounds</br><hr>";
				}
				if(numOpponents >= 4){
					document.getElementById('EndGame').innerHTML += "AI 4 won "+points[0].ai4+" rounds</br><hr>";
				}

				document.getElementById('EndGame').innerHTML +=	"Click on \"Top Trumps\" above to go back to the main screen.<hr>";



			}
			function setCategory(catNumber)
			{
				categorySelected = catNumber;
				//Step 4
				sendToUserChoice();
				//Step 5
				playRound();
				seeCategoryPage();
			}
			function disableHumanButtons()
			{
				for (i = 0; i < buttonElements.length; i++){
				  		buttonElements[i].disabled = true;
				}
		//		document.getElementById('humanButton').disabled = true;
			}
			function enableHumanButtons()
			{
				for (i = 0; i < buttonElements.length; i++){
							buttonElements[i].disabled = false;
				}
		//		document.getElementById('humanButton').disabled = false;
			}

			function setActivePlayer()
			{
				nextPlayerHuman();
				//Step 3
				displayCards();
			}

			function setInformationForPlayer(attributeList, winnerName, draw){
				document.getElementById('active').innerHTML =currentPlayer+ " chose " + attributeList +" this round.";

				if(draw.includes("true"))
				{
					document.getElementById("playerInformation").innerHTML = "This round is a draw! </br>"
				}
				else {
					document.getElementById("playerInformation").innerHTML = winnerName +" won this round.";
				}
			}

			function setRoundInformation()
			{
				if(document.getElementById('Human') != null)
				{
					document.getElementById("playerInformation").innerHTML +=  "     Your " + attributeList[catString]+" was: "+gameState[0][0].attributes[catString];
				}
					if(numOpponents >= 1){
						if(document.getElementById('AI1') != null)
						{
							document.getElementById("playerInformation").innerHTML +=  "     AI 1's " + attributeList[catString]+" was: "+gameState[1][0].attributes[catString];
						}
					}
					if(numOpponents >= 2){
						if(document.getElementById('AI2') != null)
						{
							document.getElementById("playerInformation").innerHTML += "     AI 2's " + attributeList[catString]+" was: "+gameState[2][0].attributes[catString];
						}
					}
				 	if(numOpponents >= 3){
						if(document.getElementById('AI3') != null)
						{
							document.getElementById("playerInformation").innerHTML += "   AI 3's " + attributeList[catString]+" was: "+gameState[3][0].attributes[catString];
						}
					}
					if (numOpponents >= 4){
						if(document.getElementById('AI2') != null)
						{
							document.getElementById("playerInformation").innerHTML += "    AI 4's " + attributeList[catString]+" was: "+gameState[4][0].attributes[catString];
						}
					}
			}

			function seeCategoryPage()
			{
				document.getElementById('catButton').style.display = 'none';
				document.getElementById('roundButton').style.display = 'block';
		//		document.getElementById('mainScreenButton').display = "none";
				displayOpponentCards();
				disableHumanButtons();
			}
			function seeActivePlayer()
			{
				//Step 2
				setActivePlayer();

				document.getElementById('catButton').style.display = 'block';
				document.getElementById('roundButton').style.display = 'none';
		//		document.getElementById('mainScreenButton').display = "none";
				displayHumanCards();
				setOpponentsDisplayOff();
			}

			function displayOpponentCards()
			{
				setOpponentsDisplayOn();
			}

			function displayHumanCards()
			{
			}

			function setOpponents()
			{
				if(numOpponents == 1)
				{
					document.getElementById('AI4').remove();
					document.getElementById('AI3').remove();
					document.getElementById('AI2').remove();
					opponentCards[0] = document.getElementById('AI1');
				}
				else if(numOpponents == 2)
				{
					document.getElementById('AI4').remove();
					document.getElementById('AI3').remove();
					opponentCards[0] = document.getElementById('AI1');
					opponentCards[1] = document.getElementById('AI2');
				}
				else if(numOpponents == 3)
				{
					document.getElementById('AI4').remove();
					opponentCards[0] = document.getElementById('AI1');
					opponentCards[1] = document.getElementById('AI2');
					opponentCards[2] = document.getElementById('AI3');
				}
				else
				{
					opponentCards[0] = document.getElementById('AI1');
					opponentCards[1] = document.getElementById('AI2');
					opponentCards[2] = document.getElementById('AI3');
					opponentCards[3] = document.getElementById('AI4');
				}
			}

			function setOpponentsDisplayOff()
			{
				for (i = 0; i < opponentCards.length; i++) {
					opponentCards[i].style.display = "none";
				}
			}

			function setOpponentsDisplayOn()
			{
				for (i = 0; i < opponentCards.length; i++) {
					opponentCards[i].style.display = "block";
				}
			}
			function setHumanCardImage()
			{
				document.getElementById('humanImage').src = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + shipImages[Math.floor(Math.random()*12)];
			}
			function setCardImages(temp)
			{
				if(temp.hasOwnProperty("You"))
				{
					var you = JSON.parse(JSON.stringify(temp["You"]));
					humanCardNameVar = you[0].name;
					gameState[0] = you;
					document.getElementById('humanCardName').innerHTML = humanCardNameVar;
					setHumanCardImage();
					var catButtonArray = document.getElementsByClassName('hcat');

					for(i = 0; i < 5; i++)
					{
						catButtonArray[i].innerHTML = you[0].criterias[i]+": " + you[0].attributes[i];
					}
				}
				else {
					if(document.getElementById('Human') != null)
					{
						document.getElementById('Human').remove();
					}
				}

				if(numOpponents >= 1)
				{
					if(temp.hasOwnProperty("AI 1"))
					{
						var ai1 = JSON.parse(JSON.stringify(temp["AI 1"]));
						gameState[1] = ai1;
						AI1CardNameVar = ai1[0].name;
						document.getElementById('AI1Image').src = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + shipImages[Math.floor(Math.random()*12)];
						document.getElementById('AI1CardName').innerHTML = AI1CardNameVar;
						catButtonArray = document.getElementsByClassName('cat1');

						for(i = 0; i < 5; i++)
						{
							catButtonArray[i].innerHTML = ai1[0].criterias[i]+": " + ai1[0].attributes[i];
						}
					}
					else {
						if(document.getElementById('AI1') != null)
						{
							document.getElementById('AI1').remove();
					}
					}
				}
				if(numOpponents >= 2)
				{
					if(temp.hasOwnProperty("AI 2"))
					{
						var ai2 = JSON.parse(JSON.stringify(temp["AI 2"]));
						gameState[2] = ai2;
						AI2CardNameVar = ai2[0].name;
						document.getElementById('AI2Image').src = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + shipImages[Math.floor(Math.random()*12)];
						document.getElementById('AI2CardName').innerHTML = AI2CardNameVar;
						catButtonArray = document.getElementsByClassName('cat2');

						for(i = 0; i < 5; i++)
						{
							catButtonArray[i].innerHTML = ai2[0].criterias[i]+": " + ai2[0].attributes[i];
						}
					}
					else {
						if(document.getElementById('AI2') != null)
						{
						document.getElementById('AI2').remove();
					}
					}
				}
				if(numOpponents >= 3)
				{
					if(temp.hasOwnProperty("AI 3"))
					{
						var ai3 = JSON.parse(JSON.stringify(temp["AI 3"]));
						gameState[3] = ai3;
						AI3CardNameVar = ai3[0].name;
						document.getElementById('AI3Image').src = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + shipImages[Math.floor(Math.random()*12)];
						document.getElementById('AI3CardName').innerHTML = AI3CardNameVar;
						catButtonArray = document.getElementsByClassName('cat3');

						for(i = 0; i < 5; i++)
						{
							catButtonArray[i].innerHTML = ai3[0].criterias[i]+": " + ai3[0].attributes[i];
						}
					}
					else {
						if(document.getElementById('AI3') != null)
						{
						document.getElementById('AI3').remove();
					}
					}
				}
				if(numOpponents >= 4)
				{
					if(temp.hasOwnProperty("AI 4"))
					{
						var ai4 = JSON.parse(JSON.stringify(temp["AI 4"]));
						gameState[4] = ai4;
						AI4CardNameVar = ai4[0].name;
						document.getElementById('AI4Image').src = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + shipImages[Math.floor(Math.random()*12)];
						document.getElementById('AI4CardName').innerHTML = AI4CardNameVar;
						catButtonArray = document.getElementsByClassName('cat4');

						for(i = 0; i < 5; i++)
						{
							catButtonArray[i].innerHTML = ai4[0].criterias[i]+": " + ai4[0].attributes[i];
						}
					}
					else {
						if(document.getElementById('AI14') != null)
						{
						document.getElementById('AI4').remove();
					}
					}
				}

			}

			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------

			// This is a reusable method for creating a CORS request. Do not edit this.
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

		</script>

		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">

			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloJSONList() {

				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloJSONList"); // Request type and URL

				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};

				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}

			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloWord(word) {

				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloWord?Word="+word); // Request type and URL+parameters

				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};

				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}


			//Moving RESTAPI functions to see where they fit in front send

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

//      STEP 4 - /userChoice - if /isNextPlayerHuman is true, then choose which card you want to play.<br>
//	  This should be safe to call several times in a row
			function sendToUserChoice(){
				userChoice = categorySelected;

				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/userChoice?choice="+userChoice); // Request type and URL+parameters

						// Message is not sent yet, but we can check that the browser supports CORS
						if (!xhr) {
							alert("CORS not supported");
						}

						// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
						// to do when the response arrives
						xhr.onload = function(e) {
							var responseText = xhr.response; // the text of the response
						//	alert(userChoice); // lets produce an alert
						};

						// We have done everything we need to prepare the CORS request, so send it
						xhr.send();
			}

//			STEP 4 - /userChoice - if /isNextPlayerHuman is true, then choose which card you want to play.<br>
//		  This should be safe to call several times in a row
			function sendToAIPlayers(){
					AIPlayers = numOpponents; //document.getElementById("AIPlayers").value;

					var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/AIplayers?AIplayers="+AIPlayers); // Request type and URL+parameters

					// Message is not sent yet, but we can check that the browser supports CORS
					if (!xhr) {
						alert("CORS not supported");
					}

					// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
					// to do when the response arrives
					xhr.onload = function(e) {
						var responseText = xhr.response; // the text of the response
						//alert(AIPlayers); // lets produce an alert
					};

					// We have done everything we need to prepare the CORS request, so send it
					xhr.send();
			}

			function populateDisplay(str){

			console.log(str);
			console.log(JSON.parse(str));

				var jsonObject = JSON.parse(str);

				const statsString = JSON.stringify(jsonObject, null, '\t');

		//		document.getElementById("textdisplay").innerHTML = "<pre> " + statsString + " </pre>";

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
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/numOpponents"); // Request type and URL+parameters

				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					numOpponents = responseText;
					setOpponentsDisplayOff();
					setOpponents();

	//Step 2 & 3
					seeActivePlayer();


					setHumanCardImage();


				//	alert(responseText); // lets produce an alert
				};


				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}
//	  STEP 2 - /isNextPlayerHuman returns true if is human and false if AI. <br>
//	  Calling many times is safe
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

				if(str.includes("true"))
				{
					activePlayer = 0;
					enableHumanButtons();
				}
				else {
					activePlayer = 1;
					disableHumanButtons();
				}
			//	document.getElementById("nextPlayerDisplay").innerHTML = str
			}

//	  STEP 5 - /playRound plays the round assuming the user choice has been made..<br>
//	  if it is the users turn but the API is not used to make the choice it will default to the attribute choice<br>
//	  that was made last round
//	  returns a json file of the current round <br>
//	  This is NOT safe to call several times, 1 call = 1 turn :)
//	  If the game is over, it will return GAME : OVER, and thereafter it will return an error


			function playRound(){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/playRound"); // Request type and URL+parameters

				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
				//	var responseText = xhr.response; // the text of the response
				//	console.log(responseText); // lets produce an alert
					var temp = JSON.parse(xhr.response)
					var turnStats = JSON.parse(JSON.stringify(temp["turnStats"]));
					var points = JSON.parse(JSON.stringify(temp["points"]));

					if (!turnStats[0].hasOwnProperty("GAME")){
						var winnerName = turnStats[0].winnerName;
						var draw = JSON.stringify(turnStats[0].isDraw);
						var attributeNumberPlayed = parseInt(turnStats[0].attributeNumberPlayed) - 1;

						catString = attributeNumberPlayed;
						setInformationForPlayer(attributeList[attributeNumberPlayed], winnerName, points, draw);
						setRoundInformation();
					}
					else {
						displayEndGame(points);
					}
			//		populatePlayRoundDisplay(responseText)
				};

				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();

			}

			function populatePlayRoundDisplay(str){

				var jsonObject = JSON.parse(str);


				const statsString = JSON.stringify(jsonObject, null, '\t');

		//		document.getElementById("playerInformation").innerHTML = temp;

			}

//	  STEP 3 - /displayCards return a JSON of the key data when beginning the round<br>
//	  Round Number, isHumanChoice and nameOfNextPlayer, and card data per player!
//	  Safe to call as many times
				function displayCards(){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/displayCards"); // Request type and URL+parameters

				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
					var temp = JSON.parse(xhr.response)
					var stats = JSON.parse(JSON.stringify(temp["Stats"]));

					currentPlayer = stats[0].nameOfNextPlayer;

					if(JSON.stringify(stats[0].nameOfNextPlayer).includes("You"))
					{
						activePlayer = 0;
						document.getElementById("playerInformation").innerHTML = "Please choose a category by clicking on the category buttons on your card";
						enableHumanButtons();
						document.getElementById('catButton').style.display = 'none';
						setOpponentsDisplayOff();
					}
					else {
						activePlayer = 1;
						document.getElementById("playerInformation").innerHTML = "Click the 'See Category Chosen' button above to see the category your opponent chose.";
						disableHumanButtons();
						document.getElementById('catButton').style.display = 'block';
						setOpponentsDisplayOff();
					}

					document.getElementById('active').innerHTML = "Round: "+ stats[0].roundNumber+"</br>"+"Current Player: "+stats[0].nameOfNextPlayer;

					setCardImages(temp);

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

					//document.getElementById("playerInformation").innerHTML = "<pre> " + statsString + " </pre>";

			}

		</script>

		</body>
</html>
