package com.xinyi.controller;

import java.io.IOException;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;
import org.apache.ibatis.session.SqlSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.cj.Session;
import com.xinyi.bean.XinyiUser;
import com.xinyi.bean.XinyiUserExample;
import com.xinyi.bean.XinyiUserExample.Criteria;
import com.xinyi.dao.XinyiUserMapper;
import com.xinyi.test.User;
import com.xinyi.utils.MybatisOfSpringUtil;


@Controller
public class myController {
	
	
	@RequestMapping(value="/subLogin",method=RequestMethod.POST
			,produces="application/json;charset=utf-8")
	
	public String subLogin(User user,HttpSession httpSession) {
		Md5Hash md5Hash = new Md5Hash(user.getPassword());
		org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),md5Hash.toString());
		
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		httpSession.setAttribute("UserName", user.getUsername());
		SqlSession sqlSession = MybatisOfSpringUtil.getSessionFactory().openSession();
		sqlSession = MybatisOfSpringUtil.getSessionFactory().openSession();
		XinyiUserMapper xinyiUserMapper = sqlSession.getMapper(XinyiUserMapper.class);
		XinyiUserExample example = new XinyiUserExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andUserUsernameEqualTo(user.getUsername());
		XinyiUser xinyiUser = xinyiUserMapper.selectByExample(example).get(0);
		Integer UserId = xinyiUser.getUserId();
		httpSession.setAttribute("UserId", UserId);
		
		sqlSession.close();
		
		
		return "mainPage";
		
	}
	@RequestMapping("/test")
	public void test(HttpServletResponse response) throws IOException {
		response.sendRedirect("login-page.html");
	}
	@RequestMapping("/mainPage")
	public String home() {
		org.apache.shiro.subject.Subject currentSubject = SecurityUtils.getSubject();
        if (!currentSubject.isAuthenticated())
            return "login";
        else {
        	System.out.println("重定向了");
            return "mainPage";
            
        }
	}
}
