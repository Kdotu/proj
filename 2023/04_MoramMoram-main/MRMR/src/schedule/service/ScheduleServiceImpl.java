package schedule.service;


import java.util.List;
import java.util.Map;

import schedule.dao.ScheduleDAO;
import schedule.dao.ScheduleDAOImpl;
import vo.ScheduleVO;

public class ScheduleServiceImpl implements ScheduleService{
	private ScheduleDAO dao;
	private static ScheduleServiceImpl service;
	
	public static ScheduleServiceImpl getInstance() {
		if(service == null) service = new ScheduleServiceImpl();
		return service;
	}
	private ScheduleServiceImpl() {
		dao = ScheduleDAOImpl.getInstance();
	}
	
	
	@Override
	public List<ScheduleVO> selectSchedule(int num) {
		return dao.selectSchedule(num);
	}
	
	@Override 
	public int insertSchedule(ScheduleVO scvo) { 
		return dao.insertSchedule(scvo); 
	}
	@Override
	public int deleteSchedule(int schedule_no) {
		return dao.deleteSchedule(schedule_no);
	}
	@Override
	public int updateSchedule(ScheduleVO vo) {
		return dao.updateSchedule(vo);
	}


}
