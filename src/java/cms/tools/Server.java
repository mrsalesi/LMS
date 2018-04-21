package cms.tools;

import cms.access.*;
import cms.cms.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.*;
import javax.servlet.http.*;
import jj.jjCalendar_IR;
import jj.jjDatabaseWeb;

public class Server extends HttpServlet {
    
//    public static sites mainSite = sites.iranSepano;
//    public static String portalPage = "";
//    public static String databaseName = "db_arminpolymer";
//    public static String userName = "root";
//    public static String password = "m123456";
////    public static String userNameSMS = "mrsalesi";
//    public static String userNameSMS = "0";
////    public static String passwordSMS = "1234";
//    public static String passwordSMS = "0";
//    public static String defaultLang = "1"; // fa
//    public static String smsKey = "0";
//    public static String smsPanelNumber = "30006703323323";
//    public static String siteName = "komeilipoor.ir";// comment answer sender
//    public static String emailAccount = "komeilipoor.ir@gmail.com";
//    public static String passEmail = "komeilipoor123";
//    public static String smtpAcount = "smtp.gmail.com";
//    public static String mainPage = "index_arminpolymer.html";
 public static sites mainSite = sites.iranSepano;
    public static String portalPage = "";
    public static String databaseName ="db_lms";
    public static String userName = "root";
    public static String password = "m123456";
//    public static String userNameSMS = "mrsalesi";
    public static String userNameSMS = "0";
//    public static String passwordSMS = "1234";
    public static String passwordSMS = "0";
    public static String defaultLang = "1"; // fa
    public static String smsKey = "0";
    public static String smsPanelNumber = "30006703323323";
    public static String siteName = "komeilipoor.ir";// comment answer sender
    public static String emailAccount = "komeilipoor.ir@gmail.com";
    public static String passEmail = "komeilipoor123";
    public static String smtpAcount = "smtp.gmail.com";
    public static String mainPage = "index_MySite.html";
    
    
    public static jjDatabaseWeb db;
    public static final String port = "3306";
//    public static final String port = "3307";
    public static final String serverHostIP = "localhost";
    public static boolean pollNeedLogin = false;
    public static boolean pollShowAfterVote = true;
    public static HttpServletResponse Publicresponse;//By Md

    public static enum sites {
        iranSepano;
    }
    private static List<Class> clazzes = new ArrayList<Class>();

    public static List<Class> getClazzes() {
        if (clazzes.isEmpty()) {
            clazzes.add(Content.class);
            clazzes.add(News.class);
            clazzes.add(Pic.class);
            clazzes.add(Category_Gallery.class);
            clazzes.add(Category_News.class);
            clazzes.add(Category_Forum.class);
            clazzes.add(Category_Product.class);
            clazzes.add(Category_Poll.class);
            clazzes.add(Comment.class);
            clazzes.add(Access_Group.class);
            clazzes.add(Access_Group_User.class);
            clazzes.add(Access_User.class);
            clazzes.add(Product.class);
            clazzes.add(Forum.class);
            clazzes.add(Backup.class);
            clazzes.add(Poll.class);
            clazzes.add(sms.class);// ADDED BY RASHIDI
            clazzes.add(smsSetting.class);// ADDED BY RASHIDI
            clazzes.add(Enrolment.class);
            clazzes.add(Enrolment3.class);
            clazzes.add(Portal.class);
            clazzes.add(PortalUser.class);
            clazzes.add(Factor.class);
            clazzes.add(Customer.class);
            clazzes.add(tags.class);//SHAHSANAEI
            clazzes.add(Language.class);// ADDED BY RASHIDI
            clazzes.add(email.class);
            clazzes.add(Search.class);
        }
        return clazzes;
    }

