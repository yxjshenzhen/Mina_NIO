package com.you.mina.dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


import com.you.mina.bean.EquipmentBean;

/**
 * @author lucher 
 * 本类为sql工具类，包括sql无参、有参查询，以及sql更新语句方法 
 * 该类一般配合DBUtil使用，只是列举了常用的方法
 */
public class SQLUtil {

	// 无参数的查询语句
	private static final String sql1 = "select * from Equipment";

	// 有参数的查询语句
	private static final String sql2 = "select * from ControlModel";
	
	//返回烟雾最新数据状态记录
	private static final String sql3 = "select top 1 * from YanWuData  order by YanWuDataId  desc";

	// 更新语句
	private static final String sql4 = "update ControlModel set ControlModel=? where ControlModelId=?";

	// 删除语句，同时执行
	private static final String sql5 = "delete from user where id=?";
	private static final String sql6 = "delete from userInfo where id=?";
	
	/**
	 * 添加传感器类方法
	 * @param ChuanGanModel对象
	 * @return 执行添加的结果
	 */
	public boolean addEquipmentBean(EquipmentBean equipmentBean) {
		
		String sql = "insert into equipmentBean (Type,Name,Value) values (?,?,?)";
		int result = 0;
		boolean flag = true;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getSQLSERVERConnection();
//			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
		
			pstmt.setString(1, equipmentBean.getType());
			pstmt.setString(2, equipmentBean.getName());
			pstmt.setString(3, equipmentBean.getValue());

			result = pstmt.executeUpdate();
			
//			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			DBUtil.closeConn(pstmt, conn);
		}

		if (result == 0 ) {
			flag = false;
		}
		return flag;
	}
	
	
	
	/**
	 * 添加传感器类方法
	 * @param ChuanGanModel对象
	 * @return 执行添加的结果
	 */
	public void addEquipmentBeanList(ArrayList<EquipmentBean> arrayList) {
		String sql = "insert into equipmentBean (Type,Name,Value) values ";
		for(int i = 0 ;i < arrayList.size();i++){
			sql = sql + "("+"'"+arrayList.get(i).getType()+"'"+","+"'"+arrayList.get(i).getName()+"'"+","+"'"+arrayList.get(i).getValue()+"'"+"),";
			if(i==arrayList.size()-1){
				sql = sql + "("+"'"+arrayList.get(i).getType()+"'"+","+"'"+arrayList.get(i).getName()+"'"+","+"'"+arrayList.get(i).getValue()+"'"+")";
			}
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getSQLSERVERConnection();
			
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
		
			pstmt.executeUpdate();
            System.out.println("成功入库！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(pstmt, conn);
			arrayList.clear();
		}
	}
	
	
	
	/**
	 * 无参数查询,返回所有控制设备名称
	 * 
	 * @return 所有记录的名字的list
	 */
//	public static List<String> getAllControlModelState() {
//
//		Connection conn = null;
//		Statement ps = null;
//		ResultSet rs = null;
//		List<String> info = new ArrayList<String>();
//
//		try {
//			conn = DBUtil.getSQLSERVERConnection();
//			ps = conn.createStatement();
//			rs = ps.executeQuery(sql2);
//
//			while (rs.next()) {
//				// 根据具体情况定（获取传感数据类型）
//				info.add(rs.getString("ControlModelState")); 
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			DBUtil.closeConn(rs, ps, conn);
//		}
//		return info;
//	}
	


	/**
	 * 有参数查询,返回类型据情况稍加修改
	 * 
	 * @param ModelType
	 * @param ModelWhere
	 * @return 返回指定字段的记录list
	 */
//	public static List<String> getAllByMM(String ModelType, String ModelWhere) {
//
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		List<String> info = new ArrayList<String>();
//
//		try {
//			conn = DBUtil.getSQLSERVERConnection();
//			pstmt = conn.prepareStatement(sql1);
//			pstmt.setString(1, ModelType);
//			pstmt.setString(2, ModelWhere);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				info.add(rs.getString(1));
//				info.add(rs.getString(2));
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			DBUtil.closeConn(rs, pstmt, conn);
//		}
//
//		return info;
//	}

	

//	/**
//	 * 更新类方法,同时执行两条的情况
//	 * @param ModelType
//	 * @return 执行删除的结果
//	 */
//	public static boolean deleteByModelType(String ModelType) {
//		
//		int result1 = 0;
//		int result2 = 0;
//		boolean flag = true;
//
//		Connection conn = null;
//		PreparedStatement pstmt1 = null;
//		PreparedStatement pstmt2 = null;
//
//		try {
//			conn = DBUtil.getSQLSERVERConnection();
//			conn.setAutoCommit(false);
//
//			pstmt1 = conn.prepareStatement(sql4);
//			pstmt1.setString(1, ModelType);
//
//			pstmt2 = conn.prepareStatement(sql5);
//			pstmt2.setString(1, ModelType);
//
//			result1 = pstmt1.executeUpdate();
//			result2 = pstmt2.executeUpdate();
//
//			conn.commit();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		} finally {
//			DBUtil.closeConn(pstmt1, pstmt2, conn);
//		}
//
//		if (result1 == 0 || result2 == 0) {
//			flag = false;
//		}
//		return flag;
//	}
}
