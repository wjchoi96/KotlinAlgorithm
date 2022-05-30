/*
    카카오 2021 인턴 기출문제 3번
    level 3

    표 편집
    
    명령어 기반으로 표의 행을 선택, 삭제, 복구 프로그램

    선택 
    - 한번에 한 행만 선택 가능
    - U X : 현재 선택된 행에서 x칸 위에 있는 행을 선택
    - D X : 현재 선택된 행에서 X칸 아래에 있는 행을 선택
    - C   : 현재 선택된 행을 삭제한 후, 바로 아래 행 선택, 삭제된행이 맨 아래행이였다면 바로 윗행 선택
    - Z   : 가장 최근에 삭제된 행을 복구. 선택된 행은 바뀌지 않는다
*/
/*
    arrayList 에 표 정보를 저장

    remove 명령을 수행한다면 해당 수행정보를 스택에 저장

    현재 선택 idx => p (pointer)

    원본 list를 보관하고있다가
    원본을 순회하며 결과물과 일치하지 않는 행은 삭제된것
    삭제된 행은 삭제된행 처리를 하고 건너뛰고, 다시 일치하는 행을 찾게된다면 다시 진행
    => x 
    removeStack 에 남은 값들이 삭제된 행 

    5<= n <= 1,000,000 : 처음 표의 행 개수
    0<= K < n : 처음 선택된 위치
    1<= cmd <= 200,000 : 명령어 배열

    "이름" 열은 문제풀이에 딱히 쓰이지 않는다
*/

/*
    제출 이력
    1. array + pointer 방식
    => Kakao003Try2
    : tc두개 런타임에러
    : move 시 삭제된 item들을 건너뛰면서 배열을 순회하는게 문제인가 싶다

    2. arrayList + StringBuilder insert
    => Kakao003Try1
    : 정확성 통과, 효율성 소수만 통과하고 실패
    : arrayList add,remove 연산의 특성상 효율성을 통과 할 수 없다

    3. pointer 방식
    => Kakao003Solve
    : 배열이 있다고 가정하고, pointer, size 로만 작업
    : 실제로 배열의 값(이름)이 필요가 없기때문에 가능한 방식

    4. LinkedList 방식
    4.1 : 직접 구현
    4.2 : ListIterator

*/

/*
    수정 이력

    0. arrayList 를 사용하는 방식으로 시도
    - tc 2~3개 제외하고 다틀림
    - 5번과정으로 넘어갔음

    1. try1 방식으로는 문제를 못찾을것같아서 카카오 공식 문제 해설을 확인
    현재 방식으로 변경
    - tc 반타작

    2. remove 이후 삭제되지 않은 행을 선택해야한다
    - tc 2개 런타임에러
    - 효율성 개박살

    3. 2번에서 OX 문자열 설정하는것을 String+ 연산이 아닌 StringBuilder append로 변경
    - tc 2개 런타임에러
    - 효율성 절반 통과
    - up, down때 배열을 순회하면서 삭제되지않은 행을 찾아서 이동하기떄문에 최악의 경우O(n)의 시간복잡도
    - 이거때문에 2개 런타임에러가 뜨는거같다

    4. 원래방식(1번)에서 배열을 제거하고 size, pointer 로만 접근하는 방식 수행
    - StringBuilder의 insert 연산의 시간복잡도를 알아내보자
    - insert(at)은 O(1), insert는O(n)이라고 기억
    - insert(at)이 아닌 setCharAt메소드가 O(1)이였다 => 해당 위치의 값을 변경하는 메소드
    //https://loosie.tistory.com/339
    =>>> 정확성 효율성 모두 통과!

    5. arrayList 사용하는 방식도 가능성은 있다. restore 할때 p를 조정 안해주는게 문제였던거 같다
    => 정확성 통과, 효율성은 2개 제외하고 모두 시간초과
    - 이는 arrayList의 add, add(at)연산이 O(n)이여서 그렇다
    - arrayList를 사용해서는 효율성을 통과할 수 없다고 판단된다

    5. custom linkedList로 구현해보자
    => Kakao003UseLinkedList
    => 정확성 통과
    => 효율성 통과
*/

