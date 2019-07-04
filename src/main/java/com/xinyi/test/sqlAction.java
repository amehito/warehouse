package com.xinyi.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.shiro.crypto.hash.Md5Hash;

import com.xinyi.bean.XinyiUser;
import com.xinyi.bean.XinyiUserRoleKey;
import com.xinyi.dao.XinyiUserMapper;
import com.xinyi.dao.XinyiUserRoleMapper;
import com.xinyi.utils.MybatisOfSpringUtil;

public class sqlAction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		SqlSession sqlSession = MybatisOfSpringUtil.getSessionFactory().openSession();
//		XinyiUser record = new XinyiUser();
//		Md5Hash md5Hash = new Md5Hash("123456");
//		record.setUserUsername("admin");
//		record.setUserPassword(md5Hash.toString());
//		sqlSession.getMapper(XinyiUserMapper.class).insert(record);
//		sqlSession.commit();
//		sqlSession.close();
		
		SqlSession sqlSession = MybatisOfSpringUtil.getSessionFactory().openSession();
		XinyiUserRoleKey record = new XinyiUserRoleKey();
		record.setRoleId(2);
		record.setUserId(3);
		sqlSession.getMapper(XinyiUserRoleMapper.class).insert(record);
		sqlSession.commit();
		sqlSession.close();
	}

}
