package project.common.sql;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommonDAO {

	@Autowired
	private SqlSession sqlSession;

	public <T> T selectOne(String queryId, Object parameter) {
		System.out.println(queryId + "(selectOne)");
		return sqlSession.selectOne(queryId, parameter);
	}

	public <T> List<T> selectList(String queryId, Object parameter) {
		System.out.println(queryId + "(selectList)");
		return sqlSession.selectList(queryId, parameter);
	}

	public int insert(String queryId, Object parameter) {
		System.out.println(queryId + "(insert)");
		return sqlSession.insert(queryId, parameter);
	}

}
