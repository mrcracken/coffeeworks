package com.ibagroup.coffeeworks.beans.web.mapper;

import java.util.ArrayList;
import java.util.List;

import com.ibagroup.coffeeworks.beans.database.dto.Beans;
import com.ibagroup.coffeeworks.beans.web.bean.BeansBean;

/**
 * 
 * @author IBA Group
 * @since 2019
 *
 */
public class BeansEntityMapper {

public Beans toEntity(BeansBean bean) {
		
		Beans entity = null;
		if (bean != null) {
			entity = new Beans();
			entity.setId(bean.getId());
			entity.setName(bean.getName());
		}
		return entity;
		
	}
	
	public List<Beans> toEntityList(List<BeansBean> beansBeans){
		
		final List<Beans> beans = new ArrayList<Beans>();
		if(beansBeans != null) {
			for(BeansBean beansBean : beansBeans) {
				Beans entity = new Beans();
				entity.setId(beansBean.getId());
				entity.setName(beansBean.getName());
				beans.add(entity);
			}
		}
		return beans;
		
	}
	
}
