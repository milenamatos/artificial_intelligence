
/* Nome: Milena de Matos Siqueira, RA: 122.044 */
import java.util.Scanner;
import java.text.DecimalFormat;
import java.math.RoundingMode;

public class Main {
	public int iterations;
    public Scanner scanner;

	public Main(int iterations, Scanner scanner) {
		this.iterations = iterations;
        this.scanner = scanner;
	}

    float getResult(int bits, int position, float probability, int[] firstSubject, int[] secondSubject, int[] goalSubject) {
        int[] firstGenerated = new int[bits];
        int[] secondGenerated = new int[bits];

        int index = position - 1;
        float firstProbability = 1, secondProbability = 1;

        for (int i = 0; i < bits; i++) {
            firstGenerated[i] = (i <= index) ? firstSubject[i] : secondSubject[i];
            secondGenerated[i] = (i <= index) ? secondSubject[i] : firstSubject[i];

            firstProbability *= (firstGenerated[i] == goalSubject[i]) ? (1 - probability) : probability;
            secondProbability *= (secondGenerated[i] == goalSubject[i]) ? (1 - probability) : probability;
        }

        float sum = firstProbability + secondProbability;
        float multi = firstProbability * secondProbability;

        return sum - multi;
    }

    void startIterations() {
        int i;
        float[] results = new float[iterations];

        for(i = 0; i < iterations; i++) {
            int bits = scanner.nextInt();
            int position = scanner.nextInt();
            float probability = Float.parseFloat(scanner.next());

            String first = scanner.next();
            String second = scanner.next();
            String goal = scanner.next();

            int[] firstSubject, secondSubject, goalSubject;
            firstSubject = new int[bits];
            secondSubject = new int[bits];
            goalSubject = new int[bits];

            for (int j = 0; j < bits; j++) {
                firstSubject[j] = Character.getNumericValue(first.charAt(j));
                secondSubject[j] = Character.getNumericValue(second.charAt(j));
                goalSubject[j] = Character.getNumericValue(goal.charAt(j));
            }

            results[i] = getResult(bits, position, probability, firstSubject, secondSubject, goalSubject);
        }

        DecimalFormat df = new DecimalFormat("#.#######");
        df.setRoundingMode(RoundingMode.FLOOR);

        for(i = 0; i < iterations; i++) {
            System.out.println(df.format(results[i]));
        }
    }

	public static void main(String args[]) {
		try {
			Scanner scanner = new Scanner(System.in);
			int iterations = scanner.nextInt();
			Main process = new Main(iterations, scanner);
            process.startIterations();

			scanner.close();
		} catch (Exception error) {
			System.out.println(error.getMessage());
		}
	}
}