    protected void run(HttpServletRequest request, HttpServletResponse response, boolean isPost) throws ServletException, IOException {
        Publicresponse = response;
        StringBuffer script = new StringBuffer();
        PrintWriter out = jjTools.getWriterUTF8(request, response);
        try {
            Connect();
//            jjTools.setLang(request);
//            request.getSession(true).setMaxInactiveInterval(6000);
            script.append(Language.setLang(request));
//            script.append(jjTools.setLang(request));/////*****************/*/*/*/*/*/*/*/*/
            jjTools.setPoolStatus(request);
            String Action = jjTools.getParameter(request, "do");
            String clazz = jjTools.getParameter(request, "tbl");
            String method = jjTools.getParameter(request, "act");
//            String dbName = jjTools.getParameter(request, "db");
//            if (!dbName.equals("")) {
//                databaseName = dbName;
//                jjTools.setSessionAttribute(request, "databaseName", dbName);
//            }
//            databaseName = jjTools.getSessionAttribute(request, "databaseName").equals("") ? databaseName : jjTools.getSessionAttribute(request, "databaseName");
            int dot = Action.indexOf(".");
            if (dot > -1) {
                clazz = Action.substring(0, dot);
                method = Action.substring(dot + 1, Action.length());
            }
            // -----------------------------------------------------------------
            script.append(run(getClazzes(), clazz, method, request, db, isPost));
            ServerLog.Print(script);//By Md
            //ServerLog.Print(script.toString());
            out.print(script);
            // Runtime.getRuntime().gc();
            System.gc();
        } catch (Exception ex) {
            out.print(ErrorHandler(ex));
        } finally {
            out.close();
            System.gc();
//            db.disConnectCustom();
        }
    }

    public static void Connect() {
        if (db == null) {
            db = new jjDatabaseWeb(userName, password, databaseName, serverHostIP, port);
        }
        db.ConnectCustom();        
    }

