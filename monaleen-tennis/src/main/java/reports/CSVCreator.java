package reports;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;

import users.User;

public class CSVCreator implements I_Report {
	
	@Autowired
	private MailSender mailSender;

	private static Logger logger = Logger.getLogger(CSVCreator.class);

	private CSVCreator() {
		
	}

	

	public static boolean createCSVToEmail(List<User> list, File file) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			create(writer, list);
			writer.flush();
			writer.close();
			logger.info("CSVCreator Exiting");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("CSVCreator Exiting");
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



	public void create() {
		// TODO Auto-generated method stub
		
	}

}
