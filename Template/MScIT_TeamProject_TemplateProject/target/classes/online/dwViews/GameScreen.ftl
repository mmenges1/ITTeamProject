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
							<div class="alert alert-info" id="player-info">
								<strong>Who's turn is it?</strong>
								<p>Information we need to provide the player</p>
							</div>
						</section>
						<div class="gridcontainer", "col-sm-4">
							<div class="card" id="Human">
								<img class="card-img-top" src="http://dcs.gla.ac.uk/~richardm/TopTrumps/350r.jpg" alt="Card image">
								<div class="card-img-overlay">
									<h4 class="card-title">350r</h4>
									<button type="button" class="btn btn-primary"> Size: 1</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary"> Speed: 9</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary"> Range: 2</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary"> Firepower: 3</button>
									<section> &nbsp;</section>
									<button type="button" class="btn btn-primary"> Cargo: 0</button>
								</div>
							</div>
						</div>

						<div class="gridcontainer", "col-sm-4">
							<div class="card" id="AI1">
								<img class="card-img-top" src="http://dcs.gla.ac.uk/~richardm/TopTrumps/Avenger.jpg" alt="Card image">
								<div class="card-img-overlay">
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
								<img class="card-img-top" src="http://dcs.gla.ac.uk/~richardm/TopTrumps/Carrack.jpg" alt="Card image">
								<div class="card-img-overlay">
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
								<img class="card-img-top" src="http://dcs.gla.ac.uk/~richardm/TopTrumps/Hurricane.jpg" alt="Card image">
								<div class="card-img-overlay">
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
								<img class="card-img-top" src="http://dcs.gla.ac.uk/~richardm/TopTrumps/Orion.jpg" alt="Card image">
								<div class="card-img-overlay">
									<h4 class="card-title">Orion</h4>
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
				height:400px;
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
			// Method that is called on page load
			function initalize() {
				var query = decodeURIComponent(window.location.search);
				var queries = query.split("=");
				numOpponents = queries[1];

				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				if(numOpponents == 1)
				{
				 document.getElementById('AI4').remove();
				 document.getElementById('AI3').remove();
				 document.getElementById('AI2').remove();
				}
				else if(numOpponents == 2)
				{
					document.getElementById('AI4').remove();
 					document.getElementById('AI3').remove();
				}
				else if(numOpponents == 3)
				{
					document.getElementById('AI4').remove();
				}

				// For example, lets call our sample methods
				helloJSONList();
				helloWord("Student");

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
