import java.awt.Shape;
import java.awt.geom.Area;

public class Physics implements Commons {
	
	public static void reflectBallFromPaddle (Ball ball, Paddle paddle){
				
		if (paddle.movingAxis==0){

			int paddlePos = (int) paddle.getRect().getMaxX() + ball.i_width; 
	        int ballPos = (int) ball.getRect().getMaxX();
	        int paddleLength = paddle.i_width + 2*ball.i_width;
	        
	        double reflectingAngle = (((double)paddlePos - ballPos)/paddleLength);
	        reflectingAngle = reflectingAngle*Math.PI*5/6 + Math.PI/12;
	        //System.out.println("Reflection Angle (degrees): "+ Double.toString(reflectingAngle*180/Math.PI));

	        ball.setXDir((float)Math.cos(reflectingAngle));
	        if (paddle.getY()>Commons.HEIGHT/2)
	        	ball.setYDir(-(float)Math.sin(reflectingAngle));
	        else
	        	ball.setYDir((float)Math.sin(reflectingAngle));
	        
		} else {
			
			int paddlePos = (int) paddle.getRect().getMaxY() + ball.i_heigth;
	        int ballPos = (int) ball.getRect().getMaxY();
	        int paddleLength = paddle.i_heigth + 2*ball.i_heigth;
	       
	        double reflectingAngle = (((double)paddlePos - ballPos)/paddleLength);
	        reflectingAngle = reflectingAngle*Math.PI*5/6 + Math.PI/12;
	        //System.out.println("Reflection Angle (degrees): "+ Double.toString(reflectingAngle*180/Math.PI));

	        ball.setYDir((float)Math.cos(reflectingAngle));
	        if (paddle.getX()>Commons.WIDTH/2)
	        	ball.setXDir(-(float)Math.sin(reflectingAngle));
	        else
	        	ball.setXDir((float)Math.sin(reflectingAngle));
		}
	}
	
	public static boolean ballHitPlayersWall(Ball ball, Player player){
		
		switch (player.playerNumber){
		case 1:	if (ball.getRect().getMaxY()>=Commons.BOTTOM_EDGE)
					return true;
				break;
		case 2:	if (ball.getRect().getMinX()<=Commons.BORDER)
					return true;
				break;
		case 3:	if (ball.getRect().getMinY()<=Commons.BORDER)
					return true;
				break;
		case 4: if (ball.getRect().getMaxX()>=Commons.BOTTOM_EDGE)
					return true;
		}
		return false;
	}
	
	public static void reflectBallFromWall(Ball ball, Player player){

		switch (player.playerNumber){
		case 1:
		case 3:	ball.setYDir(-1 * ball.getYDir());
				break;
		case 2: 
		case 4:	ball.setXDir(- 1 * ball.getXDir());
				break;
		}
	}

    public static void reflectBallFromCorner(Ball ball, int corner_no){
    	
    	// Corner number 1 -> Top Left
    	// Corner number 2 -> Top Right ...
    	
    	float xdir = ball.getXDir();
    	float ydir = ball.getYDir();
    	
    	switch(corner_no)
    	{
    	case 1:
    	case 3:	ball.setXDir(-ydir);
    			ball.setYDir(-xdir);
    			break;
    	case 2:
    	case 4: ball.setXDir(ydir);
				ball.setYDir(xdir);
				break;
    	}
	}
    
    public static boolean testIntersection(Shape shapeA, Shape shapeB) {
	   Area areaA = new Area(shapeA);
	   areaA.intersect(new Area(shapeB));
	   return !areaA.isEmpty();
	}	
}
