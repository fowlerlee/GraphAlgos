package dynamicprog

import (
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
