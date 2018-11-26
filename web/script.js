
var str = "";

function play(){
    var inputStrObj = document.getElementById("inputStr");

    str = inputStrObj.value.toString();
    app.playSong(inputStrObj.value);

    changePosition();
}

function changePosition(){
    var prevNotesObj = document.getElementById("prevNotes");
    var actualNoteObj = document.getElementById("actualNote");
    var nextNotesObj = document.getElementById("nextNotes");

    var position = parseInt(app.getPosition());
    var bpm = parseInt(app.getBpm());

    if(position < str.length){
        var start = position - 10;
        if(start < 0) { start = 0; }

        var end = position + 10;
        if(end >= str.length) { end = str.length - 1; }

        prevNotes.innerHTML = str.slice(start, position);
        actualNoteObj.innerHTML = str.substr(position, 1);
        nextNotesObj.innerHTML = str.slice(position + 1, end);

        setTimeout(changePosition, 1000*(60/bpm));
    }
}

function pause(){

}

function stop(){

}

function read(){

}

function save(){

}
