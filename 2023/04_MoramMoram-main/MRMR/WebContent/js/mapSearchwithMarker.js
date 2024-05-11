/**
 * 
 */

mapSearch = function () {
    new daum.Postcode({
        oncomplete: function(data) {
            var addr = data.address; // 최종 주소 변수

	        var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
           		 document.getElementById("addr_detail").value = extraAddr;
            } else {
                extraAddr = '';
           		// document.getElementById("addr_detail2").style.display = 'none'; // 적용안됨
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('zip').value = data.zonecode;
            document.getElementById('addr_base').value = addr;
		
			
            // 주소로 상세 정보를 검색
            geocoder.addressSearch(data.address, function(results, status) { 
                // 정상적으로 검색이 완료됐으면
                if (status === daum.maps.services.Status.OK) {

                    var result = results[0]; //첫번째 결과의 값을 활용

                    // 해당 주소에 대한 좌표를 받아서
                    coords = new daum.maps.LatLng(result.y, result.x);
                    // 지도를 보여준다.
                    mapContainer.style.display = "block";
                    map.relayout();
                    // 지도 중심을 변경한다.
                    map.setCenter(coords);
                    // 마커를 결과값으로 받은 위치로 옮긴다.
                    marker.setPosition(coords)

					customOverlay.setPosition(coords)
					
					
					//console.log(coords);
					//console.log('좌표위치 검색 결과 : ' + coords);
					
					pDataSave();
                }
            });
        }
    }).open();
}

function pDataSave(){
		var pData = "";
		pData += coords;
		
		pDatas = pData.split(",")
		
		pDatas[0]=pDatas[0].replace("(","").replace(")","").trim();
		pDatas[1]=pDatas[1].replace("(","").replace(")","").trim();
		
		$('#longitude').val(pDatas[0]);				
		$('#latitude').val(pDatas[1]);	
   }



saveData = function(){
	
	$.ajax({
		url : 'http://192.168.145.36:80/test/mapSaveData.do',
		data : { id :  userId, 
				 longitude : pDatas[0], 
				 latitude : pDatas[1]} ,
		type : 'post',
		success : function(res){ 
			alert(res.sw)
		},
		error : function(xhr){
			alert("상태 : " + xhr.status)
		},
		dataType : 'json'
	})
	
}
