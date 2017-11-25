/**
 * Created by Богдана on 29.09.2017.
 */
function load() {
    var paint = document.getElementById('graphen');
    if(paint && paint.getContext){
        var context = paint.getContext("2d");

        //context.moveTo(250,1);
      //  context.lineTo(250,500);
        context.moveTo(1, 250);
        context.lineTo(500,250);
        context.strokeStyle="#ffffff";
        context.stroke();
    }
    else{alert("NO");}
}
function paint(r){
    var paint= document.getElementById('graphen');
    if(paint && paint.getContext) {
        var context = paint.getContext("2d");
        context.clearRect(0,0,607,505);
        context.moveTo(300,1);
        context.lineTo(300,500);
        context.moveTo(1, 350);
        context.lineTo(600,350);
        context.beginPath();
        context.moveTo(300, 350);
        context.lineTo(300, 350 - r);
        context.lineTo(300 + r / 2, 350);
        context.closePath();
        context.fillStyle = "#33ff99";
        context.fill();
        context.rect(300 - r / 2, 350 - r, r / 2, r);
        context.fill();
        context.moveTo(300, 350);
        context.arc(300, 350, r / 2, (1 / 2) * Math.PI, Math.PI);
        context.fill();
        context.moveTo(300,1);
        context.lineTo(300,500);
        context.moveTo(1, 350);
        context.lineTo(600,350);
        context.strokeStyle="#ffffff";
        context.stroke();
    }else{alert("NO");}
}

function clicked(arg){
    var req = new XMLHttpRequest();

    var elem = document.getElementById(arg);
    var br = elem.getBoundingClientRect();
    var left = br.left;
    var top = br.top;
    var event = window.event;
    var x = event.clientX-left;
    var y = event.clientY-top;
    var form = document.forms.myform;
    var select = form.elements.r;
    if(select.value!=''){
    var formdata = new FormData();
    formdata.append('x',1);
        formdata.append('y',1);
        formdata.append('r',100);
        req.open("POST", "http://localhost:3377/pip.laba.servlet-1.0/control",true);
      req.setRequestHeader("Content-type","application/x-www-form-urlencoded");

        req.onreadystatechange =   function reqReadyStateChange(){
            if (req.readyState == 4) {
              var resp =   req.responseText;
              var paint = document.getElementById('graphen');
              var context = paint.getContext("2d");
             if(resp==='false'){
                 context.strokeStyle="#ff0000";
             }
                if(resp==='true'){
                    context.strokeStyle="#ffff00";
                }
                context.moveTo(x,y);
                context.arc(x,y,1,0,2*Math.PI);
                context.stroke();

            }
        };
        req.send("x="+x+"&y="+y+"&r="+select.value*100);

    }else{
        alert("Radius should be defined");
    }}