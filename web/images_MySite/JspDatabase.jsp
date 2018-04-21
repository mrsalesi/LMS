<%-- 
    Document   : JspDatabase
    Created on : Oct 9, 2017, 9:25:49 AM
    Author     : shohreh&shahla
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="jj.jjDatabaseWeb"%>
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
                        <li class="selected"><a href="#0"><div class="image-icon"><i class="fa fa-home" style="transform: scale(4)"></i></div><h6>Welcome</h6></a></li>
                        <li><a href="#0"><div class="image-icon"><i class="fa fa-phone" style="transform: scale(3.5);"></i></div><h6>Cantact Us</h6></a></li>
                        <li><a href="#0"><div class="image-icon"><i class="fa fa-photo" style="transform: scale(3.5)"></i></div><h6>Photo</h6></a></li>
                        <li><a href="#0"><div class="image-icon"><i class="fa fa-lock" style="transform: scale(3.5)"></i></div><h6>sign-in</h6></a></li>
                        <li><a href="#0"><div class="image-icon"><i class="fa fa-sign-in" style="transform: scale(3.5)"></i></div><h6>login</h6></a></li>
                        <li><a href="#0"><div class="image-icon"><i class="fa fa-sign-in" style="transform: scale(3.5)"></i></div><h6>Database</h6></a></li>
                    </ul>
                </nav> 

            </div> <!-- .cd-slider-nav -->

            <ul class="cd-hero-slider">

                <li class="selected">
                    <div class="heading">
                        <h1>NEWLINE</h1>
                        <span>Welcome To Web Design Agency</span>
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
                        <h1>Contact Us</h1>
                        <span>Get More Info About Our Agency</span> 
                    </div>
                    <div class="cd-half-width second-slide">   
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="content second-content">
                                        <div class="row">
                                            <div class="col-md-7 left-image">
                                                <img src="img/left-about-image.jpg">
                                            </div>
                                            <div class="col-md-5">
                                                <div class="right-about-text">
                                                    <h4>Who we are?</h4>
                                                    <p>Fusce neque leo, dapibus sed imperdiet sed, vulputate sed purus. Nam eget justo in nibh facilisis rhoncus. Donec et risus non mauris lobortis convallis. Aliquam id urna quis ante blandit semper.</p>
                                                    <div class="primary-button">
                                                        <a href="#">Discover More</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-7">
                                                <div class="left-about-text">
                                                    <h4>What we do?</h4>
                                                    <p>Nam aliquam ultrices interdum. Vivamus metus mi, accumsan a tincidunt a, efficitur id felis. Vivamus non nibh malesuada, vestibulum nulla in, iaculis sem. Aenean tincidunt faucibus ipsum, ac aliquet nunc accumsan sed. Nulla sodales nunc sit amet libero egestas, ut interdum ex congue.</p>
                                                    <div class="primary-button">
                                                        <a href="#">Discover More</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-5 right-image">
                                                <img src="img/right-about-image.jpg">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>                  
                        </div>
                    </div>
                </li>

                <li>
                    <div class="heading">
                        <h1>Picture</h1>
                        <span>We produce product every week</span> 
                    </div>
                    <div class="cd-half-width third-slide">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="content third-content">
                                        <div class="row">
                                            <div class="col-md-7 left-image">
                                                <img src="img/left-feature-image.png">
                                            </div>
                                            <div class="col-md-5">
                                                <div class="right-feature-text">
                                                    <h4>Featured Product: <em>Newline</em></h4>
                                                    <p>Lorem ipsum dolor amet, consecte adipiscing elit, quisque dictum convallis mi. In dapibus auctor dictum donec mattis quis eros ultricies feugiat. Morbi congue faucibus mi, ague blandit curabitur ac lacinia.</p>
                                                    <div class="feature-list">
                                                        <ul>
                                                            <p>- Suspendisse mattis finibus sem</p>
                                                            <p>- Pellentesque et urna vel lectus</p>
                                                            <p>- Vestibulum iaculis nisi dui</p>
                                                            <p>- Donec sagittis eros , ac tempus ligula</p>
                                                            <p>- Integer sapien risus, auctor</p>
                                                        </ul>
                                                    </div>
                                                    <div class="primary-button">
                                                        <a href="#">Discover More</a>
                                                    </div>
                                                </div>
                                            </div>
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
                                            <div class="col-md-3 project-item">
                                                <a href="img/item-01.jpg" data-lightbox="image-1"><img src="img/project-item-01.jpg"></a>
                                            </div>
                                            <div class="col-md-3 project-item">
                                                <a href="img/item-02.jpg" data-lightbox="image-1"><img src="img/project-item-02.jpg"></a>
                                            </div>
                                            <div class="col-md-3 project-item">
                                                <a href="img/item-03.jpg" data-lightbox="image-1"><img src="img/project-item-03.jpg"></a>
                                            </div>
                                            <div class="col-md-3 project-item">
                                                <a href="img/item-04.jpg" data-lightbox="image-1"><img src="img/project-item-04.jpg"></a>
                                            </div>
                                            <div class="col-md-3 project-item">
                                                <a href="img/item-05.jpg" data-lightbox="image-1"><img src="img/project-item-05.jpg"></a>
                                            </div>
                                            <div class="col-md-3 project-item">
                                                <a href="img/item-06.jpg" data-lightbox="image-1"><img src="img/project-item-06.jpg"></a>
                                            </div>
                                            <div class="col-md-3 project-item">
                                                <a href="img/item-07.jpg" data-lightbox="image-1"><img src="img/project-item-07.jpg"></a>
                                            </div>
                                            <div class="col-md-3 project-item">
                                                <a href="img/item-08.jpg" data-lightbox="image-1"><img src="img/project-item-08.jpg"></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>                  
                        </div>
                    </div>
                </li>

                <li>
                    <div class="heading">
                        <h1>Login</h1>
                        <span>You'll be responded within 48 hrs</span> 
                    </div>
                    <div class="cd-half-width fivth-slide">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="content fivth-content">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="left-info">
                                                    <p>Maecenas imperdiet sagittis lacus, ut consequat velit iaculis id. Praesent eu consequat urna. Morbi justo dolor, ornare sed lorem et, auctor iaculis ligula.
                                                        <br><br>
                                                        <em>3344 Donec mollis libero<br>at metus luctus 10110</em>
                                                    </p>
                                                    <ul class="social-icons">
                                                        <i><a href="#"><i class="fa fa-facebook"></i></a></i>
                                                        <i><a href="#"><i class="fa fa-twitter"></i></a></i>
                                                        <i><a href="#"><i class="fa fa-linkedin"></i></a></i>
                                                        <i><a href="#"><i class="fa fa-rss"></i></a></i>
                                                        <i><a href="#"><i class="fa fa-behance"></i></a></i>
                                                    </ul>
                                                </div>
                                            </div>
                                            <div class="col-md-8">
                                                <div class="row">
                                                    <form id="contact" action="" method="post">
                                                        <div class="col-md-6">
                                                            <fieldset>
                                                                <input name="username_r" type="text" class="form-control" id="username_register" placeholder="Your Name" required="">
                                                            </fieldset>
                                                            <fieldset>
                                                                <input name="family_r" type="text" class="form-control" id="family_register" placeholder="Your Family" required="">
                                                            </fieldset>
                                                            <fieldset>
                                                                <input name="password_r" type="text" class="form-control" id="password_register" placeholder="Your Pass" required="">
                                                            </fieldset>
                                                            <fieldset>
                                                                <input name="repeatpass_r" type="text" class="form-control" id="repeatpass_register" placeholder="Repeat Pass" required="">
                                                            </fieldset>
                                                            <fieldset>
                                                                <input name="email_r" type="text" class="form-control" id="email_register" placeholder="Your Email" required="">
                                                            </fieldset>
                                                            <fieldset>
                                                                <input name="tel_r" type="text" class="form-control" id="tel_register" placeholder="Your Phone" required="">
                                                            </fieldset>
                                                            <div class="primary-button" id="register">
                                                                <a href="#">register</a>
                                                            </div> 
                                                            <div class="primary-button" id="baresi" style="margin-left:150px;margin-top:-44px;">
                                                                <a href="#">Discover More</a>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>                  
                            </div>
                        </div>
                </li>
                <!--                /---------database-table----------/
                                /--------------------------------/-->
                <li>
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
                                                    <table >
                                                        <tbody>                                 
                                                            <tr>
                                                                <td class="header-cell">نام کاربری</td>
                                                                <td class="header-cell">ردیف</td>
                                                            </tr>                                                            
                                                        </tbody>
                                                        <%
                                                            jjDatabaseWeb db = new jjDatabaseWeb("root", "m123456", "db_test1", "localhost", "3306");
                                                            db.ConnectCustom();
                                                            DefaultTableModel dtm = db.Select("test");
                                                            List<Map<String, Object>> rows = jjDatabaseWeb.separateRow(dtm);
                                                            for (int i = 0; i < rows.size(); i++) {
                                                        %>
                                                        <%="<tr><td class='data-cell'>" + rows.get(i).get("test_username") + "</td>"
                                                                + "<td class='data-cell'>" + rows.get(i).get("id") + "</td>"
                                                                + "<td class='data-cell'><a href='select.jsp?id=" + rows.get(i).get("id") + "'>ویرایش</a></td>"
                                                                + "</tr>"%>
                                                        <%
                                                            }
                                                        %>
                                                    </table>



                                                </div>
                                            </div>

                                        </div>

                                    </div>
                                </div>
                            </div>                  
                        </div>
                    </div>
                </li>

            </ul> <!-- .cd-hero-slider 
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