import java.util.Stack
import java.util.LinkedList
fun main(args : Array<String>){
    val cmd = arrayOf(
        // "D 2","C","U 3","C","D 4","C","U 2","Z","Z"
        "D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"
    )
    val n = 8
    val k = 2

    val res = Kakao003UseLinkedList().solution(n, k, cmd)
    print("$res\n")
}

// ========= LinkedList + ListIterable 사용해서 풀어보자
/*
    remove, undo 를 O(1)에 수행하기 위해서는
    removeStack 에 node 자체를 저장해야한다 => ListIterable 사용 불가능
    remove, undo 작업을 커스텀해야하기 때문에 linkedList 자체를 구현해야한다
    단 모든기능은 필요없고 move 에 필요한 listIterator 기능과
    remove, undo 기능만 구현하자

    제출
    1. 통과
*/
private data class KakaoNode(
    var data : Int,
    var prev : KakaoNode?,
    var next : KakaoNode? 
){
    val originalData : Int = data
    constructor(data : Int) : this(data, null, null)
    constructor(data : Int, prev : KakaoNode?) : this(data, prev, null)
    override fun toString() : String {
        return "node : prev[${prev?.data}], data[${data}], next[${next?.data}]"
    }
}

private class Kakao003LinkedList {
    private var start : KakaoNode? = null
    private var cursor : KakaoNode? = null
    private var nodeCount = 0
    val size : Int 
        get() = nodeCount
    private val removeNodeData = -1

    constructor(size : Int){
        val list = Array<Int>(size){it} // 값이 존재하는 node 는 1
        add(list)
    }
    override fun toString() : String{
        val sb = StringBuilder().apply{append("[ ")}
        var node = start // start 부터 시작
        while(node != null){ //  마지막 노드까지
            sb.append("${node.data} ")
            node = node.next 
        }
        sb.append("], cursor[${cursor?.data}]")
        return sb.toString()
    }
    fun getNodesStatus() : String {
        println("node count : $size")
        val sb = StringBuilder()
        var node = start // start 부터 시작
        while(node != null){ //  마지막 노드까지
            when(node.data){ 
                removeNodeData -> sb.append("X")
                else -> sb.append("O")
            }
            print("node[${node.data}] ")
            node = node.next 
        }
        println("")
        return sb.toString()
    }
    fun select(at : Int){
        println("select $at")
        if(start == null){
            return
        }
        if(at == 0){
            cursor = start
            return
        }
        var node = start!!
        var nodeCount = 0
        while(node.next != null){
            node = node.next!!
            if(++nodeCount == at){ // at 까지 갈수 있다면 가고 없다면 최대한 진행한다음 멈춘다
                break
            }
        }
        cursor = node
    }
    fun moveUp(at : Int){
        println("moveUp from[${cursor?.data}] count $at")
        if(cursor == null){
            return
        }
        var moveCount = 0
        var node = cursor!!
        while(node.prev != null){
            println("move")
            node = node.prev!!
            if(++moveCount == at){
                break
            }
        }
        cursor = node
    }
    fun moveDown(at : Int){
        println("moveDown from[${cursor?.data}] count $at")
        if(cursor == null){
            return
        }
        var moveCount = 0
        var node = cursor!!
        while(node.next != null){
            println("move")
            node = node.next!!
            if(++moveCount == at){
                break
            }
        }
        cursor = node
    }
    fun removeCursor(stack : Stack<KakaoNode>){
        println("removeCursor[${cursor?.data}]")
        if(cursor == null){
            return
        }
        cursor!!.data = removeNodeData // remove 되는 node 는 0으로 변경
        cursor!!.next?.prev = cursor!!.prev
        cursor!!.prev?.next = cursor!!.next
        stack.push(cursor!!)
        nodeCount--
        // 이 다음 커서가 가리켜야 할 노드는?
        // 바로 아래행을(next node)를 선택합니다
        // 단, 삭제된 행이 가장 마지막 행인경우( if node.next == null )
        // 바로 윗 행(prev node)을 선택합니다
        if(cursor!!.next != null){
            cursor = cursor!!.next!!
            println("cursor select next(down) ${cursor?.data}")
        }else{
            cursor = cursor!!.prev
            println("cursor select prev(up) ${cursor?.data}")
        }
        debugLog()
    }
    fun undo(node : KakaoNode){
        println("undo[${node.originalData}]")
        // 끊어졌던 노드를 다시 이어준다
        emptyUndo(node)
        node.data = node.originalData // undo 되는 node는 값을 본래 값으로로 다시 설정
        // 이 다음 커서가 가리켜야 할 노드는?
        // 지금 가리키고 있는 노드를 그대로 유지하면 된다
        debugLog()
    }
    fun emptyUndo(node : KakaoNode){
        // node를 다시 복구시켜주지만, data는 삭제된 node값인 0으로 그대로 설정
        node.prev?.next = node
        node.next?.prev = node
        nodeCount++
    }
    private fun add(data : Int){
        if(start == null){
            start = KakaoNode(data)
            nodeCount++
            return
        }
        var node = start!!
        while(node.next != null){
            node = node.next!!
        }
        node.next = KakaoNode(data, node)
        nodeCount++
    }
    private fun add(dataList : Array<Int>){
        if(dataList.isEmpty()){
            return
        }
        var dataIdx = 0
        if(start == null){
            start = KakaoNode(dataList[dataIdx++])
            println("add start : $start")
            nodeCount++
        }
        var node = start!!
        while(node.next != null){
            node = node.next!!
        } // 현재 마지막 노드로 이동
        while(dataIdx in 0 until dataList.size){ // data 가 남아있다면
            node.next = KakaoNode(dataList[dataIdx++], node) // 다음 노드에 data 저장
            node = node.next!! // 다음 노드로 이동
            println("add node : $node")
            nodeCount++
        }
    }

