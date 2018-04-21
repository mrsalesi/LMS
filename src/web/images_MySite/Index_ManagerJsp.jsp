<%-- 
    Document   : Index_ManagerJsp
    Created on : Oct 17, 2017, 11:33:18 AM
    Author     : shohreh&shahla
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.HashMap"%>
<%@page import="jj.jjDatabaseWeb"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->
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
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap-theme.min.css">
        <link href="css/font.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/fontAwesome.css">
        <link rel="stylesheet" href="css/templatemo-style.css">
        <script src="../../002/web/js/jj2.js" type="text/javascript"></script>
        <script src="../../002/web/js/index.js" type="text/javascript"></script>
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
                        <li class="selected"><a href="#0"><div class="image-icon"><i class="fa fa-home" style="transform: scale(4)"></i></div><h6>Welcome</h6></a></li>
                        <li><a href="#0"><div class="image-icon"><i class="fa fa-lock" style="transform: scale(3.5)"></i></div><h6>Users</h6></a></li>
                        <li><a href="#0"><div class="image-icon"><i class="fa fa-lock" style="transform: scale(3.5)"></i></div><h6>Edit</h6></a></li>
                        <li><a href="#0"><div class="image-icon"><i class="fa fa-lock" style="transform: scale(3.5)"></i></div><h6>Delete</h6></a></li>
                        <li><a href="#0"><div class="image-icon"><i class="fa fa-lock" style="transform: scale(3.5)"></i></div><h6>Delete</h6></a></li>
                    </ul>
                </nav> 

            </div> <!-- .cd-slider-nav -->

            <ul class="cd-hero-slider">

                <li class="selected">
                    <div class="heading">
                        <h1>NEWLINE</h1>
                        <span>Welcome To Page Manege</span>
                    </div>
                    <div class="cd-full-width first-slide">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="content first-content">
                                        <h4>Let’s Talk More About Newline</h4>
                                        <p>Newline is free CSS template by templatemo. Credits go to <a href="https://videos.pexels.com/videos/busy-street-in-the-city-1089" target="_blank">Pexels.com</a> for a video background and <a href="http://unsplash.com" target="_blank">Unsplash</a> for images. Please tell your friends about templatemo and this is a way to help back. Thank you. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque dictum convallis mi. In dapibus auctor dictum donec mattis quis eros ultricies feugiat.</p>
                                        <div class="primary-button">
                                            <a href="#">Discover More</a>
                                        </div>
                                    </div>
                                </div>
                            </div>                  
                        </div>
                    </div>
                </li>

                <li>
                    <div class="heading">
                        <h1>USERS</h1>
                        <span>Get More Info About Our Agency</span> 
                    </div>
                    <div class="cd-half-width second-slide">   
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="content second-content">
                                        <div class="row">
                                            <%jjDatabaseWeb db = new jjDatabaseWeb("root", "m123456", "db_test1", "localhost", "3306");
                                                db.ConnectCustom();
                                                DefaultTableModel dtm;
                                                Map<String, Object> map = new HashMap<String, Object>();
                                                String d0=request.getParameter("do");
                                            String username = request.getParameter("username_R");
                                                String password = request.getParameter("password_R");
                                                String family = request.getParameter("family_R");
                                                String email = request.getParameter("email_R");
                                                String tel = request.getParameter("tel_R");
                                            %>
                                            <%
