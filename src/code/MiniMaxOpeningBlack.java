package code;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MiniMaxOpeningBlack {

	private static final int a0=0, g0=1, b1=2, f1=3, c2=4, e2=5, a3=6, b3=7, c3=8, e3=9, f3=10, g3=11, c4=12, d4=13, e4=14, b5= 15, d5=16, f5=17, a6=18, d6=19, g6=20;
	private static int positions_evaluated = 0;
	private static int  minimax_estimate = 0;

	public int staticEvaluationOpening(char[] board){
		int w = 0, b = 0;
		for(char c : board){
			if(c == 'W')
				w++;
			else if(c == 'B')
				b++;
		}
		return w-b;
	}

	public List<char[]> allChildCombinations(char[] board, boolean player){
		char p = Utils.whichPlayer(player);
		List<char[]> res = new ArrayList<>();

		for(int i=0; i<board.length; i++){
			if(board[i] == 'x'){
				char[] tmp = board.clone();
				tmp[i] = p;
				if(Utils.isMill(i, tmp))
					Utils.removeOpponentPiece(tmp, res, player);
				else
					res.add(tmp);
			}
		}
		return res;
	}

	public char[] maxMin(char[] board, int ply) {
		if(ply == 0) {
			positions_evaluated++;
		}else if(ply > 0) {
			ply --;
			List<char[]> childNodes = allChildCombinations(board, true);
			char[] selectedMax = new char[board.length];
			int evaluation = Integer.MIN_VALUE;

			for(char[] child : childNodes) {
				char[] minChoice = minMax(child, ply);
				if(evaluation < staticEvaluationOpening(minChoice)) {
					evaluation = staticEvaluationOpening(minChoice);
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
			List<char[]> childNodes = allChildCombinations(board, false);
			char[] selectedMin = new char[board.length];
			int evaluation = Integer.MAX_VALUE;

			for(char[] child : childNodes) {
				char[] maxChoice = maxMin(child, ply);
				if(evaluation > staticEvaluationOpening(maxChoice)) {
					evaluation = staticEvaluationOpening(maxChoice);
					selectedMin = child;
				}
			}
			return selectedMin;
		}
		return board;
	}

	public static void main(String[] args) {
		String position = "";
		MiniMaxOpeningBlack open = new MiniMaxOpeningBlack();

		Scanner sc = new Scanner(System.in);

		System.out.print("input file : ");
		String inputName = sc.next();
		System.out.print("output file : ");
		String outputName = sc.next();
		System.out.print("ply (depth) : ");
		int ply = sc.nextInt();

		try{
			String path = System.getProperty("user.dir");
			File file = new File(path + "\\src\\code\\" + inputName);
			FileReader file_reader = new FileReader(file);
			OutputStream output = new FileOutputStream(path + "\\src\\code\\" + outputName);

			int cur = 0;
			while((cur = file_reader.read()) != -1){
				position += (char) cur;
			}

			char[] board = position.toCharArray();
			Utils.swapWB(board);
			char[] result = open.maxMin(board, ply);
			Utils.swapWB(result);

			System.out.println("Input Position: " + position);
			System.out.println("Ply(depth): " + ply);
			System.out.println("Board Position : " + new String(result));
			System.out.println("Positions evaluated by static estimation : " + open.positions_evaluated);
			System.out.println("MiniMax estimate : " + open.minimax_estimate);

			String res = new String(result);
			String out1 = "Input Position: " + position.toString() + "\n";
			String out2 = "Ply(depth): " + String.valueOf(ply) + "\n";
			String out3 = "Board Position : " + res + "\n";
			String out4 = "Positions evaluated by static estimation : " + String.valueOf(open.positions_evaluated) + "\n";
			String out5 = "MiniMax estimate : " + String.valueOf(open.minimax_estimate) + "\n";

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
