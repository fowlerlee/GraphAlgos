## Debug and analyze with dlv

```
dlv test ./nqueens -- -test.run=TestQueens

gosource % dlv test ./nqueens -- -test.run=TestQueens

(dlv) b nqueens_test.go:10

```
