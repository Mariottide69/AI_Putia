package it.arces.ai_putia.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class LoggingFilter implements javax.servlet.Filter {

   private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
         throws IOException, ServletException {
      HttpServletRequest httpRequest = (HttpServletRequest) request;
      logger.info("Request URL: {} Method: {}", httpRequest.getRequestURL(), httpRequest.getMethod());
      chain.doFilter(request, response);
   }

   @Override
   public void init(FilterConfig filterConfig) throws ServletException {
      // Initialization code if needed
   }

   @Override
   public void destroy() {
      // Cleanup code if needed
   }
}
