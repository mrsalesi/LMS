<%-- 
    Document   : mellatPaymentResult
    Created on : Jul 17, 2016, 9:01:41 AM
    Author     : Rashidi
--%>

<%@page import="cms.tools.Payment"%>
<%@page import="jj.jjDatabaseWeb"%>
<%@page import="cms.tools.Server"%>
<%@page import="behPardakhtMellat.bpmPayment"%>
<%@page import="java.io.Reader"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.DataOutputStream"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="cms.tools.ServerLog"%>
<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link href="images_komeilipoor/css/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-ui-1.9.2.custom.min.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.cookie.min.js"></script>
    <script type="text/javascript" src="js/jj2.js"></script>
    <script type="text/javascript" src="js/index.js"></script>
    <%

        ///////----------- getParameters from bank ---->
        Enumeration<String> parameterNames = request.getParameterNames();
        if (request.getParameter("ResCode").equals("") || request.getParameter("SaleOrderId").equals("") || request.getParameter("SaleReferenceId").equals("")) {
            ServerLog.Print("NULL REQUEST");
    %>
    <div class="paymentResult">
        <div>
            <h3>
                انصراف از تراکنش
            </h3>
            <div style="margin-top:5%;">
                <a href="index.jsp">
                    بازگشت به صفحه اصلی سایت
                </a>
            </div>
        </div>
    </div>
    <%
    } else {
        StringBuilder outStr = new StringBuilder();
        jjDatabaseWeb db;
        Server.Connect();
        db = Server.db;
        outStr.append("--------------------------------------\n");
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            outStr.append(key + "=" + request.getParameter(key));
            //                urlParameters = key + "=" + request.getParameter(key) + "&";
        }
        outStr.append("\n--------------------------------------\n");
        String resCode = request.getParameter("ResCode");
        //        long resCode = Long.parseLong(request.getParameter("ResCode"));
        long saleOrderId = Long.parseLong(request.getParameter("SaleOrderId"));
        long saleReferenceId = Long.parseLong(request.getParameter("SaleReferenceId"));

        ///////<----------- getParameters from bank ----
        ServerLog.Print("pay rescode is :::  " + resCode);

        if (resCode.equals("0")) {

            ///////----------- verify method ---->
            String urlParameters = "TerminalId=" + bpmPayment.terminalId + "&UserName=" + bpmPayment.userName + "&UserPassword=" + bpmPayment.userPassword + "&VerifyOrderId=" + bpmPayment.orderId + "&VerifySaleOrderId=" + saleOrderId + "&VerifySaleReferenceId=" + saleReferenceId;
            ServerLog.Print("$$$$ urlParameter:  method: verify ::: " + urlParameters);

            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
            int postDataLength = postData.length;
            //            String req = "http://dfbyjh.tk/?method=verify";/////
            String req = "http://sepanoshop.ir/?method=verify";
            URL url = new URL(req);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            conn.setUseCaches(false);
            conn.getOutputStream().write(postData);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.write(postData);
            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();

            for (int c; (c = in.read()) >= 0;) {
                sb.append((char) c);
            }
            ///////<----------- verify method ----

            ServerLog.Print("verify rescode is :::  " + sb.toString().replace("<html>", "").replace("</html>", "").replace("\n", ""));
            resCode = sb.toString().replace("<html>", "").replace("</html>", "").replace("\n", "");

            if (resCode.replace(" ", "").equals("0")) {
    %>
    <script>
        //پاک کردن کالاها از سبد خرید کوکی
        new jj('productsId').jjCookieDelete();
        new jj('productNums').jjCookieDelete();
        $("#productNums").html(0);
    </script>
    <%
                ///////----------- settle method ---->
                urlParameters = "TerminalId=" + bpmPayment.terminalId + "&UserName=" + bpmPayment.userName + "&UserPassword=" + bpmPayment.userPassword + "&SettleOrderId=" + bpmPayment.orderId + "&SettleSaleOrderId=" + saleOrderId + "&SettleSaleReferenceId=" + saleReferenceId;
                ServerLog.Print("$$$$ urlParameter:  method: settle ::: " + urlParameters);

                byte[] postData2 = urlParameters.getBytes(StandardCharsets.UTF_8);
                int postDataLength2 = postData2.length;
                req = "http://sepanoshop.ir/?method=settle";
                //            req = "http://dfbyjh.tk/?method=settle";/////
                URL url2 = new URL(req);
                HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
                conn2.setDoOutput(true);
                conn2.setInstanceFollowRedirects(false);
                conn2.setRequestMethod("POST");
                conn2.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn2.setRequestProperty("charset", "utf-8");
                conn2.setRequestProperty("Content-Length", Integer.toString(postDataLength2));
                conn2.setUseCaches(false);
                conn2.getOutputStream().write(postData2);
                DataOutputStream wr2 = new DataOutputStream(conn2.getOutputStream());
                wr2.write(postData2);
                Reader in2 = new BufferedReader(new InputStreamReader(conn2.getInputStream(), "UTF-8"));
                StringBuilder sb2 = new StringBuilder();

                for (int c; (c = in2.read()) >= 0;) {
                    sb2.append((char) c);
                }
                ServerLog.Print("rescode settle is :::  " + sb2.toString().replace("<html>", "").replace("</html>", ""));
                resCode = sb2.toString().replace("<html>", "").replace("</html>", "").replace("\n", "").replace(" ", "");

            }
            ///////<-----------  settle method ----
            request.setAttribute(Payment._status, bpmPayment.checkStatus(Integer.parseInt(resCode)));//اطلاعاتی از قبیل وضعیت و شماره مرجع تراکنش را در ریکوئست میگذاره
            request.setAttribute(Payment._amount, bpmPayment.amount);
            request.setAttribute(Payment._orderId, bpmPayment.orderId);
            ///////----------- changr factor status ---->
            bpmPayment.refreshFactorStatus(request, db, true);
            ///////<----------- changr factor status  ----
        }

        ///////-----------  insert transaction in db ---->
        bpmPayment.insert(request, db, true);
//        bpmPayment.insert(resCode, saleReferenceId, request, db, true);
        ///////<-----------  insert transaction in db ----
    %>
    <body>
        <div class="paymentResult">
            <div>
                <table class="shoppingCart">
                    <thead>
                        <tr>
                            <td>
                                عنوان
                            </td>
                            <td>
                                مقدار
                            </td>
                        </tr>
                    </thead>
                    <tr>
                        <td>
                            نتیجه تراکنش 
                        </td>
                        <td>
                            <%=bpmPayment.checkStatus(Integer.parseInt(resCode))%>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            مبلغ پرداختی
                        </td>
                        <td>
                            <%=bpmPayment.amount%>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            شماره مرجع 
                        </td>
                        <td>
                            <%=request.getParameter("SaleReferenceId")%>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            شماره سفارش 
                        </td>
                        <td>
                            <%=request.getParameter("SaleOrderId")%>
                        </td>
                    </tr>
                </table>
            </div>
            <div style="margin-top:5%;">
                <a href="index.jsp">
                    بازگشت به صفحه اصلی سایت
                </a>
            </div>
        </div>
        <%
            }
        %>

    </body>
</html>
