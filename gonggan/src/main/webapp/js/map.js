var image, title;

function selectSubmit() {
	$("#form").submit();
}

function getAddr(adress, mapx, mapy, title_) {

	myaddress = adress;
 
	naver.maps.Service.geocode({address: myaddress}, function(status, response) {
		
		if (status !== naver.maps.Service.Status.OK)
			return alert(myaddress + '의 검색 결과가 없거나 기타 네트워크 에러');
		
		var result = response.result;
		// 검색 결과 갯수: result.total
		// 첫번째 결과 결과 주소: result.items[0].address
		// 첫번째 검색 결과 좌표: result.items[0].point.y, result.items[0].point.x
		
		var myaddr = new naver.maps.Point(result.items[0].point.x, result.items[0].point.y);
		map.setCenter(myaddr); // 검색된 좌표로 지도 이동
		
		// 마커 표시
		var markerOptions = {
				position: myaddr,
				map: map,
				icon: {
					url: './images/marker.png',
		            size: new naver.maps.Size(70, 100),
		            origin: new naver.maps.Point(0, 0),
		            anchor: new naver.maps.Point(11, 35)
				}
		};
		var marker = new naver.maps.Marker(markerOptions);
		
		// 마커 클릭 이벤트 처리
		naver.maps.Event.addListener(marker, "click", function(e) {
			if (infowindow.getMap()) infowindow.close();
			else infowindow.open(map, marker);
	    });
		
	    // 마크 클릭시 인포윈도우 오픈
		var infowindow = new naver.maps.InfoWindow({
			content: '<button type="button" id="searchBtn" style="display:block;float:right;" onclick="selectMap(image);">추 가 하 기</button>'
		});
	});
	
	mapImg(mapx, mapy);
	title = title_;
}

function mapImg(mapx, mapy) {
	$.ajax({
		url:"/gonggan/mapImg.do",
		data: {mapx:mapx,
			mapy:mapy},
		success:function(data){
			image = data;
		}
	});
}


function selectMap(image, title) {

	parent.recieveMap(image, title);
	
	parent.$.fancybox.close();
}

 