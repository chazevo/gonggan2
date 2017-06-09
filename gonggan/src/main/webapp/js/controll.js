
var m_less10 = 0;
var m_less20 = 0;
var m_less30 = 0;
var m_less50 = 0;
var m_less60 = 0;
var f_less10 = 0;
var f_less20 = 0;
var f_less30 = 0;
var f_less50 = 0;
var f_less60 = 0;
   
function reqVisitor() {
	$.ajax({
		url : "/gonggan/selectVisitorList.do",
		data : {
			writer_id : writer_id
		},
		success : function(data) {
			callbackVisitorList(data);
		},
		error : function(data, status, error) {
			console.log("error : " + error);
		}
	});
}

function reqNeighborVisitor() {
	$.ajax({
		url : "/gonggan/selectNeigborVisitorList.do",
		data : {
			writer_id : writer_id
		},
		success : function(data) {
			callbackNeighborVisitorList(data);
		},
		error : function(data, status, error) {
			console.log("error : " + error);
		}
	});
}

function reqMonNeiVisitor() {
	$.ajax({
		url : "/gonggan/selectMonNeigborVisitorList.do",
		data : {
			writer_id : writer_id
		},
		success : function(data) {
			callbackMonNeiVisitorList(data);
		},
		error : function(data, status, error) {
			console.log("error : " + error);
		}
	});
}

function reqMonNeiList() {
	$.ajax({
		url : "/gonggan/selectMonNeiList.do",
		data : {
			writer_id : writer_id
		},
		success : function(data) {
			callbackMonNeiList(data);
		},
		error : function(data, status, error) {
			console.log("error : " + error);
		}
	});
}

function callbackVisitorList(data) {

	var jsonObj = JSON.stringify(data);
	var jsonArr = JSON.parse(jsonObj);

	$("#todayCnt").text(jsonArr.list.length);

	for ( var j in jsonArr.list) {
		
		var age = year - jsonArr.list[j].birth.substring(0, 4);
		
		jsonArr.list[j].gender
		jsonArr.list[j].birth
		jsonArr.list[j].member_id
		
		if (jsonArr.list[j].gender == 'M') {
			if (age <= 10) {
				m_less10++;
			} else if (age > 10 && age <= 20) {
				m_less20++;
			} else if (age > 20 && age <= 30) {
				m_less30++;
			} else if (age > 30 && age <= 50) {
				m_less50++;
			} else if (age > 50 && age <= 60) {
				m_less60++;
			}
		} else if (jsonArr.list[j].gender == 'F') {
			if (age <= 10) {
				f_less10++;
			} else if (age > 10 && age <= 20) {
				f_less20++;
			} else if (age > 20 && age <= 30) {
				f_less30++;
			} else if (age > 30 && age <= 50) {
				f_less50++;
			} else if (age > 50 && age <= 60) {
				f_less60++;
			}
		}

	}
	
	$(function() {
		$("#chart").highcharts({
			chart: {
				type: 'column'
			},
			title: {
				text: ''
			},
			credits:{
				enabled:false
			},
			legend: {
				symbolHeight:24,
				layout: 'horizontal',
				//backgroundColor: '#FFFFFF',
				align: 'left',
				verticalAlign: 'top',
				floating: true,
				shadow: false,
				padding:20,
				itemStyle: {
					fontWeight: 'normal',
					fontSize: '150%'
				}
			},
			xAxis: {
				categories: ['0-10', '10-20', '20-30', '30-50', '50-60'],
			},
			yAxis: {
				title: {
					text: ''
				}
			},
			series: [{
				color:'#BE6C95',
				name: '여자',
				data: [f_less10, f_less20, f_less30, f_less50, f_less60]
			}, 
			{
				color:'#307BB8',
				name: '남자',
				data: [m_less10, m_less20, m_less30, m_less50, m_less60]
			}]
		});
	});

}

function callbackNeighborVisitorList(data) {

	var jsonObj = JSON.stringify(data);
	var jsonArr = JSON.parse(jsonObj);

	$("#todayNeighborCnt").text(jsonArr.list.length);

	for ( var j in jsonArr.list) {
		$("#visitedNeighbor").html(
				$("#visitedNeighbor").html()
						+ "<a href='myhome.do?writer_id="
								+ jsonArr.list[j].member_id + "' target='_blank'>"
						+ jsonArr.list[j].member_id + "</a>");

	}
}

