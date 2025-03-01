package lrucache


type LRUCache struct {
    mp map[int]*listNode
    top, bottom *listNode
    capacity int
}

type listNode struct {
    key,value int
    prev, next *listNode
}

func Constructor(capacity int) LRUCache {
    top := &listNode{}
    bottom := &listNode{}

    bottom.next = top
    top.prev = bottom
    return LRUCache{
        mp: make(map[int]*listNode, capacity),
        top: top,
        bottom: bottom,
        capacity: capacity,
    }
}

func (this *LRUCache) MoveNodeToTop(node *listNode) {
    node.prev.next = node.next
	node.next.prev = node.prev

	node.prev = this.top.prev
	this.top.prev.next = node
	this.top.prev = node
	node.next = this.top
}

func (this *LRUCache) AddNodeToTop(node *listNode) {
    node.prev = this.top.prev
    node.next = this.top
    node.prev.next = node
    this.top.prev = node
}

func (this *LRUCache) Get(key int) int { 
    if v,ok:=this.mp[key]; !ok {
        return -1
    } else {
        // move the node to top 
        this.MoveNodeToTop(v)
        // return the value
        return v.value
    }
}

func (this *LRUCache) Put(key int, value int)  {
    //exists -> update value -> move to top
    //not exists -> capacity full -> remove last -> add to top
                 // add to top
    if v,ok := this.mp[key]; ok {
        v.value = value
        this.MoveNodeToTop(v)
    } else {
        if len(this.mp) == this.capacity {
            // remove bottom and add node to top
            bottom := this.bottom.next
            this.bottom.next = bottom.next
            bottom.next.prev= this.bottom
            delete(this.mp, bottom.key)
            
        } 
        newNode := &listNode{
                key: key,
                value: value,
            }
        this.AddNodeToTop(newNode)
        this.mp[key] = newNode
    }
}


/**
 * Your LRUCache object will be instantiated and called as such:
 * obj := Constructor(capacity);
 * param_1 := obj.Get(key);
 * obj.Put(key,value);
 */