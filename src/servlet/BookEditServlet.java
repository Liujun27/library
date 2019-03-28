package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Book;
import dao.BookDAO;

public class BookEditServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		Book book = new BookDAO().get(id);

		StringBuffer format = new StringBuffer();
		response.setContentType("text/html; charset=UTF-8");

		format.append("<!DOCTYPE html>");

		format.append("<form action='updateBook' method='post'>");
		format.append("书名 ： <input type='text' name='bookName' value='%s' > <br>");
		format.append("作者 ： <input type='text' name='author'  value='%s' > <br>");
		format.append("日期： <input type='text' name='pd'  value='%s' > <br>");
		format.append("<input type='hidden' name='id' value='%d'>");
		format.append("<input type='submit' value='更新'>");
		format.append("</form>");

		String html = String.format(format.toString(), book.getBookName(), book.getAuthor(), book.getPublishDate(), book.getId());

		response.getWriter().write(html);

	}
}
