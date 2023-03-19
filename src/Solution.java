class Solution {
    public static int[][] gameOfLife(int[][] board) {
        //1. 살아있는 이웃이 2개미만인 live cell은 죽는다
        //2. 2-3명의 살아있는 이웃이 있는 live cell은 산다
        //3. 3개 초과 살아있는 이웃이 있는 live cell은 죽는다
        //4. 3개의 살아있는 이웃을 가진 died cell은 산다

//        int[][] tmp = board.clone();

        int[][] tmp = new int[board.length][board[0].length];
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                tmp[i][j] = board[i][j];
            }
        }



        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                board[i][j] = updateCell(tmp, i, j);
            }
        }

        return board;
    }

    public static int updateCell(int[][] board, int i, int j){
        int val = board[i][j];
        int live = checkLiveNeighbors(board, i, j);
        if(val == 1){
            if(live < 2)
                return 0;
            else if(live > 3)
                return 0;
        }else{
            if(live == 3)
                return 1;
        }
        return val;
    }

    public static int checkLiveNeighbors(int[][] board, int i, int j){
        int live = 0;

        if(i-1 >= 0 && i-1 < board.length && j-1 >=0 && j-1<board[0].length && board[i-1][j-1] == 1)
            live++;
        if(i-1 >= 0 && j >=0 && j<board[0].length && board[i-1][j] == 1)
            live++;
        if(i >= 0 && i<board.length && j-1 >=0 && board[i][j-1] == 1)
            live++;
        if(i-1 >= 0 && j+1<board[0].length && board[i-1][j+1] == 1)
            live++;
        if(i+1 < board.length && j-1 >=0 && board[i+1][j-1] == 1)
            live++;
        if(i+1 < board.length && j >= 0 && j<board[0].length && board[i+1][j] == 1)
            live++;
        if(j+1 < board[0].length && i < board.length && j+1 >= 0 && board[i][j+1] == 1)
            live++;
        if(i+1 < board.length && j+1 < board[0].length && board[i+1][j+1] == 1)
            live++;

        return live;
    }

    public static void main(String[] args) {
        int[][] input = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        int[][] output = gameOfLife(input);

        for(int i=0; i<output.length; i++){
            for(int j=0; j<output[0].length; j++){
                System.out.print(output[i][j] + " ");
            }
            System.out.println();
        }
    }
}
// int[] tmp1 = [1 0 -1];
// for(int k=0;k<tmp1.length;k++) {
//     for(int  l=0;l<tmp1.length;l++)
//         if(i+k >= 0 && i+k<board.length && j+1)
//     int val = board[i+k][j+l]

//         i+k
//         j+k
//         //k represent iterative of another for loop

//         tmp1[i][j]