package src;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        BestFirst alg = new BestFirst();

       // Board Board1 = new Board(sc.next());
       // Board Board2 = new Board(sc.next());

        //List<Ilayout> children = Board1.children();
       // System.out.println(children.get(0).equals(Board1));

        Board main = new Board(sc.next());
        char temp = main.board[0][0];
        main.board[0][0] = main.board[2][1];
        main.board[2][1] = temp;
        System.out.println(main.toString());

       /* if (Board1.equals(Board2))
            System.out.println("yes");
        else
            System.out.println("nope");

        if(children.get(0).equals(Board1)){
            System.out.println("Sao iguais");
        } else  System.out.println("Sao diferentes");
        */

    }
 /*
       Iterator<BestFirst.State> it = alg.solve(new Board(sc.next()), new Board(sc.next()));
        if (it == null) System.out.println("No solution found");
        else {
            while (it.hasNext()) {
                BestFirst.State i = it.next();
                if (!it.hasNext()) System.out.println((int)i.getG());
            }
        }
        sc.close();
    }*/
}
