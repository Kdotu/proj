<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <!--  전문가가 자신의 서비스 위치를 보여줄 때 사용 -->
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
<style>
.customoverlay {position:relative;bottom:85px;border-radius:6px;border: 1px solid #ccc;border-bottom:2px solid #ddd;float:left;}
.customoverlay:nth-of-type(n) {border:0; box-shadow:0px 1px 2px #888;}
.customoverlay a {display:block;text-decoration:none;color:#000;text-align:center;border-radius:6px;font-size:14px;font-weight:bold;overflow:hidden;background: #d95050;background: #d95050 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 14px center;}
.customoverlay .title {display:block;text-align:center;background:#fff;margin-right:35px;padding:10px 15px;font-size:14px;font-weight:bold;}
.customoverlay:after {content:'';position:absolute;margin-left:-12px;left:50%;bottom:-12px;width:22px;height:12px;background:url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
</style>
</head>

<body>
<div style="width : 100%">
<div id="map" style="width:500px; height:350px; overflow:hidden"></div>
</div>
<!-- <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=805f435fff584ac35854286c3ec0233d&libraries=services"></script>

<script src="<%= request.getContextPath() %>/js/jquery-3.6.1.min.js"></script>
<script src="<%= request.getContextPath() %>/js/mapSearchwithMarker.js"></script>

<script>

var logitutde = 36.3225501550863;
var latitude = 127.40328415685;
var link = 'https://map.kakao.com/link/map/11394059';

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
  mapOption = { 
        center: new kakao.maps.LatLng(logitutde, latitude), // 지도의 중심좌표 // 중심좌표를 db에서 가져오기
//        center: new kakao.maps.LatLng(127.31232050686, 36.3904624559243), // 내가 바꾼 기본 좌표
        level: 4 // 지도의 기본 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); 

// 컨트롤창-------------------------------------------------------------------------------------------

//일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
var mapTypeControl = new kakao.maps.MapTypeControl();

// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
var zoomControl = new kakao.maps.ZoomControl();
map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

// 마커-------------------------------------------------------------------------------------------

/* var imageSrc = '../../images/check.png', // 마커이미지의 주소입니다 ==> 이미지 해상도 고려 */
 var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png', // 마커이미지의 주소입니다

	imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
    markerPosition = new kakao.maps.LatLng(logitutde, latitude); // 마커가 표시될 위치입니다
    
// 마커를 생성합니다
var marker = new kakao.maps.Marker({
  position: markerPosition,
  image: markerImage // 마커이미지 설정 
});

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);


// 커스텀 오버레이가 표시될 위치입니다 
var position = new kakao.maps.LatLng(logitutde, latitude);  // 마커와 같은 위치

// 커스텀 오버레이를 생성합니다
var customOverlay = new kakao.maps.CustomOverlay({
    map: map,
    position: position,
    yAnchor: 1 
});

// ------------------------------------------------------------------------------------------------

</script>
</body>
</html>