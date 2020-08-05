import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "MainServlett", urlPatterns = "/main_servlet")
public class GmainServlet extends HttpServlet implements Servlet{

    private static Logger logger = LoggerFactory.getLogger(GmainServlet.class);

    private transient ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }


    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        logger.info("New request");
        {
            res.setContentType( "text/html" );
            String text = "<!DOCTYPE html PUBLIC " +
                    "\"-//W3C//DTD HTML 4.01 Transitional//EN\" " +
                    "\"http://www.w3.org/TR/html4/loose.dtd\"> " +
                    "<html><head>" +
                    "<meta http-equiv=\"Content-Type\" " +
                    "content=\"text/html; charset=UTF-8\"> " +
                    "<title>Меню</title>" +
                    "</head>" +
                    "<body>" +
                    "<h1> Главное Меню </h1>" +
                    "</body></html>";
            text = String.format(text, config.getInitParameter("Меню"),
                    config.getInitParameter("Меню"));
            OutputStream outStream = res.getOutputStream();
            outStream.write(text.getBytes("UTF-8"));
            outStream.flush();
            outStream.close();
        }

//        res.getWriter().println("<h1>Главное Менюt</h1>");
    }

    @Override
    public String getServletInfo() {
        return "MainServlet";
    }


    @Override
    public void destroy() {
        logger.info("Servlet {} destroyed", getServletInfo());
    }
}