//sliver 4
//18-2
/*
    N장의 카드
    1~N
    1번카드가 제일 위에, N번 카드가 제일 아래인 상태로 존재

    해당 동작을 반복
    예) [1,2,3,4]
    1. 제일 위의 카드를 버린다 => 1을 버린다 [2,3,4]
    2. 제일 위의 카드를 제일 아래로 옮긴다 => 2를 제일 아래로 옮긴다 [3,4,2]

    마지막에 남는 카드를 구하시오

    10 장 => 
    1장 버리고 1장 앞으로 이동 => 각 loop 마다 + 1 size가 필요하다
    loop 한번당 카드 개수가 -1개가 된다
    이론상 10장이면 loop 10번

    수학적으로도 접근이 되네 => 어케했는데
    r = 1
    while(r<size){
        r*=2
    }
    bw.write(size*2-r)
 */


import java.io.*
import java.util.StringTokenizer 
private lateinit var queue : Array<Int>
private lateinit var cards : Array<Int>
private var front = -1 // 삭제연산만 수행(pop 기준점)
private var rear = -1 // 삽입연산만 수행(push 기준점)

fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val size = br.readLine().toInt()
    queue = Array(size*2) {-1}
    cards = Array(size) {i->i+1}
    for(card in cards){
        offer(card)
    }

    while(size() > 1){
        poll()
        offer(poll()!!)
    }

    bw.write("${poll()}\n")
    
    bw.flush()
    bw.close()
    br.close()
}


private fun offer(n : Int) {
    queue[++rear] = n
    if(front < 0){
        front = 0
    }
    // print("offer ${queue[rear]}\n")
}
private fun poll() : Int? {
    if(front<0 || front>rear){
        front = -1
        rear = -1
        return null
    }
    // print("poll ${peek()}\n")
    return queue[front++]
}
private fun peek() : Int? {
    if(front<0 || front>rear){
        return null
    }
    return queue[front]
}
private fun size() : Int {
    if(front<0 || front>rear){
        return 0
    }
    return rear - front + 1
}
