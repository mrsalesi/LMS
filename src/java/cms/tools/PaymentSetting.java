/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.tools;

import cms.access.Access_User;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import jj.jjDatabase;
import jj.jjDatabaseWeb;
import jj.jjNumber;

/**
 *
 * @author Rashidi
 */
public class PaymentSetting {

    public static String tableName = "payment_setting";
    public static String _id = "id";
    public static String _terminalId = "payment_setting_terminal_id";
    public static String _userName = "payment_setting_userName";
    public static String _pass = "payment_setting_pass";
    public static String _webService = "payment_setting_webService";
    public static String _bankName = "payment_setting_name";
    public static int rul_rfs = 185;
    public static int rul_ins ;
    public static int rul_edt ;
    public static int rul_dlt ;

    ///---------------------------- refresh ------------------>
    ////// ------------- refresh() ------------->  
    public static String refresh(HttpServletRequest request, jjDatabaseWeb db, boolean isPost){
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            StringBuffer html = new StringBuffer();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName));
            html.append("<table id='refreshPaymentSetting' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='10%'>شماره ترمینال</th>");
            html.append("<th width='10%'>نام کاربری</th>");
            html.append("<th width='15%'>گذرواژه</th>");
            html.append("<th width='10%'>نام بانک</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr class='mousePointer'>");
                html.append("<td class='c'>" + row.get(i).get(_id).toString() + "</td>");
                html.append("<td class='l'>" + row.get(i).get(_terminalId).toString() + "</td>");
                html.append("<td class='c'>" + row.get(i).get(_userName).toString() + "</td>");
                html.append("<td class='c'>" + row.get(i).get(_pass).toString() +"</td>");
                html.append("<td class='c'>" + row.get(i).get(_bankName).toString() +"</td>");
                html.append("</tr>");
            }
            html.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swProduct10Tbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshPaymentSetting", height, 0, "", "لیست درگاه بانک ها");
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
    ////// <------------- refresh() -------------  

}
