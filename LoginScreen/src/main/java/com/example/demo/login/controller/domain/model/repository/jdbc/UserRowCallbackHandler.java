package com.example.demo.login.controller.domain.model.repository.jdbc;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowCallbackHandler;

//ポイント：RoCallbackHandler
public class UserRowCallbackHandler implements RowCallbackHandler{
	
	@Override
	public void processRow(ResultSet rs) throws SQLException{
		
		try {
			
			//ファイル書き込みの準備
			File file = new File("sample.csv");
			FileWriter fw= new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
		//取得件数分 loop
			do {
				//ResultSetから値を取得してStringにセット
				String str = rs.getString("user_id") + ","
						+ rs.getString("password") + ","
						+ rs.getDate("birthday") + ","
						+ rs.getInt("age") + ","
						+ rs.getBoolean("marriage") + ","
						+ rs.getString("role");
				//ファイルに書き込み&改行
				bw.write(str);
				bw.newLine();		
			} while(rs.next());
			
			//強制的に書き込み&ファイルクローズ
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new SQLException(e);
		}
	}

}
