
var str = "";
var numNotes = 0;
var state = 0;

function changeState(btn){
    if(state == 0 && btn == 2){
        var firstBtnObj = document.getElementById("firstBtn");
        firstBtnObj.disabled = false;
        firstBtnObj.innerHTML = "Pause";
        var sndBtnObj = document.getElementById("sndBtn");
        sndBtnObj.disabled = true;
        sndBtnObj.innerHTML = "Resume";
        state = 1;
        play();
    }
    else if(state == 1 && btn == 1){
        var firstBtnObj = document.getElementById("firstBtn");
        firstBtnObj.innerHTML = "Stop";
        var sndBtnObj = document.getElementById("sndBtn");
        sndBtnObj.disabled = false;
        state = 2;
    }
    else if((state == 2 && btn == 1) || (state == 1 && btn == 0)){
        var firstBtnObj = document.getElementById("firstBtn");
        firstBtnObj.disabled = true;
        firstBtnObj.innerHTML = "Stop";
        var sndBtnObj = document.getElementById("sndBtn");
        sndBtnObj.disabled = false;
        sndBtnObj.innerHTML = "Play";
        state = 0;
        stop();
    }
    else if(state == 2 && btn == 2){
        var firstBtnObj = document.getElementById("firstBtn");
        firstBtnObj.innerHTML = "Pause";
        var sndBtnObj = document.getElementById("sndBtn");
        sndBtnObj.disabled = true;
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

    prevNotes.innerHTML = ".";
    actualNoteObj.innerHTML = ".";
    nextNotesObj.innerHTML = ".";
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
            actualNoteObj.innerHTML = str.substr(position, 1);
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
    app.saveFile();
}
