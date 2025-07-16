import java.util.PriorityQueue
import kotlin.math.max

class AdvancedArrayTechniques {

    fun productExceptSelf(nums: IntArray): IntArray {
        val prefArray = IntArray(nums.size)
        prefArray[0] = 1
        val suffArray = IntArray(nums.size)
        suffArray[nums.size - 1] = 1
        for (i in 1..nums.size - 1) {
            prefArray[i] = prefArray[i - 1] * nums[i - 1]
        }
        for (i in nums.size - 2 downTo 0) {
            suffArray[i] = suffArray[i + 1] * nums[i + 1]
        }
        val retArray = IntArray(nums.size)
        for (i in retArray.indices) {
            retArray[i] = prefArray[i] * suffArray[i]
        }
        return retArray
    }

    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val freqMap = mutableMapOf<Int, Int>()
        for (num in nums) {
            freqMap[num] = freqMap.getOrDefault(num, 0) + 1
        }
        val pq = PriorityQueue<Map.Entry<Int, Int>>(compareByDescending { it.value })
        for (entry in freqMap) {
            pq.add(entry)
        }
        val retArray = IntArray(k)
        for (i in retArray.indices) {
            retArray[i] = pq.poll().key
        }
        return retArray
    }

    fun isValidSudoku(board: Array<CharArray>): Boolean {
        val rowSet = Array(9) { mutableSetOf<Char>() }
        val colSet = Array(9) { mutableSetOf<Char>() }
        val boxSet = Array(9) { mutableSetOf<Char>() }
        for (i in board.indices) {
            for (j in board[0].indices) {
                val char = board[i][j]
                if (board[i][j] == '.') {
                    continue
                }
                val subBoxIndex = (i / 3 * 3) + j / 3
                if (rowSet[i].contains(char) || colSet[j].contains(char) || boxSet[subBoxIndex].contains(char)) {
                    return false
                }
                rowSet[i].add(char)
                colSet[j].add(char)
                boxSet[subBoxIndex].add(char)
            }
        }
        return true
    }

    fun longestConsecutive(nums: IntArray): Int {
        val set = mutableSetOf<Int>()
        for (num in nums) {
            set.add(num)
        }
        var maxConsecutive = 0
        for (num in nums) {
            if (!set.contains(num - 1)) {
                var count = 1
                var curr = num
                while (set.contains(curr + 1)) {
                    count++
                    curr++
                }
                maxConsecutive = max(maxConsecutive, count)
            }
        }
        return maxConsecutive
    }

}