package com.example.demo.login.controller.domain.model.repository.jdbc;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import com.example.demo.login.controller.domain.model.User;
import org.springframework.stereotype.Repository;
import com.example.demo.login.controller.domain.model.repository.UserDao;

@Repository("UserDaoNamedJdbcImpl")
public class UserDaoNamedJdbcImpl implements UserDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	//Userテーブルの件数を取得
	@Override
	public int count() {
		
		//SQL文
		String sql ="SERECT COUNT(*) FROM m_user";
		
		//パラメーター生成
		SqlParameterSource params = new MapSqlParameterSource();
		
		//全件取得してカウント
		return jdbc.queryForObject(sql, params, Integer.class);
	}
	//Userテーブルにデータを1件　insert
	@Override
	public int insertOne(User user) {
		
		//SQL文にキー名を指定
		//SQL文
		String sql = "INSERT INTO m_user(user_id," 
		+ "password,"
		+ "user_name,"
		+ "birthday,"
		+ "age,"
		+ "marriage,"
		+ "rol)"
		+ "VALUES(userId,"
		+ ":password,"
		+ ":userName,"
		+ ":birthday,"
		+ ":age,"
		+ ":marriage,"
		+ ":rol)";
		
		//パラメーターも設定
		SqlParameterSource params = new MapSqlParameterSource()
		.addValue("userId",user.getUserId())
		.addValue("password",user.getPassword())
		.addValue("birthday",user.getBirthday())
		.addValue("age",user.getAge())
		.addValue("marriage", user.isMarriage())
		.addValue("role",user.getRole());
		
		//SQL実行
		return jdbc.update(sql, params);
	}
	
	//Userテーブルのデータを1件取得
	@Override
	public User selectOne(String userId) {
		
		//SQL文
		String sql = "SELECT * FROM m_user WHERE user_id = :userId";
		
		//パラメーター
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("userId", userId) ;
		//SQL実行
		Map<String, Object>map = jdbc.queryForMap(sql, params);
		
		//結果返却用のインスタンスを生成
		User user =new User();
		
		//取得用のデータをインスタンスにセットしていく
		user.setUserId((String)map.get("user_id"));
		user.setPassword((String)map.get("password"));
		user.setUserName((String)map.get("user_name"));
		user.setBirthday((Date)map.get("birthday"));
		user.setAge((Integer)map.get("age"));
		user.setMarriage((Boolean)map.get("marriage"));
		user.setRole((String)map.get("role"));
		
		return user;
	}
	//Userテーブルの全データに取得
	@Override
	public List<User>selectMany(){
		//SQL文
		String sql ="SELECT * FROM m_user";
		
		//パラメーター
		SqlParameterSource params =new MapSqlParameterSource();
		
		//SQL実行
		List<Map<String, Object>> getList = jdbc.queryForList(sql, params);
		
		//結果返却用のList
		List<User> userList = new ArrayList<>();
		
		//取得データ文loop
		for(Map<String, Object> map: getList) {
			
			//Userインスタンスの生成
			User user = new User();
			
			//Userインスタンスに取得したデータをセットする
			user.setUserId((String)map.get("user_id"));
			user.setPassword((String)map.get("password"));
			user.setUserName((String)map.get("username"));
			user.setBirthday((Date)map.get("birthday"));
			user.setAge((Integer)map.get("age"));
			user.setMarriage((Boolean)map.get("marriage"));
			user.setRole((String)map.get("role"));
			
			//Listに追加
			userList.add(user);
		}
		
		return	userList;
	}
	
	//Userテーブルを1件更新
	@Override
	public int updateOne(User user) {
		
		//SQL文
		String sql ="UPDATE M_USER"
				+ "SET"
				+ "password = :password,"
				+ "user_name = :userName,"
				+ "birthday = :birthday,"
				+ "age = :age,"
				+ "marriage = :marriage,"
				+ "WHERE user_id = :userId";
		
		//パラメーター
		SqlParameterSource params =new MapSqlParameterSource()
				.addValue("userId", user.getUserId())
				.addValue("password", user.getPassword())
				.addValue("userName", user.getUserName())
				.addValue("birthday", user.getBirthday())
				.addValue("age", user.getAge())
				.addValue("marriage", user.isMarriage());
		
		//SQL実行
		return jdbc.update(sql, params);
	}
	
	//Userテーブルを1件削除
	@Override
	public int deleteOne(String userId) {	
		
		
		//SQL文
		String sql ="DELETE FROM m_user WHERE user_id = :userId";
		
		//パラメーター
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("userId", userId);
		
		//SQL実行
		int rowNumder = jdbc.update(sql,params);
		
		return rowNumder;
	}
	
	//SQL取得結果をサーバーにCSVで保存する
	@Override
	public void userCsvOut() {
		//M_USERテーブルのデータを全件取得するSQL
		String sql = "SELECT * FROM m_user";
		
		//ResultSetExtractorの生成
		UserRowCallbackHandler handler = new UserRowCallbackHandler();
		
		//クエリー実行＆CSV出力
		jdbc.query(sql, handler);
	}
}
	

