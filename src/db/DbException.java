package db;

public class DbException extends RuntimeException { //Runtimeexception sao os erros que podem ser previnidos, exception sao os erros que devem ser previnidos.
	private static final long serialVersionUID = 1L;

	public DbException(String msg) { //Criar um construtor e passar como argumento uma String msg.
		super(msg); //E no super retorna essa msg, o super serve para chamar o construtor da classe mãe.
	}
}
