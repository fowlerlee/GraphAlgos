package dynamicprog

func recurseConstruct(target string, pieces []string, memo map[string]bool) bool {
	if memo == nil {
		(memo) = make(map[string]bool, len(target))
	}
	if value, exists := (memo)[target]; exists {
		return value
	}
	if target == "" {
		return true
	}
	for _, piece := range pieces {
		if len(piece) <= len(target) && target[0:len(piece)] == piece {
			if recurseConstruct(target[len(piece):], pieces, memo) {
				(memo)[target] = true
				return true
			}
		}
	}
	(memo)[target] = false
	return false
}

// 7, [2,1,4,3,5] -> [2,5]
func bestSum(target int, array []int) []int {
	if target == 0 { return []int{} }
	if target < 0 { return nil }
	var shortestCombination []int
	for _, val := range array {
		remainder := target - val
		remainderCombination := bestSum(remainder, array)
		if remainderCombination != nil {
			remainderCombination = append(remainderCombination, val)
			if shortestCombination == nil || len(remainderCombination) < len(shortestCombination) {
				shortestCombination = remainderCombination
			}
		}
	}
	return shortestCombination
}
