/*
    solved.ac class 2 문제
    bronze 1

    펠린드롬수

    어떤 단어를 뒤어서 읽어도 똑같다면 그 단어를 팰린드롬이라고 한다
    'radar, sees' 는 팰린드롬이다

    수도 팰린드롬으로 취급할 수 있다
    수의 숫자들을 뒤에서부터 읽어도 같다면 그 수는 팰린드롬수 이다
    121, 12421

    무의미한 0은 앞에 올수 없다
    010 -> 불가능

    1초
    99999이하의 정수
    0이 주어지면 종료
*/
/*
    길이가 짝수라면 no
    양 끝부터 검사하여 일치 하지 않는다면 no
    중앙값을 제외하고 앞과 뒤가 일치하면 yes

    7인경우 => 7/2 = 3 을 제외하고 loop를 돌며 
    
*/
/*
    제출
    1. 틀렸습니다(50%)
    - string reverse 후 비교 방식으로 변경

    2. 틀렸습니다(50%)
    - 길이가 짝수인 경우도 존재한다;

    3. 성공
    
*/
fun main(args: Array<String>){
    Solution1259().solve()
}
class Solution1259 {
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()
        
        while(true){
            val inputStr = br.readLine()
            val size = inputStr.count()
            if(inputStr.toInt() == 0){
                break
            }
            var isFalindrom = true
            for(i in 0 until size/2){
                if(inputStr[i] != inputStr[size-1-i]){
                    isFalindrom = false
                    break
                }
            }
            if(isFalindrom){
                bw.write("yes\n")
            }else{
                bw.write("no\n")
            }
        }

        bw.flush()
        bw.close()
        br.close()
    }

    fun solve2(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()
        
        while(true){
            val inputStr = br.readLine()
            if(inputStr.toInt() == 0){
                break
            }
            val reverseStr = inputStr.reversed()
            if(reverseStr == inputStr){
                bw.write("yes\n")
            }else{
                bw.write("no\n")
            }
        }

        bw.flush()
        bw.close()
        br.close()
    }
}