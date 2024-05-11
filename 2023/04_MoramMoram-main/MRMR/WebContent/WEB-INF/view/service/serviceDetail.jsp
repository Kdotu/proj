z<%@page import="vo.MemberVO"%>
<%@page import="java.util.Map"%>
<%@page import="vo.AddressVO"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="vo.ExpertVO"%>
<%@page import="member.service.MemberService"%>
<%@page import="member.service.MemberServiceImpl"%>
<%@page import="vo.ServiceVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>MORAM MORAM</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">
    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;500&family=Roboto:wght@500;700&display=swap" rel="stylesheet">
    
    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">


    <!-- Customized Bootstrap Stylesheet -->
    <link href="<%= request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/css/jquery.dataTables.min.css" rel="stylesheet">
    <script src="<%= request.getContextPath() %>/js/jquery.dataTables.min.js"></script>
    <script src='<%= request.getContextPath() %>/js/jquery-3.6.1.min.js'></script>
    
    <!-- modal -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	 
    <!-- calendar -->
    <link href='<%= request.getContextPath() %>/calendar/packages/core/main.css' rel='stylesheet' />
	<link href='<%= request.getContextPath() %>/calendar/packages/daygrid/main.css' rel='stylesheet' />
	<link href='<%= request.getContextPath() %>/calendar/packages/timegrid/main.css' rel='stylesheet' />
	<link href="<%=request.getContextPath() %>/style/chat.css" rel="stylesheet" >
	<script src='<%= request.getContextPath() %>/calendar/packages/core/main.js'></script>
	<script src='<%= request.getContextPath() %>/calendar/packages/interaction/main.js'></script>
	<script src='<%= request.getContextPath() %>/calendar/packages/daygrid/main.js'></script>
	<script src='<%= request.getContextPath() %>/calendar/packages/timegrid/main.js'></script>
	<script src='<%= request.getContextPath() %>/js/calendar.js'></script>
    <script src='<%= request.getContextPath() %>/js/servicePay.js'></script>
    <script src="<%= request.getContextPath() %>/js/mapSearchwithMarker.js"></script>
	<script src="<%=request.getContextPath() %>/js/chat.js" type="text/javascript" charset="utf-8"></script>
    
    <!-- jQuery -->
  	<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
  	<!-- iamport.payment.js -->
  	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
  	
    
    <script src="https://t1.kakaocdn.net/kakao_js_sdk/2.0.1/kakao.min.js" integrity="sha384-eKjgHJ9+vwU/FCSUG3nV1RKFolUXLsc6nLQ2R1tD0t4YFPCvRmkcF8saIfOZNWf/" crossorigin="anonymous"></script>
	<%
      boolean isLogined = (boolean)session.getAttribute("isLogined");

      MemberVO mvo = (MemberVO)request.getAttribute("MemberVO");
      ServiceVO svo = (ServiceVO)request.getAttribute("serviceVO");
      ExpertVO evo = (ExpertVO)request.getAttribute("ExpertVO");
      AddressVO avo = (AddressVO)request.getAttribute("AddressVO");
       double rate_point = (double)request.getAttribute("rate_point");
       int countWishlist = -1;
      
       if(isLogined){
         countWishlist= (int)request.getAttribute("countWishlist"); 
      }
       
       Map<String, Object> loginInfo = (Map<String, Object>) session.getAttribute("loginInfo");
      int memNo = 0; 
       if(loginInfo==null){
          session.setAttribute("isLogined", false);
       }
      if(loginInfo != null){ // 로그인 한 경우 
          MemberVO memberVO = (MemberVO)loginInfo.get("memberInfo"); //로그인한사람 회원   
           memNo = memberVO.getMem_no(); // 회원번호 얻어오기
      }
       
       DecimalFormat formatter = new DecimalFormat("###,###,###,###");
       int price = svo.getService_price();
       String service_price = formatter.format(price);
       
       int expertNo = svo.getMem_no();
       int serviceNo = svo.getService_no();
       
   %>
   
    <script>
    var memNo = <%=memNo%>
    var expertNo = <%=expertNo%>
    var serviceNo =  <%=serviceNo%>
    
	// 전문가의 서비스 -> 주소 api 조회해서 가져올 정보로 지도 바꿔두기	
	function relayout(latitude,longitude) {    
	    map.relayout();
	    coords = new kakao.maps.LatLng(latitude, longitude);
        map.setCenter(coords);
        marker.setPosition(coords);
	}
  
	 $(function(){
			if(<%= countWishlist%>==1){
				$('#redheart').show();
			}else{
				$('#whiteheart').show();
			}
   		
 		$('.heart').on('click',function(){
   			if($(this).attr('id')=='redheart'){
   				$('#redheart').hide()
   				$('#whiteheart').show();
   				change($(this).attr("value"));
   				
   			}else{
   				$('#whiteheart').hide();
   				$('#redheart').show();
   				change($(this).attr("value"));
   			}
   			
   			
   		}) 
   		
   		
   		
   change = function(val){
 			
   			$.ajax({
   				url : '<%= request.getContextPath()%>/service/memWishlist.do',
   				data : {"serviceNo" : '<%= svo.getService_no()%>',
   						 "memNo" :  memNo ,
   						 "countWishlist" : val },
   				type : 'get',
   				success : function(res){
   					
//    					if(res==1){
//    						alert("찜하기");
//    					}
   					// 1. 디테일 화면을 불러올때 회원이 이 서비스를 찜 했는지 판단 - DB
   					// select count(*) from 위시리스트 where service_no = 선택한 서비스 and mem_no = 로그인한 회원번호
   					// 2. 1번의 결과에 따라 해당하는 이미지(편한 방법으로 하시면 됩니다)를 보여줌 - js
   					
   					// 3. 이미지를 누르면 1번의 결과에 따라 DB의 정보를 반대로 해주면 된다.
   					// 1이면 delete 0이면 insert
   					// -> 있으ㅕㅁㄴ 삭제하고 없으면 insert하고
   				},
   				error : function(xhr){
   					alert("상태:" + status)
   				},
   				dataType : 'text'
   			})
 		} 		
		 
		//*** 결제 진행
		$('#kakaoPay').on('click',function(){
			checkbox = $('input[id=checkbox]');
			check = checkbox.is(':checked');
			
			// 체크사항 확인
			if(! check){
				alert("주문 내용 확인사항을 체크해주세요!");
				return false;
			}else if(memNo == 0){	 	
				alert('로그인을 해주세요!');
				return false;
			}
 			// 로그인정보로 회원번호 가져오기
 			// 결제금액
			lostpay = $('#paybox').val();
			lostpay = lostpay.replaceAll(",","");
			
			// 서비스명
			serviceName = $('#service_name').text();
			serviceNo =  <%=svo.getService_no()%>;
			
			console.log('서비스번호 : ' + serviceNo);
			console.log('서비스명 : ' + serviceName);
			console.log('결제금액' + lostpay);
			console.log('회원번호' + memNo);
			
			payResult = PayService(memNo,serviceName);
		})
		
		
		$('#report').on('mouseover',function(){
			if(memNo == 0){	 	
				alert('로그인 이후에 이용하실 수 있습니다.');
				return false;
			}
		})
		
		$('#chatting').on('mouseover',function(){
			console.log(memNo);
			
			if(memNo == 0){	 	
				alert('로그인 이후에 이용하실 수 있습니다.');
				return false;
			}
			
			
		})
		
		$('#reportSubmit').on('click',function(){
			$('#reportModalForm').serialize();
			$('#reportModalForm').submit();
		})
	})

