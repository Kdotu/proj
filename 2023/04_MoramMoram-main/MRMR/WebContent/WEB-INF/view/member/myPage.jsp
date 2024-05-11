<%@page import="java.util.ArrayList"%>
<%@page import="vo.ServiceVO"%>
<%@page import="java.util.List"%>
<%@page import="vo.ExpertVO"%>
<%@page import="vo.MemberVO"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Map<String, Object> loginInfo = (Map<String, Object>)session.getAttribute("loginInfo");
MemberVO memberInfo = (MemberVO)loginInfo.get("memberInfo");
// List<Map<String, Object>> expertServiceHistoryList = (List<Map<String, Object>>) request.getAttribute("expertServiceHistory");
MemberVO loginMemberInfo = (MemberVO)loginInfo.get("memberInfo");
ExpertVO expertInfo = null;
int memNo = loginMemberInfo.getMem_no();
if(loginMemberInfo.getMem_grade() == 1){
	expertInfo = (ExpertVO)loginInfo.get("expertInfo");
}

int grade = loginMemberInfo.getMem_grade();
String gradeName = "";
String toggleBtn = "";
List<Map<String, Object>> list = null; 
List<Map<String, Object>> boardList = null;
List<Object> labels = new  ArrayList<Object>();
List<Object> datas = new  ArrayList<Object>();
if(grade == 0){
	gradeName = "일반회원";
	toggleBtn = "전문가";
	list = (List<Map<String, Object>>) request.getAttribute("cartList");
	boardList = (List<Map<String, Object>>) request.getAttribute("boardList");
}else if(grade == 1){
	gradeName = "전문가";
	toggleBtn = "일반회원";
	list = (List<Map<String, Object>>) request.getAttribute("statistics");
	for(Map<String, Object> map : list){
		labels.add((Object)map.get("STATISTICS_DATE"));
		datas.add((Object)map.get("SALES_STATISTICS"));
	}
}else if(grade == 9){
	gradeName = "관리자";
}
%>
<!DOCTYPE html>
<html lang="kr">
<head>
<meta charset="utf-8">
<title>MORAM MORAM</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">
<!--       Favicon   -->
<link href="<%=request.getContextPath() %>/img/favicon.ico" rel="icon">

<!--       Google Web Fonts   -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">

<!--       Icon Font Stylesheet   -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

<!--       Libraries Stylesheet   -->
<link href="<%=request.getContextPath()%>/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

<!--       Customized Bootstrap Stylesheet   -->
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">

<!--       Template Stylesheet   -->
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
<script src='<%=request.getContextPath()%>/js/jquery-3.6.1.min.js'></script>
<script src="<%=request.getContextPath() %>/js/jquery.serializejson.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/js/tempusdominus-bootstrap-4.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/js/main.js" type="text/javascript"></script>


<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

</head>
<script>
myservicelist = function(){
	$.ajax({
		url : "<%= request.getContextPath()%>/service/myServiceList.do?mem_no=<%=loginMemberInfo.getMem_no()%>",
		type : "get",
		success : function(myservicelist){
			$('#myServiceDiv').html(myservicelist);
		},
		error : function(xhr){
			alert(" 내 서비스 리스트 상태" + xhr.status);
		},
		dataType : "html"
	})
}
</script>

<%
List<ServiceVO> myServiceList = (List<ServiceVO>) request.getAttribute("myServiceList");
%>
 <script>
 
