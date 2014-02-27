package service;

import java.util.List;
import java.util.Map;

import logs.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.LogDAO;

@Service("logService")
public class LogService {
	
	private LogDAO logDAO;

	@Autowired
	public void setLogDAO(LogDAO logDAO) {
		this.logDAO = logDAO;
	}
	
	public void createLog(Log log){
		logDAO.createLogEntry(log);
	}
	
	public List<Log> getLogs(){
		return logDAO.getLogs();
	}
	
	public List<Log> getNoShowLogStats(){
		return logDAO.getNoShowLogStats();
	}
}
