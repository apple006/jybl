/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.weichai.modules.sys.service;

import java.util.List;

import com.weichai.common.persistence.Page;
import com.weichai.modules.sys.dao.BookDao;
import com.weichai.modules.sys.entity.Book;
import com.weichai.modules.sys.utils.UserUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weichai.common.service.TreeService;
import com.weichai.common.utils.StringUtils;
import com.weichai.modules.sys.entity.Category;
import com.weichai.modules.sys.dao.CategoryDao;

/**
 * 分类管理Service
 * @author 徐韵轩
 * @version 2017-05-05
 */
@Service
@Transactional(readOnly = true)
public class CategoryService extends TreeService<CategoryDao, Category> {

	@Autowired
	private BookDao bookDao;

	public Category get(String id) {
		return super.get(id);
	}
	
	public List<Category> findList(Category category) {
		if (StringUtils.isNotBlank(category.getParentIds())){
			category.setParentIds(","+category.getParentIds()+",");
		}
		return super.findList(category);
	}
	
	@Transactional(readOnly = false)
	public void save(Category category) {
		super.save(category);
	}
	
	@Transactional(readOnly = false)
	public void delete(Category category) {
		super.delete(category);
		UserUtils.removeCache(UserUtils.CACHE_CATAGORY_LIST);
	}

	public List<Book> findBookByCategoryId(String categoryId) {
		return bookDao.selectBookByCategoryId(categoryId);
	}


}