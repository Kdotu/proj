package schedule.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import config.SqlMapClientFactory;
import vo.ScheduleVO;

public class ScheduleDAOImpl implements ScheduleDAO {
	private SqlMapClient smc; 
	private static ScheduleDAOImpl dao;
	
	private ScheduleDAOImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static ScheduleDAOImpl getInstance() {
		if(dao == null) dao = new ScheduleDAOImpl();
		return dao;
	}
	
	
	@Override
	public List<ScheduleVO> selectSchedule(int num) {
		List<ScheduleVO> list = null;
		try {
			list = smc.queryForList("schedule.selectSchedule", num);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertSchedule(ScheduleVO scvo) {
		Object obj= null;
		int cnt = 0;
		
		try {
			obj = (Object)smc.insert("schedule.insertSchedule",scvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(obj == null) cnt = 1;
		return cnt;
	}

	@Override
	public int deleteSchedule(int schedule_no) {
		int cnt = 0;
		
		try {
			cnt = smc.delete("schedule.deleteSchedule", schedule_no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int updateSchedule(ScheduleVO vo) {
		int cnt = 0;
		
		try {
			cnt = smc.update("schedule.updateSchedule", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	
}
