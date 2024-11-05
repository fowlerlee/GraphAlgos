package main

import "fmt"

type Node struct {
	s int
	t chan string
	x string
}

func cool(node *Node) {
	node.s = 5
	node.t <- "value"
}

func main() {
	fmt.Println("hello")

	node := new(Node)
	node.s = 2
	node.x = "A"
	node.t = make(chan string, 1)

	cool(node)

	fmt.Printf("rune: %v \n", node.x)
}
