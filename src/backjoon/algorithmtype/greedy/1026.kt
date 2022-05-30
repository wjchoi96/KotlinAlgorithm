/*
    바킹독님 알고리즘 강의 중 그리디 단원 연습문제
    sliver 4

    보물

    수학이 항상 큰 골칫거리였던 나라가 있었다
    국왕은 다음 문제를 내고 큰 상금을 걸었다

    길이가 n인 정수 배열 a,b가 있다
    다음과 같은 함수 s를 정의하자

    s = a[0]*b[0] + ... +a[n-1]*b[n-1]

    s의 값을 가장 작게 만들기 위해 a를 재배열 하자
    단, b는 재배열 해선 안된다

    s의 최소값을 출력하시오

    1<= N <=50
    0<= A[i], B[i] <= 100

    2초
*/
/*
    s = a[0]*b[0] + ... +a[n-1]*b[n-1]
    
    1. 가장 큰 수에 가장 작은 수를 곱해줘야 한다
    - b의 가장 큰 수를 매번 알아내면서 진행을 한다면 O(nm) 이 된다
    - n 이 최대 50이니 해결은 될것같은데

    ! 근데 b는 재배열 하면 안된다고는 했지만, 문제 출력은 최소값만 출력하는거니까 정렬해도 될거같다
    문제에서는 B에 있는 수는 재배열하면 안된다는 조건이 있지만 그 조건과 무관하게 A만 재배열해도 우리가 원하는대로 큰 것과 작은 것을 이어줄 수 있기 때문에 둘 다 정렬한 후 값을 계산하면 됩니다

    A만 재배열해서 진행해보자
*/
/*
    제출
    1. 성공
*/
fun main(args: Array<String>){
    Solution1026().solve()
}

class Solution1026 {
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()
        var n = br.readLine().toInt()
        
        val a = br.readLine().split(' ').map{it.toInt()}.toTypedArray()
        val b = br.readLine().split(' ').map{it.toInt()}.toTypedArray()
        a.sort()
        b.sortDescending()

        var sum = 0
        for(i in 0 until n){
            sum += a[i]*b[i]
        }
        bw.write("\n$sum\n")
        
        bw.flush()
        bw.close()
        br.close()
    }

    // b는 재정렬을 못한다는 조건을 지켜, a가 재정렬된 결과값도 함께 출력
    // 이러면 시간복잡도는 O(n^2)
    fun solve2(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()
        var n = br.readLine().toInt()
        
        val a = br.readLine().split(' ').map{it.toInt()}.toTypedArray()
        val b = br.readLine().split(' ').map{it.toInt()}.toTypedArray()
        a.sort()
        val sortB = b.sortedDescending()

        var sum = 0
        
        //b의 가장큰값이 0에있고, 그에 대칭되는 a의 가장 작은값이 0에 있다
        //sortB[0]이 본래 b에는 몇번째에 있나 확인후, 해당 위치에 현재a의 값을 넣어준다 인데
        for(i in 0 until n){
            var idx = -1
            for(s in 0 until n){
                if(b[i] == sortB[s]) {
                    idx = s
                    break
                }
            }
            sum += a[idx]*sortB[idx]
            bw.write("${a[idx]} ")
        }
        bw.write("\n$sum\n")
        
        bw.flush()
        bw.close()
        br.close()
    }
}