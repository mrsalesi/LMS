package cms.cms;

import cms.tools.*;
import cms.access.*;
import java.io.File;
import java.util.ArrayList;
import jj.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.swing.table.DefaultTableModel;

public class Content {

    public static String tableName = "content";
    public static String _id = "id";
    public static String _title = "content_title";
    public static String _content = "content_content";
    public static String _parent = "content_parent";
    public static String _lang = "content_lang";
    public static String _hasLink = "content_has_link";//====== BY RASHIDI ======برای این محتوا لینک در محتوا ساخته شود یا خیر
    public static String _tags = "content_tags";//====== BY RASHIDI ======
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";
    public static String lbl_add_en = "افزودن زبان انگلیسی";
    public static String lbl_edit_en = "ویرایش بخش انگلیسی";
    public static String lbl_add_ar = "افزودن زبان عربی";
    public static String lbl_edit_ar = "ویرایش بخش عربی";
//    public static int rul_rfs = 1;
//    public static int rul_ins = 2;
//    public static int rul_edt = 3;
//    public static int rul_dlt = 4;
//    public static int rul_lng = 5;

    public static int rul_rfs = 21;
    public static int rul_ins = 22;
    public static int rul_edt = 23;
    public static int rul_dlt = 24;
    public static int rul_lng2 = 25;//====== BY RASHIDI ======
    public static int rul_lng3 = 26;//====== BY RASHIDI ======
    public static int rul_lng4 = 27;//====== BY RASHIDI ======
    public static int rul_lng5 = 28;//====== BY RASHIDI ======
//    public static int rul_reserved = 29 --- 40;// RESERVED : 29 -- 40
    ///**************LANGUAAGE ADDED*******>
//    public static int rul_lng2 = 25;//====== BY RASHIDI ======
//    public static int rul_lng3 = 26;//====== BY RASHIDI ======
//    public static int rul_lng4 = 27;//====== BY RASHIDI ======
//    public static int rul_lng5 = 28;//====== BY RASHIDI ======
    public static String lbl_add_ln2 = "افزودن زبان انگلیسی";//====== BY RASHIDI ======
    public static String lbl_edit_ln2 = "ویرایش بخش انگلیسی";//====== BY RASHIDI ======
    public static String lbl_add_ln3 = "افزودن زبان عربی";//====== BY RASHIDI ======
    public static String lbl_edit_ln3 = "ویرایش بخش عربی";//====== BY RASHIDI ======
    public static String lbl_add_ln4 = "افزودن زبان آلمانی";//====== BY RASHIDI ======
    public static String lbl_edit_ln4 = "ویرایش بخش آلمانی";//====== BY RASHIDI ======
    public static String lbl_add_ln5 = "افزودن زبان چینی";//====== BY RASHIDI ======
    public static String lbl_edit_ln5 = "ویرایش بخش چینی";//====== BY RASHIDI ======
    ///<**************LANGUAAGE ADDED*******

    public static String refresh(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            StringBuilder html = new StringBuilder();
            DefaultTableModel dtm = db.Select(tableName, _id + "," + _title, _parent + "=0 AND id>10");
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            html.append("<table id='refreshContent' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='90%'>عنوان</th>");
            html.append("<th width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
//                html.append("<tr onclick='$(this).html().print();' class='mousePointer'>");
//                html.append("<tr onclick='alert($(this).children(1).html());' class='mousePointer'>");
                html.append("<tr onclick='cmsContent.m_select(" + row.get(i).get(_id) + ");' class='mousePointer'>");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_title).toString()) + "</td>");
                html.append("<td class='c'><img src='img/l.png' style='height:30px'/></td>");
                html.append("</tr>");
            }
            html.append("</tbody></tabl"
                    + "e>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swContentTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshContent", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "1" : "", "لیست محتویات سایت");
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