$(function(){
	$("#yearBtn").on("click", function(){
		$.ajax({
			url : "<%=request.getContextPath()%>/member/selectSalesStatistics.do",
			data : {
				dateType : $("#selectDateType").val(),
				date : $("#date").val(),
				mem_no : "<%=memberInfo.getMem_no()%>"
			},
			type : "get",
			success : function(res){
				labels = [];
				data = [];
				$.each(res, function(i, v){
					labels.push(v.STATISTICS_DATE);
					data.push(v.SALES_STATISTICS);
				})
				var barCanvas = $("#bar-chart").get(0).getContext("2d");
				if (barStatistics != undefined) {
					barStatistics.destroy();
				}
				barStatistics = new Chart(barCanvas, {
			        type: "bar",
			        data: {
			            labels: labels,
			            datasets: [{
			                backgroundColor: [
			                    "rgba(0, 156, 255, .7)"
			                ],
			                data: data
			            }]
			        },
			        options: {
			            responsive: true
			        }
			    });
				$(".p-4").show();
			},
			error : function(xhr){
				alert(xhr.status);
			},
			dataType : "json"
			
		})
	})
	
	
	// Single Bar Chart
    var barCanvas = $("#bar-chart").get(0).getContext("2d");
    var barStatistics = new Chart(barCanvas, {
        type: "bar",
        data: {
            labels: <%=labels%>,
            datasets: [{
                label: "MonthStatistics",
                backgroundColor: [
                	"rgba(0, 156, 255, .7)"
                ],
                data: <%=datas%>
            }]
        },
        options: {
            responsive: true
        }
    });
    
});

$(function(){
	$("#selectDateType").on("change", function(){
		dateType = $(this).val();
		if(dateType == "year"){
			$("#date").prop("type", "text");
			$("#date").prop("placeholder", "년도입력");
		}else if(dateType == "month"){
			$("#date").prop("type", "text");
			$("#date").prop("placeholder", "년도입력");
		}else if(dateType == "week"){
			$("#date").prop("type", "month");
		}else if(dateType == "day"){
			$("#date").prop("type", "date");
		}
	})
})
	
</script>

