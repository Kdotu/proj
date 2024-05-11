<%@page import="java.text.DecimalFormat"%>
<%@page import="vo.ServiceVO"%>
<%@page import="vo.AddressVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서비스 등록</title>
<!-- 모달창 링크 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">

    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">
    
    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

<%--     <!-- Libraries Stylesheet -->
    <link href="<%= request.getContextPath() %>/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" /> --%>

    <!-- Customized Bootstrap Stylesheet -->
    <link href="<%= request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet">
    
<script src="${pageContext.request.contextPath }/js/jquery-3.6.1.min.js"></script>
<script src="${pageContext.request.contextPath }/js/mapSearchwithMarker.js"></script>
<!-- <script src="../js/jquery.serializejson.js"></script> -->
<script src="<%= request.getContextPath() %>/js/main.js"></script>




<!-- 모달창링크 -->

<%
ServiceVO serviceVo = (ServiceVO)request.getAttribute("serviceInfo");
String selectSubCategoryName = (String)request.getAttribute("selectSubCategoryName");
AddressVO addrVo = (AddressVO)request.getAttribute("updateAddr");


%>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script> 
<script>
 $(function(){
	function relayout() {    
	    map.relayout();
	    coords = new kakao.maps.LatLng(36.3225501550863, 127.403284156852);
        map.setCenter(coords);
        marker.setPosition(coords);
		customOverlay.setPosition(coords);
	}
	 $('#action').on('click',function(){
	 	 $('#exampleModal').show();
	 	 
		 $.ajax({
			url : '<%=request.getContextPath() %>/service/selectMainCategory.do',
			type : 'get',
			success : function(res){
				code="";
				$.each(res,function(i,v){
					code += "<option value='" + v.service_category_main_no + "'>" + v.service_category_main_name + "</option>";
				})
				$('#main').html(code);
				$('#main').trigger('change');
				
			},
			error : function(xhr){
				alert("action 상태" + xhr.status);
			},
			dataType : 'json'
		 })//ajax끝
	 });
	 
	 $('#addr').on('click',function(){
		 relayout();
	 })
	 
	 $('#main').on('change',function(){
	
		 $.ajax({
			url : '<%= request.getContextPath()%>/service/serviceSubCategory.do',
			type : 'get',
			data : { "mainCategory" : $(this).val() },
			success : function(res){
				code="";
				$.each(res,function(i,v){
					code+= "<option value='"+v.service_category_sub_no+"'>" + v.service_category_sub_name + "</option>";
				})
				$('#sub').html(code);
			},
			error : function(xhr){
				alert("action 상태" + xhr.status);
			},
			dataType : 'json'
		 })
	 }) //sub끝
	 $('#option').on('click',function(){
		 var optionSub = $('#sub option:selected').html();
		 
		 var mainValue = $('#main option:selected').val();
		 var subValue = $('#sub option:selected').val();
		 
	 	 $('#exampleModal').modal('hide');
		 $('#servicecategory').val(optionSub);
		 $('#service_category_sub_no').val(subValue);
		 
	 })
	 
	 $('#serviceName').on('keyup',function(){
		var serviceName = $(this).val().trim();
			$("#textLengthCheck").html("(" + serviceName.length + "/ 최대 30글자)"); //실시간 글자수 카운팅
			
		if(serviceName.length<1){
			alert("서비스 이름을 입력하세요");
			return;
		}
		
		if(serviceName.length>30){
			$(this).css('border','1px solid red');
			alert("상품이름은 30글자까지 입력가능합니다.");
			$(this).val(serviceName.substring(0,20))
			$('#textLengthCheck').html("(30 / 최대 30글자)");
		}
	 })
	 
	 $('#serviceInfo').on('keyup',function(){
		var serviceInfo = $(this).val().trim();
		
		if(serviceInfo.length<300){
			$(this).css('border','1px solid red');
			alertMsg = "";
			alertMsg += "300글자 이상을 입력해주세요! ";
			$('#insert').prop('disabled', true);
			
		}else{
			$('#insert').prop('disabled', false);
		}
		
		
		
		
		
		$('#textLengthCheck2').html(alertMsg + " ("+serviceInfo.length + "/ 최소 300글자)");
		
	 })
	 $('#servicePrice').on('keyup',function(){
		 
		 if($('#servicePrice').val() == 0){
			 $(this).css('border','1px solid red');
			 alert('상품 금액을 입력해주세요!');
			 return;
		 }	
	 })
	 
 })
 
 function readURL(input) {
   if (input.files && input.files[0]) {
      var reader = new FileReader();
      reader.onload = function(e) {
         document.getElementById('preview').src = e.target.result;
      };
      reader.readAsDataURL(input.files[0]);
   } else {
      document.getElementById('preview').src = "";
   }
};
 </script>
