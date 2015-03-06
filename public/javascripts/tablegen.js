window.onload = function(){
    var socket = io()
    var sendButton = document.getElementById("send")
    var ifield = document.getElementById("field")
    var datfield = document.getElementById("data")
    socket.on("update", function(message){
        var m = document.createTextNode(message['status']);
        var tr = document.createElement('tr');
        var tc = document.createElement("td");
        tc.appendChild(m);
        tr.appendChild(tc);
        datfield.appendChild(tr);
    })
    socket.on("err", function(errm){
        datfield.innerHTML = errm['err'];
    })
    socket.on("done", function(data){
        datfield.innerHTML = ""
        for(row in data['arr']){
            var tr = document.createElement('tr');
            for(col in data['arr'][row]){
                var text = document.createTextNode(data['arr'][row][col]);
                var tc = document.createElement("td");
                tc.appendChild(text)
                tr.appendChild(tc)
            }
            datfield.appendChild(tr)
        }
    })
    sendButton.onclick = function(){
        var data = ifield.value
        socket.emit('query', {q: data})
        datfield.innerHTML = ""
    }
}