</script>

<style type="text/css">
.serviceInfo{
	font-size: small;
}
.serviceSpan{
	font-size: large;
}
</style>    
</head>
<body>
<jsp:include page="../../../serviceSidebar.jsp"></jsp:include>
<jsp:include page="../../../header.jsp"></jsp:include>

<!-- Content Start -->
<div class="content col-sm-1 w-85 mx-3">
		<div class="bg-light text-center rounded p-4" style="width: 1350px; margin-left: 15%;">
				<div class="row">
					<div class="col-sm grid-margin stretch-card">
						<div class="card">
							<div class="card-body border-bottom">
							</div>
							<div class="container-fluid pt-4 px-4">
								<div class="row g-4 pb-4" style="padding: 3%;">
									<main class="col col-sm col-3" style="border: 1px solid lightgray; padding-top: 1%;">
										<img alt="profileImage" id="preview" style="width: 100%; height: 500px;" src="<%=request.getContextPath() %>/files/imageView.do?file_category_no=3&col=SERVICE_NO&no=<%=svo.getService_no()%>">
											<!-- 서비스 정보 -->
											<!-- <div class="row"> -->
											<div class="card">
												<div class="col-xl grid-margin stretch-card" style="padding : 10px;">
													<div class="card-body">
														<div class="d-flex justify-content-between align-items-center flex-wrap">
															<div class="card-body" style="text-align:left; overflow: auto;">
																<span class="serviceSpan"><b>서비스분류</b></span><br/>
																<div class="serviceInfo"><%=svo.getService_category_main_name()%>><%=svo.getService_category_sub_name() %></div><br>
																<hr/>
																<span class="serviceSpan"><b>서비스명</b></span><br/>
																<div class="serviceInfo"><%=svo.getService_name() %></div><br>
																<hr/>
																<span class="serviceSpan"><b>서비스설명</b></span><br/>
																<div class="serviceInfo"><%=svo.getService_info() %></div><br>
																<hr/>
																<span class="serviceSpan"><b>서비스가격</b></span><br/>
																<div class="serviceInfo"><%=service_price %>원</div><br/>
																<hr/>
																<span class="serviceSpan"><b>전문가 학력</b></span><br/>
																<div class="serviceInfo"><%=evo.getExpert_education() %></div><br/>
																<hr/>
																<span class="serviceSpan"><b>전문가 경력</b></span><br/>
																<div class="serviceInfo"><%=evo.getExpert_career() %></div><br/>
																<hr/>
																<span class="serviceSpan"><b>서비스일정</b></span><br/>
																<div class="serviceInfo"><%=evo.getExpert_contact() %></div><br/>
																
				                                                <script>
				                                                   viewSchedule(expertNo);
				                                                </script>
				                                                <div id="calendar"></div>
				                                                <hr/>
																
																<span class="serviceSpan"><b>전문가 자격증</b></span><br/>
																<div class="serviceInfo"><%=evo.getExpert_license() %></div><br/>
																<hr/>
																<span class="serviceSpan"><b>전문가 서비스 지역</b></span><br/>
																	<div>
																		<%if(avo.getAddr_longi() != "없음" && !avo.getAddr_longi().equals("없음")){%>
																			<jsp:include page="./mapView.jsp"></jsp:include>
																			<script>
																				longitude =<%=avo.getAddr_longi()%>;
																				latitude = <%=avo.getAddr_lati()%>;
																				relayout(longitude,latitude);
																			</script>							                	
																		<%}else{%>
																			<div>등록된 정보가 없습니다.</div><br/>
																		<%} %>
																	</div>
																	<hr/>
																<span class="serviceSpan"><b>후기 및 서비스평점 </b></span>
																<div class="serviceInfo">
																<div class="star-container " style="position: relative; display: inline-block; vertical-align: middle; padding-left: 0px; padding-right: 0px;"><svg viewBox="0 0 24 24" class="widget-svg" style="width: 20px; height: 20px; transition: transform 0.2s ease-in-out 0s;"><path class="star" d="M8.37094152,8.12482574 L2.52598096,8.59636398 L2.36821881,8.6135218 C0.881583763,8.81867772 0.513822851,10.1467426 1.72605142,11.1443161 L6.11068071,14.7526934 L4.80553251,20.0682859 L4.77348322,20.2161997 C4.50052597,21.673724 5.6402616,22.4726949 6.9887771,21.699537 L12.00271,18.8250573 L17.0166429,21.699537 L17.1506515,21.7715841 C18.4829447,22.4403279 19.5680516,21.5674348 19.1998875,20.0682859 L17.8937294,14.7526934 L22.2793686,11.1443161 L22.3984321,11.0405714 C23.4954951,10.0270601 23.0352205,8.72174778 21.479439,8.59636398 L15.6334685,8.12482574 L13.3880977,3.09014615 C12.7393731,1.6361626 11.2656405,1.63707337 10.6173223,3.09014615 L8.37094152,8.12482574 Z" style="fill: rgb(255, 212, 0); transition: fill 0.2s ease-in-out 0s;"></path></svg></div>
																<div class="star-container" style="position: relative; display: inline-block; vertical-align: middle; padding-left: 0px; padding-right: 0px;"><svg viewBox="0 0 24 24" class="widget-svg" style="width: 20px; height: 20px; transition: transform 0.2s ease-in-out 0s;"><path class="star" d="M8.37094152,8.12482574 L2.52598096,8.59636398 L2.36821881,8.6135218 C0.881583763,8.81867772 0.513822851,10.1467426 1.72605142,11.1443161 L6.11068071,14.7526934 L4.80553251,20.0682859 L4.77348322,20.2161997 C4.50052597,21.673724 5.6402616,22.4726949 6.9887771,21.699537 L12.00271,18.8250573 L17.0166429,21.699537 L17.1506515,21.7715841 C18.4829447,22.4403279 19.5680516,21.5674348 19.1998875,20.0682859 L17.8937294,14.7526934 L22.2793686,11.1443161 L22.3984321,11.0405714 C23.4954951,10.0270601 23.0352205,8.72174778 21.479439,8.59636398 L15.6334685,8.12482574 L13.3880977,3.09014615 C12.7393731,1.6361626 11.2656405,1.63707337 10.6173223,3.09014615 L8.37094152,8.12482574 Z" style="fill: rgb(255, 212, 0); transition: fill 0.2s ease-in-out 0s;"></path></svg></div>
																<div class="star-container" style="position: relative; display: inline-block; vertical-align: middle; padding-left: 0px; padding-right: 0px;"><svg viewBox="0 0 24 24" class="widget-svg" style="width: 20px; height: 20px; transition: transform 0.2s ease-in-out 0s;"><path class="star" d="M8.37094152,8.12482574 L2.52598096,8.59636398 L2.36821881,8.6135218 C0.881583763,8.81867772 0.513822851,10.1467426 1.72605142,11.1443161 L6.11068071,14.7526934 L4.80553251,20.0682859 L4.77348322,20.2161997 C4.50052597,21.673724 5.6402616,22.4726949 6.9887771,21.699537 L12.00271,18.8250573 L17.0166429,21.699537 L17.1506515,21.7715841 C18.4829447,22.4403279 19.5680516,21.5674348 19.1998875,20.0682859 L17.8937294,14.7526934 L22.2793686,11.1443161 L22.3984321,11.0405714 C23.4954951,10.0270601 23.0352205,8.72174778 21.479439,8.59636398 L15.6334685,8.12482574 L13.3880977,3.09014615 C12.7393731,1.6361626 11.2656405,1.63707337 10.6173223,3.09014615 L8.37094152,8.12482574 Z" style="fill: rgb(255, 212, 0); transition: fill 0.2s ease-in-out 0s;"></path></svg></div>
																<div class="star-container" style="position: relative; display: inline-block; vertical-align: middle; padding-left: 0px; padding-right: 0px;"><svg viewBox="0 0 24 24" class="widget-svg" style="width: 20px; height: 20px; transition: transform 0.2s ease-in-out 0s;"><path class="star" d="M8.37094152,8.12482574 L2.52598096,8.59636398 L2.36821881,8.6135218 C0.881583763,8.81867772 0.513822851,10.1467426 1.72605142,11.1443161 L6.11068071,14.7526934 L4.80553251,20.0682859 L4.77348322,20.2161997 C4.50052597,21.673724 5.6402616,22.4726949 6.9887771,21.699537 L12.00271,18.8250573 L17.0166429,21.699537 L17.1506515,21.7715841 C18.4829447,22.4403279 19.5680516,21.5674348 19.1998875,20.0682859 L17.8937294,14.7526934 L22.2793686,11.1443161 L22.3984321,11.0405714 C23.4954951,10.0270601 23.0352205,8.72174778 21.479439,8.59636398 L15.6334685,8.12482574 L13.3880977,3.09014615 C12.7393731,1.6361626 11.2656405,1.63707337 10.6173223,3.09014615 L8.37094152,8.12482574 Z" style="fill: rgb(255, 212, 0); transition: fill 0.2s ease-in-out 0s;"></path></svg></div>
																<div class="star-container" style="position: relative; display: inline-block; vertical-align: middle; padding-left: 0px; padding-right: 0px;"><svg viewBox="0 0 24 24" class="widget-svg" style="width: 20px; height: 20px; transition: transform 0.2s ease-in-out 0s;"><path class="star" d="M8.37094152,8.12482574 L2.52598096,8.59636398 L2.36821881,8.6135218 C0.881583763,8.81867772 0.513822851,10.1467426 1.72605142,11.1443161 L6.11068071,14.7526934 L4.80553251,20.0682859 L4.77348322,20.2161997 C4.50052597,21.673724 5.6402616,22.4726949 6.9887771,21.699537 L12.00271,18.8250573 L17.0166429,21.699537 L17.1506515,21.7715841 C18.4829447,22.4403279 19.5680516,21.5674348 19.1998875,20.0682859 L17.8937294,14.7526934 L22.2793686,11.1443161 L22.3984321,11.0405714 C23.4954951,10.0270601 23.0352205,8.72174778 21.479439,8.59636398 L15.6334685,8.12482574 L13.3880977,3.09014615 C12.7393731,1.6361626 11.2656405,1.63707337 10.6173223,3.09014615 L8.37094152,8.12482574 Z" style="fill: rgb(255, 212, 0); transition: fill 0.2s ease-in-out 0s;"></path></svg></div>
																<%=rate_point %>
																</div>
																
																<br/>
																<div class="serviceInfo">
																
																<div></div> <!-- 후기 게시판 불러오기 -->
																<hr/>
														 </div>
													</div>
												</div>
											</div>
										</div>
											<!--  </div --> <!--  -->
									</main>
									
									<aside class="col col-sm-auto asidemin" style="border:1px solid lightgray;">
									
										<div class="card">
											<div class="col-xl grid-margin stretch-card">
											  <div>
												<div id="buttonGroup" style="text-align: right; margin-top : 5px;">
													<div class="position-relative">
														 <h6 class="mb-0" style="text-align: right;">
														 <img class="rounded-circle" src="<%=request.getContextPath() %>/files/imageView.do?mem_no=<%=svo.getMem_no() %>&file_category_no=4" alt="" style="width: 50px; height: 40px;">
										           		<label style="margin-right: 35%; margin-top: 5%;"><%=svo.getMem_name() %> 전문가</label>
													<button type="button" id="report" class="btn btn-square btn-outline-danger m-2"  data-bs-toggle="modal" data-bs-target="#reportModal"><img src="<%=request.getContextPath() %>/img/service/siren.png" style="height:20px; filter: opacity(0.5) drop-shadow(0 0 0 #ff0000);"></button>
													<%if(isLogined){ %>
														<img src="<%= request.getContextPath() %>/img/빨간하트.png" id="redheart" class="heart" style="width: 50px; display: none;" value="1">
														<img src="<%= request.getContextPath() %>/img/빈하트.png" id="whiteheart" class="heart" style="width: 50px; display : none;" value="0">
													<% }%>
											        </h6></div>
												</div><br>
													<h5 class="mb-2 mb-md-0 text-uppercase font-weight-medium" style="word-break : keep-all;"><%=svo.getService_name() %></h5>
											  </div>
												<div class="card-body d-grid gap-2" style="text-align:left">
													<button class="btn btn-success btn-block w-100 m-2" type="button" style="background-color : teal;" data-bs-toggle="modal" data-bs-target="#exampleModal" id="action">구매하기</button>
													<button type="button" id="chatting" class="btn btn-outline-success w-100 m-2" data-bs-toggle="modal" data-bs-target="#chatspace" chatNo="<%=memNo %>" target="<%=evo.getMem_no()%>" onclick="chatWinOpen(this);">채팅으로 문의</button>
												</div>
												
												
												<hr>
												<div style="font-size: small; padding: 3%; text-align: left;">
													<span><b>취소,환불규정</b></span>
													<p>01. 전문가와 의뢰인 간의 상호 협의 후 청약철회가 가능합니다. </p>
													<p></p>
													<p>02. 전문가의 귀책사유로 디자인작업을 시작하지 않았거나 혹은 이에 준하는 보편적인 관점에서 심각하게 잘못 이행한 경우 결제 금액 전체 환불이 가능합니다. </p>
													<p></p>
													<p>03. 전문가가 작업 기간 동안 지정된 서비스를 제공하지 못할 것이 확실한 경우 지급받은 서비스 비용을 일할 계산하여 작업물 개수와 작업 기간 일수만큼 공제하고 잔여 금액을 환불합니다. </p>
													<p></p>
													<p>04. 서비스 받은 항목을 공제하여 환불하며, 공제 비용은 정가 처리됩니다.
													<p>가. 소비자 피해 보상 규정에 의거하여 작업물 원본의 멸실 및 작업 기간 미이행 및 이에 상응하는 전문가 책임으로 인한 피해 발생 시, 전액 환불 </p>
													<p>나. 시안 작업 진행된 상품 차감 환불
													<p>ⓐ. '디자인'에 대한 금액이 서비스 내 별도 기재가 되지 않았거나, 디자인 상품 패키지 내 수정 횟수가 1회(1회 포함) 이상인 서비스 상품의 시안 or 샘플이 제공된 경우</p>
													<p>→ 구매금액의 10% 환불(디자인 비용이 별도 기재되어 있는 경우, 해당금액 차감 후 환불)</p>
													<p>※ 시안 제공 및 수정이 추가로 이뤄진 경우 환불 금액내 수정 횟수에 따라 분할하여 환불. v
													<p></p>
													<p>05. 주문 제작 상품 등 서비스 받은 항목이 없으며, 결제 후 1일 이내 작업이 진행되기 전 시점은 전액 환불 가능. </p>
													<p></p>
													<p>06. 다만, 환불이 불가능한 서비스에 대한 사실을 표시사항에 포함한 경우에는 의뢰인의 환불요청이 제한될 수 있습니다. </p>
													<p>가. 고객의 요청에 따라 개별적으로 주문 제작되는 재판매가 불가능한 경우(인쇄, 이니셜 각인, 사이즈 맞춤 등) v
													<p>ⓐ. 주문 제작 상품 특성상 제작(인쇄 등) 진행된 경우. 
													<p>ⓑ. 인쇄 색상의 차이 : 모니터의 종류에 따라 색상의 차이가 발생하며,인쇄 시마다 합판 인쇄 방법의 특성상 색상 표현의 오차가 발생함. </p>
													<p>ⓒ. 디자인 서비스이며 수정 횟수가 존재하지 않았던 상품일 경우 시안 수령 후 환불 불가</p>
												</div>
											</div>
										</div>
									</aside>
								</div>
							</div>
						</div>
			           <!-- Back to Top -->
			           <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top" style="width: 50px;"><i class="bi bi-arrow-up"></i></a>
					</div>
				</div>
		</div>
	</div>
