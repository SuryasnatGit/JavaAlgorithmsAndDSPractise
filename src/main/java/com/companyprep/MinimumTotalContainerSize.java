package com.companyprep;

/**
 * You want to move N items in k days (N >= k). You have to move at least one item per day. The items are listed in
 * array P, where P[i] is size of item i. You can move item i only if all items from 0 to [i - 1] are already moved.
 * Every day you need a container to pack the item and move it. The container size needed for day i is the maximum item
 * size moved on that day.
 * 
 * Given k days and array P as the item sizes, find out the minimum total container size required to move all the items.
 * 
 * Examples
 * 
 * Example 1:
 * 
 * Input: P = [10, 2, 20, 5, 15, 10, 1], d = 3
 * 
 * Output: 31
 * 
 * Explanation:
 * 
 * day 1 - [10, 2, 20, 5, 15]. ContainerSize = 20<br/>
 * day 2 - [10]. ContainerSize = 10<br/>
 * day 3 - [1]. ContainerSize = 1<br/>
 * Total = 20 + 10 + 1 = 31<br/>
 * 
 * Example 2:
 * 
 * Input: P = [10, 2, 20, 5, 15, 10, 1], d = 5
 * 
 * 
 * Output: 43
 * 
 * Explanation:
 * 
 * day 1 - move [10]. ContainerSize = 10<br/>
 * day 2 - move [2]. ContainerSize = 2<br/>
 * day 3 - move [20, 5, 15]. ContainerSize = 20<br/>
 * day 4 - move [10]. ContainerSize = 10<br/>
 * day 5 - move [1]. ContainerSize = 1<br/>
 * Total = 10 + 2 + 20 + 10 + 1 = 43<br/>
 * 
 * Example 3:
 * 
 * Input: P = [5, 4, 2, 4, 3, 4, 5, 4], d = 4
 * 
 * Output: 16
 * 
 * Explanation:
 * 
 * day 1 - [5, 4], containerSize = 5<br/>
 * day 2 - [2], containerSize = 2<br/>
 * day 3 - [4, 3], containerSize = 4<br/>
 * day 4 - [4, 5, 4], containerSize = 5<br/>
 * day 5 - move [1]. ContainerSize = 1<br/>
 * Total = 5 + 2 + 4 + 5 = 16<br/>
 * 
 * Example 4:
 * 
 * Input: P = [22, 12, 1, 14, 17], d = 2
 * 
 * Output: 39
 * 
 * Explanation:
 * 
 * day 1 - [22, 12], containerSize = 22<br/>
 * day 2 - [1, 14, 17], containerSize = 17<br/>
 * Total = 22 + 17 = 39<br/>
 *
 * 
 * TODO : look into
 * 
 * https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/discuss/1085239/(JAVA)recursion-greatermemoization-greaterdp
 * 
 * https://leetcode.com/discuss/interview-question/958494/Minimum-Total-Container-Size-or-Interview-Question
 * 
 * https://leetcode.com/discuss/interview-question/977057/PLEASE-HELP%3A-How-to-solve-this/793469
 * 
 * https://algo.monster/problems/minimum_total_container_size
 */
public class MinimumTotalContainerSize {

}
