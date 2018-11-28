
var str = "";
var numNotes = 0;
var state = 0;


if(inputStr.addEventListener ) {
        inputStr.addEventListener('keydown',this.keyHandler,false);
        document.getElementById("inputStr").value = "test";
    } else if(inputStr.attachEvent ) {
        inputStr.attachEvent('onkeydown',this.keyHandler); /* damn IE hack */
    }
    
    document.getElementById("inputStr").value = "test";
    
function keyHandler(e) {
    var TABKEY = 9;
    if(e.keyCode == TABKEY) {
        this.value += "    ";
        if(e.preventDefault) {
            e.preventDefault();
        }
        return false;
    }
}

function changeState(btn){
    if(state == 0 && btn == 2){
        var inputStrObj = document.getElementById("inputStr");
   		str = inputStrObj.value.toString();
   		if(str){
		    var stopBtnObj = document.getElementById("stopBtn");
		    stopBtnObj.disabled = false;
		    var playBtnObj = document.getElementById("playBtn");
		    playBtn.innerHTML = "Pause <span style='letter-spacing: -5px;'>&#10074;&#10074;</span>";
		    state = 1;
		    play();
		}
    }
    else if(state == 1 && btn == 2){
        var playBtnObj = document.getElementById("playBtn");
        playBtnObj.innerHTML = "Play &#9658;";
        state = 2;
    }
    else if((state != 0 && btn == 1) || (state == 1 && btn == 0)){
        var stopBtnObj = document.getElementById("stopBtn");
        stopBtnObj.disabled = true;
        var playBtnObj = document.getElementById("playBtn");
        playBtnObj.disabled = false;
        playBtnObj.innerHTML = "Play &#9658;";
        state = 0;
        stop();
    }
    else if(state == 2 && btn == 2){
        var playBtnObj = document.getElementById("playBtn");
        playBtnObj.innerHTML = "Pause <span style='letter-spacing: -5px;'>&#10074;&#10074;</span>";
        state = 1;
        changePosition();
        app.resumeSong();
    }
}

function stop(){
    app.stopSong();

    var prevNotesObj = document.getElementById("prevNotes");
    var actualNoteObj = document.getElementById("actualNote");
    var nextNotesObj = document.getElementById("nextNotes");

    prevNotes.innerHTML = "&#9834;";
    actualNoteObj.innerHTML = "&#9834;";
    nextNotesObj.innerHTML = "&#9834;";
}

function play(){
    var inputStrObj = document.getElementById("inputStr");
    str = inputStrObj.value.toString();
    app.playSong(inputStrObj.value);
    numNotes = parseInt(app.getNumNotes());

  	changePosition();
}

function changePosition(){
    if(state == 1){
        var prevNotesObj = document.getElementById("prevNotes");
        var actualNoteObj = document.getElementById("actualNote");
        var nextNotesObj = document.getElementById("nextNotes");

        var position = parseInt(app.getPosition());
        var bpm = parseInt(app.getBpm());

        if(numNotes >= 0){
            var start = position - 10;
            if(start < 0) { start = 0; }

            var end = position + 10;
            if(end >= str.length) { end = str.length; }

            prevNotes.innerHTML = str.slice(start, position);
            actualNoteObj.innerHTML = "|" + str.substr(position, 1) + "|";
            nextNotesObj.innerHTML = str.slice(position + 1, end);

            numNotes = numNotes - 1;
            if(numNotes == 0){
                setTimeout(changeState, 1000*(60/bpm), 0);
            }
            else{
                setTimeout(changePosition, 1000*(60/bpm));
            }
        }
    }
    else{
        app.pauseSong();
    }
}

function read(){
    var inputStrObj = document.getElementById("inputStr");
    inputStrObj.value = app.readFile().toString();
}

function save(){
    var inputStrObj = document.getElementById("inputStr");
    app.saveFile(inputStrObj.value);
}
