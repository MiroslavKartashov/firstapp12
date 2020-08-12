import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.OutputStream;


@WebServlet(name = "OrderServlet", urlPatterns = "/order_servlet")
public class Order extends HttpServlet implements Servlet {

    private static Logger logger = LoggerFactory.getLogger(Order.class);

    private transient ServletConfig config;

    // Метод вызывается контейнером после того как был создан класс сервлета
    @Override
    public void init(ServletConfig config) throws ServletException {
        // Сохраняем полученную от сервера конфигурацию
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    // Метод вызывается для каждого нового HTTP запроса к данному сервлету
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        logger.info("New request");
        res.setContentType( "text/html" );
        String text = "Ваши Заказы" ;
        text = String.format(text, config.getInitParameter("Заказы"),
                config.getInitParameter("Заказы"));
        OutputStream outStream = res.getOutputStream();
        outStream.write(text.getBytes("UTF-8"));
        outStream.flush();
        outStream.close();
    }

    @Override
    public String getServletInfo() {
        return "OrderServlet";
    }

    // При завершении работы веб приложения, контейнер вызывает этот метод для всех сервлетов из этого приложения
    @Override
    public void destroy() {
        logger.info("Servlet {} destroyed", getServletInfo());
    }
}