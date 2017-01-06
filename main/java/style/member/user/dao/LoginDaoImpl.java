package style.member.user.dao;

import org.mybatis.spring.SqlSessionTemplate;

import style.member.user.model.MemberVO;

public class LoginDaoImpl implements LoginDao {
	//sqlsessiontemplate ����
	private SqlSessionTemplate sqlSession;
	//sqlsessiontemplate ����
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	String namespace = "style.member.user.dao.userMember.";
	
	@Override
	public MemberVO getUserInfo(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+"getMemberInfo", id);
	}


	

}
