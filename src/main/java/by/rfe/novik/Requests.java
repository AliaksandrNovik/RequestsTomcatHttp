package by.rfe.novik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class Requests
 */
@WebServlet("/Requests")
public class Requests extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<Message> messages = new LinkedList<Message>();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Requests() {
		super();

		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Message> listMessages = new ArrayList<Message>();
		listMessages = new FetchData().getList();
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(listMessages, new TypeToken<List<Message>>() {}.getType());
		JsonArray jsonArray = jsonElement.getAsJsonArray();
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FetchData database;
		database = new FetchData();
		database.putIntoDataBase(processRequest(request, response));

	}

	protected Message processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
		if(br != null){
			json = br.readLine();
		}
		// 2. initiate jackson mapper
		ObjectMapper mapper = new ObjectMapper();
		Message meassage = mapper.readValue(json, Message.class);
		// 4. Set response type to JSON
		response.setContentType("application/json");            
		// 5. Add article to List<Message>
		messages.add(meassage);
		// 6. Send List<Message> as JSON to client
		mapper.writeValue(response.getOutputStream(), messages);

		return meassage;

	}


}
