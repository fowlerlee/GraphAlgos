package dynamicprog

import (
	"reflect"
	"testing"
)

func TestCanConstruct(t *testing.T) {
	target := "abcdef"
	wordBank := []string{"ab", "abc", "cd", "def", "abcd"}
	result := canConstruct(target, wordBank)
	expected := true
	if result != expected {
		t.Fatalf("failed 1st the test!")
	}

	target = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef"
	wordBank = []string{"ee", "eeeeee", "eeeeeeeeeee", "eeeeeeeeeeeee", "eeeeeeeeee", "eeeeeeeeeeee"}
	result = canConstruct(target, wordBank)
	expected = false
	if result != expected {
		t.Fatalf("failed 2nd the test!")
	}
}

func TestBestSum(t *testing.T) {
	target := 7
	numbers := []int{5, 3, 4}
	result := bestSum(target, numbers)
	expected := []int{4, 3}
	if !reflect.DeepEqual(result, expected) {
		t.Fatalf("failed 1st test")
	}
}
