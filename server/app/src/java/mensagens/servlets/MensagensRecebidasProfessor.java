package mensagens.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mensagens.model.MensagensModel;
import mensagens.repository.MensagensRepository;
import utils.Conector;

@WebServlet(name = "MensagensRecebidasProfessor", urlPatterns = {"/mensagens/recebidas/professor"})
public class MensagensRecebidasProfessor extends HttpServlet {

	protected void processRequest(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		Connection c;
		
		try {
			c = Conector.getConnection();
			MensagensRepository mr = new MensagensRepository(c);
		
			String id = req.getParameter("id");
			
			List<MensagensModel> r = mr.mensagensRecebidasProfessor(id);
			out.println("<mensagens>");
			for(int i = 0; i < r.size(); i++){
				out.println("<mensagem>");
				out.println("<idMensagem>" + r.get(i).getIdMensagem() + "</idMensagem>");
				out.println("<texto>" + r.get(i).getTexto() + "</texto>");
				out.println("<idAluno>" + r.get(i).getIdAluno() + "</idAluno>");
				out.println("<idProf>" + r.get(i).getIdProf() + "</idProf>");
				out.println("<alunoEnviou>" + r.get(i).isAlunoEnviou() + "</alunoEnviou>");
				out.println("<data>" + r.get(i).getDataHorario() + "</data>");
				out.println("</mensagem>");
			}
			out.println("</mensagens>");
			c.close();
		} catch (ClassNotFoundException | SQLException ex) {
			res.setStatus(500);
			out.println("<erro><mensagem>Erro na interação com o servidor</mensagem></erro>");
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
