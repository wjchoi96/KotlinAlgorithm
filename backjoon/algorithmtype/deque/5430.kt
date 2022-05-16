/*
    덱 유형 문제
    gold 5

    AC

    정수 배열에 연산을 하기 위한 언어 AC
    해당 언어에는 두가지 함수 R(뒤집기), D(버리기) 가 있다
    함수는 조합해서 한번에 사용할 수 있다
    "AB" => A를 수행한 뒤 바로B를 수행하는 함수
    "RDD" => 배열을 뒤집고, 처음 두 수를 버리는 함수

    배열의 초기값, 수행할 함수가 주어졌을때 최종 결과를 구하는 프로그램을 작성
    에러가 발생한다면 error 출력
*/
/*
    T: Tc 개수. 최대 100
    p: 수행할 함수
    1<= P <= 100,000
    n: 배열에 들어있는 수의 개수
    0<= n <= 100,000
    1<= nx <= 100

    p의 길이와 n의 합은 70만을 넘지 않는다

    1초
*/
/*
    시간복잡도를 통과하기 위해서는 R,D가 연속으로 입력받는 횟수를 구하고 
    그만큼 한번에 처리해야할듯하다

    D: 뒤집기
    => head, tail 을 바꾸면 된다 => O(1)
    => 해당방법을 사용하려면 deque를 custom 해야한다
    => queue로도 가능할듯?

    => size만큼 queue를 하나 생성하고 
    => newQueue.offer(oldQueue.pollLast)
    => offerFirst, pollLast를 사용해야 하니 queue가 아닌 deque를 사용해야한다
    => O(n)
    => 최악의 경우 뒤집기를 p/2번 수행, 한번당 o(n) => 최대 10만번을 5만번 수행 => 5,000,000,000
    : 시간복잡도 통과 못할거같은데..

    => D가 짝수번 들어오면 그대로 유지
    => 홀수번 들어오면 한번 뒤집기

    R: 버리기
    => pollFirst
    => 횟수가 중첩된다고 시간복잡도를 낮출방법은 없고, 어차피 O(1) 연산이다

    reverse status 변수를 두고 반대로 offer, poll 실행하는 방법으로 해보자
*/

/*
    1. size와 다르게 값이 들어오는 경우 => 이건 고려 안해도 될듯
    2. poll 시 null이 리턴되는 경우
    3. 결과값이 빈경우 : 모두 poll된경우 => [] 출력
*/

/*
    제출
    1. 틀렸습니다(35%)
    - 결과값이 빈경우 : 모두 poll된경우 => [] 출력 추가

    2. 틀렸습니다(35%)
    - R이 쌓인채로 opList순회가 종료되면 스택에 남은 R에 대한 적용이 되지 않는 문제 수정
    - 이걸 왜 못찾았지

    3. 정답
    - 최적화 무조건 필요
*/

import java.util.Deque
import java.util.Stack
import java.util.LinkedList
fun main(args: Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()   
    val opStack: Stack<Char> = Stack()
    val values: Deque<Int> = LinkedList()
    
    val tc = br.readLine().toInt()
    loop@for(loop in 0 until tc){
        var reverseStatus = false
        val op = br.readLine().toCharArray()
        val size = br.readLine().toInt()
        values.clear()
        var value = br.readLine()
        value = value.replace("[", "")
        value = value.replace("]", "")
        if(!value.isEmpty()){
            value.split(',').map{it.toInt()}.forEach {
                values.offer(it)
            }
        }

        for(i in 0 until op.size){
            // 같은 연산이 연속되어 나오는것을 확인
            // 가장 마지막에 넣은 연산이랑, 현재 연산이 다르다면, 이전에 입력되어있는 연산들을 모두 실행, 이후 현재 연산 push
            // stack 으로 해야할듯
            // R만 수행하면된다
            if(op[i] == 'R'){
                if(!opStack.isEmpty() && opStack.peek() != op[i]){
                    var dCount = opStack.size
                    if(dCount%2 == 1){
                        reverseStatus = !reverseStatus // 홀수라면 reverse
                    }
                    opStack.clear()
                    opStack.push(op[i])
                }else{
                    opStack.push(op[i])
                }
            }else{
                //D 수행 이전에, opStack에 쌓인 R처리를 먼저 진행
                if(!opStack.isEmpty()){
                    var dCount = opStack.size
                    if(dCount%2 == 1){
                        reverseStatus = !reverseStatus // 홀수라면 reverse
                    }
                    opStack.clear()
                }
                val poll = if(reverseStatus){
                    values.pollLast()
                }else{
                    values.pollFirst()
                }
                if(poll == null){
                    bw.write("error\n")
                    continue@loop
                }
            }
        }
        if(!opStack.isEmpty()){
            var dCount = opStack.size
            if(dCount%2 == 1){
                reverseStatus = !reverseStatus // 홀수라면 reverse
            }
            opStack.clear()
        }
        bw.write("[")
        while(!values.isEmpty() && values.size != 1){
            if(!reverseStatus)
                bw.write("${values.pollFirst()},")
            else
                bw.write("${values.pollLast()},")
        }
        if(!values.isEmpty()){
            if(!reverseStatus)
                bw.write("${values.pollFirst()}")
            else
                bw.write("${values.pollLast()}")
        }
        bw.write("]\n")
    }
    


    br.close()
    bw.flush()
    bw.close()
}
















/*
    custom queue 구현을 통해 head, tail을 바꾸는 방법을 사용해볼까?
    근데 까다로울거 같긴한데...
    LinkedList + custom 이라면?
    해보자
*/
/*
    head, tail 바꾸는 방식의 reverse는 힘들겠는데
    head, tail을 바꿨을때
    
    pollFirst는 head값을 리턴하고, head = head?.next 가 되어야하는데
    head, tail값을 바꿔버린다면 head가 next값을 가지고 있지 않는다

    이건 불가능하고 좀더 어거지로 구현한다면 reverse status값을 가지고
    reverse 상태일때 offerFirst가 들어온다면 offerLast를 수행하는 식으로 해야할듯
*/
private class ReverseableDequeNode(
    val data: Int,
    var prev: ReverseableDequeNode?,
    var next: ReverseableDequeNode?
){
    constructor(data: Int): this(data, null, null)
    constructor(data: Int, prev: ReverseableDequeNode?): this(data, prev, null)
}
private class ReverseableDeque {
    /*
        값이 하나일땐, head, tail이 같은 값을 가리킨다?
        => size 가 1인걸로 구분?
        reverse시 head와 tail을 뒤바꾸는 형식으로 진행하려면 head와 tail이 모두 값을 가리키고있어야한다

        head: front
        tail: last
    */
    private var head: ReverseableDequeNode? = null
    private var tail: ReverseableDequeNode? = null
    private var size = 0

    private fun offerFirstData(data: Int){
        val newNode = ReverseableDequeNode(data)
        head = newNode
        tail = newNode
    }
    // must check size before call
    private fun pollWhenSizeOne(): Int{
        val poll = head
        head = null
        tail = null
        return poll!!.data
    }
    fun offerFirst(data: Int){
        if(size == 0){
            offerFirstData(data)
            return 
        }
        //head 앞으로 추가
        val newNode = ReverseableDequeNode(data, null, head)
        head?.prev = newNode
    }
    fun offerLast(data: Int){
        if(size == 0){
            offerFirstData(data)
            return 
        }
        //tail 뒤로 추가
        val newNode = ReverseableDequeNode(data, tail)
        tail?.next = newNode
    }
}