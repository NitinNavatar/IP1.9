package com.navatar.generic;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;

public class EmailLib {
	private static String[] partSecond;
	private static BodyPart mbp;
	static String lst;
	private static Message messages[];
	private static String mailcontent;
	private static String mailSubject;
	private static String Form;
	private static List<String> list = new ArrayList<String>();
	private static String[] ss1=null;
	String[] second_split=null;
	private static Properties emailProperties;
	private static Session mailSession;
	private static MimeMessage emailMessage;
	private static String [] splitedList =null;
	private String password=null;
	
	
	/**
	 * @author Ankit jaiswal
	 * @param mailType
	 * @param Email
	 * @param emailPassword
	 * @param peUserEmailID
	 * @param contactEmailId
	 * @return
	 * @throws InterruptedException
	 * @description gets the contact registration link from the gmail account
	 */	
	public String getInvestorRegLink(String mailType, String Email, String emailPassword, String peUserEmailID,String contactEmailId)
			throws InterruptedException {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		try {
			Thread.sleep(2000);
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			System.out.println("Gmail logging in...");
			store = session.getStore("imaps");
			store.connect("imap.gmail.com", Email, emailPassword);
			Folder inbox;
			Folder spam;
			System.out.println("Connected to = " + store);
			Thread.sleep(2000);
			inbox = store.getFolder("Inbox");
			spam=store.getFolder("[Gmail]/Spam");
			inbox.open(Folder.READ_WRITE);
			spam.open(Folder.READ_WRITE);
			spam.copyMessages(spam.getMessages(), inbox);
			Thread.sleep(5000);
			System.out.println("Total mails are = " + inbox.getMessageCount());
			Flags seen = new Flags(Flags.Flag.SEEN);
			FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
			Message[] UnreadMessages = inbox.search(unseenFlagTerm);

			System.out.println("Searching done...");
			System.out.println("Total Unread mails are = " + UnreadMessages.length);
			int i=1;
			int flag = 0, UnreadMessagesCount = 0;
			if ((mailType.equalsIgnoreCase("InvitationMail"))) {
				System.out.println("inside access");
				for (Message message : UnreadMessages) {
					UnreadMessagesCount = UnreadMessages.length;
					try {
						Address[] froms = message.getFrom();
						MimeMessage email = new MimeMessage(session);
						Address[] To = message.getAllRecipients();
						String sender = froms == null ? null : ((InternetAddress) froms[0]).getAddress();
						String too= To == null ? null : ((InternetAddress)To[0]).getAddress();
						String Subject = message.getSubject();
						System.out.println(i+" :sender's email address : " + sender);
						System.out.println(i+" TO email address: "+too);
						if ((sender.equals(peUserEmailID)) && Subject.contains("Invitation from") && too.contains(contactEmailId)) {
							System.out.println("Email Subject : " + message.getSubject());
							mailcontent = message.getContent().toString();
							System.out.println("\n\n>>>>>>>>>>>>>>>>>>>\n\n"+mailcontent+"\n\n>>>>>>>>>>>>>>>>>>>\n\n");
						   System.out.println(mailcontent);
							String[] ss = mailcontent.split("href=\"");
							ss1=ss[1].split("\"");
							System.out.println("\n\n>>>>>>>>>>>>>>>>>>>\n\n"+ss1[0]+"\n\n>>>>>>>>>>>>>>>>>>>\n\n");
//							for (int j = 0; j < 2; j++) {
//								list.add(ss[j]);
//							}
//							System.out.println("\n\n>>>>>>>>>>>>>>>>>>>\n\n"+list.get(1)+"\n\n>>>>>>>>>>>>>>>>>>>\n\n");
//							ss1 = list.get(1).split("\">Click here</a> to register.");
							break;
						}
						else {
							flag++;
						}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.getMessage();
						return null;
					}
				}
				inbox.close(false);
				store.close();

				if (flag == UnreadMessagesCount) {
					System.out.println("No Password Reset mail received");
					return null;
				} else {

				}
			}
		} catch (MessagingException e) {
			e.printStackTrace();
			return null;
		}
		return ss1[0];
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param mailType
	 * @param Email
	 * @param emailPassword
	 * @param peUserEmailID
	 * @param contactEmailId
	 * @return String
	 * @throws InterruptedException
	 * @description Gets the external admin registration link from the gmail account
	 */
	public String getExternalAdminRegLink(String mailType, String Email, String emailPassword, String peUserEmailID,String contactEmailId)
			throws InterruptedException {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		try {
			Thread.sleep(2000);
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			System.out.println("Gmail logging in...");
			store = session.getStore("imaps");
			store.connect("imap.gmail.com", Email, emailPassword);
			Folder inbox;
			Folder spam;
			System.out.println("Connected to = " + store);
			Thread.sleep(2000);
			inbox = store.getFolder("Inbox");
			spam=store.getFolder("[Gmail]/Spam");
			inbox.open(Folder.READ_WRITE);
			spam.open(Folder.READ_WRITE);
			spam.copyMessages(spam.getMessages(), inbox);
			Thread.sleep(5000);
			System.out.println("Total mails are = " + inbox.getMessageCount());
			Flags seen = new Flags(Flags.Flag.SEEN);
			FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
			Message[] UnreadMessages = inbox.search(unseenFlagTerm);

			System.out.println("Searching done...");
			System.out.println("Total Unread mails are = " + UnreadMessages.length);
			int i=1;
			int flag = 0, UnreadMessagesCount = 0;
			if ((mailType.equalsIgnoreCase("InvitationMail"))) {
				for (Message message : UnreadMessages) {
					UnreadMessagesCount = UnreadMessages.length;
					try {
						Address[] froms = message.getFrom();
						Address[] To = message.getAllRecipients();
						String sender = froms == null ? null : ((InternetAddress) froms[0]).getAddress();
						String too= To == null ? null : ((InternetAddress)To[0]).getAddress();
						String Subject = message.getSubject();
						System.out.println(i+" :sender's email address : " + sender);
						System.out.println(i+" TO email address: "+too);
						i++;
						 if ((sender.equals(peUserEmailID)) && (Subject.contains("Your Access to Deal Room")) && too.contains(contactEmailId)) {
//							System.out.println("Email Subject : " + message.getSubject());
							mailcontent = message.getContent().toString();
							
							System.out.println(mailcontent);
							for (int j = 0; j < 2; j++) {
								String[] ss = mailcontent.split("href=\"");
								//for(int j=0; j< ss.length; j++){
								list.add(ss[j]);
//								System.out.println(list.get(j).toString());
								//}
								// System.out.println("break>>>>>" +
								// list.get(j));
							}
							for(int k=1; k<list.size(); k++){
								splitedList =list.get(k).split("\"");	
							}
							System.out.println("link>>>>"+splitedList[0]);
							//ss1 = list.get(1).split("\">Click here</a> to register.");
							break;
						}

						else {
							flag++;
						}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.getMessage();
						return null;
					}
				}
				inbox.close(false);
				store.close();

				if (flag == UnreadMessagesCount) {
					System.out.println("No Password Reset mail received");
					return null;
				} else {

				}
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return splitedList[0];
	}

	/**
	 * @author Ankit Jaiswal
	 * @param mailType
	 * @param Email
	 * @param emailPassword
	 * @return Sring
	 * @throws InterruptedException
	 * @description Gets the reset password Link from the gmail account
	 */
	public String getResetPasswordLink(String mailType, String Email, String emailPassword)
			throws InterruptedException {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");

		try {
			Thread.sleep(2000);
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");

			System.out.println("Gmail logging in...");
			store = session.getStore("imaps");
			store.connect("imap.gmail.com", Email, emailPassword);
			Folder inbox;
			Folder spam;
			System.out.println("Connected to = " + store);
			Thread.sleep(2000);
			inbox = store.getFolder("Inbox");
			spam=store.getFolder("[Gmail]/Spam");
			inbox.open(Folder.READ_WRITE);
			spam.open(Folder.READ_WRITE);
			spam.copyMessages(spam.getMessages(), inbox);
			Thread.sleep(5000);
			System.out.println("Total mails are = " + inbox.getMessageCount());

			Flags seen = new Flags(Flags.Flag.SEEN);
			FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
			Message[] UnreadMessages = inbox.search(unseenFlagTerm);

			System.out.println("Searching done...");
			System.out.println("Total Unread mails are = " + UnreadMessages.length);

			int flag = 0, UnreadMessagesCount = 0;
			if ((mailType.equalsIgnoreCase("PasswordReset")) || (mailType.equalsIgnoreCase("Password_Reset"))
					|| (mailType.equalsIgnoreCase("Password-Reset") || (mailType.equalsIgnoreCase("Password")))) {
				for (Message message : UnreadMessages) {
					UnreadMessagesCount = UnreadMessages.length;
					try {
						Address[] froms = message.getFrom();
						String sender = froms == null ? null : ((InternetAddress) froms[0]).getAddress();
						String Subject = message.getSubject();
						System.out.println("sender's email address : " + sender);
						if ((sender.contains("support@") && sender.contains("salesforce.com"))
								&& Subject.contains("Welcome to Salesforce: Verify your account")) {
							System.out.println("Email Subject : " + message.getSubject());
							// String Content = (String) message.getContent();
							Object content = message.getContent();
							MimeMultipart mmp = (MimeMultipart) content;
							for (int j = 0; j < mmp.getCount() - 1; j++) {
								mbp = mmp.getBodyPart(j);
								lst = mbp.getContent().toString();
								// System.out.println(lst);

								String[] ss = lst.split("Log in automatically by clicking");

								partSecond = ss[1].split("\n");
								System.out.println("Reset password URL: " + partSecond[0]);
							}
						}
						// String fetchlink = Content.split("nk.")[1];
						// ResetLink = fetchlink.split("If you")[0];
						// System.out.println("ResetLink: " + ResetLink);
						// inbox.close(false);
						// store.close();

						else {
							flag++;
						}
						// if ((sender.equals("support@salesforce.com"))
						// && Subject.contains("Finish resetting your Salesforce
						// password")) {
						// System.out.println("Email Subject : " +
						// message.getSubject());
						// String Content = (String) message.getContent();
						// String[] fetchlink = Content.split("nk.");
						// partSecond = fetchlink[1].split("If you");
						// System.out.println("ResetLink: " + partSecond[0]);
						// inbox.close(false);
						// store.close();
						// break;
						// } else {
						// flag++;
						// }

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.getMessage();
						return null;
					}
				}
			}
			inbox.close(false);
			store.close();

			if (flag == UnreadMessagesCount) {
				System.out.println("No Password Reset mail received");
				return null;
			} else {

			}

		} catch (MessagingException e) {
			e.printStackTrace();

		}
		return partSecond[0];
	}
	
	/**
	 * 
	 * @author Ankit Jaiswal
	 * @description - This method is used to get temporary password for the target user
	 */
	public String getPassword(String mailType, String Email, String emailPassword)
			throws InterruptedException {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		try {
			Thread.sleep(2000);
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			System.out.println("Gmail logging in...");
			store = session.getStore("imaps");
			store.connect("imap.gmail.com", Email, emailPassword);
			Folder inbox;
			Folder spam;
			System.out.println("Connected to = " + store);
			Thread.sleep(2000);
			inbox = store.getFolder("Inbox");
			spam=store.getFolder("[Gmail]/Spam");
			inbox.open(Folder.READ_WRITE);
			spam.open(Folder.READ_WRITE);
			spam.copyMessages(spam.getMessages(), inbox);
			Thread.sleep(5000);
			System.out.println("Total mails are = " + inbox.getMessageCount());
			Flags seen = new Flags(Flags.Flag.SEEN);
			FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
			Message[] UnreadMessages = inbox.search(unseenFlagTerm);

			System.out.println("Searching done...");
			System.out.println("Total Unread mails are = " + UnreadMessages.length);

			int flag = 0, UnreadMessagesCount = 0;
			if ((mailType.equalsIgnoreCase("forgotPasswordMail"))) {
				for (Message message : UnreadMessages) {
					UnreadMessagesCount = UnreadMessages.length;
					try {
						Address[] froms = message.getFrom();
						String Subject = message.getSubject();
						System.out.println("Subject " + Subject);
						
						if (Subject.contains("Customer Portal password") || Subject.contains("Your new Navatar Investor password")) {
							System.out.println("Email Subject : " + message.getSubject());
							mailcontent = message.getContent().toString();
						   System.out.println(mailcontent);
								String[] ss = mailcontent.split("Password:");
								System.out.println("0>>>>>>>>>>>>>"+ss[0]);
								System.out.println("1>>>>>>>>>"+ss[1]);
								password = ss[1].trim();
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.getMessage();
					}
				}
				inbox.close(false);
				store.close();

				if (flag == UnreadMessagesCount) {
					System.out.println("No Password Reset mail received");
					return "No Password Reset mail received";
				} else {

				}
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return password;
	}
	

	/*
	 * public static String investorRegLink(String username, String password)
	 * throws MessagingException, IOException { try { Properties pro = new
	 * Properties(); pro.setProperty("mail.store.protocol", "imaps"); Session
	 * emailsession = Session.getDefaultInstance(pro); Store mailStore =
	 * emailsession.getStore("imaps"); mailStore.connect("imap.gmail.com",
	 * username, password); Folder emailFolder = mailStore.getFolder("INBOX");
	 * emailFolder.open(Folder.READ_WRITE); messages =
	 * emailFolder.getMessages(); for (int i = messages.length - 1; i <
	 * messages.length; i++) { mailSubject = messages[i].getSubject().trim();
	 * Form = messages[i].getFrom()[0].toString(); System.out.println(
	 * "email number : " + (i + 1)); System.out.println("Subject : " +
	 * messages[i].getSubject().trim()); // System.out.println("From : " +
	 * messages[i].getFrom()[0]); mailcontent =
	 * messages[i].getContent().toString(); // System.out.println(mailcontent);
	 * for (int j = 0; j < 2; j++) { String[] ss = mailcontent.split(
	 * "If you have not yet registered,&nbsp;<a href=\""); list.add(ss[j]); //
	 * System.out.println("break>>>>>" + list.get(j)); } ss1 =
	 * list.get(1).split("\">Click here</a>&nbsp;to register."); //
	 * System.out.println(ss1[0]); // messages[i].setFlag(Flags.Flag.DELETED,
	 * true); } emailFolder.close(false); mailStore.close(); }
	 * 
	 * catch (NoSuchProviderException nspe) { // TODO: handle exception
	 * nspe.printStackTrace(); } catch (MessagingException exp) { // TODO:
	 * handle exception exp.printStackTrace(); } return ss1[0];
	 * 
	 * }
	 */
	/**
	 * @author Ankit Jaiswal
	 * @param mailType
	 * @param Email
	 * @param emailPassword
	 * @param peUserEmailID
	 * @param contactEmailId
	 * @return String
	 * @throws InterruptedException
	 */
	public String getInvestorLoginLink(String mailType, String Email, String emailPassword, String peUserEmailID,String contactEmailId)
			throws InterruptedException {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		try {
			Thread.sleep(2000);
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			System.out.println("Gmail logging in...");
			store = session.getStore("imaps");
			store.connect("imap.gmail.com", Email, emailPassword);
			Folder inbox;
			Folder spam;
			System.out.println("Connected to = " + store);
			Thread.sleep(2000);
			inbox = store.getFolder("Inbox");
			spam=store.getFolder("[Gmail]/Spam");
			inbox.open(Folder.READ_WRITE);
			spam.open(Folder.READ_WRITE);
			spam.copyMessages(spam.getMessages(), inbox);
			Thread.sleep(5000);
			System.out.println("Total mails are = " + inbox.getMessageCount());
			Flags seen = new Flags(Flags.Flag.SEEN);
			FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
			Message[] UnreadMessages = inbox.search(unseenFlagTerm);

			System.out.println("Searching done...");
			System.out.println("Total Unread mails are = " + UnreadMessages.length);
			int i=1;
			int flag = 0, UnreadMessagesCount = 0;
			if ((mailType.equalsIgnoreCase("InvitationMail"))) {
				System.out.println("inside access");
				for (Message message : UnreadMessages) {
					UnreadMessagesCount = UnreadMessages.length;
					try {
						Address[] froms = message.getFrom();
						MimeMessage email = new MimeMessage(session);
						Address[] To = message.getAllRecipients();
						String sender = froms == null ? null : ((InternetAddress) froms[0]).getAddress();
						String too= To == null ? null : ((InternetAddress)To[0]).getAddress();
						String Subject = message.getSubject();
						System.out.println(i+" :sender's email address : " + sender);
						System.out.println(i+" TO email address: "+too);
						if ((sender.equals(peUserEmailID)) && Subject.contains("Invitation from") && too.contains(contactEmailId)) {
							System.out.println("Email Subject : " + message.getSubject());
							mailcontent = message.getContent().toString();
							System.out.println("\n\n>>>>>>>>>>>>>>>>>>>\n\n"+mailcontent+"\n\n>>>>>>>>>>>>>>>>>>>\n\n");
							String[] spilt=mailcontent.split("If you have already registered");
							System.err.println("0"+spilt[0]);
							System.err.println("1"+spilt[1]);	
							String[] ss = spilt[1].split("href=\"");
							ss1=ss[1].split("\"");
							System.out.println("\n\n>>>>>>>>>>>>>>>>>>>\n\n"+ss1[0]+"\n\n>>>>>>>>>>>>>>>>>>>\n\n");
//							for (int j = 0; j < 2; j++) {
//								list.add(ss[j]);
//							}
//							System.out.println("\n\n>>>>>>>>>>>>>>>>>>>\n\n"+list.get(1)+"\n\n>>>>>>>>>>>>>>>>>>>\n\n");
//							ss1 = list.get(1).split("\">Click here</a> to register.");
							break;
						}
						else {
							flag++;
						}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.getMessage();
						return null;
					}
				}
				inbox.close(false);
				store.close();

				if (flag == UnreadMessagesCount) {
					System.out.println("No Password Reset mail received");
					return null;
				} else {

				}
			}
		} catch (MessagingException e) {
			e.printStackTrace();
			return null;
		}
		return ss1[0];
	}
	
	public String getInvestorMailContent(String mailType, String Email, String emailPassword, String peUserEmailID,String contactEmailId)
			throws InterruptedException {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		try {
			Thread.sleep(2000);
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			System.out.println("Gmail logging in...");
			store = session.getStore("imaps");
			store.connect("imap.gmail.com", Email, emailPassword);
			Folder inbox;
			Folder spam;
			System.out.println("Connected to = " + store);
			Thread.sleep(2000);
			inbox = store.getFolder("Inbox");
			spam=store.getFolder("[Gmail]/Spam");
			inbox.open(Folder.READ_WRITE);
			spam.open(Folder.READ_WRITE);
			spam.copyMessages(spam.getMessages(), inbox);
			Thread.sleep(5000);
			System.out.println("Total mails are = " + inbox.getMessageCount());
			Flags seen = new Flags(Flags.Flag.SEEN);
			FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
			Message[] UnreadMessages = inbox.search(unseenFlagTerm);

			System.out.println("Searching done...");
			System.out.println("Total Unread mails are = " + UnreadMessages.length);
			int i=1;
			int flag = 0, UnreadMessagesCount = 0;
			if ((mailType.equalsIgnoreCase("InvitationMail"))) {
				System.out.println("inside access");
				for (Message message : UnreadMessages) {
					UnreadMessagesCount = UnreadMessages.length;
					try {
						Address[] froms = message.getFrom();
						MimeMessage email = new MimeMessage(session);
						Address[] To = message.getAllRecipients();
						String sender = froms == null ? null : ((InternetAddress) froms[0]).getAddress();
						String too= To == null ? null : ((InternetAddress)To[0]).getAddress();
						String Subject = message.getSubject();
						System.out.println(i+" :sender's email address : " + sender);
						System.out.println(i+" TO email address: "+too);
						if ((sender.equals(peUserEmailID)) && Subject.contains("Invitation from") && too.contains(contactEmailId)) {
							System.out.println("Email Subject : " + message.getSubject());
							mailcontent = message.getContent().toString();
							System.out.println("\n\n>>>>>>>>>>>>>>>>>>>\n\n"+mailcontent+"\n\n>>>>>>>>>>>>>>>>>>>\n\n");
						   System.out.println(mailcontent);
							break;
						}
						else {
							flag++;
						}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.getMessage();
						return null;
					}
				}
				inbox.close(false);
				store.close();

				if (flag == UnreadMessagesCount) {
					System.out.println("No Password Reset mail received");
					return null;
				} else {

				}
			}
		} catch (MessagingException e) {
			e.printStackTrace();
			return null;
		}
		return mailcontent;
	}
	
	public String getInvestorCustomMailContent(String mailType, String Email, String emailPassword, String peUserEmailID,String contactEmailId,String SubjectMail)
			throws InterruptedException {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		try {
			Thread.sleep(2000);
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			System.out.println("Gmail logging in...");
			store = session.getStore("imaps");
			store.connect("imap.gmail.com", Email, emailPassword);
			Folder inbox;
			Folder spam;
			System.out.println("Connected to = " + store);
			Thread.sleep(2000);
			inbox = store.getFolder("Inbox");
			spam=store.getFolder("[Gmail]/Spam");
			inbox.open(Folder.READ_WRITE);
			spam.open(Folder.READ_WRITE);
			spam.copyMessages(spam.getMessages(), inbox);
			Thread.sleep(5000);
			System.out.println("Total mails are = " + inbox.getMessageCount());
			Flags seen = new Flags(Flags.Flag.SEEN);
			FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
			Message[] UnreadMessages = inbox.search(unseenFlagTerm);

			System.out.println("Searching done...");
			System.out.println("Total Unread mails are = " + UnreadMessages.length);
			int i=1;
			int flag = 0, UnreadMessagesCount = 0;
			if ((mailType.equalsIgnoreCase("InvitationMail"))) {
				System.out.println("inside access");
				for (Message message : UnreadMessages) {
					UnreadMessagesCount = UnreadMessages.length;
					try {
						Address[] froms = message.getFrom();
						MimeMessage email = new MimeMessage(session);
						Address[] To = message.getAllRecipients();
						String sender = froms == null ? null : ((InternetAddress) froms[0]).getAddress();
						String too= To == null ? null : ((InternetAddress)To[0]).getAddress();
						String Subject = message.getSubject();
						System.out.println(i+" :sender's email address : " + sender);
						System.out.println(i+" TO email address: "+too);
						if ((sender.equals(peUserEmailID)) && Subject.contains(SubjectMail) && too.contains(contactEmailId)) {
							System.out.println("Email Subject : " + message.getSubject());
							mailcontent = message.getContent().toString();
							System.out.println("\n\n>>>>>>>>>>>>>>>>>>>\n\n"+mailcontent+"\n\n>>>>>>>>>>>>>>>>>>>\n\n");
						   System.out.println(mailcontent);
							break;
						}
						else {
							flag++;
						}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.getMessage();
						return null;
					}
				}
				inbox.close(false);
				store.close();

				if (flag == UnreadMessagesCount) {
					System.out.println("No Password Reset mail received");
					return null;
				} else {

				}
			}
		} catch (MessagingException e) {
			e.printStackTrace();
			return null;
		}
		return mailcontent;
	}
	
	public static boolean mailReceived(String Email, String emailPassword, String fromEmailID, String toEmailID, String subject, String Content) {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		try {
			CommonLib.ThreadSleep(2000);
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			System.out.println("Gmail logging in...");
			store = session.getStore("imaps");
			store.connect("imap.gmail.com", Email, emailPassword);
			Folder inbox;
			System.out.println("Connected to = " + store);
			CommonLib.ThreadSleep(2000);
			inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_WRITE);
			CommonLib.ThreadSleep(5000);
			System.out.println("Total mails are = " + inbox.getMessageCount());
			Flags seen = new Flags(Flags.Flag.SEEN);
			FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
			Message[] UnreadMessages = inbox.search(unseenFlagTerm);

			System.out.println("Searching done...");
			System.out.println("Total Unread mails are = " + UnreadMessages.length);
			int i=1;
			int flag = 0, UnreadMessagesCount = 0;
			System.out.println("inside access");
			for (Message message : UnreadMessages) {
				UnreadMessagesCount = UnreadMessages.length;
				try {
					Address[] froms = message.getFrom();
					MimeMessage email = new MimeMessage(session);
					Address[] To = message.getAllRecipients();
					String sender = froms == null ? null : ((InternetAddress) froms[0]).getAddress();
					String too= To == null ? null : ((InternetAddress)To[0]).getAddress();
					String Subject = message.getSubject();
					System.out.println(i+" :sender's email address : " + sender);
					System.out.println(i+" TO email address: "+too);
					if ((sender.equals(fromEmailID)) && Subject.contains(subject) && too.contains(toEmailID)) {
						String msg = message.getContent().toString();
						if(msg.contains(Content)) {
							System.out.println(msg);
							System.out.println(Content+ " is present in the mail content");
							
							return true;
						} else {
							return false;
						}
					} else {
						flag++;
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.getMessage();
					return false;
				}
			}
			inbox.close(false);
			store.close();

			if (flag == UnreadMessagesCount) {
				System.out.println("No mail received");
				return false;
			} else {

			}
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public static void sendMail(String fromEmail, String fromPassword, String[] toEmailId, String emailSubject, String emailBody, String[] attachments) throws AddressException, MessagingException {

		setMailServerProperties();
		createEmailMessage(toEmailId, emailSubject, emailBody, attachments);
		sendEmail(fromEmail, fromPassword);
	}

	public static void setMailServerProperties() {

		String emailPort = "587";//gmail's smtp port

		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");

	}

	public static void createEmailMessage(String[] toEmailId, String emailSubject, String emailBody, String[] attachments) throws AddressException,
			MessagingException {

		mailSession = Session.getDefaultInstance(emailProperties, null);
		emailMessage = new MimeMessage(mailSession);

		for (int i = 0; i < toEmailId.length; i++) {
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmailId[i]));
		}
		emailMessage.setSubject(emailSubject);
		 BodyPart messageBodyPart = new MimeBodyPart();
         messageBodyPart.setText(emailBody);
         Multipart multipart = new MimeMultipart();
         multipart.addBodyPart(messageBodyPart);

         for(int i = 0; i < attachments.length; i++){
        	 messageBodyPart = new MimeBodyPart();
        	 String filename = attachments[i]; /*System.getProperty("user.dir")+"/reports/logs/IPLog19_02_26_06_40_53.log"*/
        	 DataSource source = new FileDataSource(filename);
        	 messageBodyPart.setDataHandler(new DataHandler(source));
        	 messageBodyPart.setFileName(filename);
        	 multipart.addBodyPart(messageBodyPart);
         }
         emailMessage.setContent(multipart );
	}

	public static void sendEmail(String fromEmail, String fromPassword)  {

		String emailHost = "smtp.gmail.com";
		Transport transport;
		try {
			transport = mailSession.getTransport("smtp");
			transport.connect(emailHost, fromEmail, fromPassword);
			transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
			transport.close();
			AppListeners.appLog.info("Email Sent Successfully.");
			return;
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AppListeners.appLog.error("Email is not sent.");
	}
	
	public String getForgotPasswordMsg(String mailType, String Email, String emailPassword, String peUserEmailID,String contactEmailId)
			throws InterruptedException {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		try {
			Thread.sleep(2000);
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			System.out.println("Gmail logging in...");
			store = session.getStore("imaps");
			store.connect("imap.gmail.com", Email, emailPassword);
			Folder inbox;
			Folder spam;
			System.out.println("Connected to = " + store);
			Thread.sleep(2000);
			inbox = store.getFolder("Inbox");
			spam=store.getFolder("[Gmail]/Spam");
			inbox.open(Folder.READ_WRITE);
			spam.open(Folder.READ_WRITE);
			spam.copyMessages(spam.getMessages(), inbox);
			Thread.sleep(5000);
			System.out.println("Total mails are = " + inbox.getMessageCount());
			Flags seen = new Flags(Flags.Flag.SEEN);
			FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
			Message[] UnreadMessages = inbox.search(unseenFlagTerm);

			System.out.println("Searching done...");
			System.out.println("Total Unread mails are = " + UnreadMessages.length);
			int i=1;
			int flag = 0, UnreadMessagesCount = 0;
			if ((mailType.equalsIgnoreCase("forgotPasswordMail"))) {
				System.out.println("inside access");
				for (Message message : UnreadMessages) {
					UnreadMessagesCount = UnreadMessages.length;
					try {
						Address[] froms = message.getFrom();
						MimeMessage email = new MimeMessage(session);
						Address[] To = message.getAllRecipients();
						String sender = froms == null ? null : ((InternetAddress) froms[0]).getAddress();
						String too= To == null ? null : ((InternetAddress)To[0]).getAddress();
						String Subject = message.getSubject();
						System.out.println(i+" :sender's email address : " + sender);
						System.out.println(i+" TO email address: "+too);
						if ((sender.equals(peUserEmailID)) && Subject.contains("Reset your Navatar Investor password")|| (Subject.contains("Reset your") && Subject.contains("Navatar Investor password")) ) {
							System.out.println("Email Subject : " + message.getSubject());
							mailcontent = message.getContent().toString();
							System.out.println("\n\n>>>>>>>>>>>>>>>>>>>\n\n"+mailcontent+"\n\n>>>>>>>>>>>>>>>>>>>\n\n");
							
							if(mailcontent.contains(contactEmailId)){
							
							
							
						   //System.out.println(mailcontent);
						//	String[] ss = mailcontent.split("href=\"");
						//	ss1=ss[1].split("\"");
						//	System.out.println("\n\n>>>>>>>>>>>>>>>>>>>\n\n"+ss1[0]+"\n\n>>>>>>>>>>>>>>>>>>>\n\n");
//							for (int j = 0; j < 2; j++) {
//								list.add(ss[j]);
//							}
//							System.out.println("\n\n>>>>>>>>>>>>>>>>>>>\n\n"+list.get(1)+"\n\n>>>>>>>>>>>>>>>>>>>\n\n");
//							ss1 = list.get(1).split("\">Click here</a> to register.");
							break;
							}
						}
						else {
							flag++;
						}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.getMessage();
						return null;
					}
				}
				inbox.close(false);
				store.close();

				if (flag == UnreadMessagesCount) {
					System.out.println("No Password Reset mail received");
					return null;
				} else {

				}
			}
		} catch (MessagingException e) {
			e.printStackTrace();
			return null;
		}
		return mailcontent;
	}
	
}
