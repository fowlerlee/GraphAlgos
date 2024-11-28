package dynamicprog

// "fmt"

func canConstruct(target string, wordBank []string) bool {
	table := make([]bool, len(target)+1)
	table[0] = true
	for i := 0; i <= len(target); i++ {
		if table[i] {
			for _, v := range wordBank {
				// fmt.Println(target[i : i+len(v)])
				if len(target) >= (i + len(v)) && target[i:(i+len(v))] == v {
					table[i+len(v)] = true
				}
			}
		}
	}
	return table[len(target)]
}