    private fun debugLog(){
        var node = start
        while(node != null){
            println("$node")
            node = node.next
        }
    }

}

private class Kakao003UseLinkedList {
    private var removeStack = Stack<KakaoNode>()
    private lateinit var list : Kakao003LinkedList
    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        list = Kakao003LinkedList(n)
        list.select(k)
        println("$list")
        for(i in 0 until cmd.size){
            val op = cmd[i].split(' ')
            when(op[0]){
                "U" -> list.moveUp(op[1].toInt())
                "D" -> list.moveDown(op[1].toInt())
                "C" -> list.removeCursor(removeStack)
                "Z" -> list.undo(removeStack.pop())
            }
            println("$list")
        }
        while(!removeStack.isEmpty()){
            list.emptyUndo(removeStack.pop()) // 삭제되었던 노드를 삭제된 노드 표시하고 복구시켜준다
        }
        return list.getNodesStatus()
    }
    
}

// 처음부터 다시 해보자
// list를 실제로 사용하지않고, 포인터만 이동시켜서 수행하는 방식
// 성공!! => 수정이력 4번
private class Kakao003Solve {
    private var removeStack = Stack<Int>()
    private var size = 0
    fun getSize() : Int {
        return size
    }
    private var p = 0
    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        p = k
        size = n
        for(i in 0 until cmd.size){
            val op = cmd[i].split(' ')
            when(op[0]){
                "U" -> up(op[1].toInt())
                "D" -> down(op[1].toInt())
                "C" -> remove()
                "Z" -> restore()
            }
        }
        val res = StringBuilder()
        for(i in 0 until size){
            res.append("O")
        }
        //https://loosie.tistory.com/339
        while(!removeStack.isEmpty()){
            res.insert(removeStack.pop().toInt(), "X")
        }
        return res.toString()
    }
    private fun up(count : Int){
        p-=count
    }
    private fun down(count : Int){
        p+=count
    }
    private fun remove(){
        removeStack.push(p)
        if(p==size-1){
            p-- // 한칸 위
        }
        size-- // size가 줄기때문에 알아서 한칸 아래(++)한 효과가 된다
    }
    private fun restore(){
        val restore = removeStack.pop()
        if(restore <= p){
            p++
        }
        size++
    }
}

// try2 : 전날 풀었을땐 tc 2개뺴곤 통화
// 효율성 개 박살 => StringBuilder 사용으로 바꾸니 효율성도 어느정도 통과한게 생긴다
// 21, 27 런타임 에러 대체 뭔데
private class Kakao003Try2 {
    private var removeStack = Stack<Int>()
    private lateinit var list : Array<Int>
    private var p = 0
    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        p = k
        list = Array(n){1}

