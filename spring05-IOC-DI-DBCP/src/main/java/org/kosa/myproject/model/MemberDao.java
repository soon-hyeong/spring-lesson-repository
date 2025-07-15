package org.kosa.myproject.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

/**
 * final field로 DataSource를 선언
 * Constructor Injection
 * 실제 DataSource를 이용해 spring_member table의 회원정보를 조회하여 회원 객체를 리턴한다
 * 
 */
@Repository
public class MemberDao {

    private final DataSource dataSource;
		
	private MemberDao(DataSource datasource, DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public MemberVo findMemberById(String id) throws SQLException {
		MemberVo memberVo = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection(); //DataSource 타입의 DBCP 구현체로부터 커넥션을 빌려온다
			String sql = "SELECT * FROM spring_member WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberVo = new MemberVo(rs.getString("id"), rs.getString("password"), rs.getString("name"), rs.getString("address"));
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
		return memberVo;
	}
	
	private void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException {
		if(rs != null)
			rs.close();
		if(pstmt != null)
			pstmt.close();
		if(con != null)
			con.close();
	}
}
