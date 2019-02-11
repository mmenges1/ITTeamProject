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
						<div class="container">
							<div class="row">
								<div class="col-6 col-sm-12">
									<h1>Top Trumps Game Statistics</h1>
								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-md-8 col-sm-12">
									<div class="media">
										<div class="media-body">
											<h5 class="mt-0">Overall number of games played</h5>
											<p id="numOfGames" >Number of overall games played here</p></div>
										</div>
									</div>
								</div>
								<hr>
								<div class="row">
									<div class="col-md-8 col-sm-12">
										<div class="media">
											<div class="media-body">
												<h5 class="mt-0">Computer wins</h5>
												<p id="numOfCPUWins">Computer has won this many times<p></div>
											</div>
										</div>
									</div>
									<hr>
									<div class="row">
										<div class="col-md-8 col-sm-12">
											<div class="media">
												<div class="media-body">
													<h5 class="mt-0">Player wins</h5>
													<p id="numOfHumanWins">Player has won this many times</p></div>
												</div>
											</div>
										</div>
										<hr>
										<div class="row">
											<div class="col-md-8 col-sm-12">
												<div class="media">
													<div class="media-body">
														<h5 class="mt-0">Average number of draws</h5>
														<p id="aveOfGamesDrawn">Average number of draws placed here</p></div>
													</div>
												</div>
											</div>
											<hr>
											<div class="row">
												<div class="col-md-8 col-sm-12">
													<div class="media">
														<div class="media-body">
															<h5 class="mt-0">Largest number of rounds played in a single game</h5>
															<p id="highNumOfRounds">Longest single game here<p></div>
														</div>
													</div>
												</div>
												<hr>
											</div>
										</div>
									</section>
								</div>


		<style>
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
		.media-body{
			text-align: left;
		}
		</style>
		<script type="text/javascript">
			// Method that is called on page load
			function initalize() {
			
			previousGameStats();
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				// For example, lets call our sample methods
				// helloJSONList();
				// helloWord("Student");
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
			function previousGameStats(){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/previousGameStats"); // Request type and URL+parameters
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}
				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
					var temp = JSON.parse(xhr.response);
					document.getElementById("numOfGames").innerHTML = temp["numOfGames"];
					document.getElementById("numOfCPUWins").innerHTML = temp["numOfCPUWins"];
					document.getElementById("numOfHumanWins").innerHTML = temp["numOfHumanWins"];
					document.getElementById("aveOfGamesDrawn").innerHTML = temp["aveOfGamesDrawn"];
					document.getElementById("highNumOfRounds").innerHTML = temp["highNumOfRounds"];
					var responseText = xhr.response; // the text of the response
					console.log(responseText); // lets produce an alert
					populatePreviousStatsDisplay(responseText)
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}
			function populatePreviousStatsDisplay(str){
				var jsonObject = JSON.parse(str);
				const statsString = JSON.stringify(jsonObject, null, '\t');
				document.getElementById("previousStatsDisplay").innerHTML = "<pre> " + statsString + " </pre>";
			}
		</script>

		</body>
</html>
