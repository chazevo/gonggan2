var rownum = 1;
var Ca = /\+/g;

function requestList(val) {
   
   var rownum2;
   
   //if (maxRownum - val < 20)
   if (maxRownum - val < 8)
      var rownum2 = maxRownum;
   //else rownum2 = rownum + 19;
   else rownum2 = rownum + 7;
   
   //alert("rownum2 : " + rownum2);
   
   $.ajax({
      url: "postlist.do",
      data: { writer_id: "",
         rownum: rownum,
         rownum2: rownum2,
         category: "all" },
      success: function(data) {
         rownum = rownum2 + 1;
         callbackList(data);
      },
      error: function(data,status,error){
         console.log("error : " + error);
      }
   });
}

function requestNeighborPostList(val, loginUser) {
   
   $("#blogHomeContentDiv").html("");
   
   var rownum2;
   var loginUser;
   
   if (maxRownum - val < 8)
      var rownum2 = maxRownum;
   else rownum2 = rownum + 7;
   
   $.ajax({
            url: "/gonggan/postNeighborlist.do",
            //url: "/gonggan/userpostlist.do",
            data: { loginUser : loginUser,
               rownum: rownum, rownum2: rownum2
            },
            success: function(data) {
               
               callbackList(data);
            },
            error: function(data,status,error){
               console.log("error : " + error);
            }
      });
}

function requestCategoryList(val, category) {
   
   var rownum2;
   
   if (maxRownum - val < 8)
      var rownum2 = maxRownum;
   else rownum2 = rownum + 7;
   
   $.ajax({
            url: "/gonggan/postlist.do",
            //url: "/gonggan/userpostlist.do",
            data: { writer_id : "",
               rownum: rownum, rownum2: rownum2,
               category: category
            },
            success: function(data) {
               callbackList(data);
            },
            error: function(data,status,error){
               console.log("error : " + error);
            }
      });
}

function requestLikeList(val) {

   var rownum2;

   if (maxRownum - val < 8)
      var rownum2 = maxRownum;
   else rownum2 = rownum + 7;
   
   $.ajax({
      url: "plikelist.do",
      data: { rownum: rownum,
         rownum2: rownum2 },
      success: function(data) {
         rownum = rownum2 + 1;
         callbackList(data);
      },
      error: function(data,status,error){
         console.log("error : " + error);
      }
   });
}

function sorting() {
   if ($("#select").val() == "like") {
      $("#blogHomeContentDiv").html("");
      requestLikeList(rownum = 1);
   }
   else if ($("#select").val() == "date") {
      $("#blogHomeContentDiv").html("");
      requestList(rownum = 1);
   }
}

function callbackList(data) {
   
   var jsonObj = JSON.stringify(data);
   var jsonArr = JSON.parse(jsonObj);
   
   var div, table, tr, td;
   
   var postId, content, writerId, goodCnt;
   
   for (var i in jsonArr.list) {
      
      div = document.createElement("div");
      div.class = "";
      table = document.createElement("table");
      table.border = "1";
      
      postId = jsonArr.list[i].postId;
      content = reqPostDetail(postId, jsonArr.list[i].category);
      writerId = jsonArr.list[i].writerId;
      goodCnt = jsonArr.list[i].goodCnt;
      
      photoPath = jsonArr.list[i].photoPath;
      tr = document.createElement("tr");
      td = document.createElement("td");
      td.colSpan = "2";
      td.class = "blogHomeContent";
      
      /*td.innerHTML = "<a data-fancybox data-src='pdetail.do?"
         + "postId=" + postId
         + "&writerId=" + writerId + "'>"
         + decodeURIComponent(content.replace(Ca, " "))
         + "</a>"
         tr.appendChild(td);
      table.appendChild(tr);
      */
      
      if(photoPath=='0'){
         td.innerHTML = "<a data-fancybox data-src='pdetail.do?"
            + "postId=" + postId
            + "&writerId=" + writerId + "'>"
            + decodeURIComponent(content.replace(Ca, " "))
            + "</a>"
            tr.appendChild(td);
         td.width ="30px";
         table.appendChild(tr);
      }
      else{
        tr.innerHTML = "<td colspan='2' class='nPostTd' width='150px' height='150px' background='uploadImages/"
          + photoPath + "'>" 
             + "<a class='nPostText' data-fancybox data-src='pdetail.do?"
                + "postId=" + postId
                + "&writerId=" + writerId + "'>"
                + decodeURIComponent(content.replace(Ca, " "))
                + "</a><td></td>"
         /*td.innerHTML = "<div class='nPostCell'><a class='nPostText' data-fancybox data-src='pdetail.do?"
            + "postId=" + postId
            + "&writerId=" + writerId + "'>"
            + decodeURIComponent(content.replace(Ca, " "))
            + "<image class='nPostImg' src='uploadImages/"+ photoPath + "' width='170px' height='170px'>"
            + "</a></div>"
            tr.appendChild(td);*/
         table.appendChild(tr);
      }
      
      tr = document.createElement("tr");
      tr.class = "trBottom";
      td = document.createElement("td");
      td.innerHTML = "<a href='selectBlog.do?writer_id=" + writerId + "'>"
      + writerId + "</a>";
      tr.appendChild(td);
      td = document.createElement("td");
      td.class = "rightAlign";
      td.innerHTML = "<label class='checkbox-wrap'>"   
         + "<input type='checkbox' id='' "
         + "onclick='like($(this), loginUser, " + postId + ");'>"
         + "<i class='like-icon'></i></label>&nbsp;<span>"
         + (goodCnt == 0? goodCnt
               : "<a data-fancybox data-src='goodList.do?postId=" + postId + "'>"
               + goodCnt + "</a></span>");
      tr.appendChild(td);
      table.appendChild(tr);
      
      div.appendChild(table);
      
      document.getElementById("blogHomeContentDiv").appendChild(div);
   }
   
   if (rownum >= maxRownum) {
      $("#div_Loading").html("더이상 포스트가 존재하지 않습니다.");
      $("#div_Loading").show();
      return;
   }
   $("#div_Loading").fadeOut(100);
}

