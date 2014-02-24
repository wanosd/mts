package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import analytics.MTCAnalytics;
import dao.AnalyticsDAO;

@Service("analyticsService")
public class AnalyticsService {

	private AnalyticsDAO analyticsDAO;

	@Autowired
	public void setAnalyticsDAO(AnalyticsDAO analyticsDAO) {
		this.analyticsDAO = analyticsDAO;
	}
	
	public MTCAnalytics get(){
		return analyticsDAO.get();
	}
	
	public void save(MTCAnalytics a){
		analyticsDAO.save(a);
	}
	
	
}
