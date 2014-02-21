package reports;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import logs.EmailLog;
import logs.Log;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;

import service.LogService;
import users.User;

public class CSVCreator {

	private LogService logService;
	
	@Autowired
	private MailSender mailSender;

	private static Logger logger = Logger.getLogger(CSVCreator.class);

	@Autowired
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public CSVCreator(List<User> list, String name) {
		createCSVToEmail(list, name);
	}

	public boolean createCSVToEmail(List<User> list, String name) {
		try {
		
			DateFormat df = new SimpleDateFormat("ddmmyyyyHHmmss");
			Date today = Calendar.getInstance().getTime();
			String date = df.format(today);
			date.replace("\\", "");
			date.replace(" ", "");
			File file = new File(name.replace("@","") + date + ".csv");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			create(writer, list);
			writer.flush();
			writer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

	public static void create(BufferedWriter writer, List<User> list)
			throws IOException {
		writer.append("ID");
		writer.append(',');
		writer.append("Email");
		writer.append(',');
		writer.append("Password");
		writer.append(',');
		writer.append("Name");
		writer.append(',');
		writer.append("Gender");
		writer.append(',');
		writer.append("Member Type");
		writer.append(',');
		writer.append("Address Line 1");
		writer.append(',');
		writer.append("Address Line 2");
		writer.append(',');
		writer.append("Address Town");
		writer.append(',');
		writer.append("Address County");
		writer.append(',');
		writer.append("Grade");
		writer.append(',');
		writer.append("Contact Number");
		writer.append(',');
		writer.append("Emergency Contact Number");
		writer.append(',');
		writer.append("Emergency Contact Name");
		writer.append(',');
		writer.append("Enabled");
		writer.append(',');
		writer.append("Authority");
		writer.append('\n');

		for (int i = 0; i < list.size(); i++) {
			writer.append(String.valueOf(list.get(i).getId()));
			writer.append(',');
			writer.append(String.valueOf(list.get(i).getUsername()));
			writer.append(',');
			writer.append(String.valueOf(list.get(i).getPassword()));
			writer.append(',');
			writer.append(String.valueOf(list.get(i).getName()));
			writer.append(',');
			writer.append(String.valueOf(list.get(i).getGender()));
			writer.append(',');
			writer.append(String.valueOf(list.get(i).getMember_type()));
			writer.append(',');
			writer.append(String.valueOf(list.get(i).getAd_line1()));
			writer.append(',');
			writer.append(String.valueOf(list.get(i).getAd_line2()));
			writer.append(',');
			writer.append(String.valueOf(list.get(i).getAd_city()));
			writer.append(',');
			writer.append(String.valueOf(list.get(i).getAd_county()));
			writer.append(',');
			writer.append(String.valueOf(list.get(i).getGrade()));
			writer.append(',');
			writer.append(String.valueOf(list.get(i).getContact_num()));
			writer.append(',');
			writer.append(String.valueOf(list.get(i).getEm_con_name()));
			writer.append(',');
			writer.append(String.valueOf(list.get(i).getEm_con_num()));
			writer.append(',');
			writer.append(String.valueOf(list.get(i).isEnabled()));
			writer.append(',');
			writer.append(String.valueOf(list.get(i).getAuthority()));
			writer.append('\n');
		}
	}

}
