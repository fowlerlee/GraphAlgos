package gosource

import (
	"fmt"
)

// Input: n = 4
// Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]

func solveNQueens(n int) [][]string {
	board := make([][]string, n)
	// cols := len(board[0])
	rows := len(board)

	if solve(board, rows, n) {
		for row := range len(board) {
			fmt.Println(row)
		}
	}
	return board
}

func solve(board [][]string, row, n int) bool {
	if row >= n {
		return true
	}
	for col := range n {
		if isSafe(board, row, col, n) {
			board[row][col] = "Q"
			if solve(board, row+1, n) {
				return true
			}
			board[row][col] = "."
		}
	}
	return false
}

func isSafe(board [][]string, row, col, n int) bool {
	for i := 0; i < row; i++ {
		if board[i][col] == "Q" {
			return false
		}
		if col-1 >= 0 && board[row-i][col-1] == "Q" {
			return false
		}
		if col+1 < n && board[row-1][col+1] == "Q" {
			return false
		}
	}
	return true
}
