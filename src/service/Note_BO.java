package service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import DAO.NoteDAO;
import DTO.NoteDTO;
import vo.Note_VO;
import vo.Note_formVO;
import vo.Note_updateVO;

public class Note_BO {
	
	/*
	 * 笔记管理 - 列表展示
	 */
	public List<Note_formVO> note_form(){
		//新建VO类型的list备用
		List<Note_formVO> vo_list = new ArrayList<Note_formVO>();
		
		//调用DAO，返回DTO类型的list
		NoteDAO note_dao = new NoteDAO();
		List<NoteDTO> dto_list = note_dao.findAllNote();
		
		//遍历DTO，将DTO中合适的部分封装进VO
		Iterator<NoteDTO> it = dto_list.iterator();
		while(it.hasNext()){
			NoteDTO dto = it.next();
			Note_formVO vo = new Note_formVO();
			vo.setN_id(dto.getN_id());
			vo.setN_type(dto.getN_type());
			vo.setN_name(dto.getN_name());
			vo.setN_uptime(dto.getN_uptime());
			vo.setN_content(dto.getN_content());
			vo_list.add(vo);
		}
		//返回VO类型的list
		return vo_list;
	}
	
	
	/*
	 * 笔记分类 - 列表展示
	 */
	public List<Note_formVO> note_type(int n_type){
		//新建VO类型的list备用
		List<Note_formVO> vo_list = new ArrayList<Note_formVO>();
		
		//调用DAO，返回DTO类型的list
		NoteDAO note_dao = new NoteDAO();
		List<NoteDTO> dto_list = note_dao.findByNoteType(n_type);
		
		//遍历DTO，将DTO中合适的部分封装进VO
		Iterator<NoteDTO> it = dto_list.iterator();
		while(it.hasNext()){
			NoteDTO dto = it.next();
			Note_formVO vo = new Note_formVO();
			vo.setN_id(dto.getN_id());
			vo.setN_type(dto.getN_type());
			vo.setN_name(dto.getN_name());
			vo.setN_uptime(dto.getN_uptime());
			vo.setN_content(dto.getN_content());
			vo_list.add(vo);
		}
		//返回VO类型的list
		return vo_list;
	}
	
	
	/*
	 * 新增笔记
	 */
	public int newNote(Note_VO new_note){
		//新增成功与否的状态，0表示失败，1表示成功
		int rtn = 0;
		
		//从VO中获取数据
		String n_id = new_note.getN_id();
		String n_name = new_note.getN_name();
		int n_type = new_note.getN_type();
		String n_uptime = new_note.getN_uptime();
		String n_content = new_note.getN_content();
		
		//封装进DTO
		NoteDTO note_dto_send = new NoteDTO();
		note_dto_send.setN_id(n_id);
		note_dto_send.setN_name(n_name);
		note_dto_send.setN_type(n_type);
		note_dto_send.setN_uptime(n_uptime);
		note_dto_send.setN_content(n_content);
		
		//调用DAO，更改返回值，并进一步返回
		NoteDAO note_dao = new NoteDAO();
		rtn = note_dao.createNote(note_dto_send);
		return rtn;
	}
	
	
	/*
	 * 修改笔记 - 根据id展示数据
	 */
	public Note_updateVO update_show(String n_id){
		//调用DAO，得到DTO
		NoteDAO schedule_dao = new NoteDAO();
		NoteDTO dto = schedule_dao.findByNoteID(n_id);
		
		//新建VO，把需要的DTO中的数据封装进VO内
		Note_updateVO vo = new Note_updateVO();
		vo.setN_name(dto.getN_name());
		vo.setN_type(dto.getN_type());
		vo.setN_content(dto.getN_content());
		
		//返回VO
		return vo;
	}
	
	
	/*
	 * 修改笔记 - 根据id修改数据
	 */
	public int update_update(String n_id,Note_updateVO updatevo){
		//设置初始的返回状态，0表示失败
		int rtn = 0;
		
		//获取数据
		String n_name = updatevo.getN_name();
		int n_type = updatevo.getN_type();
		String n_content = updatevo.getN_content();
		
		//封装进DTO
		NoteDTO note_dto_send = new NoteDTO();
		note_dto_send.setN_id(n_id);
		note_dto_send.setN_name(n_name);
		note_dto_send.setN_type(n_type);
		note_dto_send.setN_content(n_content);
		
		//调用DAO，更新返回值
		NoteDAO note_dao = new NoteDAO();
		rtn = note_dao.updateNote(note_dto_send);
		return rtn;
	}
	
	
	/*
	 * 删除笔记 - 根据id删除笔记
	 */
	public int delete_note(String n_id){
		NoteDAO note_dao = new NoteDAO();
		int rtn = note_dao.deleteByNoteID(n_id);
		return rtn;
	}

}