        for(i in 0 until cmd.size){
            val op = cmd[i].split(' ')
            when(op[0]){
                "U" -> up(op[1].toInt())
                "D" -> down(op[1].toInt())
                "C" -> remove()
                "Z" -> restore()
            }
        }
        var res = StringBuilder()
        for(i in 0 until n){
            if(list[i] == 0){
                res.append("X")
            }else{
                res.append("O")
            }
        }
        return res.toString()
    }
    private fun up(count : Int){
        var upCount = count
        while(upCount > 0){
            if(list[--p] != 0){ 
                upCount--
            }
        }
    }
    private fun down(count : Int){
        var downCount = count
        while(downCount > 0){
            if(list[++p] != 0){ 
                downCount--
            }
        }
    }
    private fun remove(){
        list[p] = 0
        removeStack.push(p)
        // 삭제되지 않은 행을 선택해야한다
        if(p == list.size-1){ // 가장 마지막 행이였다면 윗칸 선택
            while(list[--p] == 0){}
        }else{
            while(list[++p] == 0){} // 아니면 아래칸 선택
        }
    }
    private fun restore(){
        val restore = removeStack.pop()
        list[restore] = 1
    }

}

// try 1 => 3개 빼고 다 fail
// restore시 p조정을 안해준 문제 발견
/*
    tc 통과
    효율성은 2개 빼고 시간초과

    ArrayList는 get은 예상대로 O(1)이지만
    add(addAt 포함)은 O(N)이였다
    add과정은 처음 기본size 10 이후부터는 새 배열을 만들고 복사 후 옮기게 되는 과정
    add(at)은 특정위치 다음에 존재하는 데이터를 복사 후 한칸 뒤에 붙이는 과정
    때문에 arrayList로는 효율성을 통과 할 수가 없다
*/
private class Kakao003Try1 {
    private var removeStack = Stack<Int>()
    private val list : ArrayList<Int> = ArrayList<Int>()
    private var p = 0
    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        p = k
        for(i in 0 until n){
            list.add(i)
        }
        for(i in 0 until cmd.size){
            val op = cmd[i].split(' ')
            when(op[0]){
                "U" -> select(-op[1].toInt())
                "D" -> select(op[1].toInt())
                "C" -> remove()
                "Z" -> restore()
            }
        }
        var res = StringBuilder()
        for(i in 0 until n){
            res.append("O")
        }
        /*
            insert는 성공하고 charAt은 실패하는 경우가 있는 이유
            removeStack에 저장된 idx들은 삭제될때 당시의 list의 size에 대응되는 idx이다
            때문에 처음부터 원래 size를 가져다놓고, setCharAt을 하면 틀린다
            stack pop횟수에 따른(몇번째 pop인지) 추가 idx 계산을 해주면 될듯?
            => 아니다 이건 여기서는 못쓴다
            => linkedList나 배열을 사용해서 idx가 remove, restore을 해도 원래 자리대로 유지되는 상태에서만 가능
        */
        // var count = 0
        while(!removeStack.isEmpty()){
            res.insert(removeStack.pop(), 'X')
            //res.setCharAt(removeStack.pop()+count++, 'X')
        }
        return res.toString()
    }
    private fun select(to : Int){
        p+=to // 범위를 벗어나게 하는 값도 나올까?
        // print("select to[$to] => $p\n")
    }
    private fun remove(){
        // print("remove : $p\n")
        // 가장 아래인경우 한칸 위로 옮긴다
        // 그 외에는 remove를 수행하면 자연스레 아래칸이 위로 올라오므로 그대로 두면 된다
        removeStack.push(p)
        list.removeAt(p)
        if(p == list.size){
            p-=1
        }
    }
    // remove 되었던게 마지막 행인경우, addAt 을 호출해봐야 마지막 행으로 들어가게 될것
    private fun restore(){
        val restore = removeStack.pop()
        // print("restore : $restore\n")
        list.add(restore, restore) // 삭제되었던 idx에 다시 집어넣어준다
        if(restore <= p){ // 현재 선택항목보다 위에 삽입되었다면 p 조정
            p++
        }
    }
    private fun printSheet(){
        print("print sheet\n")
        for(i in 0 until list.size){
            print("$i : ${list[i]} ")
            if(p == i){
                print("p\n")
            }else{
                print("\n")
            }
        }
    }
}