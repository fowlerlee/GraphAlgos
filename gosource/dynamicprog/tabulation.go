package dynamicprog

func canConstruct(target string, wordBank []string) bool {
	table := make([]bool, len(target)+1)
	table[0] = true
	for i := 0; i <= len(target); i++ {
		if table[i] {
			for _, v := range wordBank {
				if len(target) >= (i+len(v)) && target[i:(i+len(v))] == v {
					table[i+len(v)] = true
				}
			}
		}
	}
	return table[len(target)] 
}

func howSum(targetSum int, numbers []int) []int {
	table := make([][]int, targetSum+1)
	table[0] = make([]int, 0)
	for i := 0; i <= targetSum; i++ {
		if table[i] != nil {
			for _, num := range numbers {
				if targetSum >= (i + num) {
					if table[i+num] == nil {
						table[i+num] = make([]int, 0)
					}
					table[i+num] = append(table[i+num], num)
				}
			}
		}
	}
	return table[targetSum]
}
