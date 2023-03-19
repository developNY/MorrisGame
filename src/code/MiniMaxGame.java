package code;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MiniMaxGame {

    private static final int a0=0, g0=1, b1=2, f1=3, c2=4, e2=5, a3=6, b3=7, c3=8, e3=9, f3=10, g3=11, c4=12, d4=13, e4=14, b5= 15, d5=16, f5=17, a6=18, d6=19, g6=20;
    private static int positions_evaluated = 0;
    private static int minimax_estimate = 0;

    public List<char[]> moveMidEndGame(char[] board, boolean player){
        int cnt = 0;
        List<char[]> games;
        char p = Utils.whichPlayer(player);

        for(char c : board){
            if(c == p)
                cnt++;
        }
        if(cnt == 3)
            games = hopping(board, player);
        else
            games = moveToNeighbor(board, player);
        return games;
    }

    public List<char[]> hopping(char[] board, boolean player){
        char p = Utils.whichPlayer(player);
        List<char[]> res = new ArrayList<>();

        for(int i=0; i<board.length; i++){
            if(board[i] == p){
                for(int j=0; j<board.length; j++){
                    if(board[j] == 'x'){
                        char[] tmp = board.clone();
                        tmp[i] = 'x';
                        tmp[j] = p;
                        if(Utils.isMill(j, tmp))
                            Utils.removeOpponentPiece(tmp, res, player);
                        else
                            res.add(tmp);
                    }
                }
            }
        }
        return res;
    }

    public List<char[]> moveToNeighbor (char[] board, boolean player){
        char p = Utils.whichPlayer(player);
        List<char[]> list = new ArrayList<>();

        for(int i=0; i<board.length; i++){
            if(board[i] == p){
                int[] neighbors = Utils.makeNeighborList(i);
                for(int j : neighbors){
                    if(board[j] == 'x'){
                        char[] tmp = board.clone();
                        tmp[i] = 'x';
                        tmp[j] = p;
                        if(Utils.isMill(j, tmp))
                            Utils.removeOpponentPiece(tmp, list, player);
                        else
                            list.add(tmp);
                    }
                }
            }
        }
        return list;
    }

    public List<char[]> moveBlack(char[] board) {
        char[] tmp1 = board.clone();
        Utils.swapWB(tmp1);
        List<char[]> childNodes = moveMidEndGame(tmp1, true);
        List<char[]> list = new ArrayList<>();

        for(char[] c : childNodes) {
            char[] tmp2 = c.clone();
            Utils.swapWB(tmp2);
            list.add(tmp2);
        }
        return list;
    }

    public int staticEvaluationMidEnd(char[] board){
        int w = 0, b = 0;
        List<char[]> list = moveBlack(board);

        for(char c : board){
            if(c == 'W')
                w++;
            else if(c == 'B')
                b++;
        }
        if(b <= 2)
            return 10000;
        else if(w <= 2)
            return -10000;
        else if(list.size() == 0)
            return 10000;
        else
            return((1000*(w-b))-list.size());
    }

    public char[] maxMin(char[] board, int ply) {
        if(ply == 0) {
            positions_evaluated++;
        }else if(ply > 0) {
            ply --;
            List<char[]> childNodes = moveMidEndGame(board, true);
            char[] selectedMax = new char[board.length];
            int evaluation = Integer.MIN_VALUE;

            for(char[] child : childNodes) {
                char[] minChoice = minMax(child, ply);
                if(evaluation < staticEvaluationMidEnd(minChoice)) {
                    evaluation = staticEvaluationMidEnd(minChoice);
                    minimax_estimate = evaluation;
                    selectedMax = child;
                }
            }
            return selectedMax;
        }
        return board;
    }

    public char[] minMax(char[] board, int ply) {
        if(ply == 0)
            positions_evaluated++;
        else if(ply > 0) {
            ply--;
            List<char[]> childNodes = moveBlack(board);
            char[] selectedMin = new char[board.length];
            int evaluation = Integer.MAX_VALUE;

            for(char[] child : childNodes) {
                char[] maxChoice = maxMin(child, ply);
                if(evaluation > staticEvaluationMidEnd(maxChoice)) {
                    evaluation = staticEvaluationMidEnd(maxChoice);
                    selectedMin = child;
                }
            }
            return selectedMin;
        }
        return board;
    }

    public static void main(String[] args) {
        String position = "";
        MiniMaxGame game = new MiniMaxGame();

        Scanner sc = new Scanner(System.in);

        System.out.print("input file : ");
        String inputName = sc.next();
        System.out.print("output file : ");
        String outputName = sc.next();
        System.out.print("ply (depth) : ");
        int ply = sc.nextInt();

        try {
            String path = System.getProperty("user.dir");
            File file = new File(path + "\\src\\code\\" + inputName);
            FileReader file_reader = new FileReader(file);
            OutputStream output = new FileOutputStream(path + "\\src\\code\\" + outputName);

            int cur = 0;
            while ((cur = file_reader.read()) != -1) {
                position += (char) cur;
            }

            char[] board = position.toCharArray();
            char[] result = game.maxMin(board, ply);

            System.out.println("Input Position: " + position);
            System.out.println("Ply(depth): " + ply);
            System.out.println("Board Position : " + new String(result));
            System.out.println("Positions evaluated by static estimation : " + game.positions_evaluated);
            System.out.println("MiniMax estimate : " + game.minimax_estimate);

            String res = new String(result);
            String out1 = "Input Position: " + position.toString() + "\n";
            String out2 = "Ply(depth): " + String.valueOf(ply) + "\n";
            String out3 = "Board Position : " + res + "\n";
            String out4 = "Positions evaluated by static estimation : " + String.valueOf(game.positions_evaluated) + "\n";
            String out5 = "MiniMax estimate : " + String.valueOf(game.minimax_estimate) + "\n";

            output.write(out1.getBytes());
            output.write(out2.getBytes());
            output.write(out3.getBytes());
            output.write(out4.getBytes());
            output.write(out5.getBytes());

            output.close();
            file_reader.close();
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}