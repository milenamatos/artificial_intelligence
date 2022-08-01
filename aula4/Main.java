/* Nome: Milena de Matos Siqueira, RA: 122.044 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static class State {
        // m = missionários, c = canibais
        int mLeft, mRight, cLeft, cRight, direction;
        State parent;
        int maxPeople = 5;

        int[][] moves = { { 1, 0 }, { 2, 0 }, { 0, 1 }, { 0, 2 }, { 1, 1 } };

        public State(int mLeft, int mRight, int cLeft, int cRight, int direction, State parent) {
            this.mLeft = mLeft;
            this.mRight = mRight;
            this.cLeft = cLeft;
            this.cRight = cRight;
            this.direction = direction; // 0 = esquerda, 1 = direita
            this.parent = parent;
        }

        public boolean isValid() {
            if (this.mLeft < 0 || this.mLeft > maxPeople || this.cLeft < 0 || this.cLeft > maxPeople) {
                return false;
            }

            if (this.mRight < 0 || this.mRight > maxPeople || this.cRight < 0 || this.cRight > maxPeople) {
                return false;
            }

            if (this.cLeft > this.mLeft && this.mLeft > 0) {
                return false;
            }

            if (this.cRight > this.mRight && this.mRight > 0) {
                return false;
            }

            return true;
        }

        public boolean isFinal() {
            return this.mRight == 0 && this.cRight == 0;
        }

        public boolean wasVisited(List<State> visited) {
            return visited.stream().anyMatch(state -> state.equals(this));
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }

            State state = (State) o;

            return this.mLeft == state.mLeft && this.cLeft == state.cLeft
                && this.mRight == state.mRight && this.cRight == state.cRight
                && this.direction == state.direction;
        }

        public List<State> getChildren() {
            List<State> children = new ArrayList<State>();

            for (int i = 0; i < this.moves.length; i++) {
                int m = moves[i][0];
                int c = moves[i][1];
                State newState;

                if (this.direction == 1) {
                    newState = new State(this.mLeft + m, this.mRight - m, this.cLeft + c, this.cRight - c, 0, this);
                } else {
                    newState = new State(this.mLeft - m, this.mRight + m, this.cLeft - c, this.cRight + c, 1, this);
                }

                if (newState.isValid()) {
                    children.add(newState);
                }
            }

            return children;
        }

        public String toString() {
            StringBuilder text = new StringBuilder(this.mLeft + " M e " + this.cLeft + " C na esquerda / "
                    + this.mRight + " M e " + this.cRight + " C na direita");

            return text.toString();
        }

        public void printSolution() {
            Deque<State> resultList = new ArrayDeque<State>();
            resultList.add(this);

            State currentState = this;

            //percorrendo os estados pais e empilhando até chegar no inicial para poder printar
            while(currentState.parent != null) {
                resultList.addFirst(currentState.parent);
                currentState = currentState.parent;
            }

            while (resultList.peek() != null) {
                State state = resultList.pop();
                System.out.println(state.toString());
                System.out.println(state.direction == 1 ? "Indo para esquerda" : "Indo para direita");
            }

            System.out.println("----------");
        }
    }

    public static void main(String args[]) {
        State initial = new State(0, 3, 0, 3, 1, null);
        Queue<State> queue = new LinkedList<State>();
        queue.add(initial);

        List<State> visited = new ArrayList<State>();
        visited.add(initial);

        while (queue.peek() != null) {
            State current = queue.poll();
            if (current.isFinal()) {
                current.printSolution();
                break;
            }

            List<State> children = current.getChildren();

            for (int i = 0; i < children.size(); i++) {
                State possibleState = children.get(i);

                if (!possibleState.wasVisited(visited)) {

                    queue.add(possibleState);
                    visited.add(possibleState);
                }
            }
        }
    }
}