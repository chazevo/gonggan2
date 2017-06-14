var start = 1;
var display = 8;
var maxRownum;
var Ca = /\+/g;

function changeCategory() {
	$("input[name=keyword]").val("");
	$("input[name=keyword]").focus();
	while(document.getElementById("listbody").rows.length > 0)
		document.getElementById("listbody").deleteRow(0);
}

function selectMovie(image, title, director , actor, pubDate) {
	
	parent.recieveMovie(image, title, director , actor, pubDate);
	
	parent.$.fancybox.close();
}

function selectNews(title, originallink, description, pubDate) {
	
	parent.recieveNews(title, originallink, description, pubDate);
	
	parent.$.fancybox.close();
}
function selectBook(image, title, author, publisher, pubdate) {
	
	parent.recieveBook(image, title, author, publisher, pubdate);
	
	parent.$.fancybox.close();
}

function selectMusic(videoId, title, thumbnail) {
	
	parent.recieveMusic(videoId, title, thumbnail);

	parent.$.fancybox.close();
}

function selectSubmit(){
	
	if ($("input[name=keyword]").val() == "") {
		alert("키워드를 입력해주세요! ");
		return;
	}
	
	while(document.getElementById("listbody").rows.length > 0)
		document.getElementById("listbody").deleteRow(0);

	document.getElementById('submit').action = assignUrl();
	document.getElementById('submit').submit();

	requestList(start = 1);
	
}

function assignUrl() {

	var url;
	
	if(categoryval == 0)
		url = "booksearch.do";
		//url = "booksearch2.do";
	else if(categoryval == 1)
		url = "moviesearch.do";
		//url = "moviesearch2.do";
	else if(categoryval == 2)
		url = "musicpost.do";
	else if(categoryval == 3)
		url = "넘길 주소";
	else if(categoryval == 4)
		url = "newssearch.do";
	
	return url;
}

function requestList(start) {
	
	$.ajax({
		url: assignUrl(),
		data: {
			start: start,
			display: display,
			keyword:$("input[name=keyword]").val()
		},
		success: function(data) {
			start = start + display;
			callbackList(data);
		},
		error: function(data,status,error) {
			console.log("error : " + error);
		}
	});
	
}

function callbackList(data) {

	var jsonObj = JSON.stringify(data);
	var jsonArr = JSON.parse(jsonObj);
	
	var tr, td, table, colgroup, col, tbody;
	var innerTr, innerTd, innerTh, span, a;
	
	var title, director, actor, pubDate, image;
	
	for (var i in jsonArr.list) {
		
		title = decodeURIComponent((jsonArr.list[i].title).replace(Ca, " "));
		director = decodeURIComponent((jsonArr.list[i].director).replace(Ca, " "));
		actor = decodeURIComponent((jsonArr.list[i].actor).replace(Ca, " "));
		pubDate = decodeURIComponent((jsonArr.list[i].pubDate).replace(Ca, " "));
		image = jsonArr.list[i].image;
		
		tr = document.createElement("tr");
		td = document.createElement("td");
		td.style.textAlign = "center";
		table = document.createElement("table");
		table.className = "categorySearch movie";
		
		tbody = document.createElement("tbody");
		
		innerTr = document.createElement("tr");
		innerTd = document.createElement("td");
		innerTd.rowSpan = '5';
		a = document.createElement("a");
		a.href = "javascript:void();";
		a.onclick = function() { 
			selectMovie(image, title, director, actor, pubDate);
		};
		a.innerHTML = "<img height='100%' width='100%' src='" + image + "'>";
		innerTd.appendChild(a);
		innerTr.appendChild(innerTd);
		
		innerTh = document.createElement("th");
		span = document.createElement("span");
		a = document.createElement("a");
		a.href = "javascript:void();";
		a.onclick = function() { 
			selectMovie(image, title, director, actor, pubDate);
		};
		a.innerHTML = "<b style='font-size:140%'>" + title + "</b></a>";
		span.appendChild(a);
		innerTh.appendChild(span);
		innerTr.appendChild(innerTh);
		tbody.appendChild(innerTr);
		
		innerTr = document.createElement("tr");
		innerTd = document.createElement("td");
		innerTd.innerHTML = director + "&nbsp;"
		innerTr.appendChild(innerTd);
		tbody.appendChild(innerTr);

		innerTr = document.createElement("tr");
		innerTd = document.createElement("td");
		innerTd.innerHTML = actor + "&nbsp;"
		innerTr.appendChild(innerTd);
		tbody.appendChild(innerTr);

		innerTr = document.createElement("tr");
		innerTd = document.createElement("td");
		innerTd.innerHTML = "<a href='" + jsonArr.list[i].link + "' target='_blank'>상세정보</a>"
		innerTr.appendChild(innerTd);
		tbody.appendChild(innerTr);

		innerTr = document.createElement("tr");
		innerTd = document.createElement("td");
		innerTd.innerHTML = "&nbsp;";
		innerTr.appendChild(innerTd);
		tbody.appendChild(innerTr);
		
		table.appendChild(tbody);
		td.appendChild(table);
		tr.appendChild(td);
		document.getElementById("listbody").appendChild(tr);

		
		$(".categorySearch").prepend("<colgroup>"
				+ "<col width='30%' /><col style='width:*;' />"
				+ "</colgroup>");
		
	}
	if (start >= 100) {
		$(".moreBtb").text("더이상 데이터가 존재하지 않습니다.");
		return;
	}
		
	$(".moreBtb").fadeOut(1000);
		
}