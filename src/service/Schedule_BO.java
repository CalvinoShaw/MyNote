package service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import DAO.ScheduleDAO;
import DTO.ScheduleDTO;

import vo.Schedule_VO;
import vo.Schedule_formVO;
import vo.Schedule_updateVO;

public class Schedule_BO {
	
	/*
	 * 日程管理 - 列表展示 - 置顶
	 */
	public List<Schedule_formVO> schedule_form_top(){
		//新建VO类型的list备用
		List<Schedule_formVO> vo_list_top = new ArrayList<Schedule_formVO>();
		
		//调用DAO，返回DTO类型的list
		ScheduleDAO schedule_dao = new ScheduleDAO();
		List<ScheduleDTO> dto_list = schedule_dao.findAllSchedule_top();
		
		//遍历DTO，将DTO中合适的部分封装进VO
		Iterator<ScheduleDTO> it = dto_list.iterator();
		while(it.hasNext()){
			ScheduleDTO dto = it.next();
			Schedule_formVO vo = new Schedule_formVO();
			vo.setS_id(dto.getS_id());
			vo.setS_name(dto.getS_name());
			vo.setS_type(dto.getS_type());
			vo.setS_status(dto.getS_status());
			vo.setS_deadline(dto.getS_deadline());
			vo.setS_progress(dto.getS_progress());
			vo_list_top.add(vo);
		}
		//返回VO类型的list
		return vo_list_top;
	}
	
	
	/*
	 * 日程管理 - 列表展示 - 非置顶
	 */
	public List<Schedule_formVO> schedule_form_notop(){
		//新建VO类型的list备用
		List<Schedule_formVO> vo_list_notop = new ArrayList<Schedule_formVO>();
		
		//调用DAO，返回DTO类型的list
		ScheduleDAO schedule_dao = new ScheduleDAO();
		List<ScheduleDTO> dto_list = schedule_dao.findAllSchedule_notop();
		
		//遍历DTO，将DTO中合适的部分封装进VO
		Iterator<ScheduleDTO> it = dto_list.iterator();
		while(it.hasNext()){
			ScheduleDTO dto = it.next();
			Schedule_formVO vo = new Schedule_formVO();
			vo.setS_id(dto.getS_id());
			vo.setS_name(dto.getS_name());
			vo.setS_type(dto.getS_type());
			vo.setS_status(dto.getS_status());
			vo.setS_deadline(dto.getS_deadline());
			vo.setS_progress(dto.getS_progress());
			vo_list_notop.add(vo);
		}
		//返回VO类型的list
		return vo_list_notop;
	}
	
	
	
	/*
	 * 新增日程
	 */
	public int newSchedule(Schedule_VO new_schedule){
		//新增成功与否的状态，0表示失败，1表示成功
		int rtn = 0;
		
		//从VO中获取数据
		String s_id = new_schedule.getS_id();
		String s_name = new_schedule.getS_name();
		String s_deadline = new_schedule.getS_deadline();
		String s_content = new_schedule.getS_content();
		int s_type = new_schedule.getS_type();
		int s_status = new_schedule.getS_status();
		int s_progress = new_schedule.getS_progress();
		
		//封装进DTO
		ScheduleDTO schedule_dto_send = new ScheduleDTO();
		schedule_dto_send.setS_id(s_id);
		schedule_dto_send.setS_name(s_name);
		schedule_dto_send.setS_deadline(s_deadline);
		schedule_dto_send.setS_content(s_content);
		schedule_dto_send.setS_progress(s_progress);
		schedule_dto_send.setS_type(s_type);
		schedule_dto_send.setS_status(s_status);
		
		//调用DAO，更改返回值，并进一步返回
		ScheduleDAO schedule_dao = new ScheduleDAO();
		rtn = schedule_dao.createSchedule(schedule_dto_send);
		return rtn;
	}
	
	
	/*
	 * 修改日程 - 根据id展示数据
	 */
	public Schedule_updateVO update_show(String s_id){
		//调用DAO，得到DTO
		ScheduleDAO schedule_dao = new ScheduleDAO();
		ScheduleDTO dto = schedule_dao.findByScheduleID(s_id);
		
		//新建VO，把需要的DTO中的数据封装进VO内
		Schedule_updateVO vo = new Schedule_updateVO();
		vo.setS_name(dto.getS_name());
		vo.setS_deadline(dto.getS_deadline());
		vo.setS_content(dto.getS_content());
		vo.setS_progress(dto.getS_progress());
		vo.setS_status(dto.getS_status());
		vo.setS_type(dto.getS_type());
		
		//返回VO
		return vo;
	}
	
	
	/*
	 * 修改日程 - 根据id修改数据
	 */
	public int update_update(String s_id,Schedule_updateVO updatevo){
		//设置初始的返回状态，0表示失败
		int rtn = 0;
		
		//获取数据
		String s_name = updatevo.getS_name();
		System.out.println(s_name);
		String s_deadline = updatevo.getS_deadline();
		String s_content = updatevo.getS_content();
		int s_type = updatevo.getS_type();
		int s_status = updatevo.getS_status();
		int s_progress = updatevo.getS_progress();
		
		//封装进DTO
		ScheduleDTO schedule_dto_send = new ScheduleDTO();
		schedule_dto_send.setS_id(s_id);
		schedule_dto_send.setS_name(s_name);
		schedule_dto_send.setS_deadline(s_deadline);
		schedule_dto_send.setS_content(s_content);
		schedule_dto_send.setS_progress(s_progress);
		schedule_dto_send.setS_type(s_type);
		schedule_dto_send.setS_status(s_status);
		
		//调用DAO，更新返回值
		ScheduleDAO schedule_dao = new ScheduleDAO();
		rtn = schedule_dao.updateSchedule(schedule_dto_send);
		return rtn;
	}
	
	
	/*
	 * 删除日程 - 根据id删除日程
	 */
	public int delete_schedule(String s_id){
		ScheduleDAO schedule_dao = new ScheduleDAO();
		int rtn = schedule_dao.deleteBySchduleID(s_id);
		return rtn;
	}
}
