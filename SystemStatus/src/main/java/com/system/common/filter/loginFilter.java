package  com.system.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.system.entity.Users;


public class loginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		 HttpServletRequest request = (HttpServletRequest) servletRequest; 
		 HttpServletResponse response=(HttpServletResponse)servletResponse;
		 HttpSession session = request.getSession();
		 Users  u=(Users) session.getAttribute("loginUser");	
		 String reqUrl = request.getRequestURL().toString();
	
		 if(reqUrl.indexOf("login")>-1||reqUrl.indexOf("image")>-1||reqUrl.indexOf("SystemStatus/services")>-1){
			 filterChain.doFilter(servletRequest, servletResponse);
			 return;
		 }
		if(u==null){
			response.sendRedirect(request.getContextPath()+"/tologin");
		}else{
			filterChain.doFilter(servletRequest, servletResponse); 
		}
		

			 
		  
		 
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
