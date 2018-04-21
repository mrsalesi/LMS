<%@page import="java.util.ArrayList"%>
<%@page import="java.util.regex.Matcher"%>
<%@page import="java.util.List"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="cms.tools.jjTools"%>
<%@page import="cms.tools.ServerLog"%>
<%@page import="jj.jjFileTxt"%>
<%@page import="cms.tools.Server"%>
<%@page import="jj.jjDatabaseWeb"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<script type="text/javascript">
    window.location.replace("<%= "/lms/" + Server.mainPage%>");
</script>

<%
    ServerLog.Print("######################################");
    ServerLog.Print("call for index.jsp");
    ServerLog.Print("######################################");
    String address = request.getServletContext().getRealPath("/");
    File file = new File(address + "/" + Server.mainPage);
//    StringBuilder result = new StringBuilder(jjFileTxt.read(file).replace("\n", ""));
    if (!file.exists()) {%>
<%= "is not bieng such as file"%>;
<%  } else {
    StringBuilder result = new StringBuilder(jjFileTxt.read(file).replace("\n", ""));
    //============ BY RASHIDI ========>
//    int indexOfHead = result.indexOf("head");
//    if(indexOfHead>-1){
//        result.insert(indexOfHead + 1,"<script type=\"text/javascript\"> window.location.replace(\"/002/"+Server.mainPage+"\");</script>");
//    }

    List<String> listMatches = new ArrayList<String>();
    int index = result.indexOf("jjCmsScript");//تگ اسکریپتی که در آن مشخص می شود چه کانتنت هایی خوانده شود باید این آی دی را داشته باشد.
    if (index > -1) {
//        index = result.indexOf(">", index);
        //از فایل خوانده شده رشته های منطبق با عبارت منطقی زیر را پیدا میکند
        Pattern pattern = Pattern.compile("do=[a-zA-Z0-9\\-_]+\\.[a-zA-Z0-9ا-ی\\s+&\\+='\"\\-_]+");//---> do=ClassName.Method&parameters=Values&panel=panelName
        // در صورتی که در فایل خوانده شده علامت 
        //+ " 
        //برای الحاق رشته ها باشد جواب مد نظر گرفته نمی شود
        //پس در فایل 
        //html
        // خوانده شده این مورد رعایت شود
        Matcher matcher = pattern.matcher(result.substring(index));//پیدا کردن الگو در فایل خوانده شده
        while (matcher.find()) {
            listMatches.add(matcher.group(0));
//            System.out.println("* " + matcher.group(0));
        }
        Pattern panelPatern = Pattern.compile("panel=[a-zA-Z0-9\\-_]+");//---> panel=panelName
        Matcher panelMatcher;
        String panel[];
        int panelIndex;
        jjDatabaseWeb db;
        Server.Connect();
        db = Server.db;
//        jjTools.setSessionAttribute(request, "myLang", 1);
        for (String s : listMatches) {

            //به ازای تمام رشته های پیدا شده تابع 
            //noAjaxRun
            // اجرا می شود و نتیجه ی اجرای آنها در پنل مربوطه اش چاپ می شود
//            ServerLog.Print("^^^^^^ matched patern : " + s);
            panelMatcher = panelPatern.matcher(s);//پیدا کردن پنل
            while (panelMatcher.find()) {
                panel = panelMatcher.group(0).toString().split("=");
                panelIndex = result.indexOf("\"" + panel[1] + "\"");
//                ServerLog.Print("%%%%%%%%%%%%%%%%%% panel : " + panel[1]);
                if (panelIndex > -1) {
                    String content = "";
                    panelIndex = result.indexOf(">", panelIndex);
                    content = cms.tools.Server.noAjaxRun(s.substring(0, s.length() - 1), request);
                    result.insert(panelIndex + 1, cms.cms.Content.ConvertToWiki(request, content, db, false));
                    //                    result.replace(0, result.length(), cms.cms.Content.ConvertToWiki(request, content, db, false));
                    ;//محتوای ایجاد شده سمت سرور در پنل چاپ می شود
                }
            }
        }
//        int index = result.indexOf("\"sw\"");//finding div in wich id="sw"
//        if (index > -1) {
//            index = result.indexOf(">", index); //finding end of "sw" div ( <div id="sw" clas="example" style="any" >
//
//            result = result.insert(index + 1, cms.cms.Content.ConvertToWiki(request, content, db, false));
//        }
    }

            //<============ BY RASHIDI ======== 
//    int index = result.indexOf("\"sw\"");//finding div in wich id="sw"
//    if (index > -1) {
//        index = result.indexOf(">", index); //finding end of "sw" div ( <div id="sw" clas="example" style="any" >
//        jjDatabaseWeb db;
//        Server.Connect();
//        db = Server.db;
//        //            String content = cms.tools.Server.run(Server.getClazzes(), "Content", "sw", request, db, false);
//        jjTools.setSessionAttribute(request, "myLang", 1);
//        String content = cms.tools.Server.noAjaxRun("do=Content.sw&text=جمله روز&panel=sidebar&jj=1", request);
//        //            String content = cms.tools.Server.run(request, re, false);
//        result = result.insert(index + 1, cms.cms.Content.ConvertToWiki(request, content, db, false));
//    }
%>
<%= result.toString()%>
<% }
%>
