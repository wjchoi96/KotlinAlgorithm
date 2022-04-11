// bronze 3
// 16-1

/*
    2개의 숫자가 주어졌을때, 3가지중 어떤 관계인지 구하라
    1. 첫번째 숫자가 두번째 숫자의 약수이다
    2. 첫번째 숫자가 두번째 숫자의 배수이다
    3. 첫번째 숫자가 두번째 숫자의 약수와 배수 모두 아니다

    1. 두번째 숫자 % 첫번째 숫자 == 0
    2. 첫번째 숫자 % 두번째 숫자 == 0
*/

import java.io.*
import java.util.StringTokenizer 

fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st : StringTokenizer
    
    var x : Int
    var y : Int
    while(true){
        st = StringTokenizer(br.readLine())
        x = st.nextToken().toInt()
        y = st.nextToken().toInt()

        if(x == 0 && y == 0){
            break
        }

        if(y%x == 0){
            bw.write("factor\n")
        }else if(x%y == 0){
            bw.write("multiple\n")
        }else{
            bw.write("neither\n")
        }
    }

    bw.flush()
    bw.close()
    br.close()
}
