
/* Nome: Milena de Matos Siqueira, RA: 122.044 */
import java.util.Scanner;

public class Main {
	public int gridSize;
	public int grid[][];

	public Main(int gridSize) {
		this.gridSize = gridSize;
		this.grid = new int[gridSize][gridSize];
	}

	//0 indica um bloco livre, e -1 tem uma parede
	public void setBlock(int i, String input) {
		for(int j = 0; j < gridSize; j++)
			this.grid[i][j] = input.charAt(j) == '.' ? 0 : -1;
	}


	//verifica se é possível avançar para esse bloco: precisa estar dentro do grid e ser 0, pois -1 indica uma parede
	private boolean isSafe(int i, int j) {
		return (i >= 0 && i < gridSize && j >= 0
				&& j < gridSize && grid[i][j] == 0);
	}

	//método auxiliar para printar durante testes
	private void printGrid(int newGrid[][]) {
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				System.out.print(newGrid[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private int countPossiblePaths(int x, int y, int newGrid[][], int possiblePaths) {
		if (x == gridSize - 1 && y == gridSize - 1) {
			//chegou no bloco desejado t
			newGrid[x][y] = 1;
			// printGrid(newGrid);
			return possiblePaths + 1;
		}

		if (isSafe(x, y)) {
			if (newGrid[x][y] == 1) {
				return possiblePaths;
			}

			newGrid[x][y] = 1;

			//tentativa para baixo
			possiblePaths = countPossiblePaths(x + 1, y, newGrid, possiblePaths);

			//tentativa para direita
			possiblePaths = countPossiblePaths(x, y + 1, newGrid, possiblePaths);

			newGrid[x][y] = 0;

			return possiblePaths;
		}

		return possiblePaths;
	}

	private boolean canSolvePathAllDirections(int x, int y, int newGrid[][]) {
		if (x == gridSize - 1 && y == gridSize - 1) {
			//chegou no bloco desejado t
			newGrid[x][y] = 1;
			return true;
		}

		if (isSafe(x, y)) {
			if (newGrid[x][y] == 1) {
				return false;
			}

			newGrid[x][y] = 1;

			//tentativa para baixo
			if (canSolvePathAllDirections(x + 1, y, newGrid)) {
				return true;
			}

			//tentativa para direita
			if (canSolvePathAllDirections(x, y + 1, newGrid)) {
				return true;
			}

			//tentativa para cima
			if (canSolvePathAllDirections(x - 1, y, newGrid)) {
				return true;
			}

			//tentativa para esquerda
			if (canSolvePathAllDirections(x, y - 1, newGrid)) {
				return true;
			}

			newGrid[x][y] = 0;

			return false;
		}

		return false;
	}

	public void getPaths() {
		int newGrid[][] = new int[gridSize][gridSize];

		//caminhos com permissão para andar apenas pra direita e pra baixo
		int paths = countPossiblePaths(0, 0, newGrid, 0);

		if (paths == 0) {
			//verifica se é possível resolver com permissão para esquerda e para cima também
			boolean canSolve = canSolvePathAllDirections(0, 0, newGrid);
			System.out.print(canSolve ? "THE GAME IS A LIE" : "INCONCEIVABLE");
		} else {
			System.out.println(paths);
		}
	}

	public static void main(String args[]) {
		try {
			Scanner scanner = new Scanner(System.in);
			int gridSize = scanner.nextInt();
			//iniciando grid
			Main grid = new Main(gridSize);

			//lendo as entradas por linhas para criar cada bloco
			for (int i = 0; i < gridSize; i++) {
				String input = scanner.next();
				grid.setBlock(i, input);
			}

			grid.getPaths();
			scanner.close();
		} catch (Exception error) {
			System.out.println(error.getMessage());
		}
	}
}
// This code is contributed by Abhishek Shankhadhar
