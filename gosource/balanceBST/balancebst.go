package balancebst

// Input: root = [1,null,2,null,3,null,4,null,null]
// Output: [2,1,3,null,null,null,4]
// balanced if the depth of the two subtrees of every node never differs by more than 1.
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func checkBalanced(node *TreeNode) bool {
	for node.Left != nil && node.Right != nil {
		if dfs(node.Left) == dfs(node.Right) {

		}
	}
	return true
}

func dfs(node *TreeNode) int {
	
	dfs(node.Left) 
	return 1
}

func balanceBST(root *TreeNode) *TreeNode {
	for root.Left != nil && root.Right != nil {

	}
	return &TreeNode{}
}
