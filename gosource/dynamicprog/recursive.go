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
func bestSum(target int, array []int, memo map[int]([]int)) []int {
	if target == 0 {
		return []int{}
	}
	if memo == nil {
		memo = make(map[int]([]int), 0)
	}
	if memo[target] != nil {
		return memo[target]
	}
	var shortestCombination []int
	for _, val := range array {
		remainder := target - val
		if remainder >= 0 {
			remainderCombination := bestSum(remainder, array, memo)
			if remainderCombination != nil {
				remainderCombination = append(remainderCombination, val)
				if shortestCombination == nil || len(remainderCombination) < len(shortestCombination) {
					shortestCombination = remainderCombination
					memo[target] = shortestCombination
				}
			}
		}
	}
	memo[target] = shortestCombination
	return shortestCombination
}
