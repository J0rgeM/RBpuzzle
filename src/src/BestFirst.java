package src;
import java.util.*;

class BestFirst {
    protected Queue<State> abertos;
    private Map<Ilayout, State> fechados;
    private State actual;
    private Ilayout objective;

    static class State {
        private Ilayout layout;
        private State father;
        private double g;

        public State(Ilayout l, State n) {
            layout = l;
            father = n;
            if (father != null)
                g = father.g + l.getG();
            else
                g = 0.0;
        }

        public String toString() {
            return layout.toString();
        }

        public double getG() {
            return g;
        }

        public int hashCode() {
            return toString().hashCode();
        }

        public boolean equals(Object o) {
            if (o == null)
                return false;
            if (this.getClass() != o.getClass())
                return false;
            State n = (State) o;
            return this.layout.equals(n.layout); // verifica se as boards (layout) sao iguais
        }
    }

    final private List<State> sucessores(State n) {
        List<State> sucs = new ArrayList<>();
        List<Ilayout> children = n.layout.children(); // chama a funcao do board que  retorna todos os filhos
        
        for (Ilayout e : children) {
            if ((n.father == null || !e.equals(n.father.layout)) && !fechados.containsKey(e)) { // verifica se o atual nao e igual ao pai e se ele ainda nao existe nos fechados
                State nn = new State(e, n);
                sucs.add(nn);
            }
        }
        return sucs;
    }

    final public Iterator<State> solve(Ilayout s, Ilayout goal) {
        objective = goal;
        abertos = new PriorityQueue<>(10, (s1, s2) -> (int) Math.signum(s1.getG() - s2.getG())); // define uma prioridade
        fechados = new HashMap<>();
        abertos.add(new State(s, null));
        List<State> sucs;
        LinkedList<State> list = new LinkedList<>();

        while (true) {
            if (abertos.isEmpty()) {
                System.out.println("No solution has been found");
                return null;
            }
            actual = abertos.remove();
            if (actual.layout.equals(objective)) {
                while (actual.father != null) {
                    list.addFirst(actual);
                    actual = actual.father;
                }
                list.addFirst(actual);
                return list.iterator();
            }
            sucs = sucessores(actual);
            fechados.put(actual.layout, actual);
            for (State child : sucs)
                abertos.add(child);
        }
    }
}