
function run(){
var data = [ "0:12 47", "1:47 9", "2:9 20", "3:20 49", "4:49 16", "5:16 24", "6:24 32", "7:32 34", "8:34 16", "9:1 2", "10:16 13", "11:13 43", "12:0 43", "13:10 11", "14:43 39", "15:39 26", "16:4 5 8 10", "17:26 39", "18:39 19", "19:18 34", "20:2 3", "21:34 28", "22:28 42", "23:42 32", "24:5 6", "25:32 37", "26:15 17", "27:37 28", "28:21 22 27 29", "29:28 46", "30:46 33", "31:33 48", "32:6 7 23 25", "33:30 31", "34:7 8 19 21", "35:48 45", "36:45 41", "37:25 27", "38:41 42", "39:14 15 17 18", "40:42 44", "41:36 38", "42:22 23 38 40", "43:11 12 14", "44:40 48", "45:35 36", "46:29 30", "47:0 1", "48:31 35 44", "49:3 4" ];
var canvas = document.getElementById('myCanvas');
var context = canvas.getContext('2d');
var len = data.length;
context.beginPath();
for (var i = 0; i < len; i++) {
	var out = "";
    var temp = data[i];
	var firstSplit = temp.split(":");
	var base = (firstSplit[0].trim());
	var edges = firstSplit[1];
	//second split
	//var baseCoY = getCoOrdinate(base, len);
	//var baseCoX = base+2;
	var r = 300;
	var angle = 180/len;
	
	
	var baseCoX = getCoOrdinate( angle,  base, true);
	var baseCoY = getCoOrdinate( angle,  base,false);
	context.moveTo(baseCoX, baseCoY);	
	context.arc(baseCoX, baseCoY, 15, 0, 2 * Math.PI, false);
	context.fillText(base, baseCoX, baseCoY);
	context.stroke();
	var secondSplit = edges.split(" ");	
	for(var j=0;j<secondSplit.length;j++){
				
		var edge =(secondSplit[j].trim());		
		var edgeCoX = getCoOrdinate( angle,  edge, true);
		var edgeCoY = getCoOrdinate( angle,  edge, false);
		context.lineTo(edgeCoX, edgeCoY);
		context.stroke();
	}
	out+=":"+baseCoX+ baseCoY;
	console.log(out);
    //Do something
}
}
function getCoOrdinate( angle,  val, isX){
	
	var r = 300;
if(isX==true){
	out = r*(Math.sin(toDegrees(angle*(val+1))))+490;
}else{
	out = r*(Math.cos(toDegrees(angle*(val+1))))+480;
}	
	
	return out;
	if(val <= (len/5)){
		return 0;
	}
	if(val <= (len*2/5)){
		return 100;
	}
	if(val <= (len*3/5)){
		return 200;
	}
	if(val <= (len*4/5)){
		return 300;
	}
	return 400;
}
function toDegrees (angle) {
  return angle * (180 / Math.PI);
}
run();