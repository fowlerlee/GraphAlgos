package scratch

import (
	"fmt"
)

// Solves the N-Queens problem and prints all solutions.
func solveNQueens(n int) {
	board := make([][]rune, n)
	for i := range board {
		board[i] = make([]rune, n)
		for j := range board[i] {
			board[i][j] = '.' // Empty cell
		}
	}
	var results [][]string
	backtrack(board, 0, &results)
	fmt.Printf("Found %d solutions\n", len(results))
	for i, solution := range results {
		fmt.Printf("Solution %d:\n", i+1)
		for _, line := range solution {
			fmt.Println(line)
		}
		fmt.Println()
	}
}

// Backtracking function to explore all valid placements.
func backtrack(board [][]rune, row int, results *[][]string) {
	n := len(board)
	if row == n {
		// Base case: A valid solution is found.
		*results = append(*results, formatBoard(board))
		return
	}

	for col := 0; col < n; col++ {
		// Check if placing a queen here is valid.
		if isValid(board, row, col) {
			// Place the queen.
			board[row][col] = 'Q'

			// Recur to place queens in the next row.
			backtrack(board, row+1, results)

			// Backtrack: Remove the queen to explore other options.
			board[row][col] = '.'
		}
	}
}

// Checks if placing a queen at board[row][col] is valid.
func isValid(board [][]rune, row, col int) bool {
	n := len(board)

	// Check the column for another queen.
	for i := 0; i < row; i++ {
		if board[i][col] == 'Q' {
			return false
		}
	}

	// Check the upper-left diagonal.
	for i, j := row-1, col-1; i >= 0 && j >= 0; i, j = i-1, j-1 {
		if board[i][j] == 'Q' {
			return false
		}
	}

	// Check the upper-right diagonal.
	for i, j := row-1, col+1; i >= 0 && j < n; i, j = i-1, j+1 {
		if board[i][j] == 'Q' {
			return false
		}
	}

	return true
}

// Converts the board to a slice of strings for pretty printing.
func formatBoard(board [][]rune) []string {
	formatted := make([]string, len(board))
	for i := range board {
		formatted[i] = string(board[i])
	}
	return formatted
}

