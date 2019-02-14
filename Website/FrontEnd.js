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
var currentNumPlayers;
var humanLost = false;
// Method that is called on page load
function initalize() {
	buttonElements = document.querySelectorAll('[id="humanButton"]');
	setUpGame();
}
function displayEndGame(points)
{
	document.getElementById('EndGameView').display = 'block';
	if(document.getElementById('Human') != null){
		document.getElementById('Human').remove();
	}
	if(document.getElementById('AI1') != null){
		document.getElementById('AI1').remove();
	}
	if(document.getElementById('AI2') != null){
		document.getElementById('AI2').remove();
	}
	if(document.getElementById('AI3') != null){
		document.getElementById('AI3').remove();
	}
	if(document.getElementById('AI4') != null){
		document.getElementById('AI4').remove();
	}
	document.getElementById('activePlayer').remove();

	if(humanLost){
		document.getElementById('EndGame').innerHTML =	"</br></br><strong>You Lost!!</strong><hr>The Overall Winner Was: "+points[0].winner+"</br><hr>You won "+points[0].human+" rounds</br><hr>";
	}
	else {
		document.getElementById('EndGame').innerHTML =	"</br></br><hr>The Overall Winner Was: "+points[0].winner+"</br><hr>You won "+points[0].human+" rounds</br><hr>";
	}

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
function disableHumanButtons(){
	for (i = 0; i < buttonElements.length; i++){
		buttonElements[i].disabled = true;
	}
}
function enableHumanButtons(){
	for (i = 0; i < buttonElements.length; i++){
		buttonElements[i].disabled = false;
	}
}

function setActivePlayer(){
	nextPlayerHuman();
	//Step 3
	displayCards();
}

function setInformationForPlayer(attributeList, winnerName, draw, turnStats){
	document.getElementById('active').innerHTML =currentPlayer+ " chose " + attributeList +" this round.";

	if(draw.includes("true")){
		document.getElementById("playerInformation").innerHTML = "This round is a draw!      |      Community Pile has " + turnStats[0].communitySize + " cards <br>";
	}
	else {
		document.getElementById("playerInformation").innerHTML = winnerName +" won this round.     |      Community Pile has " + turnStats[0].communitySize + " cards <br>";
	}
}

function setRoundInformation(turnStats, winnerName, draw){
	if(draw.includes("false")){
		if(winnerName.includes("You")){
			document.getElementById('Human').style["boxShadow"] = "0 0 10px #4d0026";
		}
		else if(winnerName.includes("AI 1")){
			document.getElementById('AI1').style["boxShadow"] = "0 0 15px #4d0026";
		}
		else if(winnerName.includes("AI 2")){
			document.getElementById('AI2').style["boxShadow"] = "0 0 5px #4d0026";
		}
		else if(winnerName.includes("AI 3")){
			document.getElementById('AI3').style["boxShadow"] = "0 0 5px #4d0026";
		}
		else // if(winnerName.includes("AI 4"))
		{
			document.getElementById('AI4').style["boxShadow"] = "0 0 5px #4d0026";
		}
	}
	if(document.getElementById('Human') != null){
		document.getElementById("playerInformation").innerHTML +=  "     Your " + attributeList[catString]+" was: "+gameState[0][0].attributes[catString];
		console.log(turnStats[0].playerHandSizes[currentNumPlayers]);
		document.getElementById("humanDeckSize").innerHTML = "Deck Size: " + turnStats[0].playerHandSizes[currentNumPlayers];
		currentNumPlayers++;
	}
	if(numOpponents >= 1){
		if(document.getElementById('AI1') != null)
		{
			document.getElementById("playerInformation").innerHTML +=  "  |   AI 1's " + attributeList[catString]+" was: "+gameState[1][0].attributes[catString];
			document.getElementById("AI1DeckSize").innerHTML = "Deck Size: " + turnStats[0].playerHandSizes[currentNumPlayers];
			currentNumPlayers++;
		}
	}
	if(numOpponents >= 2){
		if(document.getElementById('AI2') != null)
		{
			document.getElementById("playerInformation").innerHTML += "   |  AI 2's " + attributeList[catString]+" was: "+gameState[2][0].attributes[catString];
			document.getElementById("AI2DeckSize").innerHTML = "Deck Size: " + turnStats[0].playerHandSizes[currentNumPlayers];
			currentNumPlayers++;
		}
	}
	if(numOpponents >= 3){
		if(document.getElementById('AI3') != null)
		{
			document.getElementById("playerInformation").innerHTML += " |  AI 3's " + attributeList[catString]+" was: "+gameState[3][0].attributes[catString];
			document.getElementById("AI3DeckSize").innerHTML = "Deck Size: " + turnStats[0].playerHandSizes[currentNumPlayers];
			currentNumPlayers++;
		}
	}
	if (numOpponents >= 4){
		if(document.getElementById('AI4') != null)
		{
			document.getElementById("playerInformation").innerHTML += "  |  AI 4's " + attributeList[catString]+" was: "+gameState[4][0].attributes[catString];
			document.getElementById("AI4DeckSize").innerHTML = "Deck Size: " + turnStats[0].playerHandSizes[currentNumPlayers];
			currentNumPlayers++;
		}
	}
}


function seeCategoryPage(){
	document.getElementById('catButton').style.display = 'none';
	document.getElementById('roundButton').style.display = 'block';
	displayOpponentCards();
	disableHumanButtons();
}
function seeActivePlayer(){
	//Step 2
	setActivePlayer();
	document.getElementById('catButton').style.display = 'block';
	document.getElementById('roundButton').style.display = 'none';
	displayHumanCards();
	setOpponentsDisplayOff();
}

function displayOpponentCards(){
	setOpponentsDisplayOn();
}

function displayHumanCards(){

}

function setOpponents(){
	if(numOpponents == 1){
		document.getElementById('AI4').remove();
		document.getElementById('AI3').remove();
		document.getElementById('AI2').remove();
		opponentCards[0] = document.getElementById('AI1');
	}
	else if(numOpponents == 2){
		document.getElementById('AI4').remove();
		document.getElementById('AI3').remove();
		opponentCards[0] = document.getElementById('AI1');
		opponentCards[1] = document.getElementById('AI2');
	}
	else if(numOpponents == 3){
		document.getElementById('AI4').remove();
		opponentCards[0] = document.getElementById('AI1');
		opponentCards[1] = document.getElementById('AI2');
		opponentCards[2] = document.getElementById('AI3');
	}
	else{
		opponentCards[0] = document.getElementById('AI1');
		opponentCards[1] = document.getElementById('AI2');
		opponentCards[2] = document.getElementById('AI3');
		opponentCards[3] = document.getElementById('AI4');
	}
}

function setOpponentsDisplayOff(){
	for (i = 0; i < opponentCards.length; i++) {
		opponentCards[i].style.display = "none";
	}
}

function setOpponentsDisplayOn(){
	for (i = 0; i < opponentCards.length; i++) {
		opponentCards[i].style.display = "block";
	}
}
function setHumanCardImage(){
	document.getElementById('humanImage').src = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + shipImages[Math.floor(Math.random()*12)];
}
function setCardImages(temp){
	if(temp.hasOwnProperty("You")){
		var you = JSON.parse(JSON.stringify(temp["You"]));
		humanCardNameVar = you[0].name;
		gameState[0] = you;
		document.getElementById('humanCardName').innerHTML = humanCardNameVar;
		document.getElementById('Human').style["boxShadow"] = "0 0 0px #ffffff";
		setHumanCardImage();
		var catButtonArray = document.getElementsByClassName('hcat');

		for(i = 0; i < 5; i++){
			catButtonArray[i].innerHTML = you[0].criterias[i]+": " + you[0].attributes[i];
		}
	}
	else {
		if(document.getElementById('Human') != null){
			document.getElementById('Human').remove();
			document.getElementById('roundButton').remove();
			document.getElementById('catButton').remove();
			humanLost = true;
			automateRounds();
		}
	}

	if(numOpponents >= 1){
		if(temp.hasOwnProperty("AI 1")){
			var ai1 = JSON.parse(JSON.stringify(temp["AI 1"]));
			gameState[1] = ai1;
			AI1CardNameVar = ai1[0].name;
			document.getElementById('AI1Image').src = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + shipImages[Math.floor(Math.random()*12)];
			document.getElementById('AI1CardName').innerHTML = AI1CardNameVar;
			document.getElementById('AI1').style["boxShadow"] = "0 0 0px #ffffff";
			catButtonArray = document.getElementsByClassName('cat1');

			for(i = 0; i < 5; i++){
				catButtonArray[i].innerHTML = ai1[0].criterias[i]+": " + ai1[0].attributes[i];
			}
		}
		else {
			if(document.getElementById('AI1') != null){
				document.getElementById('AI1').remove();
			}
		}
	}
	if(numOpponents >= 2){
		if(temp.hasOwnProperty("AI 2")){
			var ai2 = JSON.parse(JSON.stringify(temp["AI 2"]));
			gameState[2] = ai2;
			AI2CardNameVar = ai2[0].name;
			document.getElementById('AI2Image').src = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + shipImages[Math.floor(Math.random()*12)];
			document.getElementById('AI2CardName').innerHTML = AI2CardNameVar;
			document.getElementById('AI2').style["boxShadow"] = "0 0 0px #ffffff";
			catButtonArray = document.getElementsByClassName('cat2');

			for(i = 0; i < 5; i++){
				catButtonArray[i].innerHTML = ai2[0].criterias[i]+": " + ai2[0].attributes[i];
			}
		}
		else {
			if(document.getElementById('AI2') != null){
				document.getElementById('AI2').remove();
			}
		}
	}
	if(numOpponents >= 3){
		if(temp.hasOwnProperty("AI 3")){
			var ai3 = JSON.parse(JSON.stringify(temp["AI 3"]));
			gameState[3] = ai3;
			AI3CardNameVar = ai3[0].name;
			document.getElementById('AI3Image').src = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + shipImages[Math.floor(Math.random()*12)];
			document.getElementById('AI3CardName').innerHTML = AI3CardNameVar;
			document.getElementById('AI3').style["boxShadow"] = "0 0 0px #ffffff";
			catButtonArray = document.getElementsByClassName('cat3');
			for(i = 0; i < 5; i++){
				catButtonArray[i].innerHTML = ai3[0].criterias[i]+": " + ai3[0].attributes[i];
			}
		}
		else {
			if(document.getElementById('AI3') != null){
				document.getElementById('AI3').remove();
			}
		}
	}
	if(numOpponents >= 4){
		if(temp.hasOwnProperty("AI 4")){
			var ai4 = JSON.parse(JSON.stringify(temp["AI 4"]));
			gameState[4] = ai4;
			AI4CardNameVar = ai4[0].name;
			document.getElementById('AI4Image').src = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + shipImages[Math.floor(Math.random()*12)];
			document.getElementById('AI4CardName').innerHTML = AI4CardNameVar;
			document.getElementById('AI4').style["boxShadow"] = "0 0 0px #ffffff";
			catButtonArray = document.getElementsByClassName('cat4');

			for(i = 0; i < 5; i++){
				catButtonArray[i].innerHTML = ai4[0].criterias[i]+": " + ai4[0].attributes[i];
			}
		}
		else {
			if(document.getElementById('AI14') != null){
				document.getElementById('AI4').remove();
			}
		}
	}
}
