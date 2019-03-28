package servlet;
 
import java.io.IOException;
import java.util.List;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import bean.Book;
import dao.BookDAO;
 
public class BookListServlet extends HttpServlet {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String userName = (String) request.getSession().getAttribute("userName");
        if (null == userName) {
            response.sendRedirect("/library/login.html");
            return;
        }
//        int start = 0;
//        int count = 5;
//        try {
//        	start = Integer.parseInt(request.getParameter("start"));
//		} catch (NumberFormatException e) {
//			// TODO: handle exception
//		}
        
//        int next = start + count;
        List<Book> books = new BookDAO().list();
//        request.setAttribute("next", next);
//        request.setAttribute("books", books);
        StringBuffer sb = new StringBuffer();
        sb.append("<h3 align='center'>欢迎使用图书管理系统</h3>");
        sb.append("<table style='width:500px; border-collapse:collapse !important margin:44px auto' align='center' border='1' cellspacing='0'>\r\n");
        sb.append("<tr><td>id</td><td>bookName</td><td>author</td><td>publishDate</td><td>edit</td><td>delete</td></tr>\r\n");
        String trFormat = "<tr><td>%d</td><td>%s</td><td>%s</td><td>%s</td><td><a href='editBook?id=%d'>edit</a></td><td><a href='deleteBook?id=%d'>delete</a></td></tr>\r\n";
        
        for (Book book : books) {
            String tr = String.format(trFormat, book.getId(), book.getBookName(), book.getAuthor(), book.getPublishDate(), book.getId(), book.getId());
            sb.append(tr);
        }
//        sb.append("<tr><td align='center' colspan='6'><a href='?start=next'>[下一页]</a></td></tr>");
        sb.append("</table>");
 
        response.getWriter().write(sb.toString());
 
    }
}