function reqPostDetail(postId, category) {
   
   var content;
   
   $.ajax({
      async: false,
      url: "plistDetail.do",
      data: { postId: postId,
         category : category},
      success: function(data) {
         content = data;
      },
      error: function(data,status,error){
         console.log("error : " + error);
      }
   });
   
   return content;
}

function trace(loginUser) {
   $.ajax({
         url: "/gonggan/trace.do",
         data: {loginUser:loginUser},
         success: function(data) {
               callbacktrace(data);
         },
         error: function(data,status,error){
            console.log("error : " + error);
         }
      });
}

function callbacktrace(data) {
   
   var jsonObj = JSON.stringify(data);
   var jsonArr = JSON.parse(jsonObj);   

   var tr;
   var td;
   
   $("#listbody_newPost").hide();
   $("#listbody_newNeighbor").hide();
   $(".title").hide();
   $("#postAlarm").text('나의 흔적_');
   $("#postAlarmCnt").text(jsonArr.list.length + "");
   
   while (document.getElementById("listbody_mytrace").rows.length > 0 )
      document.getElementById("listbody_mytrace").deleteRow(0);
   
   for (var j=0 ; j<7 ; j++){
      
      loginUser = jsonArr.list[j].loginUser;
      
      tr = document.createElement("tr");
      td = document.createElement("td");
      //document.getElementById("listbody").innerHTML += "<tr><td colSpan='7'>"
      
      td.innerHTML = "<a data-fancybox data-src='pdetail.do?postId="+jsonArr.list[j].postId+"&writerId=" + jsonArr.list[j].postWriter +"'>"
      + decodeURIComponent((jsonArr.list[j].commentContent).replace(Ca, " ")) +"</a> " ;
      tr.appendChild(td);
      document.getElementById("listbody_mytrace").appendChild(tr);
      
      if (jsonArr.list.length-1 == j) break;
      
   }
      
   
}
function checkGood(loginUser, postId){
   
   $.ajax({
         url: "/gonggan/checkGood.do",
     data: {loginUser:loginUser,
            postId:postId},
     success: function(data) {
        if (data == "good")
           document.getElementById("like").checked=true;
        else if (data == "nogood")
           document.getElementById("like").checked=false;
     },
     error: function(data,status,error){
        console.log("error : " + error);
     }
   });
}


