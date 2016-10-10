package service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import DAO.FinancialDAO;
import DTO.FinancialDTO;
import vo.Financial_formVO;
import vo.Financial_VO;
import vo.Financial_statics_VO;
import vo.Financial_updateVO;

public class Financial_BO {
	
	/*
	 * 财务管理列表展示
	 */
	public List<Financial_formVO> financial_form(){
		//新建VO类型的list备用
		List<Financial_formVO> vo_list = new ArrayList<Financial_formVO>();
		
		//调用DAO，返回DTO类型的list
		FinancialDAO financial_dao = new FinancialDAO();
		List<FinancialDTO> dto_list = financial_dao.findAllFinancial();
		
		//遍历DTO，将DTO中合适的部分封装进VO
		Iterator<FinancialDTO> it = dto_list.iterator();
		while(it.hasNext()){
			FinancialDTO dto = it.next();
			Financial_formVO vo = new Financial_formVO();
			vo.setF_id(dto.getF_id());
			vo.setF_type(dto.getF_type());
			vo.setF_amount(dto.getF_amount());
			vo.setF_time(dto.getF_time());
			vo_list.add(vo);
		}
		//返回VO类型的list
		return vo_list;
	}
	
	
	
	/*
	 * 新增财务项
	 */
	public int newFinancial(Financial_VO new_financial){
		//新增成功与否的状态，0表示失败，1表示成功
		int rtn = 0;
		
		//从VO中获取数据
		String f_id = new_financial.getF_id();
		int f_type = new_financial.getF_type();
		String f_amount = new_financial.getF_amount();
		String f_time = new_financial.getF_time();
		String f_tip = new_financial.getF_tip();
		
		//封装进DTO
		FinancialDTO financial_dto_send = new FinancialDTO();
		financial_dto_send.setF_id(f_id);
		financial_dto_send.setF_type(f_type);
		financial_dto_send.setF_amount(f_amount);
		financial_dto_send.setF_time(f_time);
		financial_dto_send.setF_tip(f_tip);
		
		//调用DAO，更改返回值，并进一步返回
		FinancialDAO financial_dao = new FinancialDAO();
		rtn = financial_dao.createFinancial(financial_dto_send);
		return rtn;

	}
	
	/*
	 * 修改财务项 - 根据id展示旧数据
	 */
	public Financial_updateVO update_show(String f_id){
		//调用DAO，得到DTO
		FinancialDAO financial_dao = new FinancialDAO();
		FinancialDTO dto = financial_dao.findByFinancialID(f_id);
		
		//新建VO，把需要的DTO中的数据封装进VO内
		Financial_updateVO vo = new Financial_updateVO();
		vo.setF_type(dto.getF_type());
		vo.setF_amount(dto.getF_amount());
		vo.setF_time(dto.getF_time());
		vo.setF_tip(dto.getF_tip());
		
		//返回VO
		return vo;
	}
	
	/*
	 * 修改 - 根据id修改数据
	 */
	public int update_update(String f_id,Financial_updateVO updatevo){
		//设置初始的返回状态，0表示失败
		int rtn = 0;
		
		//获取数据
		int f_type = updatevo.getF_type();
		String f_amount = updatevo.getF_amount();
		String f_time = updatevo.getF_time();
		String f_tip = updatevo.getF_tip();
		
		//封装进DTO
		FinancialDTO financial_dto_send = new FinancialDTO();
		financial_dto_send.setF_id(f_id);
		financial_dto_send.setF_type(f_type);
		financial_dto_send.setF_amount(f_amount);
		financial_dto_send.setF_time(f_time);
		financial_dto_send.setF_tip(f_tip);
		
		//调用DAO，更新返回值
		FinancialDAO financial_dao = new FinancialDAO();
		rtn = financial_dao.updateFinancial(financial_dto_send);
		return rtn;
	}
	
	
	/*
	 * 删除 - 根据id删除财务项
	 */
	public int delete_financial(String f_id){
		FinancialDAO financial_dao = new FinancialDAO();
		int rtn = financial_dao.deleteByFinancialID(f_id);
		return rtn;
	}
	

	/*
	 * 财务统计 - 总数 - 返回sum们的封装类
	 */
	public Financial_statics_VO financial_statics(int ask){
		int f_type = 0;
		long totalin = 0;
		long totalout = 0;
		long profit = 0;
		//如果请求ask为1，代表查收入总数
		if(ask == 1){
			f_type = 1;
			FinancialDAO financial_dao = new FinancialDAO();
			totalin = financial_dao.findSumByFinancialType(f_type);
		}
		//如果请求ask为2，代表查支出
		else if(ask ==2){
			f_type = 2;
			FinancialDAO financial_dao = new FinancialDAO();
			totalout = financial_dao.findSumByFinancialType(f_type);
		}
		//如果请求ask为3，代表查利润
		else if(ask == 3){
			FinancialDAO financial_dao = new FinancialDAO();
			totalin = financial_dao.findSumByFinancialType(1);
			totalout = financial_dao.findSumByFinancialType(2);
			profit = totalin - totalout;
		}
		
		//封装，没有或不需要的数据会被封装为0
		Financial_statics_VO financial_statics = new Financial_statics_VO();
		financial_statics.setTotalin(totalin);
		financial_statics.setTotalout(totalout);
		financial_statics.setProfit(profit);
		
		//返回
		return financial_statics;
	}
	
	
	
	
	/*
	 * 财务统计 - 列表 - 返回列表
	 */
	public List<Financial_formVO> financial_statics_form(int ask){
		
		//默认设置type为0，不对应任何数据
		int f_type = 0;
		//初始化VO类型的list，供返回
		List<Financial_formVO> vo_list = new ArrayList<Financial_formVO>();
		//初始化DTO类型的list，供接收
		List<FinancialDTO> dto_list = null;
		
		//如果请求ask为1，代表查收入
		if(ask == 1){
			//设置f_type也为1
			f_type = 1;
			
			//调用DAO，获取list
			FinancialDAO financial_dao = new FinancialDAO();
			dto_list = financial_dao.findByFinancialType(f_type);
			
			//遍历list，把每一个DTO的数据封装进封装入VO
			Iterator<FinancialDTO> it = dto_list.iterator();
			while(it.hasNext()){
				FinancialDTO dto = it.next();
				Financial_formVO vo = new Financial_formVO();
				vo.setF_id(dto.getF_id());
				vo.setF_type(dto.getF_type());
				vo.setF_amount(dto.getF_amount());
				vo.setF_time(dto.getF_time());
				vo_list.add(vo);
			}
			//返回VO类型的list
			return vo_list;
		}
		//如果请求ask为2，代表查支出
		else if(ask ==2){
			//设置f_type也为2
			f_type = 2;
			
			//调用DAO，获取list
			FinancialDAO financial_dao = new FinancialDAO();
			dto_list = financial_dao.findByFinancialType(f_type);
			
			//遍历list，把每一个DTO的数据封装进封装入VO
			Iterator<FinancialDTO> it = dto_list.iterator();
			while(it.hasNext()){
				FinancialDTO dto = it.next();
				Financial_formVO vo = new Financial_formVO();
				vo.setF_id(dto.getF_id());
				vo.setF_type(dto.getF_type());
				vo.setF_amount(dto.getF_amount());
				vo.setF_time(dto.getF_time());
				vo_list.add(vo);
			}
			//返回VO类型的list
			return vo_list;
		}
		return vo_list;
		

	}

}
