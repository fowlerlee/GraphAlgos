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

func TestHowSum(t *testing.T) {
	target := 7
	numbers := []int{5, 3, 4}
	result := howSum(target, numbers)
	expected := []int{4, 3}
	if !reflect.DeepEqual(result, expected) {
		t.Fatalf("failed, expected: %v, result: %v", expected, result)
	}
}

func TestBestSum(t *testing.T) {
	target := 17
	numbers := []int{5, 6, 1, 2, 4, 3, 7, 10}
	result := bestSum(target, numbers, nil)
	expected := []int{10, 7}
	if !reflect.DeepEqual(result, expected) {
		t.Fatalf("failed, expected: %v, result: %v", expected, result)
	}
}

func TestRecurseConstruct(t *testing.T) {
	target := "abcdef"
	wordBank := []string{"ab", "abc", "cd", "def", "abcd"}
	result := recurseConstruct(target, wordBank, nil)
	expect := true
	if !reflect.DeepEqual(result, expect) {
		t.Fatalf("failed, expected: %v, result: %v", expect, result)
	}

	target = "sledgehammer"
	wordBank = []string{"sledge", "sl", "geh", "hammer", "mer"}
	result = recurseConstruct(target, wordBank, nil)
	expect = true
	if !reflect.DeepEqual(result, expect) {
		t.Fatalf("failed, expected: %v, result: %v", expect, result)
	}

	target = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeX"
	wordBank = []string{"eee", "eeeeeee", "eeeeeeeeee", "eeeeeeeeeee", "eeeeeeeeeeeeeee"}
	result = recurseConstruct(target, wordBank, nil)
	expect = false
	if !reflect.DeepEqual(result, expect) {
		t.Fatalf("failed, expected: %v, result: %v", expect, result)
	}
}
