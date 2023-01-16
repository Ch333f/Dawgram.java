/**
* A problem-specific run time exception for a Nonogram puzzle.
* 
* @author OTechCup
* @credits ["Mr. O"]
* @version 0.1
*/


package nonogram;


@SuppressWarnings("serial")
public class NonogramException extends RuntimeException {
	/**
	 * Constructor with explanatory message
	 * 
	 * @param message the explanatory message
	 */
	public NonogramException(String msg) {
		super(msg);
	}
}
