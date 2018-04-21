<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="jj.jjDatabaseWeb"%>
<%@page import="jj.jjDatabaseWeb"%>
<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                        
                        <li><a href="#"><div class="image-icon"><i class="fa fa-sign-in" style="transform: scale(3.5)"></i></div><h6>Database</h6></a></li>
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
                                                    <form id="contact" action="" method="post">

                                                        <table >
                                                            <tbody>
                                                            <thead>
                                                                <tr>
                                                                    <td  class="header-cell">نام کاربری</td>
                                                                    <td class="header-cell">ردیف</td>
                                                                </tr>
                                                            </thead>
                                                            </tbody>
                                                            <%
         jjDatabaseWeb db = new jjDatabaseWeb("root", "m123456", "db_test1","localhost","3306");
        db.ConnectCustom();
        DefaultTableModel dtm = db.Select("test");
            List<Map<String,Object>> rows = jjDatabaseWeb.separateRow(dtm);
        for (int i = 0; i < rows.size(); i++) {
                %>
                <%="<tr><td class='data-cell'>" + rows.get(i).get("test_username") + "</td>"
                        + "<td class='data-cell'>" +rows.get(i).get("id") +"</td>"
                        + "<td class='data-cell'><a href='select.jsp?id=" + rows.get(i).get("id")+ "'>ویرایش</a></td>"
                        + "</tr>"%>
                <%
                    }
                %>
                                                        </table>
                                                    </form>


                                                </div>
                                            </div>

                                        </div>

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
        <script>window.jQuery || document.write('<script src="js/vendor/jquery-1.11.2.min.js"><\/script>')</script>

        <script src="js/vendor/bootstrap.min.js"></script>
        <script src="js/plugins.js"></script>
        <script src="js/main.js"></script>

    </body>
</html>