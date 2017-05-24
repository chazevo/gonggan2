
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
	
	//parent.recieveMusic(videoId, title, thumbnail);

	parent.$.fancybox.close();
}

function selectSubmit(){
	
	if ($("input[name=keyword]").val() == "") {
		alert("키워드를 입력해주세요! ");
		return;
	}
	
	if(categoryval == 0)
		document.getElementById('submit').action = "booksearch.do";
	else if(categoryval == 1)
		document.getElementById('submit').action = "moviesearch.do";
	else if(categoryval == 2) {
		document.getElementById('submit').action = "musicpost.do";
		return;
	}
	else if(categoryval == 3)
		document.getElementById('submit').action = "넘길 주소";
	else if(categoryval == 4) {
		document.getElementById('submit').action = "newssearch.do";
	}
	
	document.getElementById('submit').submit();

}