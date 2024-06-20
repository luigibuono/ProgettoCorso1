package entities;

public class Utente {
	 	private int idStudente;
	    private String nome;
	    private String cognome;
	    private String username;
	    private String password;
	    
	    
		public Utente( int idStudente, String nome, String cognome, String username, String password) {
			super();
			this.idStudente = idStudente;
			this.nome = nome;
			this.cognome = cognome;
			this.username = username;
			this.password = password;
		}

		public int getIdStudente() {
			return idStudente;
		}
		public void setIdStudente(int idStudente) {
			this.idStudente = idStudente;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getCognome() {
			return cognome;
		}
		public void setCognome(String cognome) {
			this.cognome = cognome;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	
	    
	    
}
