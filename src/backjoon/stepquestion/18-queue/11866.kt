//sliver4
//18-3

/*
    1~N 번까지 원을 이루며 앉아있다
    K (<=N) 이 주어지는데, 순서대로 K번째 사람을 제거한다
    한사람이 제거되면 남은사람들로 이루어진 원을 따라 이 과정을 계속해 나간다
    N명의 사람이 모두 제거될때까지 계속된다

    사람들이 제거되는 순서를 (N,K)- 요세푸스 순열이라고 한다
    (7,3)- [3, 6, 2, 7, 5, 1, 4]
    k = 3 
    [1, 2, 3, 4, 5, 6, 7] arr[2] 제거
    [1, 2, 4, 5, 6, 7] arr[2]에서 3번째 => arr[4] 제거
    [1, 2, 4, 5, 7] arr[4]에서 3번째 => arr[1]제거
    [1, 4, 5, 7] arr[1]에서 3번째 => arr[3]제거 
    [1, 4, 5] arr[3]에서 3번째 => arr[2]
    [1, 4]

    원을 이루는 queue 를 구현
    k번만큼 pop, push 반복, k번째 요소는 push 하지 않고 res배열에 저장
    queue가 빌때까지 반복

    최적화 참고 : https://www.acmicpc.net/source/7639687
 */
import java.io.*
import java.util.*
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val st = StringTokenizer(br.readLine())
    val size = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    val queue : Queue<Int> = LinkedList()
    for(i in 1 until size+1){
        queue.offer(i)
    }
    bw.write("<")

    while(queue.size > 1){
        for(i in 0 until k-1){
            queue.offer(queue.poll())
        }
        bw.write("${queue.poll()}, ")
    }
    bw.write("${queue.poll()}>\n")
    
    bw.flush()
    bw.close()
    br.close()
}

private fun case1(){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val st = StringTokenizer(br.readLine())
    val size = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    val queue : Queue<Int> = LinkedList()
    val resArr : Array<Int> = Array(size){-1}
    var resPoint = 0
    for(i in 1 until size+1){
        queue.offer(i)
    }

    var count = 0
    while(!queue.isEmpty()){
        val pop = queue.poll()
        count++
        if(count == k){
            count = 0
            resArr[resPoint++] = pop
        }else{
            queue.offer(pop)
        }
    }

    bw.write("<")
    for(i in 0 until resArr.size-1){
        bw.write("${resArr[i]}, ")
    }
    bw.write("${resArr[resArr.size-1]}>\n")
    
    bw.flush()
    bw.close()
    br.close()
}
