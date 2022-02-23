package dad.multiarcade.informes;

import java.util.ArrayList;
import java.util.List;
import dad.multiarcade.snake.controller.*;

public class SnakeDataProvider {

	public static List<Score> getScore() {
			
			List<Score> scores = new ArrayList<>();
			
			for (long i = 1; i <= 1; i++) {
				Score score = new Score();

				score.setScore(SnakeController.getScore());
				
				scores.add(score);
				
			}
			
			return scores;
		}
		
	
}