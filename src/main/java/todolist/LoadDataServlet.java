package todolist;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Todo;

import org.eclipse.jetty.util.Trie;
/**
 * Servlet implementation class LoadDataServlet
 */
public class LoadDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<Integer, Todo> listTodo = new HashMap<Integer, Todo>();
	int key = 0;
	int size = 0;
	Todo todoModel;
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
		String delAll = request.getParameter("delAll");
		if(delAll == null){
			Iterator<Todo> i = listTodo.values().iterator();
			PrintWriter writer = response.getWriter();
			
			 writer.print("[");
		      
		      while (i.hasNext()) {
		        todoModel = i.next();
		        response.setContentType("text/json");
		        StringBuilder sb = new StringBuilder("{");
		        sb.append("\"id\" : ").append(todoModel.getId()).append(",");
		        sb.append("\"name\" :").append("\"").append(todoModel.getName()).append("\"");
		        sb.append("}");
		        if (i.hasNext()) sb.append(",");
		        writer.print(sb.toString());
		        
		      }
		      
		      writer.print("]");
		} else {
			listTodo.clear();
			key = 0;
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(key);
		}
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String outputParam =request.getParameter("param");
		String item = request.getParameter("itemChoose");
		todoModel = new Todo();
		if((outputParam.trim()!=null)&&(outputParam.trim()!="")&&(item==null)){
			todoModel.setId(key+1);
			todoModel.setName(outputParam);
			listTodo.put(key,todoModel);
			key++;
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(Integer.toString(key));
		}
		if(item!=null){
			int tam=Integer.parseInt(item)-1;
			System.out.println("item is:"+tam);
			listTodo.remove(tam);
			size = listTodo.size();
			System.out.println("key-- is:"+size);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(Integer.toString(size));
		}
		
	}
}
