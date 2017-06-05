<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
function copytoclipboard( tagId ){
	 var e = document.getElementById(tagId);
	 try{
	  if(typeof document.body.createTextRange != "undefined"){ //IE
	   var textRange = document.body.createTextRange();
	   textRange.moveToElementText(e);
	   textRange.execCommand("Copy", false);
	   alert("현재 게시글의 주소가 복사되었습니다.");
	  }else if(window.netscape){ //파이어폭스
	   temp = prompt("이 글의 트랙백 주소입니다. Ctrl+C를 눌러 클립보드로 복사하세요", e.innerHTML);
	  }else{ // 크롬
	   chromeCopy( e, "selectAll" );
	   chromeCopy( e, "Copy" );
	   chromeCopy( e, "Unselect" );
	   alert("현재 게시글의 주소가 복사되었습니다.");
	  }
	 }catch(e){
	  
	 }
	}

	function chromeCopy(element, command){
	 var selection = window.getSelection();
	 var tempRanges = new Array();
	 for(var i = 0 ; i < selection.rangeCount ; i++ ){
	  tempRanges[i] = selection.getRangeAt(i).cloneRange();
	 }
	 
	 document.designMode = "on";
	 selection = window.getSelection();
	 var range = document.createRange();
	 range.selectNodeContents(element);
	 selection.removeAllRanges();
	 selection.addRange(range);
	 
	 var result = document.execCommand( command , false , null );
	 
	 document.designMode = "off";
	 
	 selection = window.getSelection();
	 selection.removeAllRanges();
	 for (var i = 0 ; i < tempRanges.length; ++i) {
	  selection.addRange(tempRanges[i]);
	    }
	 return result
	}
</script>
</head>
<body>
<dir onclick="copytoclipboard('urlAddr');">복사하기 클릭
<span id="urlAddr" style="font-size:0px;">복사할 내용</span>

<!-- 
IE에서는 정상적으로 글 주소복사 기능이 작동하지만
크롬, 파이어폭스 등등의 브라우저에서는 보안관련하여 클립보드에 복사하기가 힘들다.
사실 제로클립보드나 네이버 같은 경우는 플래쉬파일을 이용하여 클립보드 사용권한을 얻어
복사를 하고 있지만 현재 플래쉬파일같은 경우 랜섬웨어의 통로가 되어있으며 어도비 측에서도
사용을 권장하지 않는 추세이므로 자바스크립트를 이용하여 클립보드에 복사를 구현했다.
이에따라 IE, 크롬 에서는 클립보드에 복사하는기능 파이어폭스는 prompt 창을 이용하여 사용자가
직접 복사하도록 만든 소스
 -->


<!-- 
복사할 내용의 tag는 어디에 들어가든 상관은 없지만 font-size:0px을 해준 이유는
크롬 또는 ie11에서는 display:none 속성을 해당 태그에 줘버리면
복사가 되지 않는 문제가 발생하여 font-size:0px로 하여 사람들이 볼수 없도록만 해놓았다.
 -->

</dir>
</body>
</html>