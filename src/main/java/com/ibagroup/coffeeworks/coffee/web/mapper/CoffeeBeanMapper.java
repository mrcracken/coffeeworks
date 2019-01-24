package com.ibagroup.coffeeworks.coffee.web.mapper;

import java.util.ArrayList;
import java.util.List;

import com.ibagroup.coffeeworks.beans.web.mapper.BeansBeanMapper;
import com.ibagroup.coffeeworks.coffee.database.dto.Coffee;
import com.ibagroup.coffeeworks.coffee.web.bean.CoffeeBean;

/**
 * 
 * @author IBA Group
 * @since 2019
 *
 */
public class CoffeeBeanMapper {

	private BeansBeanMapper beansMapper = new BeansBeanMapper();
	
	public CoffeeBean toBean(Coffee entity) {
			CoffeeBean bean = null;
			if (entity != null) {
				bean = new CoffeeBean();
				bean.setId(entity.getId());
				bean.setName(entity.getName());
				bean.setDescription(entity.getDescription());
				bean.setLocation(entity.getLocation());
				bean.setStars(entity.getStars());
				bean.setBeans(beansMapper.toBean(entity.getBeans()));
			}
			return bean;
			
		}
	
	public List<CoffeeBean> toBeanList(List<Coffee> coffeeEntities){
			final List<CoffeeBean> coffeeBeans = new ArrayList<CoffeeBean>();
			if(coffeeEntities != null) {
				for(Coffee coffeeEntity : coffeeEntities) {
					CoffeeBean bean = new CoffeeBean();
					bean.setId(coffeeEntity.getId());
					bean.setName(coffeeEntity.getName());
					bean.setDescription(coffeeEntity.getDescription());
					bean.setLocation(coffeeEntity.getLocation());
					bean.setBeans(beansMapper.toBean(coffeeEntity.getBeans()));
					coffeeBeans.add(bean);
				}
			}
			return coffeeBeans;
			
		}
	
}
