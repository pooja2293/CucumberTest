package com.qa.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import com.qa.util.Base;




public class Email {

	private Folder folder;
	 
	  /**
	 * TODO Goes into the email folder.
	 *
	 * @author Pooja.
	 *         Created 26-Mar-2020.
	 */
	public enum EmailFolder {
	    /**
	     * TODO Checks the inbox in the mail.
	     */
	    INBOX("INBOX"),
	    /**
	     * TODO Checks the spam in the mail
	     */
	    SPAM("SPAM");
	 
	    private String text;
	 
	    private EmailFolder(String text){
	      this.text = text;
	    }
	 
	    /**
	     * TODO To get the text from the mail.
	     * @return string
	     *
	     */
	    public String getText() {
	      return this.text;
	    }
	  }
	 
	  /**
	   * Uses email.username and email.password properties from the properties file. Reads from Inbox folder of the email application
	   * @throws MessagingException
	   */
	  public Email() throws MessagingException {
	    this(EmailFolder.INBOX);
	  }
	  /**
	 * TODO This function calls other function to get username, password.
	 *
	 * @param emailFolder
	 * @throws MessagingException
	 */
	public Email(EmailFolder emailFolder) throws MessagingException {
		    this(getEmailUsernameFromProperties(),
		        getEmailPasswordFromProperties(),
		        getEmailServerFromProperties(),
		        emailFolder);
		  }
	  /**
	 * TODO This constructor load the file & connect to the server.
	 *
	 * @param username
	 * @param password
	 * @param server
	 * @param emailFolder
	 * @throws MessagingException
	 */
	public Email(String username, String password, String server, EmailFolder emailFolder) throws MessagingException {
		    Properties props = System.getProperties();
		    try {
		      props.load(new FileInputStream(new File("C:\\Users\\Pooja\\eclipse-workspace\\PageObjectBDDFramework\\src\\main\\java\\com\\qa\\config\\config.properties")));
		    } catch(Exception e) {
		      e.printStackTrace();
		      System.exit(-1);
		    }
		    
		 //   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		   
		 
		   Session session = Session.getInstance(props);
		    Store store = session.getStore("imaps");
		    store.connect(server, username, password);
		    this.folder = store.getFolder(emailFolder.getText());
		    this.folder.open(Folder.READ_WRITE);
		  }
		 
		 
		 
		  //************* GET EMAIL PROPERTIES *******************
		 
		  /**
		 * TODO This function get email address.
		 *
		 * @return String
		 */
		public static String getEmailAddressFromProperties(){
		    return System.getProperty("email.address");
		  }
		 
		  /**
		 * TODO This function get username.
		 *
		 * @return String
		 */
		public static String getEmailUsernameFromProperties(){
		    return System.getProperty("email.username");
		  }
		 
		  /**
		 * TODO This function get password.
		 *
		 * @return String
		 */
		public static String getEmailPasswordFromProperties(){
		    return System.getProperty("email.password");
		  }
		 
		  /**
		 * TODO This function get email protocol.
		 *
		 * @return	String
		 */
		public static String getEmailProtocolFromProperties(){
		    return System.getProperty("email.protocol");
		  }
		 
		  /**
		 * TODO This function get email port.
		 *
		 * @return String
		 */
		public static int getEmailPortFromProperties(){
		    return Integer.parseInt(System.getProperty("email.port"));
		  }
		 
		  /**
		 * TODO This function get email server.
		 *
		 * @return String
		 */
		public static String getEmailServerFromProperties(){
		    return System.getProperty("email.server");
		  }
		 
		  /**
		 * TODO This function get no. of messages.
		 *
		 * @return String
		 * @throws MessagingException
		 */
		public int getNumberOfMessages() throws MessagingException {
			    return this.folder.getMessageCount();
			  }
		  
		  /**
		 * TODO This function get latest messsage.
		 *
		 * @return String
		 * @throws MessagingException
		 */
		public Message getLatestMessage() throws MessagingException{
			    return getMessageByIndex(getNumberOfMessages());
			  }
			 
		  /**
		 * TODO This function get message by index.
		 *
		 * @param index
		 * @return Message
		 * @throws MessagingException
		 */
		public Message getMessageByIndex(int index) throws MessagingException {
			    return this.folder.getMessage(index);
			  }
		  
		  public String getAuthorizationCode() throws Exception {
			    Message email = getLatestMessage();
			    System.out.println(email.getContent());
			    BufferedReader reader = new BufferedReader(new InputStreamReader(email.getInputStream()));
			 
			    String line;
			   
			    String prefix = "Verification code:";
			 
			    while ((line = reader.readLine()) != null) {
			      if(line.startsWith(prefix)) {
			        return line.substring(line.indexOf(":") + 2);
			    		
			    		
			      }
			    }
			
				return null;
			 
}
}
		  
		