//                                                Enumeration<String> parameterNames = request.getParameterNames();
//                                                System.out.println("-----------ShowAllParameter-----------");
//                                                while (parameterNames.hasMoreElements()) {
//                                                    String key = parameterNames.nextElement();
//                                                    System.out.println(key + "=" + request.getParameter(key));
//                                                }
//                                                System.out.println("--------------_________---------------");
//                                               

                                                if (d0.equals("m_insert")){
                                                
                                                map.put("test_username", username);
                                                map.put("test_family", family);
                                                map.put("test_password", password);
                                                map.put("test_email", email);
                                                map.put("test_tel", tel);
                                                db.insert("test", map);
                                                }
                                            %>
                                            

                                        </div>
                                        </li>
                                        <li>
                                            <div class="heading">
                                                <h1>Sign-in</h1>
                                                <span>Here you can check our recent projects</span> 
                                            </div>
                                            <div class="cd-half-width third-slide">
                                                <div class="container">
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="content third-content">
                                                                <div class="row">
                                                                    
                                                                        <thead>
                                                                            <tr>
                                                                                <td class="header-cell">نام کاربری</td>
                                                                                <td class="header-cell">ردیف</td>

                                                                            </tr>
                                                                        </thead>
                                                                        

                                                                            <%
                                                                                List<Map<String,Object>> rows = jjDatabaseWeb .separateRow (dtm);
                                                                                for (int i = 0; i < rows.size(); i++) {
                                                                            %>
                                                                            <%="<tr><td class='data-cell'>" + rows.get(i).get("test_username") + "</td>"
                                                                                    + "<td class='data-cell'>" + rows.get(i).get("id") + "</td>"
                                                                                    + "<td class='data-cell'><a href='Index_ManagerJsp.jsp?id=" + rows.get(i).get("id") + "'>ویرایش</a></td>"
                                                                                    + "</tr>"%>
                                                                            <%
                                                                                }
                                                                            %>
                                                                        
                                                                    
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>                  
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="heading">
                                                <h1>Sign-in</h1>
                                                <span>Here you can check our recent projects</span> 
                                            </div>
                                            <div class="cd-half-width fourth-slide">
                                                <div class="container">
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="content fourth-content">
                                                                <div class="row">

                                                                    <%      String userId = request.getParameter("id");
                                                                     dtm = db.Select("test", "id=" + userId);
                                                                        if (rows.size() > 0) {
                                                                    %>
                                                                    <div style="width: 100% ;
                                                                         height: auto">  
                                                                        نام کاربری :<input  name='username' value="<%=rows.get(0).get("test_username")%>"/>
                                                                        <br/>
                                                                        پسورد: <input   style="margin-left: 20px;" name='password' value="<%=rows.get(0).get("test_password")%>"/>
                                                                        <br/>
                                                                        ایمیل: <input  style="margin-left: 28px;"name='email' value="<%=rows.get(0).get("test_email")%>"/>
                                                                        <br/>
                                                                        تلفن: <input  style="margin-left: 33px;" name='tel' value="<%=rows.get(0).get("test_tel")%>"/>
                                                                        <br/>
                                                                    </div>
                                                                    <input  type="button" onclick="window.location ='Index_ManagerJsp.jsp?id=<%=userId%>'" value='حذف' class="primary-button" 
                                                                            style="margin-left:58px;margin-top:-3px;font-family:IRANIANSANS"/>

                                                                    <%
                                                                        }
                                                                    %>

                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>                  
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="heading">
                                                <h1>welcome</h1>
                                                <span>Here you can check our recent projects</span> 
                                            </div>
                                            <div class="cd-half-width first-slide">
                                                <div class="container">
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="content first-content">
                                                                <div class="row">
                                          
                                                    </div>                  
                                                </div>
                                            </div>
                                                </div>
                                                </div>
                                        </li>
                                        </ul> <!-- .cd-hero-slider -->
                                        </section> <!-- .cd-hero -->


                                        <footer>
                                            <p>Copyright &copy; 2017 Your Company 

                                                | Designed by <a href="http://www.templatemo.com" target="_parent"><em>templatemo</em></a></p>
                                        </footer>

                                        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
                                        <script>window.jQuery || document.write('<script src="js/vendor/jquery-1.11.2.min.js"><\/script>');
    <script src="js/vendor/bootstrap.min.js"></script>
                                        <script src="js/plugins.js"></script>
                                        <script src="js/main.js"></script>
                                        <script>
                                          
    <script>
                                        </body>
                                        </html>