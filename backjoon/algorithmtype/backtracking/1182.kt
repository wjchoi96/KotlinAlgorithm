//sliver2
/*
    바킹독님 백트래킹 강의 도중 제시된 연습문제

    부분수열의 합

    N개의 정수로 이루어진 수열이 존재
    크기가 양수인 부분수열 중에서, 해당 수열의 원소의 합이 S가 되는 경우의 수를 구하라

    정수의 개수N, 정수 S
    1 <= N <= 20
    |S| <= 1,000,000

    N개의 정수 부여 (절대값이 100,000을 넘지 않는다)

    1. 크기가N인 수열에서 1~N개의 수를 뽑아서 그 합을 구한다
    2. 합이S가 되면 count++
    3. 중복된 수를 뽑지않는다
    4. 동일한 경우의 수가 나오지않게 오름차순으로 수를 뽑는다 => 원본 수열을 정렬해놓은 상태로 수행해야할듯

    5. 1~N개의 수를 뽑는건데, 종료 조건을 어떻게 처리할지 생각해보자
    - N개의 수열을 뽑는다 생각하고, 뽑는 진행 과정에서 S가 완성이 되는지 체크?
    - 수를 뽑는데, 뽑은 수들의 합이 S가 되거나, S가 완성되지않고 N개의 수열을 다 뽑는다면 종료
*/