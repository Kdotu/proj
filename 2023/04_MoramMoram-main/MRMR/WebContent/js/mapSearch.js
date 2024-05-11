/**
 * 지도 정보를 불러올 때 사용하는 js파일
 */

	mapSearch = function () {
	    new daum.Postcode({
	        oncomplete: function(data) {
	            var addr = data.address; // 최종 주소 변수
				
				console.log(addr);
				console.log(data);
				
	            // 주소 정보를 해당 필드에 넣는다.
	            document.getElementById("address").value = addr;
	            // 주소로 상세 정보를 검색
	            geocoder.addressSearch(data.address, function(results, status) { 
	                // 정상적으로 검색이 완료됐으면
	                if (status === daum.maps.services.Status.OK) {
	
	                    var result = results[0]; //첫번째 결과의 값을 활용
	
	                    // 해당 주소에 대한 좌표를 받아서
	                    var coords = new daum.maps.LatLng(result.y, result.x);
	                    // 지도를 보여준다.
	                    mapContainer.style.display = "block";
	                    map.relayout();
	                    // 지도 중심을 변경한다.
	                    map.setCenter(coords);
	                    // 마커를 결과값으로 받은 위치로 옮긴다.
	                    marker.setPosition(coords)
	                }
	            });
	        }
	    }).open();
	}




/*

1. 미리 좌표를 찍은 지도 영역을 만들어둠
2. 주소 검색완료 시 해당 위치를 찍은 영역을 보이게 만듦

http://localhost/test/test/addrestTest/mapaddr.jsp
지번주소, 도로명주소,좌표



{"documents":[
	{"address":
		{"address_name":"대전 중구 오류동 169-4","b_code":"3014011300","h_code":"3014067000","main_address_no":"169","mountain_yn":"N","region_1depth_name":"대전","region_2depth_name":"중구","region_3depth_h_name":"오류동","region_3depth_name":"오류동","sub_address_no":"4","x":"127.40511971153","y":"36.3212054277473"},
		"address_name":"대전 중구 오류로 7","address_type":"ROAD_ADDR","road_address":
																			{"address_name":"대전 중구 오류로 7","building_name":"","main_building_no":"7","region_1depth_name":"대전","region_2depth_name":"중구","region_3depth_name":"오류동","road_name":"오류로","sub_building_no":"","underground_yn":"N","x":"127.40511971153","y":"36.3212054277473","zone_no":"34906"},
		"x":"127.40511971153","y":"36.3212054277473"}
		     ],
 "meta":{"is_end":true,"pageable_count":1,"total_count":1}}







*/