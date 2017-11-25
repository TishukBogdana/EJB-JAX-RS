/**
 * Created by Богдана on 29.09.2017.
 */
var app = angular.module("myApp",[]);
angular.element(document).ready(function () {
    var paint = document.getElementById('graphen');
    var context = paint.getContext("2d");
        var width = document.body.clientWidth;
    function load(x,y) {
        context.moveTo(x/2, 1);
        context.lineTo(x/2, y);
        context.moveTo(1, y/2);
        context.lineTo(x, y/2);
        context.strokeStyle = "#ffffff";
        context.stroke();
    }
       if (width >= 1187) {
           load(650,650);
        }
        if ((width >= 672)&&(width<1187)) {

            paint=document.getElementById('graphen_tabl');
            context = paint.getContext("2d");
            load(500,600);
        }
    if (width <672) {
        paint=document.getElementById('graphen_mob');
        context = paint.getContext("2d");
    load(350,350);
    }

});

app.controller('canvasController',['$scope','$window', function ($scope, $window) {
    $scope.paint=function (r){
      var width =document.body.clientWidth;
  function paint_dep(x,y,k) {
      context.clearRect(0, 0, x, y);
      context.beginPath();
      context.moveTo(x/2, y/2);
      context.lineTo(x/2, y/2 - r * k);
      context.lineTo(x/2 + r * k, y/2);
      context.closePath();
      context.fillStyle = "#ffa500";
      context.fill();
      context.rect(x/2 - r * k, y/2 - r * k, r * k, r * k);
      context.fill();
      context.moveTo(x/2, y/2);
      context.arc(x/2, y/2, r * k / 2, (1 / 2) * Math.PI, Math.PI);
      context.fill();
      context.moveTo(x/2, 1);
      context.lineTo(x/2, y);
      context.moveTo(1, y/2);
      context.lineTo(x, y/2);
      context.strokeStyle = "#ffffff";
      context.stroke();
  }

            var paint = document.getElementById('graphen');
            var context = paint.getContext("2d");
            if (width >= 1187) {
            paint_dep(650,650,100);
            }
            if((width>=672)&&(width<1187)){
                paint = document.getElementById('graphen_tabl');
                 context = paint.getContext("2d");
                paint_dep(500,600,70);

            }
        if (width < 672) {
            paint = document.getElementById('graphen_mob');
            context = paint.getContext("2d");
     paint_dep(350,350,50);
        }

    };
    $scope.listen=function (r) {
         var rad = document.forms.checker.r;
         for(var i =0;i<rad.length;i++) {
             if (i !== r) {
                 rad[i].checked = false;
             }
         }
         if(rad[r].checked){
             $scope.paint(r);
         }if(!rad[r].checked){
            $scope.paint(0);

        }
    };
 $scope.listenX=function (n) {
     var xfield =document.forms.checker.x;
     for(var i = 0;i<xfield.length;i++){
         if(i!==n){
             xfield[i].checked=false;
         }
     }
 };

 $scope.checkText=function () {
   var text = document.forms.checker.y;

     if((text.value<=-5)||(text.value>=3)||(isNaN(text.value))||(text.value==='')){
         text.style.backgroundColor="#f80040";

     }else{ text.style.backgroundColor="#ffa500";

    }
 };

}]);
function validate() {
  if(isNaN( document.forms.checker.y.value) ||(document.forms.checker.y.value==='')) {
      return false;
  }else{return true;}
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