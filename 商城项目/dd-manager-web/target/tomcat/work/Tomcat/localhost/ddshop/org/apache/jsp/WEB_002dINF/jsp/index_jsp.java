/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2017-11-20 10:31:24 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>天天商城后台管理系统</title>\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"js/jquery-easyui-1.5/themes/default/easyui.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"js/jquery-easyui-1.5/themes/icon.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"js/layer/skin/layer.css\" />\r\n");
      out.write("\r\n");
      out.write("<!-- 自定义css -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"css/web.css\" />\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"easyui-layout\">\r\n");
      out.write("\r\n");
      out.write("    <div data-options=\"region:'north',border:false\" style=\"padding:5px;background-color:#3D3D3D;color:#fff; \">\r\n");
      out.write("        <h1 style=\"font-size:20px;\">天天商城后台管理系统</h1>\r\n");
      out.write("    </div>\r\n");
      out.write("    \r\n");
      out.write("    <div data-options=\"region:'west'\" style=\"width:200px;\">\r\n");
      out.write("        <div id=\"menu\" class=\"easyui-accordion\" data-options=\"border:false\">\r\n");
      out.write("\t\t      <div title=\"商品管理\" data-options=\"selected:true\" style=\"padding:10px 0;\">\r\n");
      out.write("\t\t         \t<ul class=\"easyui-tree\"> \r\n");
      out.write("\t\t         \t  <li data-options=\"attributes:{'href':'item-add'}\">新增商品</li>\r\n");
      out.write("\t\t\t\t      <li data-options=\"attributes:{'href':'item-list'}\">查询商品</li>\r\n");
      out.write("\t\t\t\t      <li data-options=\"attributes:{'href':'item-param-list'}\">规格参数</li>  \r\n");
      out.write("\t\t\t\t\t</ul>    \r\n");
      out.write("\t\t      </div>\r\n");
      out.write("\t\t      <div title=\"广告管理\" style=\"padding:10px 0;\">\r\n");
      out.write("\t\t      \t\t<ul class=\"easyui-tree\">  \r\n");
      out.write("\t\t\t\t\t      <li data-options=\"attributes:{'href':'content-category'}\">分类管理</li>\r\n");
      out.write("\t\t\t\t\t      <li data-options=\"attributes:{'href':'content'}\">广告内容管理</li>\r\n");
      out.write("\t\t\t\t\t</ul> \t         \r\n");
      out.write("\t\t      </div>\r\n");
      out.write("\t\t      <div title=\"索引库管理\" style=\"padding:10px 0;\">\r\n");
      out.write("\t\t      \t\t<ul class=\"easyui-tree\"> \r\n");
      out.write("\t\t\t\t      \t<li data-options=\"attributes:{'href':'index-item'}\">solr索引库维护</li>\r\n");
      out.write("\t\t\t\t\t</ul>          \r\n");
      out.write("\t\t      </div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("    </div>\r\n");
      out.write("    \r\n");
      out.write("    <div data-options=\"region:'south'\" style=\"background:#F2F2F2;padding:5px;\">\r\n");
      out.write("        \t系统版本：V1.0 by Kwinin\r\n");
      out.write("    </div>    \r\n");
      out.write("    <div data-options=\"region:'center'\">\r\n");
      out.write("    \r\n");
      out.write("    \t<div id=\"tt\" class=\"easyui-tabs\" data-options=\"fit:true\">   \r\n");
      out.write("\t\t    <div title=\"主页面\" style=\"padding:20px;display:none;\">   \r\n");
      out.write("\t\t        Welcome  \r\n");
      out.write("\t\t    </div>    \r\n");
      out.write("\t\t</div>  \r\n");
      out.write("\t\t    \t\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("<script src=\"js/jquery-easyui-1.5/jquery.min.js\"></script>\r\n");
      out.write("<script src=\"js/jquery-easyui-1.5/jquery.easyui.min.js\"></script>\r\n");
      out.write("<script src=\"js/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<!-- moment -->\r\n");
      out.write("<script src=\"js/moment/moment.js\"></script>\r\n");
      out.write("<script src=\"js/moment/zh-cn.js\"></script>\r\n");
      out.write("<!-- layer -->\r\n");
      out.write("<script src=\"js/layer/layer.js\"></script>\r\n");
      out.write("<!-- 百度富文本编辑器 UEditor -->\r\n");
      out.write("<script src=\"js/ueditor/ueditor.config.js\"></script>\r\n");
      out.write("<script src=\"js/ueditor/ueditor.all.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;\r\n");
      out.write("UE.Editor.prototype.getActionUrl = function(action) {\r\n");
      out.write("    if (action == 'uploadimage') {\r\n");
      out.write("        //return 'http://localhost:8080/ttshop/file/upload';\r\n");
      out.write("        return '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/file/upload?action=uploadimage';\r\n");
      out.write("    } else {\r\n");
      out.write("        return this._bkGetActionUrl.call(this, action);\r\n");
      out.write("    }\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("<!-- 自定义js -->\r\n");
      out.write("<script src=\"js/common.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