<body>
	<!-- 	header.jsp include -->
	<jsp:include page="../../../header.jsp"></jsp:include>
	<jsp:include page="../../../memberSidebar.jsp"></jsp:include>

    <!-- Content Start -->
    <div class="content" id="myPageDiv" style="margin-left: 250px; padding: 3%;">
    
		<!-- 일반회원 -->
          <%if("일반회원".equals(gradeName)){ %>
          <div class="row g-2 mt-3 mb-3 myPageList">
        <!-- Widgets Start -->
                <!-- 캘린더 시작 -->
<%--                 <div class="col-sm">
                    <div class="h-100 bg-light rounded p-4">
                        <div class="d-flex align-items-center justify-content-between mb-4">
                            <h6 class="mb-0">Calender</h6>
                            <a class="schedule">Show All</a>
                        </div>
                        <script>
                        	viewSchedule(<%=memNo%>);
                        </script>
                        
                        <div id="calender"></div>
                    </div>
                </div> --%>
                <!-- 캘린더 끝 -->
               <!-- todo 시작 -->
                <div class="col-lg-8">
                    <div class="h-100 bg-light rounded p-4">
                        <div class="d-flex align-items-center justify-content-between mb-4">
                            <h6 class="mb-0">My Service List</h6>
                            <a class="serviceHistory">Show All</a>
                        </div>
                       	<%
                       	if(list != null || list.size() != 0) {
                       		for(Map<String, Object> cart : list){%>
                        <div class="d-flex align-items-center justify-content-between mb-3" id="myCartDiv">
                        	<div class="w-100 ms-3">
                                <div style="display: inline-block;">
                                    <h6 class="mb-0"  style="display: inline-block;"><%=cart.get("SERVICE_NAME")%></h6>
                                </div>
                                <div style="display: inline-block;float:right;"><%=cart.get("CART_DATE") %> [<%=cart.get("CART_STATE_NAME") %>]</div>
                            </div>
                        </div>
                        <hr/>
                       		<%}
                       	}
                       	%>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="h-100 bg-light rounded p-4">
                        <div class="d-flex align-items-center justify-content-between mb-4">
                            <h6 class="mb-0">My Board List</h6>
                            <a class="myBoard">Show All</a>
                        </div>
                        <%
                        if(boardList != null || boardList.size() != 0){
                        	for(Map<String, Object> board : boardList){%>
                        <div class="d-flex align-items-center border-bottom py-2" id="myServiceDiv">
                        	<div class="w-100 ms-3">
                                <div class="d-flex w-100 justify-content-between">
                                    <h6 class="mb-0"><%=board.get("BOARD_TITLE") %></h6>
                                    <small><%=board.get("BOARD_CATEGORY_NAME") %></small>
                                </div>
                                <span><%=board.get("BOARD_CONTENT")%></span>
                            </div>
                        </div>		
                        <%	}
                        }
                       	%>
                        
                    </div>
                </div>
                <!-- todo 끝 -->
                
            </div>
        <!-- Widgets End -->
    </div>
    <!-- Content End -->		
    <%}else if("전문가".equals(gradeName)){ %>
           
        <!-- Sale & Revenue End -->
          <div class="row g-3 mt-3 mb-3 myPageList container-fluid w70" style="margin-left : -2px;">
            <div class="col-lg-8">
               <div class="bg-light text-center rounded p-4">
                   <div class="d-flex align-items-center justify-content-between mb-4">
                       <h6 class="mb-0">Single Bar Chart</h6>
					<select id="selectDateType">
						<option value="year">년</option>
						<option value="month">월</option>
						<option value="week">주</option>
						<option value="day">일</option>
					</select>
					<input type="text" id="date" placeholder="년도입력">
					<input type="button" id="yearBtn" value="조회">
                   </div>
                   <canvas id="bar-chart" width="685" height="342" style="display: block; box-sizing: border-box; height: 342px; width: 685px;"></canvas>
               </div>
            </div>   
        <!-- Sales Chart End -->

        <!-- Widgets Start -->
                <!-- 캘린더 시작 -->
                <div class="col col-lg-4">
                    <div class="h-100 bg-light rounded p-4">
                        <div class="d-flex align-items-center justify-content-between mb-4">
                            <h6 class="mb-0">Calender</h6>
                            <a class="schedule">Show All</a>
                        </div>
                        <div id="calender"></div>
                    </div>
                </div>
                <!-- 캘린더 끝 -->
                
               <!-- todo 시작 -->
<!--                 <div class="col">
                    <div class="h-100 bg-light rounded p-4">
                        <div class="d-flex align-items-center justify-content-between mb-4">
                            <h6 class="mb-0">My Service List</h6>
                            <a href="">Show All</a>
                        </div>
                        <div class="d-flex align-items-center border-bottom py-2" id="myServiceDiv">
                        </div>
                    </div>
                </div>
              </div> -->
                <!-- todo 끝 -->
                
                
            </div>
        <!-- Widgets End -->
    </div>
    </div>
    <!-- Content End -->		
    <%}else if("관리자".equals(gradeName)){ %>
    <script type="text/javascript">
    	$(function(){
    		$.ajax({
    			url : "<%=request.getContextPath() %>/admin/selectMemberlist.do",
    				type : "get",
    				data : {
    					page : 1
    				},
    				success : function(selectMemberlist){
    					$("#myPageDiv").html(selectMemberlist);
    				},
    				error : function(xhr){
    					alert("상태 : " + xhr.status);
    				},
    				dataType : "html"
    			})
    	})
    </script>
   <%} %>
    
	
	<!-- 	footer.jsp include -->
	<jsp:include page="../../../footer.jsp"></jsp:include>
	
    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath() %>/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>
    
    <script src="<%=request.getContextPath() %>/lib/chart/chart.min.js"></script>
    <script src="<%=request.getContextPath() %>/lib/easing/easing.min.js"></script>
    <script src="<%=request.getContextPath() %>/lib/waypoints/waypoints.min.js"></script>
    <script src="<%=request.getContextPath() %>/lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="<%=request.getContextPath() %>/lib/tempusdominus/js/moment.min.js"></script>
    <script src="<%=request.getContextPath() %>/lib/tempusdominus/js/moment-timezone.min.js"></script>
    <script src="<%=request.getContextPath() %>/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

	  <!-- iamport.payment.js -->
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>

    <!-- Template Javascript -->
    <script src="<%= request.getContextPath() %>/js/main.js"></script>
</body>
</html>