window.onload = function(){
    var socket = io()
    var sendButton = document.getElementById("send")
    var ifield = document.getElementById("inputfield")
    var datfield = document.getElementById("data")
    var statfield = document.getElementById("status")
    var sub = function(){
        var data = ifield.value
        if(data.match(/(hr|hres|sr|hjres|sjres|hconres|sconres)\d*\-\d{2,}/g) === null){
            socket.emit('search', {s: data})
        } else {
            socket.emit('query', {q: data})
        }
        datfield.innerHTML = ""
    }
    socket.on("update", function(message){
        var m = document.createTextNode(message['status']);
        var tr = document.createElement('tr');
        var tc = document.createElement("td");
        tc.appendChild(m);
        tr.appendChild(tc);
        statfield.appendChild(tr);
    })
    socket.on("err", function(errm){
        datfield.innerHTML = errm['err'];
    })
    socket.on("done", function(data){
        datfield.innerHTML = ""
        var content = null
        for(row in data['arr']){
            if(row == 0){
                content = document.createElement('thead');
            } else if(row == 1){
                content = document.createElement('tbody');
            }
            var tr = document.createElement('tr');
            for(col in data['arr'][row]){
                var text = document.createTextNode(data['arr'][row][col]);
                var tc = document.createElement("td");
                tc.appendChild(text)
                tr.appendChild(tc)
            }
            if(row == 0){
                content.appendChild(tr);
                datfield.appendChild(content);
            } else{
                content.appendChild(tr);
            }
        }
        datfield.appendChild(content);
    })
    sendButton.onclick = function(){
        sub()
    }
    ifield.onkeypress = function(e){
        if(e.which == 13){
            sub()
        }
    }
}
