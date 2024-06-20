package entities;

public class Professore {

		 	private int idProfessore;
		    private String nome;
		    private String cognome;
		    private String username;
		    private String password;
		    private String tipo_utente;
		    
		    
			public Professore ( int idProfessore, String nome, String cognome,String tipo_utente, String username, String password) {
				super();
				this.idProfessore = idProfessore;
				this.nome = nome;
				this.cognome = cognome;
				this.tipo_utente = tipo_utente;
				this.username = username;
				this.password = password;
			}

			public int getIdProfessore() {
				return idProfessore;
			}
			public void setIdProfessore(int idProfessore) {
				this.idProfessore = idProfessore;
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

			public String getTipo_utente() {
				return tipo_utente;
			}

			public void setTipo_utente(String tipo_utente) {
				this.tipo_utente = tipo_utente;
			}
		
		    
		    
	}
