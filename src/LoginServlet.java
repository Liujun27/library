import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class LoginServlet extends HttpServlet {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        String name = request.getParameter("name");
        String password = request.getParameter("password");
 
        String html = null;
 
        if ("admin".equals(name) && "123".equals(password)) {
        	request.getSession().setAttribute("userName", name);
        	response.sendRedirect("/library/listBook");
        }else {
            html = "<div style='color:red'>fail</div>";
            PrintWriter pw = response.getWriter();
            pw.println(html);
            response.sendRedirect("/library/login.html");
        }
        
        
 
    }
 
}