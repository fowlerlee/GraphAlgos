package gosource

import (
	"fmt"
	"reflect"
	"testing"
)

func TestQueens(t *testing.T) {
	fmt.Println("NQueens solution")
	result := solveNQueens(4)
	expected := [][]string{{".Q..", "...Q", "Q...", "..Q."}, {"..Q.", "Q...", "...Q", ".Q.."}}
	if !reflect.DeepEqual(result, expected) {
		fmt.Errorf("failed test with %v != %v", result, expected)
	}
}