//    public static String getParent(List<Map<String, Object>> rows, String id) {
//        try {
//            for (int i = 0; i < rows.size(); i++) {
//                if (rows.get(i).get(_parent).toString().equals(id)) {
//                    return rows.get(i).get(_id).toString();
//                }
//            }
//            return "";
//        } catch (Exception ex) {
//            return Server.ErrorHandler(ex);
//        }
//    }
    public static String add_new(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#Content_button", "<input type=\"button\" id=\"insert_content_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">"));
                html.append(Js.buttonMouseClick("#insert_content_new", Js.jjContent.insert()));
            }
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String insert(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            catchContentTitle = null;
            String hasAccess = Access_User.getAccessDialog(request, db, rul_ins);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            Map<String, Object> map = new HashMap<String, Object>();

            map.put(_content, jjTools.getParameter(request, _content));
            map.put(_title, jjTools.getParameter(request, _title).trim());

            String parent = jjTools.getParameter(request, _parent);
            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);
            String lang = jjTools.getParameter(request, _lang);
            map.put(_lang, jjNumber.isDigit(lang) ? Integer.parseInt(lang) : 1);
            map.put(_tags, jjTools.getParameter(request, _tags));//============ BY RASHIDI ========

            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjContent.refresh();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String edit(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            catchContentTitle = null;
            String hasAccess = Access_User.getAccessDialog(request, db, rul_edt);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }

            Map<String, Object> map = new HashMap<String, Object>();

            String parent = jjTools.getParameter(request, _parent);
            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);
            String lang = jjTools.getParameter(request, _lang);
            map.put(_lang, jjNumber.isDigit(lang) ? Integer.parseInt(lang) : 1);

            map.put(_content, jjTools.getParameter(request, _content));
            map.put(_title, jjTools.getParameter(request, _title).trim());
            map.put(_hasLink, jjTools.getParameter(request, _hasLink));//============ BY RASHIDI ========
            map.put(_tags, jjTools.getParameter(request, _tags));//============ BY RASHIDI ========

            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            if (!db.update(tableName, map, _id + "=" + jjTools.getParameter(request, _id))) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjContent.refresh();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String delete(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            catchContentTitle = null;
            String hasAccess = Access_User.getAccessDialog(request, db, rul_dlt);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }

            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            if (!row.isEmpty()) {
                String toString = row.get(0).get(_content).toString();
                //
                File folderAddress = new File(request.getServletContext().getRealPath("/upload"));
                String[] list = new File(request.getServletContext().getRealPath("/upload")).list();

//                for (int i = 0; i < list.length; i++) {
//                    if (toString.indexOf("src=\"upload/" + list[i] + "\"") > -1) {
//                        File pics = new File(folderAddress.getAbsolutePath() + "/" + list[i]);
//                        if (pics.exists()) {
//                            pics.delete();
//                        }
//                        File pics_small = new File(folderAddress.getAbsolutePath() + "/" + list[i].replace(".", "_small."));
//                        if (pics_small.exists()) {
//                            pics_small.delete();
//                        }
//                    }
//                }
            }
            if (!db.delete(tableName, _id + "=" + id + " OR " + _parent + " = " + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                return Js.dialog(errorMessage);
            }
            db.delete(tableName, _parent + "=" + id);
            return Js.jjContent.refresh();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String select(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            if (row.isEmpty()) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            Object title = row.get(0).get(_title);
            if (title != null) {
                html.append(Js.setVal("#" + _title, title));
            }
            html.append(Js.setVal("#" + _parent, row.get(0).get(_parent)));
            html.append(Js.setVal("#" + _lang, row.get(0).get(_lang)));
            html.append(Js.setVal("#" + _hasLink, row.get(0).get(_hasLink)));//============ BY RASHIDI ========
            //============ BY RASHIDI ========>
            String tagsHtml = "";
            String tagsString = "";
            if (!row.get(0).get(_tags).toString().equals("")) {
                tagsString = row.get(0).get(_tags).toString();
                String[] tags = tagsString.split(",");
                for (int i = 0; i < tags.length; i++) {
                    System.out.println(i + " " + tags[i]);
                    tagsHtml += "<span id='contetn_tag_span" + i + "' calss='tags' onclick='deleteContentTag(" + i + ");'>" + tags[i] + ",</span> ";
                }
            }
            html.append(Js.setVal("#" + _tags, tagsString));
            html.append(Js.setHtml("#content_tags_div", tagsHtml));
            //<============ BY RASHIDI ========
            Object content = row.get(0).get(_content);
            if (content != null) {
                html.append(Js.setValEditor(_content, content));
            }
            boolean accDel = Access_User.hasAccess2(request, db, rul_dlt);
            boolean accEdt = Access_User.hasAccess2(request, db, rul_edt);
//            boolean acclng = Access_User.hasAccess2(request, db, rul_lng);

            if (accEdt) {
                html2.append("<input type='button' id='edit_content' value='" + lbl_edit + "' class='tahoma10'>");
                html.append(Js.buttonMouseClick("#edit_content", Js.jjContent.edit()));
            }
            if (accDel && !row.get(0).get(_parent).toString().equals("1")) {
                html2.append("<input type='button' id='delete_content' value='" + lbl_delete + "' class='tahoma10'  />");
                html.append(Js.buttonMouseClick("#delete_content", Js.jjContent.delete(id)));
            }
            //============ BY RASHIDI ========>
//            if (acclng) {
//                List<Map<String, Object>> rowEn = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + id + " AND " + _lang + "=2"));
//                if (rowEn.size() > 0) {
//                    html2.append("<input type='button' id='edit_en_content' value='" + lbl_edit_en + "' class='tahoma10'  />");
//                    html.append(Js.buttonMouseClick("#edit_en_content", Js.jjContent.select(rowEn.get(0).get(_id).toString())));
//                } else {
//                    if (row.get(0).get(_parent).equals("0")) {
//                        html2.append("<input type='button' id='add_en_content' value='" + lbl_add_en + "' class='tahoma10' />");
//                        html.append(Js.buttonMouseClick("#add_en_content", Js.jjContent.addEN(id)));
//                    }
//                }
//                List<Map<String, Object>> rowAr = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + id + " AND " + _lang + "=3"));
//                if (rowAr.size() > 0) {
//                    html2.append("<input type='button' id='edit_ar_content' value='" + lbl_edit_ar + "' class='tahoma10'  />");
//                    html.append(Js.buttonMouseClick("#edit_ar_content", Js.jjContent.select(rowAr.get(0).get(_id).toString())));
//                } else {
//                    if (row.get(0).get(_parent).equals("0")) {
//                        html2.append("<input type='button' id='add_ar_content' value='" + lbl_add_ar + "' class='tahoma10' />");
//                        html.append(Js.buttonMouseClick("#add_ar_content", Js.jjContent.addAr(id)));
//                    }
//                }
//            }
            ///**************LANGUAAGE ADDED*******>
//            boolean acclng = Access_User.hasAccess2(request, db, rul_lng);//====== COMMENTED BY RASHIDI ======
            boolean acclng2 = Access_User.hasAccess2(request, db, rul_lng2);//====== BY RASHIDI ======
            boolean acclng3 = Access_User.hasAccess2(request, db, rul_lng3);//====== BY RASHIDI ======
            boolean acclng4 = Access_User.hasAccess2(request, db, rul_lng4);//====== BY RASHIDI ======
            boolean acclng5 = Access_User.hasAccess2(request, db, rul_lng5);//====== BY RASHIDI ======
            if (acclng2) {
                List<Map<String, Object>> row2 = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + id + " AND " + _lang + "=2"));
                if (row2.size() > 0) {
                    html3.append("<input type='button' id='edit_ln2_content' value='" + lbl_edit_ln2 + "' class='tahoma10'  />");
                    html.append(Js.buttonMouseClick("#edit_ln2_content", Js.jjContent.select(row2.get(0).get(_id).toString())));
                } else {
                    if (row.get(0).get(_parent).equals("0")) {
                        html3.append("<input type='button' id='add_ln2_content' value='" + lbl_add_ln2 + "' class='tahoma10' />");
                        html.append(Js.buttonMouseClick("#add_ln2_content", Js.jjContent.addLang(Integer.parseInt(id), 2)));
                    }
                }
            }
            if (acclng3) {
                List<Map<String, Object>> row3 = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + id + " AND " + _lang + "=3"));
                if (row3.size() > 0) {
                    html3.append("<input type='button' id='edit_ln3_content' value='" + lbl_edit_ln3 + "' class='tahoma10'  />");
                    html.append(Js.buttonMouseClick("#edit_ln3_content", Js.jjContent.select(row3.get(0).get(_id).toString())));
                } else {
                    if (row.get(0).get(_parent).equals("0")) {
                        html3.append("<input type='button' id='add_ln3_content' value='" + lbl_add_ln3 + "' class='tahoma10' />");
                        html.append(Js.buttonMouseClick("#add_ln3_content", Js.jjContent.addLang(Integer.parseInt(id), 3)));
                    }
                }
            }
            if (acclng4) {
                List<Map<String, Object>> row4 = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + id + " AND " + _lang + "=4"));
                if (row4.size() > 0) {
                    html3.append("<input type='button' id='edit_ln4_content' value='" + lbl_edit_ln4 + "' class='tahoma10'  />");
                    html.append(Js.buttonMouseClick("#edit_ln4_content", Js.jjContent.select(row4.get(0).get(_id).toString())));
                } else {
                    if (row.get(0).get(_parent).equals("0")) {
                        html3.append("<input type='button' id='add_ln4_content' value='" + lbl_add_ln4 + "' class='tahoma10' />");
                        html.append(Js.buttonMouseClick("#add_ln4_content", Js.jjContent.addLang(Integer.parseInt(id), 4)));
                    }
                }
            }
            if (acclng5) {
                List<Map<String, Object>> row5 = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + id + " AND " + _lang + "=5"));
                if (row5.size() > 0) {
                    html3.append("<input type='button' id='edit_ln5_content' value='" + lbl_edit_ln5 + "' class='tahoma10'  />");
                    html.append(Js.buttonMouseClick("#edit_ln5_content", Js.jjContent.select(row5.get(0).get(_id).toString())));
                } else {
                    if (row.get(0).get(_parent).equals("0")) {
                        html3.append("<input type='button' id='add_ln5_content' value='" + lbl_add_ln5 + "' class='tahoma10' />");
                        html.append(Js.buttonMouseClick("#add_ln5_content", Js.jjContent.addLang(Integer.parseInt(id), 5)));
                    }
                }
            }
            html.append(Js.show("#Content_Language_button"));
            return (Js.setHtml("#Content_button", html2.toString())) + Js.setHtml("#Content_Language_button", html3) + html.toString();

            ///<**************LANGUAAGE ADDED*******
            //<============ BY RASHIDI ========
