package code;

import java.util.List;

public class Utils {
    private static final int a0=0, g0=1, b1=2, f1=3, c2=4, e2=5, a3=6, b3=7, c3=8, e3=9, f3=10, g3=11, c4=12, d4=13, e4=14, b5= 15, d5=16, f5=17, a6=18, d6=19, g6=20;

    public static char whichPlayer(boolean player){
        char p;

        if(player)
            p = 'W';
        else
            p='B';

        return p;
    }

    public static boolean isMill(int idx, char[] board){
        char c = board[idx];
        if(c=='W' || c=='B'){
            switch(idx){
                case a0: if((board[a3] == c && board[a6] == c) || (board[b1] == c && board[c2]== c)) return true;
                else return false;
                case b1: if((board[a0] == c && board[c2] == c) || (board[b3] == c && board[b5]== c)) return true;
                else return false;
                case c2: if((board[c3] == c && board[c4] == c) || (board[a0] == c && board[b1]== c)) return true;
                else return false;
                case g0: if((board[e2] == c && board[f1] == c) || (board[g6] == c && board[g3]== c)) return true;
                else return false;
                case f1: if((board[e2] == c && board[g0] == c) || (board[f5] == c && board[f3]== c)) return true;
                else return false;
                case e2: if((board[f1] == c && board[g0] == c) || (board[e4] == c && board[e3]== c)) return true;
                else return false;
                case a3: if((board[a0] == c && board[a6] == c) || (board[b3] == c && board[c3]== c)) return true;
                else return false;
                case b3: if((board[a3] == c && board[c3] == c) || (board[b5] == c && board[b1]== c)) return true;
                else return false;
                case c3: if((board[a3] == c && board[b3] == c) || (board[c4] == c && board[c2]== c)) return true;
                else return false;
                case e3: if((board[e4] == c && board[e2] == c) || (board[f3] == c && board[g3]== c)) return true;
                else return false;
                case f3: if((board[e3] == c && board[g3] == c) || (board[f5] == c && board[f1]== c)) return true;
                else return false;
                case g3: if((board[e3] == c && board[f3] == c) || (board[g6] == c && board[g0]== c)) return true;
                else return false;
                case a6: if((board[b5] == c && board[c4] == c) || (board[a3] == c && board[a0]== c) || (board[d6] == c && board[g6]== c)) return true;
                else return false;
                case b5: if((board[a6] == c && board[c4] == c) || (board[b3] == c && board[b1]== c) || (board[d5] == c && board[f5]== c)) return true;
                else return false;
                case c4: if((board[b5] == c && board[a6] == c) || (board[c3] == c && board[c2]== c) || (board[d4] == c && board[e4]== c)) return true;
                else return false;
                case d6: if((board[d5] == c && board[d4] == c) || (board[a6] == c && board[g6]== c)) return true;
                else return false;
                case g6: if((board[g3] == c && board[g0] == c) || (board[a6] == c && board[d6]== c) || (board[f5] == c && board[e4]== c)) return true;
                else return false;
                case f5: if((board[g6] == c && board[e4] == c) || (board[b5] == c && board[d5]== c) || (board[f3] == c && board[f1]== c)) return true;
                else return false;
                case e4: if((board[c4] == c && board[d4] == c) || (board[e3] == c && board[e2]== c) || (board[f5] == c && board[g6]== c)) return true;
                else return false;
                case d5: if((board[d6] == c && board[d4] == c) || (board[b5] == c && board[f5]== c)) return true;
                else return false;
                case d4: if((board[e4] == c && board[c4] == c) || (board[d6] == c && board[d5]== c)) return true;
                else return false;
            }
        }
        return false;
    }

    public static List<char[]> removeOpponentPiece(char[] board, List<char[]> list, boolean player){
        char p = Utils.whichPlayer(!player);
        for(int i=0; i<board.length; i++){
            if(board[i] == p){
                if(!Utils.isMill(i, board)) {
                    char[] tmp = board.clone();
                    tmp[i] = 'x';
                    list.add(tmp);
                }
            }
        }
        if(list.isEmpty()) {
            char tmp[] = board.clone();
            list.add(tmp);
        }

        return list;
    }

    public static char[] swapWB(char[] board){
        for(int i=0; i<board.length; i++){
            if(board[i] == 'W'){
                board[i] = 'B';
            }else if(board[i] == 'B'){
                board[i] = 'W';
            }
        }
        return board;
    }

    public static int[] makeNeighborList(int idx){
        int[] neighbors;
        switch(idx){
            case a0: neighbors = new int[]{a3,b1,g0}; return neighbors;
            case b1: neighbors = new int[]{a0,b3,c2,f1}; return neighbors;
            case c2: neighbors = new int[]{e2,b1,c3}; return neighbors;
            case a3: neighbors = new int[]{a0,b3,a6}; return neighbors;
            case b3: neighbors = new int[]{a3,b1,c3,b5}; return neighbors;
            case c3: neighbors = new int[]{b3,c2,c4}; return neighbors;
            case c4: neighbors = new int[]{b5,c3,d4}; return neighbors;
            case b5: neighbors = new int[]{a6,b3,c4,d5}; return neighbors;
            case a6: neighbors = new int[]{a3,b5,d6}; return neighbors;
            case d6: neighbors = new int[]{a6,d5,g6}; return neighbors;
            case d5: neighbors = new int[]{d6,b5,d4,f5}; return neighbors;
            case d4: neighbors = new int[]{c4,d5,e4}; return neighbors;
            case e4: neighbors = new int[]{d4,f5,e3}; return neighbors;
            case f5: neighbors = new int[]{d5,g6,e4,f3}; return neighbors;
            case g6: neighbors = new int[]{d6,f5,g3}; return neighbors;
            case e3: neighbors = new int[]{e4,f3,e2}; return neighbors;
            case e2: neighbors = new int[]{e3,c2,f1}; return neighbors;
            case f1: neighbors = new int[]{b1,e2,f3,g0}; return neighbors;
            case g0: neighbors = new int[]{a0,f1,g3}; return neighbors;
            case f3: neighbors = new int[]{e3,f1,f5,g3}; return neighbors;
            case g3: neighbors = new int[]{g6,f3,g0}; return neighbors;
            default: neighbors = null;	return neighbors;
        }
    }
}
