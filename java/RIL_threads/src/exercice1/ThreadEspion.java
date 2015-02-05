package exercice1;

public class ThreadEspion implements Runnable {
	// champ privé qui contenant l'adresse de l'objet Test
	private Test papa;

	public ThreadEspion(Test papa) {
		this.papa = papa;
	}

	@Override
	public void run() {
		//Boucle infini
		while(true) {
			// dormir 200 milliseconde
			try {
				Thread.sleep(200);
			} catch(InterruptedException e) {
			}

			int nbrPropose = papa.getNombrePropose();
			int nbrMemorise = papa.getNombreMemorise();
			// si le nombre mémorisé de Test différent de nbr proposé
			if(nbrMemorise != nbrPropose) {
				// si le nombre proposé est 0 --> exit de l'application
				if(nbrPropose == 0)
					System.exit(0);
				// sinon on stock le nbr proposé dans le mémorisé
				papa.setNombreMemorise(nbrPropose);
				System.out.println("Le démon s'est réveillé et a moifié le mémorisé...");
			}
		}
	}

}
