package schedule.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import schedule.service.ScheduleService;
import schedule.service.ScheduleServiceImpl;
import vo.ScheduleVO;
public class ScheduleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=utf-8");
	    
		
		// 스케쥴 정보 불러오기 - 전체리스트
		if(command.equals("/schedule/scheduleSelect.do")) {
			selectSchedule(req,resp);
		}
		// 스케쥴 정보 입력하기
		if(command.equals("/schedule/scheduleInsert.do")) {
			insertSchedule(req,resp);
		}
		// 스케쥴 정보 수정하기
		if(command.equals("/schedule/scheduleUpdate.do")) {
			updateSchedule(req,resp);
		}
		// 스케쥴 정보 삭제하기
		if(command.equals("/schedule/scheduleDelete.do")) {
			deleteSchedule(req,resp);
		}
				
	}

	private void deleteSchedule(HttpServletRequest req, HttpServletResponse resp) {
		ScheduleService service = ScheduleServiceImpl.getInstance();
		int schedule_no = Integer.parseInt(req.getParameter("no"));
		
		int res = service.deleteSchedule(schedule_no);
		
	}

	private void updateSchedule(HttpServletRequest req, HttpServletResponse resp) {
		ScheduleService service = ScheduleServiceImpl.getInstance();
		Gson gson = new Gson();
		ScheduleVO vo = gson.fromJson(req.getParameter("datas"), ScheduleVO.class);
		
		int res = service.updateSchedule(vo);
	}
	
	private void insertSchedule(HttpServletRequest req, HttpServletResponse resp) {
		String title = req.getParameter("title");
		String start = req.getParameter("start");
		String end = req.getParameter("end");
		int memNo = Integer.parseInt(req.getParameter("mem_no"));
		
		ScheduleService service = ScheduleServiceImpl.getInstance();
		
		ScheduleVO scvo = new ScheduleVO();
		scvo.setSchedule_name(title);
		scvo.setSchedule_start(start);
		scvo.setSchedule_end(end);
		scvo.setMem_no(memNo);

		int res = service.insertSchedule(scvo);
		
		PrintWriter out;
		try {
			out = resp.getWriter();
			out.print(res);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void selectSchedule(HttpServletRequest req, HttpServletResponse resp) {
		int mem_no = Integer.parseInt(req.getParameter("mem_no"));
		ScheduleService service = ScheduleServiceImpl.getInstance();
		List<ScheduleVO> scheduleList = service.selectSchedule(mem_no);
		
		req.setAttribute("scheduleList", scheduleList);
		try {
			req.getRequestDispatcher("/WEB-INF/view/schedule/scheduleJson.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
