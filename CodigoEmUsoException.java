/**
 * Exceção para o caso de tentar cadastrar com um código que já está sendo utilizado
 * @author José Augusto
 *
 */
public class CodigoEmUsoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CodigoEmUsoException(String msg) {
		super(msg);
	}
}
