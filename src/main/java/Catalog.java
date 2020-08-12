import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.OutputStream;


@WebServlet(name = "CatalogServlet", urlPatterns = "/Catalog_servlet")
public class Catalog extends HttpServlet implements Servlet {

    private static Logger logger = LoggerFactory.getLogger(Catalog.class);

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
        String text = "Наш Ассоортимент ";
        text = String.format(text, config.getInitParameter("Catalog"),
                config.getInitParameter("Catalog"));
        OutputStream outStream = res.getOutputStream();
        outStream.write(text.getBytes("UTF-8"));
        outStream.flush();
        outStream.close();
    }

    @Override
    public String getServletInfo() {
        return "CatalogServlet";
    }

    @Override
    public void destroy() {
        logger.info("Servlet {} destroyed", getServletInfo());
    }
}