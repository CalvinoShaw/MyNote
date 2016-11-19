package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class filterForLoginAndDecode implements Filter{
	
	

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		
		System.out.println("filter--in");
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;   
		HttpServletResponse response = (HttpServletResponse) servletResponse;   
		
		HttpSession session = request.getSession();
		String uid = (String)session.getAttribute("uid");
		if(uid==null){
			response.sendRedirect("login.html");
			return;
		}
		
		chain.doFilter(request, response);
		
		System.out.println("filter--out");
	}
	
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("filter--init");
	}
	public void destroy() {
		System.out.println("filter--destroy");
	}
}
