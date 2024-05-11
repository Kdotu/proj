/**
 * 
 */


var title;
var args;
var memNum;

selectSchedule = function(memNo){
   // 편집가능한 스케쥴
   // 회원정보를 넘겨줘서 일정 데이터를 받아온다.
   memNum = memNo;
   $.ajax({
      url : 'http://192.168.145.36:80/MRMR/schedule/scheduleSelect.do',
      data : {"mem_no" : memNo},
      type : 'post',
      success : function(res){
         if(res.flag=="ok"){
            console.log(res) // json 데이터 체크
            // 캘린더를 출력할 위치
             var calendarEl = document.getElementById('calendar');
         
            // 캘린더 생성
             var calendar = new FullCalendar.Calendar(calendarEl, {
               plugins: [ 'interaction', 'dayGrid', 'timeGrid' ],
               header: {
                 left: 'prev,next today',
                 center: 'title',
                 right: 'dayGridMonth,timeGridWeek,timeGridDay'
               },
               defaultDate: '2022-12-12',
               navLinks: true, // can click day/week names to navigate views
               selectable: true,
               selectMirror: true,
              editable: true,
              droppable: true,
      
               select: function(arg) {
                var eventId = 1 + Math.floor(Math.random() * 1000);
               args = arg;
            // 선택시 생기는 이벤트 함수
                console.log(arg)
            
                 title = prompt('Event Title:');
                 if (title) {
                   calendar.addEvent({
                     _id : eventId, 
                  title: title,
                     start: arg.start,
                     end: arg.end,
                     allDay: arg.allDay
                   })
                 }
               calendarSave(args,memNum);
                 calendar.unselect()
               },

                eventDrop: function (info){
               // 일정 수정---------------------------------------------
                   if(confirm("'"+ info.event.title +"' 일정을 수정하시겠습니까 ?")){
                    
               console.log(memNo)
                   var obj = new Object();

                   obj.schedule_name = info.event._def.title;
                   obj.schedule_start = info.event._instance.range.start;

                   start = info.event._instance.range.start;
               obj.schedule_start = dateConvert(start);
                   end = info.event._instance.range.end;
               obj.schedule_end = dateConvert(end);
               
               obj.schedule_no = info.event.extendedProps._id;
               obj.mem_no = memNo;               
   
                   }else{ // 새로고침
                       location.reload();
                   }
                   $(function modifyData() {
                       $.ajax({
                           url: "http://192.168.145.36:80/MRMR/schedule/scheduleUpdate.do",
                           method: "post",
                           data: {"datas" : JSON.stringify(obj)},
                     success : function(res){
                        if(res > 0) alert('일정 수정이 완료되었습니다.')
                     },
                     error : function(xhr){
                        alert('상태 : ' + xhr.status)
                     }
                       })
                   })
               },
                eventResize: function (info){
               if(confirm("'"+ info.event.title +"' 일정을 수정하시겠습니까 ?")){
                
                    var obj = new Object();

                   obj.schedule_name = info.event._def.title;

                   start = info.event._instance.range.start;
               obj.schedule_start = dateConvert(start);
                   end = info.event._instance.range.end;
               obj.schedule_end = dateConvert(end);

               obj.schedule_no = info.event.extendedProps._id;
               obj.mem_no = memNo;   
   
                    }else{
                        location.reload();
                    }
                    $(function modifyData() {
                        $.ajax({
                            url: "http://192.168.145.36:80/MRMR/schedule/scheduleUpdate.do",
                            method: "post",
                            data: {"datas" : JSON.stringify(obj)},
                     success : function(res){
                        if(res > 0) alert('일정 수정이 완료되었습니다.')
                     },
                     error : function(xhr){
                        alert('상태 : ' + xhr.status)
                     }
                        })
                    })
                },
               eventLimit: true, // allow "more" link when too many events

              // 일정 삭제---------------------------------------------   
              eventClick: function (info){
                        if(confirm("'"+ info.event.title +"' 일정을 삭제하시겠습니까 ?")){
                            // 확인 클릭 시
                            info.event.remove();
                             console.log(info.event);
                     var schedule_no = info.event.extendedProps._id;
                     console.log(schedule_no);
                        }
                        $(function deleteData(){
                            $.ajax({
                                url: "http://192.168.145.36:80/MRMR/schedule/scheduleDelete.do",
                                method: "post",
                                data: { "no" : schedule_no},
                                //contentType: 'application/json',
                        success : function(res){
                           if(res > 0) alert('일정 삭제가 완료되었습니다.')
                           
                        },
                        error : function(xhr){
                           alert('상태 : ' + xhr.status)
                        }
                            })
                        })
                    },
                        locale: 'ko',
                    eventRemove: function (obj) { // 이벤트가 삭제되면 발생하는 이벤트

                     },   
               events: // DB정보를 불러오면 될 것 같음
               res.datas
             });
             calendar.render();
         }
      },
      error : function(xhr){
         alert('상태  : ' + xhr.status)
      },
      dataType : 'json'
   }) // select ajax 끝
}   

// INSERT
calendarSave = function(args, memNo){
   // 정보값 빼서 저장할 형태로 만들어두기
   vStart= args.startStr;
   vEnd = args.endStr;
   
   // 해당 등록 정보를 db에 전송
   $.ajax({
      url : 'http://192.168.145.36:80/MRMR/schedule/scheduleInsert.do',
      type : 'post',
      data : { "title" : title, "start" : vStart, "end" : vEnd, "mem_no" : memNo },
      success : function(res){
         if(res > 0){
            alert('일정 등록이 완료되었습니다.')
            location.reload();
         }
      },
      error : function(xhr){
         alert('상태 : ' + xhr.status)
      },
      dataType : 'text'
   });
}


// date객체의 날짜를 변환해주는 함수
dateConvert = function(date){
   year = date.getFullYear();
   vmonths = date.getMonth()+1;
   vdates = date.getDate();
   
   console.log("년 : " + year)
   console.log("월 : " +vmonths)
   console.log("일 : " +vdates)
   date="";
   month= "";
   
   if(vmonths < 10){ 
      month += "0" + vmonths; 
   }else{
      month = vmonths;
   }
   if(vdates < 10) {
      date += "0" + vdates; 
   }else{
      date = vdates;
   }
   var res = year + '-' + month + '-' + date;
   return res;
}


viewSchedule = function(memNo){
   // 열람만 가능한 스케쥴
      $.ajax({
      url : 'http://192.168.145.36:80/MRMR/schedule/scheduleSelect.do',
      data : {"mem_no" : memNo},
      type : 'post',
      success : function(res){
         if(res.flag=="ok"){
            console.log(res) // json 데이터 체크
            // 캘린더를 출력할 위치
             var calendarEl = document.getElementById('calendar');
         
            // 캘린더 생성
             var calendar = new FullCalendar.Calendar(calendarEl, {
               plugins: [ 'interaction', 'dayGrid', 'timeGrid' ],
               header: {
                 left: 'prev,next today',
                 center: 'title',
                 right: 'dayGridMonth,timeGridWeek,timeGridDay'
               },
               defaultDate: '2022-12-12',
               navLinks: true, // can click day/week names to navigate views
               selectMirror: true,
              events: 
                  res.datas
                });
                calendar.render();
            }
         },
         error : function(xhr){
            alert('상태  : ' + xhr.status)
         },
         dataType : 'json'
      }) // select ajax 끝
   }



