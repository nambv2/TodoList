package todolist;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class LoadDataServlet
 */
public class LoadDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<Integer, String> listTodo = new HashMap<Integer, String>() ;
	int key = 0;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*Iterator<String> iter = listTodo.values().iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		String json = new Gson().toJson(listTodo);
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);*/
		Iterator<String> i = listTodo.values().iterator();
		PrintWriter writer = response.getWriter();
		
		 writer.print("[");
	      
	      while (i.hasNext()) {
	        response.setContentType("text/json");
	        
	        StringBuilder sb = new StringBuilder("{");
	        
	        sb.append("\"id\" : ").append(i.next()).append(",");
	        sb.append("\"name\" :").append("\"").append(i.next()).append("\"");
	        sb.append("}");
	        if (i.hasNext()) sb.append(",");
	        writer.print(sb.toString());
	        
	      }
	      
	      writer.print("]");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String outputParam =request.getParameter("param");
		if(outputParam.trim()!=null){
			listTodo.put(key, outputParam);
			key++;
		}
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(Integer.toString(key));
		System.out.println(key+"****");
		
		/*String json = new Gson().toJson(listTodo);
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);*/
	}

}
