/**
* A problem-specific run time exception for a Dawgram puzzle.
* 
* @author OTechCup
* @credits ["Mr. O"]
* @version 0.1
*/


package dawgram;


@SuppressWarnings("serial")
public class DawgramException extends RuntimeException {
	/**
	 * Constructor with explanatory message
	 * 
	 * @param message the explanatory message
	 */
	public DawgramException(String msg) {
		super(msg);
	}
}
