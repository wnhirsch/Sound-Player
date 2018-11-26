
var str = "";
var numNotes = 0;

function play(){
    var playBtnObj = document.getElementById("playBtn");
    var stopBtnObj = document.getElementById("stopBtn");
    playBtnObj.disabled = true;
    stopBtnObj.disabled = false;

    var inputStrObj = document.getElementById("inputStr");
    str = inputStrObj.value.toString();
    app.playSong(inputStrObj.value);
    numNotes = parseInt(app.getNumNotes());

    changePosition();
}

function changePosition(){
    var prevNotesObj = document.getElementById("prevNotes");
    var actualNoteObj = document.getElementById("actualNote");
    var nextNotesObj = document.getElementById("nextNotes");

    var position = parseInt(app.getPosition());
    var bpm = parseInt(app.getBpm());

    if(numNotes > 0){
        var start = position - 10;
        if(start < 0) { start = 0; }

        var end = position + 10;
        if(end >= str.length) { end = str.length; }

        prevNotes.innerHTML = str.slice(start, position);
        actualNoteObj.innerHTML = str.substr(position, 1);
        nextNotesObj.innerHTML = str.slice(position + 1, end);

        numNotes = numNotes - 1;
        if(numNotes == 0){
            var playBtnObj = document.getElementById("playBtn");
            var stopBtnObj = document.getElementById("stopBtn");
            playBtnObj.disabled = false;
            stopBtnObj.disabled = true;
        }
        setTimeout(changePosition, 1000*(60/bpm));
    }
}

function stop(){

}

function read(){
    var inputStrObj = document.getElementById("inputStr");
    inputStrObj.value = app.readFile().toString();
}

function save(){
    app.saveFile();
}