function callbackMonNeiVisitorList(data) {

	var jsonObj = JSON.stringify(data);
	var jsonArr = JSON.parse(jsonObj);

	$("#monthNeiVisitorCnt").text(jsonArr.list.length);

	for ( var j in jsonArr.list) {
		$("#monNeiVisitor").html(
				$("#monNeiVisitor").html() + "<a href='myhome.do?writer_id="
						+ jsonArr.list[j].member_id + "' target='_blank'>"
						+ jsonArr.list[j].member_id + "</a>"
						+ (j < jsonArr.list.length-1 ? ", " : ""));
	}
}

function callbackMonNeiList(data) {

	var jsonObj = JSON.stringify(data);
	var jsonArr = JSON.parse(jsonObj);

	$("#monNeiCnt").text(jsonArr.list.length);

	for ( var j in jsonArr.list) {
		$("#monNei").html(
				$("#monNei").html() + "<a href='myhome.do?writer_id="
						+ jsonArr.list[j].member_id + "' target='_blank'>"
						+ jsonArr.list[j].member_id + "</a>"
						+ (j < jsonArr.list.length-1 ? ", " : ""));
	}
}

function tab(val) {

	if (val == 1) {
		document.getElementById("blogControll").style.display = "block";
		$("#blogControllBtn").removeClass("gray");
		$("#blogControllBtn").addClass("gradLong");
		document.getElementById("blogStatistics").style.display = "none";
		$("#blogStatisticsBtn").removeClass("gradLong");
		$("#blogStatisticsBtn").addClass("gray");
	} else if (val == 2) {
		document.getElementById("blogStatistics").style.display = "block";
		$("#blogStatisticsBtn").removeClass("gray");
		$("#blogStatisticsBtn").addClass("gradLong");
		document.getElementById("blogControll").style.display = "none";
		$("#blogControllBtn").removeClass("gradLong");
		$("#blogControllBtn").addClass("gray");
	}
   
	location.href = "#";
}

function deletePost(loginUser, postId,comment_num) {
   
	if (confirm("정말 삭제하시겠습니까? ") == true){
		$.ajax({
			url: "/gonggan/codelete.do",
			data: {writer_id:loginUser,
				postId:postId,
				comment_num:comment_num
			},
			success: function(data) {
				$(this).parent().parent().remove();
			},
			error: function(data,status,error){
				console.log("error : " + error);
			}
		});
	}     
}

function cnvrtRGBClrToHex(/*rgbClr*/r, g, b){
    /*
	var rgbClr = rgbClr.split(',');
    var r = rgbClr[0];
    var g = rgbClr[1];
    var b = rgbClr[2];
    */
    return "#" + (r << 16 | g << 8 | b).toString(16).toUpperCase()
}

function changeBgColor(obj) {
	$('#background_color').css('color', obj.value); 
}

function changeBgImg(obj/*inputId, outputId*/) {
	
	var file = document.getElementById('file').files[0];
	var reader = new FileReader();
	reader.readAsDataURL(file);
	reader.onload = function() {
		//var output = document.getElementById(outputId);
		//output.src = reader.result;
		$('.bannerEdit').css('background' , 'url(' + reader.result + ')');
	}
	
}

function changeTitleColor(obj) {
	$('#blogTitle').css('color', obj.value); 
}

function changeContentsColor(obj) {
	$('#blogComment').css('color', obj.value); 
}

function cancelTitleColorChange() {
	var val;
	
	colorSplit1step = color.split("(")[1];
	
	val = cnvrtRGBClrToHex(
			colorSplit1step.split(", ")[0],
			colorSplit1step.split(", ")[1],
			colorSplit1step.split(", ")[2].split(")")[0]);
	
	$("input[name=color]").val(val);
	$('#blogTitle').css("color", val);
	
}

function cancelContentColorChange() {
	var val;
	colorSplit1step = contents_color.split("(")[1];
	
	val = cnvrtRGBClrToHex(
			colorSplit1step.split(", ")[0],
			colorSplit1step.split(", ")[1],
			colorSplit1step.split(", ")[2].split(")")[0]);
	
	$("input[name=contents_color]").val(val);
	$("#blogComment").css("color", val);
}

function cancelBgcolorChange() {
	var val;
	colorSplit1step = background_color.split("(")[1];
	
	val = cnvrtRGBClrToHex(
			colorSplit1step.split(", ")[0],
			colorSplit1step.split(", ")[1],
			colorSplit1step.split(", ")[2].split(")")[0]);
	
	$("input[name=background_color]").val(val);
	$(".bannerEdit").css("background-color", val);
}

function settingComplete() {
	if (confirm("수정사항을 반영하시겠습니까?") == true) {
		if ($("input[name=bgmode]").val() == "img")
			;
		else if ($("input[name=bgmode]").val() == "color")
			; // 기존 파일 삭제 
		
		$("#form").submit();
	}
}