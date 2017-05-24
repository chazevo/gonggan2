var categoryval = 1;

function selectMovie(image, title, director , actor, pubDate) {
	
	parent.recieveMovie(image, title, director , actor, pubDate);
	
	parent.$.fancybox.close();
}

function selectNews(title, originallink, description, pubDate) {
	
	parent.recieveNews(title, originallink, description, pubDate);
	
	parent.$.fancybox.close();
}
function selectBook(image, title, author, publisher, pubDate) {
	
	parent.recieveBook(image, title, author, publisher, pubDate);
	
	parent.$.fancybox.close();
}


function selectSubmit(){

	if(categoryval == 0)
		document.getElementById('submit').action = "booksearch.do";
	else if(categoryval == 1)
		document.getElementById('submit').action = "moviesearch.do";
	else if(categoryval == 2)
		document.getElementById('submit').action = "넘길 주소";
	else if(categoryval == 3)
		document.getElementById('submit').action = "넘길 주소";
	else if(categoryval == 4) {
		document.getElementById('submit').action = "newssearch.do";
	}
	
	document.getElementById('submit').submit();

}