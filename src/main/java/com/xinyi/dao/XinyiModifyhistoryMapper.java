package com.xinyi.dao;

import com.xinyi.bean.XinyiModifyhistory;
import com.xinyi.bean.XinyiModifyhistoryExample;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XinyiModifyhistoryMapper {
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xinyi_modifyhistory
	 * @mbg.generated  Sun Jul 14 18:16:22 CST 2019
	 */
	long countByExample(XinyiModifyhistoryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xinyi_modifyhistory
	 * @mbg.generated  Sun Jul 14 18:16:22 CST 2019
	 */
	int deleteByExample(XinyiModifyhistoryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xinyi_modifyhistory
	 * @mbg.generated  Sun Jul 14 18:16:22 CST 2019
	 */
	int deleteByPrimaryKey(Integer modifyid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xinyi_modifyhistory
	 * @mbg.generated  Sun Jul 14 18:16:22 CST 2019
	 */
	int insert(XinyiModifyhistory record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xinyi_modifyhistory
	 * @mbg.generated  Sun Jul 14 18:16:22 CST 2019
	 */
	int insertSelective(XinyiModifyhistory record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xinyi_modifyhistory
	 * @mbg.generated  Sun Jul 14 18:16:22 CST 2019
	 */
	List<XinyiModifyhistory> selectByExample(XinyiModifyhistoryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xinyi_modifyhistory
	 * @mbg.generated  Sun Jul 14 18:16:22 CST 2019
	 */
	XinyiModifyhistory selectByPrimaryKey(Integer modifyid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xinyi_modifyhistory
	 * @mbg.generated  Sun Jul 14 18:16:22 CST 2019
	 */
	int updateByExampleSelective(@Param("record") XinyiModifyhistory record,
			@Param("example") XinyiModifyhistoryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xinyi_modifyhistory
	 * @mbg.generated  Sun Jul 14 18:16:22 CST 2019
	 */
	int updateByExample(@Param("record") XinyiModifyhistory record,
			@Param("example") XinyiModifyhistoryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xinyi_modifyhistory
	 * @mbg.generated  Sun Jul 14 18:16:22 CST 2019
	 */
	int updateByPrimaryKeySelective(XinyiModifyhistory record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xinyi_modifyhistory
	 * @mbg.generated  Sun Jul 14 18:16:22 CST 2019
	 */
	int updateByPrimaryKey(XinyiModifyhistory record);

	ArrayList<XinyiModifyhistory> selectAll();
}