<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=JVmBHBSdqNcd5JKBkRhO&submodules=geocoder"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script> 
<script src="js/map.js"></script> 
<script type="text/javascript">
var xVal, yVal;

function getCord(){
	$.ajax({
	      url: "/gonggan/geocode.do",
	      data: {address : $("#address").val()},
	      success: function(data) {
	    	  callback(data);
	      },
	      error: function(data,status,error){
	         console.log("error : " + error);
	      }
	   });
}
 
 function mapImg() {
	 $.ajax({
		 url:"/gonggan/mapImg.do",
		 success:function(data){}
		 
	 });
 }
 
 function callback(data){

		var jsonObj = JSON.stringify(data);
		var jsonArr = JSON.parse(jsonObj);
		xVal = jsonArr.x;
		yVal = jsonArr.y;
		console.log(xVal);
		var map = new naver.maps.Map('map',{
			center: new naver.maps.LatLng(xVal,yVal),
			zoom: 10
			
		});
			console.log(xVal);
} 
 function result(){
	 
 }
 
 function building() {
		$.ajax({
		      url: "/gonggan/locationsearch.do",
		      data: {keyword : $("#address").val()},
		      success: function(data) {
		    	  //callback(data);
		      },
		      error: function(data,status,error){
		         console.log("error : " + error);
		      }
		   });
 }
</script>
</head>
<body>
<div id="map" style="width:100%;height:400px;"></div>
<input type="text" id="address">
<button onclick='getAddr();'>좌표로 주소검색</button>
<button onclick='result();'>확인</button>
<button onclick='building();'>건물명으로 주소 검색</button>
<script type="text/javascript">

var map = new naver.maps.Map('map');

var myaddress = '불정로 6';

 naver.maps.Service.geocode({address: myaddress}, function(status, response) {
	    if (status !== naver.maps.Service.Status.OK) {
	        return alert(myaddress + '의 검색 결과가 없거나 기타 네트워크 에러');
	    }
	    var result = response.result;
	    // 검색 결과 갯수: result.total
	    // 첫번째 결과 결과 주소: result.items[0].address
	    // 첫번째 검색 결과 좌표: result.items[0].point.y, result.items[0].point.x
	    var myaddr = new naver.maps.Point(result.items[0].point.x, result.items[0].point.y);
	    map.setCenter(myaddr); // 검색된 좌표로 지도 이동
	    // 마커 표시
/* 		    var marker = new naver.maps.Marker({
	      position: myaddr,
	      map: map
	    }); */
	    var markerOptions = {
		        position: myaddr,
		        map: map,
		        icon: {
		            url: './images/heart_r.png',
		            size: new naver.maps.Size(22, 35),
		            origin: new naver.maps.Point(0, 0),
		            anchor: new naver.maps.Point(11, 35)
		        }
		    };
			var marker = new naver.maps.Marker(markerOptions);
			
	    // 마커 클릭 이벤트 처리
	    naver.maps.Event.addListener(marker, "click", function(e) {
	      if (infowindow.getMap()) {
	          infowindow.close();
	      } else {
	          infowindow.open(map, marker);
	      }
	    });
	    // 마크 클릭시 인포윈도우 오픈
	    var infowindow = new naver.maps.InfoWindow({
	        content: '<h4> [네이버 개발자센터]</h4><a href="https://developers.naver.com" target="_blank"><img src="https://developers.naver.com/inc/devcenter/images/nd_img.png"></a>'
	    });
	});

</script>
</body>
</html>