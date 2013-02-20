package com.lunalvo.CorporatePhonebookServer;

public class Contact {

	public String team,name,companyEmail,personalEmail,workphone,cellphone,msn,skype;
	
	public Contact(String team,String name,String companyEmail,String personalEmail,String workphone,String cellphone,String msn,String skype) {
		this.team = team;
		this.name = name;
		this.companyEmail = companyEmail;
		this.personalEmail = personalEmail;
		this.workphone = workphone;
		this.cellphone = cellphone;
		this.msn = msn;
		this.skype = skype;
	}

	public Contact(String line) {
		// TODO Auto-generated constructor stub
		String[] fields = line.split(",");
		this.team = fields[0];
		this.name = fields[1];
		this.companyEmail = fields[2];
		this.personalEmail = fields[3];
		this.workphone = fields[4];
		this.cellphone = fields[5];
		this.msn = fields[6];
		this.skype = fields[7];
	}
	
	@Override
	public String toString() {
		return String.format("%s,%s,%s,%s,%s,%s,%s,%s", team,name,companyEmail,personalEmail,workphone,cellphone,msn,skype);
	}
}
