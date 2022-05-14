package cardgame.exception;

/**
 * Runtime系のエラーを吐き出すクラス(NullPointerExceptionなど)
 *
 * @author kijima
 *
 */
public class SystemErrorException extends Exception {

	/**
	 * シリアルID
	 */
	private static final long serialVersionUID = 1L;

	Exception ex;

	public SystemErrorException(Exception e) {
		this.ex = e;
	}

	public SystemErrorException(String msg) {

	}

	public SystemErrorException() {
	}
}