<!-- content End -->	
     
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">서비스 결제 진행창</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
      	<form id="modalForm" action="<%=request.getContextPath() %>/cart/order.do" method="post">
       <div class="card-body border-bottom">
      	
      	<div><b>서비스 정보</b></div>
      	<img alt="profileImage" id="preview" style="width: 40%; height: auto;" src="<%=request.getContextPath() %>/files/imageView.do?file_category_no=3&col=SERVICE_NO&no=<%=svo.getService_no()%>">
      	
      	<div>서비스명</div>
      	<div id="service_name"><%=svo.getService_name() %></div>
      	
      	<div>서비스 설명</div>
      	<div id="service_info"><%=svo.getService_info() %></div>
      	
      	</div>
        <div class="modal-body">
      	 <div><b>가격 정보</b></div>
      	 <div>총 결제금액</div>
      	 <div><%=service_price %>원</div>
      	</div>
		 	<div class="OrderSummary__agreement-wrapper">
	  			<div class="OrderSummary__agreement-checkbox-wrapper">
				  <div class="awesome-checkbox">
       				 <label for="checkbox">
	                     <input class="checkbox" type="checkbox" id="checkbox">
	                     	<h5> 주문 내용을 확인하였으며, 결제에 동의합니다. (필수)</h5>
      				 </label>
  				  </div>
		       </div>
		   </div>
      	
      	<!-- 결제에 필요한 정보 -->
      	<input type="hidden" id="memberNo" name="serviceNo" value="<%= svo.getService_no() %>">
      	<input type="hidden" name="memNo" value="<%=memNo %>">
      	<input type="hidden" id="paybox" name="servicePrice" value="<%=service_price %>">
      	
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
        <button type="button" class="btn btn-warning" id="kakaoPay">카카오페이</button>
      </div>
      </form>
      </div>
     </div>
   </div>
 </div>
 
 
