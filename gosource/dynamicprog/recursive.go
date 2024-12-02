package dynamicprog

func recurseConstruct(target string, pieces []string, memo map[string]bool) bool {
	if memo == nil {
		(memo) = make(map[string]bool,len(target))
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
