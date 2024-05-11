package schedule.service;

import java.util.List;
import java.util.Map;

import vo.ScheduleVO;

public interface ScheduleService {
	
	/**
	 * 회원번호를 인수값으로 받아 스케쥴 정보를 받아오는 메서드
	 * @param numMap 회원번호 또는 서비스 번호를 담은 Map
	 * @return 스케쥴 정보가 담긴 ScheduleVO 객체
	 */
	public List<ScheduleVO> selectSchedule(int num);
	
	
	/**
	 * 스케쥴 정보를 입력하는 메서드
	 * @return 성공 : 1 실패 : 0
	 */
	public int insertSchedule(ScheduleVO scvo);

	/**
	 * 스케쥴의 변경 정보를 인수값으로 받아 수정하는 메서드
	 * @param 수정된 스케쥴 정보가 담긴 scheduleVO객체
	 * @return 성공 : 1 실패 : 0
	 */
	public int updateSchedule(ScheduleVO vo);
	
	/**
	 * 스케쥴 번호를 인수값으로 받아 정보를 삭제하는 메서드
	 * @return 성공 : 1 실패 : 0
	 */
	public int deleteSchedule(int schedule_no);
	
	
		
}
