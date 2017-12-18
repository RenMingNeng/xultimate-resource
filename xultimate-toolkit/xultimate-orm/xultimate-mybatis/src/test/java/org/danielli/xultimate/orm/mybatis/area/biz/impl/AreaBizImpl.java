package org.danielli.xultimate.orm.mybatis.area.biz.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.danielli.xultimate.orm.mybatis.area.biz.AreaBiz;
import org.danielli.xultimate.orm.mybatis.area.dao.AreaDAO;
import org.danielli.xultimate.orm.mybatis.ds.Item;
import org.danielli.xultimate.orm.mybatis.po.Area;
import org.springframework.stereotype.Service;

@Service("areaBizImpl")
public class AreaBizImpl implements AreaBiz {

	@Resource(name = "areaDAO")
	private AreaDAO areaDAO;
	
	@Override
	public void save(Area entity) {
		if (entity.getCreateTime() == null) {
			entity.setCreateTimeToCurrent();
		}
		if (entity.getUpdateTime() == null) {
			entity.setUpdateTime(entity.getCreateTime());
		}
		if (entity.getPoVersion() == null) {
			entity.clearPoVersion();
		}
		if (entity.getBoost() == null) {
			entity.clearBoost();
		}
		areaDAO.save(entity);
	}

	@Override
	public void update(Area entity) {
		entity.setUpdateTimeToCurrent();
		areaDAO.update(entity);
	}

	@Override
	public void delete(Area entity) {
		areaDAO.delete(entity);
	}

	@Override
	public Long findIdByName(String name) {
		return areaDAO.findIdByName(name);
	}

	@Override
	public Map<Long, Area> findIdAndNameMap() {
		return areaDAO.findIdAndNameMap();
	}

	@Override
	public Map<String, Object> findOneMap(Long id) {
		return areaDAO.findOneMap(id);
	}

	@Override
	public Area findOne(Long id) {
		return areaDAO.findOne(id);
	}
	
	@Override
	public List<Area> findAll() {
		return areaDAO.findAll();
	}

	@Override
	public Long count() {
		return areaDAO.count();
	}

	@Override
	public List<Area> findByItems(List<Item<? extends Object>> items) {
		return areaDAO.findByItems(items);
	}

}
