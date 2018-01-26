import java.util.function.Predicate;

class Requerente {
	public boolean isConfiavel() {
		return true;
	}

	public int getPontuacaoDeCredito() {
		return 700;
	}

	public int getAnosDeTrabalho() {
		return 10;
	}

	public boolean hasRegistroCriminal() {
		return true;
	}
}

class Sample {
	public static void avaliar(Requerente requerente, Predicate<Requerente> avaliador) {
		String result = avaliador.test(requerente) ? "accepted" : "rejected";
		System.out.println("Resultado da Avaliação do Requerente: " + result);
	}

	public static void main(String[] args) {
		Requerente requerente = new Requerente();

		final Predicate<Requerente> creditCheck = 
				onRequerente -> onRequerente.getPontuacaoDeCredito() > 600;

		final Predicate<Requerente> employmentCheck = 
				onRequerente -> onRequerente.getAnosDeTrabalho() > 0;

		final Predicate<Requerente> crimeCheck = 
				onRequerente -> !onRequerente.hasRegistroCriminal();

		avaliar(requerente, creditCheck);

		avaliar(requerente, creditCheck.and(employmentCheck));

		avaliar(requerente, crimeCheck.and(employmentCheck));

		avaliar(requerente, crimeCheck.and(creditCheck).and(employmentCheck));
	}
}
