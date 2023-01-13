package src;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Board implements Ilayout, Cloneable {
    private static final int dim = 5;

    public char board[][];
    private int zeroI, zeroJ;
    public Board() {
        board = new char[dim][dim];
    }


    public Board(String str) throws IllegalStateException {
        if (str.length() != (dim * dim) - 8) // verifica se a board esta valida
            throw new IllegalStateException("Invalid arg in Board constructor");

        board = new char[dim][dim];

        int si = 0;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if((i <= 1 && j >= 3) || (i >= 3 && j <= 1)) // restringe a board
                    board[i][j] = '\0';
                else board[i][j] = str.charAt(si++); // coloca peca da string para dentro da board
            }
        }
    }

    public String toString() {
        String a = "";
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (board[i][j] == '\0')
                    a += " ";
                else
                    a+=board[i][j];
            }
            a += "\n";
        }
        return a;
    }

    public boolean equals(Object o) {
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (this.board[i][j] != ((Board) o).board[i][j]) return false;
            }
        }
        return true;
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(board);
        return result;
    }

    public Board clone() {
        Board clone = new Board();
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                clone.board[i][j] = this.board[i][j];
                if (this.board[i][j] == 'E') { // quando igual E guarda os E da board atual
                    zeroI = i;
                     zeroJ = j;
                }
            }
        }
        return clone;
    }

    @Override
    public List<Ilayout> children() {
        List<Ilayout> children = new ArrayList<>();
        Board clone = this.clone();

        if (this.zeroI - 1 >= 0) { // verifica do empty para cima e move a peca para baixo
            if(clone.board[this.zeroI - 1][this.zeroJ] == 'B') {
                clone.board[this.zeroI][this.zeroJ] = clone.board[this.zeroI - 1][this.zeroJ]; // redefine a peca empty e vai buscar o valor da peca adjante B
                clone.board[this.zeroI - 1][this.zeroJ] = 'E'; // peca de cima fica empty
            }
            else if(this.zeroI - 2 >= 0 && clone.board[this.zeroI - 2][this.zeroJ] == 'B') { // jump da peca de cima para baixo
                clone.board[this.zeroI][this.zeroJ] = clone.board[this.zeroI - 2][this.zeroJ]; // redefine a peca empty e vai buscar o valor da peca B
                clone.board[this.zeroI - 2][this.zeroJ] = 'E'; // peca de cima fica empty
            }
            children.add(clone);
        }
        if (this.zeroI + 1 < dim) { // empty para baixo
            clone = this.clone();
            if(clone.board[this.zeroI + 1][this.zeroJ] == 'R') {
                clone.board[this.zeroI][this.zeroJ] = clone.board[this.zeroI + 1][this.zeroJ];
                clone.board[this.zeroI + 1][this.zeroJ] = 'E';
            }
            else if(this.zeroI + 2 < dim && clone.board[this.zeroI + 2][this.zeroJ] == 'R') { // jump para baixo
                clone.board[this.zeroI][this.zeroJ] = clone.board[this.zeroI + 2][this.zeroJ];
                clone.board[this.zeroI + 2][this.zeroJ] = 'E';
            }
            children.add(clone);
        }
        if (this.zeroJ - 1 >= 0) { // empty para esquerda
            clone = this.clone();
            if(clone.board[this.zeroI][this.zeroJ - 1] == 'B') {
                clone.board[this.zeroI][this.zeroJ] = clone.board[this.zeroI][this.zeroJ - 1];
                clone.board[this.zeroI][this.zeroJ - 1] = 'E';
            }
            else if(this.zeroJ - 2 >= 0 && clone.board[this.zeroI][this.zeroJ - 2] == 'B') {
                clone.board[this.zeroI][this.zeroJ] = clone.board[this.zeroI][this.zeroJ - 2];
                clone.board[this.zeroI][this.zeroJ - 2] = 'E';
            }
            children.add(clone);
        }
        if (this.zeroJ + 1 < dim) { // empty para direita
            clone = this.clone();
            if(clone.board[this.zeroI][this.zeroJ + 1] == 'R') {
                clone.board[this.zeroI][this.zeroJ] = clone.board[this.zeroI][this.zeroJ + 1];
                clone.board[this.zeroI][this.zeroJ + 1] = 'E';
            }
            else if(this.zeroJ + 2 < dim && clone.board[this.zeroI][this.zeroJ + 2] == 'R') {
                clone.board[this.zeroI][this.zeroJ] = clone.board[this.zeroI][this.zeroJ + 2];
                clone.board[this.zeroI][this.zeroJ + 2] = 'E';
            }
            children.add(clone);
        }
        return children;
    }


    @Override
    public boolean isGoal(Ilayout l) { return this.equals(l); }

    @Override
    public double getG() {return 1; }
}