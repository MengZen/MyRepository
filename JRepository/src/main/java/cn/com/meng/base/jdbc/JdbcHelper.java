package cn.com.meng.base.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.meng.base.common.Constants;

/**
 * JDBC工具类，建立数据库连接
 * @author meng
 *
 */
public class JdbcHelper {
	
	//数据库连接对象
	private Connection conn = null;
	
	public JdbcHelper() {
		try {
			//加载jdbc驱动类
			Class.forName(Constants.jdbcDriver);
			//建立连接
			conn = DriverManager.getConnection(Constants.jdbcUrl, Constants.username,Constants.password);
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
	
	/**
	 * 查询并将结果存入list<Map>
	 * @param sql 
	 * @param params 参数数组
	 * @return
	 */
	public List<Map<String,Object>> query(String sql,Object... params){
		PreparedStatement statement = null;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			statement = conn.prepareStatement(sql);
			//设置参数值
			for(int i = 0;i < params.length ;i++){
				statement.setObject(i+1,params[i]);
			}
			
			ResultSet rs = statement.executeQuery();
			ResultSetMetaData metas = rs.getMetaData();
			while(rs.next()){
				Map<String,Object> map = new HashMap<String,Object>();
				for(int i = 0; i < metas.getColumnCount(); i++){
					String colName = metas.getColumnName(i+1);
					map.put(colName, rs.getObject(colName));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(null != statement){
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	/**
	 * 执行更新、插入或删除sql
	 * @param sql 
	 * @param params 参数数组
	 * @return
	 */
	public int execute(String sql,Object... params){
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement(sql);
			for(int i = 0;i < params.length ;i++){
				if(params[i] instanceof Integer){
					statement.setInt(i+1,Integer.parseInt(params[i].toString()));
				}else if(params[i] instanceof String){
					statement.setString(i+1,params[i].toString());
				}else if(params[i] instanceof Date){
					statement.setDate(i+1,new java.sql.Date(((Date)params[i]).getTime()));
				}else{
					statement.setObject(i+1,params[i]);
				}
			}
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally{
			if(null != statement){
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void close(){
		if(null != conn){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		JdbcHelper helper = new JdbcHelper();
		List<Map<String,Object>> list = helper.query("select * from tn_annual_record where year ＝ ?","2015");
		for(Map<String,Object> map : list){
			System.out.println(map);
		}
		helper.close();
		
	}
	
}
