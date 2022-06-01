/*
    바킹독님 알고리즘 강의 중 수학단원 소수 연습문제
    sliver 3

    소수 구하기

    M이상 N이하의 소수를 모두 출력

    2초
    1<= M <= N <= 1,000,000
    소수가 하나 이상 있는 입력만 주어진다
*/
/*
    제출
    1. 성공

    2. 성공
    - Math.sqrt 사용
    - 해당 결과를 포함하도록 순회해야한다
*/
fun main(args: Array<String>){
    Solution1929().solve()
}
class Solution1929 {
    fun solve(){
        val br = System.`in`.bufferedReader()
        val bw = System.out.bufferedWriter()
    
        //input data
        val (m,n) = br.readLine().split(' ').map{it.toInt()}
        val state = getPrimeState2(n)
        for(i in m..n){
            if(state[i]){
                bw.write("${i}\n")
            }
        }

        bw.flush()
        br.close()
        bw.close()
    }

    private fun getPrimeState(n: Int): Array<Boolean> {
        val state = Array(n+1){true}
        state[0]=false
        state[1]=false
        var i = 2
        while(i*i<=n){
            if(state[i]){
                var j = i
                while(i*j<=n){
                    state[i*j]=false
                    j++
                }
            }
            i++
        }
        return state
    }
    private fun getPrimeState2(n: Int): Array<Boolean> {
        val state = Array(n+1){true}
        state[0]=false
        state[1]=false
        val x = Math.sqrt(n.toDouble()).toInt()
        for(i in 2..x){
            if(!state[i]) continue
            var j = i
            while(i*j<=n){
                state[i*j]=false
                j++
            }
        }
        return state
    }
}