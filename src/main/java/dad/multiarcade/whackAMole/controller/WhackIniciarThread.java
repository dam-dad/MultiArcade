package dad.multiarcade.whackAMole.controller;

import javafx.scene.image.Image;

public class WhackIniciarThread extends Thread{
	
	public void run() {
		//int segActual = (int) System.currentTimeMillis();
		//int delay = 20000; //20 segundos
		//while((int)System.currentTimeMillis() <= (segActual + delay)){
		for(int i=0;i<=20;i++) {
			int aleatorio1 = (int) ((Math.round(Math.random()*2+0)));
			int aleatorio2 = (int) ((Math.round(Math.random()*2+0)));
			
			if(WhackAMoleController.agujero[aleatorio1][aleatorio2].isActivado()==false) {
				WhackAMoleController.agujero[aleatorio1][aleatorio2].setActivado(true);
				WhackAMoleController.agujero[aleatorio1][aleatorio2].setMoleImg(new Image("/img/WhackAMole/peepo.png"));
			}	
			try {
				sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