//            return (Js.setHtml("#Content_button", html2.toString())) + html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String add_EN(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            if (row.isEmpty()) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            Object title = row.get(0).get(_title);
            if (title != null) {
                html.append(Js.setVal("#" + _title, title));
            }
            html.append(Js.setVal("#" + _parent, id));
            html.append(Js.setVal("#" + _lang, 2));
            Object content = row.get(0).get(_content);
            if (content != null) {
                html.append(Js.setValEditor(_content, content));
            }
            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html2.append(Js.setHtml("#Content_button", "<input type=\"button\" id=\"insert_content_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">"));
                html2.append(Js.buttonMouseClick("#insert_content_new", Js.jjContent.insert()));
            }
            return html.toString() + html2.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String add_Ar(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            if (row.isEmpty()) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            Object title = row.get(0).get(_title);
            if (title != null) {
                html.append(Js.setVal("#" + _title, title));
            }
            html.append(Js.setVal("#" + _parent, id));
            html.append(Js.setVal("#" + _lang, 3));
            Object content = row.get(0).get(_content);
            if (content != null) {
                html.append(Js.setValEditor(_content, content));
            }
            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html2.append(Js.setHtml("#Content_button", "<input type='button' id='insert_content_new_ar' value='" + lbl_insert + "' class='tahoma10'>"));
                html2.append(Js.buttonMouseClick("#insert_content_new_ar", Js.jjContent.insert()));
            }
            return html.toString() + html2.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
//============ BY RASHIDI ========>
////// ------------- add_lang() ------------->

    public static String add_lang(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            String lang = jjTools.getParameter(request, "myLang");
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            if (row.isEmpty()) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
            StringBuilder html = new StringBuilder();
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            Object title = row.get(0).get(_title);
            if (title != null) {
                html.append(Js.setVal("#" + _title, title));
            }
            html.append(Js.setVal("#" + _parent, id));
            html.append(Js.setVal("#" + _lang, lang));
            Object content = row.get(0).get(_content);
            if (content != null) {
                html.append(Js.setValEditor(_content, content));
            }
            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#Content_button", "<input type='button' id='insert_content_new_lang' value='" + lbl_insert + "' class='tahoma10'>"));
                html.append(Js.buttonMouseClick("#insert_content_new_lang", Js.jjContent.insert()));
                html.append(Js.hide("#Content_Language_button"));
            }
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
//<============ BY RASHIDI ========
////// <------------- add_lang() -------------

    public static String getTitle(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _lang + "=" + (jjTools.isLangFa(request) ? 1 : 2)));
            String panel = jjTools.getParameter(request, "panel");
            html.append("<table  class='mousePointer' dir='" + jjTools.getLangDir(request) + "' style='width:100%'>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr style='text-align: right;height:25px'><td><a onclick='sw(" + (row.get(i).get(_id)) + ");return false;'"
                        + " href='Server?do=Content.sw&text=" + (row.get(i).get(_id)) + "' >"
                        + (row.get(i).get(_title)) + "</a></td></tr>");
            }
            html.append("</table>");
            return Js.setHtml("#" + panel, html.toString());
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getSomeTitle(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            String exept = jjTools.getParameter(request, "exept");
            exept = exept.equals("") ? "خانه،درباره ما، تماس با ما، محصولات" : exept;
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _lang + "=" + (jjTools.isLangFa(request) ? 1 : 2)));
            String panel = jjTools.getParameter(request, "panel");
            for (int i = 0; i < row.size(); i++) {
                if (!exept.toString().contains((row.get(i).get(_title).toString()))) {
                    html.append("<li><a onclick=\"sw(" + (row.get(i).get(_id)) + ");\">" + (row.get(i).get(_title))
                            + "</a></li>");
                }
            }
            return Js.append("#" + panel, html.toString());
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String searchTextInTitle(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            String text = jjTools.getParameter(request, "text");
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _title + " LIKE '%" + text + "%' "));
            html.append("<table class='news_cat mousePointer' dir='" + jjTools.getLangDir(request) + "' style='width:100%'>");
            String title = !jjTools.isLangEn(request) ? "عناوین یافت شده:" : " Search Status:";
            if (row.size() < 1) {
                title = !jjTools.isLangEn(request) ? "موردی یافت نشد." : "Not found this statement.";
            };
            html.append("<tr class='tblHeader'><td width=100% style='text-align: right'>" + title + "<br/><br/></td></tr>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr class='tblBody' style='text-align: right'><td  onclick=sw(" + (row.get(i).get(_id)) + ");>"
                        + (i + 1) + ". " + (row.get(i).get(_title)) + "</td>" + "</tr>");
            }
            html.append("</table>");
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String searchTextInAll(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            String title = "حاصل جستجو";
            if (jjTools.isLangEn(request)) {
                title = "Search result:";
            }
            String text = jjTools.getParameter(request, "text");
            String panel = jjTools.getParameter(request, "panel");
            String titlePanel = jjTools.getParameter(request, "title");
            panel = (panel.equals("") ? "sw" : panel);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _title + " LIKE '%" + text + "%'" + " OR " + _content + " LIKE '%" + text + "%'"));
            List<Map<String, Object>> rowNews = jjDatabase.separateRow(db.Select(News.tableName, News._title + " LIKE '%" + text + "%'" + " OR " + News._content + " LIKE '%" + text + "%'"));
            List<Map<String, Object>> rowProducts = jjDatabase.separateRow(db.Select(Product.tableName, Product._name + " LIKE '%" + text + "%'" + " OR " + Product._content + " LIKE '%" + text + "%'"));//====== BY RASHIDI ======
            if (row.size() < 1 && rowNews.size() < 1 && rowProducts.size() < 1) {
                return Js.setHtml("#" + panel, !jjTools.isLangEn(request) ? "موردی یافت نشد." : "Not found this statement in database.") + (titlePanel.equals("") ? "" : Js.setHtml("#" + titlePanel, title));
            }
            html.append("<table class='searchResult'>");
            html.append("<tr class='tahoma9'><th>"
                    + (jjTools.isLangEn(request) ? "Search result:" : "نتایج جستجو:")
                    + "</th></tr>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr class='tahoma9'>");
                html.append("<td class=\"mousePointer\" onclick=sw(" + (row.get(i).get(_id)) + ");>"
                        + (i + 1) + ". " + (row.get(i).get(_title)) + "</td></tr>");
            }
            for (int i = 0; i < rowNews.size(); i++) {
                html.append("<tr class='tahoma9'>");
                html.append("<td class=\"mousePointer\" onclick=swNews(" + (rowNews.get(i).get(News._id)) + ");>"
                        + (i + 1) + ". " + (rowNews.get(i).get(News._title)) + "</td></tr>");
            }
            //====== BY RASHIDI ======>
            for (int i = 0; i < rowProducts.size(); i++) {
                html.append("<tr class='tahoma9'>");
                html.append("<td class=\"mousePointer\" onclick=getOneproduct(" + (rowProducts.get(i).get(Product._id)) + ");>"
                        + (i + 1) + ". " + (rowProducts.get(i).get(Product._name)) + "</td></tr>");
            }
            //<====== BY RASHIDI ======
            html.append("</table>");

            return Js.setHtml("#" + panel, html.toString()) + (titlePanel.equals("") ? "" : Js.setHtml("#" + titlePanel, title));
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String sw(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String text = java.net.URLDecoder.decode(jjTools.getParameter(request, "text"), "utf-8");
            System.out.println("========== id" + text);
            String panel = jjTools.getParameter(request, "panel");
            String titlePanel = jjTools.getParameter(request, "title");
            boolean senderIsMyJs = jjTools.getParameter(request, "jj").equals("1");
            System.out.println(jjTools.getParameter(request, "jj"));

            String lang = jjTools.getSessionAttribute(request, "myLang");

            if (text.equals("")) {
                jjTools.ShowAllAttribute(request);
                text = request.getAttribute("text").toString();
                panel = request.getAttribute("panel").toString();
            }
            panel = (panel.equals("") ? "sw" : panel);
            List<Map<String, Object>> swContent = new ArrayList<Map<String, Object>>();
            if (jjNumber.isDigit(text)) {
                swContent = jjDatabase.separateRow(db.Select(tableName, _id + "=" + text + " AND " + _lang + " = " + lang));
//                swContent = jjDatabase.separateRow(db.Select(tableName, _id + "=" + tagName));
            } else {
                swContent = jjDatabase.separateRow(db.Select(tableName, _title + "='" + text + "'" + " AND " + _lang + " = " + lang));
//                swContent = jjDatabase.separateRow(db.Select(tableName, _title + "='" + tagName + "'"));
            }
            if (swContent.size() > 0) {
//                if (jjTools.isLangEn(request)) {
//                    List<Map<String, Object>> rowContent_en = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + swContent.get(0).get(_id) + " AND " + _lang + "=2"));
//                    if (rowContent_en.size() > 0) {
//                        swContent = rowContent_en;
//                    }
//                }
//                if (jjTools.isLangAr(request)) {
//                    List<Map<String, Object>> rowContent_ar = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + swContent.get(0).get(_id) + " AND " + _lang + "=3"));
//                    if (rowContent_ar.size() > 0) {
//                        swContent = rowContent_ar;
//                    }
//                }
                String content = ConvertToWiki(request, swContent.get(0).get(_content).toString(), db, isPost);
                String title = titlePanel.equals("") ? "" : Js.setHtml("#" + titlePanel, swContent.get(0).get(_title));
                String tamasBaMa = "";
                //by mohammad salesi غیر فعال کردن فرم اتوماتیک تماس با ما
//                if (text.equals("تماس با ما") || swContent.get(0).get(_title).toString().equals("تماس با ما")) {
//                    tamasBaMa = "sw('$comment');\n";
//                }
//                if (text.toLowerCase().equals("contact us")) {
//                    tamasBaMa = "sw('$comment');\n";
//                }
//                if (text.equals("استخدام") || swContent.get(0).get(_title).toString().equals("استخدام")) {
//                    tamasBaMa = "sw('$enrolment');\n";
//                }
//                if (text.toLowerCase().equals("enrolment")) {
//                    tamasBaMa = "sw('$enrolment');\n";
//                }
                //is not by Ajax
                if (!senderIsMyJs) {
                    System.out.println("jj != 1");
//                    return content;
                    String address = request.getServletContext().getRealPath("/");
                    File file = new File(address + "/" + Server.mainPage);
                    StringBuilder html = new StringBuilder(jjFileTxt.read(file).replace("\n", ""));
                    if (!file.exists()) {
                        return content;

                    } else {
                        //replace title.........................................
                        int index = html.indexOf("<title");//finding title tag
                        if (index > -1) {
                            index = html.indexOf(">", index); //finding title tag ( <title> )
                            title = swContent.get(0).get(_title).toString() + ", ";
                            html = html.insert(index + 1, title);
                        }

                        //replace content in <div id="sw"> ... </div>...........
                        index = html.indexOf("\"sw\"");//finding div in wich id="sw"
                        if (index > -1) {
                            index = html.indexOf(">", index); //finding end of "sw" div ( <div id="sw" clas="example" style="any" >
                            html = html.insert(index + 1, content);
                        }
                        //......................................................
                    }
//                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~ : " + html.toString());
                    return html.toString();
                }
                //is not by Ajax
                return (Js.setHtml("#" + panel, content) + title + tamasBaMa);
            } else {
                String errorMessage = "رکوردی با این محتوا وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "wiki Text Fail;";
                }
                return Js.setHtml("#" + panel, errorMessage);
            }
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String sw2(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            List<Map<String, Object>> swContent = jjDatabase.separateRow(db.Select(tableName, _id + "= " + "57"));
            return swContent.get(0).get(_content).toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
    public static List<Map<String, Object>> catchContentTitle = null;
    public static List<Map<String, Object>> catchProductTitle = null;
    public static List<Map<String, Object>> catchNewsTitle = null;
    public static String previousLang = null;

    public static String ConvertToWiki(HttpServletRequest request, String content, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
//            System.out.println("********************ConvertToWiki");
//            String lang =jjTools.getLangNum(request);
            //============ BY RASHIDI ========>            

            String lang = jjTools.getSessionAttribute(request, "myLang");

            if (previousLang == null) {
                previousLang = lang;
            }
            String panel = jjTools.getParameter(request, "panel");
            panel = (panel.equals("") ? "sw" : panel);

            SetCatchTitles(request, content, db, isPost);
            //<============ BY RASHIDI ========
//            if (catchContentTitle == null) {
//                catchContentTitle = jjDatabase.separateRow(db.Select(tableName, _lang + " = " + lang));
////                catchContentTitle = jjDatabase.separateRow(db.Select(tableName));
//            }
//            if (catchProductTitle == null) {
//                catchProductTitle = jjDatabase.separateRow(db.SelectAll(Product.tableName));
//            }
//            if (catchNewsTitle == null) {
//                catchNewsTitle = jjDatabase.separateRow(db.SelectAll(News.tableName));
//            }
//            content = content.replace("  ", " ");

            for (int i = 0; i < catchContentTitle.size(); i++) {
                String ti = catchContentTitle.get(i).get(_title).toString();
                String id = catchContentTitle.get(i).get(_id).toString();
                if (ti.length() > 2) {

                    content = content.replace(ti, "<a href='Server?do=Content.sw&text=" + id
                            + "' onclick='sw(" + id + ");return false;' class='mousePointer jjLink'>"
                            + ti + "</a>");
                }
            }
            for (int i = 0; i < catchProductTitle.size(); i++) {
                String ti = catchProductTitle.get(i).get(Product._name).toString();
                String id = catchProductTitle.get(i).get(_id).toString();
                if (ti.length() > 2) {
                    content = content.replace(ti, "<a href='Server?do=Product.getOneProduct&id=" + id + "&panel=" + panel + "' onclick='swProduct(" + id + ");return false;' class='mousePointer jjLink'>" + ti + "&nbsp;</a>");//============ EDITED BY RASHIDI ========>
                    content = content.replace("&nbsp;" + ti + "&nbsp;", "<a onclick=swProduct('" + id + "'); class='mousePointer jjLink' >" + ti + "&nbsp;</a>");
                }
            }
            for (int i = 0; i < catchNewsTitle.size(); i++) {
                String ti = catchNewsTitle.get(i).get(News._title).toString();
                String id = catchNewsTitle.get(i).get(_id).toString();
                if (ti.length() > 2) {
//                    content = content.replace(ti, "<a href=\"Server?do=News.sw&id=" + id + "&panel=" + panel + "\" onclick='swNews(" + id + ");return false;' class='mousePointer jjLink' >" + ti + "&nbsp;</a>");//============ EDITED BY RASHIDI ========>
                    content = content.replace(ti, "<a href='Server?do=News.getOneNews&id=" + id + "&panel=" + panel + "' onclick='swNews(" + id + ");return false;' class='mousePointer jjLink' >" + ti + "&nbsp;</a>");//============ EDITED BY RASHIDI ========>
                    content = content.replace("&nbsp;" + ti + "&nbsp;", "<a onclick=swNews('"
                            + id + "'); class='mousePointer jjLink' >" + ti + "&nbsp;</a>");
                }
            }
            //============ BY RASHIDI ========> 
            //برای درست نمایش دادن مقدار alt در عکسها.
            //برای مقدار ویژگی
            //alt
            //لینک ساخته نشود.
            Pattern pattern = Pattern.compile("alt=['\"]<a href=[\"'][a-zA-Z\\?=\\.&0-9'\"\\s\\(\\);>ا-ی]*</a>['\"]");//alt='<a href="Server?do=className.method?parameters=value" onclick="method(1);return false;" class="mousePointer jjLink">title</a>'
            Matcher matcher;
            List<String> listMatches = new ArrayList<String>();
            matcher = pattern.matcher(content);
            while (matcher.find()) {
                listMatches.add(matcher.group(0));
            }

            for (int j = 0; j < listMatches.size(); j++) {
                content = content.replace(listMatches.get(j), listMatches.get(j).replaceFirst("<a href=[\"'][a-zA-Z\\?=\\.&0-9'\"\\s\\(\\);>ا-ی]*>", "").replace("</a>", ""));
            }
            //<============ BY RASHIDI ======= 
            String searchValue = "نظرسنجی";
            int starter = 0;
            while (content.indexOf(searchValue, starter) > 0) {
                int searchValueIndex = content.indexOf(searchValue, starter);
                if (searchValueIndex > -1) {
                    if (content.length() > (searchValueIndex + (searchValue.length() + 4))) {
                        String val = content.substring(searchValueIndex, searchValueIndex + (searchValue.length() + 4));
                        String idString = val.replace(searchValue, "").trim();
                        if (jjNumber.isDigit(idString)) {
                            content = content.replace(val, Poll.getOnePoll(request, db, Integer.parseInt(idString)));
                        }
                    }
                }
                starter = searchValueIndex + 1;
            }
            return content;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    //============ BY RASHIDI ========>
    public static void SetCatchTitles(HttpServletRequest request, String content, jjDatabaseWeb db, boolean isPost) throws Exception {

        String lang = jjTools.getSessionAttribute(request, "myLang");

        if (previousLang.equals(lang)) {

            if (catchContentTitle == null) {
//                System.out.println("CONTENT");
                catchContentTitle = jjDatabase.separateRow(db.Select(tableName, _lang + " = " + lang + " AND " + _hasLink + " =1"));
//                catchContentTitle = jjDatabase.separateRow(db.Select(tableName));
            }
            if (catchProductTitle == null) {
//                System.out.println("PRODUCT");
                catchProductTitle = jjDatabase.separateRow(db.Select(Product.tableName, Product._lang + " = " + lang + " AND " + Product._hasLink + " =1"));
            }
            if (catchNewsTitle == null) {
//                System.out.println("NEWS");
                catchNewsTitle = jjDatabase.separateRow(db.Select(News.tableName, News._lang + " = " + lang + " AND " + News._hasLink + " =1"));
            }

        } else {
            previousLang = lang;
            ServerLog.Print("Content.SetCatchTitles ^^^^^^^^^^^^^  previousLang : " + previousLang);
            catchContentTitle = jjDatabase.separateRow(db.Select(tableName, _lang + " = " + lang + " AND " + _hasLink + " =1"));
            catchProductTitle = jjDatabase.separateRow(db.Select(Product.tableName, Product._lang + " = " + lang + " AND " + Product._hasLink + " =1"));
            catchNewsTitle = jjDatabase.separateRow(db.Select(News.tableName, News._lang + " = " + lang + " AND " + News._hasLink + " =1"));
        }
    }
//////--------------- searchTags() ----------------------->

    public static String searchTags(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            StringBuilder script = new StringBuilder();
            String text = jjTools.getParameter(request, tags._name);
            String panel = jjTools.getParameter(request, "panel");
            panel = (panel.equals("") ? "content_search_tags_result" : panel);
            List<Map<String, Object>> rowTags = jjDatabase.separateRow(db.Select(tags.tableName, tags._name + " LIKE '%" + text + "%'"));//تگ وارد شده پیش از این در جدول تگ ها ثبت شده یانه
            if (!rowTags.isEmpty()) {
                html.append("<table class='searchResult'>");
                for (int i = 0; i < rowTags.size(); i++) {
                    html.append("<tr class='tahoma9'>");
                    html.append("<td id=\"tagsResult_td" + i + "\" onclick=\"selectSearchResult(" + i + ");\">" + rowTags.get(i).get(tags._name) + "</td></tr>");
                }
                html.append("</table>");
                script.append(Js.setHtml("#" + panel, html));
                script.append(Js.show("#" + panel));
            } else {
                script.append(Js.setHtml("#" + panel, ""));
                script.append(Js.hide("#" + panel));
            }

            return script.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
//////<--------------- searchTags() -----------------------
//////--------------- insertTags() ----------------------->

    public static String insertTags(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            StringBuilder script = new StringBuilder();
            String errorMessage = "";
            String tagName = jjTools.getParameter(request, tags._name);
            String panel = jjTools.getParameter(request, "panel");
            panel = (panel.equals("") ? "content_tags_div" : panel);
            List<Map<String, Object>> rowTag = jjDatabase.separateRow(db.Select(tags.tableName, tags._name + " = '" + tagName + "'"));
            if (rowTag.isEmpty()) {//اگر تگ وارد شده پیش از این در جدول تگها ثبت نشده بود اینجا ابتدا این تگ را در جدول تگها ثبت میکند سپس ستون تگ جدول محتوا را آپدیت میکند
                //insert tag
                ServerLog.Print("Content.insertTags : INSERT TAG IN TAGS TBL ---->");
                request.setAttribute(tags._name, tagName);
                if (!tags.insert(request, db, isPost).equals("")) {
                    errorMessage = "در انجام عملیات مشکلی پیش آمده است.";
                    return Js.dialog(errorMessage);
                }
            }
            //اگر تگ وارد شده پیش از این در جدول تگ ها ثبت شده بود فقط ستون جدول محتوا را آپدیت می کند
            ///update content_tags
//            ServerLog.Print("Content.insertTags : UPDATE TAG IN CONTENT TBL ---->");
//            if (!db.otherStatement("UPDATE " + tableName + " SET " + _tags + " = CONCAT(" + _tags + ",'" + tagName + "',',') WHERE " + _id + "=" + jjTools.getParameter(request, _id))) {
//                errorMessage = "عملیات افزودن تگ به درستی صورت نگرفت.";
//                return Js.dialog(errorMessage);
//            } else {
            html.append("<span calss='tags' onclick=''>" + tagName + " , </span>");
            script.append(Js.append("#" + panel, html.toString()));
            return script.toString();
//            }
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
//////<--------------- insertTags() -----------------------
    //<============ BY RASHIDI ========
}
