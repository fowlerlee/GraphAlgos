def solve_n_queens(n):
    def is_safe(board, row, col):
        # Check for attacks in the same row, column, or diagonals
        for i in range(row):
            if board[i][col] == 1:
                return False
            if col - i >= 0 and board[row - i][col - i] == 1:
                return False
            if col + i < n and board[row - i][col + i] == 1:
                return False
        return True

    def solve(board, row):
        if row >= n:
            return True
        for col in range(n):
            if is_safe(board, row, col):
                board[row][col] = 1
                if solve(board, row + 1):
                    return True
                board[row][col] = 0
        return False

    board = [[0] * n for _ in range(n)]
    if solve(board, 0):
        for row in board:
            print(row)
    else:
        print("No solution found")

if __name__ == '__main__':
    n = 4
    solve_n_queens(n)