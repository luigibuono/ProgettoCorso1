package controllers;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.Connessione;

/**
 * Servlet implementation class Prenota
 */
@WebServlet("/jsp/Prenota") // Definisce l'URL pattern per il servlet
public class Prenota extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore del servlet Prenota
	 */
	public Prenota() {
		super();
	}

	/**
	 * Metodo doGet che gestisce le richieste GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Risponde con un messaggio che include il contesto dell'applicazione
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * Metodo doPost che gestisce le richieste POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Ottiene la sessione corrente
		HttpSession session = request.getSession();
		// Recupera il parametro 'appello' dalla richiesta
		String appello = request.getParameter("appello");
		// Recupera l'attributo 'matricola' dalla sessione
		String matricola = (String) session.getAttribute("matricola");
		// Ottiene una connessione al database
		Connection conn = Connessione.getCon();

		try {
			// Prepara una query per verificare se lo studente è già prenotato per questo appello
			PreparedStatement checkStmt = conn.prepareStatement("SELECT * FROM prenotazione WHERE stud_prenotato = ? AND app_prenotato = CAST(? AS UNSIGNED INTEGER)");
			checkStmt.setString(1, matricola);
			checkStmt.setString(2, appello);
			ResultSet checkResult = checkStmt.executeQuery();

			if (checkResult.next()) {
				// Se lo studente è già prenotato, imposta un messaggio di errore e reindirizza a studente.jsp
				String error = "Hai già prenotato questo appello.";
				request.setAttribute("error", error);
				RequestDispatcher rd = request.getRequestDispatcher("studente.jsp");
				rd.forward(request, response);
			} else {
				// Se lo studente non è prenotato, esegue l'inserimento della prenotazione
				PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO prenotazione (stud_prenotato, app_prenotato) VALUES (CAST(? AS UNSIGNED INTEGER), CAST(? AS UNSIGNED INTEGER))");
				insertStmt.setString(1, matricola);
				insertStmt.setString(2, appello);
				insertStmt.executeUpdate();

				// Recupera la data dell'appello prenotato
				PreparedStatement recuperoData = conn.prepareStatement("SELECT data FROM appello WHERE idAppello = CAST(? AS UNSIGNED INTEGER)");
				recuperoData.setString(1, appello);
				ResultSet data = recuperoData.executeQuery();
				data.next();
				String dataScelta = data.getString(1);

				// Recupera il nome della materia dell'appello prenotato
				PreparedStatement recuperoMateria = conn.prepareStatement("SELECT materia FROM corso WHERE idcorso = CAST(? AS UNSIGNED INTEGER)");
				recuperoMateria.setString(1, appello);
				ResultSet materia = recuperoMateria.executeQuery();
				materia.next();
				String nomeMateria = materia.getString(1);

				 // Imposta gli attributi per la visualizzazione dei dati nella JSP formstudente.jsp
                // Imposta l'elenco dei corsi
                PreparedStatement corsiStmt = conn.prepareStatement("SELECT idcorso, materia, nome, cognome FROM corso INNER JOIN docente ON corso.iddocente = docente.iddocente");
                ResultSet resCorsi = corsiStmt.executeQuery();
                request.setAttribute("tabella_corso", resCorsi);

                // Imposta l'elenco degli appelli disponibili
                PreparedStatement appelliStmt = conn.prepareStatement("SELECT idAppello, data FROM appello");
                ResultSet resAppelli = appelliStmt.executeQuery();
                request.setAttribute("elenco_appelli", resAppelli);
                
				// Imposta gli attributi per la visualizzazione dei dati nella JSP studente.jsp
				request.setAttribute("data", dataScelta);
				request.setAttribute("materia2", nomeMateria);

				RequestDispatcher rd2 = request.getRequestDispatcher("/jsp/formstudente.jsp");
                rd2.forward(request, response);
                
				// Reindirizza alla pagina studente.jsp per mostrare il messaggio di successo
				RequestDispatcher rd1 = request.getRequestDispatcher("/jsp/studente.jsp");
				rd1.forward(request, response);
			}
		} catch (SQLException e) {
			// Gestisce eventuali eccezioni SQL, impostando un messaggio di errore e reindirizzando a studente.jsp
			e.printStackTrace();
			request.setAttribute("error", "La prenotazione è avvenuta con successo");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/studente.jsp");
			rd.forward(request, response);
		}
	}
}
