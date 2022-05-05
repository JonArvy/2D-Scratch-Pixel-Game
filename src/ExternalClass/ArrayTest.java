package ExternalClass;

public class ArrayTest {
    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0}};
        
        int[][] clipboard = {{1, 1},
                             {1, 1}};
        
        int posX = 1;
        int posY = 1;
        
        for (int y = 0; y < clipboard.length; y++) {
            for (int x = 0; x < clipboard[y].length; x++) {
                board[posY + y][posX + x] = clipboard[y][x];
            }
        }
        
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                System.out.print(board[y][x]);
            }
            System.out.println();
        }
    }
}
