/*
    바킹독님 알고리즘 강의 중 연결리스트 단원 연습문제
    sliver 2

    에디터
    https://www.acmicpc.net/problem/1406

    한줄로 된 간단한 에디더를 구현
    영어 소문자를 기록, 최대 600,000글자까지 가능

    커서가 존재
    문장의 맨앞(첫번째 문자의 왼쪽),
    문장의 맨뒤(마지막 문자의 오른쪽),
    문장 중간 임의의 곳(모든 연속된 두 문자 사이)
    에 위치 가능

    길이가L인 문자열이 현재 편집기에 입력되어있다면, 커서가 위치할수 있는 곳은 L+1가지가 있다

    L : 커서를 왼쪽으로 한칸 옮김(커서가 문장의 맨 앞이면 무시)
    D : 커서를 오른쪽으로 한칸 옮김(커서가 문장의 맨 뒤면 무시)
    B : 커서 왼쪽에 있는 문자를 삭제함(커서가 문장의 맨 앞이면 무시) 
      : 삭제로 인해 커서는 한칸 왼쪽으로 이동한것처럼 나타나지만, 실제로 커서의 오른쪽에 있는 문자는 그대로
    P $ : $문자를 커서 왼쪽에 추가
    
    커서의 초기값 : 문장의 맨 뒤에 위치
    모든 명령어를 수행 후 편집기에 입력되어있는 문자열을 구하시오
*/
/*
    0.3초
    1<= N <= 100,000
    1<= 명령어의 개수M <= 500,000

    연결리스트의 삭제, 추가 연산은 O(1) => 원하는 위치에 삭제, 추가는 o(n+1) 인데
    => 커서를 기준으로 추가, 삭제가 이루어지니 커서= 특정 노드정보를 가지고 있는게 좋을듯
    => 그러면 o(1)로 가능
    최대 500,000 

    마지막에 연결리스트 순회O(n)
    최대 100,000

    이게 맞나?
*/
/*
    커서 왼쪽의 문자를 삭제 => 커서 맨 앞이면 무시
    커서 왼쪽에 문자를 추가

    커서의 시작은 0번째 node
    커서의 마지막은 n-1 번째 node의 오른쪽

    삭제 : 현재 node의 prev 제거
    추가 : 현재 node의 prev위치에 추가


    1, 2, 3, 4, 5 
                  c
    
    커서는 -1 ~ n-1 에 위치한다고 인지하자
    초기값 : n-1 node
    커서 왼쪽의 문자를 삭제 : 현재 cursor가 위치한 node 삭제 
    => 커서의 왼쪽이 삭제된것이니, cursor 는 삭제된노드.prev 의 정보를 가져야한다
    커서 왼쪽에 문자를 추가 : 현재 cursor가 위치한 node가 앞으로 밀리도록 node 추가 
    => cursor.next = newNode
    => 커서의 왼쪽에 추가된것이니 cursor 는 추가된노드의 정보를 가져야한다

    커서가 n-1 일땐 cursor 에 null 넣어야하나?
    커서가 null 이면 명령어 무시
*/
/*
    제출
    1. 시간초과(5%?)
    - add 가 문제인거같아서 약간 편법으로 end 추가

    2. 런타임에러(5%)(StackOverflow)

    3. 런타임에러(5%)(StackOverflow)
*/


// StackOverflow exception 
// ListIterator 를 사용해서 java.util.LinkedList 사용해서 하면 시간초과 피할 수 있다함
fun main(args : Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()   

    val str = br.readLine()
    val linkedList = CharLinkedList(str)
    println("${linkedList.print()}")
    val m = br.readLine().toInt()

    repeat(m){
        val op = br.readLine().split(' ')
        when(op[0]){
            "L" -> {
                // println("move left")
                linkedList.moveCursorLeft()
                // println("${linkedList.print()}")
            }
            "D" -> {
                // println("move right")
                linkedList.moveCursorRight()
                // println("${linkedList.print()}")
            }
            "B" -> {
                // println("remove cursor")
                linkedList.removeAtCursor()
                // println("${linkedList.print()}")
            }
            "P" -> {
                // println("add cursor ${op[1]}")
                linkedList.addAtCursor(op[1][0])
                // println("${linkedList.print()}")
            }
        }
    }
    println("finish")
    println("${linkedList.print()}")

    bw.write("${linkedList}\n")

    bw.flush()
    bw.close()
    br.close()
}

private data class Node(
    val data : Char,
    var prev : Node?,
    var next : Node?
){
    constructor(data : Char) : this(data, null, null)
    constructor(data : Char, prev : Node) : this(data, prev, null)

    override fun toString() : String{
        return "$data"
    }
}
private class CharLinkedList {
    private var start : Node? = null
    private var cursor : Node? = null // null => 커서의 오른끝

    constructor(str : String){
        if(str.isEmpty()){
            return
        }
        start = Node(str[0])
        var node = start!!
        for(i in 1 until str.count()-1){
            node.next = Node(str[i], node)
            node = node.next!!
        }
        node.next = Node(str[str.count()-1], node)
        cursor = node.next
    }

    fun moveCursorLeft(){
        cursor = cursor?.prev
    }
    fun moveCursorRight(){
        if(cursor?.next == null){
            return
        }
        cursor = cursor?.next
    }
    fun addAtCursor(data : Char){
        if(start == null){// 데이터가 없는경우
            cursor = add(data)
            return
        } else if(cursor == null){ // cursor가 맨 앞에 있을때
            // start 의 왼쪽에 추가 => start 교체
            start?.prev = Node(data, null, start)
            start = start?.prev
            cursor = start
            return
        }
        // 현재 cursor가 앞으로 가게 추가
        // 현재 cursor의 뒤에 추가하고, cursor를 새로 추가된 노드로 이동
        val newNode = Node(data, cursor, cursor?.next)
        cursor!!.next?.prev = newNode
        cursor!!.next = newNode
        cursor = newNode
    }
    fun removeAtCursor(){
        if(cursor == null){
            return
        }else if(cursor == start){ // start 제거
            start = start?.next
            start?.prev = null
            cursor = null
            return 
        }
        // 커서 왼쪽의 문자를 삭제 : 현재 cursor가 위치한 node 삭제 
        // => 커서의 왼쪽이 삭제된것이니, cursor 는 삭제된노드.prev 의 정보를 가져야한다
        cursor?.next?.prev = cursor?.prev
        cursor?.prev?.next = cursor?.next
        cursor = cursor?.prev
    }
    
    override fun toString() : String {
        val sb = StringBuilder()
        var node = start
        while(node != null){
            sb.append("${node}")
            node = node.next
        }
        return sb.toString()
    }


    private fun add(data : Char) : Node{
        if(start == null){
            start = Node(data)
            return start!!
        }
        var currentNode = start!!
        while(currentNode.next != null){
            currentNode = currentNode.next!!
        }
        // 맨 마지막 node 도착
        currentNode.next = Node(data, currentNode)
        return currentNode.next!!
    }

    fun print() : String {
        val sb = StringBuilder()
        var node = start
        while(node != null){
            sb.append("${node} ")
            node = node.next
        }
        sb.append("\ncursor : $cursor\n")
        return sb.toString()
    }
}