<!-- Report Modal -->
<div class="modal fade" id="reportModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-md" style="width:500px; position:relative; left:300px; top:130px;">
		<div class="modal-content">
			<div class="modal-header">
				<h1 class="modal-title fs-5" id="exampleModalLabel">신고하기</h1>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form id="reportModalForm" action="<%=request.getContextPath() %>/member/insertReport.do" method="post">
					<div class="card-body">
						<!-- 신고 카테고리 -->
						<label for="reportedMem">신고할 회원명
						(회원번호)</label>
						<div id="reportedMem" style="padding-left: 30px;"><%=mvo.getMem_name() %> (<%=mvo.getMem_no() %>)</div>
						
						<label for="reportCategoryNo">신고 항목</label>
						<select id="reportCategoryNo" name="reportCategoryNo" class="form-select mb-3">
							<option value="1">스팸, 홍보 도배</option>
							<option value="2">음란물 게시</option>
							<option value="3">불법정보포함</option>
							<option value="4">욕설,생명경시,혐오,차별적 표현</option>
							<option value="5">개인정보 노출</option>
						</select>
						<label for="reportContent">신고 내용</label>
						<textarea class="form-control mb-3" id="reportContent" name="reportContent" type="text" placeholder="신고 내용 입력.." aria-label="default input example" style="width : 433px; height : 200px;"></textarea>
						
						<!-- 신고할 회원 -->
						<input type="hidden" name="memNoBad" value="<%=evo.getMem_no()%>">
						<input type="hidden" name="memNoDo" value="<%=memNo %>">
						<input type="hidden" name="serviceNo" value="<%=svo.getService_no()%>">
					</div>
					
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" style="height:45px;" data-bs-dismiss="modal">취소</button>
						<button type="button" class="btn btn-primary" style="height:45px;" id="reportSubmit">제출</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
 
 
<div class="modal fade" id="chatspace" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-md" style="width:500px; position:relative; left:300px; top:130px;">
	    <div class="modal-content">
			<div class="modal-header">
		        <h1 class="modal-title fs-5" id="exampleModalLabel">문의하기</h1>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>	
			<div class="modal-body" style="width: 100%;height: 600px;">
				<!--  채팅내역 -->
				<div id=chatWindow class="h-100 bg-light rounded p-4 chatList" style="overflow: auto;"></div>
				<div class="chatText">
				</div>
			</div>
			<div class="modal-footer">
				<input type="text" id="chatMessage" onkeyup="enterKey()"/>
				<button type="button" id="sendBtn" class="btn btn-primary" style="width:100px; height:45px;" onclick="sendMessage()">전송</button>
				<button type="button" id="listBtn" class="btn btn-secondary" style="height:45px;" data-bs-dismiss="modal">돌아가기</button>
			</div>
		</div>
	</div>
</div>
	
	
    <jsp:include page="/footer.jsp"></jsp:include>
</body>

    
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
</html>