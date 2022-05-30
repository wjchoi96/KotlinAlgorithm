//sliver 3
// 18-4

/*
    1. Queue의 가장 앞에있는 문서의 중요도를 확인한다
    2. 나머지 문서중 현재 문서보다 중요도가 높은 문서가 하나라도 있다면, 이 문서를 가장 뒤로 재배치한다.
    3. 아니라면 인쇄한다

    [A, B, C, D], [2, 1, 4, 3]
    => [C, D, A, B] 순으로 인쇄하게 된다

    어떤 한 문서가 몇번째로 인쇄되는지 알아내자

 */

 
import java.io.*
import java.util.* 
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    repeat(br.readLine().toInt()){
        var st = StringTokenizer(br.readLine())
        val size = st.nextToken().toInt()
        val k = st.nextToken().toInt()
        st = StringTokenizer(br.readLine())
        val queue : Queue<Pair<Int, Int>> = LinkedList()
        for(i in 0 until size){
             queue.offer(Pair(i, st.nextToken().toInt())) // 순서, 중요도 저장
        }

        var count = 0
        while(!queue.isEmpty()){
            val item = queue.poll()
            var availablePrint = true
            for(i in 0 until queue.size){
                if(item.second < queue.elementAt(i).second){ // 중요도가 더 큰 원소를 발견
                    queue.offer(item) // 현재 item 을 뒤로 재배치한다
                    for(j in 0 until i){ // i번째 이전의 item들을 뒤로 재배치한다
                        queue.offer(queue.poll())
                    }
                    availablePrint = false
                    break
                }
            }
            if(!availablePrint){ // 출력이 불가능하다면 다음 loop 로 넘어간다
                continue 
            }
            count++
            if(item.first == k){
                break
            }
        }
        bw.write("$count\n")
    }
    
    bw.flush()
    bw.close()
    br.close()
}


