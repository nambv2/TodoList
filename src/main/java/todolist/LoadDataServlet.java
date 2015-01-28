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
		
		String [] deleteItem = request.getParameterValues("deleteItem[]");
		if(request.getParameterValues("deleteItem[]") != null){
			if((deleteItem.length == 1) && (Integer.parseInt(deleteItem[0]) == -1)){
				listTodo.clear();
				key = 0;
				/*response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(key);*/
			}
			for(int i=0;i<deleteItem.length;i++){
		        System.out.println("Checked include: "+deleteItem[i]);
		        listTodo.remove(Integer.parseInt(deleteItem[i])-1);
			}
			key = listTodo.size();
	        System.out.println("Size is: "+key);
	        response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(Integer.toString(key));
		} else {
			System.out.println("Checked is null");
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
		String itemEdit = request.getParameter("idEdit");
		String newValue = request.getParameter("newValue");
		todoModel = new Todo();
		if((outputParam.trim()!=null)&&(outputParam.trim()!="")&&((item==null)||(item.trim() == ""))){
			System.out.println("Enter list to do");
			todoModel.setId(key+1);
			todoModel.setName(outputParam);
			listTodo.put(key,todoModel);
			key++;
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(Integer.toString(key));
		}
		if((item!=null)&&(itemEdit==null)){
			System.out.println("Item is inside :"+item+" "+item.length());
			System.out.println("Delete item");
			int tam=Integer.parseInt(item)-1;
			System.out.println("item is:"+tam);
			listTodo.remove(tam);
			key = listTodo.size();
			System.out.println("key-- is:"+size);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(Integer.toString(key));
		}
		if(itemEdit != null){
			System.out.println("Edit item");
			int tam=Integer.parseInt(itemEdit);
			String value = listTodo.get(tam-1).getName();
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(value);
			System.out.println("Value of item will be edit is:"+value);
		}
		if(newValue!=null){
			System.out.println("I'm here");
			int tam = Integer.parseInt(itemEdit);
			todoModel.setId(tam);
			todoModel.setName(newValue);
			listTodo.put(tam-1, todoModel);
		}
	}
}