    public static String run(List<Class> clazz, String className, String methodName, HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            jjTools.ShowAllParameter(request);
            Language.setLang(request);//============ BY RASHIDI ========
//            jjTools.ShowAllAttribute(request);
            String Action = jjTools.getParameter(request, "do");
            String reqClazz = jjTools.getParameter(request, "tbl");
            String method = jjTools.getParameter(request, "act");
//            String dbName = jjTools.getParameter(request, "db");
//            if (!dbName.equals("")) {
//                databaseName = dbName;
//                jjTools.setSessionAttribute(request, "databaseName", dbName);
//            }
//            databaseName = jjTools.getSessionAttribute(request, "databaseName").equals("") ? databaseName : jjTools.getSessionAttribute(request, "databaseName");
            int dot = Action.indexOf(".");
            if (dot > -1) {
                reqClazz = Action.substring(0, dot);
                method = Action.substring(dot + 1, Action.length());
            }
            for (int j = 0; j < clazz.size(); j++) {
                if (clazz.get(j).getSimpleName().equals(className)) {
                    Method[] methods = clazz.get(j).getMethods();
                    for (int i = 0; i < methods.length; i++) {
                        if (methods[i].getName().equals(methodName)) {
                            ServerLog.Print("Run: " + className + "." + methods[i].getName() + "()");
                            return (String) methods[i].invoke(null, request, db, isPost);
                        }
                    }
                }
            }
            return "";
        } catch (Exception ex) {
            return ErrorHandler(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        run(request, response, true);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        run(request, response, false);
    }

    public static String noAjaxRun(String parameters, HttpServletRequest request) {
        try {
            ServerLog.Print("------- noAjaxRun ---------");
            String action = parameters;
            String reqClazz = "";
            String method = "";
            int index1 = action.indexOf("do=");
            int index2 = action.indexOf(".");
            if (index1 >= 0 && index2 > 0) {// do  وجود دارد؟
                index1 = action.indexOf("=", index1) + 1;
                reqClazz = action.substring(index1, index2);
                index1 = index2 + 1;// بعد از  نقطه برای پیدا کردن تابع
                index2 = action.indexOf("&");// بعد از  نقطه برای پیدا کردن تابع
                method = action.substring(index1, index2);
            }
            String attributes[] = parameters.split("&");
            for (int i = 0; i < attributes.length; i++) {
                if (attributes[i].matches(".*=.*")) {
                    ServerLog.Print(attributes[i]);
                    String attribNameAndValue[] = attributes[i].split("=");
                    request.setAttribute(attribNameAndValue[0], attribNameAndValue[1]);
                }
            }
            jjTools.ShowAllAttribute(request);
//            String reqClazz = jjTools.getParameter(request, "tbl");
//            String method = jjTools.getParameter(request, "act");
//            String method = jjTools.getParameter(request, "act");

//            String reqClazz = jjTools.getParameter(request, "tbl");
            int dot = action.indexOf(".");
            String content = cms.tools.Server.run(Server.getClazzes(), reqClazz, method, request, db, false);
            return content;
        } catch (Exception ex) {
            System.out.println(ex);
            ServerLog.Print(ex);
            return ex.toString();
        }
    }

    public static String ErrorHandler(Exception ex) {
        try {
            db.ConnectCustom();
            System.err.println(ex.toString());
            StringBuilder dbErrorWrite = new StringBuilder();
//            StringBuffer returnDialog = new StringBuffer();
            StackTraceElement[] stackTrace = ex.getStackTrace();
            for (int i = 0; i < stackTrace.length; i++) {
                StackTraceElement st = stackTrace[i];
                if (st.getClassName().startsWith("cms") || st.getClassName().startsWith("tice")) {
                    if (!st.getClassName().startsWith("cms.tools.Server")) {
                        dbErrorWrite.append("<p style='direction:ltr'>").append(st.getClassName()).append(".").append(st.getMethodName()).append(" > line:").append(st.getLineNumber()).append("</p>");
                    }
                }
            }
            dbErrorWrite.append("<p style='direction:ltr'>").append(ex.toString()).append("</p>");
//            returnDialog.append("<p style='float: left;direction:ltr'>" + ex.toString() + "</p>");

            Map<String, Object> map = new HashMap<String, Object>();
            map.put(Comment._date, new jjCalendar_IR().getDBFormat_8length());
            map.put(Comment._email, "mrsalesi@gmail.com");
            map.put(Comment._name_Full, "سیستم");
            map.put(Comment._tell, "03112683807");
            map.put(Comment._text, dbErrorWrite.toString());
            map.put(Comment._title, "مشکلی در سیستم");
            map.put(Comment._answer, "");
            db.insert(Comment.tableName, map);
            return Js.dialog(dbErrorWrite.toString());
        } catch (Exception ex2) {
            return Js.dialog("Error in Server ErrorHandler");
        }
    }
//    public static void setSettingProject(String siteName, String userName, String password, String databaseName, String defaultLang) {
//        Server.siteName = siteName;
//        Server.userName = userName;
//        Server.password = password;
//        Server.databaseName = databaseName;
//        Server.defaultLang = defaultLang;
//    }

    public static boolean sendEmail(String from, String to, String subject, String body, boolean isRtl, HttpServletRequest request) {
//        String host = "mail.hafteghlim-ins.com";
//        String user = "mail@hafteghlim-ins.com";
//        String pass = "m!@#$%&*()";
        String host = Server.smtpAcount;
        String user = Server.emailAccount;
        String pass = Server.passEmail;
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.localhost", host);
        props.put("mail.smtp.user", user);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] address = new InternetAddress[0];
            if (to.indexOf(",") > 0) {
                String[] split = to.split(",");
                address = new InternetAddress[split.length];
                for (int i = 0; i < split.length; i++) {
                    address[i] = new InternetAddress(split[i]);
                }
            } else {
                address = new InternetAddress[1];
                address[0] = new InternetAddress(to);
            }
            message.setRecipients(Message.RecipientType.TO, address);

            message.setSubject(subject, "UTF-8");
            message.setHeader("Content-Type", "UTF-8");
//            File file = new File(request.getServletContext().getRealPath("/print") + "/email.html");
//            if (file.exists()) {
//                body = jjFileTxt.read(file).replace("bodybody", body);
//            } else {
            message.setText(body, "UTF-8");
//            }
            Transport transport = session.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
        System.out.println("");
        return true;
    }

    public static void main(String[] args) {
        Server.sendEmail("mail@hafteghlim-ins.com", "mrsalesi@gmail.com", "تست", "تست test ", true, null);
    }
}
