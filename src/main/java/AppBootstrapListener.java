
import keep.Prod;
import keep.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AppBootstrapListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(AppBootstrapListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
             logger.info("Инициализация");
        ServletContext sc = sce.getServletContext();
        String jdbcConnectionString =sc.getInitParameter("jdbcConnectionString");
        String username = sc.getInitParameter("username");
        String password = sc.getInitParameter("password");

        try {
            Connection conn = DriverManager.getConnection(jdbcConnectionString, username, password);
            sc.setAttribute("connection", conn);

            ProductRepository product  = new ProductRepository(conn);
            sc.setAttribute("producRepository", product);

            ProductRepository productRepository = new ProductRepository(conn);
            sc.setAttribute("productRepository", productRepository);

            if (productRepository.findAll().isEmpty()) {
                logger.info("No products in DB. Initializing.");


                productRepository.insert(new Prod(-1L, "Виски Армянский", "Крепкие спиртные напитки", new BigDecimal(900)));
                productRepository.insert(new Prod(-1L, "Водка Столичная", "Крепкие спиртные напитки", new BigDecimal(500)));
                productRepository.insert(new Prod(-1L, "Джин Bin", "Крепкие спиртные напитки", new BigDecimal(1200)));
                productRepository.insert(new Prod(-1L, "Кокур Массандра", "Вина", new BigDecimal(500)));
                productRepository.insert(new Prod(-1L, "Мускат", "Вина", new BigDecimal(800)));
                productRepository.insert(new Prod(-1L, "Охота", "Пиво", new BigDecimal(300)));
            }

        } catch (SQLException ex) {
            logger.error("", ex);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
