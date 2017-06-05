<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <title>간단한 지도 표시하기</title>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
</head>
<body>
<div id="map" style="width:26%;height:300px;"></div>
<form action="geocode.do">
<input type="text" name="address">
<input type="submit" value="search">
</form>
<script>
var msg={
	    label_date_format_yymmdd_hhmmss:'yyyy년 MM월 dd일 a/p hh시 mm분 ss초',
	    };
	     
	Date.prototype.format = function(f) {
	        if (!this.valueOf()) return " ";
	      
	        alert();
	        var weekName = ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'];
	        var d = this;
	          
	        return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
	            switch ($1) {
	                case "yyyy": return d.getFullYear();
	                case "yy": return (d.getFullYear() % 1000).zf(2);
	                case "MM": return (d.getMonth() + 1).zf(2);
	                case "dd": return d.getDate().zf(2);
	                case "E": return weekName[d.getDay()];
	                case "HH": return d.getHours().zf(2);
	                case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
	                case "mm": return d.getMinutes().zf(2);
	                case "ss": return d.getSeconds().zf(2);
	                case "a/p": return d.getHours() < 12 ? '오전' : '오후';
	                default: return $1;
	            }
	        });
	    };
	     
	    String.prototype.string = function(len)
	            {var s = '', i = 0; while (i++ < len) { s += this; } return s;};
	    String.prototype.zf = function(len)
	            {return "0".string(len - this.length) + this;};
	    Number.prototype.zf = function(len)
	            {return this.toString().zf(len);};
	     
	    function viewSearchTime(){
	        var nowTime = new Date().format(msg.label_date_format_yymmdd_hhmmss);
	        $("#searchTime").html(nowTime);
	    }

	    window.onload = function() {

		    viewSearchTime();
	    }
	    
</script>
<div id="searchTime"></div>
</body>
</html>