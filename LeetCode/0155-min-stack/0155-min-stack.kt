class MinStack() {
    val minStk = Stack<Int>()
    val stk = Stack<Int>()

    fun push(value: Int) {
        stk.push(value)
        if (minStk.isEmpty() || minStk.peek() >= value) {
            minStk.push(value)
        }
    }

    fun pop() {
        val x = stk.pop()
        if (minStk.peek() == x) minStk.pop()
    }

    fun top(): Int {
        return stk.peek()
    }

    fun getMin(): Int {
        return minStk.peek()
    }

}

/**
 * Your MinStack object will be instantiated and called as such:
 * var obj = MinStack()
 * obj.push(value)
 * obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.getMin()
 */