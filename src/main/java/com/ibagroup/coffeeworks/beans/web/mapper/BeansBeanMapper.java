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
public class BeansBeanMapper {

	public BeansBean toBean(Beans entity) {
		
		BeansBean bean = null;
		if (entity != null) {
			bean = new BeansBean();
			bean.setId(entity.getId());
			bean.setName(entity.getName());
		}
		return bean;
		
	}

	public List<BeansBean> toBeanList(List<Beans> beansEntities){
			
			final List<BeansBean> beansBeans = new ArrayList<BeansBean>();
			if(beansEntities != null) {
				for(Beans beansEntity : beansEntities) {
					BeansBean bean = new BeansBean();
					bean.setId(beansEntity.getId());
					bean.setName(beansEntity.getName());
					beansBeans.add(bean);
				}
			}
			return beansBeans;
			
		}
	
}
