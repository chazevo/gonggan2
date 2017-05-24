<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='css/css.css' />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=JVmBHBSdqNcd5JKBkRhO&submodules=geocoder"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script> 
<title>Insert title here</title>
<script type="text/javascript" src="js/map.js"></script>
</head>
<body class="map">

<div>
	<table width="100%">
		<tr>
			<td>
				<form action="locationsearch.do">
					<input type="text" name="keyword" value="${keyword }">
					<input type="submit" value="건물명으로 주소 검색">
				</form>
			</td>
		</tr>
		<c:if test="${!empty searchLocationList}">
		<c:forEach items="${searchLocationList}" var="i" begin="0">
		<tr>
			<td>
				<a href="javascript:getAddr('${i.address }', '${i.mapx }', '${i.mapy }');">
				${i.title}</a>
			</td>
		</tr>
		<tr>
			<td>
				<a href="javascript:getAddr('${i.address }', '${i.mapx }', '${i.mapy }');">
					${i.address}</a>
			</td>
		</tr>
		</c:forEach>
		</c:if>
	</table>
</div>


<div id="map" style="width:400px;height:400px;"></div>


<script type="text/javascript">
	var myaddress = '불정로 6';
	var map = new naver.maps.Map('map');
	
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