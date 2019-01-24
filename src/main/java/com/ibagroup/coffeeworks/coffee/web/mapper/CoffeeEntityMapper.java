package com.ibagroup.coffeeworks.coffee.web.mapper;

import java.util.ArrayList;
import java.util.List;

import com.ibagroup.coffeeworks.beans.web.mapper.BeansEntityMapper;
import com.ibagroup.coffeeworks.coffee.database.dto.Coffee;
import com.ibagroup.coffeeworks.coffee.web.bean.CoffeeBean;

/**
 * 
 * @author IBA Group
 * @since 2019
 *
 */
public class CoffeeEntityMapper {

	private BeansEntityMapper beansMapper = new BeansEntityMapper();
	
	public Coffee toEntity(CoffeeBean bean) {
		Coffee entity = null;
		if (bean != null) {
			entity = new Coffee();
			entity.setName(bean.getName());
			entity.setDescription(bean.getDescription());
			entity.setLocation(bean.getLocation());
			entity.setStars(bean.getStars());
			entity.setBeans(beansMapper.toEntity(bean.getBeans()));
		}
		return entity;
		
	}
	
	public List<Coffee> toEntityList(List<CoffeeBean> coffeeBeans){
		
		final List<Coffee> coffees = new ArrayList<Coffee>();
		if(coffeeBeans != null) {
			for(CoffeeBean coffeeBean : coffeeBeans) {
				Coffee entity = new Coffee();
				entity.setId(coffeeBean.getId());
				entity.setName(coffeeBean.getName());
				entity.setDescription(coffeeBean.getDescription());
				entity.setLocation(coffeeBean.getLocation());
				entity.setBeans(beansMapper.toEntity(coffeeBean.getBeans()));
				coffees.add(entity);
			}
		}
		return coffees;
		
	}
	
}
