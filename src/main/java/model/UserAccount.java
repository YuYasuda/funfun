package model;

public class UserAccount {
	private int customerId;
	private String userId;
	private String customerLastName;
	private String customerFirstName;
	private String phoneNumber;
	private String email;
	private String birth;
	private String gender;
	private String password;
	private String remarks;
	private String shippingPostCode;
	private String shippingAddress1;
	private String shippingAddress2;
	private String shippingLastName;
	private String shippingFirstName;
	private String state;
	private String city;
	private String shippingPhoneNumber;

	public UserAccount(String userId, String customerLastName, String customerFirstName, 
            String phoneNumber, String email, String birth, String gender, 
            String password, String remarks, 
            String shippingPostCode, String shippingAddress1, String shippingAddress2, 
            String shippingLastName, String shippingFirstName, 
            String state, String city, String shippingPhoneNumber) {
		this.userId = userId;
		this.customerLastName = customerLastName;
		this.customerFirstName = customerFirstName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.birth = birth;
		this.gender = gender;
		this.password = password;
		this.remarks = remarks;
		this.shippingPostCode = shippingPostCode;
		this.shippingAddress1 = shippingAddress1;
		this.shippingAddress2 = shippingAddress2;
		this.shippingLastName = shippingLastName;
		this.shippingFirstName = shippingFirstName;
		this.state = state;
		this.city = city;
		this.shippingPhoneNumber = shippingPhoneNumber;
	}

	public UserAccount(String userId, String customerLastName, String customerFirstName, String phoneNumber, String email,String birth, String gender, String password, String remarks,String shippingPostCode, String shippingAddress1, String shippingAddress2 ) {
		this.userId = userId;
		this.customerLastName = customerLastName;
		this.customerFirstName = customerFirstName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.birth = birth;
		this.gender = gender;
	    this.password = password;
	    this.remarks = remarks;
	    this.shippingPostCode = shippingPostCode;
	    this.shippingAddress1 = shippingAddress1;
	    this.shippingAddress2 = shippingAddress2;	}
	
	public UserAccount(int customerId, String customerLastName, String customerFirstName, String phoneNumber, String email,String birth, String gender, String password, String remarks,String shippingPostCode, String shippingAddress1, String shippingAddress2 ) {
		this.customerId = customerId;
		this.customerLastName = customerLastName;
		this.customerFirstName = customerFirstName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.birth = birth;
		this.gender = gender;
	    this.password = password;
	    this.remarks = remarks;
	    this.shippingPostCode = shippingPostCode;
	    this.shippingAddress1 = shippingAddress1;
	    this.shippingAddress2 = shippingAddress2;	}
	
	public UserAccount(int customerId,  String customerLastName, String customerFirstName, String phoneNumber, String email,String birth, String gender, String postCode, String address, String password, String shippingPostCode, String shippingAddress1, String shippingAddress2) {
		this.customerId = customerId;
		this.customerLastName = customerLastName;
		this.customerFirstName = customerFirstName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.birth = birth;
		this.gender = gender;
	    this.password = password;
	    this.shippingPostCode = shippingPostCode;
	    this.shippingAddress1 = shippingAddress1;
	    this.shippingAddress2 = shippingAddress2;	}
	
	public UserAccount(int customerId, String userId, String customerLastName, String customerFirstName, String phoneNumber, String email, 
            String birth, String gender, String password, String remarks, 
            String shippingPostCode, String shippingAddress1, String shippingAddress2, 
            String shippingLastName, String shippingFirstName, String state, String city, String shippingPhoneNumber) {
		this.customerId = customerId;
		this.userId = userId;
		this.customerLastName = customerLastName;
		this.customerFirstName = customerFirstName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.birth = birth;
		this.gender = gender;
		this.password = password;
		this.remarks = remarks;
		this.shippingPostCode = shippingPostCode;
		this.shippingAddress1 = shippingAddress1;
		this.shippingAddress2 = shippingAddress2;
		this.shippingLastName = shippingLastName;
		this.shippingFirstName = shippingFirstName;
		this.state = state;
		this.city = city;
		this.shippingPhoneNumber = shippingPhoneNumber;
	}

	
	public int getCustomerId() {return customerId;}
	public String getUserId() {return userId;}
	public String getCustomerLastName() {return customerLastName;}
	public String getCustomerFirstName() {return customerFirstName;}
	public String getPhoneNumber() {return phoneNumber;}
	public String getEmail() {return email;}
	public String getBirth() {return birth;}
	public String getGender() {return gender;}
	public String getShippingPostCode() {return shippingPostCode;}
	public String getShippingAddress1() {return shippingAddress1;}
	public String getShippingAddress2() {return shippingAddress2;}
	public String getPassword() {return password;}
	public String getRemarks() {return remarks;}
	public String getShippingLastName() { return shippingLastName; }
	public String getShippingFirstName() { return shippingFirstName; }
	public String getState() { return state; }
	public String getCity() { return city; }
	public String getShippingPhoneNumber() { return shippingPhoneNumber; }



}
