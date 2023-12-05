package br.com.qfa.resources.domain.user;

public enum UserRole {
    ADMIN(1,"ROLE_ADMIN"),
    USER(2, "ROLE_CLIENTE");

	private int cod;
    private String role;

    private UserRole(int cod, String descricao) {
		this.cod = cod;
		this.role = descricao;
	}

    public String getRole(){
        return role;
    }

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public static UserRole toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for (UserRole x : UserRole.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id Invalido" + cod);
	}
    
}