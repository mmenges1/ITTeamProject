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
		<nav class="navbar navbar-dark"><a class="navbar-brand" href="../toptrumps">Top Trumps</a></nav>

		<div class="container">
			<section>
				<div class="jumbotron text-center mt-2">
					<div class="row">
						<section>
							<div class="alert alert-info">
								<div id="active"><strong>Who's turn is it?</strong></div>
								<div id="playerInformation"><p>Information we need to provide the player</p></div>
							</div>
							<button type="button" onclick="seeCategoryPage()" class="btn btn-primary">See Category Chosen</button>
						</section>
						<div class="gridcontainer", "col-sm-4">
							<div class="card" id="Human">
							<h4>Your Card</h4>
								<img class="card-img-top" id="humanImage" src="http://dcs.gla.ac.uk/~richardm/TopTrumps/350r.jpg" alt="Card image">
								<div class="card-img-overlay">
									</br></br></br>
									<h4 class="card-title">350r</h4>
									<button type="button" id="humanButton" class="btn btn-primary" onclick="setCategory(1)"> Size: 1</button>
									<section> &nbsp;</section>
									<button type="button" id="humanButton" class="btn btn-primary" onclick="setCategory(2)"> Speed: 9</button>
									<section> &nbsp;</section>
									<button type="button" id="humanButton" class="btn btn-primary" onclick="setCategory(3)"> Range: 2</button>
									<section> &nbsp;</section>
									<button type="button" id="humanButton" class="btn btn-primary" onclick="setCategory(4)"> Firepower: 3</button>
									<section> &nbsp;</section>
									<button type="button" id="humanButton" class="btn btn-primary" onclick="setCategory(5)"> Cargo: 0</button>
								</div>
							</div>
						</div>

						<div class="gridcontainer", "col-sm-4">
							<div class="card" id="AI1">
							<h4>AI 1 Card</h4>
							<img class="card-img-top" id="AI1Image" src="http://dcs.gla.ac.uk/~richardm/TopTrumps/Avenger.jpg" alt="Card image">
								<div class="card-img-overlay">
									</br></br></br>
									<h4 class="card-title">Avenger</h4>
									<button type="button" class="btn btn-primary" disabled> Size: 1</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary"disabled> Speed: 9</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary"disabled> Range: 2</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary"disabled> Firepower: 3</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary"disabled> Cargo: 0</button>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="gridcontainer-2", "col-sm-4">
							<div class="card" id="AI2">
							<h4>AI 2 Card</h4>
								<img class="card-img-top" id="AI2Image" src="http://dcs.gla.ac.uk/~richardm/TopTrumps/Carrack.jpg" alt="Card image">
								<div class="card-img-overlay">
									</br></br></br>
									<h4 class="card-title">Carrack</h4>
									<button type="button" class="btn btn-primary"disabled> Size: 1</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary"disabled> Speed: 9</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary"disabled> Range: 2</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary"disabled> Firepower: 3</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary"disabled> Cargo: 0</button>
								</div>
							</div>
						</div>
						<div class="gridcontainer-2", "col-2">
							<div class="card" id="AI3">
							<h4>AI 3 Card</h4>
								<img class="card-img-top" id="AI3Image" src="http://dcs.gla.ac.uk/~richardm/TopTrumps/Hurricane.jpg" alt="Card image">
								<div class="card-img-overlay">
								</br></br></br>
									<h4 class="card-title">Hurricane</h4>
									<button type="button" class="btn btn-primary"disabled> Size: 1</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary"disabled> Speed: 9</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary"disabled> Range: 2</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary"disabled> Firepower: 3</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary"disabled> Cargo: 0</button>
								</div>
							</div>
						</div>
						<div></div>
						<div class="gridcontainer-2", "col-2">

							<div class="card" id="AI4">
								<h4>AI 4 Card</h4>
								<img class="card-img-top" id="AI4Image" src="http://dcs.gla.ac.uk/~richardm/TopTrumps/Hurricane.jpg" alt="Card image">
								<div class="card-img-overlay">
									</br></br></br>
									<h4 class="card-title" id="shipName">Orion</h4>
									<button type="button" class="btn btn-primary"disabled> Size: 1</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary"disabled> Speed: 9</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary"disabled> Range: 2</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary"disabled> Firepower: 3</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary"disabled> Cargo: 0</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>

			</div>


			<style>

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
				padding-left: 10%;
			}
			.gridcontainer-3{
				display: grid;
				padding: 3% 10%;
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
		</style>


		<script type="text/javascript">
			var numOpponents;
			var shipImages = ["350r.jpg", "Avenger.jpg", "Carrack.jpg", "Constellation.jpg", "Hawk.jpg", "Hornet.jpg", "Hurricane.jpg", "Merchantman.jpg", "Idris.jpg", "Orion.jpg", "Sabre.jpg", "m50.jpg"];
			var activePlayer = 0;
			var gameState = 0;
			var categorySelected = 0;
			var opponentCards = new Array();
			var buttonElements;


			// Method that is called on page load
			function initalize() {
				var query = decodeURIComponent(window.location.search);
				var queries = query.split("=");
				numOpponents = queries[1];
				buttonElements = document.querySelectorAll('[id="humanButton"]');

				setOpponentsDisplayOff();
				setOpponents();
				seeActivePlayer();
				setHumanCardImage();
				setCardImages();

				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------

				// For example, lets call our sample methods
				// helloJSONList();
				// helloWord("Student");

			}
			function setCategory(catNumber)
			{
				categorySelected = catNumber;
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
				if(activePlayer == 0)
				{
					document.getElementById("active").innerHTML = "It is your turn";
					document.getElementById("playerInformation").innerHTML = "Please choose a category by clicking on the category buttons on your card";
				}
				else {
					document.getElementById("active").innerHTML = "It is AI 1's turn";
					document.getElementById("playerInformation").innerHTML = "See the category your opponent chose";
				}
			}

			function setInformationForPlayer()
			{
				document.getElementById("active").innerHTML = "Player Chose Category 1";
				if(opponentCards.length == 1)
				{
					document.getElementById("playerInformation").innerHTML = "Your Speed Category: 2 <br /> AI 1 Speed Category: 3";
				}
				else if(opponentCards.length == 2)
				{
					document.getElementById("playerInformation").innerHTML = "Your Speed Category: 2 <br /> AI 1 Speed Category: 3 <br /> AI 2 Speed Category: 4";
				}
				else if(opponentCards.length == 3)
				{
					document.getElementById("playerInformation").innerHTML = "Your Speed Category: 2 <br /> AI 1 Speed Category: 3 <br /> AI 2 Speed Category: 4 <br /> AI 3 Speed Category: 4";
				}
				else if(opponentCards.length == 4)
				{
					document.getElementById("playerInformation").innerHTML = "Your Speed Category: 2 <br /> AI 1 Speed Category: 3 <br /> AI 2 Speed Category: 4 <br /> AI 3 Speed Category: 4 <br /> AI 4 Speed Category: 4";
				}
			}

			function seeCategoryPage()
			{
				setOpponentsDisplayOn();
				setInformationForPlayer();
				disableHumanButtons();
			}
			function seeActivePlayer()
			{
				setOpponentsDisplayOff();
				setActivePlayer();
				if (activePlayer >= 1)
				{
					disableHumanButtons();
				}
				else {
					enableHumanButtons();
				}
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
			function setCardImages()
			{

				if(numOpponents >= 1)
				{
					document.getElementById('AI1Image').src = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + shipImages[Math.floor(Math.random()*12)];
				}

				if(numOpponents >= 2)
				{
					document.getElementById('AI2Image').src = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + shipImages[Math.floor(Math.random()*12)];
				}

				if(numOpponents >= 3)
				{
					document.getElementById('AI3Image').src = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + shipImages[Math.floor(Math.random()*12)];
				}

				if(numOpponents >= 4)
				{
					document.getElementById('AI4Image').src = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + shipImages[Math.floor(Math.random()*12)];
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

		</script>

		</body>
</html>