function like(obj, loginUser, postId){
   
   var target = obj.parent().next();
   var plikecnt;
   
   if (loginUser == "") {
      alert("로그인 하셔야 가능합니다 ! ");

      if (obj.prop("checked") == true) 
         obj.prop("checked", false);
      else 
         obj.prop("checked", true);
   
      return ;
   }
   
   if (obj.prop("checked") == true) {
      $.ajax({
         url: "/gonggan/insertGood.do",
         data: {loginUser:loginUser,
            postId:postId},
            success: function(data) {
               alert("좋아요 함 ");
            },
            error: function(data,status,error){
               console.log("error : " + error);
            }
      });
   }
   else {
      $.ajax({
         url: "/gonggan/deleteGood.do",
         data: {loginUser:loginUser,
            postId:1},
            success: function(data) {
               alert("좋아요 취소함 ");
            },
            error: function(data,status,error){
               console.log("error : " + error);
            }
      });
      
      plikecnt =  postLikeCnt(postId);
      target.html( ( plikecnt > 0 ?
            ("<a data-fancybox data-src='goodList.do?postId=" + postId + "'>"
            + plikecnt +  "</a>") : (plikecnt + "") ) );
      
   }
}

function postLikeCnt(postId) {
   
   var gcnt;
   
   $.ajax({
      async:false,
      url: "/gonggan/plikecnt.do",
      data: { postId:postId },
         success: function(data) {
            gcnt = data;
         },
         error: function(data,status,error){
            console.log("error : " + error);
         }
   });
   alert(gcnt);
   return gcnt;
}

function acceptNeig(member_id, member_id2) {
   
   $(this).parent().remove();
   
   /*
   $.ajax({
      url: "/gonggan/naccept.do",
      data: {member_id: member_id, member_id2: member_id2},
      success: function(data) {
         $("#neighborReqListSize").text($("#neighborReqListSize").text() - 1);
         alert(data);
         $(this).parent().remove();
      },
      error: function(data,status,error){
         console.log("error : " + error);
      }
   });*/
}

function rejectNeig(member_id, member_id2) {
   $.ajax({
      url: "/gonggan/nreject.do",
      data: {member_id: member_id, member_id2: member_id2},
      success: function(data) {
         alert(data);
         $("#neighborReqListSize").text($("#neighborReqListSize").text() - 1);
         $(this).parent().remove();
      },
      error: function(data,status,error){
         console.log("error : " + error);
      }
   });
}

function neighborList(loginUser) {
   $.ajax({
      url: "neighborlist.do",
      data: { 
         loginUser: loginUser},
         success: function(data){
         callbackNeighborList(data);         
      },
      error: function(data,status,error){
         console.log("error : " + error);
      } 
   });
} //이웃 블로그 목록

function callbackNeighborList(data){

   var jsonObj = JSON.stringify(data);
   var jsonArr = JSON.parse(jsonObj);
   
   var div;
   var tr, td;
   
   while (document.getElementById("table_newPost").rows.length > 1 )
      document.getElementById("table_newPost").deleteRow(1);   

   tr = document.createElement("tr");
   td = document.createElement("td");
   td.innerHTML = "서로이웃 수_<font color='#2D86C9'><b>" + jsonArr.list.length + "</b></font>";
   tr.appendChild(td);
   document.getElementById("table_newPost").appendChild( tr );
   for(var i in jsonArr.list) {
      var memberId = jsonArr.list[i].memberId;
      tr = document.createElement( 'tr' );
      td = document.createElement( 'td' );
      var a = document.createElement( 'a' );
      var aText = document.createTextNode(memberId);
      var font = document.createElement('font');
      a.href="selectBlog.do?writer_id="+jsonArr.list[i].memberId;
      tr.appendChild(td);
      td.appendChild(a);
      a.appendChild( aText );
      document.getElementById("table_newPost").appendChild( tr );
   }
} //이웃 블로그 목록

function searchNeighbor() {
   
   $.ajax({
         url: "/gonggan/nsearch.do",
         data: {member_id:loginUser,
            member_id2 : $('#searchNeighbor').val()
            },
         success: function(data) {
            callbackNsearch(data);
         },
         error: function(data,status,error){
            console.log("error : " + error);
         }
      });
}

function callbackNsearch(data) {

   var jsonObj = JSON.stringify(data);
   var jsonArr = JSON.parse(jsonObj);
   
   var tr, td;
   
   while (document.getElementById("table_newPost").rows.length > 1 )
      document.getElementById("table_newPost").deleteRow(1);
   
   for (var i in jsonArr.list) {
      var member_id = jsonArr.list[i].member_id;
      tr = document.createElement( 'tr' );
      td = document.createElement( 'td' );
      var a = document.createElement( 'a' );
      var aText = document.createTextNode(member_id);
      var font = document.createElement('font');
      a.href="selectBlog.do?writer_id="+jsonArr.list[i].member_id;
      tr.appendChild(td);
      td.appendChild(a);
      a.appendChild( aText );
      document.getElementById("table_newPost").appendChild( tr );
   }
}