</head>

<body>

<jsp:include page="/header.jsp"></jsp:include>

      <div class="content col-sm-1 w-85 mx-3">
          <div class="container-fixed pt-4 px-4 container-center-align">
              <div class="bg-light rounded p-4">
				<div class="main-panel">
			        <div class="content-wrapper">
			          <div class="row g-4">
			            <div class="col-sm grid-margin stretch-card">
			                <div class="card-body border-bottom"> <!--구역 나눔선-->
			                	<div class="bg-light rounded h-100 p-4">
						            <h4 class="mb-4">상품정보등록</h4>
						            <form action="<%= request.getContextPath() %>/service/serviceUpdate2.do" method="post"  enctype="multipart/form-data">
						                <div class="mb-3">
						                    <label for="exampleInputEmail1" class="form-label">상품카테고리를 선택해주세요(필수)</label><br>
											<button type="button" class="btn btn-primary btn" data-bs-toggle="modal" data-bs-target="#exampleModal" id="action">
											  서비스선택
											</button>
						                    <input type="text" class="form-control" id="servicecategory" aria-describedby="emailHelp" readonly>
						                    <input type="hidden" id="service_category_sub_no" name="service_category_sub_no"> 
						                </div>
						                
						                 <div class="mb-3">
						                    <label for="serviceName" class="form-label">상품이름을 입력해주세요(필수)</label>
						                    <input type="text" class="form-control" id="serviceName"  name="serviceName" aria-describedby="emailHelp">
						                    <p id="textLengthCheck" class="textLengthCheck">( 0 / 최대 20자 )</p>
						                    <div id="NameCheck" class="form-text">*상품의 특징이 잘 나타나는 이름을 적어주세요. 이름이 매력적일수록 고객이 선택할 확률이 높아집니다.
						                                    </div>
						                </div>
						                 <div class="mb-3">
						                    <label for="mainImg" class="form-label">대표 메인 이미지를 등록해주세요</label><br/>
											<img alt="mainImg" id="preview"  name="mainImg">   <br/>
											<input class="form-control" type="file" id="files" name="files" onchange="readURL(this);" >    
											<div id="ImgCheck" class="form-text">*서비스와 관련되지 않은 이미지 또는 저작권/초상권을 침해하거나 타인에게 불쾌감을 주는 이미지는 반려 사유가 됩니다.
						                    </div>
						                </div>
						                                                                                              
						                <div class="mb-3">
						                    <label for="serviceInfo" class="form-label">제품 상세 설명을 작성해주세요(필수)</label>
						                    <textarea class="form-control" id="serviceInfo" name="serviceInfo" style="height: 150px;"></textarea>
						                    <p id="textLengthCheck2" class="textLengthCheck">( 0 / 최소 300자 )</p>
						                    <div id="infoCheck" class="form-text">*상품의 특장점, 서비스제공방식, 커리큘럼, 고객이 얻을 수 있는 이익과 혜택등 구체적인 정보를 안내해주세요
						                    </div>
									        </div>                                                                                              
									        <div class="mb-3">
									            <label for="servicePrice" class="form-label">상품가격을 입력해주세요(필수)</label>
									            <input type="text" class="form-control" id="servicePrice" name="servicePrice" aria-describedby="emailHelp">
									         <div id="priceCheck" class="form-text">*실제 상품 거래금액을 입력해주세요</div>   
									          </div>      
						          	
						                   <div class="mb-3">
											<div class="accordion" id="accordionExample">
											  <div class="accordion-item">
											    <h2 class="accordion-header" id="headingOne">
											      <button class="accordion-button collapsed" type="button" id="addr" name="addr" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
											       	서비스가 가능한 주소를 검색해주세요(선택)
											      </button>
											    </h2>
											        <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
												      <div class="accordion-body container-accordion container" style="overflow : hidden;">
												      
														<div class="item1">
														<jsp:include page="./mapRegist.jsp"></jsp:include>
														</div>
														<div class="item2" style="overflow : hidden;">
															<p><b>주소정보</b></p>
															<ul class="list-group list-group">
								                                <li class="list-group-item bg-transparent">
																	<input class="form-control"  type="text" readonly id="zip" name="addr_zip" placeholder="우편번호">
																	<input class="form-control"  type="text" readonly id="addr_base" name="addr_base" placeholder="기본주소1">
																	<input class="form-control"  type="text" readonly id="addr_detail" name="addr_detail" placeholder="기본주소1">
																	
																	<br>
																	<input type="button" class="btn btn-outline-info m-2" id="mapSearch" value="주소 검색"><br><br>
								                                </li>
								                            </ul>
														</div>														
												      </div>
												    </div>
											  	</div>
											  </div>
											 <input type="hidden" id="longitude" name="longitude">
											 <input type="hidden" id="latitude" name="latitude">
											 <input type="hidden" id="addrNO" name="addrNO" value="<%= addrVo.getAddr_no() %>">
											 <input type="hidden" id="serviceNo" name="serviceNo" value="<%= serviceVo.getService_no() %>">
						                </div>                                           
							        <button type="submit" class="btn btn-primary" id="update">상품 수정 완료</button>
							    </form>
							</div>
	              			</div>
				           </div>
						</div>				           
			           </div>
		           </div>
		        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
		    </div>
	    </div>
	   </div>
