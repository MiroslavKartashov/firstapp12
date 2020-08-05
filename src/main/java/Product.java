import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.nio.cs.UTF_8;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;


@WebServlet(name = "ProductServlet", urlPatterns = "/product_servlet")
public class Product extends HttpServlet implements Servlet {

    private static Logger logger = LoggerFactory.getLogger(Product.class);

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
                    "<h1> Выбор Товары/Product </h1>" +
                    "</body></html>";
            text = String.format(text, config.getInitParameter("Товары"),
                    config.getInitParameter("Товары"));
            OutputStream outStream = res.getOutputStream();
            outStream.write(text.getBytes("UTF-8"));
            outStream.flush();
            outStream.close();
        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("New POST request");
        resp.setHeader("Content-Type", "text/html; charset=utf-8");
        resp.getWriter().printf("<h1>Ghjlerns</h1>");
    }



    @Override
    public String getServletInfo() {
        return "ProductServlet";
    }

    @Override
    public void destroy() {
        logger.info("Servlet {} destroyed", getServletInfo());
    }
}