/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package n.queens.problem;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author Verly
 */
public class NQueensProblem {

    /**
     * @param args the command line arguments
     */
    final int N = 15;

	
	void printSolution(int board[][])
	{
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
				System.out.print(" " + board[i][j]
								+ " ");
			System.out.println();
		}
	}
        
	boolean isSafe(int board[][], int row, int col)
	{
		int i, j;

		/* Check this row on left side */
		for (i = 0; i < col; i++)
			if (board[row][i] == 1)
				return false;

		/* Check upper diagonal on left side */
		for (i=row, j=col; i>=0 && j>=0; i--, j--)
			if (board[i][j] == 1)
				return false;

		/* Check lower diagonal on left side */
		for (i=row, j=col; j>=0 && i<N; i++, j--)
			if (board[i][j] == 1)
				return false;

		return true;
	}

	/* A recursive utility function to solve N
	Queen problem */
	boolean solveNQUtil(int board[][], int col)
	{
		/* base case: If all queens are placed
		then return true */
		if (col >= N)
			return true;

		/* Consider this column and try placing
		this queen in all rows one by one */
		for (int i = 0; i < N; i++)
		{
			/* Check if queen can be placed on
			board[i][col] */
			if (isSafe(board, i, col))
			{
				/* Place this queen in board[i][col] */
				board[i][col] = 1;

				/* recur to place rest of the queens */
				if (solveNQUtil(board, col + 1) == true)
					return true;

				/* If placing queen in board[i][col]
				doesn't lead to a solution then
				remove queen from board[i][col] */
				board[i][col] = 0; // BACKTRACK
			}
		}

		/* If queen can not be place in any row in
		this colum col, then return false */
		return false;
	}

	boolean solveNQ()
	{
		int[][] board = new int[N][N];
                board = board(N);

		if (solveNQUtil(board, 0) == false)
		{
			System.out.print("Solusi tidak ada");
			return false;
		}

		printSolution(board);
		return true;
	}
        
        public int[][] board(int n){
            int[][] brd = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    brd[i][j]=0;
                }
            }
            return brd;
        }

	// driver program to test above function
	public static void main(String args[])
	{
                long startTime = System.currentTimeMillis();
		NQueensProblem Queen = new NQueensProblem();
		Queen.solveNQ();
                long endTime   = System.currentTimeMillis();
                NumberFormat formatter = new DecimalFormat("#0.00000");
                System.out.println("Execution time is " + formatter.format((endTime - startTime) / 1000d) + " seconds");
	}
}