<!-- Button trigger modal -->

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
      
      <select class="form-select mb-3" aria-label="Default select example" id="main">
               </select>
					 <select class="form-select mb-3" aria-label="Default select example" id="sub">
               </select>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
        <button type="submit" class="btn btn-primary" id="option">서비스선택</button>
      </div>
    </div>
  </div>
</div>

<jsp:include page="/footer.jsp"></jsp:include>
   
<script>


// 주소 검색
$(function(){

	$('#mapSearch').on('click',function(){
		 mapSearch();
	})
	
	$('#servicecategory').val('<%= selectSubCategoryName %>');
	$('#serviceName').val('<%= serviceVo.getService_name() %>');
 	
	
	 aa ='<%= serviceVo.getService_info() %>';
	 aa = aa.replaceAll(/<br>/g, "\n");
	 $('#serviceInfo').val(aa);  

	<%-- $('#serviceInfo').val('<%= serviceVo.getService_info() %>');  --%>

	
	<%
     DecimalFormat formatter = new DecimalFormat("###,###,###,###");
	 int price = serviceVo.getService_price();
	 String service_price = formatter.format(price);
	%>
	
	
 	$('#servicePrice').val('<%= service_price %>'); 
	$("#service_category_sub_no").val('<%=serviceVo.getService_category_sub_no()%>');
	
	
  	$('#longitude').val('<%= addrVo.getAddr_longi()%>');
	$('#latitude').val('<%= addrVo.getAddr_lati() %>');

	
 	$('#zip').val('<%= addrVo.getAddr_zip()%>');
	$('#addr_base').val('<%= addrVo.getAddr_road()%>');
	$('#addr_detail').val('<%= addrVo.getAddr_dong()%>');  
	
	
	function relayout() {    
	    map.relayout();
	    coords = new kakao.maps.LatLng(<%= addrVo.getAddr_longi()%>, <%= addrVo.getAddr_lati() %>);
        map.setCenter(coords);
        marker.setPosition(coords);
		customOverlay.setPosition(coords);
	}
	
	 $('#addr').on('click',function(){
		 relayout();
	 })
	
	const input = document.querySelector('#servicePrice');
	input.addEventListener('keyup', function(e) {
	  let value = e.target.value;
	  value = Number(value.replaceAll(',', ''));
	  if(isNaN(value)) {
	    input.value = 0;
	  }else {
	    const formatValue = value.toLocaleString('ko-KR');
	    input.value = formatValue;
	  }
	})
	
})
</script>
</body>
</html>