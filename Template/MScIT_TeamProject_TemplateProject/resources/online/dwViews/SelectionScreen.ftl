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
							 <div class="col-12">
									 <h1>Top Trumps</h1>
									 <p>&nbsp;</p>
									 <p><div class="container-instruct">Please choose the number of opponents you would like to play against to start the game.</div></a>
																<a role="button" id="button" onclick="setNumberOpponents(1)" class="btn btn-primary" >1 Opponent</a>
																<a role="button" id="button" onclick="setNumberOpponents(2)" class="btn btn-primary" >2 Opponents</a>
																<a role="button" id="button" onclick="setNumberOpponents(3)" class="btn btn-primary" >3 Opponents</a>
																<a role="button" id="button" onclick="setNumberOpponents(4)" class="btn btn-primary" >4 Opponents</a>
													</div>
												</div>
											</div>
									 </br></br></br></br>
								 <a class="btn btn-primary" href="toptrumps/stats" role="button">Previous Game Statistics</a></p>
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
		.container-instruct{
			height: 50px;
			border: 1px solid transparent;
		}
		#button{
				color: #ffffff;
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
		.btn-primary {
		  color: #ffffff;
		  background-color: #4d0026;
		  border-color: #4d0026;
		}

		.btn-primary:hover {
		  color: #fff;
		  background-color: #660033;
		  border-color: #660033;
		}
		.dropdown {
			position: relative;
		}

		</style>


		<script type="text/javascript">
			var numberOpponents = 4;
			// Method that is called on page load
			function initalize() {

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
			function setNumberOpponents(num)
			{
				numberOpponents = num;
				setUpGame(num);
			}
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
//		STEP 1 - /setUpGame - set up game and select the number of AI players
//		This is NOT safe to call in the middle of a game, it will restart
		function setUpGame(num){
						numberOfPlayers = num;
						var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/setUpGame?numberOfPlayers="+numberOfPlayers); // Request type and URL+parameters

						// Message is not sent yet, but we can check that the browser supports CORS
						if (!xhr) {
							alert("CORS not supported");
						}

						// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
						// to do when the response arrives
						xhr.onload = function(e) {
							var responseText = xhr.response; // the text of the response
							console.log(responseText);
							window.location.href = "../toptrumps/game";
						};

						// We have done everything we need to prepare the CORS request, so send it
						xhr.send();
					}
		

		</script>

		</body>
</html>
