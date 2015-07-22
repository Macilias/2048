# 2048
2048 in ASCII Art

Like 2 play? You can run the game with:

mvn package exec:java

Welcome to the 2048 wiki!

All you need to play this game is a: 1. JAVA 1.8.0_45 jdk and 2. Maven 3.3.3

The Game has by now a simple ASCII Interface wich looks like that:

    |---------------------------|
    |    0 |    0 |    0 |    0 |
    |---------------------------|
    |    0 |    0 |    2 |    8 |
    |---------------------------|
    |    0 |    0 |    4 |   16 |
    |---------------------------|
    |    0 |    2 |   16 |  128 |
    |---------------------------|

The goal is to reach 2048 by adding equal ties. 
This can be done by moving all ties with 'a' (left), 'd' (right), 'w' (up) and 's' (down).

Of course you are free to add your own fancy UI by using the privided interface:

        public interface Game {

            Field moveLeft();

            Field moveRight();

            Field moveUp();

            Field moveDown();

            Field getField();

            boolean isOver();

            long getScore();
        }

Where the Field is a simple wrapper over a multi dimensional int[][] Array. The Field class can also easily be extended to provide serialization.

Have fun.
