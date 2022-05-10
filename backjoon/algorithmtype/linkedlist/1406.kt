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
    - 입력문제
    => 1개를 입력하면 2개로 입력받는 문제가 존재했었다

    - 맨 왼쪽에서 moveRight 했을 경우 문제
    => null 에서 moveRight 했기때문에 null이 나오는 문제가 존재
    해당 반례 링크
    https://www.acmicpc.net/board/view/37758

    4. 성공

    LinkedList + ListIterator 방식으로 시도
    1. 성공

*/

// ListIterator 를 사용해서 java.util.LinkedList 사용해서 하면 시간초과 피할 수 있다함
// 해당 방식으로 구현 시도
/*
    listIterator : 이전과 다음 item의 정보를 가진 Iterator
    1. 범위를 벗어나서도 이동을 한다
    2. add하면 현재 + 1 위치에 add 된다
    3. remove => 가장 마지막에 next, previous 로 반환된 요소 제거
    4. LinkedList().listIterator(idx) 로 들어가는 idx가 nextIdx가 되는것
    - 실제로 현재 idx는 -1 된다고 생각하면 된다

    -1 ~ size 까지 이동하도록 설정?
    size 에서 remove 하면 왼쪽인 size-1 item이 remove 된다

    근데 item 이 없으면 previous 를 호출을 못하는데 -1로 어케감
    0~size 해야할듯

    item 3개
    item idx 
    0,1,2

    list iterator 로는
    0,1,2,3 => 이렇게 3갠 위치?

    왼쪽 item을 추가 제거

    0,1,2,3 으로 결정
    1. iterator remove
    => cursor == 0 return
    => iterator.previous(); iterator.remove() 수행
    - 종합하면 if(iterator.hasPrevious()) iterator.remove() 가 된다

    2. interator add
    => 그냥 add
    
    3. moveLeft => hasLeft가 허용할때까지 이동
    -> 0위치에선 remove 하지않고
    -> 나머지는 왼쪽으로 한칸 이동해서 remove 한다
    -> size가3일때 커서가 위치할수 있는 위치를 0,1,2,3 로 정했기 때문
    - 0이 -1역할?? 느낌

    4. moveRight => hasRight 가 허용할때까지 이동
    -> 맨마지막에서 add 하면 +1 위치에 add된다

    

    
*/
import java.util.LinkedList
fun main(args : Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()   

    val str = br.readLine()
    val m = br.readLine().toInt()
    val linkedList = LinkedList<Char>(str.toList())
    val listIterator = linkedList.listIterator()
    val debug = linkedList.listIterator(linkedList.size-1) // 여기 생성자로 들어가는 idx가 next값이 된다
    // 근데 왜 size=4 이고 size-3 은 3인데 왜 2로 되어있는거지
    println("\nlist size : ${linkedList.size}, ${linkedList.size-1}, debug at ${debug.nextIndex()-1}")
    while(listIterator.hasNext()){
        println("move to ${listIterator.nextIndex()}")
        listIterator.next()
    }
    println("iterator at ${listIterator.nextIndex()-1}, debug at ${debug.nextIndex()-1}")

    repeat(m){
        val op = br.readLine().split(' ')
        println("$linkedList, ${listIterator.nextIndex()-1}")
        when(op[0]){
            "L" -> {
                println("move left from ${listIterator.previousIndex()+1}")
                if(listIterator.hasPrevious()){
                    listIterator.previous()
                }
            }
            "D" -> {
                println("move right from ${listIterator.nextIndex()-1}")
                if(listIterator.hasNext()){
                    listIterator.next()
                }
            }
            "B" -> {
                println("remove at [${listIterator.nextIndex()-1}]]")
                if(listIterator.hasPrevious()){
                    listIterator.previous()
                    listIterator.remove()
                }
            }
            "P" -> {
                println("add at [${listIterator.nextIndex()-1}] ${op[1]}")
                listIterator.add(op[1][0])
            }
        }
    }
    println("finish")
    println("$linkedList")

    val iterator = linkedList.iterator()
    val sb = StringBuilder()
    while(iterator.hasNext()){
        sb.append("${iterator.next()}")
    }
    bw.write("${sb.toString()}")

    bw.flush()
    bw.close()
    br.close()
}


// ============== sovle 1 ====================
// cursor 정보를 내장하는 linkedList 구현
private fun solve1(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()   

    val str = br.readLine()
    val linkedList = CharLinkedList(str)
    val m = br.readLine().toInt()
    
    println("${linkedList.print()}")

    repeat(m){
        val op = br.readLine().split(' ')
        when(op[0]){
            "L" -> {
                println("move left")
                linkedList.moveCursorLeft()
                println("${linkedList.print()}")
            }
            "D" -> {
                println("move right")
                linkedList.moveCursorRight()
                println("${linkedList.print()}")
            }
            "B" -> {
                println("remove cursor")
                linkedList.removeAtCursor()
                println("${linkedList.print()}")
            }
            "P" -> {
                println("add cursor ${op[1]}")
                linkedList.addAtCursor(op[1][0])
                println("${linkedList.print()}")
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
    private var cursor : Node? = null // null => list의 왼쪽 끝

    constructor(str : String){
        if(str.isEmpty()){
            return
        }
        start = Node(str[0])
        var node = start!!
        for(i in 1 until str.count()){
            node.next = Node(str[i], node)
            node = node.next!!
        }
        cursor = node
    }

    fun moveCursorLeft(){
        cursor = cursor?.prev
    }
    fun moveCursorRight(){
        if(cursor == null){
            cursor = start
            return 
        }else if(cursor?.next == null){
            return
        }
        cursor = cursor?.next // 최대 마지막 node를 가리킬 수 있다
    }
    fun addAtCursor(data : Char){
        if(start == null){ // list 가 비어있는 경우 
            cursor = add(data)
            return
        } else if(cursor == null){ // cursor가 맨 앞에 있을때
            // start 의 왼쪽에 추가 => start 교체
            val newNode = Node(data, null, start)
            start?.prev = newNode
            start = newNode
            cursor = newNode
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
        if(cursor == null){ // cursor가 맨 앞에 있을때
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