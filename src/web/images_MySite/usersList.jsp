<%-- 
    Document   : usersList
    Created on : Oct 9, 2017, 9:28:57 AM
    Author     : shohreh&shahla
--%>

<%@page import="jj.jjDatabase.separateRow(DefaultTableModel)"%>
<%@page import="java.util.Enumeration"%>
<%@page import="jj.jjDatabaseWeb"%>
<%@page import="java.util.List"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang=""> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Newline CSS Template with a video background</title>
        <!--
        Newline Template
        http://www.templatemo.com/tm-503-newline
        -->
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="apple-touch-icon" href="apple-touch-icon.png">
        <script src="js/register.js" type="text/javascript"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap-theme.min.css">
        <link href="css/font.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/fontAwesome.css">
        <link rel="stylesheet" href="css/templatemo-style.css">

        <link href="https://fonts.googleapis.com/css?family=Montserrat:100,200,300,400,500,600,700,800,900" rel="stylesheet">

        <script src="js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>
    </head>
    <body>

        <div class="overlay"></div>
        <section class="top-part">
            <video controls autoplay loop>
                <source src="videos/video.mp4" type="video/mp4">
                <source src="videos/video.ogg" type="video/ogg">
                Your browser does not support the video tag.
            </video>
        </section>

        <section class="cd-hero">

            <div class="cd-slider-nav">
                <nav>
                    <span class="cd-marker item-1"></span>
                    <ul>
                        <li><a href="#"><div class="image-icon"><i class="fa fa-sign-in" style="transform: scale(3.5)"></i></div><h6>کاربر اضافه شده </h6></a></li>
                    </ul>
                </nav> 

            </div> <!-- .cd-slider-nav -->

            <ul class="cd-hero-slider">



                <!--                /---------database-table----------/
                                /--------------------------------/-->

                <li class="selected">
                    <div class="heading">
                        <h1>Database</h1>
                        <span></span> 
                    </div>
                    <div class="cd-half-width sixth-slide">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="content sixth-content">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="left-info">

                                                </div>
                                            </div>
                                            <div class="col-md-8">
                                                <div class="row">
        <table border="1px" >
            <thead>
                <tr>
                    <td class="header-cell">نام کاربری</td>
                    <td class="header-cell">ردیف</td>
                    <td class="header-cell">پسورد </td>
                    <td class="header-cell">فامیلی</td>
                    <td class="header-cell">ایمیل</td>
                    <td class="header-cell">تلفن</td>
                </tr>
            </thead>
            <tbody>
                <%
                    String username=request.getParameter("username_R");
                    String password=request.getParameter("password_R");
                    String family=request.getParameter("family_R");
                    String email=request.getParameter("email_R");
                    String tel=request.getParameter("tel_R");
        Enumeration<String> parameterNames = request.getParameterNames();
        System.out.println("-----------ShowAllParameter-----------");
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            System.out.println(key + "=" + request.getParameter(key));
        }
         System.out.println("--------------_________---------------");
        
        jjDatabaseWeb db = new jjDatabaseWeb("root", "m123456", "db_test1","localhost","3306");
        db.ConnectCustom();
        DefaultTableModel dtm = db.Select("test");
        Map<String, Object> map = new HashMap<String, Object>();
         map.put("test_username",username);
         map.put("test_family",family);
         map.put("test_password",password);
         map.put("test_email",email);
         map.put("test_tel",tel);
         db.insert("test",map);
                List<Map<String, Object>> rows =jjDatabaseWeb.separateRow(dtm);
                   for (int i = 0; i < rows.size(); i++) {
                %>
                <%="<tr><td class='data-cell'>" + rows.get(i).get("test_username") + "</td>"
                        + "<td class='data-cell'>" +rows.get(i).get("id") +"</td>"
                        + "<td class='data-cell'><a href='select.jsp?id=" + rows.get(i).get("id")+ "'>ویرایش</a></td>"
                        + "</tr>"%>
                <%
                    }
                %>
            </tbody>
        </table>
                                                </div>
    </body>
</html>
