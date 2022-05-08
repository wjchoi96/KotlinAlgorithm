/*
    연결리스트 구현을 해볼 파일
    이중 연결리스트를 구현해보았다
*/


fun main(args : Array<String>){
    val mLinkedList = LinkedListImplements<Int>()
    repeat(5){ // add 0 ~ 4
        mLinkedList.add(it)
        println("${mLinkedList.print()}")
    }
    println("${mLinkedList.print()}")

    mLinkedList.remove(1) // remove data 1
    println("${mLinkedList.print()}")
    mLinkedList.remove(1) // remove data 2
    println("${mLinkedList.print()}")

    mLinkedList.add(1, 1) // add 1 at 1
    println("${mLinkedList.print()}")

    mLinkedList.add(2, 2) // add 2 at 2
    println("${mLinkedList.print()}")
}
private data class Node<T>(
    var data : T,
    var prev : Node<T>?,
    var next : Node<T>?
){
    constructor(data : T) : this(data, null, null)
    constructor(data : T, prev : Node<T>) : this(data, prev, null)
}

private class LinkedListImplements<T> {
    companion object {
        private const val n = 1000005
    }
    private var start : Node<T>? = null
    private var size = 0

    fun add(data : T){
        if(start == null){
            start = Node<T>(data)
        }else{
            var node = start!! // start 부터 시작
            while(node.next != null){ // node가 next정보를 가지고 있지 않을때까지 전진 => 마지막 node로 접근하는것
                node = node.next!!
            }
            node.next = Node(data, node) // 마지막 node의 next에 새로운 node를 추가해준다
        }
        size++
    }
    fun add(data : T, at : Int) : Boolean {
        if(size <= at){
            return false
        }
        /*
            현재 위치에 data를 넣어야한다
            prevNode.next = newNode
            newNode.prev = prevNode
            newNode.next = currentNode
            currentNode.prev = newNode
            node.prev = Node<T>(data, )
        */
        val node = getNode(at) ?: return false
        val newNode = Node<T>(data, node?.prev, node)
        node.prev?.next = newNode
        node.prev = newNode
        size++
        return true
    }
    fun remove(at : Int) : Boolean{
        if(start == null){
            return false
        }
        if(size <= at){
            return false // throw Exception
        }
        var idx = 0
        var node = start!!
        while(node.next != null){
            node = node.next!!
            idx++
            if(idx == at){
                break
            }
        }
        size--
        node.prev?.next = node.next
        node.next?.prev = node.prev

        node.next = null
        node.prev = null
        return true
    }
    private fun getNode(at : Int) : Node<T>? {
        if(start == null){
            return null
        }
        if(size <= at){
            return null // throw Exception
        }
        if(at == 0){
            return start
        }
        var idx = 0
        var node = start!!
        while(node.next != null){ // 해당 idx까지 전진
            node = node.next!!
            idx++
            if(idx == at){
                break
            }
        }
        return node
    }
    fun get(at : Int) : T? {
        return getNode(at)?.data
    }
    fun print() : String {
        val sb = StringBuilder()
        for(i in 0 until size){
            sb.append("[$i] : ${get(i)}\n")
        }
        return sb.toString()
    }
}