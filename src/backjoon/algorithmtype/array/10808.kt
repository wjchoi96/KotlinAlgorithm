/*
    바킹독님 알고리즘 강의 중 배열단원
    bronze 2

    알파벳 개수

    알파벳 소문자로만 이루어진 단어 s가 주어진다
    각 알파벳이 단어에 몇개가 포함되어있는지 구하시오

    첫째 줄에 단어 S가 주어진다. 단어의 길이는 100을 넘지 않으며, 알파벳 소문자로만 이루어져 있다.
    단어에 포함되어 있는 a의 개수, b의 개수, …, z의 개수를 공백으로 구분해서 출력한다.
*/
/*
    a ~ z 까지의 개수만큼 int 형 배열을 생성, 0으로 초기화
    단어를 순회하며 각 알파벳의 자리에 ++ 

    char to int 를 하면 아스키코드로 전환되는것으로 알고있다
    97이 소문자 a였던거로 기억하는데 
    arr[a-97] 을 하면 a가 0번째 idx로 접근이 가능할것이다
*/
/*
    제출 

    1. 성공
*/
fun main(args : Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()   
    val baseAsciiCode = 97
    val size = 26
    val arr = Array(size){0}
    val s = br.readLine()
    for(i in 0 until s.count()){
        bw.write("${s[i].toInt()}\n")
        arr[s[i].toInt()-baseAsciiCode]++
    }
    for(i in 0 until size){
        bw.write("${arr[i]} ")
    }
    bw.write("\n")

    bw.flush()
    bw.close()
    br.close()
}
