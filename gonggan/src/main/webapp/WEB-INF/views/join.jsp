<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[공:간] 회원가입 - 정보 입력하기</title>
<link rel='stylesheet' href='css/css.css'/> 
<script src="http://code.jquery.com/jquery-1.9.1.js"></script> 
<script type="text/javascript" src="js/join.js"></script>
<script type="text/javascript">
   $(document).ready(function() {
	  
      $("input[name='member_id']").keyup(function() {
         $("input[name='member_id_']").val($(this).val());
         });
      $("input[name='member_id_']").keyup(function() {
         $("input[name='member_id']").val($(this).val());
         });
      $("input").click(function() {
         $(this).next().css("display", "block");
         });
      $("input[name='id']").focus();
      });
</script>
</head>
<body id="indexBg">
   <div id="middleDiv">
      <a href="start.do">
         <img class="" width="20%"
         src="images/KakaoTalk_Photo_2017-04-22-18-12-10_54.png">
      </a>
      <form action="/gonggan/insert.do" method="post" id="join" autocomplete="off">
         <br><br>
         <font color="#E6E6E6" size="5px">
         <b>공간</b></font>&nbsp;과 함께 하실
         <span id="idSpan">
            <input type="text" name="member_id" value="" placeholder=" 아이디" title="아이디 입력(4-12자 이내 영문 소문자,숫자,underbar만 사용가능)" class="id join" maxlength="12" 
            onblur="blurEvent(this);"/>
            <em>4-12자 이내 영문 소문자,숫자,underbar만 사용가능합니다.</em>
         </span>님의 이름은
         <span id="idSpan">
            <input type="text" name="member_name" value="" placeholder=" 이름" title="이름 입력(4-12자 이내 영문 소문자,한글만 사용가능)" class="join" maxlength="12" 
            onblur="blurEvent(this);"/>
            <em>4-12자 이내 영문 한글만 사용가능합니다.</em>
         </span> 이고 비밀번호는
         <span id="pwdSpan">
            <input type="password" name="member_pw" value="" placeholder=" 비밀번호" title="비밀번호 입력(4자 이상 영문 대/소문자, 숫자)" class="pw join" maxlength="12" 
            onblur="blurEvent(this);"/>
            <em>4-12자 이내 영문 대/소문자, 숫자로 입력해주세요.</em>
         </span>입니다.<br />
         <span id="pwd2Span">
            <input type="password" name="member_pw2" value="" placeholder=" 비밀번호" title="비밀번호 재입력(10-12자 이내 영문 대/소문자, 숫자)" class="pw join" maxlength="12"
            onblur="blurEvent(this);"/>
            <em>한번 더 입력해주세요.</em>
         </span>를 한번 더 알려주세요!<br />
         <span><input type="text" name="member_id_" value="" placeholder=" 아이디" title="아이디 입력(4-12자 이내 영문 소문자,숫자,underbar만 사용가능)" class="id join" maxlength="12" 
         onblur="blurEvent(this);"/>
          </span>님과 연락할 수 있는 <br>
         <span id="phoneSpan">
            <input type="text" name="member_phone" value="" placeholder=" 휴대전화 번호" title="휴대전화 번호 입력(숫자만 입력가능)" class="phone join" maxlength="11" onkeydown="return onlyNumber(event)" onkeyup="removeChar(event)" style="ime-mode:disabled;"
            onblur="blurEvent(this);"/>
            <em>숫자만 입력해 주세요.</em>
         </span>와 
         <span id="emailSpan">
            <input type="text" name="email" value="" placeholder=" 이메일 주소" title="이메일 주소 입력" class="email join"
            onblur="blurEvent(this);"/>
            <em>이메일을 입력해 주세요.</em>
         </span>를 알려주세요.<br />
         <span id="emailSpan">
            <input type="text" name="profile" class="join" value="" placeholder=" 프로필" title="프로필 입력"
            onblur="blurEvent(this);"/>
            <em>프로필을 입력해 주세요.</em>
         </span>로 자기를 소개해 주세요.<br>
         <span id="emailSpan">
            <input type="text" name="member_gender"  class="join" value="" placeholder=" 성별" title="성별 입력"
            onblur="blurEvent(this);"/>
            <em>성별을 입력해 주세요.</em>
         </span>과 
         <span id="emailSpan">
         	<select>
         	</select>
            <input type="text" name="member_birth" class="join" value="" placeholder=" 생일 " title="생일 입력"
            onblur="blurEvent(this);"/>
            <em>생일을 입력해 주세요.</em>
         </span>을 입력해주세요.<br />

         <div class="divisionMargin2">
            <a href="javascript:goSubmit();">
               <div class="button">WELCOME TO THE GONG:GAN</div>
               
            </a>
            <a href="javascript:history.back();">
               <div class="button2">취소</div>
            </a>
         </div> 
      </form>
   <br><br><br><br><br><br><br><br><br><br>
   </div>
</body>
</html>