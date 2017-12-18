package org.danielli.xultimate.transaction.biz.impl;

import javax.annotation.Resource;

import org.danielli.xultimate.jdbc.datasource.lookup.DataSourceContext;
import org.danielli.xultimate.transaction.biz.OtherBiz;
import org.danielli.xultimate.transaction.dao.OtherDAO;
import org.springframework.stereotype.Service;

@Service("otherBizImpl")
public class OtherBizImpl implements OtherBiz {

	@Resource(name = "otherDAOImpl")
	private OtherDAO otherDAO;
	
	@Override
	public void saveOther() {
		System.out.println("otherDAO.saveOther(); Before");
		DataSourceContext.setCurrentLookupKey("other");
		otherDAO.saveOther();
		System.out.println("otherDAO.saveOther(); After");
	}
	
	@Override
	public void saveErrorOther() {
		System.out.println("otherDAO.saveErrorOther(); Before");
		DataSourceContext.setCurrentLookupKey("other");
		otherDAO.saveErrorOther();
		System.out.println("otherDAO.saveErrorOther(); After");
	}

}
